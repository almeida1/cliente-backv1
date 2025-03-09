package com.fatec.sigvsmsuser;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.fatec.sigvsmsuser.model.Cliente;
import com.fatec.sigvsmsuser.service.ClienteRepository;
@DataJpaTest
class Req10ConsultarClientePorCPFTests {
	private Cliente cliente;
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Test
	void ct01_quando_cliente_cadastrado_retorna_detalhes() {
		//Dado - que o cpf esta cadastrado
		cliente = new Cliente();
		cliente.setCpf("80983098000");
		cliente.setNome("Jose da Silva");
		cliente.setCep("01310-100");
		cliente.setEndereco("Av. Paulista");
		cliente.setEmail("jose@gmail.com");
		cliente.setDataCadastro();
		clienteRepository.save(cliente);
		//Quando - consulto o cpf
		Optional<Cliente> c = clienteRepository.findByCpf("80983098000");
		//Entao - retorna os detalhes do cliente
		assertTrue (c.isPresent());
	}
	
	@Test
	void ct02_quando_cliente_nao_cadastrado_retorna_vazio() {
		//Dado - que o cpf nao esta cadastrado
		//Quando - consulto o cpf
		Optional<Cliente> c = clienteRepository.findByCpf("80983098001");
		//Entao - retorna vazio
		assertTrue (c.isEmpty());
	}
}
