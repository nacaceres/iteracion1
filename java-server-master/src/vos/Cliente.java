package vos;


import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;


public class Cliente
{
	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Id del cliente
	 */
	@JsonProperty( value = "id")
	private Long id;

	/**
	 * tipo del id
	 */
	@JsonProperty( value = "tipoId")
	private String tipoId;

	/**
	 * nombre del cliente
	 */
	@JsonProperty( value = "nombre")
	private String nombre;

	/**
	 * Contacto del cliente
	 */
	@JsonProperty( value = "contacto")
	private String contacto;

	/**
	 * lista de los contratos que tiene el cliente
	 */
	@JsonProperty( value = "contratos")
	private List <Contrato> contratos;

	/**
	 * lista de las reservas que tiene un cliente.
	 */
	@JsonProperty( value = "reservas")
	private List<Reserva> reservas;

	/**
	 * Describe la relacion con uniandes que tiene el cliente
	 */
	@JsonProperty( value = "relacionUniandes")
	private RelacionUniandes relacionUniandes;

	/**
	 * Lista de servicios preferidos por el cliente
	 */
	@JsonProperty( value = "serviciosPreferidos")
	private List<Servicio> serviciosPreferidos;

	/**
	 * Alojamientos preferidos por el cliente.
	 */
	@JsonProperty( value = "alojamientosPreferidos")
	private List<Alojamiento> alojamientosPreferidos;
	//----------------------------------------------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Constructor de cliente.
	 * <b>post: </b> Crea el cliente con los valores que entran por parametro
	 */
	public Cliente(@JsonProperty( value = "id")Long pId,
			       @JsonProperty( value = "tipoId")String pTipoId, 
			       @JsonProperty( value = "nombre")String pNombre,
			       @JsonProperty( value = "contacto")String pContacto,
			       @JsonProperty( value = "relacionUniandes")RelacionUniandes pRelacion, 
			       @JsonProperty( value = "contratos")List <Contrato> pContratos, 
			       @JsonProperty( value = "reservas")List <Reserva> pReservas, 
			       @JsonProperty( value = "serviciosPreferidos")List <Servicio> pServicios, 
			       @JsonProperty( value = "alojamientosPreferidos")List<Alojamiento> pAlojamientos  ){
		id = pId;
		tipoId = pTipoId;
		nombre = pNombre;
		contacto = pContacto;
		contratos = pContratos;
		reservas = pReservas;
		relacionUniandes= pRelacion;
		serviciosPreferidos= pServicios;
		alojamientosPreferidos = pAlojamientos;
	}

	//----------------------------------------------------------------------------------------------------------------------------------
	// METODOS
	//----------------------------------------------------------------------------------------------------------------------------------

	public Long getId() {
		return id;
	}


	public String getTipoId() {
		return tipoId;
	}


	public String getNombre() {
		return nombre;
	}


	public String getContacto() {
		return contacto;
	}


	public List<Contrato> getContratos() {
		return contratos;
	}


	public List<Reserva> getReservas() {
		return reservas;
	}


	public RelacionUniandes getRelacionUniandes() {
		return relacionUniandes;
	}


	public List<Servicio> getServiciosPreferidos() {
		return serviciosPreferidos;
	}


	public List<Alojamiento> getAlojamientosPreferidos() {
		return alojamientosPreferidos;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public void setTipoId(String tipoId) {
		this.tipoId = tipoId;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setContacto(String contacto) {
		this.contacto = contacto;
	}

	public void setContratos(List<Contrato> contratos) {
		this.contratos = contratos;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

	public void setRelacionUniandes(RelacionUniandes relacionUniandes) {
		this.relacionUniandes = relacionUniandes;
	}

	public void setServiciosPreferidos(List<Servicio> serviciosPreferidos) {
		this.serviciosPreferidos = serviciosPreferidos;
	}

	public void setAlojamientosPreferidos(List<Alojamiento> alojamientosPreferidos) {
		this.alojamientosPreferidos = alojamientosPreferidos;
	}

	
}

