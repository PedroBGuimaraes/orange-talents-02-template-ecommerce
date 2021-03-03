package com.example.mercadolivre.utils;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/*
 * Representa uma senha limpa
 */
public class SenhaLimpa {
	private String senha;

	public SenhaLimpa(@NotBlank @Length(min = 6) String senha) {
		super();
		Assert.isTrue(StringUtils.hasLength(senha),"senha nao pode ser em branco");
		Assert.isTrue(senha.length() >= 6,"senha no minino de 6 caracteres");
		this.senha = senha;
	}
	
	public String hash() {
		String passwordEncoded = new BCryptPasswordEncoder().encode(senha);
		return passwordEncoded;
	}
	
	
}
