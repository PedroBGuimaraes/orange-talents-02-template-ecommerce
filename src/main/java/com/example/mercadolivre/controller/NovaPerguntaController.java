package com.example.mercadolivre.controller;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mercadolivre.dto.NovaPerguntaRequest;
import com.example.mercadolivre.model.Pergunta;
import com.example.mercadolivre.model.Produto;
import com.example.mercadolivre.model.Usuario;
import com.example.mercadolivre.utils.Emails;

@RestController
@RequestMapping("/produto/{id}/perguntas")
public class NovaPerguntaController {

	@Autowired
	private EntityManager manager;
	
	@Autowired
	private Emails emails;
	
	@PostMapping
	@Transactional
	public String postNovaPergunta(@RequestBody @Valid NovaPerguntaRequest request, @PathVariable("id") Integer id) {
		Produto produto = manager.find(Produto.class, id);
		Usuario dono = manager.find(Usuario.class, (long)1);
		Pergunta novaPergunta = request.toModel(produto, dono);
		manager.persist(novaPergunta);
		
		emails.novaPergunta(novaPergunta);
		
		return novaPergunta.toString();
	}
	
}
