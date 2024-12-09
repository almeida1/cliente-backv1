package com.fatec.sigvsmsuser.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fatec.sigvsmsuser.model.EmailDto;
import com.fatec.sigvsmsuser.model.Usuario;

@Component
public class UserProducer {
	
	final RabbitTemplate rabbitTemplate;
	public UserProducer (RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}
	@Value(value = "${broker.queue.email.name}")
	private String routingKey;
	
	public void publishMessageEmail(Usuario usuario) {
		var emailDto = new EmailDto();
		emailDto.setId(usuario.getId());
		emailDto.setEmailTo(usuario.getEmail());
		emailDto.setSubject("Cadastro de usuario realizado com sucesso");
		emailDto.setText(usuario.getNome() + ", seja bem vindo! \n Agradecemos seu cadastro");
		rabbitTemplate.convertAndSend("", routingKey,emailDto);
		
	}

}
