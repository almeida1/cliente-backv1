package com.fatec.sigvsmsuser.service;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatec.sigvsmsuser.model.Cliente;
@Repository
public interface ClienteRepository extends JpaRepository <Cliente, Long> {
	Optional<Cliente> findByCpf(String cpf);
	void deleteByCpf(String cpf);

}
