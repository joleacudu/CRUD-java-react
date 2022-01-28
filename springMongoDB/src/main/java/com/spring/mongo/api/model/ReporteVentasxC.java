package com.spring.mongo.api.model;

public class ReporteVentasxC {
	private long cedulaCliente;
	private String nombreCliente;
	private Double totalVentas;
	public long getCedulaCliente() {
		return cedulaCliente;
	}
	public void setCedulaCliente(long cedulaCliente) {
		this.cedulaCliente = cedulaCliente;
	}
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	public Double getTotalVentas() {
		return totalVentas;
	}
	public void setTotalVentas(Double totalVentas) {
		this.totalVentas = totalVentas;
	}
	
	

}
