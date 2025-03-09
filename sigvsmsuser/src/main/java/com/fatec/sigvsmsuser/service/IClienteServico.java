package com.fatec.sigvsmsuser.service;

import java.util.List;
import com.fatec.sigvsmsuser.model.Cliente;

public interface IClienteServico {
	public List<Cliente> consultaTodos();
	public ClienteResponse cadastrar(Cliente cliente);
	public ClienteResponse consultarPorCpf(String cpf);
	public ClienteResponse atualizar(String cpf, Cliente cliente);
	public ClienteResponse excluir(String cpf);
	public Double estoqueImobilizado();
}