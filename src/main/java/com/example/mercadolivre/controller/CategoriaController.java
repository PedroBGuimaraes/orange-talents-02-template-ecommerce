package com.example.mercadolivre.controller;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mercadolivre.dto.NovaCategoriaRequest;
import com.example.mercadolivre.model.Categoria;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
	
	@Autowired
	private EntityManager manager;

	@PostMapping
	@Transactional
	public String postNovaCategoria(@RequestBody @Valid NovaCategoriaRequest novaCategoriaRequest) {
		Categoria novaCategoria = novaCategoriaRequest.toModel(manager);
		manager.persist(novaCategoria);
		return novaCategoria.toString();
	}
}
