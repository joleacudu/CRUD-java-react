package com.spring.mongo.api.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "proveedores")
public class Proveedor {

	@Id
	private long nitProveedor;
	private String ciudadProveedor;
	private String direccionProveedor;
	private String nombreProveedor;
	private String telefonoProveedor;
	
	public long getNitProveedor() {
		return nitProveedor;
	}
	public void setNitProveedor(long nitProveedor) {
		this.nitProveedor = nitProveedor;
	}
	public String getCiudadProveedor() {
		return ciudadProveedor;
	}
	public void setCiudadProveedor(String ciudadProveedor) {
		this.ciudadProveedor = ciudadProveedor;
	}
	public String getDireccionProveedor() {
		return direccionProveedor;
	}
	public void setDireccionProveedor(String direccionProveedor) {
		this.direccionProveedor = direccionProveedor;
	}
	public String getNombreProveedor() {
		return nombreProveedor;
	}
	public void setNombreProveedor(String nombreProveedor) {
		this.nombreProveedor = nombreProveedor;
	}
	public String getTelefonoProveedor() {
		return telefonoProveedor;
	}
	public void setTelefonoProveedor(String telefonoProveedor) {
		this.telefonoProveedor = telefonoProveedor;
	}
}
