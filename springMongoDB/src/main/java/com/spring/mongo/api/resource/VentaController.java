package com.spring.mongo.api.resource;

import java.util.ArrayList;
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

import com.spring.mongo.api.model.Cliente;
import com.spring.mongo.api.model.ReporteVentasxC;
import com.spring.mongo.api.model.ReporteVentasxU;
import com.spring.mongo.api.model.Sucursal;
import com.spring.mongo.api.model.Usuario;
import com.spring.mongo.api.model.Venta;
import com.spring.mongo.api.repository.VentaRepository;

import ch.qos.logback.core.net.SyslogOutputStream;

@RequestMapping("/venta")
@RestController
@CrossOrigin(origins="*", methods= {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
public class VentaController {
	
	@Autowired
	private VentaRepository repositorioV;
	@Autowired
	private SucursalController controladorSucursal;
	@Autowired
	private ClienteController controladorCliente;
	@Autowired
	private UsuarioController controladorUsuario;
	
	/**
	 * LA VENTA VIENE SI N UN CODIGO DE VENTA, EL CUAL SE AGREGA CON EL MÉTODO ultimoIdVenta()+1.
	 * SE CARGA EL codigoVenta AL OBJ VENTA Y ESTE ES REGISTRADO EN LA BD
	 * EL VALOR TOTAL ES AGREGADO AL ACUMULADO DE VENTAS DE LA SUCURSAL CORRESPONDIENTE
	 * @param venta  OBJ SIN CÓDIGO DE VENTA
	 * @return  long CÓDIGO QUE SE ASIGNO A LA VENTA EN SU MOMENTO DE REGISTRO
	 */
	@PostMapping("/savev")
	public long registrarVenta(@RequestBody Venta venta) {
		venta.setCodigoVenta(this.ultimoIdVenta()+1);
		repositorioV.save(venta);
		
		//INCREMENTO LA VENTA EN EL TOTAL DE LA SUCURSAL
		Sucursal s = controladorSucursal.buscarSucursal(venta.getIdSucursal());
		double valorAcumulado= s.getVentasAcumuladas() + venta.getTotalVenta();
		s.setVentasAcumuladas(valorAcumulado);
		controladorSucursal.saveSucursal(s);
		return venta.getCodigoVenta();
	}

	/**
	 * LISTO TODAS LAS VENTAS EN LA TABLA/COLLECTION 
	 * @return  OBJ TIPO List<Venta>
	 */
	@GetMapping("/listar")
	public List<Venta> listarVentas(){
		return repositorioV.findAll();
	}

	/**
	 * SOLICITA EL REGISTRO DE VENTA QUE TENGA EL codigoVenta MAYOR/ULTIMO REGISTRADO
	 * @return long CON EL ULTIMO codigoVenta REGISTRADO
	 */
	@GetMapping("/serial")
	public long ultimoIdVenta() {
		 Venta v = repositorioV.findTopByOrderByCodigoVentaDesc();
		 if(v!=null) {
			 return v.getCodigoVenta();
		 }
		 else {
			 return 0;
		 }
	}
	
	
	/**
	 * FILTRO LOS REGISTROS DE LA TABLA/COLLECTION POR CEDULAA DE UN CLIENTE Y RETORNA UNA  List<Venta>
	 * SI ESE CLIENTE NO TIENE  VENTAS REGISTRAS A SU NOMBRE DEVOLVERA UNA LISTA VACIA
	 * @param id     cedulaCliente
	 * @return		List<Venta>
	 */
	@GetMapping("/filtrarxcliente/{id}")
	public List<Venta> filtrarVentasxCliente(@PathVariable long id){
		return repositorioV.findAllByCedulaCliente(id);
	}
	
	
	/**
	 * FILTRO LA TABLA/COLLECTION VENTAS POR LA CEDULA DE UN USUARIO Y RETORNO UNA LISTA CON TODAS LAS VENTAS
	  QUE ESE USUARIO HAYA REGISTRADO
	 *SI EL USUARIO NO HA REGISTRADO VENTAS SE DEVUELVE UNA LISTA VACIA 
	 * @param id    cedulaUsuario
	 * @return   List<Venta>
	 */
	@GetMapping("/filtrarxusuario/{id}")
	public List<Venta> filtrarVentasxUsuario(@PathVariable long id){
		return repositorioV.findAllByCedulaUsuario(id);
	}
	
	
	//MÉTODOS PARA LOS REPORTES 
	
//					REPORTES DE VENTAS  X USUARIOS      				//	
	/**
	 * RECIBE UNA LISTA DE VENTAS Y EXTRAE LAS CEDULAS DE USUARIOS PRESENTES EN ELLA
	 * @param listaV   LIST<VENTA>
	 * @return  List<Long> CON LAS DEDULAS DE USUARIOS QUE REGISTRARON VENTAS
	 */
	public List<Long> listarCedulasU(List<Venta> listaV){
		List<Long> cedulasU= new ArrayList<>();
		for(Venta v : listaV) {
			long cedula= v.getCedulaUsuario();
			if(!cedulasU.contains(cedula)) {
				cedulasU.add(cedula);
			}
		}
		return cedulasU;					
	}
	
	
	/**
	 * CALCULO EL TOTAL DE LAS VENTAS DE UN UNICO USUARIO EN TODAS LAS SUCURSALES
	 * @param cedula
	 * @param listaV
	 * @return  double   TOTAL DE  SUS VENTAS REGISTRADAS EN TODAS LAS SUCURSALES
	 */
	public double totalVentasxU(long cedula,List<Venta> listaV) {
		double sumaVentasU=0;
		for(Venta v : listaV ) {
			if(v.getCedulaUsuario()==cedula) {
				sumaVentasU += v.getTotalVenta();
			}
		}
		return sumaVentasU;
	}
	
	
	/**
	 * GENERA UNA LISTA DE OBJ TIPO reporteVentasU INDICANDO EL VALOR TOTAL DE LAS VENTAS
	   REGISTRADAS POR CADA USUARIO 
	   (SI UN USUARIO NO APARECE EN LA LISTA ES PORQUE NO HA REGISTRADO VENTAS)
	 * 
	 * @return List<ReporteVentasU>  o NULL
	 */
	@GetMapping("/reporteVentasU")
	public List<ReporteVentasxU> reporteVentasU(){
		List<Venta> listaV = repositorioV.findAll();
		 System.out.println(listaV.size());
		
		if(listaV != null) {
		
			List<ReporteVentasxU> listaReporte = new ArrayList<>();
			List<Long> listaCedulas = this.listarCedulasU(listaV);
			System.out.println(listaCedulas.size());
			
			for(Long c : listaCedulas) {
				ReporteVentasxU reporteU = new ReporteVentasxU();
				Usuario usuario = new Usuario();
				usuario = this.controladorUsuario.buscarCedula(c);
				
				double totalVentasU = this.totalVentasxU(c, listaV);
				System.out.println(totalVentasU);
				
				String nombreU = usuario.getNombreUsuario();
				
				reporteU.setCedulaUsuario(c);
				reporteU.setNombreUsuario(nombreU);
				reporteU.setTotalVentas(totalVentasU);
				System.out.println(reporteU.getNombreUsuario());
				
				listaReporte.add(reporteU);
			}
			 System.out.print("prueba");
			return listaReporte;
			
		}
		return null;
	}
	
	
//				REPORTES DE VENTAS  X CLIENTES      		//
	/**
	 *RECIBE UNA LISTA DE VENTAS Y EXTRAE LAS CEDULAS DE CLIENTES PRESENTES EN ELLA
	 * @param listaV   LIST<VENTA>
	 * @return  List<Long> CON LAS DEDULAS DE CLIENTES QUE REALIZARON COMPRAS
	 */
	public List<Long> listarCedulasC(List<Venta> listaV){
		List<Long> cedulasC= new ArrayList<>();
		for(Venta v : listaV) {
			long cedula= v.getCedulaCliente();
			if(!cedulasC.contains(cedula)) {
				cedulasC.add(cedula);
			}
		}
		return cedulasC;
	}
	
	
	/**
	 * CALCULO EL TOTAL DE LAS VENTAS/COMPRAS DE UN UNICO CLIENTE EN TODAS LAS SUCURSALES
	 * @param cedula
	 * @param listaV
	 * @return  double   TOTAL DE SUS COMPRAS EN TODAS LAS SUCURSALES
	 */
	public double totalVentasxC(long cedula,List<Venta> listaV) {
		double sumaVentasC=0;
		for(Venta v : listaV ) {
			if(v.getCedulaCliente()==cedula) {
				sumaVentasC += v.getTotalVenta();
			}
		}
		return sumaVentasC;
	}
	
	
	/**
	 * GENERA UNA LISTA DE OBJ TIPO reporteVentasC INDICANDO EL VALOR TOTAL DE LAS VENTAS
	   QUE ADQUIRIO CADA CLIENTE
	   (SI UN CLIENTE NO APARECE EN LA LISTA ES PORQUE NO HA REALIZADO COMPRAS)
	 * 
	 * @return List<ReporteVentasC>  o NULL
	 */
	@GetMapping("/reporteVentasC")
	public List<ReporteVentasxC> reporteVentasC(){
		List<Venta> listaV = repositorioV.findAll();
		if(listaV != null) {
			List<ReporteVentasxC> listaReporte = new ArrayList<>();
			List<Long> listaCedulas = this.listarCedulasC(listaV);
			
			for(Long c : listaCedulas) {
				ReporteVentasxC reporteC = new ReporteVentasxC();
				Cliente cliente = new Cliente();
			    cliente = this.controladorCliente.buscarCedula(c);
				
				
				double totalVentasC = this.totalVentasxC(c, listaV);
				String nombreC = cliente.getNombreCliente();
				
				reporteC.setCedulaCliente(c);
				reporteC.setNombreCliente(nombreC);
				reporteC.setTotalVentas(totalVentasC);
				
				listaReporte.add(reporteC);
			}
			return listaReporte;
		}
		return null;
	} 	
}
