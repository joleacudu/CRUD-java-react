package com.spring.mongo.api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "sucursales")
public class Sucursal {
	
	@Id
	private int idSucursal;
	private String nombreSucursal;
	private double ventasAcumuladas;
	
	public int getIdSucursal() {
		return idSucursal;
	}
	public void setIdSucursal(int idSucursal) {
		this.idSucursal = idSucursal;
	}
	public String getNombreSucursal() {
		return nombreSucursal;
	}
	public void setNombreSucursal(String nombreSucursal) {
		this.nombreSucursal = nombreSucursal;
	}
	public double getVentasAcumuladas() {
		return ventasAcumuladas;
	}
	public void setVentasAcumuladas(double ventasAcumuladas) {
		this.ventasAcumuladas = ventasAcumuladas;
	}
}
