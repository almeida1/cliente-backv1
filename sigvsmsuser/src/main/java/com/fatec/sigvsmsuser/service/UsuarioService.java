package com.fatec.sigvsmsuser.service;

import org.springframework.stereotype.Service;

import com.fatec.sigvsmsuser.model.Usuario;
import com.fatec.sigvsmsuser.model.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class UsuarioService {

	final UsuarioRepository usuarioRepository;
	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	@Transactional
	public Usuario save (Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
}
