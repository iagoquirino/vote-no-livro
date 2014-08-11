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

import com.votenolivro.model.Livro;
import com.votenolivro.repository.interfaces.ILivroRepository;

public class LivroServiceTest {

	@Mock
	private ILivroRepository livroRepository;
	
	private LivroService livroService = new LivroService();
	
	private List<Livro> todosOsLivros = getAllLivros();
	
	
	ArgumentMatcher<List<Livro>> matcherLivros  = new ArgumentMatcher<List<Livro>>() {
	    @Override
	    public boolean matches(final Object argument) {
	    	List<Livro> livrosVotados = (List<Livro>) argument;
	    	Assert.assertEquals(2, livrosVotados.size());
	    	Assert.assertEquals(livrosVotados.get(0).getTotalVoto(),2);
	    	Assert.assertEquals(livrosVotados.get(1).getTotalVoto(),1);
	      return true;
	    }
	 };
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		livroService.setLivroRepository(livroRepository);
		Mockito.when(livroRepository.listAll()).thenReturn(todosOsLivros);
	}
	
	@Test
	public void deveListarLivrosParaVotacao(){
		List<Livro> listarParaVotar = livroService.listarLivrosParaVotar();
		Assert.assertEquals(2, listarParaVotar.size());
		Mockito.verify(livroRepository).listAll();
		Assert.assertTrue(todosOsLivros.containsAll(listarParaVotar));
	}
	
	@Test
	public void deveProcessarVotos() throws Exception{
		livroService.processarVotos(getVotosLivros());
		Mockito.verify(livroRepository).listAll();
		Mockito.verify(livroRepository).saveOrUpdateAll(Mockito.argThat(matcherLivros));
	}
	
	@Test
	public void naoDeveProcessarVotosQuandoEnviadoNull() throws Exception{
		livroService.processarVotos(null);
		Mockito.verify(livroRepository,Mockito.never()).listAll();
		Mockito.verify(livroRepository,Mockito.never()).saveOrUpdateAll(Mockito.anyList());
	}
	
	@Test
	public void naoDeveProcessarVotosQuandoEnviadoListaVazia() throws Exception{
		livroService.processarVotos(new ArrayList<Livro>());
		Mockito.verify(livroRepository,Mockito.never()).listAll();
		Mockito.verify(livroRepository,Mockito.never()).saveOrUpdateAll(Mockito.anyList());
	}
	
	private List<Livro> getVotosLivros() {
		return Arrays.asList(new Livro(1L),new Livro(1L),new Livro(2L));
	}

	private List<Livro> getAllLivros() {
		return Arrays.asList(new Livro(1L, "teste1"),new Livro(2L, "teste2"), new Livro(3L, "teste3"),new Livro(4L, "teste4"),new Livro(5L, "teste5"));
	}
}
