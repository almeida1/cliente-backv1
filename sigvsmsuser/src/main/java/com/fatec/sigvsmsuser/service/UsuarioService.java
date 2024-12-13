package com.fatec.sigvsmsuser.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.fatec.sigvsmsuser.model.Usuario;
import com.fatec.sigvsmsuser.model.UsuarioRepository;
import com.fatec.sigvsmsuser.producer.UserProducer;

import jakarta.transaction.Transactional;

@Service
public class UsuarioService {
	Logger logger = LogManager.getLogger(this.getClass());
	final UsuarioRepository usuarioRepository;
	final UserProducer userProducer;
	
	public UsuarioService(UsuarioRepository usuarioRepository, UserProducer userProducer) {
		this.usuarioRepository = usuarioRepository;
		this.userProducer = userProducer;
	}
	@Transactional
	public Usuario save (Usuario usuario) {
		usuario = usuarioRepository.save(usuario);
		logger.info(">>>>> usuarioservice save ->" + usuario.getUsuarioId());
		userProducer.publishMessageEmail(usuario);
		return usuario;
	}
	
}
