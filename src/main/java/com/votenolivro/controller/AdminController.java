package com.votenolivro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.votenolivro.service.LivroService;


@Controller
@RequestMapping(value = "admin")
public class AdminController extends ProjetoController {

	@Autowired
	private LivroService livroService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String adminpage(){
		return "admin";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@RequestParam("nome")String nome){
		livroService.adicionar(nome);
		return "redirect:/admin/";
	}
	
}
