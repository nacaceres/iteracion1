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
		rs.close();
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
		//String fecha = Alojamiento.getFechaRetiro().getDate() +"/" +Alojamiento.getFechaRetiro().getMonth()+"/" +Alojamiento.getFechaRetiro().getYear();
		String sql2 = "INSERT INTO "+ USUARIO+".ALOJAMIENTOS (ID, UBICACION, COSTO_BASICO, CAPACIDAD, VIGENTE, FECHA_RETIRO, TIPO , ID_OPERADOR) VALUES ("+ 

				Alojamiento.getId()+", '"+
				Alojamiento.getUbicacion()+"' ,"+
				Alojamiento.getCostoBasico()+","+
				Alojamiento.getCapacidad()+", '"+
				var+"' , "+
				null+" , '"+
				Alojamiento.getTipo()+"' ,"+
				Alojamiento.getOperador().getId()+")";
		System.out.println(sql2);
		PreparedStatement prepStmt2 = conn.prepareStatement(sql2);
		recursos.add(prepStmt2);
		prepStmt2.executeQuery();

		List<Servicio> lista = Alojamiento.getServicios();
		for (int i = 0; i < lista.size(); i++) {
			Servicio actual = lista.get(i);
			String sql3 = "INSERT INTO "+USUARIO+".SERVICIOS (ID, NOMBRE, DESCRIPCION, COSTO_ADICIONAL) VALUES ("+
					actual.getId()+", '"+
					actual.getNombre()+"' , '"+
					actual.getDescripcion()+"', "+
					actual.getCostoAdicional() +" )";
			System.out.println(sql3);
			PreparedStatement prepStmt3 = conn.prepareStatement(sql3);
			recursos.add(prepStmt3);
			prepStmt3.executeQuery();

			String sql4 = "INSERT INTO " +USUARIO +".SERVICIOS_OFRECIDOS (ID_ALOJAMIENTO , ID_SERVICIO) VALUES ("+
					Alojamiento.getId()+" , "+
					actual.getId()+")";
			System.out.println(sql4);
			PreparedStatement prepStmt4 = conn.prepareStatement(sql4);
			recursos.add(prepStmt4);
			prepStmt4.executeQuery();
		}

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
		String sql2 = "INSERT INTO "+ USUARIO+".ALOJAMIENTOS (ID, UBICACION, COSTO_BASICO, CAPACIDAD, VIGENTE, FECHA_RETIRO, TIPO , ID_OPERADOR) VALUES ("+ 

				Apartamento.getId()+", '"+
				Apartamento.getUbicacion()+"' ,"+
				Apartamento.getCostoBasico()+","+
				Apartamento.getCapacidad()+", '"+
				var+"' , "+
				null+" , '"+
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

		List<Servicio> lista = Apartamento.getServicios();
		for (int i = 0; i < lista.size(); i++) {
			Servicio actual = lista.get(i);
			String sql5 = "INSERT INTO "+USUARIO+".SERVICIOS (ID, NOMBRE, DESCRIPCION, COSTO_ADICIONAL) VALUES ("+
					actual.getId()+", '"+
					actual.getNombre()+"' , '"+
					actual.getDescripcion()+"', "+
					actual.getCostoAdicional() +" )";
			System.out.println(sql5);
			PreparedStatement prepStmt5 = conn.prepareStatement(sql5);
			recursos.add(prepStmt5);
			prepStmt5.executeQuery();

			String sql4 = "INSERT INTO " +USUARIO +".SERVICIOS_OFRECIDOS (ID_ALOJAMIENTO , ID_SERVICIO) VALUES ("+
					Apartamento.getId()+" , "+
					actual.getId()+")";
			System.out.println(sql4);
			PreparedStatement prepStmt4 = conn.prepareStatement(sql4);
			recursos.add(prepStmt4);
			prepStmt4.executeQuery();
		}
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
		String sql2 = "INSERT INTO "+ USUARIO+".ALOJAMIENTOS (ID, UBICACION, COSTO_BASICO, CAPACIDAD, VIGENTE, FECHA_RETIRO, TIPO , ID_OPERADOR) VALUES ("+ 

				HabHostal.getId()+", '"+
				HabHostal.getUbicacion()+"' ,"+
				HabHostal.getCostoBasico()+","+
				HabHostal.getCapacidad()+", '"+
				var+"' , "+
				null+" , '"+
				HabHostal.getTipo()+"' ,"+
				HabHostal.getOperador().getId()+")";
		System.out.println(sql2);
		PreparedStatement prepStmt2 = conn.prepareStatement(sql2);
		recursos.add(prepStmt2);
		prepStmt2.executeQuery();

		String var2 = "F";
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		String fecha2 = dateFormat.format(HabHostal.getHorarioApertura())+":"+HabHostal.getHorarioApertura().getHours()+":"+HabHostal.getHorarioApertura().getMinutes()+":"+HabHostal.getHorarioApertura().getSeconds();
		String fecha3 = dateFormat.format(HabHostal.getHorarioCierre())+":"+HabHostal.getHorarioCierre().getHours()+":"+HabHostal.getHorarioCierre().getMinutes()+":"+HabHostal.getHorarioCierre().getSeconds();
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

		List<Servicio> lista = HabHostal.getServicios();
		for (int i = 0; i < lista.size(); i++) {
			Servicio actual = lista.get(i);
			String sql5 = "INSERT INTO "+USUARIO+".SERVICIOS (ID, NOMBRE, DESCRIPCION, COSTO_ADICIONAL) VALUES ("+
					actual.getId()+", '"+
					actual.getNombre()+"' , '"+
					actual.getDescripcion()+"', "+
					actual.getCostoAdicional() +" )";
			System.out.println(sql5);
			PreparedStatement prepStmt5 = conn.prepareStatement(sql5);
			recursos.add(prepStmt5);
			prepStmt5.executeQuery();

			String sql4 = "INSERT INTO " +USUARIO +".SERVICIOS_OFRECIDOS (ID_ALOJAMIENTO , ID_SERVICIO) VALUES ("+
					HabHostal.getId()+" , "+
					actual.getId()+")";
			System.out.println(sql4);
			PreparedStatement prepStmt4 = conn.prepareStatement(sql4);
			recursos.add(prepStmt4);
			prepStmt4.executeQuery();
		}
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
		String sql2 = "INSERT INTO "+ USUARIO+".ALOJAMIENTOS (ID, UBICACION, COSTO_BASICO, CAPACIDAD, VIGENTE, FECHA_RETIRO, TIPO , ID_OPERADOR) VALUES ("+ 

				HabHotel.getId()+", '"+
				HabHotel.getUbicacion()+"' ,"+
				HabHotel.getCostoBasico()+","+
				HabHotel.getCapacidad()+", '"+
				var+"' , "+
				null+" , '"+
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

		List<Servicio> lista = HabHotel.getServicios();
		for (int i = 0; i < lista.size(); i++) {
			Servicio actual = lista.get(i);
			String sql5 = "INSERT INTO "+USUARIO+".SERVICIOS (ID, NOMBRE, DESCRIPCION, COSTO_ADICIONAL) VALUES ("+
					actual.getId()+", '"+
					actual.getNombre()+"' , '"+
					actual.getDescripcion()+"', "+
					actual.getCostoAdicional() +" )";
			System.out.println(sql5);
			PreparedStatement prepStmt5 = conn.prepareStatement(sql5);
			recursos.add(prepStmt5);
			prepStmt5.executeQuery();

			String sql4 = "INSERT INTO " +USUARIO +".SERVICIOS_OFRECIDOS (ID_ALOJAMIENTO , ID_SERVICIO) VALUES ("+
					HabHotel.getId()+" , "+
					actual.getId()+")";
			System.out.println(sql4);
			PreparedStatement prepStmt4 = conn.prepareStatement(sql4);
			recursos.add(prepStmt4);
			prepStmt4.executeQuery();
		}
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
		String sql2 = "INSERT INTO "+ USUARIO+".ALOJAMIENTOS (ID, UBICACION, COSTO_BASICO, CAPACIDAD, VIGENTE, FECHA_RETIRO, TIPO , ID_OPERADOR) VALUES ("+ 

				habUniversitaria.getId()+", '"+
				habUniversitaria.getUbicacion()+"' ,"+
				habUniversitaria.getCostoBasico()+","+
				habUniversitaria.getCapacidad()+", '"+
				var+"' , "+
				null+" , '"+
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

		List<Servicio> lista = habUniversitaria.getServicios();
		for (int i = 0; i < lista.size(); i++) {
			Servicio actual = lista.get(i);
			String sql5 = "INSERT INTO "+USUARIO+".SERVICIOS (ID, NOMBRE, DESCRIPCION, COSTO_ADICIONAL) VALUES ("+
					actual.getId()+", '"+
					actual.getNombre()+"' , '"+
					actual.getDescripcion()+"', "+
					actual.getCostoAdicional() +" )";
			System.out.println(sql5);
			PreparedStatement prepStmt5 = conn.prepareStatement(sql5);
			recursos.add(prepStmt5);
			prepStmt5.executeQuery();

			String sql4 = "INSERT INTO " +USUARIO +".SERVICIOS_OFRECIDOS (ID_ALOJAMIENTO , ID_SERVICIO) VALUES ("+
					habUniversitaria.getId()+" , "+
					actual.getId()+")";
			System.out.println(sql4);
			PreparedStatement prepStmt4 = conn.prepareStatement(sql4);
			recursos.add(prepStmt4);
			prepStmt4.executeQuery();
		}
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
		String sql2 = "INSERT INTO "+ USUARIO+".ALOJAMIENTOS (ID, UBICACION, COSTO_BASICO, CAPACIDAD, VIGENTE, FECHA_RETIRO, TIPO , ID_OPERADOR) VALUES ("+ 

				Vivienda.getId()+", '"+
				Vivienda.getUbicacion()+"' ,"+
				Vivienda.getCostoBasico()+","+
				Vivienda.getCapacidad()+", '"+
				var+"' , "+
				null+" , '"+
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

		List<Servicio> lista = Vivienda.getServicios();
		for (int i = 0; i < lista.size(); i++) {
			Servicio actual = lista.get(i);
			String sql5 = "INSERT INTO "+USUARIO+".SERVICIOS (ID, NOMBRE, DESCRIPCION, COSTO_ADICIONAL) VALUES ("+
					actual.getId()+", '"+
					actual.getNombre()+"' , '"+
					actual.getDescripcion()+"', "+
					actual.getCostoAdicional() +" )";
			System.out.println(sql5);
			PreparedStatement prepStmt5 = conn.prepareStatement(sql5);
			recursos.add(prepStmt5);
			prepStmt5.executeQuery();

			String sql4 = "INSERT INTO " +USUARIO +".SERVICIOS_OFRECIDOS (ID_ALOJAMIENTO , ID_SERVICIO) VALUES ("+
					Vivienda.getId()+" , "+
					actual.getId()+")";
			System.out.println(sql4);
			PreparedStatement prepStmt4 = conn.prepareStatement(sql4);
			recursos.add(prepStmt4);
			prepStmt4.executeQuery();
		}
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


		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date1 = new Date();
		String x = dateFormat.format(date1);

		String fecha = x;

		String sentencia ="SELECT MAX (RE.FECHA_FIN) AS MAX_DATE FROM RESERVAS RE WHERE RE.ID_ALOJAMIENTO="+alojamiento.getId();
		PreparedStatement prepStmt2 = conn.prepareStatement(sentencia);
		recursos.add(prepStmt2);
		prepStmt2.executeQuery();
		ResultSet rs = prepStmt2.executeQuery();
		if(rs.next()) {
			String fechaUltimaReserva = rs.getString("MAX_DATE");
			if(fechaUltimaReserva!= null)
			{
				String prueba = fechaUltimaReserva.substring(2, 10);
				String [] array = prueba.split("-");
				int anho = Integer.parseInt(array[0])+100;
				int mes = Integer.parseInt(array[1])-1;
				int dia = Integer.parseInt(array[2]);
				Date date = new Date(anho, mes, dia);
				if(date.compareTo(date1)>0)
				{
					fecha = dia+"/"+(mes+1)+"/"+(anho+1900);
				}
			}
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
	/**
	 * Metodo que deshabilita la oferta de alojamiento en la Base de Datos que tiene el identificador dado por parametro<br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>  
	 * @param Alojamiento alojamiento que se desea actualizar a la Base de Datos
	 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public Informe deshabilitarOfertaAlojamiento(Alojamiento alojamiento, DAOReserva daoReserva, DAOCliente daoCliente) throws SQLException, Exception {

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date datex = new Date();
		String xm = dateFormat.format(datex);
		
		ArrayList<String> informe = new ArrayList<>();
		String sentencia ="SELECT * FROM  " +USUARIO +".RESERVAS RE WHERE RE.ID_ALOJAMIENTO = "+alojamiento.getId()+" AND CANCELADA = 'F' AND TERMINADA = 'F' ORDER BY RE.FECHA_INICIO";
		PreparedStatement prepStmt2 = conn.prepareStatement(sentencia);
		recursos.add(prepStmt2);
		prepStmt2.executeQuery();
		ResultSet rs = prepStmt2.executeQuery();
		while(rs.next()) {
			String idReservaActual = rs.getString("ID");
			String fechaInicio = "'"+ rs.getString("FECHA_INICIO")+"'";
			String fechaFin = "'"+rs.getString("FECHA_FIN")+"'";
			String sql3 = "SELECT * FROM "+USUARIO+".ALOJAMIENTOS ALO WHERE ROWNUM < 2 AND ALO.ID NOT IN ( SELECT RE.ID_ALOJAMIENTO FROM  "+USUARIO+".RESERVAS RE WHERE( RE.FECHA_INICIO  BETWEEN "+fechaInicio+" AND "+fechaFin+") OR  ( RE.FECHA_FIN  BETWEEN "+fechaInicio+" AND "+fechaFin+") OR (  RE.FECHA_INICIO <"+fechaInicio+"  AND   RE.FECHA_FIN>  "+fechaFin+") ) ;";
			PreparedStatement prepStmt3 = conn.prepareStatement(sql3);
			recursos.add(prepStmt3);
			ResultSet rs3 = prepStmt3.executeQuery();
			if(rs3.next())
			{
				String sql4 = "SELECT COUNT (*) AS NUMACTUAL FROM "+USUARIO+".RESERVAS;";
				PreparedStatement prepStmt4 = conn.prepareStatement(sql4);
				recursos.add(prepStmt4);
				ResultSet rs4 = prepStmt4.executeQuery();
				Long idReserva = Long.parseLong(rs4.getString("NUMACTUAL"));
				Alojamiento actual = convertResultSetToAlojamiento(rs3);
				int numPer = Integer.parseInt(rs.getString("NUM_PERSONAS"));
				String fechaCancelacion = rs.getString("FECHA_CANCELACION");
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

				String fif = fechaInicio.substring(2, 10);
				String [] array = fif.split("-");
				int anho = Integer.parseInt(array[0])+100;
				int mes = Integer.parseInt(array[1])-1;
				int dia = Integer.parseInt(array[2]);
				Date date1 = new Date(anho, mes, dia);

				String fff = fechaFin.substring(2, 10);
				String [] array2 = fff.split("-");
				int anho2 = Integer.parseInt(array2[0])+100;
				int mes2 = Integer.parseInt(array2[1])-1;
				int dia2 = Integer.parseInt(array2[2]);
				Date date2 = new Date(anho2, mes2, dia2);

				long idCliente = Long.parseLong(rs.getString("ID_CLIENTE"));
				Cliente cliente = daoCliente.findClienteById(idCliente);
				ArrayList<Servicio> servicios = new ArrayList<>();
				Reserva ope = new Reserva(idReserva+1, date1, date2, false , numPer , null, actual.getCostoBasico(), false, date3, actual, cliente, false , Long.parseLong("0"), servicios);
				daoReserva.addReserva(ope);
				String x = "La reserva No."+idReservaActual+"ha sido asignada al alojamiento con id "+actual.getId()+" con ubicacion "+actual.getUbicacion()+"con un nuevo id "+idReserva+1 +" debido a inconvenientes con el alojamiento " +alojamiento.getId()+ " quien se encuentra deshabilitado temporalmente";
				informe.add(x);
				prepStmt4.close();
				
			}
			else
			{
				String zx = "La reserva con id: "+ idReservaActual +" fue cancelada debido a inconvenitentes con el alojamiento y no se encontro una oferta disponible en las fechas";
				informe.add(zx);
			}
			prepStmt3.close();

		}

		StringBuilder sql = new StringBuilder();
		sql.append(String.format("UPDATE %s.ALOJAMIENTOS SET ", USUARIO));
		sql.append("VIGENTE = 'F' ");
		sql.append(String.format(" WHERE ID = %d ", alojamiento.getId()));
		System.out.println(sql);
		PreparedStatement prepStmt = conn.prepareStatement(sql.toString());
		recursos.add(prepStmt);
		prepStmt.executeQuery();
		String y = "Se ha deshabilitado el alojamiento: " +alojamiento.getId();
		informe.add(y);
		
		String sql6 = "UPDATE " +USUARIO +".RESERVAS SET CANCELADA = 'T' , FECHA_CANCELACION = '"+xm+"' WHERE RE.ID_ALOJAMIENTO = "+alojamiento.getId();
		PreparedStatement prepStmt6 = conn.prepareStatement(sql6);
		recursos.add(prepStmt6);
		prepStmt6.executeQuery();
		
		Informe inf = new Informe(informe);
		return inf;
	}
	
	/**
	 * Metodo que habilita la oferta de alojamiento en la Base de Datos que tiene el identificador dado por parametro<br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>  
	 * @param Alojamiento alojamiento que se desea habilitar a la Base de Datos
	 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public Informe habilitarOfertaAlojamiento(Alojamiento alojamiento) throws SQLException, Exception {

		StringBuilder sql = new StringBuilder();
		sql.append(String.format("UPDATE %s.ALOJAMIENTOS SET ", USUARIO));
		sql.append("VIGENTE = 'V' ");
		sql.append(String.format(" WHERE ID = %d ", alojamiento.getId()));
		System.out.println(sql);
		PreparedStatement prepStmt = conn.prepareStatement(sql.toString());
		recursos.add(prepStmt);
		prepStmt.executeQuery();
		ArrayList<String> noMasSistrans = new ArrayList<>();
		String y = "Se ha habilitado la oferta de alojamiento identificada con id: "+alojamiento.getId();
		noMasSistrans.add(y);
		Informe x = new Informe(noMasSistrans);
		return x;
	}
	
	/**
	 * Metodo que obtiene la informacion de todos los Alojamientos mas populares en la base de datos <br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>
	 * @return	lista con la informacion de todos los Alojamientos que se encuentran en la Base de Datos
	 * @throws SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public ArrayList<AlojamientosTop> getAlojamientosMasPopulares() throws SQLException, Exception {
		ArrayList<AlojamientosTop> Alojamientos = new ArrayList<AlojamientosTop>();

		String sql = "SELECT A.* FROM (SELECT ALO.ID as id_alojamiento,OPE.NOMBRE AS OPERADOR,ALO.UBICACION AS UBICACION ,COUNT(RE.ID) AS mas_reservado FROM (ISIS2304A431810.RESERVAS RE INNER JOIN ISIS2304A431810.ALOJAMIENTOS ALO ON RE.ID_ALOJAMIENTO=ALO.ID INNER JOIN ISIS2304A431810.OPERADORES OPE ON ALO.ID_OPERADOR=OPE.ID) GROUP BY ALO.ID,ALO.UBICACION, OPE.NOMBRE ORDER BY COUNT(RE.ID) DESC) A WHERE ROWNUM<20";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();


		while (rs.next()) {
			String id = rs.getString("ID_ALOJAMIENTO");
			String operador = rs.getString("OPERADOR");
			String ubicacion = rs.getString("UBICACION");
			String numReserva = rs.getString("MAS_RESERVADO");
			AlojamientosTop actual = new AlojamientosTop(id, operador, ubicacion, numReserva);
			Alojamientos.add(actual);		}
		return Alojamientos;
	}
	/**
	 * Metodo que obtiene la informacion de todos los Alojamientos con restriccion de fecha y servicios <br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>
	 * @return	lista con la informacion de todos los Alojamientos que se encuentran en la Base de Datos
	 * @throws SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public ArrayList<Alojamiento> getAlojamientosConRestriccion(Condiciones pCondiciones) throws SQLException, Exception {
		ArrayList<Alojamiento> Alojamientos = new ArrayList<Alojamiento>();
		if(pCondiciones.getFechaFin()!= null && pCondiciones!=null && pCondiciones.getServicios()!= null&& !pCondiciones.getServicios().isEmpty())
		{
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date fechaInicio = pCondiciones.getFechaInicio();
			Date fechaFin = pCondiciones.getFechaFin();
			String x1 = dateFormat.format(fechaInicio);
			String x2 = dateFormat.format(fechaFin);

			String sql = "SELECT * FROM ISIS2304A431810.ALOJAMIENTOS ALO WHERE ALO.ID NOT IN ( SELECT RE.ID_ALOJAMIENTO FROM  ISIS2304A431810.RESERVAS RE WHERE( RE.FECHA_INICIO  BETWEEN '"+x1+"' AND '"+x2+"')";
			String sql2	=	" ) AND ALO.ID IN ( SELECT SEO.ID_ALOJAMIENTO FROM  ISIS2304A431810.SERVICIOS_OFRECIDOS SEO INNER JOIN  ISIS2304A431810.SERVICIOS SE ON SE.ID=SEO.ID_SERVICIO WHERE ";
			String sql3 = "";
			for (int i = 1; i < pCondiciones.getServicios().size(); i++) {
				String actual = pCondiciones.getServicios().get(i).getNombre();
				sql3+=" SE.NOMBRE='"+actual+"'OR ";
			}
			String sql4 =  "SE.NOMBRE='"+pCondiciones.getServicios().get(0).getNombre()+"' )";
			System.out.println(sql+sql2+sql3+sql4);
			PreparedStatement prepStmt = conn.prepareStatement(sql+sql2+sql3+sql4);
			recursos.add(prepStmt);
			ResultSet rs = prepStmt.executeQuery();


			while (rs.next()) {
				Alojamientos.add(convertResultSetToAlojamiento(rs));
			}
		}
		else
			throw new Exception("Condiciones de busqueda invalidas");
		return Alojamientos;
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
		Date date = null;
		if(fech!=null)
		{
			String prueba = fech.substring(2, 10);
			System.out.println(fech);
			System.out.println(prueba);
			String [] array = prueba.split("-");
			int anho = Integer.parseInt(array[0])+100;
			int mes = Integer.parseInt(array[1])-1;
			int dia = Integer.parseInt(array[2]);
			System.out.println(dia);
			System.out.println(mes);
			System.out.println(anho);
			date = new Date(anho, mes, dia);
		}
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
		int carnet =0;
		if(rs1.getString("CARNET")!= null)
			carnet = Integer.parseInt(rs1.getString("CARNET"));

		RelacionUniandes rela = new RelacionUniandes(idRelacion, tipo2, carnet);
		ArrayList <Alojamiento> alojamientos = new ArrayList <Alojamiento>();
		Operador ope = new Operador(idOpera, tipoOpera, nombre, contacto, alojamientos, rela);
		ArrayList <Servicio> servicios = new ArrayList <Servicio>();
		//Falta agregar todos los servicios
		ArrayList <Reserva> reservas = new ArrayList <Reserva>();
		//Falta agregar todas las reservas
		Alojamiento alojamiento = new Alojamiento(id, ubicacion, costoBasico, capacidad,  vigente, date, ope, servicios, reservas, tipo);

		//Falta agregar los contratos y demas atributos segun el tipo
		return alojamiento;
	}
}
