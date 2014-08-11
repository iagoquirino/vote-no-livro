package com.votenolivro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.votenolivro.service.LivroServiceImpl;
import com.votenolivro.service.PessoaServiceImpl;


@Controller
@RequestMapping(value = "admin")
public class AdminController extends ProjetoController {

	@Autowired
	private LivroServiceImpl livroService;
	
	@Autowired
	private PessoaServiceImpl pessoaService;
	
	@RequestMapping(value = "/cargaLivros", method = RequestMethod.GET)
	public String adminpage(){
		livroService.cargaLivros();
		return "admin";
	}
	
	@RequestMapping(value = "/cargaPessoas", method = RequestMethod.GET)
	public String add(@RequestParam("nome")String nome){
		pessoaService.cargaPessoa();
		return "admin";
	}
	
	public void setLivroService(LivroServiceImpl livroService) {
		this.livroService = livroService;
	}
	
	
	public void setPessoaService(PessoaServiceImpl pessoaService) {
		this.pessoaService = pessoaService;
	}
}
