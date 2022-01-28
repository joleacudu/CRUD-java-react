package com.spring.mongo.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.mongo.api.model.Sucursal;
import com.spring.mongo.api.repository.SucursalRepository;

@RequestMapping("/sucursal")
@RestController
@CrossOrigin(origins="*", methods= {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
public class SucursalController {
	
	@Autowired
	private SucursalRepository repositorioS;

	@PostMapping("/save")
	public String saveSucursal(@RequestBody Sucursal newSucursal) {
		repositorioS.save(newSucursal);
		return "sucursal agregada: " + newSucursal.getNombreSucursal();
	}
	
	@GetMapping("/listar")
	public List<Sucursal> listarSucursales(){
		return repositorioS.findAll();
	}
	
	@GetMapping("/buscar/{id}")
	public Sucursal buscarSucursal(@PathVariable int id) {
		System.out.println("msj de prueba");
		if(repositorioS.findById(id).isPresent()) {
			Sucursal s=repositorioS.findById(id).get();
			System.out.println("msj2: "+s.getNombreSucursal());
			return repositorioS.findById(id).get();
		}
		return null;
	}
}
