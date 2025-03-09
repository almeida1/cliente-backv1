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
class Req12AtualizarClienteTests {
	private Cliente cliente;
	@Autowired
	private ClienteRepository clienteRepository;
	@BeforeEach
	public void setUp() {
	 	cliente = new Cliente();
		cliente.setCpf("80983098000");
		cliente.setNome("Jose da Silva");
		cliente.setCep("01310-100");
		cliente.setEndereco("Av. Paulista");
		cliente.setEmail("jose@gmail.com");
		cliente.setDataCadastro();
		clienteRepository.save(cliente);
	}
	@Test
	void ct01_quando_cliente_modificado_retorna_informacoes_atualizadas() {
		cliente.setNome("Carlos Xavier");
		clienteRepository.save(cliente);
		Optional<Cliente> clienteAtualizado = clienteRepository.findByCpf("80983098000");
		assertEquals ("Carlos Xavier",clienteAtualizado.get().getNome());
	}

}
