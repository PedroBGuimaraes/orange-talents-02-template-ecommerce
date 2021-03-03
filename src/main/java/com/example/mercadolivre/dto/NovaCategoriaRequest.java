package com.example.mercadolivre.dto;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;

import org.springframework.util.Assert;

import com.example.mercadolivre.model.Categoria;
import com.example.mercadolivre.validator.UniqueValue;

public class NovaCategoriaRequest {

	@NotBlank @UniqueValue(domainClass = Categoria.class, fieldName = "nome")
	private String nome;
	private Integer idCategoria;

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	public Categoria toModel(EntityManager manager) {
		Categoria categoriaMae = null;
		if(idCategoria != null) {
			categoriaMae = manager.find(Categoria.class, idCategoria);
			Assert.notNull(categoriaMae, "Id Categoria mae precisa ser valido");
		}
		return new Categoria(nome, categoriaMae);
	}
	
	
}
