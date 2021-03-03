package com.example.mercadolivre.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;

@Entity
public class ImagemProduto {

	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Integer id;
	@ManyToOne
	private @NotNull @Valid Produto produto;
	private @URL @NotBlank String link;
	
	@Deprecated
	public ImagemProduto() {
		
	}

	public ImagemProduto(@NotNull @Valid Produto produto, @URL String link) {
		this.produto = produto;
		this.link = link;
		// TODO Auto-generated constructor stub
	}

	public String getLink() {
		// TODO Auto-generated method stub
		return link;
	}
	
	
}
