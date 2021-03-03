package com.example.mercadolivre.dto;

import com.example.mercadolivre.model.CaracteristicaProduto;

public class DetalheProdutoCaracteristica {
	private String nome;
	private String descricao;

	public DetalheProdutoCaracteristica(CaracteristicaProduto caracteristica) {
		this.nome = caracteristica.getNome();
		this.descricao = caracteristica.getDescricao();
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getDescricao() {
		return descricao;
	}

}
