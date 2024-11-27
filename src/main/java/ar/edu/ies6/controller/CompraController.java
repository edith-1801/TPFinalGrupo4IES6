package ar.edu.ies6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.ies6.model.Cliente;
import ar.edu.ies6.model.Compra;
import ar.edu.ies6.model.Producto;
import ar.edu.ies6.service.ICompraService;
import ar.edu.ies6.service.IProductoService;
import ar.edu.ies6.service.IClienteService;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class CompraController {

    private static final Logger logger = LoggerFactory.getLogger(CompraController.class);

    @Autowired
    Compra unaCompra;

    @Autowired
    @Qualifier("servicioCompraBD")
    ICompraService compraService;

    @Autowired
    @Qualifier("servicioProductoBD")
    private IProductoService productoService;

    @Autowired
    @Qualifier("servicioClienteBD")
    private IClienteService clienteService;

    @GetMapping("/compra")
    public ModelAndView getIndexWithCompra() {
        ModelAndView transportador = new ModelAndView("formularioCompra");
        transportador.addObject("compra", unaCompra);
        transportador.addObject("clientes", clienteService.listarTodosClientes());
        transportador.addObject("productos", productoService.listarTodosProducto());
        transportador.addObject("band", false);
        return transportador;
    }

    @GetMapping("/listadoCompras")
    public ModelAndView getAllCompras() {
        ModelAndView transportador = new ModelAndView("listaCompras");
        transportador.addObject("listadoCompras", compraService.listarTodasCompras().stream()
                .filter(compra -> !compra.getEstadoCompra().equals("Cancelada"))
                .collect(Collectors.toList()));
        return transportador;
    }

    @PostMapping("/guardarNuevaCompra")
    public ModelAndView guardarCompra(Compra compra, @RequestParam("imagen") MultipartFile imagen) {
        if (!imagen.isEmpty()) {
            try {
                byte[] bytes = imagen.getBytes();
                String base64Image = Base64.getEncoder().encodeToString(bytes);
                compra.setFoto(base64Image); // Usar el campo Foto
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        compraService.guardarCompra(compra);
        return new ModelAndView("redirect:/listadoCompras");
    }

    @GetMapping("/eliminarCompra/{id}")
    public ModelAndView eliminarCompra(@PathVariable(name = "id") Long id) {
        Compra compra = compraService.consultarCompra(id);
        if (compra != null) {
            compra.setEstadoCompra("Cancelada");
            compraService.modificarCompra(compra);
        }
        return new ModelAndView("redirect:/listadoCompras");
    }

    @GetMapping("/modificarCompra/{id}")
    public ModelAndView modificarCompra(@PathVariable(name = "id") Long id) {
        Compra compra = compraService.consultarCompra(id);
        ModelAndView modelView = new ModelAndView("formularioCompra");
        modelView.addObject("compra", compra);
        modelView.addObject("band", true);  
        modelView.addObject("clientes", clienteService.listarTodosClientes());
        modelView.addObject("productos", productoService.listarTodosProducto());
        return modelView;
    }

    @PostMapping("/guardarCompraModificada")
    public ModelAndView guardarCompraModificada(Compra compra, @RequestParam("imagen") MultipartFile imagen) {
        if (!imagen.isEmpty()) {
            try {
                byte[] bytes = imagen.getBytes();
                String base64Image = Base64.getEncoder().encodeToString(bytes);
                compra.setFoto(base64Image); // Usa el campo Foto
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        compraService.modificarCompra(compra);
        return new ModelAndView("redirect:/listadoCompras");
    }

    @GetMapping("/confirmarCompra/{id}")
    public ModelAndView confirmarCompra(@PathVariable(name = "id") Long id) {
        Compra compra = compraService.consultarCompra(id);
        ModelAndView modelView = new ModelAndView("confirmarCompra");
        modelView.addObject("compra", compra);
        modelView.addObject("productos", productoService.listarTodosProducto());
        modelView.addObject("clientes", clienteService.listarTodosClientes());
        return modelView;
    }

    @PostMapping("/realizarCompra/{id}")
    public ModelAndView realizarCompra(@PathVariable(name = "id") Long id, @RequestParam("metodoPago") String metodoPago, @RequestParam("productosIds") String productosIds, @RequestParam("clienteId") String clienteId, @RequestParam("cantidad") int cantidad, @RequestParam("retiroEn") String retiroEn) {
        logger.info("Realizando compra con ID: {}", id);
        logger.info("Método de Pago: {}", metodoPago);
        logger.info("Producto ID: {}", productosIds);
        logger.info("Cliente ID: {}", clienteId);
        logger.info("Cantidad: {}", cantidad);
        logger.info("Retiro En: {}", retiroEn);

        Compra compra = compraService.consultarCompra(id);
        compra.setMetodoPago(metodoPago); // Actualizamos el método de pago
        compra.setEstadoCompra("Confirmada"); // Actualizamos el estado de la compra
        compra.setRetiroEn(retiroEn); // Actualizamos el lugar de retiro
        Cliente cliente = clienteService.consultarCliente(clienteId);
        compra.setCliente(cliente); // Asignamos el cliente a la compra

        // Obtener producto por ID y añadirlo a la compra
        Producto producto = productoService.consultarProducto(productosIds);
        List<Producto> productos = List.of(producto);

        if (productos.isEmpty()) {
            logger.error("No se encontraron productos con los IDs proporcionados.");
            throw new IllegalArgumentException("No se encontraron productos con los IDs proporcionados.");
        }

        compra.setProductos(productos);
        // Implementa lógica para manejar las cantidades
        compraService.modificarCompra(compra);
        ModelAndView modelView = new ModelAndView("compraRealizada");
        modelView.addObject("compra", compra);
        modelView.addObject("productos", compra.getProductos());
        modelView.addObject("cantidad", List.of(cantidad)); // Añade la cantidad a la vista
        return modelView;
    }

    @PostMapping("/cancelarCompra/{id}")
    public ModelAndView cancelarCompra(@PathVariable(name = "id") Long id) {
        Compra compra = compraService.consultarCompra(id);
        if (compra != null) {
            compra.setEstadoCompra("Cancelada");
            compraService.modificarCompra(compra);
        }
        return new ModelAndView("redirect:/listadoCompras");
    }
}
