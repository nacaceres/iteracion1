package vos;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class Vivienda extends Alojamiento
{
	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Numero de habitaciones que tiene la vivienda.
	 */
	@JsonProperty( value = "numHabitaciones")
	public int numHabitaciones;

	/**
	 * True si representa una vivienda temporal, false si es una vivienda que pueden prestar mas de 30 dias
	 */
	@JsonProperty( value = "numHabitaciones")
	public boolean cedido;

	/**
	 * Boolean que indica si el cuarto es compartido.
	 */
	@JsonProperty( value = "compartido")
	public boolean compartido;
	/**
	 * Lista con todos los contratos de una vivienda.
	 */
	@JsonProperty( value = "contratos")
	public List<Contrato> contratos;

	//----------------------------------------------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Contructor de alojamiento.
	 * <b>post: </b> Crea un alojamiento con los valores que entran por parametro
	 */
	public Vivienda(@JsonProperty( value = "id")Long pId,
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
			@JsonProperty( value = "reservas") List<Reserva> pReservas,
			@JsonProperty( value = "tipo") String pTipo,
			@JsonProperty( value = "numHabitaciones") int pNumHabitaciones,
			@JsonProperty( value = "cedido") boolean pCedido,
			@JsonProperty( value = "compartido")boolean pCompartido,
			@JsonProperty( value = "contratos") List <Contrato> pContratos)
	{
		super(pId, pUbicacion, pCostoBasico, pDiasAlquilado, pCapacidad, pNumPersonas, pVigente, pFechaRetiro, pVecesAlquilado, pOperador, pServicios, pReservas, pTipo);
		numHabitaciones = pNumHabitaciones;
		cedido = pCedido;
		compartido = pCompartido;
		contratos = pContratos;
	}
	//----------------------------------------------------------------------------------------------------------------------------------
	// METODOS
	//----------------------------------------------------------------------------------------------------------------------------------

	public int getNumHabitaciones() {
		return numHabitaciones;
	}

	public void setNumHabitaciones(int numHabitaciones) {
		this.numHabitaciones = numHabitaciones;
	}

	public boolean isCedido() {
		return cedido;
	}

	public void setCedido(boolean cedido) {
		this.cedido = cedido;
	}

	public boolean isCompartido() {
		return compartido;
	}

	public void setCompartido(boolean compartido) {
		this.compartido = compartido;
	}

	public List<Contrato> getContratos() {
		return contratos;
	}

	public void setContratos(List<Contrato> contratos) {
		this.contratos = contratos;
	}


}

