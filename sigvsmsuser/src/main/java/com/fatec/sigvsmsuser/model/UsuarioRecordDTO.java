package com.fatec.sigvsmsuser.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioRecordDTO(@NotBlank String nome, @NotBlank @Email String email) {

}
