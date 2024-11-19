package ar.edu.ies6.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.ies6.model.Producto;
@Service
public interface IProductoService {
	//crud
			//metodos
			public void guardarProducto(Producto producto);
			public void eliminarProducto(String id);
			public void modificarProducto(Producto materiaModificado);
			public Producto consultarProducto(String id);
			public List<Producto> listarTodosProducto();
			public List<Producto> listarTodoActivos ();
			



}
