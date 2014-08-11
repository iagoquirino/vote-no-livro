package com.votenolivro.repository.interfaces;

import com.votenolivro.model.Pessoa;

public interface IPessoaRepository extends IBaseRepositoryHibernate<Pessoa> {

	Pessoa listarPessoaPorNomeEmail(String nome, String email);

}
