package com.votenolivro.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;



@Entity
@SequenceGenerator(sequenceName = "SEQ_PESSOA" , name = "PESSOA_SEQ",allocationSize=1,initialValue=1)
public class Pessoa implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8147702006081300144L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PESSOA_SEQ")
	private Long id;
	
	private String nome;
	
	private String email;
	
	@OneToMany(fetch =FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name="pessoa_id")
	private List<LivroVotado> livros;

	public Pessoa() {
		super();
	}
	
	public Pessoa(Long id, String nome, String email) {
		this.id = id;
		this.nome = nome;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<LivroVotado> getLivros() {
		return livros;
	}
	
	public void setLivros(List<LivroVotado> livros) {
		this.livros = livros;
	}
}
