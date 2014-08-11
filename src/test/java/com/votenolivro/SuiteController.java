package com.votenolivro;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.votenolivro.controller.LivroControllerTest;
import com.votenolivro.controller.RankingControllerTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	LivroControllerTest.class,
	RankingControllerTest.class
})
public class SuiteController {
  
}