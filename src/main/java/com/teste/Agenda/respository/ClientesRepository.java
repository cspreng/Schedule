package com.teste.Agenda.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teste.Agenda.entities.Clientes;


public interface ClientesRepository extends JpaRepository<Clientes, Long> {
	

}