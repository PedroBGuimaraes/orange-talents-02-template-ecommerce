package com.example.mercadolivre.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.example.mercadolivre.model.Usuario;
import com.example.mercadolivre.utils.SenhaLimpa;
import com.example.mercadolivre.validator.UniqueValue;

public class NovoUsuarioRequest {
	@NotBlank @Email @UniqueValue(domainClass = Usuario.class, fieldName = "login")
	private String login;
	@NotBlank @Size(min = 6)
	private String senha;
	
	public NovoUsuarioRequest(@NotBlank @Email String login, @NotBlank @Size(min = 6) String senha) {
		super();
		this.login = login;
		this.senha = senha;
	}

	public Usuario toModel() {
		return new Usuario(login, new SenhaLimpa(senha));
	}
	
}
