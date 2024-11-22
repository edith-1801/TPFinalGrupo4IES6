package ar.edu.ies6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.ies6.model.Producto;
import ar.edu.ies6.service.IProductoService;


@Controller
public class ProductoController {
	@Autowired
 Producto unProducto;
	@Autowired
	@Qualifier("servicioProductoBD")
	IProductoService productoService;
	@GetMapping("/productos")
	public ModelAndView getWithproductos() {
		// codigo
		//Producto unProducto = new Producto();
       //transporte hacia la vista
		ModelAndView transportador = new ModelAndView("formularioProducto");
		transportador.addObject("producto", unProducto);

		return transportador;
	}
	@PostMapping("/guardarProducto")
	public ModelAndView guardarProducto (Producto producto) {
		productoService.guardarProducto(producto);

   // ProductoServiceImp  productoService = new ProductoServiceImp ();
	//	productoService.guardarProducto(producto);

		ModelAndView transportador = new ModelAndView("listaProductos");
		transportador.addObject("listadoProducto", productoService.listarTodosProducto());
		return transportador;
	}
	//eliminar
	@GetMapping("/eliminarProducto/{id}")
	public ModelAndView deleteProducto(@PathVariable(name = "id") String id) {
		productoService.eliminarProducto(id);
		// mostrar el nuevo listado
		ModelAndView modelView = new ModelAndView("listaProductos");
		modelView.addObject("listadoProducto", productoService.listarTodoActivos());
		return modelView;

	}

}
