package com.example.mercadolivre.controller;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.mercadolivre.dto.DetalheProdutoView;
import com.example.mercadolivre.dto.NovoImagemRequest;
import com.example.mercadolivre.dto.NovoProdutoRequest;
import com.example.mercadolivre.model.Produto;
import com.example.mercadolivre.model.Usuario;
import com.example.mercadolivre.utils.UploaderFake;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
	
	@Autowired
	private EntityManager manager;
	@Autowired
	private UploaderFake uploaderFake;

	@PostMapping
	@Transactional
	public String postNovoProduto(@RequestBody @Valid NovoProdutoRequest request) {
		@NotNull Usuario dono = manager.find(Usuario.class, (long) 1);
		Produto novoProduto = request.toModel(manager, dono);
		manager.persist(novoProduto);
		return novoProduto.toString();
	}
	
	@PostMapping(value = "/{id}/imagem")
	@Transactional
	public String postNovaImagem(@PathVariable Integer id, @Valid NovoImagemRequest request) {
		@NotNull Usuario dono = manager.find(Usuario.class, (long) 1);
		Produto produto = manager.find(Produto.class, id);
		if(!produto.pertenceAoUsuario(dono)) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		
		Set<String> links = uploaderFake.envia(request.getImagens());
		
		produto.associaImagens(links);
		manager.merge(produto);
		return produto.toString();
	}
	
	@GetMapping(value = "/{id}")
	public DetalheProdutoView getProdutoDetalhes(@PathVariable("id") Integer id) {
		Produto produtoEscolhido = manager.find(Produto.class, id);
		return new DetalheProdutoView(produtoEscolhido);
	}
	
	
}
