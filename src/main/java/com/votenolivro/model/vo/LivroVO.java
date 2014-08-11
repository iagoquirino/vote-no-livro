package com.votenolivro.model.vo;

public class LivroVO {
	private Long id;
	
	private String nome;
	
	private int totalVoto;
	
	public LivroVO() {
		super();
	}
	
	public LivroVO(long id, String nome, int totalVoto) {
		this.id = id;
		this.nome  = nome;
		this.totalVoto = totalVoto;
	}
	public Long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public void setId(Long id) {
		this.id = id;
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
}
