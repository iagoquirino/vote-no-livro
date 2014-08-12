package com.votenolivro.converters;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.votenolivro.model.pessoa.Pessoa;
import com.votenolivro.model.vo.PessoaVO;

public class PessoaConverterTest {

	PessoaConverter pessoaConverter = new PessoaConverter();
	
	@Mock
	private LivroConverter livroConverter;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		pessoaConverter.setLivroConverter(livroConverter);
	}
	
	@Test
	public void deveConverterToListPessoaVO(){
		Pessoa pessoa = getPessoa();
		PessoaVO pessoaVO = pessoaConverter.convertToVO(pessoa);
		Assert.assertEquals(pessoa.getId(), pessoaVO.getId());
		Assert.assertEquals(pessoa.getNome(), pessoaVO.getNome());
		Assert.assertEquals(pessoa.getEmail(), pessoaVO.getEmail());
		Mockito.verify(livroConverter).convertLivrosVotadosToLivroVO(Mockito.anyList());
	}

	private Pessoa getPessoa() {
		return new Pessoa(1L,"nome","email");
	}

}
