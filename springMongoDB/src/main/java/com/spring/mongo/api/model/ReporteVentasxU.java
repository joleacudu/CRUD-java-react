package com.spring.mongo.api.model;

public class ReporteVentasxU {
	private long CedulaUsuario;
	private String nombreUsuario;
	private double totalVentas;
	public long getCedulaUsuario() {
		return CedulaUsuario;
	}
	public void setCedulaUsuario(long cedulaUsuario) {
		CedulaUsuario = cedulaUsuario;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public double getTotalVentas() {
		return totalVentas;
	}
	public void setTotalVentas(double totalVentas) {
		this.totalVentas = totalVentas;
	}
}
