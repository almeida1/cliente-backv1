package com.fatec.sigvsmsuser.producer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fatec.sigvsmsuser.model.EmailDto;
import com.fatec.sigvsmsuser.model.Usuario;

@Component
public class UserProducer {
	Logger logger = LogManager.getLogger(this.getClass());
	final RabbitTemplate rabbitTemplate;
	public UserProducer (RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}
	@Value("${broker.queue.email.name}")
	private String routingKey;
	
	public void publishMessageEmail(Usuario usuario) {
		logger.info(">>>>> userProducer publish -> public msg de novo usuario cadastrado");
		var emailDto = new EmailDto();
		emailDto.setUsuarioId(usuario.getUsuarioId());
		emailDto.setEmailTo(usuario.getEmail());
		emailDto.setSubject("Confirmação de cadastro");
		emailDto.setText(usuario.getNome() + ", \n\n Agradecemos seu cadastro. \n\n SIGVS Administrador");
		rabbitTemplate.convertAndSend("", routingKey,emailDto);
		
	}

}
