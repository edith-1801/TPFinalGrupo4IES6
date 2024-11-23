package ar.edu.ies6.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ar.edu.ies6.model.Producto;
import ar.edu.ies6.repository.ProductoRepository;
import ar.edu.ies6.service.IProductoService;
@Service
@Qualifier("servicioProductoBD")
public class ProductoServiceImpBD implements IProductoService {
@Autowired
ProductoRepository productoRepository;
	@Override
	public void guardarProducto(Producto producto) {
		// TODO Auto-generated method stub
		producto.setEstado(true);
		productoRepository.save(producto);
		
	}

	@Override
	public void eliminarProducto(String id) {
		// TODO Auto-generated method stub
		Optional<Producto> productoEncontrado = productoRepository.findById(id);
		productoEncontrado.get().setEstado(false);
		productoRepository.save(productoEncontrado.get());
	}

	@Override
	public void modificarProducto(Producto productoModificado) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Producto consultarProducto(String id) {
		// TODO Auto-generated method stub
		return productoRepository.findById(id).get();
	}

	@Override
	public List<Producto> listarTodosProducto() {
		// TODO Auto-generated method stub
		return (List<Producto>) productoRepository.findAll();
	}
	

	@Override
	public List<Producto> listarTodoActivos() {
		// TODO Auto-generated method stub
		return (List<Producto>) productoRepository.findProductoByEstado(true);
	}

}
