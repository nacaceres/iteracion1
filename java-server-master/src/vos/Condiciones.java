package vos;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class Condiciones {


	@JsonProperty( value = "servicios")
	private List<Servicio> servicios;
	
	@JsonProperty( value = "fechaInicio")
	private Date fechaInicio;
	
	@JsonProperty( value = "fechaFin")
	private Date fechaFin;
	
	public Condiciones(@JsonProperty( value = "servicios") List<Servicio> pServicios,@JsonProperty( value = "fechaInicio") Date pFechaInicio,@JsonProperty( value = "fechaFin") Date pFechaFin )
	{
		servicios = pServicios;
		fechaInicio = pFechaInicio;
		fechaFin = pFechaFin;
	}

	public List<Servicio> getServicios() {
		return servicios;
	}

	public void setServicios(List<Servicio> servicios) {
		this.servicios = servicios;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	
}
