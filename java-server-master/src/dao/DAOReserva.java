package dao;

import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
	public ArrayList<Reserva> getReservas( DAOAlojamiento daoAlojamiento, DAOCliente daoCliente) throws SQLException, Exception {
		ArrayList<Reserva> reservas = new ArrayList<Reserva>();

		String sql = String.format("SELECT * FROM %1$s.RESERVAS", USUARIO);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			reservas.add(convertResultSetToReserva(rs,daoAlojamiento,daoCliente));
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
	public Reserva findReservaById(Long id, DAOAlojamiento daoAlojamiento, DAOCliente daoCliente) throws SQLException, Exception 
	{
		Reserva Reserva = null;

		String sql = String.format("SELECT * FROM %1$s.RESERVAS WHERE ID = %2$d", USUARIO, id); 

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if(rs.next()) {
			Reserva = convertResultSetToReserva(rs,daoAlojamiento,daoCliente);
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
	public void addReserva (Reserva Reserva) throws SQLException, Exception {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String fecha1 = "'"+dateFormat.format(Reserva.getFechaInicio())+"'";
		String fecha2 = "'"+dateFormat.format(Reserva.getFechaFin())+"'";
		String fecha4 = "'"+dateFormat.format(Reserva.getTiempoOportunoCan())+"'";
		String cancelada = "F";
		if(Reserva.isCancelada())
			cancelada = "T";
		String terminada = "F";
		if(Reserva.isTerminada())
			terminada = "T";
		String colectiva = "F";
		if(Reserva.isColectiva())
			colectiva = "T";
		String sql = "INSERT INTO "+USUARIO+".RESERVAS (ID, NUM_DIAS, FECHA_INICIO, FECHA_FIN, NUM_PERSONAS,CANCELADA, FECHA_CANCELACION, COSTO_DEFINITIVO, TIEMPO_OPORTUNO, TERMINADA, ID_ALOJAMIENTO, ID_CLIENTE, COLECTIVA, ID_COLECTIVA) VALUES ("+ 
				Reserva.getId()+" , "+
				Reserva.getNumDias()+" , "+
				fecha1+" , "+
				fecha2+" , "+
				Reserva.getNumPersonas()+" , '"+
				cancelada+ "' , "+
				null+" , "+
				Reserva.getCostoDefinitivo()+" ,"+
				fecha4+" , '"+
				terminada+ "' , "+
				Reserva.getAlojamiento().getId()+" , '"+
				colectiva +"' , "+
				Reserva.getIdColectiva()+" , "+
				Reserva.getCliente().getId()+")";
		System.out.println(sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		//recursos.add(prepStmt);
		prepStmt.executeQuery();
		prepStmt.close();
		//Pendiente anhadir los servicios a tablas.

	}


	/**
	 * Metodo que intentara agregar la informacion de una reserva colectiva en la Base de Datos a partir de los parametro ingresado<br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>  
	 * @param Reserva Reserva que desea agregar a la Base de Datos
	 * @param Cantidad cantidad de reservas a agregar-
	 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si no se puede generar la reserva colectiva.
	 */
	public Informe addReservaColectiva (ReservaColectiva reservaColectiva, DAOAlojamiento daoAlojamiento) throws SQLException, Exception {
		ArrayList<String> informe = new ArrayList<String>();
		String tipo = reservaColectiva.getReserva().getAlojamiento().getTipo();
		Date fechaInicio = reservaColectiva.getReserva().getFechaInicio();
		Date fechaFin = reservaColectiva.getReserva().getFechaFin();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String fecha1 = "'"+dateFormat.format(fechaInicio)+"'";
		String fecha2 = "'"+dateFormat.format(fechaFin)+"'";
		String sql = "SELECT * FROM "+USUARIO+".ALOJAMIENTOS ALO WHERE ALO.ID NOT IN ( SELECT RE.ID_ALOJAMIENTO FROM  "+USUARIO+".RESERVAS RE WHERE( RE.FECHA_INICIO  BETWEEN "+fechaInicio+" AND "+fechaFin+") OR  ( RE.FECHA_FIN  BETWEEN "+fechaInicio+" AND "+fechaFin+") OR (  RE.FECHA_INICIO <"+fechaInicio+"  AND   RE.FECHA_FIN>  "+fechaFin+") ) AND ALO.ID IN ( SELECT ALOJA.ID FROM  "+USUARIO+".ALOJAMIENTOS ALOJA WHERE ALOJA.TIPO='"+tipo+"');";
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		
		String sql3 = "SELECT COUNT (*) AS NUM_ALOJAMIENTOS FROM "+USUARIO+".ALOJAMIENTOS ALO WHERE ALO.ID NOT IN ( SELECT RE.ID_ALOJAMIENTO FROM  "+USUARIO+".RESERVAS RE WHERE( RE.FECHA_INICIO  BETWEEN "+fechaInicio+" AND "+fechaFin+") OR  ( RE.FECHA_FIN  BETWEEN "+fechaInicio+" AND "+fechaFin+") OR (  RE.FECHA_INICIO <"+fechaInicio+"  AND   RE.FECHA_FIN>  "+fechaFin+") ) AND ALO.ID IN ( SELECT ALOJA.ID FROM  "+USUARIO+".ALOJAMIENTOS ALOJA WHERE ALOJA.TIPO='"+tipo+"');";
		PreparedStatement prepStmt3 = conn.prepareStatement(sql3);
		recursos.add(prepStmt3);
		ResultSet rs3 = prepStmt3.executeQuery();
		
		int rowcount = Integer.parseInt(rs3.getString("NUM_ALOJAMIENTOS"));
		if(rowcount >= reservaColectiva.getCantidad())
		{
			String sql2 = "SELECT COUNT (*) AS NUMACTUAL FROM "+USUARIO+".RESERVAS;";
			PreparedStatement prepStmt2 = conn.prepareStatement(sql2);
			recursos.add(prepStmt2);
			ResultSet rs2 = prepStmt2.executeQuery();
			Long idReserva = Long.parseLong(rs2.getString("NUMACTUAL"));
			int contador = 0;
			while (rs.next()) {
				contador++;
				Alojamiento actual = daoAlojamiento.convertResultSetToAlojamiento(rs);
				Reserva ope = new Reserva(idReserva+1, reservaColectiva.getReserva().getNumDias(), fechaInicio, fechaFin, reservaColectiva.getReserva().isCancelada() , reservaColectiva.getReserva().getNumPersonas(), null, actual.getCostoBasico(), reservaColectiva.getReserva().isTerminada(), reservaColectiva.getReserva().getTiempoOportunoCan(), actual, reservaColectiva.getReserva().getCliente(), true , reservaColectiva.getIdReservaColectiva(), reservaColectiva.getReserva().getServiciosAdicionales());
				addReserva(ope);
				String x = "La reserva No."+contador+"ha sido asignada al alojamiento con id "+actual.getId()+" con ubicacion "+actual.getUbicacion()+" con id de reserva individual "+idReserva+1;
				informe.add(x);
			}
		}
		else
		{
			String y ="La reserva colectiva no pudo realizarse debido a que no se contaban con los alojamientos requeridos.";
			informe.add(y);
		}
		Informe inf = new Informe(informe);
		return inf;
	}

	/**
	 * Metodo que actualiza la informacion del Reserva en la Base de Datos que tiene el identificador dado por parametro<br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>  
	 * @param Reserva Reserva que desea actualizar a la Base de Datos
	 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public void cancelarReserva(Reserva Reserva) throws SQLException, Exception {

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date1 = new Date();
		String x = dateFormat.format(date1);
		String fecha = x;
		double costoDef = Reserva.getCostoDefinitivo();
		if(Reserva.getTiempoOportunoCan().compareTo(date1)>0)
		{
			costoDef = costoDef *0.1;
		}
		else if (Reserva.getTiempoOportunoCan().compareTo(date1)<0 && Reserva.getFechaInicio().compareTo(date1)>0)
		{
			costoDef = costoDef *0.3;
		}
		else
			costoDef = costoDef *0.5;
		String sql = "UPDATE "+ USUARIO +".RESERVAS SET "+"CANCELADA = 'T' , FECHA_CANCELACION = '"+fecha+"' , COSTO_DEFINITIVO = "+costoDef+" , TERMINADA = 'F' WHERE ID ="+Reserva.getId();
		System.out.println(sql);
		PreparedStatement prepStmt = conn.prepareStatement(sql.toString());
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	
	/**
	 * Metodo que cancela la reserva colectiva dada por parametro<br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>  
	 * @param Reserva Reserva colectiva que desea cancelar
	 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public Informe cancelarReservaColectiva(ReservaColectiva reservaColectiva, DAOAlojamiento daoAlojamiento, DAOCliente daoCliente) throws SQLException, Exception {
		ArrayList<Reserva> reservas = new ArrayList<Reserva>();
		double costoDefinitivo = 0;
		String sql ="SELECT * FROM "+ USUARIO +".RESERVAS WHERE ID_COLECTIVA = "+reservaColectiva+" ;";
		PreparedStatement prepStmt = conn.prepareStatement(sql.toString());
		recursos.add(prepStmt);
		ResultSet rs =prepStmt.executeQuery();
		
		while (rs.next()) {
			reservas.add(convertResultSetToReserva(rs,daoAlojamiento,daoCliente));
		}
		
		for (int i = 0; i < reservas.size(); i++) {
			
			Reserva actual = reservas.get(i);
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date1 = new Date();
			String x = dateFormat.format(date1);
			String fecha = x;
			double costoDef = actual.getCostoDefinitivo();
			if(actual.getTiempoOportunoCan().compareTo(date1)>0)
			{
				costoDef = costoDef *0.1;
			}
			else if (actual.getTiempoOportunoCan().compareTo(date1)<0 && actual.getFechaInicio().compareTo(date1)>0)
			{
				costoDef = costoDef *0.3;
			}
			else
				costoDef = costoDef *0.5;
			costoDefinitivo += costoDef;
			String sql2 = "UPDATE "+ USUARIO +".RESERVAS SET "+"CANCELADA = 'T' , FECHA_CANCELACION = '"+fecha+"' , COSTO_DEFINITIVO = "+costoDef+" , TERMINADA = 'F' WHERE ID ="+actual.getId();
			System.out.println(sql2);
			PreparedStatement prepStmt2 = conn.prepareStatement(sql2.toString());
			recursos.add(prepStmt2);
			prepStmt2.executeQuery();
			prepStmt2.close();
			
		}
		
		return null;
		
	}
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
	 * Metodo que transforma el resultado obtenido de una consulta SQL (sobre la tabla reservaS y RELACIONES) en una instancia de la clase Reserva.
	 * @param resultSet ResultSet con la informacion de un Reserva que se obtuvo de la base de datos.
	 * @return Reserva cuyos atributos corresponden a los valores asociados a un registro particular de la tabla reservaS y RELACIONES.
	 * @throws SQLException Si existe algun problema al extraer la informacion del ResultSet.
	 */
	public Reserva convertResultSetToReserva(ResultSet resultSet, DAOAlojamiento daoAlojamiento, DAOCliente daoCliente) throws SQLException, Exception {

		long id = Long.parseLong(resultSet.getString("ID"));
		long idColectiva = Long.parseLong(resultSet.getString("ID_COLECTIVA"));
		int numDias = Integer.parseInt(resultSet.getString("NUM_DIAS"));
		String fechaInicio = resultSet.getString("FECHA_INICIO");

		String fif = fechaInicio.substring(2, 10);
		String [] array = fif.split("-");
		int anho = Integer.parseInt(array[0])+100;
		int mes = Integer.parseInt(array[1])-1;
		int dia = Integer.parseInt(array[2]);
		Date date1 = new Date(anho, mes, dia);

		String fechaFin = resultSet.getString("FECHA_FIN");
		String fff = fechaFin.substring(2, 10);
		String [] array2 = fff.split("-");
		int anho2 = Integer.parseInt(array2[0])+100;
		int mes2 = Integer.parseInt(array2[1])-1;
		int dia2 = Integer.parseInt(array2[2]);
		Date date2 = new Date(anho2, mes2, dia2);

		int numPersonas = Integer.parseInt(resultSet.getString("NUM_PERSONAS"));
		boolean cancelada = false;
		if(resultSet.getString("CANCELADA").equals("T"))
			cancelada = true;
		String fechaCancelacion = resultSet.getString("FECHA_CANCELACION");
		Date date3 = null;
		if(fechaCancelacion != null)
		{
			String fcf = fechaCancelacion.substring(2, 10);
			String [] array3 = fcf.split("-");
			int anho3 = Integer.parseInt(array3[0])+100;
			int mes3 = Integer.parseInt(array3[1])-1;
			int dia3 = Integer.parseInt(array3[2]);
			date3 = new Date(anho3, mes3, dia3);
		}

		double costoFinal = Double.parseDouble(resultSet.getString("COSTO_DEFINITIVO"));
		String tiempoOportuno = resultSet.getString("TIEMPO_OPORTUNO");
		String ftf = tiempoOportuno.substring(2, 10);
		String [] array4 = ftf.split("-");
		int anho4 = Integer.parseInt(array4[0])+100;
		int mes4 = Integer.parseInt(array4[1])-1;
		int dia4 = Integer.parseInt(array4[2]);
		Date date4 = new Date(anho4, mes4, dia4);

		boolean terminada = false;
		if(resultSet.getString("TERMINADA").equals("T"))
			terminada = true;
		boolean colectiva = false;
		if(resultSet.getString("COLECTIVA").equals("T"))
			colectiva = true;
		long idAlojamiento = Long.parseLong(resultSet.getString("ID_ALOJAMIENTO"));
		long idCliente = Long.parseLong(resultSet.getString("ID_CLIENTE"));

		Alojamiento alojamiento = daoAlojamiento.findAlojamientoById(idAlojamiento);
		Cliente cliente = daoCliente.findClienteById(idCliente);
		ArrayList <Servicio>servicios = new ArrayList<Servicio>();
		Reserva ope = new Reserva(id, numDias, date1, date2, cancelada, numPersonas, date3, costoFinal, terminada, date4, alojamiento, cliente, colectiva , idColectiva, servicios);

		return ope;
	}
}
