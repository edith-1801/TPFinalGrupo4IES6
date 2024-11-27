package ar.edu.ies6.model;

import java.sql.Date;
import java.util.List;
import org.springframework.stereotype.Component;
import jakarta.persistence.*;

@Component
@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Date fechaCompra;

    @Column
    private String totalCompra;

    @Column
    private String metodoPago;

    @Column
    private String estadoCompra;

    @Column
    private String retiroEn; // Correctamente anotado

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String foto;

    @Column
    private String descripcionProducto; // Sin longitud específica

    @ManyToOne
    private Cliente cliente;

    @ManyToMany
    private List<Producto> productos;

    public Compra() {}

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
