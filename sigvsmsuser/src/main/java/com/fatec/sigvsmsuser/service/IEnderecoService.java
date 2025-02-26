package com.fatec.sigvsmsuser.service;

import java.util.Optional;

public interface IEnderecoService {
	 public Optional<String> obtemLogradouroPorCep(String cep);
	 
}
