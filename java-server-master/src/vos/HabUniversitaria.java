package vos;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class HabUniversitaria extends Alojamiento
{
	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * tipo de la habitacion que ofrece el hotel.
	 */
	@JsonProperty( value = "duracionDeHab")
	public int duracionDeHab;


	//----------------------------------------------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Contructor de una habitacion universitaria.
	 * <b>post: </b> Crea una habitacion universitaria con los valores que entran por parametro
	 */
	public HabUniversitaria(@JsonProperty( value = "id")Long pId,
			@JsonProperty( value = "ubicacion")String pUbicacion,
			@JsonProperty( value = "costoBasico")double pCostoBasico,
			@JsonProperty( value = "capacidad")int pCapacidad,
			@JsonProperty( value = "vigente")boolean pVigente,
			@JsonProperty( value = "fechaRetiro")Date pFechaRetiro,
			@JsonProperty( value = "operador")Operador pOperador,
			@JsonProperty( value = "servicios") List<Servicio> pServicios,
			@JsonProperty( value = "reservas") List<Reserva> pReservas,
			@JsonProperty( value = "tipo") String pTipo,
			@JsonProperty( value = "duracionDeHab") int pDuracionHab)
	{
		super(pId, pUbicacion, pCostoBasico, pCapacidad, pVigente, pFechaRetiro, pOperador, pServicios, pReservas,pTipo);
		duracionDeHab = pDuracionHab;
	}

	//----------------------------------------------------------------------------------------------------------------------------------
	// METODOS
	//----------------------------------------------------------------------------------------------------------------------------------

	public int getDuracionDeHab() {
		return duracionDeHab;
	}


	public void setDuracionDeHab(int duracionDeHab) {
		this.duracionDeHab = duracionDeHab;
	}


}

