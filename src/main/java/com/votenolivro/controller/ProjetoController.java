package com.votenolivro.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class ProjetoController {

	@ExceptionHandler(value=Exception.class)
    public String tratarErro(Exception exception,ModelMap model, HttpServletResponse response){
		return "erro";
    }
	
}
