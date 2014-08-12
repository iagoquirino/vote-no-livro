package com.votenolivro.repository.interfaces;

import com.votenolivro.model.pessoa.Pessoa;

public interface IPessoaRepository extends IBaseRepositoryHibernate<Pessoa> {

	Pessoa listarPessoaPorNomeEmail(String nome, String email);

}
