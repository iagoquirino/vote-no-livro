package com.votenolivro.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity(name = "livroVotado")
@SequenceGenerator(sequenceName = "SEQ_LIVROVOT" , name = "LIVROVOT_SEQ",allocationSize=1,initialValue=1)
public class LivroVotado {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LIVROVOT_SEQ")
	private Long id;
	
	@OneToOne(targetEntity = Livro.class)
	private Livro livro;
	
	private int totalVoto = 1;

	public LivroVotado() {
		super();
	}
	
	public LivroVotado(Livro livro) {
		this.livro = livro;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public int getTotalVoto() {
		return totalVoto;
	}

	public void setTotalVoto(int totalVoto) {
		this.totalVoto = totalVoto;
	}

	public void adicionarVoto() {
		this.totalVoto = this.getTotalVoto()+1;
	}
}
