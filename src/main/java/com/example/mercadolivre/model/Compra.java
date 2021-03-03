package com.example.mercadolivre.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.example.mercadolivre.dto.RetornoPagSeguroRequest;
import com.example.mercadolivre.utils.GatewayPagamento;

@Entity
public class Compra {

	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Integer id;
	@ManyToOne
	@NotNull
	@Valid
	private Produto produtoEscolhido;
	private @Positive int quantidade;
	@ManyToOne
	@NotNull
	@Valid
	private Usuario comprador;
	@NotNull
	private GatewayPagamento gatewayPagamento;
	@OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE)
	private Set<Transacao> transacoes = new HashSet<>();
	
	@Deprecated
	public Compra() {
		
	}

	public Compra(@NotNull @Valid Produto produtoSerComprado, @Positive int quantidade, @NotNull @Valid Usuario comprador, GatewayPagamento gatewayPagamento) {
		this.produtoEscolhido = produtoSerComprado;
		this.quantidade = quantidade;
		this.comprador = comprador;
		// TODO Auto-generated constructor stub
		this.gatewayPagamento = gatewayPagamento;
	}

	@Override
	public String toString() {
		return "Compra [id=" + id + ", produtoEscolhido=" + produtoEscolhido + ", quantidade=" + quantidade
				+ ", comprador=" + comprador + ", gatewayPagamento=" + gatewayPagamento + "]";
	}

	public Integer getId() {
		return id;
	}

	public void adicionaTransacao(@Valid RetornoPagSeguroRequest request) {
		Transacao novaTransacao = request.toTransacao(this);
		this.transacoes.add(novaTransacao);
	}

}
