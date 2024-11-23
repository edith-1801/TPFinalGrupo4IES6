package ar.edu.ies6.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.ies6.model.Cliente;

@Service
public interface IClienteService {
	
	//creacion de los Crud
	//métodos
	
	public void guardarCliente(Cliente cliente);
	public void eliminarCliente(String dni);
	public void modificarCliente(Cliente clienteModificado);
	public Cliente consultarCliente(String dni);
	public List<Cliente> listarTodosClientes();
	public List<Cliente> listarTodosClientesActivos();
	

}
