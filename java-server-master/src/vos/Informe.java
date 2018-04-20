package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class Informe {

	/**
	 * Indica si la reserva hace parte de una reserva colectiva
	 */
	@JsonProperty( value = "reporte")
	private List<String> reporte;
	
	public Informe (@JsonProperty( value = "reporte")List<String>pReporte)
	{
		reporte = pReporte;
	}

	public List<String> getReporte() {
		return reporte;
	}

	public void setReporte(List<String> reporte) {
		this.reporte = reporte;
	}
	
	
}
