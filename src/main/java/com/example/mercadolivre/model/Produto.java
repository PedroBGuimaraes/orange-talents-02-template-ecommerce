package com.example.mercadolivre.model;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import com.example.mercadolivre.dto.DetalheProdutoCaracteristica;
import com.example.mercadolivre.dto.NovaCaracteristicaRequest;

@Entity
public class Produto {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private @NotBlank @Column(nullable = false) String nome;
	private @NotNull @Positive @Column(nullable = false) BigDecimal preco;
	private @NotNull @Positive @Column(nullable = false) Integer quantidadeDisponivel;
	private @NotBlank @Length(max = 1000) @Column(nullable = false) String descricao;
	@ManyToOne
	private @NotNull Categoria categoria;
	@OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
	private Set<CaracteristicaProduto> caracteristicas = new HashSet<>();
	@OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
	private Set<ImagemProduto> imagens = new HashSet<>();
	@ManyToOne
	@NotNull
	private Usuario dono;
	@OneToMany(mappedBy = "produto")
	@OrderBy("titulo asc")
	private SortedSet<Pergunta> perguntas = new TreeSet();
	
	
	@Deprecated
	public Produto() {
	}

	public Produto(@NotBlank String nome, @NotNull @Positive BigDecimal preco,
			@NotNull @Positive Integer quantidadeDisponivel, @NotBlank @Length(max = 1000) String descricao,
			@NotNull Categoria categoria, @Size(min = 3) @Valid Collection<NovaCaracteristicaRequest> caracteristicas,
			@NotNull Usuario dono) {
				this.nome = nome;
				this.preco = preco;
				this.quantidadeDisponivel = quantidadeDisponivel;
				this.descricao = descricao;
				this.categoria = categoria;
				this.caracteristicas.addAll(caracteristicas.stream().map(caracteristica -> caracteristica.toModel(this)).collect(Collectors.toSet()));
				this.dono = dono;
				
				Assert.isTrue(this.caracteristicas.size() >= 3, "Todo produto tem que ter no minimo 3 ou mais caracteristicas");
		// TODO Auto-generated constructor stub
	}

	public Usuario getDono() {
		return dono;
	}

	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", preco=" + preco + ", quantidadeDisponivel="
				+ quantidadeDisponivel + ", descricao=" + descricao + ", categoria=" + categoria + ", caracteristicas="
				+ caracteristicas + ", imagens=" + imagens + ", dono=" + dono + "]";
	}

	public void associaImagens(Set<String> links) {
		// TODO Auto-generated method stub
		Set<ImagemProduto> imagens = links.stream().map(link -> new ImagemProduto(this, link)).collect(Collectors.toSet());
		
		this.imagens.addAll(imagens);
	}

	public boolean pertenceAoUsuario(Usuario possivelDono) {
		return this.dono.equals(possivelDono);
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public Integer getQuantidadeDisponivel() {
		return quantidadeDisponivel;
	}

	public String getDescricao() {
		return descricao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public Set<CaracteristicaProduto> getCaracteristicas() {
		return caracteristicas;
	}

	public Set<ImagemProduto> getImagens() {
		return imagens;
	}

	public Set<DetalheProdutoCaracteristica> mapeiaCaracteristicas(Function<CaracteristicaProduto, DetalheProdutoCaracteristica> funcaoMapeadora) {
		return this.caracteristicas.stream().map(funcaoMapeadora).collect(Collectors.toSet());
	}

	public <T> Set<T> mapeiaImagens(Function<ImagemProduto, T> funcaoMapeadora) {
		// TODO Auto-generated method stub
		return this.imagens.stream().map(funcaoMapeadora).collect(Collectors.toSet());
	}

	public <T extends Comparable<T>> SortedSet<T> mapeiaPerguntas(Function<Pergunta, T> funcaoMapeadora) {
		// TODO Auto-generated method stub
		return this.perguntas.stream().map(funcaoMapeadora).collect(Collectors.toCollection(TreeSet :: new));
	}

	public boolean abateEstoque(@Positive int quantidade) {
		// TODO Auto-generated method stub
		Assert.isTrue(quantidade>0,"Quantidade deve ser mais que 0");
		if(quantidade <= this.quantidadeDisponivel) {
			this.quantidadeDisponivel-=quantidade;
			return true;
		}
		return false;
	}

	

}
