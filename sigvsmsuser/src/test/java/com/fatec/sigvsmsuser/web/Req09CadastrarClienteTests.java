package com.fatec.sigvsmsuser.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.fatec.sigvsmsuser.model.Cliente;
import com.google.gson.Gson;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class Req09CadastrarClienteTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	// @DisplayName("CT01 Cadastrar cliente com sucesso")
	void ct01_cadastrar_cliente_com_sucesso() {

		String URLBase = "/api/v1/clientes";
		//********************************************************************
		// Given - dado que as informacoes de cliente sao validas
		//********************************************************************
		Cliente clienteNovo = new Cliente();
		clienteNovo.setCpf("80983098000");
		clienteNovo.setNome("Jose da Silva");
		clienteNovo.setCep("01310-100");
		clienteNovo.setEmail("jose@gmail.com");
		clienteNovo.setDataCadastro();
		Gson gson = new Gson();
		String clienteJson = gson.toJson(clienteNovo);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>(clienteJson, headers);
		//********************************************************************
		// When - quando confirmo o cadastro do cliente
		//********************************************************************
		ResponseEntity<Cliente> response = restTemplate.exchange(URLBase, HttpMethod.POST, request, Cliente.class);
		//********************************************************************
		// Then - entao retorna os detalhes do cliente cadastrado
		//********************************************************************
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		Cliente clienteCadastrado = response.getBody();
		assertTrue(clienteCadastrado.equals(clienteNovo));
		assertEquals("Jose da Silva", clienteCadastrado.getNome());
		assertEquals("Avenida Paulista", clienteCadastrado.getEndereco());
	}
}
