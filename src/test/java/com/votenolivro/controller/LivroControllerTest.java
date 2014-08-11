package com.votenolivro.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.votenolivro.converters.LivroConverter;
import com.votenolivro.converters.PessoaConverter;
import com.votenolivro.exception.DAOException;
import com.votenolivro.model.Livro;
import com.votenolivro.model.Pessoa;
import com.votenolivro.model.vo.LivroVO;
import com.votenolivro.model.vo.PessoaVO;
import com.votenolivro.service.LivroServiceImpl;
import com.votenolivro.service.PessoaServiceImpl;

public class LivroControllerTest extends BaseControllerTest{

	LivroController controller = new LivroController();
	
	@Mock
	LivroServiceImpl livroService;
	
	@Mock
	LivroConverter livroConverter;
	
	@Mock 
	PessoaServiceImpl pessoaService;
	
	@Mock
	PessoaConverter pessoaConverter;
	
	private String LIVRO_CALL = "/livro/";
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		init(controller);
		controller.setLivroService(livroService);
		controller.setLivroConverter(livroConverter);
		controller.setPessoaConverter(pessoaConverter);
		controller.setPessoaService(pessoaService);
	}
	
	@Test
	public void deveMostrarTelaPrincipalDeVotacao() throws Exception{
    	String JSP_PAGE = "livro";
		getMockMvc().perform(get(LIVRO_CALL))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl(JSP_PAGE))
                .andExpect(model().attribute("livros", hasSize(0)))
                .andExpect(model().attributeExists("livro"))
                .andExpect(model().attributeExists("pessoa"));
		Mockito.verify(livroConverter).convertToListVO(Mockito.anyList());
		Mockito.verify(livroService).listarLivrosParaVotar();
	}
	
	@Test
	public void deveComputarVotos() throws Exception{
    	String JSP_PAGE = "livro";
		getMockMvc().perform(post(LIVRO_CALL+"votar").sessionAttr("livro", new LivroVO()))
                .andExpect(status().isMovedTemporarily())
                .andExpect(redirectedUrl("/livro/"))
                .andExpect(model().attribute("livrosVotados", hasSize(1)));
	}
	
	@Test
	public void devePersitirPessoaERedirecionarParaORanking() throws Exception{
		Mockito.when(pessoaService.processarVotos(Mockito.any(Pessoa.class), Mockito.anyList())).thenReturn(new Pessoa(1L, "teste", "teste"));
    	String JSP_PAGE = "livro";
		getMockMvc().perform(post(LIVRO_CALL+"computarvotos").sessionAttr("pessoa", new PessoaVO()))
                .andExpect(status().isMovedTemporarily())
                .andExpect(redirectedUrl("/ranking/mostrar-ranking/1"));
		Mockito.verify(livroConverter).convertToListModel(Mockito.anyList());
		Mockito.verify(pessoaConverter).convertToModel(Mockito.any(PessoaVO.class));
	}
	
	@Test
	public void deveIrParaPaginaDeErroQuandoExeception() throws Exception{
		Mockito.when(livroService.listarLivrosParaVotar()).thenThrow(new DAOException(Livro.class, ""));
    	String JSP_PAGE = "erro";
		getMockMvc().perform(get(LIVRO_CALL))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl(JSP_PAGE));
	}
	
}
