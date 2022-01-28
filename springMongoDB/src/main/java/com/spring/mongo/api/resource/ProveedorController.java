package com.spring.mongo.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.mongo.api.model.Proveedor;
import com.spring.mongo.api.repository.ProveedorRepository;

@RequestMapping("/proveedor")
@RestController
@CrossOrigin(origins="*", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
public class ProveedorController {
	
	@Autowired
	public ProveedorRepository repositorioProve;
	
	@PostMapping("/saveP")
	public String saveProveedor(@RequestBody Proveedor provee) {
		repositorioProve.save(provee);
		return "Proveedor creado: " + provee.getNombreProveedor();
	}
	
	@GetMapping("/listarP")
	public List<Proveedor> listarProveedor(){
		return repositorioProve.findAll();
	}
	
	@GetMapping("/buscarP/{id}")
	public Proveedor buscarCedula(@PathVariable long id) {
		if(repositorioProve.findById(id).isPresent()==true) {
			return repositorioProve.findById(id).get();
		}
		return null;
	}
	
	@PostMapping("/actualizarP")
	public String actualizarProveedor(@RequestBody Proveedor provee) {
		repositorioProve.save(provee);
		return "Proveedor actualizado con exito";
	}
	
	@DeleteMapping("/borrarP/{nit}")
	public String borrarProveedor(@PathVariable long nit) {
		repositorioProve.deleteById(nit);
		return "Proveedor eliminado de los registros exitosamente.";
	}
}
