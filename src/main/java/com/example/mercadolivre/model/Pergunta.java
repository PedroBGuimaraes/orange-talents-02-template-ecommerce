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
public class Pergunta implements Comparable<Pergunta>{

	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Integer id;
	private @NotBlank String titulo;
	@ManyToOne
	private @NotNull @Valid Produto produto;
	@ManyToOne
	private @NotNull @Valid Usuario dono;
	
	@Deprecated
	public Pergunta() {
		
	}

	public Pergunta(@NotBlank String titulo, @NotNull @Valid Produto produto, @NotNull @Valid Usuario dono) {
		this.titulo = titulo;
		this.produto = produto;
		this.dono = dono;
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Pergunta [id=" + id + ", titulo=" + titulo + ", produto=" + produto + ", dono=" + dono + "]";
	}

	public Usuario getDono() {
		// TODO Auto-generated method stub
		return dono;
	}

	public Usuario getDonoProduto() {
		// TODO Auto-generated method stub
		return produto.getDono();
	}

	@Override
	public int compareTo(Pergunta o) {
		// TODO Auto-generated method stub
		return this.titulo.compareTo(o.titulo);
	}

	public String getTitulo() {
		// TODO Auto-generated method stub
		return this.titulo;
	}
	
}
