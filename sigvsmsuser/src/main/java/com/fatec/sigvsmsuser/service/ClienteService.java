package com.fatec.sigvsmsuser.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.sigvsmsuser.model.Cliente;
import com.fatec.sigvsmsuser.producer.ClienteProducer;

import jakarta.transaction.Transactional;

@Service
public class ClienteService implements IClienteServico {
	Logger logger = LogManager.getLogger(this.getClass());
	final ClienteRepository clienteRepository;
	final ClienteProducer clienteProducer;
	@Autowired
	private IEnderecoService enderecoService;

	public ClienteService(ClienteRepository clienteRepository, ClienteProducer clienteProducer) {
		this.clienteRepository = clienteRepository;
		this.clienteProducer = clienteProducer;
	}

	@Transactional
	public Optional<Cliente> cadastrar(Cliente cliente) {
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
				 logger.info(">>>>>> clienteservico - mensagem enviada");
				return Optional.of(novoCliente);
			}
		} catch (Exception e) {
			logger.info(">>>>>> clienteservico - erro metodo cadastrar comando save ");
			return Optional.empty();
		}
	}

	@Override
	public List<Cliente> consultaTodos() {
		return clienteRepository.findAll();
	}

	
	@Override
	public Optional<Cliente> consultarPorCpf(String cpf) {
		return clienteRepository.findByCpf(cpf);
	}

	@Override
	public Optional<Cliente> atualizar(Long id, Cliente cliente) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<Cliente> excluir(String cpf) {
		Optional<Cliente> c = clienteRepository.findByCpf(cpf);
		if (c.isEmpty()) {
			return Optional.empty();
		} else {
			clienteRepository.deleteByCpf(cpf);
			return c;
		}
	}

	@Override
	public Double estoqueImobilizado() {
		// TODO Auto-generated method stub
		return null;
	}

}
