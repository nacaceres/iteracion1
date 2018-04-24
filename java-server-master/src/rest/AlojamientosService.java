package rest;

import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tm.AlohAndesMaster;
import vos.Alojamiento;
import vos.AlojamientosTop;
import vos.Apartamento;
import vos.Cliente;
import vos.Condiciones;
import vos.Condiciones2;
import vos.HabHostal;
import vos.HabHotel;
import vos.HabUniversitaria;
import vos.Informe;
import vos.Operador;
import vos.Vivienda;



/**
 * Clase que expone servicios REST con ruta base: http://localhost:8080/AlohAndes/rest/operadores/...
 */
@Path("alojamientos")
public class AlojamientosService {

	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * Atributo que usa la anotacion @Context para tener el ServletContext de la conexion actual.
	 */
	@Context
	private ServletContext context;

	//----------------------------------------------------------------------------------------------------------------------------------
	// METODOS DE INICIALIZACION
	//----------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Metodo que retorna el path de la carpeta WEB-INF/ConnectionData en el deploy actual dentro del servidor.
	 * @return path de la carpeta WEB-INF/ConnectionData en el deploy actual.
	 */
	private String getPath() {
		return context.getRealPath("WEB-INF/ConnectionData");
	}


	private String doErrorMessage(Exception e){
		return "{ \"ERROR\": \""+ e.getMessage() + "\"}" ;
	}

	//----------------------------------------------------------------------------------------------------------------------------------
	// METODOS REST
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Metodo GET que trae a todos los Alojamientos en la Base de datos. <br/>
	 * <b>Precondicion: </b> el archivo <em>'conectionData'</em> ha sido inicializado con las credenciales del usuario <br/>
	 * <b>URL: </b> http://localhost:8080/AlohAndes/rest/Alojamientos <br/>
	 * @return	<b>Response Status 200</b> - JSON que contiene a todos los Alojamientos que estan en la Base de Datos <br/>
	 * 			<b>Response Status 500</b> - Excepcion durante el transcurso de la transaccion
	 */			
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getAlojamientos() {
		
		try {
			AlohAndesMaster tm = new AlohAndesMaster(getPath());
			
			List<Alojamiento> Alojamientos;
			Alojamientos = tm.getAllAlojamientos();
			return Response.status(200).entity(Alojamientos).build();
		} 
		catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
	}

	/**
	 * Metodo GET que trae al Alojamiento en la Base de Datos con el ID dado por parametro <br/>
	 * <b>Precondicion: </b> el archivo <em>'conectionData'</em> ha sido inicializado con las credenciales del usuario <br/>
	 * <b>URL: </b> http://localhost:8080/AlohAndesMaster/rest/Alojamientos/{id} <br/>
	 * @return	<b>Response Status 200</b> - JSON Alojamiento que contiene al Alojamiento cuyo ID corresponda al parametro <br/>
	 * 			<b>Response Status 500</b> - Excepcion durante el transcurso de la transaccion
	 */
	@GET
	@Path( "{id: \\d+}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getAlojamientoById( @PathParam( "id" ) Long id )
	{
		try{
			AlohAndesMaster tm = new AlohAndesMaster( getPath( ) );
			
			Alojamiento Alojamiento = tm.getAlojamientoById( id );
			return Response.status( 200 ).entity( Alojamiento ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}

	/**
	 * Metodo que recibe un Alojamiento en formato JSON y lo agrega a la Base de Datos <br/>
	 * <b>Precondicion: </b> El archivo <em>'conectionData'</em> ha sido inicializado con las credenciales del usuario <br/>
	 * <b>Postcondicion: </b> Se agrega a la Base de datos la informacion correspondiente al Alojamiento. <br/>
	 * <b>URL: </b> http://localhost:8080/TutorialParranderos/rest/Alojamientos <br/>
	 * @param Alojamiento JSON con la informacion del Alojamiento que se desea agregar
	 * @return	<b>Response Status 200</b> - JSON que contiene al Alojamiento que ha sido agregado <br/>
	 * 			<b>Response Status 500</b> - Excepcion durante el transcurso de la transaccion
	 */
	@POST
	@Produces( { MediaType.APPLICATION_JSON } )
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response addAlojamiento(Alojamiento Alojamiento) {
		
		try{
			AlohAndesMaster tm = new AlohAndesMaster( getPath( ) );
			tm.addAlojamiento(Alojamiento);
			return Response.status( 200 ).entity( Alojamiento ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	/**
	 * Metodo que recibe un Apartamento en formato JSON y lo agrega a la Base de Datos <br/>
	 * <b>Precondicion: </b> El archivo <em>'conectionData'</em> ha sido inicializado con las credenciales del usuario <br/>
	 * <b>Postcondicion: </b> Se agrega a la Base de datos la informacion correspondiente al Apartamento. <br/>
	 * <b>URL: </b> http://localhost:8080/TutorialParranderos/rest/Apartamentos <br/>
	 * @param Apartamento JSON con la informacion del Apartamento que se desea agregar
	 * @return	<b>Response Status 200</b> - JSON que contiene al Apartamento que ha sido agregado <br/>
	 * 			<b>Response Status 500</b> - Excepcion durante el transcurso de la transaccion
	 */
	@POST
	@Produces( { MediaType.APPLICATION_JSON } )
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("apartamentos")
	public Response addApartamento(Apartamento apartamento) {
		try{
			AlohAndesMaster tm = new AlohAndesMaster( getPath( ) );
			tm.addApartamento(apartamento);
			return Response.status( 200 ).entity( apartamento ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	/**
	 * Metodo que recibe un HabHostal en formato JSON y lo agrega a la Base de Datos <br/>
	 * <b>Precondicion: </b> El archivo <em>'conectionData'</em> ha sido inicializado con las credenciales del usuario <br/>
	 * <b>Postcondicion: </b> Se agrega a la Base de datos la informacion correspondiente al HabHostal. <br/>
	 * <b>URL: </b> http://localhost:8080/TutorialParranderos/rest/HabHostals <br/>
	 * @param HabHostal JSON con la informacion del HabHostal que se desea agregar
	 * @return	<b>Response Status 200</b> - JSON que contiene al HabHostal que ha sido agregado <br/>
	 * 			<b>Response Status 500</b> - Excepcion durante el transcurso de la transaccion
	 */
	@POST
	@Produces( { MediaType.APPLICATION_JSON } )
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("habitacioneshostales")
	public Response addHabHostal(HabHostal HabHostal) {
		try{
			AlohAndesMaster tm = new AlohAndesMaster( getPath( ) );
			tm.addHabHostal(HabHostal);
			return Response.status( 200 ).entity( HabHostal ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	/**
	 * Metodo que recibe un HabHotel en formato JSON y lo agrega a la Base de Datos <br/>
	 * <b>Precondicion: </b> El archivo <em>'conectionData'</em> ha sido inicializado con las credenciales del usuario <br/>
	 * <b>Postcondicion: </b> Se agrega a la Base de datos la informacion correspondiente al HabHotel. <br/>
	 * <b>URL: </b> http://localhost:8080/TutorialParranderos/rest/HabHotels <br/>
	 * @param HabHotel JSON con la informacion del HabHotel que se desea agregar
	 * @return	<b>Response Status 200</b> - JSON que contiene al HabHotel que ha sido agregado <br/>
	 * 			<b>Response Status 500</b> - Excepcion durante el transcurso de la transaccion
	 */
	@POST
	@Produces( { MediaType.APPLICATION_JSON } )
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("habitacioneshoteles")
	public Response addHabHotel(HabHotel HabHotel) {
		try{
			AlohAndesMaster tm = new AlohAndesMaster( getPath( ) );
			tm.addHabHotel(HabHotel);
			return Response.status( 200 ).entity( HabHotel ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	/**
	 * Metodo que recibe un HabUniversitaria en formato JSON y lo agrega a la Base de Datos <br/>
	 * <b>Precondicion: </b> El archivo <em>'conectionData'</em> ha sido inicializado con las credenciales del usuario <br/>
	 * <b>Postcondicion: </b> Se agrega a la Base de datos la informacion correspondiente al HabUniversitaria. <br/>
	 * <b>URL: </b> http://localhost:8080/TutorialParranderos/rest/HabUniversitarias <br/>
	 * @param HabUniversitaria JSON con la informacion del HabUniversitaria que se desea agregar
	 * @return	<b>Response Status 200</b> - JSON que contiene al HabUniversitaria que ha sido agregado <br/>
	 * 			<b>Response Status 500</b> - Excepcion durante el transcurso de la transaccion
	 */
	@POST
	@Produces( { MediaType.APPLICATION_JSON } )
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("habitacionesuniversitarias")
	public Response addHabUniversitaria(HabUniversitaria HabUniversitaria) {
		try{
			AlohAndesMaster tm = new AlohAndesMaster( getPath( ) );
			tm.addHabUniversitaria(HabUniversitaria);
			return Response.status( 200 ).entity( HabUniversitaria ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	/**
	 * Metodo que recibe un Vivienda en formato JSON y lo agrega a la Base de Datos <br/>
	 * <b>Precondicion: </b> El archivo <em>'conectionData'</em> ha sido inicializado con las credenciales del usuario <br/>
	 * <b>Postcondicion: </b> Se agrega a la Base de datos la informacion correspondiente al Vivienda. <br/>
	 * <b>URL: </b> http://localhost:8080/TutorialParranderos/rest/Viviendas <br/>
	 * @param Vivienda JSON con la informacion del Vivienda que se desea agregar
	 * @return	<b>Response Status 200</b> - JSON que contiene al Vivienda que ha sido agregado <br/>
	 * 			<b>Response Status 500</b> - Excepcion durante el transcurso de la transaccion
	 */
	@POST
	@Produces( { MediaType.APPLICATION_JSON } )
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("viviendas")
	public Response addVivienda(Vivienda Vivienda) {
		try{
			AlohAndesMaster tm = new AlohAndesMaster( getPath( ) );
			tm.addVivienda(Vivienda);
			return Response.status( 200 ).entity( Vivienda ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	/**
	 * Metodo que recibe un Alojamiento en formato JSON y lo agrega a la Base de Datos <br/>
	 * <b>Precondicion: </b> El archivo <em>'conectionData'</em> ha sido inicializado con las credenciales del usuario <br/>
	 * <b>Postcondicion: </b> Se actualiza la Base de datos con la informacion correspondiente al Alojamiento.<br/>
	 * @param Alojamiento JSON con la informacion del Alojamiento que se desea agregar
	 * @return	<b>Response Status 200</b> - JSON que contiene al Alojamiento que se desea modificar <br/>
	 * 			<b>Response Status 500</b> - Excepcion durante el transcurso de la transaccion
	 */
	@PUT	
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response retirarOfertaAlojamiento(Alojamiento Alojamiento) {
		try{
			AlohAndesMaster tm = new AlohAndesMaster( getPath( ) );
			
			tm.retirarOfertaAlojamiento(Alojamiento);
			return getAlojamientoById(Alojamiento.getId());			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	/**
	 * Metodo que recibe un Alojamiento en formato JSON y lo agrega a la Base de Datos <br/>
	 * <b>Precondicion: </b> El archivo <em>'conectionData'</em> ha sido inicializado con las credenciales del usuario <br/>
	 * <b>Postcondicion: </b> Se actualiza la Base de datos con la informacion correspondiente al Alojamiento.<br/>
	 * @param Alojamiento JSON con la informacion del Alojamiento que se desea agregar
	 * @return	<b>Response Status 200</b> - JSON que contiene al Alojamiento que se desea modificar <br/>
	 * 			<b>Response Status 500</b> - Excepcion durante el transcurso de la transaccion
	 */
	@PUT	
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("deshabilitar")
	public Response deshabilitarOfertaAlojamiento(Alojamiento Alojamiento) {
		try{
			AlohAndesMaster tm = new AlohAndesMaster( getPath( ) );
			
			Informe x = tm.deshabilitarOfertaAlojamiento(Alojamiento);
			return Response.status( 200 ).entity( x ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	/**
	 * Metodo que recibe un Alojamiento en formato JSON y lo agrega a la Base de Datos <br/>
	 * <b>Precondicion: </b> El archivo <em>'conectionData'</em> ha sido inicializado con las credenciales del usuario <br/>
	 * <b>Postcondicion: </b> Se actualiza la Base de datos con la informacion correspondiente al Alojamiento.<br/>
	 * @param Alojamiento JSON con la informacion del Alojamiento que se desea agregar
	 * @return	<b>Response Status 200</b> - JSON que contiene al Alojamiento que se desea modificar <br/>
	 * 			<b>Response Status 500</b> - Excepcion durante el transcurso de la transaccion
	 */
	@PUT	
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("habilitar")
	public Response habilitarOfertaAlojamiento(Alojamiento Alojamiento) {
		try{
			AlohAndesMaster tm = new AlohAndesMaster( getPath( ) );
			
			Informe x = tm.habilitarOfertaAlojamiento(Alojamiento);
			return Response.status( 200 ).entity( x ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	/**
	 * Metodo GET que trae a todos los Alojamientos top en la Base de datos. <br/>
	 * <b>Precondicion: </b> el archivo <em>'conectionData'</em> ha sido inicializado con las credenciales del usuario <br/>
	 * <b>URL: </b> http://localhost:8080/AlohAndes/rest/Alojamientos <br/>
	 * @return	<b>Response Status 200</b> - JSON que contiene a todos los Alojamientos que estan en la Base de Datos <br/>
	 * 			<b>Response Status 500</b> - Excepcion durante el transcurso de la transaccion
	 */			
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("maspopulares")
	public Response getAlojamientosPopulares() {
		
		try {
			AlohAndesMaster tm = new AlohAndesMaster(getPath());
			
			List<AlojamientosTop> Alojamientos;
			Alojamientos = tm.getAlojamientosMasPopulares();
			return Response.status(200).entity(Alojamientos).build();
		} 
		catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
	}
	/**
	 * Metodo GET que trae a todos los Alojamientos top en la Base de datos. <br/>
	 * <b>Precondicion: </b> el archivo <em>'conectionData'</em> ha sido inicializado con las credenciales del usuario <br/>
	 * <b>URL: </b> http://localhost:8080/AlohAndes/rest/Alojamientos <br/>
	 * @return	<b>Response Status 200</b> - JSON que contiene a todos los Alojamientos que estan en la Base de Datos <br/>
	 * 			<b>Response Status 500</b> - Excepcion durante el transcurso de la transaccion
	 */			
	@PUT
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("conrestricciones")
	public Response getAlojamientosConRestricciones(Condiciones pCondiciones) {
		
		try {
			AlohAndesMaster tm = new AlohAndesMaster(getPath());
			
			List<Alojamiento> Alojamientos;
			Alojamientos = tm.getAlojamientosConRestriccion(pCondiciones);
			return Response.status(200).entity(Alojamientos).build();
		} 
		catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
	}
	
	
	/**
	 * Metodo GET que trae un informe que detalla los dias de pico(ocuapcion y recaudacion). <br/>
	 * <b>Precondicion: </b> el archivo <em>'conectionData'</em> ha sido inicializado con las credenciales del usuario <br/>
	 * <b>URL: </b> http://localhost:8080/AlohAndes/rest/Alojamientos <br/>
	 * @return	<b>Response Status 200</b> - JSON que contiene a todos los Alojamientos que estan en la Base de Datos <br/>
	 * 			<b>Response Status 500</b> - Excepcion durante el transcurso de la transaccion
	 */			
	@PUT
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("diaspico")
	public Response getDiasPico(Condiciones2 pCondiciones) {
		
		try {
			AlohAndesMaster tm = new AlohAndesMaster(getPath());
			
		  Informe x=tm.getDiasPico(pCondiciones);
	   return Response.status(200).entity(x).build();
		} 
		catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
	}
	
	/**
	 * Metodo GET que trae un informe que detalla los alojamientos que no han tenido reservas a lo largo de un mes). <br/>
	 * <b>Precondicion: </b> el archivo <em>'conectionData'</em> ha sido inicializado con las credenciales del usuario <br/>
	 * <b>URL: </b> http://localhost:8080/AlohAndes/rest/Alojamientos <br/>
	 * @return	<b>Response Status 200</b> - JSON que contiene a todos los Alojamientos que estan en la Base de Datos <br/>
	 * 			<b>Response Status 500</b> - Excepcion durante el transcurso de la transaccion
	 */			
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("desiertos")
	public Response getAlojamientosDesiertos() {
		
		try {
			AlohAndesMaster tm = new AlohAndesMaster(getPath());
			
		  Informe x=tm.getAlojamientosDesiertos();
	      return Response.status(200).entity(x).build();
		} 
		catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
	}
	
	
	
}