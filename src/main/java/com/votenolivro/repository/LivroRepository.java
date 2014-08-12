package com.votenolivro.repository;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.votenolivro.model.livros.Livro;
import com.votenolivro.repository.interfaces.ILivroRepository;

@Repository
@Transactional
public class LivroRepository extends BaseRepositoryHibernate<Livro> implements ILivroRepository{

	@Override
	protected Class<Livro> getEntityClass() {
		return Livro.class;
	}

	public List<Livro> listarRanking() {
		DetachedCriteria detached = DetachedCriteria.forClass(getEntityClass());
		detached.addOrder(Order.desc("totalVoto"));
		detached.getExecutableCriteria(getSession()).setMaxResults(5);
		return findByCriteria(detached);
	}

}
