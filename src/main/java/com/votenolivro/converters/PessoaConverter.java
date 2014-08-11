package com.votenolivro.converters;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.votenolivro.model.Pessoa;
import com.votenolivro.model.vo.PessoaVO;

@Component
public class PessoaConverter {
	
	String[] ignores = {"livros"};
	
	@Autowired
	private LivroConverter livroConverter;
	
	public PessoaVO convertToVO(Pessoa pessoa) {
		if(pessoa == null){
			return null;	
		}
		PessoaVO pessoaVO = new PessoaVO();
		BeanUtils.copyProperties(pessoa, pessoaVO, ignores);
		pessoaVO.setLivros(livroConverter.convertLivrosVotadosToLivroVO(pessoa.getLivros()));
		return pessoaVO;
	}

	
	public void setLivroConverter(LivroConverter livroConverter) {
		this.livroConverter = livroConverter;
	}

	public Pessoa convertToModel(PessoaVO pessoaVO) {
		if(pessoaVO == null){
			return null;	
		}
		Pessoa pessoa = new Pessoa();
		BeanUtils.copyProperties(pessoaVO,pessoa, ignores);
		return pessoa;
	}
}
