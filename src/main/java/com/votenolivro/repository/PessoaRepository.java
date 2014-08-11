package com.votenolivro.repository;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.votenolivro.model.Pessoa;
import com.votenolivro.repository.interfaces.IPessoaRepository;

@Repository
@Transactional
public class PessoaRepository extends BaseRepositoryHibernate<Pessoa> implements IPessoaRepository{

	@Override
	protected Class<Pessoa> getEntityClass() {
		return Pessoa.class;
	}

	public Pessoa listarPessoaPorNomeEmail(String nome, String email) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(getEntityClass());
		detachedCriteria.add(Restrictions.eq("nome", nome));
		detachedCriteria.add(Restrictions.eq("email", email));
		return findByOneCriteria(detachedCriteria);
	}


}
