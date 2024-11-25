package ar.edu.ies6.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ar.edu.ies6.model.Cliente;
import ar.edu.ies6.service.IClienteService;
import ar.edu.ies6.util.AlmacenCliente;

@Service
public class ClienteServiceImp implements IClienteService{

	@Override
	@Qualifier ("servicioClienteArrayList")
	public void guardarCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		AlmacenCliente.clientes.add(cliente);
		System.out.println("Cliente guardado: " + cliente);
		
		
		
	}

	@Override
	public void eliminarCliente(String dni) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void modificarCliente(Cliente clienteModificado) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Cliente consultarCliente(String dni) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cliente> listarTodosClientes() {
		// TODO Auto-generated method stub
		return AlmacenCliente.clientes;
	}

	@Override
	public List<Cliente> listarTodosClientesActivos() {
		// TODO Auto-generated method stub
		return null;
	}

}
