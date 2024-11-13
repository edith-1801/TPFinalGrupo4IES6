package ar.edu.ies6.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.ies6.model.Cliente;

@Controller
public class ClienteController {
	
	Cliente unCliente;
	
	//metodo para la vista inicial de la pagina
	
	@Controller
	public class IndexController {
	    @GetMapping("/")
	    public String index() {
	        return "index";
	    }
	
	
	//metodo para la vista cliente
	
	@GetMapping ("/nuevo")
	public ModelAndView getWithCliente() {
	
		
		ModelAndView transportador = new ModelAndView("Cliente");
		transportador.addObject("cliente",unCliente);
		transportador.addObject("band", false);
		
		return transportador;
	}
	

	}
}
