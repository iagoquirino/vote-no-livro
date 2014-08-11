package com.votenolivro.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.votenolivro.converters.LivroConverter;
import com.votenolivro.converters.PessoaConverter;
import com.votenolivro.model.vo.LivroVO;
import com.votenolivro.model.vo.PessoaVO;
import com.votenolivro.service.LivroServiceImpl;
import com.votenolivro.service.PessoaServiceImpl;

@Controller
@RequestMapping(value = "ranking")
@SessionAttributes("livrosVotados")
public class RankingController extends ProjetoController{
	
	private static final String RANKING = "ranking";
	
	@Autowired
	private PessoaServiceImpl pessoaService;
	@Autowired
	private LivroServiceImpl livroService;
	@Autowired
	private LivroConverter livroConverter;
	@Autowired
	private PessoaConverter pessoaConverter;
	
	@RequestMapping(value = "/mostrar-ranking/{idPessoa}", method = RequestMethod.GET)
	public String verRanking(@PathVariable(value = "idPessoa") Long idPessoa,ModelMap model) throws Exception{
		model.addAttribute("livrosVotados", null);
		return processarRanking(idPessoa, model);
	}

	@RequestMapping(value = "/mostrar-ranking/", method = RequestMethod.GET)
	public String verRanking(ModelMap model) throws Exception{
		return processarRanking(null, model);
	}
	
	private String processarRanking(Long idPessoa, ModelMap model) {
		model.addAttribute("pessoa", pessoaConverter.convertToVO(pessoaService.getPessoa(idPessoa)));
		model.addAttribute("livros", livroConverter.convertToListVO(livroService.listarRanking()));
		return RANKING;
	}
	
	private PessoaVO getMockPessoa() {
		PessoaVO pessoa = new PessoaVO();
		pessoa.setEmail("teste");
		pessoa.setNome("teste");
		pessoa.setLivros(getMock());
		return pessoa;
	}

	private List<LivroVO> getMock() {
		return Arrays.asList(new LivroVO(1, "rsds", 2));
	}

	public void setPessoaService(PessoaServiceImpl pessoaService) {
		this.pessoaService = pessoaService;
	}
	
	public void setLivroConverter(LivroConverter livroConverter) {
		this.livroConverter = livroConverter;
	}
	
	public void setLivroService(LivroServiceImpl livroService) {
		this.livroService = livroService;
	}
}
