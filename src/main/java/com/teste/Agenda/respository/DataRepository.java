package com.teste.Agenda.respository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.teste.Agenda.entities.Clientes;

public interface DataRepository extends JpaRepository<Clientes, Long>{
	@Query(value = "SELECT * FROM novos_clientes WHERE create_date <= DATE_SUB(CURDATE(), INTERVAL 1 DAY);", nativeQuery = true)
    List<Clientes>selectDate(@Param("createDate") LocalDate createDate);


}
