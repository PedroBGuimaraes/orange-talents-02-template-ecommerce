package com.example.mercadolivre.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.example.mercadolivre.model.Pergunta;
import com.example.mercadolivre.model.Produto;
import com.example.mercadolivre.model.Usuario;

public class NovaPerguntaRequest {
	
	@NotBlank
	private String titulo;

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Pergunta toModel(@NotNull @Valid Produto produto, @NotNull @Valid Usuario dono) {
		return new Pergunta(titulo, produto, dono);
	}
	
	
}
