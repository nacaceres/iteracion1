package dao;

import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import vos.*;

/**
 * Clase DAO que se conecta la base de datos usando JDBC para resolver los requerimientos de la aplicacion
 */
public class DAOCliente {

	//----------------------------------------------------------------------------------------------------------------------------------
	// CONSTANTES
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Constante para indicar un usuario Oracle
	 */
	public final static String USUARIO = "ISIS2304A581810";


	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Arraylits de recursos que se usan para la ejecucion de sentencias SQL
	 */
	private ArrayList<Object> recursos;

	/**
	 * Atributo que genera la conexion a la base de datos
	 */
	private Connection conn;

	//----------------------------------------------------------------------------------------------------------------------------------
	// METODOS DE INICIALIZACION
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Metodo constructor de la clase DAOOperador <br/>
	 */
	public DAOCliente() {
		recursos = new ArrayList<Object>();
	}

	//----------------------------------------------------------------------------------------------------------------------------------
	// METODOS DE COMUNICACION CON LA BASE DE DATOS
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Metodo que obtiene la informacion de todos los Clientes en la Base de Datos <br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>
	 * @return	lista con la informacion de todos los Clientes que se encuentran en la Base de Datos
	 * @throws SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public ArrayList<Cliente> getClientes() throws SQLException, Exception {
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();

		String sql = String.format("SELECT * FROM %1$s.CLIENTES", USUARIO);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			clientes.add(convertResultSetToCliente(rs));
		}
		return clientes;
	}


	/**
	 * Metodo que obtiene la informacion de todos los Clientes fieles para un alojamiento dado <br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>
	 * @param id del alojamiento para el cual se hace el analisis
	 * @return	lista con la informacion de todos los Clientes que han reservado mas de 15 noches o en 3 ocasiones un alojamiento
	 * @throws SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public Informe getClientesFieles(Long  id)    throws SQLException,Exception
	{
		ArrayList<String> clientes = new ArrayList<String>();


		String sql=String.format("SELECT RES.ID_ALOJAMIENTO,RES.ID_CLIENTE,CLI.NOMBRE,CLI.CONTACTO,COUNT(*) AS NUMERO_OCASIONES,SUM(TRUNC(RES.FECHA_FIN)-TRUNC(RES.FECHA_INICIO))   AS NUM_NOCHES");
		String sql2=String.format(	" FROM RESERVAS RES INNER JOIN CLIENTES CLI ON CLI.ID=RES.ID_CLIENTE WHERE RES.ID_ALOJAMIENTO = %1$d AND RES.CANCELADA='F' ",id);
		String sql3=String.format(" GROUP BY RES.ID_CLIENTE, RES.ID_ALOJAMIENTO,CLI.NOMBRE,CLI.CONTACTO HAVING COUNT(*)>=3 OR SUM(TRUNC(RES.FECHA_FIN)-TRUNC(RES.FECHA_INICIO)) >=15");

		System.out.println(sql+sql2+sql3);
		PreparedStatement prepStmt = conn.prepareStatement(sql+sql2+sql3);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		String z = "No existen cliente fieles";
		boolean entro = false;
		while (rs.next()) {
			entro = true;
			String idAlojamiento = rs.getString("ID_ALOJAMIENTO");
			String idCliente = rs.getString("ID_CLIENTE");
			String nombre = rs.getString("NOMBRE");
			String contacto = rs.getString("CONTACTO");
			String numOcasiones = rs.getString("NUMERO_OCASIONES");
			String numNoches = rs.getString("NUM_NOCHES");
			String y = "El Alojamiento con id: "+ idAlojamiento+" tiene un cliente fiel, identificado con id: "+ idCliente+", con nombre: " + nombre+" y contacto: "+contacto+". Ha visitado el alojamiento en: "+numOcasiones+" ocasiones y se ha hospedado un total de: "+numNoches+" noches";
			clientes.add(y);
		}
		if(!entro)
		{
			clientes.add(z);
		}
		Informe x = new Informe(clientes);
		return x;


	}


	/**
	 * Metodo que obtiene la informacion del Cliente en la Base de Datos que tiene el identificador dado por parametro<br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/> 
	 * @param id el identificador del Cliente
	 * @return la informacion del Cliente que cumple con los criterios de la sentecia SQL
	 * 			Null si no existe el bebedor conlos criterios establecidos
	 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public Cliente findClienteById(Long id) throws SQLException, Exception 
	{
		Cliente Cliente = null;

		String sql = String.format("SELECT * FROM %1$s.CLIENTES WHERE ID = %2$d", USUARIO, id); 

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if(rs.next()) {
			Cliente = convertResultSetToCliente(rs);
		}
		rs.close();
		return Cliente;
	}

	/**
	 * Metodo que agregar la informacion de un nuevo Cliente en la Base de Datos a partir del parametro ingresado<br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>  
	 * @param Cliente Cliente que desea agregar a la Base de Datos
	 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public void addCliente (Cliente Cliente) throws SQLException, Exception {

		String sql2 = String.format("INSERT INTO %1$s.RELACIONES (ID, TIPO, CARNET) VALUES (%2$d,'%3$s', '%4$s')", 
				USUARIO,
				Cliente.getRelacionUniandes().getId(),
				Cliente.getRelacionUniandes().getTipo(),
				Cliente.getRelacionUniandes().getCarnet()+"");
		PreparedStatement prepStmt2 = conn.prepareStatement(sql2);
		recursos.add(prepStmt2);
		prepStmt2.executeQuery();

		String sql = String.format("INSERT INTO %1$s.CLIENTES (ID, TIPO_ID, NOMBRE, CONTACTO, ID_RELACION) VALUES (%2$d, '%3$s', '%4$s', '%5$s', %6$d)", 
				USUARIO, 
				Cliente.getId(), 
				Cliente.getTipoId(),
				Cliente.getNombre(),
				Cliente.getContacto(), 
				Cliente.getRelacionUniandes().getId());
		System.out.println(sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();


		//Falta agregar todos los alojamientos.
		//Falta Agregar todos los contratos
		//Falta agregar todos los servicios
		//Falta agregar todas las reservas
	}

	/**
	 * Metodo que actualiza la informacion del Cliente en la Base de Datos que tiene el identificador dado por parametro<br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>  
	 * @param Cliente Cliente que desea actualizar a la Base de Datos
	 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public void updateCliente(Cliente Cliente) throws SQLException, Exception {

		StringBuilder sql = new StringBuilder();
		sql.append(String.format("UPDATE %s.CLIENTES SET ", USUARIO));
		sql.append(String.format("NOMBRE = '%1$s' , CONTACTO = '%2$s'", Cliente.getNombre(), Cliente.getContacto()));
		sql.append(String.format(" WHERE ID = %d ", Cliente.getId()));
		System.out.println(sql);
		// Falta Actualizar los alojamientos del Cliente
		//Falta Agregar todos los contratos
		//Falta agregar todos los servicios
		//Falta agregar todas las reservas
		PreparedStatement prepStmt = conn.prepareStatement(sql.toString());
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

	/**
	 * Metodo que actualiza la informacion del Cliente en la Base de Datos que tiene el identificador dado por parametro<br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>  
	 * @param Cliente Cliente que desea actualizar a la Base de Datos
	 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public void deleteCliente(Cliente Cliente) throws SQLException, Exception {



		String sql2 = String.format("DELETE FROM %1$s.CLIENTES WHERE ID = %2$d", USUARIO, Cliente.getId());

		System.out.println(sql2);

		PreparedStatement prepStmt2 = conn.prepareStatement(sql2);
		recursos.add(prepStmt2);
		prepStmt2.executeQuery();

		String sql = String.format("DELETE FROM %1$s.RELACIONES WHERE ID = %2$d", USUARIO, Cliente.getRelacionUniandes().getId());

		System.out.println(sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
		//Revisar Cascade de los alojamientos
		//Falta revisar cascade todos los contratos
		//Falta revisar cascade todos los servicios
		//Falta revisar cascade todas las reservas
	}

	/**
	 * Metodo que obtiene la informacion de todo el uso de alohandes en la Base de Datos <br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>
	 * @return	lista con la informacion de todos los Clientes que se encuentran en la Base de Datos
	 * @throws SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public ArrayList<UsoAlohAndes> getUsoAlohAndes() throws SQLException, Exception {
		ArrayList<UsoAlohAndes> clientes = new ArrayList<UsoAlohAndes>();

		String sql = "SELECT RELA.TIPO,ALO.TIPO AS TIPO_ALOJAMIENTO,SUM(RE.COSTO_DEFINITIVO) AS DINERO_PAGADO,SUM(TRUNC(RE.FECHA_FIN)-TRUNC(RE.FECHA_INICIO) ) AS DIAS_CONTRATADOS FROM CLIENTES CLI INNER JOIN  RESERVAS RE ON RE.ID_CLIENTE=CLI.ID INNER JOIN ALOJAMIENTOS ALO ON ALO.ID=RE.ID_ALOJAMIENTO INNER JOIN RELACIONES RELA ON RELA.ID=CLI.ID_RELACION GROUP BY RELA.TIPO,ALO.TIPO";
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			String tipoUsuario = rs.getString("tipo");
			String tipoAlojamiento = rs.getString("TIPO_ALOJAMIENTO");
			String dineroPagado = rs.getString("DINERO_PAGADO");
			String dias = rs.getString("DIAS_CONTRATADOS");
			UsoAlohAndes actual = new UsoAlohAndes(tipoUsuario, tipoAlojamiento, dineroPagado, dias);
			clientes.add(actual);
		}
		return clientes;
	}

	/**
	 * Metodo que obtiene la informacion de todo el consumo de alohandes en la Base de Datos <br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>
	 * @param Condiciones de los clientes a quienes se quiere buscar el consumo
	 * @return	lista con la informacion de todos los Clientes que se encuentran en la Base de Datos
	 * @throws SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public ArrayList<Cliente> getConsumoAlohandes(CondicionesRFC10 pCondiciones) throws SQLException, Exception {
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();

		String fechaInicio1 = "'"+pCondiciones.getFechaInicio()+"'";

		String fechaFin1 = "'"+pCondiciones.getFechaFin()+"'";


		String condi = pCondiciones.getCondicionDeOrdenamiento();
		String idAloj = ""+pCondiciones.getIdAlojamiento();


		String sql = "SELECT * FROM CLIENTES CLIEN WHERE CLIEN.ID IN (" + 
				" SELECT RESERV.ID_CLIENTE FROM RESERVAS RESERV WHERE"+ 
				"(( RESERV.FECHA_INICIO  BETWEEN "+fechaInicio1+" AND "+fechaFin1+")" + 
				" OR  ( RESERV.FECHA_FIN  BETWEEN  "+fechaInicio1+" AND "+fechaFin1+")"+ 
				" OR (  RESERV.FECHA_INICIO < "+fechaInicio1+"  AND   RESERV.FECHA_FIN >  "+fechaFin1+")" + 
				") AND RESERV.ID_ALOJAMIENTO = "+idAloj+" )" + 
				" ORDER BY CLIEN."+condi;
		System.out.println(sql);
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		long startTime = System.nanoTime();
		ResultSet rs = prepStmt.executeQuery();
		long endTime = System.nanoTime();
		System.out.println((endTime - startTime)/1000);
		int contador = 0;
		while (rs.next()&& contador<1000) {
			clientes.add(convertResultSetToCliente(rs));
			contador++;
		}
		return clientes;
	}
	/**
	 * Metodo que obtiene la informacion de todo el consumo de alohandes en la Base de Datos <br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>
	 * @param Condiciones de los clientes a quienes se quiere buscar el consumo
	 * @return	lista con la informacion de todos los Clientes que se encuentran en la Base de Datos
	 * @throws SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public ArrayList<Cliente> getConsumoAlohandesAlternativo(CondicionesRFC10 pCondiciones) throws SQLException, Exception {
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();

		String fechaInicio1 = "'"+pCondiciones.getFechaInicio()+"'";

		String fechaFin1 = "'"+pCondiciones.getFechaFin()+"'";


		String condi = pCondiciones.getCondicionDeOrdenamiento();
		String idAloj = ""+pCondiciones.getIdAlojamiento();


		String sql = "SELECT * FROM CLIENTES CLIEN WHERE CLIEN.ID NOT IN (" + 
				" SELECT RESERV.ID_CLIENTE FROM RESERVAS RESERV WHERE"+ 
				"(( RESERV.FECHA_INICIO  BETWEEN "+fechaInicio1+" AND "+fechaFin1+")" + 
				" OR  ( RESERV.FECHA_FIN  BETWEEN  "+fechaInicio1+" AND "+fechaFin1+")"+ 
				" OR (  RESERV.FECHA_INICIO < "+fechaInicio1+"  AND   RESERV.FECHA_FIN >  "+fechaFin1+")" + 
				") AND RESERV.ID_ALOJAMIENTO = "+idAloj+" )" + 
				" ORDER BY CLIEN."+condi;
		System.out.println(sql);
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		long startTime = System.nanoTime();
		ResultSet rs = prepStmt.executeQuery();
		long endTime = System.nanoTime();
		System.out.println((endTime - startTime)/1000);
		int contador = 0;
		while (rs.next()&&contador<10000) {
			clientes.add(convertResultSetToCliente(rs));
			contador++;
		}
		return clientes;
	}
	/**
	 * Metodo que obtiene las estadisticas del cliente enviado por parametro<br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/> 
	 * @param id el identificador del Cliente
	 * @return la informacion del Cliente que cumple con los criterios de la sentecia SQL
	 * 			Null si no existe el bebedor conlos criterios establecidos
	 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public EstadCli getEstadisticasCliente(Cliente cliente1) throws SQLException, Exception 
	{
		EstadCli cliente = null;

		String sql = "SELECT CLI.ID,  CLI.NOMBRE,ALO.TIPO AS TIPO_ALOJAMIENTO,SUM(RE.COSTO_DEFINITIVO) AS DINERO_PAGADO,SUM(TRUNC(RE.FECHA_FIN)-TRUNC(RE.FECHA_INICIO) ) AS DIAS_CONTRATADOS FROM CLIENTES CLI INNER JOIN  RESERVAS RE ON RE.ID_CLIENTE=CLI.ID INNER JOIN ALOJAMIENTOS ALO ON ALO.ID=RE.ID_ALOJAMIENTO WHERE CLI.NOMBRE='"+cliente1.getNombre()+"'  GROUP BY CLI.NOMBRE,CLI.ID,ALO.TIPO";
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if(rs.next()) {
			String id = rs.getString("ID");
			String nombre = rs.getString("NOMBRE");
			String tipo = rs.getString("TIPO_ALOJAMIENTO");
			String dinero = rs.getString("DINERO_PAGADO");
			String dias = rs.getString("DIAS_CONTRATADOS");
			cliente = new EstadCli(id,nombre,tipo,dinero,dias);
		}

		return cliente;
	}


	/**
	 * Metodo que obtiene los clientes premiun<br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/> 
	 * @param id el identificador del Cliente
	 * @return la informacion del Cliente que cumple con los criterios de la sentecia SQL
	 * 			Null si no existe el bebedor conlos criterios establecidos
	 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public List<ClientePremium> getClientesPremium() throws SQLException, Exception 
	{
		ArrayList<ClientePremium> clientes = new ArrayList<>();

		String sql = "WITH Q1 AS( SELECT CLI.ID AS IDQ1, MIN(RESV.COSTO_DEFINITIVO/(TRUNC (RESV.FECHA_FIN) - TRUNC (RESV.FECHA_INICIO))) AS RAZONQ1 FROM CLIENTES CLI INNER JOIN RESERVAS RESV ON CLI.ID=RESV.ID_CLIENTE WHERE CLI.ID NOT IN (SELECT ID_CLIENTE  FROM RESERVAS RESI WHERE (RESI.COSTO_DEFINITIVO/(TRUNC (RESI.FECHA_FIN) - TRUNC (RESI.FECHA_INICIO)))<125) GROUP BY CLI.ID )," + 
				"Q2 AS ( SELECT CLI.ID AS IDQ2 , COUNT (RES1.ID) AS RAZONQ2 FROM ((CLIENTES CLI INNER JOIN RESERVAS RES1 ON  RES1.ID_CLIENTE=CLI.ID) INNER JOIN HABITACIONES_HOTEL HOT  ON HOT.ID=RES1.ID_ALOJAMIENTO) WHERE CLI.ID NOT IN (SELECT RESER1.ID_CLIENTE FROM RESERVAS  RESER1 INNER JOIN HABITACIONES_HOTEL HO ON HO.ID=RESER1.ID_ALOJAMIENTO WHERE HO.TIPO_HABITACION != 'SUITE') AND CLI.ID NOT IN( SELECT RESER2.ID_CLIENTE FROM RESERVAS  RESER2 INNER JOIN  ALOJAMIENTOS ALOJ ON ALOJ.ID=RESER2.ID_ALOJAMIENTO WHERE ALOJ.TIPO != 'HAB HOTEL' ) AND  CLI.ID IN( SELECT RES.ID_CLIENTE FROM RESERVAS RES ) GROUP BY CLI.ID)," + 
				"Q3 AS ( SELECT  RES.ID_CLIENTE AS IDQ3,COUNT(DISTINCT(TO_CHAR(RES.FECHA_INICIO,'MM'))*(TO_CHAR(RES.FECHA_INICIO,'YY'))) AS RAZONQ3 FROM RESERVAS RES GROUP BY RES.ID_CLIENTE HAVING COUNT(DISTINCT(TO_CHAR(RES.FECHA_INICIO,'MM'))*(TO_CHAR(RES.FECHA_INICIO,'YY'))) >= ( SELECT COUNT(*) FROM (SELECT  DISTINCT TO_CHAR(FECHA_INICIO,'YY'),TO_CHAR(FECHA_INICIO,'MM') FROM RESERVAS)))," + 
				"Q4 AS ( SELECT Q1.IDQ1, Q1.RAZONQ1, Q2.IDQ2, Q2.RAZONQ2 FROM Q1 FULL OUTER JOIN Q2 ON Q1.IDQ1 = Q2.IDQ2) " + 
				"SELECT Q4.IDQ1, Q4.RAZONQ1, Q4.IDQ2, Q4.RAZONQ2, Q3.IDQ3, Q3.RAZONQ3 FROM Q4 FULL OUTER JOIN Q3 ON Q4.IDQ1 = Q3.IDQ3";
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		long startTime = System.nanoTime();
		ResultSet rs = prepStmt.executeQuery();
		long endTime = System.nanoTime();
		System.out.println((endTime - startTime)/1000);
		System.out.println(sql);
		int contador = 0;
		int contador1 = 0;
		int contador2 = 0;
		int contador3 = 0;
		while(rs.next()) {
			if(contador%10 ==0)
			{
				String id = null;
				String id1 = rs.getString("IDQ1");
				String id2 = rs.getString("IDQ2");
				String id3 = rs.getString("IDQ3");
				if(id1!= null)
				{
					id= "El cliente con id: "+id1+" se considera como buen cliente por las siguientes razones: ";
				}
				else if( id2 != null)
				{
					id= "El cliente con id: "+id2+" se considera como buen cliente por las siguientes razones: ";
				}
				else
				{
					id= "El cliente con id: "+id3+" se considera como buen cliente por las siguientes razones: ";
				}
				ArrayList <String> razones = new ArrayList<>();
				String razon1 = rs.getString("RAZONQ1");
				String razon2 = rs.getString("RAZONQ2");
				String razon3 = rs.getString("RAZONQ3");
				if(razon1 != null) 
				{
					contador1++;
					razones.add("Todas las reservas del cliente superan los $125 por noche, su reserva mas barata ha sido de: " +razon1);
				}
				if(razon2!= null)
				{
					contador2++;
					razones.add("Todas las reservas del cliente han sido en habitaciones de hotel tipo SUITE, ya que de un total de: "+ razon2 +" reservas, "+ razon2 + " han sido de tipo SUITE");
				}
				if(razon3 != null)
				{
					contador3++;
					razones.add("El cliente ha hecho una reserva minimo una vez al mes desde el inicio de AlohAndes, con un total de: "+razon3+" reservas");
				}

				ClientePremium cliente = new ClientePremium(id, razones);
				clientes.add(cliente);
				contador++;
			}
			contador++;
			//System.out.println(contador2);
		}
		//System.out.println(contador1+" de razon 1 " +contador2+" de razon 2 "+contador3+" de razon 3 "+"de un total de: " +contador );
		return clientes;
	}



	//----------------------------------------------------------------------------------------------------------------------------------
	// METODOS AUXILIARES
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Metodo encargado de inicializar la conexion del DAO a la Base de Datos a partir del parametro <br/>
	 * <b>Postcondicion: </b> el atributo conn es inicializado <br/>
	 * @param connection la conexion generada en el TransactionManager para la comunicacion con la Base de Datos
	 */
	public void setConn(Connection connection){
		this.conn = connection;
	}

	/**
	 * Metodo que cierra todos los recursos que se encuentran en el arreglo de recursos<br/>
	 * <b>Postcondicion: </b> Todos los recurso del arreglo de recursos han sido cerrados.
	 */
	public void cerrarRecursos() {
		for(Object ob : recursos){
			if(ob instanceof PreparedStatement)
				try {
					((PreparedStatement) ob).close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
		}
	}












	/**
	 * Metodo que transforma el resultado obtenido de una consulta SQL (sobre la tabla CLIENTES y RELACIONES) en una instancia de la clase Cliente.
	 * @param resultSet ResultSet con la informacion de un Cliente que se obtuvo de la base de datos.
	 * @return Cliente cuyos atributos corresponden a los valores asociados a un registro particular de la tabla ClienteS y RELACIONES.
	 * @throws SQLException Si existe algun problema al extraer la informacion del ResultSet.
	 */
	public Cliente convertResultSetToCliente(ResultSet resultSet) throws SQLException, Exception {

		long id = Long.parseLong(resultSet.getString("ID"));
		String tipo = resultSet.getString("TIPO_ID");
		String nombre = resultSet.getString("NOMBRE");
		String contacto = resultSet.getString("CONTACTO");
		long idRelacion = Long.parseLong(resultSet.getString("ID_RELACION"));
		//		String sql = String.format("SELECT * FROM %1$s.RELACIONES WHERE ID = %2$d", USUARIO, idRelacion);
		//
		//		PreparedStatement prepStmt = conn.prepareStatement(sql);
		//		recursos.add(prepStmt);
		//		ResultSet rs = prepStmt.executeQuery();
		//		rs.next();
		//		String tipo2 = rs.getString("TIPO");
		//		int carnet = Integer.parseInt(rs.getString("CARNET"));
		//
		//		RelacionUniandes rela = new RelacionUniandes(idRelacion, tipo2, carnet);
		ArrayList <Alojamiento> alojamientos = new ArrayList <Alojamiento>();
		ArrayList <Contrato> contratos = new ArrayList <Contrato>();
		ArrayList <Reserva> reservas = new ArrayList <Reserva>();
		ArrayList <Servicio> servicios = new ArrayList <Servicio>();
		//Falta agregar todos los alojamientos.
		//Falta Agregar todos los contratos
		//Falta agregar todos los servicios
		//Falta agregar todas las reservas
		Cliente ope = new Cliente(id, tipo, nombre, contacto, null, contratos, reservas, servicios, alojamientos);

		return ope;
	}
}
