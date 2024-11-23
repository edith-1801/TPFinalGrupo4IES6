package ar.edu.ies6.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.stereotype.Service;

import ar.edu.ies6.model.Cliente;
import ar.edu.ies6.repository.ClienteRepository;
import ar.edu.ies6.service.IClienteService;


@Service

@Qualifier("servicioClienteBD")
public class ClienteServiceImpBD implements IClienteService {
	
	@Autowired
	ClienteRepository clienteRepository;

	@Override
	public void guardarCliente(Cliente cliente) {
		// TODO Auto-generated method stub
	    //guardar cliente en BD
		cliente.setEstado(true);
		clienteRepository.save(cliente);
	}

	@Override
	public void eliminarCliente(String dni) {
		// TODO Auto-generated method stub
		Optional<Cliente> clienteEncontrado = clienteRepository.findById(dni);
		clienteEncontrado.get().setEstado(false);
		clienteRepository.save(clienteEncontrado.get());
		
	}

	@Override
	public void modificarCliente(Cliente clienteModificado) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Cliente consultarCliente(String dni) {
		// TODO Auto-generated method stub
		return clienteRepository.findById(dni).get();
	}

	@Override
	public List<Cliente> listarTodosClientes() {
		// TODO Auto-generated method stub
		return (List<Cliente>) clienteRepository.findAll();
	}
	@Override
	public List<Cliente> listarTodosClientesActivos() {
		// TODO Auto-generated method stub
		return (List<Cliente>) clienteRepository.findByEstado(true);
	}

}
