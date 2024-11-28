package ar.edu.ies6.util; // Define el paquete al que pertenece esta clase, ayudando a organizar el código del proyecto.

import java.util.ArrayList; // Importa la clase ArrayList del paquete java.util, que se usa para manejar listas dinámicas.
import java.util.List; // Importa la clase List del paquete java.util, que se usa para manejar listas.

import ar.edu.ies6.model.Compra; // Importa la entidad Compra del paquete ar.edu.ies6.model.

public class AlmacenCompras {
    // Define una lista estática para almacenar objetos de tipo Compra.
    public static List<Compra> compras = new ArrayList<>();
}
