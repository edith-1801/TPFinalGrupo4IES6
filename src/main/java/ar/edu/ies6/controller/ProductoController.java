package ar.edu.ies6.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import ar.edu.ies6.model.Producto;

@Controller
public class ProductoController {
	@GetMapping("/productos")
	public ModelAndView getWithproductos() {
		// codigo
		Producto unProducto = new Producto();

		ModelAndView transportador = new ModelAndView("formularioProducto");
		transportador.addObject("producto", unProducto);

		return transportador;
	}

}
