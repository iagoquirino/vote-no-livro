package com.votenolivro;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.votenolivro.converters.LivroConverterTest;
import com.votenolivro.converters.PessoaConverterTest;
import com.votenolivro.service.LivroServiceTest;
import com.votenolivro.service.PessoaServiceTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	LivroServiceTest.class,
	LivroConverterTest.class,
	PessoaServiceTest.class,
	PessoaConverterTest.class
})
public class SuiteTesteUnitario {

}
