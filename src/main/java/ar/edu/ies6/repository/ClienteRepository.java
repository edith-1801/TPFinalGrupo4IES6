package ar.edu.ies6.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ar.edu.ies6.model.Cliente;

public interface ClienteRepository extends CrudRepository <Cliente, String>{
	
	// jpaRepository
	List<Cliente>findByEstado(Boolean estado);
	

}
