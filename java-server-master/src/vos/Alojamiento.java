package vos;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Date;

public class Alojamiento
{
	//----------------------------------------------------------------------------------------------------------------------------------
	// CONSTANTES que modelan los tipos de alojamiento que existen.
	//----------------------------------------------------------------------------------------------------------------------------------

	private static final String APARTAMENTO = "APARTAMENTO";
	private static final String HAB_HOSTAL = "HAB HOSTAL";
	private static final String HAB_HOTEL = "HAB HOTEL";
	private static final String VIVIENDA = "VIVIENDA";
	private static final String HAB_UNIVERSITARIA = "HAB UNIVERSITARIA";
	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Id del alojamiento
	 */
	@JsonProperty( value = "id")
	private Long id;

	/**
	 * Id del alojamiento
	 */
	@JsonProperty( value = "tipo")
	private String tipo;
	
	/**
	 * Ubicacion del alojamiento
	 */
	@JsonProperty( value = "ubicacion")
	private String ubicacion;

	/**
	 * Costo basico del alojamiento
	 */
	@JsonProperty( value = "costoBasico")
	private double costoBasico;

	/**
	 * Numero de dias que ha sido alquilado en toda la historia de la aplicacion
	 */
	@JsonProperty( value = "diasAlquilado")
	private int diasAlquilado;

	/**
	 * Capacidad del alojamiento
	 */
	@JsonProperty( value = "capacidad")
	private int capacidad;

	/**
	 * Total de personas que han pasado por el alojamiento
	 */
	@JsonProperty( value = "numPersonas")
	private int numPersonas;

	/**
	 * Hace referencia a si el alojamiento ha sido retirado por el operardor, true si no ha sido retirada, false de lo contrario
	 */
	@JsonProperty( value = "vigente")
	private boolean vigente;

	/**
	 * Fecha en la que se hizo el retiro del alojamiento, siemrpe y cuando vigente sea false.
	 */
	@JsonProperty( value = "fechaRetiro")
	private Date fechaRetiro;

	/**
	 * Veces que ha sido alquilado el alojamiento.
	 */
	@JsonProperty( value = "vecesAlquilado")
	private int vecesAlquilado;

	/**
	 * Operador del alojamiento
	 */
	@JsonProperty( value = "operador")
	private Operador operador;

	/**
	 * Servicios que provee el alojamiento
	 */
	@JsonProperty( value = "servicios")
	private List<Servicio> servicios;

	/**
	 * Todas las reservas del alojamiento
	 */
	@JsonProperty( value = "reservas")
	private List<Reserva> reservas;

	//----------------------------------------------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Contructor de alojamiento.
	 * <b>post: </b> Crea un alojamiento con los valores que entran por parametro
	 */
	public Alojamiento(@JsonProperty( value = "id")Long pId,
			@JsonProperty( value = "ubicacion")String pUbicacion,
			@JsonProperty( value = "costoBasico")double pCostoBasico,
			@JsonProperty( value = "diasAlquilado")int pDiasAlquilado,
			@JsonProperty( value = "capacidad")int pCapacidad,
			@JsonProperty( value = "numPersonas") int pNumPersonas,
			@JsonProperty( value = "vigente")boolean pVigente,
			@JsonProperty( value = "fechaRetiro")Date pFechaRetiro,
			@JsonProperty( value = "vecesAlquilado")int pVecesAlquilado,
			@JsonProperty( value = "operador")Operador pOperador,
			@JsonProperty( value = "servicios") List<Servicio> pServicios,
			@JsonProperty( value = "reservas") List<Reserva> pReservas,
			@JsonProperty( value = "tipo") String pTipo)
	{
		id = pId;
		ubicacion = pUbicacion;
		costoBasico = pCostoBasico;
		diasAlquilado = pDiasAlquilado;
		capacidad = pCapacidad;
		numPersonas = pNumPersonas;
		vigente = pVigente;
		fechaRetiro = pFechaRetiro;
		vecesAlquilado = pVecesAlquilado;
		operador = pOperador;
		servicios = pServicios;
		reservas = pReservas;
		tipo = pTipo;
	}
	//----------------------------------------------------------------------------------------------------------------------------------
	// METODOS
	//----------------------------------------------------------------------------------------------------------------------------------

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public double getCostoBasico() {
		return costoBasico;
	}

	public void setCostoBasico(double costoBasico) {
		this.costoBasico = costoBasico;
	}

	public int getDiasAlquilado() {
		return diasAlquilado;
	}

	public void setDiasAlquilado(int diasAlquilado) {
		this.diasAlquilado = diasAlquilado;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public int getNumPersonas() {
		return numPersonas;
	}

	public void setNumPersonas(int numPersonas) {
		this.numPersonas = numPersonas;
	}

	public boolean isVigente() {
		return vigente;
	}

	public void setVigente(boolean vigente) {
		this.vigente = vigente;
	}

	public Date getFechaRetiro() {
		return fechaRetiro;
	}

	public void setFechaRetiro(Date fechaRetiro) {
		this.fechaRetiro = fechaRetiro;
	}

	public int getVecesAlquilado() {
		return vecesAlquilado;
	}

	public void setVecesAlquilado(int vecesAlquilado) {
		this.vecesAlquilado = vecesAlquilado;
	}

	public Operador getOperador() {
		return operador;
	}

	public void setOperador(Operador operador) {
		this.operador = operador;
	}

	public List<Servicio> getServicios() {
		return servicios;
	}

	public void setServicios(List<Servicio> servicios) {
		this.servicios = servicios;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	
}

