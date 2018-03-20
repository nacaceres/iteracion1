package dao;

import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.*;

/**
 * Clase DAO que se conecta la base de datos usando JDBC para resolver los requerimientos de la aplicacion
 */
public class DAOReserva {

	//----------------------------------------------------------------------------------------------------------------------------------
	// CONSTANTES
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Constante para indicar un usuario Oracle
	 */
	public final static String USUARIO = "ISIS2304A431810";


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
	 * Metodo constructor de la clase DAOReserva <br/>
	 */
	public DAOReserva() {
		recursos = new ArrayList<Object>();
	}

	//----------------------------------------------------------------------------------------------------------------------------------
	// METODOS DE COMUNICACION CON LA BASE DE DATOS
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Metodo que obtiene la informacion de todos los reservas en la Base de Datos <br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>
	 * @return	lista con la informacion de todos los reservas que se encuentran en la Base de Datos
	 * @throws SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public ArrayList<Reserva> getReservas( DAOReserva daoReserva) throws SQLException, Exception {
		ArrayList<Reserva> reservas = new ArrayList<Reserva>();

		String sql = String.format("SELECT * FROM %1$s.RESERVAS", USUARIO);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
		reservas.add(convertResultSetToReserva(rs,daoReserva));
		}
		return reservas;
	}

	

	/**
	 * Metodo que obtiene la informacion del Reserva en la Base de Datos que tiene el identificador dado por parametro<br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/> 
	 * @param id el identificador del Reserva
	 * @return la informacion del Reserva que cumple con los criterios de la sentecia SQL
	 * 			Null si no existe el bebedor conlos criterios establecidos
	 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public Reserva findReservaById(Long id, DAOReserva daoReserva) throws SQLException, Exception 
	{
		Reserva Reserva = null;

		String sql = String.format("SELECT * FROM %1$s.RESERVAS WHERE ID = %2$d", USUARIO, id); 

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if(rs.next()) {
			Reserva = convertResultSetToReserva(rs,daoReserva);
		}

		return Reserva;
	}

	/**
	 * Metodo que agregar la informacion de un nuevo Reserva en la Base de Datos a partir del parametro ingresado<br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>  
	 * @param Reserva Reserva que desea agregar a la Base de Datos
	 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public void addReserva (Reserva Reserva, DAOReserva daoReserva) throws SQLException, Exception {
		String fecha1 = "'"+Reserva.getFechaInicio().getDate() +"/" +Reserva.getFechaInicio().getMonth()+"/" +Reserva.getFechaInicio().getYear()+"'";
		String fecha2 = "'"+Reserva.getFechaFin().getDate() +"/" +Reserva.getFechaFin().getMonth()+"/" +Reserva.getFechaFin().getYear()+"'";
		String fecha3 = "'"+Reserva.getFechaCancelacion().getDate() +"/" +Reserva.getFechaCancelacion().getMonth()+"/" +Reserva.getFechaCancelacion().getYear()+"'";
		String fecha4 = "'"+Reserva.getTiempoOportunoCan().getDate() +"/" +Reserva.getTiempoOportunoCan().getMonth()+"/" +Reserva.getTiempoOportunoCan().getYear()+"'";
		String cancelada = "F";
		if(Reserva.isCancelada())
			cancelada = "T";
		String terminada = "F";
		if(Reserva.isTerminada())
			terminada = "T";
		String sql = "INSERT INTO "+USUARIO+".RESERVAS (ID, NUM_DIAS, FECHA_INICIO, FECHA_FIN, NUM_PERSONAS,CANCELADA, FECHA_CANCELACION, COSTO_DEFINITIVO, TIEMPO_OPORTUNO, TERMINADA, ID_Reserva, ID_CLIENTE) VALUES ("+ 
				Reserva.getId()+" , "+
				Reserva.getNumDias()+" , "+
				fecha1+" , "+
				fecha2+" , "+
				Reserva.getNumPersonas()+" , '"+
				cancelada+ "' , "+
				fecha3+" , '"+
				Reserva.getCostoDefinitivo()+" , '"+
				fecha4+" , '"+
				terminada+ "' , "+
				Reserva.getAlojamiento().getId()+" , "+
				Reserva.getCliente().getId()+")";
		System.out.println(sql);
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
		
	}

//	/**
//	 * Metodo que actualiza la informacion del Reserva en la Base de Datos que tiene el identificador dado por parametro<br/>
//	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>  
//	 * @param Reserva Reserva que desea actualizar a la Base de Datos
//	 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
//	 * @throws Exception Si se genera un error dentro del metodo.
//	 */
//	public void updateReserva(Reserva Reserva) throws SQLException, Exception {
//		//No actualiza los Reservas.
//		StringBuilder sql = new StringBuilder();
//		sql.append(String.format("UPDATE %s.reservaS SET ", USUARIO));
//		sql.append(String.format("NOMBRE = '%1$s' , CONTACTO = '%2$s'", Reserva.getNombre(), Reserva.getContacto()));
//		sql.append(String.format(" WHERE ID = %d ", Reserva.getId()));
//		System.out.println(sql);
//		PreparedStatement prepStmt = conn.prepareStatement(sql.toString());
//		recursos.add(prepStmt);
//		prepStmt.executeQuery();
//	}
//
//	/**
//	 * Metodo que actualiza la informacion del Reserva en la Base de Datos que tiene el identificador dado por parametro<br/>
//	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>  
//	 * @param Reserva Reserva que desea actualizar a la Base de Datos
//	 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
//	 * @throws Exception Si se genera un error dentro del metodo.
//	 */
//	public void deleteReserva(Reserva Reserva,DAOReserva daoReserva) throws SQLException, Exception {
//
//		for (int i = 0; i < Reserva.getReservas().size(); i++) {
//			Reserva actual = Reserva.getReservas().get(i);
//			//Implementar el metodo delete de DAOReserva.
//		}
//		
//		String sql2 = String.format("DELETE FROM %1$s.reservaS WHERE ID = %2$d", USUARIO, Reserva.getId());
//		
//		System.out.println(sql2);
//
//		PreparedStatement prepStmt2 = conn.prepareStatement(sql2);
//		recursos.add(prepStmt2);
//		prepStmt2.executeQuery();
//		
//		String sql = String.format("DELETE FROM %1$s.RELACIONES WHERE ID = %2$d", USUARIO, Reserva.getRelacionUniandes().getId());
//		
//		System.out.println(sql);
//
//		PreparedStatement prepStmt = conn.prepareStatement(sql);
//		recursos.add(prepStmt);
//		prepStmt.executeQuery();
//	}
//
//	//----------------------------------------------------------------------------------------------------------------------------------
//	// METODOS AUXILIARES
//	//----------------------------------------------------------------------------------------------------------------------------------
//
//	/**
//	 * Metodo encargado de inicializar la conexion del DAO a la Base de Datos a partir del parametro <br/>
//	 * <b>Postcondicion: </b> el atributo conn es inicializado <br/>
//	 * @param connection la conexion generada en el TransactionManager para la comunicacion con la Base de Datos
//	 */
//	public void setConn(Connection connection){
//		this.conn = connection;
//	}
//
//	/**
//	 * Metodo que cierra todos los recursos que se encuentran en el arreglo de recursos<br/>
//	 * <b>Postcondicion: </b> Todos los recurso del arreglo de recursos han sido cerrados.
//	 */
//	public void cerrarRecursos() {
//		for(Object ob : recursos){
//			if(ob instanceof PreparedStatement)
//				try {
//					((PreparedStatement) ob).close();
//				} catch (Exception ex) {
//					ex.printStackTrace();
//				}
//		}
//	}

	/**
	 * Metodo que transforma el resultado obtenido de una consulta SQL (sobre la tabla reservaS y RELACIONES) en una instancia de la clase Reserva.
	 * @param resultSet ResultSet con la informacion de un Reserva que se obtuvo de la base de datos.
	 * @return Reserva cuyos atributos corresponden a los valores asociados a un registro particular de la tabla reservaS y RELACIONES.
	 * @throws SQLException Si existe algun problema al extraer la informacion del ResultSet.
	 */
	public Reserva convertResultSetToReserva(ResultSet resultSet, DAOReserva daoReserva) throws SQLException, Exception {

		long id = Long.parseLong(resultSet.getString("ID"));
		int numDias = Integer.parseInt(resultSet.getString("NUM_DIAS"));
		String fechaInicio = resultSet.getString("FECHA_INICIO");
		
		String tipo = resultSet.getString("TIPO_ID");
		String nombre = resultSet.getString("NOMBRE");
		String contacto = resultSet.getString("CONTACTO");
		long idRelacion = Long.parseLong(resultSet.getString("ID_RELACION"));
		String sql = String.format("SELECT * FROM %1$s.RELACIONES WHERE ID = %2$d", USUARIO, idRelacion);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		rs.next();
		String tipo2 = rs.getString("TIPO");
		int carnet = Integer.parseInt(rs.getString("CARNET"));
		
		RelacionUniandes rela = new RelacionUniandes(idRelacion, tipo2, carnet);
		
		ArrayList<Reserva> Reservas = new ArrayList<Reserva>();

		String sql4 = String.format("SELECT * FROM %1$s.ReservaS WHERE ID_Reserva = %2$d", USUARIO,id);

		PreparedStatement prepStmt4 = conn.prepareStatement(sql4);
		recursos.add(prepStmt4);
		ResultSet rs4 = prepStmt4.executeQuery();

		while (rs4.next()) {
			Reservas.add(daoReserva.convertResultSetToReserva(rs4));
		}
		
		Reserva ope = new Reserva(id, tipo, nombre, contacto, Reservas, rela);

		return ope;
	}
}
