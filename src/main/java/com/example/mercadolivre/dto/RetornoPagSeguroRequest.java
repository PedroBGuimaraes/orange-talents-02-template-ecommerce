package com.example.mercadolivre.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.example.mercadolivre.model.Compra;
import com.example.mercadolivre.model.Transacao;
import com.example.mercadolivre.utils.StatusRetornoPagamento;

public class RetornoPagSeguroRequest {
	@NotBlank
	private String idTransacao;
	@NotNull
	private StatusRetornoPagamento status;
	public RetornoPagSeguroRequest(@NotBlank String idTransacao, @NotNull StatusRetornoPagamento status) {
		super();
		this.idTransacao = idTransacao;
		this.status = status;
	}
	@Override
	public String toString() {
		return "RetornoPagSeguroRequest [idTransacao=" + idTransacao + ", status=" + status + "]";
	}
	public Transacao toTransacao(Compra compra) {
		// TODO Auto-generated method stub
		return new Transacao(status.normaliza(), idTransacao, compra);
	}
	
	
}
