package com.votenolivro.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.votenolivro.exception.VoteNoLivroException;

public class ProjetoController {

	@ExceptionHandler(value=VoteNoLivroException.class)
    public String tratarErro(ModelMap model, HttpServletResponse response){
		return "erro";
    }
	
}
