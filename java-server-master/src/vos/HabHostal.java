package vos;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;


public class HabHostal extends Alojamiento
{
	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * horario de apertura de la recepcion del hostal.
	 */
	@JsonProperty( value = "horarioApertura")
	private Date horarioApertura;

	/**
	 * horario de cierre de la recepcion del hostal.
	 */
	@JsonProperty( value = "horarioCierre")
	private Date horarioCierre;

	/**
	 * boolean que indica si la habitacion es compartida.
	 */
	@JsonProperty( value = "compartida")
	private boolean compartida;
	//----------------------------------------------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Contructor de un hostal.
	 * <b>post: </b> Crea un hostal con los valores que entran por parametro
	 */
	public HabHostal(@JsonProperty( value = "id")Long pId,
			@JsonProperty( value = "ubicacion")String pUbicacion,
			@JsonProperty( value = "costoBasico")double pCostoBasico,
			@JsonProperty( value = "capacidad")int pCapacidad,
			@JsonProperty( value = "vigente")boolean pVigente,
			@JsonProperty( value = "fechaRetiro")Date pFechaRetiro,
			@JsonProperty( value = "operador")Operador pOperador,
			@JsonProperty( value = "servicios") List<Servicio> pServicios,
			@JsonProperty( value = "reservas") List<Reserva> pReservas,
			@JsonProperty( value = "tipo") String pTipo,
			@JsonProperty( value = "horarioApertura") Date pHorarioApertura,
			@JsonProperty( value = "horarioCierre") Date pHorarioCierre,
			@JsonProperty( value = "compartida")boolean pCompartida)
	{
		super(pId, pUbicacion, pCostoBasico,  pCapacidad, pVigente, pFechaRetiro,  pOperador, pServicios, pReservas,pTipo);
		horarioApertura =pHorarioApertura;
		horarioCierre = pHorarioCierre;
		compartida = pCompartida;
	}

	//----------------------------------------------------------------------------------------------------------------------------------
	// METODOS
	//----------------------------------------------------------------------------------------------------------------------------------

	public Date getHorarioApertura() {
		return horarioApertura;
	}

	public void setHorarioApertura(Date horarioApertura) {
		this.horarioApertura = horarioApertura;
	}

	public Date getHorarioCierre() {
		return horarioCierre;
	}

	public void setHorarioCierre(Date horarioCierre) {
		this.horarioCierre = horarioCierre;
	}

	public boolean isCompartida() {
		return compartida;
	}

	public void setCompartida(boolean compartida) {
		this.compartida = compartida;
	}

	
}

