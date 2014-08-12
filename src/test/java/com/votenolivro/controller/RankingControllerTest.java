package com.votenolivro.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.votenolivro.converters.LivroConverter;
import com.votenolivro.converters.PessoaConverter;
import com.votenolivro.model.pessoa.Pessoa;
import com.votenolivro.model.vo.PessoaVO;
import com.votenolivro.service.LivroServiceImpl;
import com.votenolivro.service.PessoaServiceImpl;

public class RankingControllerTest extends BaseControllerTest{

	RankingController controller = new RankingController();

	@Mock LivroConverter livroConverter;
	@Mock PessoaConverter pessoaConverter;
	@Mock LivroServiceImpl livroServiceImpl;
	@Mock PessoaServiceImpl pessoaServiceImpl;
	
	private String RANKING_CALL = "/ranking/mostrar-ranking/";
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		init(controller);
		controller.setLivroConverter(livroConverter);
		controller.setLivroService(livroServiceImpl);
		controller.setPessoaService(pessoaServiceImpl);
		controller.setPessoaConverter(pessoaConverter);
	}
	
	@Test
	public void deveMostrarRanking() throws Exception{
		Mockito.when(pessoaConverter.convertToVO(Mockito.any(Pessoa.class))).thenReturn(new PessoaVO());
    	String JSP_PAGE = "ranking";
		getMockMvc().perform(get("/ranking/mostrar-ranking/{idPessoa}","1").param("idPessoa", "1"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl(JSP_PAGE))
                .andExpect(model().attributeExists("livros"))
                .andExpect(model().attributeExists("pessoa"));
		Mockito.verify(pessoaServiceImpl).getPessoa(Mockito.anyLong());
		
	}
	
	@Test
	public void deveMostrarRankingSemUsuarioPreenchido() throws Exception{
    	String JSP_PAGE = "ranking";
		getMockMvc().perform(get(RANKING_CALL))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl(JSP_PAGE))
                .andExpect(model().attributeExists("livros"));
		Mockito.verify(pessoaServiceImpl,Mockito.never()).getPessoa(Mockito.anyLong());
	}
	
}
