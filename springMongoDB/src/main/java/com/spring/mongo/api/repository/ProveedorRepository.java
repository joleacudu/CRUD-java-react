package com.spring.mongo.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.spring.mongo.api.model.Proveedor;

public interface ProveedorRepository extends MongoRepository<Proveedor, Long>{
	
	//public Proveedor findByNombreProveedor(String prove);
}
