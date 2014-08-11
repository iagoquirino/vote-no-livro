package com.votenolivro.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(sequenceName = "SEQ_LIVROVOT" , name = "LIVROVOT_SEQ",allocationSize=1,initialValue=1)
public class LivroVotado implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1294162046064272961L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LIVROVOT_SEQ")
	private Long id;
	
	@OneToOne
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
