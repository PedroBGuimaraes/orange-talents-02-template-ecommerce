package com.example.mercadolivre.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import com.example.mercadolivre.model.Produto;
import com.example.mercadolivre.model.Usuario;
import com.example.mercadolivre.model.Categoria;
import com.example.mercadolivre.validator.ExistsId;

public class NovoProdutoRequest {
	
	@NotBlank
	private String nome;
	@NotNull @Positive
	private BigDecimal preco;
	@NotNull @Positive
	private Integer quantidadeDisponivel;
	@NotBlank @Length(max = 1000)
	private String descricao;
	@NotNull @ExistsId(domainClass = Categoria.class, fieldName = "id")
	private Integer idCategoria;
	@Size(min = 3)
	@Valid
	private List<NovaCaracteristicaRequest> caracteristicas = new ArrayList<>();
	
	public NovoProdutoRequest(@NotBlank String nome, @NotNull @Positive BigDecimal preco,
			@NotNull @Positive Integer quantidadeDisponivel, @NotBlank @Length(max = 1000) String descricao,
			@NotNull Integer idCategoria, @Size(min = 3) List<NovaCaracteristicaRequest> caracteristicas) {
		super();
		this.nome = nome;
		this.preco = preco;
		this.quantidadeDisponivel = quantidadeDisponivel;
		this.descricao = descricao;
		this.idCategoria = idCategoria;
		this.caracteristicas.addAll(caracteristicas);
	}
	
	public List<NovaCaracteristicaRequest> getCaracteristicas() {
		return caracteristicas;
	}
	
	public Produto toModel(EntityManager manager, Usuario dono) {
		@NotNull Categoria categoria = manager.find(Categoria.class, idCategoria);
		return new Produto(nome, preco, quantidadeDisponivel, descricao, categoria, caracteristicas, dono);
	}

	@Override
	public String toString() {
		return "NovoProdutoRequest [nome=" + nome + ", preco=" + preco + ", quantidadeDisponivel="
				+ quantidadeDisponivel + ", descricao=" + descricao + ", idCategoria=" + idCategoria
				+ ", carecteristicas=" + caracteristicas + "]";
	}
	
}
