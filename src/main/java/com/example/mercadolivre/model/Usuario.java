package com.example.mercadolivre.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import org.springframework.util.StringUtils;

import com.example.mercadolivre.utils.SenhaLimpa;



@Entity
public class Usuario {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank @Email @Column(unique = true, nullable = false)
	private String login; 
	@NotBlank @Length(min = 6) @Column(nullable = false)
	private String senha;
	@Column(nullable = false)
	private LocalDateTime instateCriado = LocalDateTime.now();
	
	@Deprecated
	public Usuario() {
		
	}
	
	public Usuario(@NotBlank @Email String login, @NotNull @Valid SenhaLimpa senhaLimpa) {
		super();
		Assert.isTrue(StringUtils.hasLength(login),"email nao pode ser em branco");
		Assert.notNull(senhaLimpa,"Objeto do tipo senha não pode ser nulo");
		
		this.login = login;
		this.senha = senhaLimpa.hash();
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", login=" + login + ", senha=" + senha + ", instateCriado=" + instateCriado + "]";
	}

	public Long getId() {
		return id;
	}

	public String getLogin() {
		return login;
	}

	public String getSenha() {
		return senha;
	}

	public LocalDateTime getInstateCriado() {
		return instateCriado;
	}

	public String getEmail() {
		// TODO Auto-generated method stub
		return login;
	}
}
