package com.fatec.sigvsmsuser.service;

import org.springframework.stereotype.Service;

import com.fatec.sigvsmsuser.model.Usuario;
import com.fatec.sigvsmsuser.model.UsuarioRepository;
import com.fatec.sigvsmsuser.producer.UserProducer;

import jakarta.transaction.Transactional;

@Service
public class UsuarioService {

	final UsuarioRepository usuarioRepository;
	final UserProducer userProducer;
	
	public UsuarioService(UsuarioRepository usuarioRepository, UserProducer userProducer) {
		this.usuarioRepository = usuarioRepository;
		this.userProducer = userProducer;
	}
	@Transactional
	public Usuario save (Usuario usuario) {
		usuario = usuarioRepository.save(usuario);
		userProducer.publishMessageEmail(usuario);
		return usuario;
	}
	
}
