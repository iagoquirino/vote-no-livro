package com.votenolivro.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.votenolivro.model.Livro;
import com.votenolivro.model.LivroVotado;
import com.votenolivro.model.vo.LivroVO;

@Component
public class LivroConverter {

	String[] ignores = {""};
	
	public List<LivroVO> convertToListVO(List<Livro> livros) {
		List<LivroVO> livrosVO = new ArrayList<LivroVO>();
		if(livros != null){
			for (Livro livro : livros) {
				LivroVO livroVO = convertToVO(livro);
				if(livroVO != null){
					livrosVO.add(livroVO);
				}
			}
		}
		return livrosVO;
	}

	private LivroVO convertToVO(Livro livro) {
		if(livro == null){
			return null;	
		}
		LivroVO livroVO = new LivroVO();
		BeanUtils.copyProperties(livro, livroVO, ignores);
		return livroVO;
	}

	public List<Livro> convertToListModel(List<LivroVO> livrosVO) {
		List<Livro> livros = new ArrayList<Livro>();
		if(livrosVO != null){
			for (LivroVO livroVO : livrosVO) {
				Livro livro = convertToModel(livroVO);
				if(livro != null){
					livros.add(livro);
				}
			}
		}
		return livros;
	}

	private Livro convertToModel(LivroVO livroVO) {
		if(livroVO == null){
			return null;	
		}
		Livro livro = new Livro();
		BeanUtils.copyProperties(livroVO, livro, ignores);
		return livro;	
	}

	public List<LivroVO> convertLivrosVotadosToLivroVO(List<LivroVotado> livros) {
		List<LivroVO> listVO = new ArrayList<LivroVO>();
		if(livros != null){
			for (LivroVotado livroVotado : livros) {
				LivroVO livroVO = convertLivroVotadoToLivroVO(livroVotado);
				if(livroVO != null){
					listVO.add(livroVO);
				}
			}
		}
		return listVO;
	}

	private LivroVO convertLivroVotadoToLivroVO(LivroVotado livroVotado) {
		if(livroVotado == null || livroVotado.getLivro() == null){
			return null;	
		}
		LivroVO livroVO = new LivroVO();
		BeanUtils.copyProperties(livroVotado.getLivro(), livroVO, ignores);
		livroVO.setTotalVoto(livroVotado.getTotalVoto());
		return livroVO;
	}

}
