package ar.edu.ies6.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class ProductoController {
	@GetMapping ("/productos")
	public String getproductos() {
		//codigo
		return "formularioProducto";
	}
		

}
