package com.example.mercadolivre.controller;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mercadolivre.dto.NovaCompraRequest;
import com.example.mercadolivre.dto.RetornoPagSeguroRequest;
import com.example.mercadolivre.model.Compra;
import com.example.mercadolivre.model.Produto;
import com.example.mercadolivre.model.Usuario;
import com.example.mercadolivre.utils.GatewayPagamento;

@RestController
@RequestMapping("/compras")
public class FechaCompraController {
	
	@Autowired
	private EntityManager manager;
	
	@PostMapping
	@Transactional
	public String postNovaCompra(@RequestBody @Valid NovaCompraRequest request) throws BindException {
		Produto produtoSerComprado = manager.find(Produto.class, request.getIdProduto());
		Boolean abateu = produtoSerComprado.abateEstoque(request.getQuantidade());
		if(abateu) {
			Usuario comprador = manager.find(Usuario.class, (long) 1);
			Compra novaCompra = new Compra(produtoSerComprado, request.getQuantidade(), comprador, request.getGateway());
			manager.persist(novaCompra);
			if(request.getGateway().equals(GatewayPagamento.pagseguro)) {
				return "pagseguro.com?returnId="+novaCompra.getId()+"?}&redirectUrl={urlRetornoAppPosPagamento}";
			}else {
				return "paypal.com/"+novaCompra.getId()+"?}?redirectUrl={urlRetornoAppPosPagamento}";
			}
			
		}
		BindException problemaComEstoque = new BindException(request, "NovaCompraRequest");
		problemaComEstoque.reject(null, "Não foi possivel realizar a compra por conta da quantidade do estoque");
		
		throw problemaComEstoque;
	}
	
	@PostMapping(value = "/retorno-pag-seguro/{id}")
	@Transactional
	public String postRetornoPagSeguro(@PathVariable("id") Integer id, @Valid RetornoPagSeguroRequest request) {
		Compra compra = manager.find(Compra.class, id);
		compra.adicionaTransacao(request);
		
		manager.merge(compra);
		
		return request.toString();
	}
	
	
}
