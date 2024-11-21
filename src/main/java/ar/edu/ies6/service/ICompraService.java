package ar.edu.ies6.service;

import java.util.List;
import org.springframework.stereotype.Service;
import ar.edu.ies6.model.Compra;


@Service
public interface ICompraService {
    public void guardarCompra (Compra compra);
    public void eliminarCompra(Long id);
    public Compra consultarCompra(Long id);
    public List<Compra> listarTodasCompras();
    public List<Compra> listarComprasPorEstadoCompra(String estadoCompra);
	public void modificarCompra(Compra compra);


}
