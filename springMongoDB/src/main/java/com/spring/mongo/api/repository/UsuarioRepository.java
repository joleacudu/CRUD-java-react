package com.spring.mongo.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.spring.mongo.api.model.Usuario;

public interface UsuarioRepository extends MongoRepository<Usuario, Long>{
	public Usuario findByUsuario(String user);
}
