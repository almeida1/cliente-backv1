package com.fatec.sigvsmsuser.model;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository <Cliente, Long> {
	Optional<Cliente> findByCpf(String cpf);

}
