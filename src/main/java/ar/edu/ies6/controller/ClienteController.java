package ar.edu.ies6.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.ies6.model.Cliente;
import ar.edu.ies6.service.IClienteService;

@Controller
public class ClienteController {
	@Autowired
	Cliente unCliente;
	
	@Autowired
	@Qualifier("servicioClienteBD")
	IClienteService clienteService;
	
	//metodo para la vista inicial de la pagina
	
	
	
	    @GetMapping("/")
	    public String index() {
	        return "index";
	    }
	
	
	//metodo para la vista cliente
	
	@GetMapping ("/nuevoCliente")
	public ModelAndView getWithCliente() {
	
		
		ModelAndView transportador = new ModelAndView("cliente");
		transportador.addObject("nuevoCliente",  unCliente);
		transportador.addObject("band", false);
		
		return transportador;
	}
	@PostMapping("/guardarCliente")
	public ModelAndView guardarCliente(Cliente cliente) { 
		clienteService.guardarCliente(cliente); 
		
		System.out.println("DNI:"+ cliente.getDni());
		System.out.println("Nombre"+ cliente.getNombre());
		System.out.println("Apellido"+ cliente.getApellido());
		System.out.println("Email"+cliente.getEmail());
		System.out.println("Direccion"+ cliente.getDireccion());
		
		ModelAndView modelAndView = new ModelAndView("listadoClientes");
		modelAndView.addObject("listadoClientes", clienteService.listarTodosClientes());
		return modelAndView;
		
	}
	public class AlmacenClientes {
	    public static List<Cliente> cliente = new ArrayList<>();
	
	
	//mostrar nuevo listado
		
	}
}
