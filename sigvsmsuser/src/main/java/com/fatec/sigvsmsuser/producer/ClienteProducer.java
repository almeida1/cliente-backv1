package com.fatec.sigvsmsuser.producer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fatec.sigvsmsuser.model.Cliente;
import com.fatec.sigvsmsuser.model.EmailDto;

@Component
public class ClienteProducer {
	Logger logger = LogManager.getLogger(this.getClass());
	final RabbitTemplate rabbitTemplate;
	public ClienteProducer (RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}
	@Value("${broker.queue.email.name}")
	private String routingKey;
	
	public void publishMessageEmail(Cliente cliente) {
		logger.info(">>>>> clienteProducer publishMessageEmail iniciado...");
		var emailDto = new EmailDto();
		emailDto.setUsuarioId(cliente.getId());
		emailDto.setEmailTo(cliente.getEmail());
		emailDto.setSubject("Confirmação de cadastro");
		emailDto.setText(cliente.getNome() + ", \n\n Agradecemos seu cadastro. \n\n SIGVS Administrador");
		rabbitTemplate.convertAndSend("", routingKey,emailDto);
		logger.info(">>>>> clienteProducer publish -> publish msg de novo usuario cadastrado");
	}

}
