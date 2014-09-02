package com.votenolivro.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.votenolivro.exception.VoteNoLivroException;
import com.votenolivro.model.livros.Livro;
import com.votenolivro.model.livros.LivroVotado;
import com.votenolivro.model.pessoa.Pessoa;
import com.votenolivro.repository.interfaces.IPessoaRepository;

@Service
@Transactional
public class PessoaServiceImpl {
	
	@Autowired
	private LivroServiceImpl livroService;
	
	@Autowired
	private IPessoaRepository pessoaRepository;
	
	public void processarVotos(Pessoa pessoa, List<Livro> livros) throws Exception {
		validar(pessoa);
		Pessoa pessoaBanco = listarPessoaPorNomeEmail(pessoa.getNome(),pessoa.getEmail());
		if(pessoaBanco != null){
			pessoa = pessoaBanco;
		}
		livroService.processarVotos(livros);
		processarVotoPessoa(pessoa,livros);
		pessoaRepository.merge(pessoa);
		pessoaRepository.flush();
	}
	
	private void processarVotoPessoa(Pessoa pessoa, List<Livro> livros) {
		if(pessoa.getLivros() == null || pessoa.getLivros().isEmpty()){
			List<LivroVotado> listLivrosVotados = setLivrosVotados(livros);
			pessoa.setLivros(listLivrosVotados);
		}else{
			Map<Long, LivroVotado> map = mapTransformToMap(pessoa.getLivros());
			if(livros != null && !livros.isEmpty()){
				for (Livro livro : livros) {
					Long key = livro.getId();
					if(map.containsKey(key)){
						map.get(key).adicionarVoto();
					}else{
						map.put(key, new LivroVotado(livro));
					}
				}
			}
			pessoa.setLivros(new ArrayList<LivroVotado>(map.values()));
		}
	}


	private Map<Long, LivroVotado> mapTransformToMap(List<LivroVotado> livros) {
		Map<Long, LivroVotado> map = new HashMap<Long, LivroVotado>();
		if(livros != null && !livros.isEmpty()){
			for (LivroVotado livroVotado : livros) {
				Long key = livroVotado.getLivro().getId();
				map.put(key, livroVotado);
			}	
		}
		return map;
	}


	private List<LivroVotado> setLivrosVotados(List<Livro> livros) {
		List<LivroVotado> listLivrosVotados = new ArrayList<LivroVotado>();
		if(livros != null){
			for (Livro livro : livros) {
				listLivrosVotados.add(new LivroVotado(livro));
			}
		}
		return listLivrosVotados;
	}


	private void validar(Pessoa pessoa) throws Exception {
		if(pessoa == null){
			throw new VoteNoLivroException("erro.invalido");
		}
		if(pessoa.getNome() == null || pessoa.getNome().isEmpty()){
			throw new VoteNoLivroException("erro.invalido.nome");
		}
		if(pessoa.getEmail() == null || pessoa.getEmail().isEmpty()){
			throw new VoteNoLivroException("erro.invalido.email");
		}
	}


	private Pessoa listarPessoaPorNomeEmail(String nome,String email) {
		return pessoaRepository.listarPessoaPorNomeEmail(nome,email);
	}

	public Pessoa getPessoa(Long idPessoa) {
		return pessoaRepository.loadById(idPessoa);
	}

	public void setLivroService(LivroServiceImpl livroService) {
		this.livroService = livroService;
	}

	public void setPessoaRepository(IPessoaRepository pessoaRepository) {
		this.pessoaRepository = pessoaRepository;
	}

	public void cargaPessoa() {
		List<Pessoa> listPessoas = new ArrayList<Pessoa>();
		listPessoas.add(new Pessoa("teste", "teste"));
		listPessoas.add(new Pessoa("teste1", "teste1"));
		listPessoas.add(new Pessoa("teste2", "teste2"));
		listPessoas.add(new Pessoa("teste3", "teste3"));
		listPessoas.add(new Pessoa("teste4", "teste4"));
		pessoaRepository.saveOrUpdateAll(listPessoas);
		
	}

	public Pessoa getPessoa(String nome, String email) {
		return listarPessoaPorNomeEmail(nome, email);
	}

}
