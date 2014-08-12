package com.votenolivro.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.votenolivro.model.livros.Livro;
import com.votenolivro.model.livros.LivroVotado;
import com.votenolivro.model.pessoa.Pessoa;
import com.votenolivro.repository.interfaces.IPessoaRepository;


public class PessoaServiceTest {

	PessoaServiceImpl pessoaService = new PessoaServiceImpl();
	
	@Mock
	private LivroServiceImpl livroService;
	@Mock
	private IPessoaRepository pessoaRepository;
	
	
	ArgumentMatcher<Pessoa> matcherLivroPessoaNova  = new ArgumentMatcher<Pessoa>() {
	    @Override
	    public boolean matches(final Object argument) {
	    	Pessoa pessoa = (Pessoa) argument;
	    	Assert.assertEquals(2, pessoa.getLivros().size());
	    	Assert.assertEquals(pessoa.getLivros().get(0).getTotalVoto(),1);
	    	Assert.assertEquals(pessoa.getLivros().get(1).getTotalVoto(),1);
	      return true;
	    }
	 };
	
	ArgumentMatcher<Pessoa> matcherLivroPessoaEncontrada  = new ArgumentMatcher<Pessoa>() {
	    @Override
	    public boolean matches(final Object argument) {
	    	Pessoa pessoa = (Pessoa) argument;
	    	Assert.assertEquals(2, pessoa.getLivros().size());
	    	Assert.assertEquals(pessoa.getLivros().get(0).getTotalVoto(),2);
	    	Assert.assertEquals(pessoa.getLivros().get(1).getTotalVoto(),2);
	      return true;
	    }
	 };

	 
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		pessoaService.setLivroService(livroService);
		pessoaService.setPessoaRepository(pessoaRepository);
	}
	
	@Test(expected = Exception.class)
	public void deveValidarQuandoProcessarVotosPessoaNula() throws Exception{
		pessoaService.processarVotos(null, null);
	}
	
	@Test(expected = Exception.class)
	public void deveValidarQuandoProcessarVotosPessoaNomeInvalido() throws Exception{
		Pessoa pessoa = new Pessoa();
		pessoaService.processarVotos(pessoa, getLivros());
	}
	
	@Test(expected = Exception.class)
	public void deveValidarQuandoProcessarVotosPessoaEmailInvalido() throws Exception{
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("nome");
		pessoaService.processarVotos(pessoa, getLivros());
	}
	
	@Test
	public void deveProcessarVotoDeUmaPessoaNova() throws Exception{
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("nome");
		pessoa.setEmail("email");
		List<Livro> livros = getLivros();
		pessoaService.processarVotos(pessoa, livros);
		Mockito.verify(livroService).processarVotos(Mockito.eq(livros));
		Mockito.verify(pessoaRepository).listarPessoaPorNomeEmail(Mockito.eq(pessoa.getNome()), Mockito.eq(pessoa.getEmail()));
		Mockito.verify(pessoaRepository).merge(Mockito.argThat(matcherLivroPessoaNova));
	}
	
	@Test
	public void deveProcessarVotoDeUmaPessoaEncontrada() throws Exception{
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("nome");
		pessoa.setEmail("email");
		Mockito.when(pessoaRepository.listarPessoaPorNomeEmail(Mockito.eq(pessoa.getNome()), Mockito.eq(pessoa.getEmail()))).thenReturn(getPessoaListada());
		List<Livro> livros = getLivros();
		pessoaService.processarVotos(pessoa, livros);
		Mockito.verify(livroService).processarVotos(Mockito.eq(livros));
		Mockito.verify(pessoaRepository).listarPessoaPorNomeEmail(Mockito.eq(pessoa.getNome()), Mockito.eq(pessoa.getEmail()));
		Mockito.verify(pessoaRepository).merge(Mockito.argThat(matcherLivroPessoaEncontrada));
	}
	

	private Pessoa getPessoaListada() {
		Pessoa pessoa = new Pessoa(1L, "nome", "email");
		pessoa.setLivros(getLivrosVotados());
		return pessoa;
	}

	private List<LivroVotado> getLivrosVotados() {
		List<Livro> livros = getLivros();
		List<LivroVotado> listLivrosVotados = new ArrayList<LivroVotado>();
		for (Livro livro: livros) {
			listLivrosVotados.add(new LivroVotado(livro));
		}
		return listLivrosVotados;
	}

	private List<Livro> getLivros() {
		return Arrays.asList(new Livro(1L, "nome"),new Livro(2L, "nome2"));
	}

}
