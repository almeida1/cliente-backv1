package com.fatec.sigvsmsuser.model;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ClienteRecordDTO(@CPF String cpf, @NotBlank String nome, String cep, String endereco, @NotBlank @Email String email, String data) {

}
