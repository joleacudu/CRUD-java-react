package com.spring.mongo.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.spring.mongo.api.model.Producto;


public interface ProductoRepository extends MongoRepository<Producto, Long> {

}
