package vos;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class HabHotel extends Alojamiento
{
	public final static String ESTANDAR = "ESTANDAR";
	public final static String SUITE = "SUITE";
	public final static String SEMISUITE = "SEMISUITE";
	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * tipo de la habitacion que ofrece el hotel.
	 */
	@JsonProperty( value = "tipoHabitacion")
	private String tipoHabitacion;
	//----------------------------------------------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Contructor de una habitacion de hotel.
	 * <b>post: </b> Crea una habitacion de hotel con los valores que entran por parametro
	 */
	public HabHotel(@JsonProperty( value = "id")Long pId,
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
			@JsonProperty( value = "reservas") List<Reserva> pReservas)
	{
		super(pId, pUbicacion, pCostoBasico, pDiasAlquilado, pCapacidad, pNumPersonas, pVigente, pFechaRetiro, pVecesAlquilado, pOperador, pServicios, pReservas);
	}

	//----------------------------------------------------------------------------------------------------------------------------------
	// METODOS
	//----------------------------------------------------------------------------------------------------------------------------------

	public String getTipoHabitacion() {
		return tipoHabitacion;
	}

	public void setTipoHabitacion(String tipoHabitacion) {
		this.tipoHabitacion = tipoHabitacion;
	}



}
