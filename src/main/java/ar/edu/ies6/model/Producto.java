package ar.edu.ies6.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
@Component
@Entity
public class Producto {
	//atributos
	@Id
	private  String id;
	@Column
	private String nombre;
	@Column
	private String descripcion;
	@Column
	private double precio;
	@Column
	private int stock;
	@Column
	private boolean estado;
	@Column
	private  String  marca;
	
	public Producto() {
		// TODO Auto-generated constructor stub
	}

	

	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}



	public String getMarca() {
		return marca;
	}



	public void setMarca(String marca) {
		this.marca = marca;
	}


	
}
