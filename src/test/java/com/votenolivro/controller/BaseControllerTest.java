package com.votenolivro.controller;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.mvc.Controller;


public class BaseControllerTest {

    private MockMvc mockMvc;
    
    public void init(Controller controller){
    	 this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }
    
    public MockMvc getMockMvc(){
    	return this.mockMvc;
    }
}
