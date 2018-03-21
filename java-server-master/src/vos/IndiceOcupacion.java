package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class IndiceOcupacion {

	@JsonProperty( value = "idAlojamiento")
	private String idAlojamiento;
	
	@JsonProperty( value = "nombreOperador")
	private String nombreOperador;
	
	@JsonProperty( value = "porcentajeOcupacion")
	private String porcentajeOcupacion;
	
	public IndiceOcupacion (@JsonProperty( value = "idAlojamiento") String pIdAlojamiento, 
							@JsonProperty( value = "nombreOperador") String pNombreOperador,
							@JsonProperty( value = "porcentajeOcupacion") String pPorcentajeOcupacion)
	{
		idAlojamiento=pIdAlojamiento;
		nombreOperador = pNombreOperador;
		porcentajeOcupacion = pPorcentajeOcupacion;
	}

	public String getIdAlojamiento() {
		return idAlojamiento;
	}

	public void setIdAlojamiento(String idAlojamiento) {
		this.idAlojamiento = idAlojamiento;
	}

	public String getNombreOperador() {
		return nombreOperador;
	}

	public void setNombreOperador(String nombreOperador) {
		this.nombreOperador = nombreOperador;
	}

	public String getPorcentajeOcupacion() {
		return porcentajeOcupacion;
	}

	public void setPorcentajeOcupacion(String porcentajeOcupacion) {
		this.porcentajeOcupacion = porcentajeOcupacion;
	}
	
	
}
