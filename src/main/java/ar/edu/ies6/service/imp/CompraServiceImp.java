package ar.edu.ies6.service.imp;

import java.util.List; // Importa la clase List para manejar listas.
import org.springframework.beans.factory.annotation.Qualifier; // Importa la anotación Qualifier para especificar implementaciones de beans.
import org.springframework.stereotype.Service; // Importa la anotación Service para marcar esta clase como un servicio gestionado por Spring.
import ar.edu.ies6.model.Compra; // Importa la entidad Compra.
import ar.edu.ies6.service.ICompraService; // Importa la interfaz ICompraService.
import ar.edu.ies6.util.AlmacenCompras; // Importa la clase AlmacenCompras que actúa como un almacén temporal para las compras.

@Service // Marca la clase como un servicio gestionado por Spring, permitiendo que Spring la detecte y gestione automáticamente.
@Qualifier("servicioCompraArrayList") // Especifica que esta implementación debe ser utilizada cuando se inyecte el bean calificado como "servicioCompraArrayList".
public class CompraServiceImp implements ICompraService {

    @Override
    public void guardarCompra(Compra compra) {
        // Agrega la compra al almacén de compras.
        AlmacenCompras.compras.add(compra);
    }

    @Override
    public void eliminarCompra(Long id) {
        // Método para eliminar una compra por ID.
        // Actualmente no implementado.
    }

    @Override
    public Compra consultarCompra(Long id) {
        // Método para consultar una compra por ID.
        // Actualmente no implementado.
        return null;
    }

    @Override
    public List<Compra> listarTodasCompras() {
        // Devuelve una lista con todas las compras almacenadas.
        return AlmacenCompras.compras;
    }

    @Override
    public List<Compra> listarComprasPorEstadoCompra(String estadoCompra) {
        // Método para listar compras por estado.
        // Actualmente no implementado.
        return null;
    }

    @Override
    public void modificarCompra(Compra compra) {
        // Método para modificar una compra.
        // Actualmente no implementado.
    }
}
