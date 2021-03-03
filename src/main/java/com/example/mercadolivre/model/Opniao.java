package com.example.mercadolivre.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Opniao {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private @Min(1) @Max(5) Integer nota;
	private @NotBlank String titulo;
	private @NotBlank @Size(max = 500) String descricao;
	@ManyToOne
	private @NotNull @Valid Produto produto;
	@ManyToOne
	private @NotNull @Valid Usuario dono;

	public Opniao(@Min(1) @Max(5) Integer nota, @NotBlank String titulo, @NotBlank @Size(max = 500) String descricao,
			@NotNull @Valid Produto produto, @NotNull @Valid Usuario dono) {
				this.nota = nota;
				this.titulo = titulo;
				this.descricao = descricao;
				this.produto = produto;
				this.dono = dono;
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Opniao [id=" + id + ", nota=" + nota + ", titulo=" + titulo + ", descricao=" + descricao + ", produto="
				+ produto + ", dono=" + dono + "]";
	}
	
	

}
