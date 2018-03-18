package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Servicio
{
	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Id del servicio
	 */
	@JsonProperty( value = "id")
	private Long id;
	/**
	 * nombre del servicio
	 */
	@JsonProperty( value = "nombre")
	private String nombre;

	/**
	 * Descripcion del servicio
	 */
	@JsonProperty( value = "descripcion")
	private String descripcion;

	/**
	 * costo adicional del servicio
	 */
	@JsonProperty( value = "costoAdicional")
	private double costoAdicional;

	//----------------------------------------------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Contructor de servicio.
	 * <b>post: </b> Crea un servicio con los valores que entran por parametro
	 */
	public Servicio(@JsonProperty( value = "id") Long pId, 
			@JsonProperty( value = "nombre") String pNombre, 
			@JsonProperty( value = "descripcion") String pDescripcion,
			@JsonProperty( value = "costoAdicional") double pCostoAdicional)
	{
		id = pId;
		nombre = pNombre;
		descripcion = pDescripcion;
		costoAdicional = pCostoAdicional;
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getCostoAdicional() {
		return costoAdicional;
	}

	public void setCostoAdicional(double costoAdicional) {
		this.costoAdicional = costoAdicional;
	}


}

