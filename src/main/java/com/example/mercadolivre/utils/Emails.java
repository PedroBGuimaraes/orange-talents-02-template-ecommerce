package com.example.mercadolivre.utils;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mercadolivre.model.Pergunta;


@Service
public class Emails {
	
	@Autowired
	//1
	private Mailer mailer;

	//1
	public void novaPergunta(@NotNull @Valid Pergunta pergunta) {
		mailer.send("<html>...</html>","Nova pergunta...",pergunta.getDono().getEmail(),"novapergunta@nossomercadolivre.com",pergunta.getDonoProduto().getEmail());
	}
}
