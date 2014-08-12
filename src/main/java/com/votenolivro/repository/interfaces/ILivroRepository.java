package com.votenolivro.repository.interfaces;

import java.util.List;

import com.votenolivro.model.livros.Livro;


public interface ILivroRepository extends IBaseRepositoryHibernate<Livro> {
	List<Livro> listarRanking();
}
