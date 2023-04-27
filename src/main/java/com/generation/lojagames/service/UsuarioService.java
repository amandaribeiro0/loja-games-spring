package com.generation.lojagames.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.generation.lojagames.model.Usuario;
import com.generation.lojagames.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired UsuarioRepository usuarioRepository;
	
	public Optional<Usuario> cadastrarUsuario (Usuario usuario){
		if (usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent())
			return Optional.empty();
		
		if (calcularIdade(usuario.getNascimento())< 18)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		
		usuario.setSenha(criptografarSenha(usuario.getSenha()));

		return Optional.of(usuarioRepository.save(usuario));
	}
	
	
	private int calcularIdade(LocalDate nascimento) {
		return Period.between(nascimento, LocalDate.now()).getYears();
	}
	
	
	private String criptografarSenha(String senha) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(senha);

	}
	
}
