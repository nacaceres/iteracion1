package vos;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Reserva
{
	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Numero de dias de la reserva
	 */
	@JsonProperty( value = "id")
	private Long id;
	/**
	 * Numero de dias de la reserva
	 */
	@JsonProperty( value = "numDias")
	private int numDias;

	/**
	 * Fecha de inicio de la reserva
	 */
	@JsonProperty( value = "fechaInicio")
	private Date fechaInicio;

	/**
	 * Fecha de fin de la reserva
	 */
	@JsonProperty( value = "fechaFin")
	private Date fechaFin;

	/**
	 * Estado de la reserva false si esta vigente true si ha sido cancelada.
	 */
	@JsonProperty( value = "cancelada")
	private boolean cancelada;

	/**
	 * Numero de personas que tomaran la reserva
	 */
	@JsonProperty( value = "numPersonas")
	private int numPersonas;

	/**
	 * Fecha en la que la reserva fue cancelada, null si no ha sido cancelada.
	 */
	@JsonProperty( value = "fechaCancelacion")
	private Date fechaCancelacion;

	/**
	 * Costo final de la reserva con servicios adicionales y numero de personas.
	 */
	@JsonProperty( value = "costoDefinitivo")
	private double costoDefinitivo;

	/**
	 * Indica si la reserva ya tuvo lugar y fue exitosa, true si ya paso, false de lo contrario.
	 */
	@JsonProperty( value = "terminada")
	private boolean terminada;

	/**
	 * Fecha en la que aun se puede pagar sin tener las respectivas sanciones.
	 */
	@JsonProperty( value = "tiempoOportunoCan")
	private Date tiempoOportunoCan;

	/**
	 * Alojamiento respectivo de la resera
	 */
	@JsonProperty( value = "alojamiento")
	private Alojamiento alojamiento;

	/**
	 * Cliente respectivo de la reserva
	 */
	@JsonProperty( value = "cliente")
	private Cliente cliente;
	
	/**
	 * Indica si la reserva hace parte de una reserva colectiva
	 */
	@JsonProperty( value = "colectiva")
	private boolean colectiva;

	/**
	 * Indica si la reserva hace parte de una reserva colectiva
	 */
	@JsonProperty( value = "idColectiva")
	private Long idColectiva;
	
	/**
	 * Lista que contiene los servicios adicionales de la reserva, estos son los servicios que no 
	 * incluye el alojamiento.
	 */
	@JsonProperty( value = "serviciosAdicionales")
	private List<Servicio> serviciosAdicionales;
	//----------------------------------------------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Contructor de reserva.
	 * <b>post: </b> Crea una reserva con los valores que entran por parametro
	 */
	public Reserva(@JsonProperty( value = "id") Long pId,
			@JsonProperty( value = "numDias") int pNumDias, 
			@JsonProperty( value = "fechaInicio") Date pFechaInicio,
			@JsonProperty( value = "fechaFin") Date pFechaFin,
			@JsonProperty( value = "cancelada") boolean pCancelada,
			@JsonProperty( value = "numPersonas")int pNumPersonas,
			@JsonProperty( value = "fechaCancelacion") Date pFechaCancelacion,
			@JsonProperty( value = "costoDefinitivo") double pCostoDefinitivo,
			@JsonProperty( value = "terminada") boolean pTerminada,
			@JsonProperty( value = "tiempoOportunoCan") Date pTiempoOportunoCan,
			@JsonProperty( value = "alojamiento") Alojamiento pAlojamiento,
			@JsonProperty( value = "cliente") Cliente pCliente,
			@JsonProperty( value = "colectiva") boolean pColectiva,
			@JsonProperty( value = "idColectiva") Long pIdColectiva,
			@JsonProperty( value = "serviciosAdicionales") List<Servicio> pServiciosAdicionales )
	{
		id=pId;
		numDias = pNumDias;
		fechaInicio = pFechaInicio;
		fechaFin = pFechaFin;
		cancelada = pCancelada;
		numPersonas = pNumPersonas;
		fechaCancelacion= pFechaCancelacion;
		costoDefinitivo = pCostoDefinitivo;
		terminada = pTerminada;
		tiempoOportunoCan = pTiempoOportunoCan;
		alojamiento = pAlojamiento;
		cliente = pCliente;
		colectiva = pColectiva;
		idColectiva = pIdColectiva;
		serviciosAdicionales = pServiciosAdicionales;
	}
	//----------------------------------------------------------------------------------------------------------------------------------
	// METODOS
	//----------------------------------------------------------------------------------------------------------------------------------

	public int getNumDias() {
		return numDias;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public boolean isCancelada() {
		return cancelada;
	}

	public int getNumPersonas() {
		return numPersonas;
	}

	public Date getFechaCancelacion() {
		return fechaCancelacion;
	}

	public double getCostoDefinitivo() {
		return costoDefinitivo;
	}

	public boolean isTerminada() {
		return terminada;
	}

	public Date getTiempoOportunoCan() {
		return tiempoOportunoCan;
	}

	public Alojamiento getAlojamiento() {
		return alojamiento;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public List<Servicio> getServiciosAdicionales() {
		return serviciosAdicionales;
	}

	public void setNumDias(int numDias) {
		this.numDias = numDias;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public void setCancelada(boolean cancelada) {
		this.cancelada = cancelada;
	}

	public void setNumPersonas(int numPersonas) {
		this.numPersonas = numPersonas;
	}

	public void setFechaCancelacion(Date fechaCancelacion) {
		this.fechaCancelacion = fechaCancelacion;
	}

	public void setCostoDefinitivo(double costoDefinitivo) {
		this.costoDefinitivo = costoDefinitivo;
	}

	public void setTerminada(boolean terminada) {
		this.terminada = terminada;
	}

	public void setTiempoOportunoCan(Date tiempoOportunoCan) {
		this.tiempoOportunoCan = tiempoOportunoCan;
	}

	public void setAlojamiento(Alojamiento alojamiento) {
		this.alojamiento = alojamiento;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setServiciosAdicionales(List<Servicio> serviciosAdicionales) {
		this.serviciosAdicionales = serviciosAdicionales;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isColectiva() {
		return colectiva;
	}

	public void setColectiva(boolean colectiva) {
		this.colectiva = colectiva;
	}

	public Long getIdColectiva() {
		return idColectiva;
	}

	public void setIdColectiva(Long idColectiva) {
		this.idColectiva = idColectiva;
	}

	
	
}

