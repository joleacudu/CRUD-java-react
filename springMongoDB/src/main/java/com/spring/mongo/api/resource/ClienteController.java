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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.mongo.api.model.Cliente;
import com.spring.mongo.api.model.Usuario;
import com.spring.mongo.api.repository.ClienteRepository;

@RequestMapping("/cliente")
@RestController
@CrossOrigin(origins="*", methods= {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
public class ClienteController {
	
	@Autowired
	private ClienteRepository repositorioClient;
	
	@PostMapping("/saveC")
	public String saveCliente(@RequestBody Cliente client) {
		repositorioClient.save(client);
		return "Usuario registrado: " + client.getNombreCliente();
	}
	
	@GetMapping("/listarC")
	public List<Cliente> listarClientes(){
		return repositorioClient.findAll();
	}
	
	@GetMapping("/buscarC/{cedula}")
	public Cliente buscarCedula(@PathVariable long cedula) {
		if(repositorioClient.findById(cedula).isPresent()) {
			return repositorioClient.findById(cedula).get();
		}
		return null;
	}
	
	@DeleteMapping("/borrarC/{cedula}")
	public String borrarCliente(@PathVariable long cedula) {
		repositorioClient.deleteById(cedula);
		return "Cliente borrado: " + cedula;
	}
	
	@PostMapping("/actualizarC")
	public String actualizarCliente(@RequestBody Cliente client) {
		repositorioClient.save(client);
		return "Cliente actualizado: " + client.getNombreCliente();
	}
}
