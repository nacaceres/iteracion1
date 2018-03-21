package vos;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;


public class Contrato
{
	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Id del contrato
	 */
	@JsonProperty( value = "id")
	private Long id;
	/**
	 * Numero de dias de vigencia del contrato
	 */
	@JsonProperty( value = "numDias")
	private int numDias;

	/**
	 * Boolean que indica si se pago anticipadamente
	 */
	@JsonProperty( value = "pagoAnticipado")
	private boolean pagoAnticipado;

	/**
	 * boolean que indica si el contrato incluye servicios publicos
	 */
	@JsonProperty( value = "serviciosPublicos")
	private boolean serviciosPublicos;

	/**
	 * boolean que indica si el contrato incluye la administracion.
	 */
	@JsonProperty( value = "incluyeAdministracion")
	private boolean incluyeAdministracion;

	/**
	 * Fecha del contrato
	 */
	@JsonProperty( value = "fecha")
	private Date fecha;

	/**
	 * Costo definitivo del arrendamiento
	 */
	@JsonProperty( value = "costoDefinitivo")
	private double costoDefinitivo;

	/**
	 * Cliente del contrato
	 */
	@JsonProperty( value = "cliente")
	private Cliente cliente;

	/**
	 * Alojamiento del contrato
	 */
	@JsonProperty( value = "alojamiento")
	private Alojamiento alojamiento;

	//----------------------------------------------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Contructor de contrato.
	 * <b>post: </b> Crea un contrato con los valores que entran por parametro
	 */
	public Contrato(@JsonProperty( value = "id")Long pId, 
			@JsonProperty( value = "numDias") int pNumDias,
			@JsonProperty( value = "pagoAnticipado") boolean pPagoAnticipado,
			@JsonProperty( value = "serviciosPublicos") boolean pServiciosPublicos, 
			@JsonProperty( value = "incluyeAdministracion") boolean pIncluyeAdministracion, 
			@JsonProperty( value = "fecha") Date pFecha, 
			@JsonProperty( value = "costoDefinitivo")double pCostoDefinitivo,
			@JsonProperty( value = "cliente") Cliente pCLiente,
			@JsonProperty( value = "alojamiento") Alojamiento pAlojamiento)
	{
		id = pId;
		numDias = pNumDias;
		pagoAnticipado = pPagoAnticipado;
		serviciosPublicos = pServiciosPublicos;
		incluyeAdministracion = pIncluyeAdministracion;
		fecha = pFecha;
		costoDefinitivo = pCostoDefinitivo;
		cliente = pCLiente;
		alojamiento = pAlojamiento;
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

	public int getNumDias() {
		return numDias;
	}

	public void setNumDias(int numDias) {
		this.numDias = numDias;
	}

	public boolean isPagoAnticipado() {
		return pagoAnticipado;
	}

	public void setPagoAnticipado(boolean pagoAnticipado) {
		this.pagoAnticipado = pagoAnticipado;
	}

	public boolean isServiciosPublicos() {
		return serviciosPublicos;
	}

	public void setServiciosPublicos(boolean serviciosPublicos) {
		this.serviciosPublicos = serviciosPublicos;
	}

	public boolean isIncluyeAdministracion() {
		return incluyeAdministracion;
	}

	public void setIncluyeAdministracion(boolean incluyeAdministracion) {
		this.incluyeAdministracion = incluyeAdministracion;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public double getCostoDefinitivo() {
		return costoDefinitivo;
	}

	public void setCostoDefinitivo(double costoDefinitivo) {
		this.costoDefinitivo = costoDefinitivo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Alojamiento getAlojamiento() {
		return alojamiento;
	}

	public void setAlojamiento(Alojamiento alojamiento) {
		this.alojamiento = alojamiento;
	}

}

