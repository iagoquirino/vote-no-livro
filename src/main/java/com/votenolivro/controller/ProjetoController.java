package com.votenolivro.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;

import com.votenolivro.exception.VoteNoLivroException;

public class ProjetoController {

	@ExceptionHandler(VoteNoLivroException.class)
    public String tratarErro(VoteNoLivroException voteNoLivroException){
		return "erro";
    }
	
}
