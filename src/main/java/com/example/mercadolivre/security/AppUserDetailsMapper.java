package com.example.mercadolivre.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.mercadolivre.model.Usuario;


@Configuration
public class AppUserDetailsMapper implements UserDetailsMapper{

	@Override
	public UserDetails map(Object shouldBeASystemUser) {						
		return new UsuarioLogado((Usuario)shouldBeASystemUser);
	}

}