package com.spring.mongo.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.spring.mongo.api.model.Cliente;

public interface ClienteRepository extends MongoRepository<Cliente, Long>{
	
	//public Cliente findByNombreCliente(String client);
}
