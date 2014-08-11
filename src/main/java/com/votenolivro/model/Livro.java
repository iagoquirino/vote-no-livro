package com.votenolivro.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;



@Entity
@SequenceGenerator(sequenceName = "SEQ_LIVRO" , name = "SEQ_LIVRO",allocationSize=1,initialValue=1)
public class Livro implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6843784565494627761L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_LIVRO")
	private Long id;
	
	private String nome;
	
	private int totalVoto;
	
	public Livro() {
		super();
	}
	public Livro(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public Livro(Long id) {
		this.id = id;
	}
	
	public Livro(Long id, String nome, int totalVoto) {
		this.id = id;
		this.nome = nome;
		this.totalVoto = totalVoto;
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
