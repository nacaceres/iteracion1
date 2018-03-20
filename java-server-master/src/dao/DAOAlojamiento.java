package dao;

import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import vos.*;

/**
 * Clase DAO que se conecta la base de datos usando JDBC para resolver los requerimientos de la aplicacion
 */
public class DAOAlojamiento {

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
	 * Metodo constructor de la clase DAOOperador <br/>
	 */
	public DAOAlojamiento() {
		recursos = new ArrayList<Object>();
	}

	//----------------------------------------------------------------------------------------------------------------------------------
	// METODOS DE COMUNICACION CON LA BASE DE DATOS
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Metodo que obtiene la informacion de todos los Alojamientos en la Base de Datos <br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>
	 * @return	lista con la informacion de todos los Alojamientos que se encuentran en la Base de Datos
	 * @throws SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public ArrayList<Alojamiento> getAlojamientos() throws SQLException, Exception {
		ArrayList<Alojamiento> Alojamientos = new ArrayList<Alojamiento>();

		String sql = String.format("SELECT * FROM %1$s.ALOJAMIENTOS", USUARIO);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();


		while (rs.next()) {
			Alojamientos.add(convertResultSetToAlojamiento(rs));
		}
		return Alojamientos;
	}



	/**
	 * Metodo que obtiene la informacion del Alojamiento en la Base de Datos que tiene el identificador dado por parametro<br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/> 
	 * @param id el identificador del Alojamiento
	 * @return la informacion del Alojamiento que cumple con los criterios de la sentecia SQL
	 * 			Null si no existe el bebedor conlos criterios establecidos
	 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public Alojamiento findAlojamientoById(Long id) throws SQLException, Exception 
	{
		Alojamiento Alojamiento = null;

		String sql = String.format("SELECT * FROM %1$s.AlojamientoS WHERE ID = %2$d", USUARIO, id); 

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if(rs.next()) {
			Alojamiento = convertResultSetToAlojamiento(rs);
		}

		return Alojamiento;
	}

	/**
	 * Metodo que agregar la informacion de un nuevo Alojamiento en la Base de Datos a partir del parametro ingresado<br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>  
	 * @param Alojamiento Alojamiento que desea agregar a la Base de Datos
	 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public void addAlojamiento (Alojamiento Alojamiento) throws SQLException, Exception {
		// Se asume que el operador ya existe.
		// El alojamiento empieza sin reservas.
		String var = "F";
		if(Alojamiento.isVigente())
			var = "T";
		String fecha = Alojamiento.getFechaRetiro().getDate() +"/" +Alojamiento.getFechaRetiro().getMonth()+"/" +Alojamiento.getFechaRetiro().getYear();
		String sql2 = "INSERT INTO "+ USUARIO+".ALOJAMIENTOS (ID, UBICACION, COSTO_BASICO, CAPACIDAD, VIGENTE, FECHA_RETIRO, TIPO , ID_OPERADOR) VALUES ("+ 

				Alojamiento.getId()+", '"+
				Alojamiento.getUbicacion()+"' ,"+
				Alojamiento.getCostoBasico()+","+
				Alojamiento.getCapacidad()+", '"+
				var+"' , '"+
				fecha+"' , '"+
				Alojamiento.getTipo()+"' ,"+
				Alojamiento.getOperador().getId()+")";
		PreparedStatement prepStmt2 = conn.prepareStatement(sql2);
		recursos.add(prepStmt2);
		prepStmt2.executeQuery();



		//Falta agregar todos los servicios.
		//Falta agregar todos los contratos.
	}

	/**
	 * Metodo que agregar la informacion de un nuevo Apartamento en la Base de Datos a partir del parametro ingresado<br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>  
	 * @param Apartamento Apartamento que desea agregar a la Base de Datos
	 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public void addApartamento (Apartamento Apartamento) throws SQLException, Exception {
		// Se asume que el operador ya existe.
		// El Apartamento empieza sin reservas.
		String var = "F";
		if(Apartamento.isVigente())
			var = "T";
		String fecha = Apartamento.getFechaRetiro().getDate() +"/" +Apartamento.getFechaRetiro().getMonth()+"/" +Apartamento.getFechaRetiro().getYear();
		String sql2 = "INSERT INTO "+ USUARIO+".ALOJAMIENTOS (ID, UBICACION, COSTO_BASICO, CAPACIDAD, VIGENTE, FECHA_RETIRO, TIPO , ID_OPERADOR) VALUES ("+ 

				Apartamento.getId()+", '"+
				Apartamento.getUbicacion()+"' ,"+
				Apartamento.getCostoBasico()+","+
				Apartamento.getCapacidad()+", '"+
				var+"' , '"+
				fecha+"' , '"+
				Apartamento.getTipo()+"' ,"+
				Apartamento.getOperador().getId()+")";
		System.out.println(sql2);
		PreparedStatement prepStmt2 = conn.prepareStatement(sql2);
		recursos.add(prepStmt2);
		prepStmt2.executeQuery();

		String var2 = "F";
		if(Apartamento.isAmoblado())
			var2 = "T";
		String sql3 = "INSERT INTO "+ USUARIO+".APARTAMENTOS (ID, AMOBLADO ) VALUES ("+ 

				Apartamento.getId()+", '"+
				var2+"' )";
		PreparedStatement prepStmt3 = conn.prepareStatement(sql3);
		recursos.add(prepStmt3);
		prepStmt3.executeQuery();

		//Falta agregar todos los servicios.
		//Falta agregar todos los contratos.
	}
	/**
	 * Metodo que agregar la informacion de un nuevo HabHostal en la Base de Datos a partir del parametro ingresado<br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>  
	 * @param HabHostal HabHostal que desea agregar a la Base de Datos
	 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public void addHabHostal (HabHostal HabHostal) throws SQLException, Exception {
		// Se asume que el operador ya existe.
		// El HabHostal empieza sin reservas.
		String var = "F";
		if(HabHostal.isVigente())
			var = "T";
		String fecha = HabHostal.getFechaRetiro().getDate() +"/" +HabHostal.getFechaRetiro().getMonth()+"/" +HabHostal.getFechaRetiro().getYear();
		String sql2 = "INSERT INTO "+ USUARIO+".ALOJAMIENTOS (ID, UBICACION, COSTO_BASICO, CAPACIDAD, VIGENTE, FECHA_RETIRO, TIPO , ID_OPERADOR) VALUES ("+ 

				HabHostal.getId()+", '"+
				HabHostal.getUbicacion()+"' ,"+
				HabHostal.getCostoBasico()+","+
				HabHostal.getCapacidad()+", '"+
				var+"' , '"+
				fecha+"' , '"+
				HabHostal.getTipo()+"' ,"+
				HabHostal.getOperador().getId()+")";
		System.out.println(sql2);
		PreparedStatement prepStmt2 = conn.prepareStatement(sql2);
		recursos.add(prepStmt2);
		prepStmt2.executeQuery();

		String var2 = "F";
		String fecha2 = HabHostal.getHorarioApertura().getYear() +"/"+ HabHostal.getHorarioApertura().getMonth()+"/" +HabHostal.getHorarioApertura().getDate()+":"+HabHostal.getHorarioApertura().getHours()+":"+HabHostal.getHorarioApertura().getMinutes()+":"+HabHostal.getHorarioApertura().getSeconds();
		String fecha3 = HabHostal.getHorarioCierre().getYear() +"/"+ HabHostal.getHorarioCierre().getMonth()+"/" +HabHostal.getHorarioCierre().getDate()+":"+HabHostal.getHorarioCierre().getHours()+":"+HabHostal.getHorarioCierre().getMinutes()+":"+HabHostal.getHorarioCierre().getSeconds();
		String fecha2final = "to_date('"+fecha2+"', 'yyyy/mm/dd:hh:mi:ss')";
		String fecha3final = "to_date('"+fecha3+"', 'yyyy/mm/dd:hh:mi:ss')";
		if(HabHostal.isCompartida())
			var2 = "T";
		String sql3 = "INSERT INTO "+ USUARIO+".HABITACIONES_HOSTAL (ID, HORARIO_APERTURA, HORARIO_CIERRE, COMPARTIDA ) VALUES ("+ 

				HabHostal.getId() +" , "+
				fecha2final+" , "+
				fecha3final+" , '"+
				var2+"' )";
		System.out.println(sql3);
		PreparedStatement prepStmt3 = conn.prepareStatement(sql3);
		recursos.add(prepStmt3);
		prepStmt3.executeQuery();

		//Falta agregar todos los servicios.
		//Falta agregar todos los contratos.
	}
	/**
	 * Metodo que agregar la informacion de un nuevo HabHotel en la Base de Datos a partir del parametro ingresado<br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>  
	 * @param HabHotel HabHotel que desea agregar a la Base de Datos
	 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public void addHabHotel (HabHotel HabHotel) throws SQLException, Exception {
		// Se asume que el operador ya existe.
		// El HabHotel empieza sin reservas.
		String var = "F";
		if(HabHotel.isVigente())
			var = "T";
		String fecha = HabHotel.getFechaRetiro().getDate() +"/" +HabHotel.getFechaRetiro().getMonth()+"/" +HabHotel.getFechaRetiro().getYear();
		String sql2 = "INSERT INTO "+ USUARIO+".ALOJAMIENTOS (ID, UBICACION, COSTO_BASICO, CAPACIDAD, VIGENTE, FECHA_RETIRO, TIPO , ID_OPERADOR) VALUES ("+ 

				HabHotel.getId()+", '"+
				HabHotel.getUbicacion()+"' ,"+
				HabHotel.getCostoBasico()+","+
				HabHotel.getCapacidad()+", '"+
				var+"' , '"+
				fecha+"' , '"+
				HabHotel.getTipo()+"' ,"+
				HabHotel.getOperador().getId()+")";
		System.out.println(sql2);
		PreparedStatement prepStmt2 = conn.prepareStatement(sql2);
		recursos.add(prepStmt2);
		prepStmt2.executeQuery();

		
		String sql3 = "INSERT INTO "+ USUARIO+".HABITACIONES_HOTEL (ID, TIPO_HABITACION ) VALUES ("+ 

				HabHotel.getId()+", '"+
				HabHotel.getTipoHabitacion()+"' )";
		PreparedStatement prepStmt3 = conn.prepareStatement(sql3);
		recursos.add(prepStmt3);
		prepStmt3.executeQuery();

		//Falta agregar todos los servicios.
		//Falta agregar todos los contratos.
	}
	/**
	 * Metodo que agregar la informacion de un nuevo habUniversitaria en la Base de Datos a partir del parametro ingresado<br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>  
	 * @param habUniversitaria habUniversitaria que desea agregar a la Base de Datos
	 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public void addHabUniversitaria (HabUniversitaria habUniversitaria) throws SQLException, Exception {
		// Se asume que el operador ya existe.
		// El habUniversitaria empieza sin reservas.
		String var = "F";
		if(habUniversitaria.isVigente())
			var = "T";
		String fecha = habUniversitaria.getFechaRetiro().getDate() +"/" +habUniversitaria.getFechaRetiro().getMonth()+"/" +habUniversitaria.getFechaRetiro().getYear();
		String sql2 = "INSERT INTO "+ USUARIO+".ALOJAMIENTOS (ID, UBICACION, COSTO_BASICO, CAPACIDAD, VIGENTE, FECHA_RETIRO, TIPO , ID_OPERADOR) VALUES ("+ 

				habUniversitaria.getId()+", '"+
				habUniversitaria.getUbicacion()+"' ,"+
				habUniversitaria.getCostoBasico()+","+
				habUniversitaria.getCapacidad()+", '"+
				var+"' , '"+
				fecha+"' , '"+
				habUniversitaria.getTipo()+"' ,"+
				habUniversitaria.getOperador().getId()+")";
		System.out.println(sql2);
		PreparedStatement prepStmt2 = conn.prepareStatement(sql2);
		recursos.add(prepStmt2);
		prepStmt2.executeQuery();

		
		String sql3 = "INSERT INTO "+ USUARIO+".HABITACIONES_VIV_UNI (ID, DURACION_DE_HAB ) VALUES ("+ 

				habUniversitaria.getId()+", "+
				habUniversitaria.getDuracionDeHab()+" )";
		PreparedStatement prepStmt3 = conn.prepareStatement(sql3);
		recursos.add(prepStmt3);
		prepStmt3.executeQuery();

		//Falta agregar todos los servicios.
		//Falta agregar todos los contratos.
	}
	/**
	 * Metodo que agregar la informacion de un nuevo Vivienda en la Base de Datos a partir del parametro ingresado<br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>  
	 * @param Vivienda Vivienda que desea agregar a la Base de Datos
	 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public void addVivienda (Vivienda Vivienda) throws SQLException, Exception {
		// Se asume que el operador ya existe.
		// El Vivienda empieza sin reservas.
		String var = "F";
		if(Vivienda.isVigente())
			var = "T";
		String fecha = Vivienda.getFechaRetiro().getDate() +"/" +Vivienda.getFechaRetiro().getMonth()+"/" +Vivienda.getFechaRetiro().getYear();
		String sql2 = "INSERT INTO "+ USUARIO+".ALOJAMIENTOS (ID, UBICACION, COSTO_BASICO, CAPACIDAD, VIGENTE, FECHA_RETIRO, TIPO , ID_OPERADOR) VALUES ("+ 

				Vivienda.getId()+", '"+
				Vivienda.getUbicacion()+"' ,"+
				Vivienda.getCostoBasico()+","+
				Vivienda.getCapacidad()+", '"+
				var+"' , '"+
				fecha+"' , '"+
				Vivienda.getTipo()+"' ,"+
				Vivienda.getOperador().getId()+")";
		System.out.println(sql2);
		PreparedStatement prepStmt2 = conn.prepareStatement(sql2);
		recursos.add(prepStmt2);
		prepStmt2.executeQuery();

		String var2 = "F";
		if(Vivienda.isCedido())
			var2= "T";
		String var3 = "F";
		if(Vivienda.isCompartido())
			var3= "T";
		String sql3 = "INSERT INTO "+ USUARIO+".VIVIENDAS (ID, NUM_HABITACIONES,CEDIDO,COMPARTIDO ) VALUES ("+ 

				Vivienda.getId()+", "+
				Vivienda.getNumHabitaciones()+ ", '"
				+var2+"' , '"
				+var3+
				"' )";
		PreparedStatement prepStmt3 = conn.prepareStatement(sql3);
		recursos.add(prepStmt3);
		prepStmt3.executeQuery();

		//Falta agregar todos los servicios.
		//Falta agregar todos los contratos.
	}
	/**
	 * Metodo que retira la oferta de alojamiento en la Base de Datos que tiene el identificador dado por parametro<br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>  
	 * @param Alojamiento alojamiento que se desea actualizar a la Base de Datos
	 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public void retirarOfertaAlojamiento(Alojamiento alojamiento) throws SQLException, Exception {
		
		String fecha = null;
		String sentencia ="SELECT MAX (RE.FECHA_FIN) AS MAX_DATE FROM RESERVAS RE WHERE RE.ID_ALOJAMIENTO="+alojamiento.getId();
		PreparedStatement prepStmt2 = conn.prepareStatement(sentencia);
		recursos.add(prepStmt2);
		prepStmt2.executeQuery();
		ResultSet rs = prepStmt2.executeQuery();
		if(rs.next()) {
			String fechaUltimaReserva = rs.getString("MAX_DATE");
			String prueba = fechaUltimaReserva.substring(2, 10);
			String [] array = prueba.split("-");
			int anho = Integer.parseInt(array[0])+100;
			int mes = Integer.parseInt(array[1])-1;
			int dia = Integer.parseInt(array[2]);
			Date date = new Date(anho, mes, dia);
		}
		
		StringBuilder sql = new StringBuilder();
		sql.append(String.format("UPDATE %s.ALOJAMIENTOS SET ", USUARIO));
		sql.append(String.format("VIGENTE = 'F' , FECHA_RETIRO = '%1$s'",  fecha));
		sql.append(String.format(" WHERE ID = %d ", alojamiento.getId()));
		System.out.println(sql);
		PreparedStatement prepStmt = conn.prepareStatement(sql.toString());
		recursos.add(prepStmt);
		prepStmt.executeQuery();
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
	 * Metodo que transforma el resultado obtenido de una consulta SQL (sobre la tabla AlojamientoS y RELACIONES) en una instancia de la clase Alojamiento.
	 * @param resultSet ResultSet con la informacion de un Alojamiento que se obtuvo de la base de datos.
	 * @return Alojamiento cuyos atributos corresponden a los valores asociados a un registro particular de la tabla AlojamientoS y RELACIONES.
	 * @throws SQLException Si existe algun problema al extraer la informacion del ResultSet.
	 */
	public Alojamiento convertResultSetToAlojamiento(ResultSet resultSet) throws SQLException, Exception {

		long id = Long.parseLong(resultSet.getString("ID"));
		String ubicacion = resultSet.getString("UBICACION");
		double costoBasico = Double.parseDouble(resultSet.getString("COSTO_BASICO"));
		int capacidad = Integer.parseInt(resultSet.getString("CAPACIDAD"));
		boolean vigente = resultSet.getString("VIGENTE").equals("T");

		String fech = resultSet.getString("FECHA_RETIRO");
		String prueba = fech.substring(2, 10);
		System.out.println(fech);
		System.out.println(prueba);
		String [] array = prueba.split("-");
		int anho = Integer.parseInt(array[0])+2000;
		int mes = Integer.parseInt(array[1]);
		int dia = Integer.parseInt(array[2]);
		System.out.println(dia);
		System.out.println(mes);
		System.out.println(anho);
		Date date = new Date(anho, mes, dia);

		String tipo = resultSet.getString("TIPO");
		Long idOp = Long.parseLong(resultSet.getString("ID_OPERADOR"));

		String sql = String.format("SELECT * FROM %1$s.OPERADORES WHERE ID = %2$d", USUARIO, idOp); 

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		rs.next();
		long idOpera = Long.parseLong(rs.getString("ID"));
		String tipoOpera = rs.getString("TIPO_ID");
		String nombre = rs.getString("NOMBRE");
		String contacto = rs.getString("CONTACTO");
		long idRelacion = Long.parseLong(rs.getString("ID_RELACION"));
		String sql1 = String.format("SELECT * FROM %1$s.RELACIONES WHERE ID = %2$d", USUARIO, idRelacion);

		PreparedStatement prepStmt1 = conn.prepareStatement(sql1);
		recursos.add(prepStmt1);
		ResultSet rs1 = prepStmt1.executeQuery();
		rs1.next();
		String tipo2 = rs1.getString("TIPO");
		int carnet = Integer.parseInt(rs1.getString("CARNET"));

		RelacionUniandes rela = new RelacionUniandes(idRelacion, tipo2, carnet);
		ArrayList <Alojamiento> alojamientos = new ArrayList <Alojamiento>();
		Operador ope = new Operador(idOpera, tipoOpera, nombre, contacto, alojamientos, rela);
		ArrayList <Servicio> servicios = new ArrayList <Servicio>();
		//Falta agregar todos los servicios
		ArrayList <Reserva> reservas = new ArrayList <Reserva>();
		//Falta agregar todas las reservas
		Alojamiento alojamiento = new Alojamiento(id, ubicacion, costoBasico, capacidad,  vigente, date, ope, servicios, reservas, tipo);


		return alojamiento;
	}
}
