package com.example.mercadolivre.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginInputDto {

	private String login;
	private String senha;

	public String getLogin() {
		return login;
	}

	public void setLogin(String Login) {
		this.login = Login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public UsernamePasswordAuthenticationToken build() {
		return new UsernamePasswordAuthenticationToken(this.login,
				this.senha);
	}
}