package ar.edu.ies6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.ies6.model.Compra;
import ar.edu.ies6.service.ICompraService;

@Controller
public class CompraController {

    @Autowired
    Compra unaCompra;

    @Autowired
    @Qualifier("servicioCompraBD")
    ICompraService compraService;

    @GetMapping("/compra")
    public ModelAndView getIndexWithCompra() {
        ModelAndView transportador = new ModelAndView("formularioCompra");
        transportador.addObject("compra", unaCompra);
        transportador.addObject("band", false);
        return transportador;
    }

    @GetMapping("/listadoCompras")
    public ModelAndView getAllCompras() {
        ModelAndView transportador = new ModelAndView("listaCompras");
        transportador.addObject("listadoCompras", compraService.listarTodasCompras());
        return transportador;
    }

    @PostMapping("/guardarNuevaCompra")
    public ModelAndView guardarCompra(Compra compra) {
        compraService.guardarCompra(compra);
        ModelAndView transportador = new ModelAndView("listaCompras");
        transportador.addObject("listadoCompras", compraService.listarTodasCompras());
        return transportador;
    }

    @GetMapping("/eliminarCompra/{id}")
    public ModelAndView eliminarCompra(@PathVariable(name = "id") Long id) {
        compraService.eliminarCompra(id);
        ModelAndView modelView = new ModelAndView("listaCompras");
        modelView.addObject("listadoCompras", compraService.listarTodasCompras());
        return modelView;
    }

    @GetMapping("/confirmarCompra/{id}")
    public ModelAndView confirmarCompra(@PathVariable(name = "id") Long id) {
        Compra compra = compraService.consultarCompra(id);
        ModelAndView modelView = new ModelAndView("confirmarCompra");
        modelView.addObject("compra", compra);
        return modelView;
    }


    @PostMapping("/realizarCompra/{id}")
    public ModelAndView realizarCompra(@PathVariable(name = "id") Long id, @RequestParam("metodoPago") String metodoPago) {
        Compra compra = compraService.consultarCompra(id);
        compra.setMetodoPago(metodoPago); // Actualizamos el método de pago
        compra.setEstadoCompra("Confirmada"); // Actualizamos el estado de la compra
        compraService.modificarCompra(compra);
        ModelAndView modelView = new ModelAndView("compraRealizada");
        modelView.addObject("compra", compra);
        return modelView;
    }
}

