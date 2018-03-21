package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class AlojamientosTop {

	@JsonProperty( value = "idAlojamiento")
	private String idAlojamiento;
	@JsonProperty( value = "operador")
	private String operador;
	@JsonProperty( value = "ubicacion")
	private String ubicacion;
	@JsonProperty( value = "numReservas")
	private String numReservas;
	
	public AlojamientosTop(@JsonProperty( value = "idAlojamiento") String pIdAlojamiento,
						   @JsonProperty( value = "operador") String pOperador,
						   @JsonProperty( value = "ubicacion") String pUbicacion,
						   @JsonProperty( value = "numReservas") String pNumReservas)
	{
		idAlojamiento = pIdAlojamiento;
		operador =pOperador;
		ubicacion = pUbicacion;
		numReservas = pNumReservas;
	}

	public String getIdAlojamiento() {
		return idAlojamiento;
	}

	public void setIdAlojamiento(String idAlojamiento) {
		this.idAlojamiento = idAlojamiento;
	}

	public String getOperador() {
		return operador;
	}

	public void setOperador(String operador) {
		this.operador = operador;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getNumReservas() {
		return numReservas;
	}

	public void setNumReservas(String numReservas) {
		this.numReservas = numReservas;
	}
	
	
}
