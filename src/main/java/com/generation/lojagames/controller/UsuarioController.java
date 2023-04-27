package com.generation.lojagames.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.lojagames.model.Usuario;
import com.generation.lojagames.repository.UsuarioRepository;
import com.generation.lojagames.service.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {
	
	@Autowired private UsuarioService usuarioService;

	@Autowired private UsuarioRepository usuarioRepository;
	
	
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> postUsuario(@RequestBody @Valid Usuario usuario) {
		return usuarioService.cadastrarUsuario(usuario)
			.map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(resposta))
			.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());

	}

}
