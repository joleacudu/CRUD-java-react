package com.spring.mongo.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.spring.mongo.api.model.Sucursal;

public interface SucursalRepository extends MongoRepository<Sucursal, Integer> {

}
