package com.spring.mongo.api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "clientes")
public class Cliente {
	
	@Id
	private long cedulaCliente;
	private String direccionCliente;
	private String emailCliente;
	private String nombreCliente;
	private long telefonoCliente;
	
	public long getCedulaCliente() {
		return cedulaCliente;
	}
	public void setCedulaCliente(long cedulaCliente) {
		this.cedulaCliente = cedulaCliente;
	}
	public String getDireccionCliente() {
		return direccionCliente;
	}
	public void setDireccionCliente(String direccionCliente) {
		this.direccionCliente = direccionCliente;
	}
	public String getEmailCliente() {
		return emailCliente;
	}
	public void setEmailCliente(String emailCliente) {
		this.emailCliente = emailCliente;
	}
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	public long getTelefonoCliente() {
		return telefonoCliente;
	}
	public void setTelefonoCliente(long telefonoCliente) {
		this.telefonoCliente = telefonoCliente;
	}
	
	
}
