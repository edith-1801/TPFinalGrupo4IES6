package ar.edu.ies6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.ies6.model.Producto;
import ar.edu.ies6.service.IProductoService;
import ar.edu.ies6.service.imp.ProductoServiceImp;

@Controller
public class ProductoController {
	@Autowired
 Producto unProducto;
	@Autowired
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

    ProductoServiceImp  productoService = new ProductoServiceImp ();
		productoService.guardarProducto(producto);

		ModelAndView transportador = new ModelAndView("listaProductos");
		transportador.addObject("listadoProducto", productoService.listarTodosProducto());
		return transportador;
	}
}
