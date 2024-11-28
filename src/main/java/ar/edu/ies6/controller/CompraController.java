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

    // Logger para registrar información sobre la ejecución de la aplicación
    private static final Logger logger = LoggerFactory.getLogger(CompraController.class);

    @Autowired
    Compra unaCompra; // Inyección de dependencia para el objeto Compra

    @Autowired
    @Qualifier("servicioCompraBD")
    ICompraService compraService; // Servicio para manejar operaciones de compra

    @Autowired
    @Qualifier("servicioProductoBD")
    private IProductoService productoService; // Servicio para manejar operaciones de producto

    @Autowired
    @Qualifier("servicioClienteBD")
    private IClienteService clienteService; // Servicio para manejar operaciones de cliente

    // Método para mostrar el formulario de nueva compra
    @GetMapping("/compra")
    public ModelAndView getIndexWithCompra() {
        ModelAndView transportador = new ModelAndView("formularioCompra");
        transportador.addObject("compra", unaCompra);
        transportador.addObject("clientes", clienteService.listarTodosClientes());
        transportador.addObject("productos", productoService.listarTodosProducto());
        transportador.addObject("band", false); // Indica que es una nueva compra y no una modificación
        return transportador;
    }

    // Método para listar todas las compras no canceladas
    @GetMapping("/listadoCompras")
    public ModelAndView getAllCompras() {
        ModelAndView transportador = new ModelAndView("listaCompras");
        transportador.addObject("listadoCompras", compraService.listarTodasCompras().stream()
                .filter(compra -> !compra.getEstadoCompra().equals("Cancelada")) // Filtra las compras no canceladas
                .collect(Collectors.toList()));
        return transportador;
    }

    // Método para guardar una nueva compra
    @PostMapping("/guardarNuevaCompra")
    public ModelAndView guardarCompra(Compra compra, @RequestParam("imagen") MultipartFile imagen) {
        if (!imagen.isEmpty()) {
            try {
                byte[] bytes = imagen.getBytes(); // Convierte la imagen a bytes
                String base64Image = Base64.getEncoder().encodeToString(bytes); // Codifica la imagen en Base64
                compra.setFoto(base64Image); // Establece la imagen en el objeto compra
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        compraService.guardarCompra(compra); // Guarda la compra en la base de datos
        return new ModelAndView("redirect:/listadoCompras"); // Redirige a la lista de compras
    }

    // Método para cancelar una compra
    @GetMapping("/eliminarCompra/{id}")
    public ModelAndView eliminarCompra(@PathVariable(name = "id") Long id) {
        Compra compra = compraService.consultarCompra(id); // Consulta la compra por ID
        if (compra != null) {
            compra.setEstadoCompra("Cancelada"); // Establece el estado de la compra como cancelada
            compraService.modificarCompra(compra); // Guarda los cambios en la base de datos
        }
        return new ModelAndView("redirect:/listadoCompras"); // Redirige a la lista de compras
    }

    // Método para mostrar el formulario de modificación de compra
    @GetMapping("/modificarCompra/{id}")
    public ModelAndView modificarCompra(@PathVariable(name = "id") Long id) {
        Compra compra = compraService.consultarCompra(id); // Consulta la compra por ID
        ModelAndView modelView = new ModelAndView("formularioCompra");
        modelView.addObject("compra", compra);
        modelView.addObject("band", true); // Indica que es una modificación de compra
        modelView.addObject("clientes", clienteService.listarTodosClientes());
        modelView.addObject("productos", productoService.listarTodosProducto());
        return modelView;
    }

    // Método para guardar los cambios de una compra modificada
    @PostMapping("/guardarCompraModificada")
    public ModelAndView guardarCompraModificada(Compra compra, @RequestParam("imagen") MultipartFile imagen) {
        if (!imagen.isEmpty()) {
            try {
                byte[] bytes = imagen.getBytes(); // Convierte la imagen a bytes
                String base64Image = Base64.getEncoder().encodeToString(bytes); // Codifica la imagen en Base64
                compra.setFoto(base64Image); // Establece la imagen en el objeto compra
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        compraService.modificarCompra(compra); // Guarda los cambios en la base de datos
        return new ModelAndView("redirect:/listadoCompras"); // Redirige a la lista de compras
    }

    // Método para confirmar una compra
    @GetMapping("/confirmarCompra/{id}")
    public ModelAndView confirmarCompra(@PathVariable(name = "id") Long id) {
        Compra compra = compraService.consultarCompra(id); // Consulta la compra por ID
        ModelAndView modelView = new ModelAndView("confirmarCompra");
        modelView.addObject("compra", compra);
        modelView.addObject("productos", productoService.listarTodosProducto());
        modelView.addObject("clientes", clienteService.listarTodosClientes());
        return modelView;
    }

    // Método para realizar una compra
    @PostMapping("/realizarCompra/{id}")
    public ModelAndView realizarCompra(@PathVariable(name = "id") Long id, @RequestParam("metodoPago") String metodoPago, @RequestParam("productosIds") String productosIds, @RequestParam("clienteId") String clienteId, @RequestParam("cantidad") int cantidad, @RequestParam("retiroEn") String retiroEn) {
        logger.info("Realizando compra con ID: {}", id); // Registrar información
        logger.info("Método de Pago: {}", metodoPago);
        logger.info("Producto ID: {}", productosIds);
        logger.info("Cliente ID: {}", clienteId);
        logger.info("Cantidad: {}", cantidad);
        logger.info("Retiro En: {}", retiroEn);

        Compra compra = compraService.consultarCompra(id); // Consulta la compra por ID
        compra.setMetodoPago(metodoPago); // Actualiza el método de pago
        compra.setEstadoCompra("Confirmada"); // Actualiza el estado de la compra
        compra.setRetiroEn(retiroEn); // Actualiza el lugar de retiro
        Cliente cliente = clienteService.consultarCliente(clienteId);
        compra.setCliente(cliente); // Asigna el cliente a la compra

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

    // Método para cancelar una compra
    @PostMapping("/cancelarCompra/{id}")
    public ModelAndView cancelarCompra(@PathVariable(name = "id") Long id) {
        Compra compra = compraService.consultarCompra(id); // Consulta la compra por ID
        if (compra != null) {
            compra.setEstadoCompra("Cancelada"); // Establece el estado de la compra como cancelada// no me funciona
            compraService.modificarCompra(compra); // Guarda los cambios en la base de datos
        }
        return new ModelAndView("redirect:/listadoCompras"); // Redirige a la lista de compras
    }
}
