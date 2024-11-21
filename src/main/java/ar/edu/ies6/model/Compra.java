package ar.edu.ies6.model;

import java.sql.Date;

import org.springframework.stereotype.Component;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

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
    private String imagenUrl;

    @Column(length = 1000)  // Incrementa el tamaño máximo permitido
    private String descripcionProducto;

    public Compra() {}

    // Constructor con todos los atributos
    public Compra(Long id, Date fechaCompra, String totalCompra, String metodoPago, String estadoCompra, String imagenUrl, String descripcionProducto) {
        this.id = id;
        this.fechaCompra = fechaCompra;
        this.totalCompra = totalCompra;
        this.metodoPago = metodoPago;
        this.estadoCompra = estadoCompra;
        this.imagenUrl = imagenUrl;
        this.descripcionProducto = descripcionProducto;
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

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }
}

