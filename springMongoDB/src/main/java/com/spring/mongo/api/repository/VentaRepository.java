package com.spring.mongo.api.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.spring.mongo.api.model.Venta;

public interface VentaRepository extends MongoRepository<Venta,Long>{
	
	public Venta findTopByOrderByCodigoVentaDesc();
	
	public List<Venta> findAllByCedulaUsuario(long cedulaU);
	
	public List<Venta> findAllByCedulaCliente(long cedulaC);

}
