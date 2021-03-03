package com.example.mercadolivre.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mercadolivre.dto.NovoUsuarioRequest;
import com.example.mercadolivre.model.Usuario;
import com.example.mercadolivre.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@PostMapping
	public String postNovoUsuario(@RequestBody @Valid NovoUsuarioRequest novoUsuarioRequest) {
		Usuario novoUsuario = novoUsuarioRequest.toModel();
		System.out.println(novoUsuario.toString());
		usuarioRepository.save(novoUsuario);
		return novoUsuario.toString();
	}
	
	@GetMapping
	public ResponseEntity<?> getListaUsuarios(){
		List<Usuario> listaUsuarios = usuarioRepository.findAll();
		return ResponseEntity.ok(listaUsuarios);
	}
}
