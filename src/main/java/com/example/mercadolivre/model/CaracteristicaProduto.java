package com.example.mercadolivre.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class CaracteristicaProduto {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private @NotBlank String nome;
	private @NotBlank String descricao;
	@ManyToOne
	private @NotNull @Valid Produto produto;
	
	@Deprecated
	public CaracteristicaProduto() {
		
	}

	public CaracteristicaProduto(@NotBlank String nome, @NotBlank String descricao, @NotNull @Valid Produto produto) {
		this.nome = nome;
		this.descricao = descricao;
		this.produto = produto;
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "CaracteristicaProduto [id=" + id + ", nome=" + nome + ", descricao=" + descricao + "]";
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public Produto getProduto() {
		return produto;
	}
	
	

}
