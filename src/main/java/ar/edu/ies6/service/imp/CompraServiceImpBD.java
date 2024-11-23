package ar.edu.ies6.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ar.edu.ies6.model.Compra;
import ar.edu.ies6.repository.CompraRepository;
import ar.edu.ies6.service.ICompraService;

@Service
@Qualifier("servicioCompraBD")
public class CompraServiceImpBD implements ICompraService {

    @Autowired
    CompraRepository compraRepository;

    @Override
    public void guardarCompra(Compra compra) {
        compra.setEstadoCompra("pendiente"); 
        compraRepository.save(compra);
    }

    @Override
    public void eliminarCompra(Long id) {
        Optional<Compra> compraEncontrada = compraRepository.findById(id);
        if (compraEncontrada.isPresent()) {
            compraEncontrada.get().setEstadoCompra("cancelado"); 
            compraRepository.save(compraEncontrada.get());
        }
    }

    @Override
    public Compra consultarCompra(Long id) {
        return compraRepository.findById(id).orElse(null);
    }

    @Override
    public List<Compra> listarTodasCompras() {
        return (List<Compra>) compraRepository.findAll();
    }

    @Override
    public List<Compra> listarComprasPorEstadoCompra(String estadoCompra) {
        return compraRepository.findByEstadoCompra(estadoCompra);
    }

	@Override
	public void modificarCompra(Compra compra) {
		// TODO Auto-generated method stub
		
	}



}
