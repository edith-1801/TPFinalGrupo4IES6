package ar.edu.ies6.repository;

import java.util.List; // Importa la clase List para manejar listas.
import org.springframework.data.repository.CrudRepository; // Importa CrudRepository para definir operaciones CRUD básicas.
import ar.edu.ies6.model.Compra; // Importa la entidad Compra.

public interface CompraRepository extends CrudRepository<Compra, Long> {
    // Define un repositorio para manejar operaciones CRUD para la entidad Compra.

    // Método personalizado para encontrar compras por su estado.
    List<Compra> findByEstadoCompra(String estadoCompra);
}
