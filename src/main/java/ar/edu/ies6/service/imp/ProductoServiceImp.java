package ar.edu.ies6.service.imp;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.ies6.model.Producto;
import ar.edu.ies6.service.IProductoService;
import ar.edu.ies6.util.AlmacenProductos;


@Service
public class ProductoServiceImp implements IProductoService {

	@Override
	public void guardarProducto(Producto producto) {
		// TODO Auto-generated method stub
		AlmacenProductos.producto.add(producto);
		System.out.println(AlmacenProductos.producto.get(0).getId());
	}

	@Override
	public void eliminarProducto(String codigo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificarProducto(Producto materiaModificado) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Producto consultarProducto(String codigo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Producto> listarTodosProducto() {
		// TODO Auto-generated method stub
		return AlmacenProductos.producto;
		
	}

	@Override
	public List<Producto> listarTodoActivos() {
		// TODO Auto-generated method stub
		return null;
	}
	 

}
