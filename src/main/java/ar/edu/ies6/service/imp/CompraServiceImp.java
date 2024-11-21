package ar.edu.ies6.service.imp;

import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ar.edu.ies6.model.Compra;
import ar.edu.ies6.service.ICompraService;
import ar.edu.ies6.util.AlmacenCompras;

@Service
@Qualifier("servicioCompraArrayList")
public class CompraServiceImp implements ICompraService {

    @Override
    public void guardarCompra(Compra compra) {
        AlmacenCompras.compras.add(compra);
    }

    @Override
    public void eliminarCompra(Long id) {
       
    }


    @Override
    public Compra consultarCompra(Long id) {
    
        return null;
    }

    @Override
    public List<Compra> listarTodasCompras() {
        return AlmacenCompras.compras;
    }

	@Override
	public List<Compra> listarComprasPorEstadoCompra(String estadoCompra) {
		return null;
	}

	@Override
	public void modificarCompra(Compra compra) {
		// TODO Auto-generated method stub
		
	}
}
  
