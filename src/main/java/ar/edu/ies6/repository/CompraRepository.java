package ar.edu.ies6.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import ar.edu.ies6.model.Compra;

public interface CompraRepository extends CrudRepository<Compra, Long> {
    List<Compra> findByEstadoCompra(String estadoCompra);



}
