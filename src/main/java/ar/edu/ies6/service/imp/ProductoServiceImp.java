package ar.edu.ies6.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ar.edu.ies6.model.Cliente;
import ar.edu.ies6.model.Producto;
import ar.edu.ies6.service.IProductoService;
import ar.edu.ies6.util.AlmacenCliente;
import ar.edu.ies6.util.AlmacenProductos;


@Service

public class ProductoServiceImp implements IProductoService {
	
	@Qualifier("servicioProductoArrayList")

	@Override
	public void guardarProducto(Producto producto) {
		// TODO Auto-generated method stub
		AlmacenProductos.producto.add(producto);
		System.out.println("Producto guardado: "+ producto);
	}
	
		

	@Override
	public void eliminarProducto(String codigo) {
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

	@Override
	public void modificarProducto(Producto productoModificado) {
		// TODO Auto-generated method stub
		
	}
	 

}