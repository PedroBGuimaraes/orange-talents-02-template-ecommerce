package com.example.mercadolivre.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.example.mercadolivre.utils.GatewayPagamento;

public class NovaCompraRequest {
	
	@Positive
	private int quantidade;
	@NotNull
	private int idProduto;
	@NotNull
	private GatewayPagamento gateway;
	
	public NovaCompraRequest(@Positive int quantidade, @NotNull int idProduto, @NotNull GatewayPagamento gateway) {
		super();
		this.quantidade = quantidade;
		this.idProduto = idProduto;
		this.gateway = gateway;
	}
	
	public int getIdProduto() {
		return idProduto;
	}
	
	public int getQuantidade() {
		return quantidade;
	}
	
	public GatewayPagamento getGateway() {
		return gateway;
	}
	
}
