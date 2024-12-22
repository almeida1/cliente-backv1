package com.fatec.sigvsmsuser.service;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.sigvsmsuser.model.Cliente;
import com.fatec.sigvsmsuser.model.ClienteRepository;
import com.fatec.sigvsmsuser.producer.ClienteProducer;

import jakarta.transaction.Transactional;

@Service
public class ClienteService {
	Logger logger = LogManager.getLogger(this.getClass());
	final ClienteRepository clienteRepository;
	final ClienteProducer clienteProducer;
	@Autowired
	private EnderecoService enderecoService;

	public ClienteService(ClienteRepository clienteRepository, ClienteProducer clienteProducer) {
		this.clienteRepository = clienteRepository;
		this.clienteProducer = clienteProducer;
	}

	@Transactional
	public Optional<Cliente> save(Cliente cliente) {
		try {
			// Verifica se o cliente já existe com base no CPF ou outro identificador único
			Optional<Cliente> clienteExistente = clienteRepository.findByCpf(cliente.getCpf());
			if (clienteExistente.isPresent()) {
				logger.info(">>>>>> clienteservico - cliente já cadastrado com CPF: " + cliente.getCpf());
				return Optional.empty(); // Retorna vazio indicando que o cliente já existe
			}

			Optional<String> endereco = enderecoService.obtemLogradouroPorCep(cliente.getCep());
			if (endereco.isEmpty()) {
				logger.warn(">>>>>> Endereço não encontrado para o CEP: " + cliente.getCep());
				return Optional.empty(); //retorna vazio indicando que o cep nao foi localizado
			} else {
				cliente.setDataCadastro();
				cliente.setEndereco(endereco.get());
				Cliente novoCliente = clienteRepository.save(cliente);
		        logger.info(">>>>>> clienteservico - cliente salvo com sucesso no repositório");
				clienteProducer.publishMessageEmail(cliente);
				return Optional.of(novoCliente);
			}
		} catch (Exception e) {
			logger.info(">>>>>> clienteservico - erro metodo cadastrar comando save ");

			return Optional.empty();
		}

	}

}
