package ar.edu.ies6.model;

import java.sql.Date;

import java.util.List;

import org.springframework.stereotype.Component;
import jakarta.persistence.*;

@Component // Marca la clase como un componente Spring, permitiendo que Spring la gestione y la inyecte donde sea necesario.
@Entity // Indica que esta clase es una entidad JPA y será mapeada a una tabla de base de datos.
public class Compra {

    @Id // Indica que el campo 'id' es la clave primaria de la tabla.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Especifica que el valor de 'id' se generará automáticamente.
    private Long id;

    @Column // Marca el campo 'fechaCompra' para que sea persistido en la base de datos como una columna.
    private Date fechaCompra;

    @Column // Marca el campo 'totalCompra' para que sea persistido en la base de datos como una columna.
    private String totalCompra;

    @Column // Marca el campo 'metodoPago' para que sea persistido en la base de datos como una columna.
    private String metodoPago;

    @Column // Marca el campo 'estadoCompra' para que sea persistido en la base de datos como una columna.
    private String estadoCompra;

    @Column // Marca el campo 'retiroEn' para que sea persistido en la base de datos como una columna.
    private String retiroEn; // Correctamente anotado

    @Lob // Marca el campo 'foto' como un tipo de datos grande (Large Object).
    @Column(columnDefinition = "LONGTEXT") // Especifica que la columna 'foto' será de tipo LONGTEXT.
    private String foto;

    @Column // Marca el campo 'descripcionProducto' para que sea persistido en la base de datos como una columna.
    private String descripcionProducto; // Sin longitud específica

    @ManyToOne // Define una relación muchos a uno con la entidad Cliente.
    private Cliente cliente;

    @ManyToMany // Define una relación muchos a muchos con la entidad Producto.
    private List<Producto> productos;

    public Compra() {} // Constructor por defecto

    // Constructor con todos los atributos
    public Compra(Long id, Date fechaCompra, String totalCompra, String metodoPago, String estadoCompra, String foto, String descripcionProducto, Cliente cliente, List<Producto> productos, String retiroEn) {
        this.id = id;
        this.fechaCompra = fechaCompra;
        this.totalCompra = totalCompra;
        this.metodoPago = metodoPago;
        this.estadoCompra = estadoCompra;
        this.foto = foto;
        this.descripcionProducto = descripcionProducto;
        this.cliente = cliente;
        this.productos = productos;
        this.retiroEn = retiroEn;
    }

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public String getTotalCompra() {
        return totalCompra;
    }

    public void setTotalCompra(String totalCompra) {
        this.totalCompra = totalCompra;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String getEstadoCompra() {
        return estadoCompra;
    }

    public void setEstadoCompra(String estadoCompra) {
        this.estadoCompra = estadoCompra;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public String getRetiroEn() {
        return retiroEn;
    }

    public void setRetiroEn(String retiroEn) {
        this.retiroEn = retiroEn;
    }
}

