package tm;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import dao.*;
import vos.*;

/**
 * 
 * Clase que representa al Manejador de Transacciones de la Aplicacion (Fachada en patron singleton de la aplicacion)
 * Responsabilidades de la clase: 
 * 		Intermediario entre los servicios REST de la aplicacion y la comunicacion con la Base de Datos
 * 		Modelar y manejar autonomamente las transacciones y las reglas de negocio.
 */
public class AlohAndesMaster{

	//----------------------------------------------------------------------------------------------------------------------------------
	// CONSTANTES
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Constante que contiene el path relativo del archivo que tiene los datos de la conexion
	 */
	private static final String CONNECTION_DATA_FILE_NAME_REMOTE = "/conexion.properties";

	/**
	 * Atributo estatico que contiene el path absoluto del archivo que tiene los datos de la conexion
	 */
	private static String CONNECTION_DATA_PATH;


	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Atributo que guarda el usuario que se va a usar para conectarse a la base de datos.
	 */
	private String user;

	/**
	 * Atributo que guarda la clave que se va a usar para conectarse a la base de datos.
	 */
	private String password;

	/**
	 * Atributo que guarda el URL que se va a usar para conectarse a la base de datos.
	 */
	private String url;

	/**
	 * Atributo que guarda el driver que se va a usar para conectarse a la base de datos.
	 */
	private String driver;

	/**
	 * Atributo que representa la conexion a la base de datos
	 */
	private Connection conn;

	//----------------------------------------------------------------------------------------------------------------------------------
	// METODOS DE CONEXION E INICIALIZACION
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * <b>Metodo Contructor de la Clase ParranderosTransactionManager</b> <br/>
	 * <b>Postcondicion: </b>	Se crea un objeto  ParranderosTransactionManager,
	 * 						 	Se inicializa el path absoluto del archivo de conexion,
	 * 							Se inicializna los atributos para la conexion con la Base de Datos
	 * @param contextPathP Path absoluto que se encuentra en el servidor del contexto del deploy actual
	 * @throws IOException Se genera una excepcion al tener dificultades con la inicializacion de la conexion<br/>
	 * @throws ClassNotFoundException 
	 */
	public AlohAndesMaster(String contextPathP) {

		try {
			CONNECTION_DATA_PATH = contextPathP + CONNECTION_DATA_FILE_NAME_REMOTE;
			initializeConnectionData();
		} 
		catch (ClassNotFoundException e) {			
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo encargado de inicializar los atributos utilizados para la conexion con la Base de Datos.<br/>
	 * <b>post: </b> Se inicializan los atributos para la conexion<br/>
	 * @throws IOException Se genera una excepcion al no encontrar el archivo o al tener dificultades durante su lectura<br/>
	 * @throws ClassNotFoundException 
	 */
	private void initializeConnectionData() throws IOException, ClassNotFoundException {

		FileInputStream fileInputStream = new FileInputStream(new File(AlohAndesMaster.CONNECTION_DATA_PATH));
		Properties properties = new Properties();

		properties.load(fileInputStream);
		fileInputStream.close();

		this.url = properties.getProperty("url");
		this.user = properties.getProperty("usuario");
		this.password = properties.getProperty("clave");
		this.driver = properties.getProperty("driver");

		Class.forName(driver);
	}

	/**
	 * Metodo encargado de generar una conexion con la Base de Datos.<br/>
	 * <b>Precondicion: </b>Los atributos para la conexion con la Base de Datos han sido inicializados<br/>
	 * @return Objeto Connection, el cual hace referencia a la conexion a la base de datos
	 * @throws SQLException Cualquier error que se pueda llegar a generar durante la conexion a la base de datos
	 */
	private Connection darConexion() throws SQLException {
		System.out.println("[ALOHANDES APP] Attempting Connection to: " + url + " - By User: " + user);
		return DriverManager.getConnection(url, user, password);
	}


	//----------------------------------------------------------------------------------------------------------------------------------
	// METODOS TRANSACCIONALES
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Metodo que modela la transaccion que retorna todos los operadores de la base de datos. <br/>
	 * @return List<operador> - Lista de operadores que contiene el resultado de la consulta.
	 * @throws Exception -  Cualquier error que se genere durante la transaccion
	 */
	public List<Operador> getAllOperadores( ) throws Exception {
		DAOAlojamiento daoAlojamiento = new DAOAlojamiento();
		DAOOperador daooperador = new DAOOperador();
		List<Operador> operadores;
		try 
		{
			this.conn = darConexion();
			daooperador.setConn(conn);
			daoAlojamiento.setConn(conn);
			operadores = daooperador.getOperadores(daoAlojamiento);
		}
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daooperador.cerrarRecursos();
				daoAlojamiento.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return operadores;
	}

	/**
	 * Metodo que modela la transaccion que busca el Operador en la base de datos que tiene el ID dado por parametro. <br/>
	 * @param name -id del Operador a buscar. id != null
	 * @return Operador - Operador que se obtiene como resultado de la consulta.
	 * @throws Exception -  cualquier error que se genere durante la transaccion
	 */
	public Operador getOperadorById(Long id) throws Exception {
		DAOAlojamiento daoAlojamiento = new DAOAlojamiento();
		DAOOperador daoOperador = new DAOOperador();
		Operador Operador = null;
		try 
		{
			this.conn = darConexion();
			daoOperador.setConn(conn);
			daoAlojamiento.setConn(conn);
			Operador = daoOperador.findOperadorById(id, daoAlojamiento);
			if(Operador == null)
			{
				throw new Exception("El Operador con el id = " + id + " no se encuentra persistido en la base de datos.");				
			}
		} 
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daoOperador.cerrarRecursos();
				daoAlojamiento.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return Operador;
	}


	/**
	 * Metodo que modela la transaccion que agrega un Operador a la base de datos. <br/>
	 * <b> post: </b> se ha agregado el Operador que entra como parametro <br/>
	 * @param Operador - el Operador a agregar. Operador != null
	 * @throws Exception - Cualquier error que se genere agregando el Operador
	 */
	public void addOperador(Operador Operador) throws Exception 
	{
		DAOAlojamiento daoAlojamiento = new DAOAlojamiento();
		DAOOperador daoOperador = new DAOOperador( );
		try
		{
			this.conn = darConexion();
			daoOperador.setConn(conn);
			daoAlojamiento.setConn(conn);
			daoOperador.addOperador(Operador,daoAlojamiento);

		}
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daoOperador.cerrarRecursos();
				daoAlojamiento.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 * Metodo que modela la transaccion que actualiza en la base de datos al Operador que entra por parametro.<br/>
	 * Solamente se actualiza si existe el Operador en la Base de Datos <br/>
	 * <b> post: </b> se ha actualizado el Operador que entra como parametro <br/>
	 * @param Operador - Operador a actualizar. Operador != null
	 * @throws Exception - Cualquier error que se genere actualizando al Operador.
	 */
	public void updateOperador(Operador Operador) throws Exception 
	{
		DAOOperador daoOperador = new DAOOperador( );
		DAOAlojamiento daoAlojamiento = new DAOAlojamiento();
		try
		{
			this.conn = darConexion();
			daoOperador.setConn( conn );
			daoAlojamiento.setConn(conn);
			Operador actual = daoOperador.findOperadorById(Operador.getId(),daoAlojamiento);
			if (actual!= null)
			{
				daoOperador.updateOperador(Operador);
			}
			else
			{
				throw new Exception ("[EXCEPTION] General Exception: No se puede actualizar el Operador debido a que no existe un Operador en la base de datos con ese id.");
			}

		}
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daoOperador.cerrarRecursos();
				daoAlojamiento.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}	
	}
	/**
	 * Metodo que modela la transaccion que elimina de la base de datos al Operador que entra por parametro. <br/>
	 * Solamente se actualiza si existe el Operador en la Base de Datos <br/>
	 * <b> post: </b> se ha eliminado el Operador que entra por parametro <br/>
	 * @param Operador - Operador a eliminar. Operador != null
	 * @throws Exception - Cualquier error que se genere eliminando al Operador.
	 */
	public void deleteOperador(Operador Operador) throws Exception 
	{
		DAOOperador daoOperador = new DAOOperador( );
		DAOAlojamiento daoAlojamiento = new DAOAlojamiento();
		try
		{
			this.conn = darConexion();
			daoOperador.setConn( conn );
			daoAlojamiento.setConn(conn);
			Operador actual = daoOperador.findOperadorById(Operador.getId(),daoAlojamiento);
			if (actual!= null)
			{
				daoOperador.deleteOperador(Operador,daoAlojamiento);
			}
			else
			{
				throw new Exception ("[EXCEPTION] General Exception: No se puede eliminar el Operador debido a que no existe un Operador en la base de datos con ese id.");
			}

		}
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daoAlojamiento.cerrarRecursos();
				daoOperador.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}	
	}

	/**
	 * Metodo que modela la transaccion que retorna todos los Clientes de la base de datos. <br/>
	 * @return List<Cliente> - Lista de Clientes que contiene el resultado de la consulta.
	 * @throws Exception -  Cualquier error que se genere durante la transaccion
	 */
	public List<Cliente> getAllClientes() throws Exception {
		DAOCliente daoCliente = new DAOCliente();
		List<Cliente> Clientes;
		try 
		{
			this.conn = darConexion();
			daoCliente.setConn(conn);

			Clientes = daoCliente.getClientes();
		}
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daoCliente.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return Clientes;
	}

	/**
	 * Metodo que modela la transaccion que busca el Cliente en la base de datos que tiene el ID dado por parametro. <br/>
	 * @param name -id del Cliente a buscar. id != null
	 * @return Cliente - Cliente que se obtiene como resultado de la consulta.
	 * @throws Exception -  cualquier error que se genere durante la transaccion
	 */
	public Cliente getClienteById(Long id) throws Exception {
		DAOCliente daoCliente = new DAOCliente();
		Cliente Cliente = null;
		try 
		{
			this.conn = darConexion();
			daoCliente.setConn(conn);
			Cliente = daoCliente.findClienteById(id);
			if(Cliente == null)
			{
				throw new Exception("El Cliente con el id = " + id + " no se encuentra persistido en la base de datos.");				
			}
		} 
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daoCliente.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return Cliente;
	}


	/**
	 * Metodo que modela la transaccion que agrega un Cliente a la base de datos. <br/>
	 * <b> post: </b> se ha agregado el Cliente que entra como parametro <br/>
	 * @param Cliente - el Cliente a agregar. Cliente != null
	 * @throws Exception - Cualquier error que se genere agregando el Cliente
	 */
	public void addCliente(Cliente Cliente) throws Exception 
	{

		DAOCliente daoCliente = new DAOCliente( );
		try
		{
			this.conn = darConexion();
			daoCliente.setConn(conn);
			daoCliente.addCliente(Cliente);

		}
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daoCliente.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 * Metodo que modela la transaccion que actualiza en la base de datos al Cliente que entra por parametro.<br/>
	 * Solamente se actualiza si existe el Cliente en la Base de Datos <br/>
	 * <b> post: </b> se ha actualizado el Cliente que entra como parametro <br/>
	 * @param Cliente - Cliente a actualizar. Cliente != null
	 * @throws Exception - Cualquier error que se genere actualizando al Cliente.
	 */
	public void updateCliente(Cliente Cliente) throws Exception 
	{
		DAOCliente daoCliente = new DAOCliente( );
		try
		{
			this.conn = darConexion();
			daoCliente.setConn( conn );
			Cliente actual = daoCliente.findClienteById(Cliente.getId());
			if (actual!= null)
			{
				daoCliente.updateCliente(Cliente);
			}
			else
			{
				throw new Exception ("[EXCEPTION] General Exception: No se puede actualizar el Cliente debido a que no existe un Cliente en la base de datos con ese id.");
			}

		}
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daoCliente.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}	
	}
	/**
	 * Metodo que modela la transaccion que elimina de la base de datos al Cliente que entra por parametro. <br/>
	 * Solamente se actualiza si existe el Cliente en la Base de Datos <br/>
	 * <b> post: </b> se ha eliminado el Cliente que entra por parametro <br/>
	 * @param Cliente - Cliente a eliminar. Cliente != null
	 * @throws Exception - Cualquier error que se genere eliminando al Cliente.
	 */
	public void deleteCliente(Cliente Cliente) throws Exception 
	{
		DAOCliente daoCliente = new DAOCliente( );
		try
		{
			this.conn = darConexion();
			daoCliente.setConn( conn );
			Cliente actual = daoCliente.findClienteById(Cliente.getId());
			if (actual!= null)
			{
				daoCliente.deleteCliente(Cliente);
			}
			else
			{
				throw new Exception ("[EXCEPTION] General Exception: No se puede eliminar el Cliente debido a que no existe un Cliente en la base de datos con ese id.");
			}

		}
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daoCliente.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}	
	}
	/**
	 * Metodo que modela la transaccion que retorna todos los Alojamientos de la base de datos. <br/>
	 * @return List<Alojamiento> - Lista de Alojamientos que contiene el resultado de la consulta.
	 * @throws Exception -  Cualquier error que se genere durante la transaccion
	 */
	public List<Alojamiento> getAllAlojamientos() throws Exception {
		DAOAlojamiento daoAlojamiento = new DAOAlojamiento();
		List<Alojamiento> Alojamientos;
		try 
		{
			this.conn = darConexion();
			daoAlojamiento.setConn(conn);

			Alojamientos = daoAlojamiento.getAlojamientos();
		}
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daoAlojamiento.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return Alojamientos;
	}

	/**
	 * Metodo que modela la transaccion que busca el Alojamiento en la base de datos que tiene el ID dado por parametro. <br/>
	 * @param name -id del Alojamiento a buscar. id != null
	 * @return Alojamiento - Alojamiento que se obtiene como resultado de la consulta.
	 * @throws Exception -  cualquier error que se genere durante la transaccion
	 */
	public Alojamiento getAlojamientoById(Long id) throws Exception {
		DAOAlojamiento daoAlojamiento = new DAOAlojamiento();
		Alojamiento Alojamiento = null;
		try 
		{
			this.conn = darConexion();
			daoAlojamiento.setConn(conn);
			Alojamiento = daoAlojamiento.findAlojamientoById(id);
			if(Alojamiento == null)
			{
				throw new Exception("El Alojamiento con el id = " + id + " no se encuentra persistido en la base de datos.");				
			}
		} 
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daoAlojamiento.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return Alojamiento;
	}


	/**
	 * Metodo que modela la transaccion que agrega un Alojamiento a la base de datos. <br/>
	 * <b> post: </b> se ha agregado el Alojamiento que entra como parametro <br/>
	 * @param Alojamiento - el Alojamiento a agregar. Alojamiento != null
	 * @throws Exception - Cualquier error que se genere agregando el Alojamiento
	 */
	public void addAlojamiento(Alojamiento Alojamiento) throws Exception 
	{

		DAOAlojamiento daoAlojamiento = new DAOAlojamiento( );
		try
		{
			this.conn = darConexion();
			daoAlojamiento.setConn(conn);
			daoAlojamiento.addAlojamiento(Alojamiento);

		}
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daoAlojamiento.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 * Metodo que modela la transaccion que agrega un Apartamento a la base de datos. <br/>
	 * <b> post: </b> se ha agregado el Apartamento que entra como parametro <br/>
	 * @param Apartamento - el Apartamento a agregar. Apartamento != null
	 * @throws Exception - Cualquier error que se genere agregando el Apartamento
	 */
	public void addApartamento(Apartamento Apartamento) throws Exception 
	{

		DAOAlojamiento daoApartamento = new DAOAlojamiento( );
		try
		{
			this.conn = darConexion();
			daoApartamento.setConn(conn);
			daoApartamento.addApartamento(Apartamento);

		}
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daoApartamento.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 * Metodo que modela la transaccion que agrega un HabHostal a la base de datos. <br/>
	 * <b> post: </b> se ha agregado el HabHostal que entra como parametro <br/>
	 * @param HabHostal - el HabHostal a agregar. HabHostal != null
	 * @throws Exception - Cualquier error que se genere agregando el HabHostal
	 */
	public void addHabHostal(HabHostal HabHostal) throws Exception 
	{

		DAOAlojamiento daoHabHostal = new DAOAlojamiento( );
		try
		{
			this.conn = darConexion();
			daoHabHostal.setConn(conn);
			daoHabHostal.addHabHostal(HabHostal);

		}
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daoHabHostal.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	/**
	 * Metodo que modela la transaccion que agrega un HabHotel a la base de datos. <br/>
	 * <b> post: </b> se ha agregado el HabHotel que entra como parametro <br/>
	 * @param HabHotel - el HabHotel a agregar. HabHotel != null
	 * @throws Exception - Cualquier error que se genere agregando el HabHotel
	 */
	public void addHabHotel(HabHotel HabHotel) throws Exception 
	{

		DAOAlojamiento daoHabHotel = new DAOAlojamiento( );
		try
		{
			this.conn = darConexion();
			daoHabHotel.setConn(conn);
			daoHabHotel.addHabHotel(HabHotel);

		}
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daoHabHotel.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	/**
	 * Metodo que modela la transaccion que agrega un HabUniversitaria a la base de datos. <br/>
	 * <b> post: </b> se ha agregado el HabUniversitaria que entra como parametro <br/>
	 * @param HabUniversitaria - el HabUniversitaria a agregar. HabUniversitaria != null
	 * @throws Exception - Cualquier error que se genere agregando el HabUniversitaria
	 */
	public void addHabUniversitaria(HabUniversitaria HabUniversitaria) throws Exception 
	{

		DAOAlojamiento daoHabUniversitaria = new DAOAlojamiento( );
		try
		{
			this.conn = darConexion();
			daoHabUniversitaria.setConn(conn);
			daoHabUniversitaria.addHabUniversitaria(HabUniversitaria);

		}
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daoHabUniversitaria.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	/**
	 * Metodo que modela la transaccion que agrega un Vivienda a la base de datos. <br/>
	 * <b> post: </b> se ha agregado el Vivienda que entra como parametro <br/>
	 * @param Vivienda - el Vivienda a agregar. Vivienda != null
	 * @throws Exception - Cualquier error que se genere agregando el Vivienda
	 */
	public void addVivienda(Vivienda Vivienda) throws Exception 
	{

		DAOAlojamiento daoVivienda = new DAOAlojamiento( );
		try
		{
			this.conn = darConexion();
			daoVivienda.setConn(conn);
			daoVivienda.addVivienda(Vivienda);

		}
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daoVivienda.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	/**
	 * Metodo que modela la transaccion que retorna todos los Reservas de la base de datos. <br/>
	 * @return List<Reserva> - Lista de Reservas que contiene el resultado de la consulta.
	 * @throws Exception -  Cualquier error que se genere durante la transaccion
	 */
	public List<Reserva> getAllReservas( ) throws Exception {
		DAOAlojamiento daoAlojamiento = new DAOAlojamiento();
		DAOReserva daoReserva = new DAOReserva();
		DAOCliente daoCliente = new DAOCliente();
		List<Reserva> Reservas;
		try 
		{
			this.conn = darConexion();
			daoReserva.setConn(conn);
			daoAlojamiento.setConn(conn);
			daoCliente.setConn(conn);
			Reservas = daoReserva.getReservas(daoAlojamiento,daoCliente);
		}
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daoReserva.cerrarRecursos();
				daoAlojamiento.cerrarRecursos();
				daoCliente.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return Reservas;
	}

	/**
	 * Metodo que modela la transaccion que busca el Reserva en la base de datos que tiene el ID dado por parametro. <br/>
	 * @param name -id del Reserva a buscar. id != null
	 * @return Reserva - Reserva que se obtiene como resultado de la consulta.
	 * @throws Exception -  cualquier error que se genere durante la transaccion
	 */
	public Reserva getReservaById(Long id) throws Exception {
		DAOAlojamiento daoAlojamiento = new DAOAlojamiento();
		DAOCliente daoCliente = new DAOCliente();
		DAOReserva daoReserva = new DAOReserva();
		Reserva Reserva = null;
		try 
		{
			this.conn = darConexion();
			daoReserva.setConn(conn);
			daoAlojamiento.setConn(conn);
			daoCliente.setConn(conn);
			Reserva = daoReserva.findReservaById(id, daoAlojamiento,daoCliente);
			if(Reserva == null)
			{
				throw new Exception("El Reserva con el id = " + id + " no se encuentra persistido en la base de datos.");				
			}
		} 
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daoReserva.cerrarRecursos();
				daoAlojamiento.cerrarRecursos();
				daoCliente.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return Reserva;
	}


	/**
	 * Metodo que modela la transaccion que agrega un Reserva a la base de datos. <br/>
	 * <b> post: </b> se ha agregado el Reserva que entra como parametro <br/>
	 * @param Reserva - el Reserva a agregar. Reserva != null
	 * @throws Exception - Cualquier error que se genere agregando el Reserva
	 */
	public void addReserva(Reserva Reserva) throws Exception 
	{
		DAOReserva daoReserva = new DAOReserva( );
		try
		{
			this.conn = darConexion();
			daoReserva.setConn(conn);
			daoReserva.addReserva(Reserva);

		}
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daoReserva.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	/**
	 * Metodo que modela la transaccion que agrega un Reserva colectiva la base de datos. <br/>
	 * <b> post: </b> se ha agregado el Reserva colectiva que entra como parametro <br/>
	 * @param Reserva - el Reserva a agregar. Reserva != null
	 * @throws Exception - Cualquier error que se genere agregando el Reserva
	 */
	public Informe addReservaColectiva(ReservaColectiva reservaColectiva) throws Exception 
	{
		ArrayList <String> array = new ArrayList<>();
		Informe inf = new Informe(array);
		DAOReserva daoReserva = new DAOReserva( );
		DAOAlojamiento daoAlojamiento = new DAOAlojamiento();
		try
		{
			this.conn = darConexion();
			daoReserva.setConn(conn);
			daoAlojamiento.setConn(conn);
			inf = daoReserva.addReservaColectiva(reservaColectiva,daoAlojamiento);

		}
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daoReserva.cerrarRecursos();
				daoAlojamiento.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return inf;
	}
	/**
	 * Metodo que modela la transaccion que actualiza en la base de datos al Alojamiento que entra por parametro.<br/>
	 * Solamente se actualiza si existe el Alojamiento en la Base de Datos <br/>
	 * <b> post: </b> se ha actualizado el Alojamiento que entra como parametro <br/>
	 * @param Alojamiento - Alojamiento a actualizar. Alojamiento != null
	 * @throws Exception - Cualquier error que se genere actualizando al Alojamiento.
	 */
	public void retirarOfertaAlojamiento(Alojamiento alojamiento) throws Exception 
	{

		DAOAlojamiento daoAlojamiento = new DAOAlojamiento( );
		try
		{
			this.conn = darConexion();
			daoAlojamiento.setConn( conn );
			Alojamiento actual = daoAlojamiento.findAlojamientoById(alojamiento.getId());
			if (actual!= null)
			{
				daoAlojamiento.retirarOfertaAlojamiento( alojamiento);
			}
			else
			{
				throw new Exception ("[EXCEPTION] General Exception: No se puede actualizar el Alojamiento debido a que no existe un Alojamiento en la base de datos con ese id.");
			}

		}
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daoAlojamiento.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}	
	}

	/**
	 * Metodo que modela la transaccion que actualiza en la base de datos al Alojamiento que entra por parametro.<br/>
	 * Solamente se actualiza si existe el Alojamiento en la Base de Datos <br/>
	 * <b> post: </b> se ha actualizado el Alojamiento que entra como parametro <br/>
	 * @param Alojamiento - Alojamiento a actualizar. Alojamiento != null
	 * @throws Exception - Cualquier error que se genere actualizando al Alojamiento.
	 */
	public Informe deshabilitarOfertaAlojamiento(Alojamiento alojamiento) throws Exception 
	{

		ArrayList <String> array = new ArrayList<>();
		Informe inf = new Informe(array);
		DAOReserva daoReserva = new DAOReserva( );
		DAOCliente daoCliente = new DAOCliente();
		DAOAlojamiento daoAlojamiento = new DAOAlojamiento( );
		try
		{
			this.conn = darConexion();
			daoAlojamiento.setConn( conn );
			inf = daoAlojamiento.deshabilitarOfertaAlojamiento(alojamiento, daoReserva, daoCliente);

		}
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daoAlojamiento.cerrarRecursos();
				daoCliente.cerrarRecursos();
				daoReserva.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}	
		return inf;
	}
	/**
	 * Metodo que modela la transaccion que actualiza en la base de datos al Alojamiento que entra por parametro.<br/>
	 * Solamente se actualiza si existe el Alojamiento en la Base de Datos <br/>
	 * <b> post: </b> se ha actualizado el Alojamiento que entra como parametro <br/>
	 * @param Alojamiento - Alojamiento a actualizar. Alojamiento != null
	 * @throws Exception - Cualquier error que se genere actualizando al Alojamiento.
	 */
	public Informe habilitarOfertaAlojamiento(Alojamiento alojamiento) throws Exception 
	{
		ArrayList <String> array = new ArrayList<>();
		Informe inf = new Informe(array);
		DAOAlojamiento daoAlojamiento = new DAOAlojamiento( );
		try
		{
			this.conn = darConexion();
			daoAlojamiento.setConn( conn );
			
			inf=	daoAlojamiento.habilitarOfertaAlojamiento( alojamiento);
			

		}
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daoAlojamiento.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}	
		return inf;
	}
	/**
	 * Metodo que modela la transaccion que actualiza en la base de datos al Reserva que entra por parametro.<br/>
	 * Solamente se actualiza si existe el Reserva en la Base de Datos <br/>
	 * <b> post: </b> se ha actualizado el Reserva que entra como parametro <br/>
	 * @param Reserva - Reserva a actualizar. Reserva != null
	 * @throws Exception - Cualquier error que se genere actualizando al Reserva.
	 */
	public void cancelarReserva(Reserva Reserva) throws Exception 
	{
		DAOAlojamiento daoAlojamiento = new DAOAlojamiento();
		DAOCliente daoCliente = new DAOCliente();
		DAOReserva daoReserva = new DAOReserva();
		try
		{
			this.conn = darConexion();
			daoReserva.setConn(conn);
			daoAlojamiento.setConn(conn);
			daoCliente.setConn(conn);
			Reserva actual = daoReserva.findReservaById(Reserva.getId(),daoAlojamiento,daoCliente);
			if (actual!= null)
			{
				daoReserva.cancelarReserva(Reserva);
			}
			else
			{
				throw new Exception ("[EXCEPTION] General Exception: No se puede actualizar el Reserva debido a que no existe un Reserva en la base de datos con ese id.");
			}

		}
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daoReserva.cerrarRecursos();
				daoAlojamiento.cerrarRecursos();
				daoCliente.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}	
	}
	/**
	 * Metodo que modela la transaccion que actualiza en la base de datos al Reserva que entra por parametro.<br/>
	 * Solamente se actualiza si existe el Reserva en la Base de Datos <br/>
	 * <b> post: </b> se ha actualizado el Reserva que entra como parametro <br/>
	 * @param Reserva - Reserva a actualizar. Reserva != null
	 * @throws Exception - Cualquier error que se genere actualizando al Reserva.
	 */
	public Informe cancelarReservaColectiva(ReservaColectiva reservaColectiva) throws Exception 
	{
		ArrayList <String> array = new ArrayList<>();
		DAOAlojamiento daoAlojamiento = new DAOAlojamiento();
		DAOCliente daoCliente = new DAOCliente();
		DAOReserva daoReserva = new DAOReserva();
		Informe inf = new Informe(array);
		try
		{
			this.conn = darConexion();
			daoReserva.setConn(conn);
			daoAlojamiento.setConn(conn);
			daoCliente.setConn(conn);
			inf = daoReserva.cancelarReservaColectiva(reservaColectiva , daoAlojamiento , daoCliente);


		}
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daoReserva.cerrarRecursos();
				daoAlojamiento.cerrarRecursos();
				daoCliente.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return inf;
	}
	/**
	 * Metodo que modela la transaccion que retorna las ganancias de todos  operadores. <br/>
	 * @return String - string de operadores que contiene el resultado de la consulta.
	 * @throws Exception -  Cualquier error que se genere durante la transaccion
	 */
	public List <OperadorGan> getGananciasActualesOperadores() throws Exception {
		DAOOperador daooperador = new DAOOperador();
		List <OperadorGan> operadores;
		try 
		{
			this.conn = darConexion();
			daooperador.setConn(conn);
			operadores = daooperador.getGananciasActualesOperadores();
		}
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daooperador.cerrarRecursos();				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return operadores;
	}
	/**
	 * Metodo que modela la transaccion que retorna las ganancias de todos  operadores. <br/>
	 * @return String - string de operadores que contiene el resultado de la consulta.
	 * @throws Exception -  Cualquier error que se genere durante la transaccion
	 */
	public List <OperadorGan> getGananciasPasadasOperadores() throws Exception {
		DAOOperador daooperador = new DAOOperador();
		List <OperadorGan> operadores;
		try 
		{
			this.conn = darConexion();
			daooperador.setConn(conn);
			operadores = daooperador.getGananciasPasadasOperadores();
		}
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daooperador.cerrarRecursos();				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return operadores;
	}

	/**
	 * Metodo que modela la transaccion que retorna todos los Alojamientos top de la base de datos. <br/>
	 * @return List<Alojamiento> - Lista de Alojamientos top que contiene el resultado de la consulta.
	 * @throws Exception -  Cualquier error que se genere durante la transaccion
	 */
	public List<AlojamientosTop> getAlojamientosMasPopulares() throws Exception {
		DAOAlojamiento daoAlojamiento = new DAOAlojamiento();
		List<AlojamientosTop> Alojamientos;
		try 
		{
			this.conn = darConexion();
			daoAlojamiento.setConn(conn);

			Alojamientos = daoAlojamiento.getAlojamientosMasPopulares();
		}
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daoAlojamiento.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return Alojamientos;
	}
	/**
	 * Metodo que modela la transaccion que retorna todos los usos de AlohAndes. <br/>
	 * @return List<Cliente> - Lista de usos que contiene el resultado de la consulta.
	 * @throws Exception -  Cualquier error que se genere durante la transaccion
	 */
	public List<UsoAlohAndes> getUsoAlohAndes() throws Exception {
		DAOCliente daoCliente = new DAOCliente();
		List<UsoAlohAndes> Clientes;
		try 
		{
			this.conn = darConexion();
			daoCliente.setConn(conn);

			Clientes = daoCliente.getUsoAlohAndes();
		}
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daoCliente.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return Clientes;
	}
	/**
	 * Metodo que modela la transaccion que busca alojamientos con restricciones. <br/>
	 * @param name -id del Alojamiento a buscar. id != null
	 * @return Alojamiento - Alojamiento que se obtiene como resultado de la consulta.
	 * @throws Exception -  cualquier error que se genere durante la transaccion
	 */
	public List <Alojamiento> getAlojamientosConRestriccion(Condiciones pCondiciones) throws Exception {
		DAOAlojamiento daoAlojamiento = new DAOAlojamiento();
		List <Alojamiento> Alojamientos = null;
		try 
		{
			this.conn = darConexion();
			daoAlojamiento.setConn(conn);
			Alojamientos = daoAlojamiento.getAlojamientosConRestriccion(pCondiciones);
		} 
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daoAlojamiento.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return Alojamientos;
	}
	/**
	 * Metodo por el que se obtienen los indices de ocupacion de varios alojamientos<br/>
	 * @param name -id del Alojamiento a buscar. id != null
	 * @return Alojamiento - Alojamiento que se obtiene como resultado de la consulta.
	 * @throws Exception -  cualquier error que se genere durante la transaccion
	 */
	public List <IndiceOcupacion> getIndicesOcupacion() throws Exception {
		DAOOperador daoOperador = new DAOOperador();
		List <IndiceOcupacion> Alojamientos = null;
		try 
		{
			this.conn = darConexion();
			daoOperador.setConn(conn);
			Alojamientos = daoOperador.getIndicesOcupacion();
		} 
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daoOperador.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return Alojamientos;
	}
	/**
	 * Metodo que obtiene las estadisticas del cliente enviado por parametro<br/>
	 * @param name -id del Cliente a buscar. id != null
	 * @return Cliente - Cliente que se obtiene como resultado de la consulta.
	 * @throws Exception -  cualquier error que se genere durante la transaccion
	 */
	public EstadCli getEstadisticasCliente(Cliente id) throws Exception {
		DAOCliente daoCliente = new DAOCliente();
		EstadCli Cliente = null;
		try 
		{
			this.conn = darConexion();
			daoCliente.setConn(conn);
			Cliente = daoCliente.getEstadisticasCliente(id);
			if(Cliente == null)
			{
				throw new Exception("El Cliente con el id = " + id + " no se encuentra persistido en la base de datos.");				
			}
		} 
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daoCliente.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return Cliente;
	}
	/**
	 * este metodo da una lista con los clieentes mas fieles para un alojamiento dado
	 * @param id id del alojamiento sobre el cual se quiere hacer la consulta
	 * @return una lista de clientes
	 * @throws Exception  cualquier error que se genere durante la transaccion
	 */
	
	public List<Cliente> getClientesFieles(Long id) throws Exception
	{
		DAOCliente daoCliente = new DAOCliente();
		List<Cliente> Clientes;
		try 
		{
			this.conn = darConexion();
			daoCliente.setConn(conn);
			Clientes = daoCliente.getClientesFieles(id);
		}
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daoCliente.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return Clientes;	
		
		
	}
	/**
	 * este metodo dice cuales dias son los de mayor 
	 * @param pCondiciones las condiciones establecen el rango de fechas posibles
	 * @return un informe que detalla maxima y minima ocupacion y maxima recaudacion.
	 * @throws Exception si se genera cualquier error.
	 */
	public Informe getDiasPico(Condiciones2 pCondiciones) throws Exception
	{
		ArrayList <String> array = new ArrayList<>();
		DAOAlojamiento daoAlojamiento = new DAOAlojamiento();
		DAOCliente daoCliente = new DAOCliente();
		DAOReserva daoReserva = new DAOReserva();
		Informe inf = new Informe(array);
		try 
		{
			this.conn = darConexion();
			daoAlojamiento.setConn(conn);
		 inf = daoAlojamiento.getOperacionAlohAndes(pCondiciones);
		}
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daoAlojamiento.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return inf;	
	}
		
	
	
	//	/**
	//	 * Metodo que modela la transaccion que elimina de la base de datos al Reserva que entra por parametro. <br/>
	//	 * Solamente se actualiza si existe el Reserva en la Base de Datos <br/>
	//	 * <b> post: </b> se ha eliminado el Reserva que entra por parametro <br/>
	//	 * @param Reserva - Reserva a eliminar. Reserva != null
	//	 * @throws Exception - Cualquier error que se genere eliminando al Reserva.
	//	 */
	//	public void deleteReserva(Reserva Reserva) throws Exception 
	//	{
	//		DAOReserva daoReserva = new DAOReserva( );
	//		DAOAlojamiento daoAlojamiento = new DAOAlojamiento();
	//		try
	//		{
	//			this.conn = darConexion();
	//			daoReserva.setConn( conn );
	//			daoAlojamiento.setConn(conn);
	//			Reserva actual = daoReserva.findReservaById(Reserva.getId(),daoAlojamiento);
	//			if (actual!= null)
	//			{
	//				daoReserva.deleteReserva(Reserva,daoAlojamiento);
	//			}
	//			else
	//			{
	//				throw new Exception ("[EXCEPTION] General Exception: No se puede eliminar el Reserva debido a que no existe un Reserva en la base de datos con ese id.");
	//			}
	//
	//		}
	//		catch (SQLException sqlException) {
	//			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
	//			sqlException.printStackTrace();
	//			throw sqlException;
	//		} 
	//		catch (Exception exception) {
	//			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
	//			exception.printStackTrace();
	//			throw exception;
	//		} 
	//		finally {
	//			try {
	//				daoAlojamiento.cerrarRecursos();
	//				daoReserva.cerrarRecursos();
	//				if(this.conn!=null){
	//					this.conn.close();					
	//				}
	//			}
	//			catch (SQLException exception) {
	//				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
	//				exception.printStackTrace();
	//				throw exception;
	//			}
	//		}	
	//	}


}