package vos;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Apartamento extends Alojamiento
{

	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * boolean que indica si un apartamento esta amoblado
	 */
	@JsonProperty( value = "amoblado")
	private boolean amoblado;

	/**
	 * lista con los contratos de un apartamento.
	 */
	@JsonProperty( value = "contratos")
	private List<Contrato> contratos;

	//----------------------------------------------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Contructor de un apartamento.
	 * <b>post: </b> Crea un apartamento con los valores que entran por parametro
	 */
	public Apartamento(@JsonProperty( value = "id")Long pId,
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
			@JsonProperty( value = "amoblado") boolean pAmoblado,
			@JsonProperty( value = "contratos") List<Contrato> pContratos)
	{
		super(pId, pUbicacion, pCostoBasico, pDiasAlquilado, pCapacidad, pNumPersonas, pVigente, pFechaRetiro, pVecesAlquilado, pOperador, pServicios, pReservas);
		amoblado = pAmoblado;
		contratos = pContratos;
	}

	//----------------------------------------------------------------------------------------------------------------------------------
	// METODOS
	//----------------------------------------------------------------------------------------------------------------------------------

	public boolean isAmoblado() {
		return amoblado;
	}

	public void setAmoblado(boolean amoblado) {
		this.amoblado = amoblado;
	}

	public List<Contrato> getContratos() {
		return contratos;
	}

	public void setContratos(List<Contrato> contratos) {
		this.contratos = contratos;
	}

	
}

