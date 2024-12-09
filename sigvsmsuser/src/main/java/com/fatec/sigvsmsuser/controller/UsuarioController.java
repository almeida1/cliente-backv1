package com.fatec.sigvsmsuser.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.sigvsmsuser.model.Usuario;
import com.fatec.sigvsmsuser.model.UsuarioRecordDTO;
import com.fatec.sigvsmsuser.service.UsuarioService;

import jakarta.validation.Valid;

@CrossOrigin("*") // desabilita o cors do spring security
@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {
	
	final UsuarioService usuarioService;
	
	public UsuarioController (UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	@PostMapping
	public ResponseEntity<Usuario> saveUsuario(@RequestBody @Valid UsuarioRecordDTO usuario){
		Usuario novoUsuario = new Usuario();
		novoUsuario.setNome(usuario.nome());
		novoUsuario.setEmail(usuario.email());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(novoUsuario));
	}

}
