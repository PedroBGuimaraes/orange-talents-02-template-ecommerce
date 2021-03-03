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

import com.example.mercadolivre.dto.NovaOpniaoRequest;
import com.example.mercadolivre.model.Opniao;
import com.example.mercadolivre.model.Produto;
import com.example.mercadolivre.model.Usuario;

@RestController
@RequestMapping("/produto/{id}/opniao")
public class OpniaoController {
	
	@Autowired
	private EntityManager manager;
	
	@PostMapping
	@Transactional
	public String postNovaOpniao(@RequestBody @Valid NovaOpniaoRequest request, @PathVariable("id") Integer id) {
		Produto produto = manager.find(Produto.class, id);
		Usuario dono = manager.find(Usuario.class, (long) 1);
		Opniao novaOpniao = request.toModel(produto,dono);
		manager.persist(novaOpniao);
		return novaOpniao.toString();
	}

}
