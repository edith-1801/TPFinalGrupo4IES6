package ar.edu.ies6.service.imp;

import java.util.List; // Importa la clase List para manejar listas.
import java.util.Optional; // Importa la clase Optional para manejar valores que pueden estar presentes o ausentes.

import org.springframework.beans.factory.annotation.Autowired; // Importa la anotación Autowired para la inyección de dependencias.
import org.springframework.beans.factory.annotation.Qualifier; // Importa la anotación Qualifier para especificar implementaciones de beans.
import org.springframework.stereotype.Service; // Importa la anotación Service para marcar esta clase como un servicio gestionado por Spring.

import ar.edu.ies6.model.Compra; // Importa la entidad Compra.
import ar.edu.ies6.repository.CompraRepository; // Importa el repositorio CompraRepository.
import ar.edu.ies6.service.ICompraService; // Importa la interfaz ICompraService.

@Service // Marca la clase como un servicio gestionado por Spring, permitiendo que Spring la detecte y gestione automáticamente.
@Qualifier("servicioCompraBD") // Especifica que esta implementación debe ser utilizada cuando se inyecte el bean calificado como "servicioCompraBD".
public class CompraServiceImpBD implements ICompraService {

    @Autowired // Inyecta automáticamente una instancia de CompraRepository.
    CompraRepository compraRepository;

    @Override
    public void guardarCompra(Compra compra) {
        // Establece el estado de la compra como "pendiente" y la guarda en el repositorio.
        compra.setEstadoCompra("pendiente"); 
        compraRepository.save(compra);
    }

    @Override
    public void eliminarCompra(Long id) {
        // Busca la compra por ID y establece su estado como "cancelado" si se encuentra.
        Optional<Compra> compraEncontrada = compraRepository.findById(id);
        if (compraEncontrada.isPresent()) {
            compraEncontrada.get().setEstadoCompra("cancelado"); 
            compraRepository.save(compraEncontrada.get());
        }
    }

    @Override
    public Compra consultarCompra(Long id) {
        // Consulta y devuelve la compra por ID, o null si no se encuentra.
        return compraRepository.findById(id).orElse(null);
    }

    @Override
    public List<Compra> listarTodasCompras() {
        // Devuelve una lista con todas las compras almacenadas en el repositorio.
        return (List<Compra>) compraRepository.findAll();
    }

    @Override
    public List<Compra> listarComprasPorEstadoCompra(String estadoCompra) {
        // Devuelve una lista de compras filtradas por su estado.
        return compraRepository.findByEstadoCompra(estadoCompra);
    }

    @Override
    public void modificarCompra(Compra compra) {
        // Método para modificar una compra. Pendiente de implementación.
        // TODO Auto-generated method stub
    }
}
