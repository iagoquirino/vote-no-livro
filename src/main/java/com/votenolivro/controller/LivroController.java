package com.votenolivro.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.votenolivro.converters.LivroConverter;
import com.votenolivro.converters.PessoaConverter;
import com.votenolivro.model.Pessoa;
import com.votenolivro.model.vo.LivroVO;
import com.votenolivro.model.vo.PessoaVO;
import com.votenolivro.service.LivroServiceImpl;
import com.votenolivro.service.PessoaServiceImpl;

@Controller
@RequestMapping(value = "livro")
@SessionAttributes("livrosVotados")
public class LivroController extends ProjetoController {
	
	@Autowired
	private LivroConverter livroConverter;
	@Autowired
	private LivroServiceImpl livroService;
	@Autowired
	private PessoaServiceImpl pessoaService;
	@Autowired
	private PessoaConverter pessoaConverter;
	
	
	private static final String LIVRO = "livro";
	private static final String REDIRECT = "redirect:/livro/";
	private static final String REDIRECT_RANKING = "redirect:/ranking/mostrar-ranking/";
	
	@RequestMapping(value = "/")
	public String init(ModelMap model)
	{
		model.addAttribute("livros", livroConverter.convertToListVO(livroService.listarLivrosParaVotar()));
		model.addAttribute("livro", new LivroVO());
		model.addAttribute("pessoa", new PessoaVO());
		return LIVRO;
	}
	
	@RequestMapping(value = "/teste")
	public String teste(ModelMap model)
	{
		model.addAttribute("livros", livroConverter.convertToListVO(livroService.listarTodos()));
		model.addAttribute("livro", new LivroVO());
		model.addAttribute("pessoa", new PessoaVO());
		return LIVRO;
	}
	
	@RequestMapping(value = "/votar", method = RequestMethod.POST)
    public String votar( @ModelAttribute(value = "livro") LivroVO livroVO, ModelMap model) {
		List<LivroVO> livros = new ArrayList<LivroVO>();
		if(model.containsAttribute("livrosVotados")){
			livros = (List<LivroVO>)model.get("livrosVotados");
		}
		livros.add(livroVO);
		model.addAttribute("livrosVotados", livros);
		return REDIRECT;
	}
	
	@RequestMapping(value = "/computarvotos", method = RequestMethod.POST)
    public String computarVotos( @ModelAttribute(value = "pessoa") PessoaVO pessoa, ModelMap model) throws Exception {
		List<LivroVO> livros = new ArrayList<LivroVO>();
		if(model.containsAttribute("livrosVotados")){
			livros = (List<LivroVO>)model.get("livrosVotados");
		}
		Pessoa pessoaPersitida = pessoaService.processarVotos(pessoaConverter.convertToModel(pessoa),livroConverter.convertToListModel(livros));
		return REDIRECT_RANKING+pessoaPersitida.getId().toString();
	}
	
	
	
	public void setLivroConverter(LivroConverter livroConverter) {
		this.livroConverter = livroConverter;
	}
	
	
	public void setLivroService(LivroServiceImpl livroService) {
		this.livroService = livroService;
	}
	
	public void setPessoaService(PessoaServiceImpl pessoaService) {
		this.pessoaService = pessoaService;
	}
	
	public void setPessoaConverter(PessoaConverter pessoaConverter) {
		this.pessoaConverter = pessoaConverter;
	}
}
