package com.votenolivro.converters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.votenolivro.model.Livro;
import com.votenolivro.model.LivroVotado;
import com.votenolivro.model.vo.LivroVO;

public class LivroConverterTest {
	LivroConverter livroConverter = new LivroConverter();
	
	@Test
	public void deveConverterListaDeLivrosParaListaVO(){
		List<Livro> livros = getLivros();
		List<LivroVO> listVO = livroConverter.convertToListVO(livros);
		Assert.assertEquals(livros.size(), listVO.size());
		assertLivro(livros.get(0),listVO.get(0));
		assertLivro(livros.get(1),listVO.get(1));
	}
	
	@Test
	public void deveConverterListaVOParaListaDeLivros(){
		List<LivroVO> livrosVO = getLivrosVO();
		List<Livro> list = livroConverter.convertToListModel(livrosVO);
		Assert.assertEquals(list.size(), livrosVO.size());
		assertLivro(list.get(0),livrosVO.get(0));
		assertLivro(list.get(1),livrosVO.get(1));
	}
	
	@Test
	public void deveConverterListaLivroVotadoParaListaVO(){
		List<LivroVotado> livros = getLivrosVotados();
		List<LivroVO> listVO = livroConverter.convertLivrosVotadosToLivroVO(livros);
		Assert.assertEquals(livros.size(), listVO.size());
		assertLivroVotado(livros.get(0),listVO.get(0));
		assertLivroVotado(livros.get(1),listVO.get(1));
	}
	
	private void assertLivroVotado(LivroVotado livroVotado, LivroVO livroVO) {
		Assert.assertEquals(livroVotado.getLivro().getId(), livroVO.getId());
		Assert.assertEquals(livroVotado.getLivro().getNome(), livroVO.getNome());
		Assert.assertEquals(livroVotado.getTotalVoto(), livroVO.getTotalVoto());
	}

	private List<LivroVotado> getLivrosVotados() {
		List<LivroVotado> votados = new ArrayList<LivroVotado>();
		List<Livro> livros = getLivros();
		for (Livro livro : livros) {
			LivroVotado livroVotadoCriado = new LivroVotado(livro);
			livroVotadoCriado.setTotalVoto(3);
			votados.add(livroVotadoCriado);
		}
		return votados;
	}

	private List<LivroVO> getLivrosVO() {
		return Arrays.asList(new LivroVO(1L,"tesss",3),new LivroVO(2L,"tesss22",8));
	}

	private void assertLivro(Livro livro, LivroVO livroVO) {
		Assert.assertEquals(livro.getId(), livroVO.getId());
		Assert.assertEquals(livro.getNome(), livroVO.getNome());
		Assert.assertEquals(livro.getTotalVoto(), livroVO.getTotalVoto());
	}

	private List<Livro> getLivros() {
		return Arrays.asList(new Livro(1L, "teste", 4),new Livro(2L, "test2",6));
	}

}
