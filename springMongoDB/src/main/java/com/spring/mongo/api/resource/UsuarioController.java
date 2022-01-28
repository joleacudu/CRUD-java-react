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

import com.spring.mongo.api.model.Usuario;
import com.spring.mongo.api.repository.UsuarioRepository;

@RequestMapping("/user")
@RestController
@CrossOrigin(origins="*", methods= {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository repositorioU;
	
	/**
	 * REGISTRO UN USUARIO NUEVO EN LA BD, ANTES VALIDAR QUE NO EXISTA UN USER CON ESA ID
	 * @param user OBJ ENVIADO POR REQUESTBODY
	 * @return String  QUE CONFIRME EL REGISTRO EXITOSO 
	 */
	@PostMapping("/save")
	public String saveUsuario(@RequestBody Usuario user) {
		repositorioU.save(user);
		return "Usuario registrado: " + user.getNombreUsuario();
	}
	
	
	/**
	 * RETORNO UNA LISTA CON TODOS LOS REGISTROS DE LA COLECCIÓN USUARIOS
	 * @return  OBJ LIST<Usuario>
	 */
	@GetMapping("/listar")
	public List<Usuario> listarUsuarios(){
		System.out.println("msj de prueba listar");
		return repositorioU.findAll();
	}
	
	
	/**
	 * BUSCO UN REGISTRO QUE COINCIDA CON EL #CEDULA SOLICITADO, SI NO HAY RESULTADO RETORNAR NULL
	 * @param id long ENVIADO POR LA URL
	 * @return EL OBJ SOLICITUDADO O NULL
	 */
	@GetMapping("/buscarCC/{id}")
	public Usuario buscarCedula(@PathVariable long id) {
		if(repositorioU.findById(id).isPresent()) {
			return repositorioU.findById(id).get();
		}
		return null;
	}
	
	
	/**
	 * BUSCO UN REGISTRO POR EL CAMPO USUARIO   
	 * @param user (EL VALOR ES ENVIADO POR GET EN LA URL)
	 * @return RETORNA EL USUARIO SOLICITADO O NULL
	 */
	@GetMapping("/buscar2/{user}")
	public Usuario buscarUsuario(@PathVariable String user) {
		return repositorioU.findByUsuario(user);
	}
	
	
	/**
	 *ELIMINO UN REGISTRO DE LA COLECCIÓN SEGUN EL NÚMERO DE CEDULA
	 * @param cedula (VALOR LONG ENVIADO POR GET EN LA URL)
	 * @return String MENSAJE CONFIRMANDO LA ELIMINACIÓN
	 */
	@DeleteMapping("/borrar/{cedula}")
	public String borrarUsuario(@PathVariable long cedula) {
		repositorioU.deleteById(cedula);
		return "Usuario borrado: " + cedula;
	}
	
	
	/**
	 * ACTUALIZO UN REGISTRO EXISTENTE, VALIDAR PREVIAMENTE QUE EXISTA ESE REGISTRO ANTES 
	 * DE USAR ESTE MÉTODO
	 * @param user : OBJ CON LOS NUEVOS DATOS A MODIFICAR ENVIADO POR REQUESTBODY
	 * @return String MENSAJE QUE CONFIRME LA REALIZACIÓN DEL PROCESO
	 */
	@PostMapping("/actualizar")
	public String actualizarUsuario(@RequestBody Usuario user) {
		repositorioU.save(user);
		return "Usuario actualizado: " + user.getNombreUsuario();
	}
	
}
