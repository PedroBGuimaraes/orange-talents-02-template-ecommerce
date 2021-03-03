package com.example.mercadolivre.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.example.mercadolivre.utils.StatusTransacao;

@Entity
public class Transacao {
	
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Integer id;
	private @NotNull StatusTransacao normaliza;
	private @NotBlank String idTransacao;
	private LocalDateTime instante;
	@ManyToOne
	private @NotNull @Valid Compra compra;
	
	@Deprecated
	public Transacao() {
		
	}

	public Transacao(@NotNull StatusTransacao normaliza, @NotBlank String idTransacao, @NotNull @Valid Compra compra) {
		this.normaliza = normaliza;
		this.idTransacao = idTransacao;
		this.compra = compra;
		this.instante = LocalDateTime.now();
		// TODO Auto-generated constructor stub
	}

}
