package ar.edu.ies6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
		modelAndView.addObject("listadoClientes", clienteService.listarTodosClientesActivos());
		return modelAndView;
		
	}
	//public class AlmacenClientes {
	  //  public static List<Cliente> cliente = new ArrayList<>();
	
	
	
	
	
	
	
	
	//mostrar nuevo listado
	    @GetMapping("/listadoClientes")
	    public ModelAndView listarClientes() {
	    	ModelAndView modelAndView = new ModelAndView("listadoClientes");
	    	modelAndView.addObject("listadoClientes", clienteService.listarTodosClientesActivos());
	    	return modelAndView;
	    }
	    @GetMapping("/eliminarCliente/{dni}")
		public ModelAndView deleteCliente(@PathVariable(name="dni")String dni){ 
		clienteService.eliminarCliente(dni);
		
		//mostrar nuevo listado
		ModelAndView modelView = new ModelAndView("listadoClientes");
	    modelView.addObject("listadoClientes", clienteService.listarTodosClientesActivos());
	    return modelView;
	    }
	    
	  //modificar
		@GetMapping("/modificarCliente/{dni}")
		public ModelAndView mofificarCliente (@PathVariable(name="dni")String dni) {
			//el parametro del constructor de ModelAndView es una html
			ModelAndView modelView = new ModelAndView("cliente");
		    modelView.addObject("nuevoCliente", clienteService.consultarCliente(dni));
		    modelView.addObject("band", true);
		   
		    return modelView;
		    
		}
		
		
	}

