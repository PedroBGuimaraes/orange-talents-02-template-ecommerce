package com.example.mercadolivre.dto;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.mercadolivre.model.Opniao;
import com.example.mercadolivre.model.Produto;
import com.example.mercadolivre.model.Usuario;

public class NovaOpniaoRequest {
	
	@Min(1)
	@Max(5)
	private Integer nota;
	@NotBlank
	private String titulo;
	@NotBlank @Size(max = 500)
	private String descricao;
	
	public NovaOpniaoRequest(@Min(1) @Max(5) Integer nota, @NotBlank String titulo,
			@NotBlank @Size(max = 500) String descricao) {
		super();
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
	}

	public Opniao toModel(@NotNull @Valid Produto produto, @NotNull @Valid Usuario dono) {
		return new Opniao(nota, titulo, descricao, produto, dono);
	}
	
	
}
