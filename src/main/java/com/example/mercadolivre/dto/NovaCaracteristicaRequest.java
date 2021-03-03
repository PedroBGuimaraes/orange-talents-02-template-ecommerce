package com.example.mercadolivre.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.example.mercadolivre.model.CaracteristicaProduto;
import com.example.mercadolivre.model.Produto;

public class NovaCaracteristicaRequest {
	@NotBlank
	private String nome;
	@NotBlank
	private String descricao;
	public NovaCaracteristicaRequest(@NotBlank String nome, @NotBlank String descricao) {
		super();
		this.nome = nome;
		this.descricao = descricao;
	}
	public String getNome() {
		return nome;
	}
	public String getDescricao() {
		return descricao;
	}
	
	
	public CaracteristicaProduto toModel(@NotNull @Valid Produto produto) {
		// TODO Auto-generated method stub
		return new CaracteristicaProduto(nome, descricao, produto);
	}
	
}
