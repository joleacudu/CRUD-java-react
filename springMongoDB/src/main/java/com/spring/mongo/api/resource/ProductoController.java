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
import org.springframework.web.bind.annotation.RestController;

import com.spring.mongo.api.model.Producto;
import com.spring.mongo.api.repository.ProductoRepository;

@RequestMapping("/producto")
@RestController
@CrossOrigin(origins="*", methods= {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
public class ProductoController {
	
	@Autowired
	private ProductoRepository repositorioP;
	
	@PostMapping("/savep")
	public String saveProducto(@RequestBody Producto product) {
		repositorioP.save(product);
		return "Producto Registrado";
	}
	
	@GetMapping("/listarp")
	public List<Producto> ListarProductos(){
		return repositorioP.findAll();		
	}
	
	@GetMapping("/buscarp/{codigo}")
	public Producto buscarProducto(@PathVariable long codigo) {
		if(repositorioP.findById(codigo).isPresent()) {
			
			return repositorioP.findById(codigo).get();
		}
		return null;
	}
	
	/**
	 * BORRA TODO LOS REGISTROS DE LA COLECCIÓN, APLICARLO AL CARGAR UN ARCHIVO DE PRODUCTOS
	 * @return
	 */
	@DeleteMapping("/borrartodo")
	public String borrarProductos() {
		repositorioP.deleteAll();
		return "todos los productos fueron borrados";
	}
	
	@DeleteMapping("/borrarp/{id}")
	public String borrarProducto(@PathVariable long id) {
		repositorioP.deleteById(id);
		return "Producto #" + id + " Elimiminado";
	}
	
	/* FALTA TERMINARLO
	public void registrarLista(String listas) {
		List<Producto> listaP= new ArrayList<Producto>();
		Gson gson= new Gson();
		listaP= gson.fromJson(listas, "formato");
		for(Producto p: listaP) {
			repositorioP.save(p);
		}
				
	}
	*/
}
