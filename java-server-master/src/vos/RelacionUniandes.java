package vos;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class RelacionUniandes
{
	//----------------------------------------------------------------------------------------------------------------------------------
	// CONSTANTES QUE REPRESENTAN EL TIPO DE RELACION
	//----------------------------------------------------------------------------------------------------------------------------------

	public static final String ESTUDIANTE = "ESTUDIANTE";

	public static final String PROFESOR = "PROFESOR";

	public static final String HOTEL = "HOTEL";

	public static final String EMPLEADO = "EMPLEADO";

	public static final String VECINO = "VECINO";

	public static final String FENICIA = "FENICIA";

	public static final String PROFESOR_INVITADO = "PROFESOR INVITADO";

	public static final String REGISTRADO = "REGISTRADO";

	public static final String EGRESADO = "EGRESADO";

	public static final String PADRE_ESTUDIANTE = "PADRE ESTUDIANTE";

	public static final String ADMINISTRADOR_VIVIENDA_UNIVERSITARIA ="ADMININISTRADOR VIVIENDA UNIVERSITARIA";
	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Tipo de la relacion con uniandes.
	 */
	@JsonProperty( value = "tipo")
	private String tipo;

	/**
	 * Tipo de la relacion con uniandes.
	 */
	@JsonProperty( value = "carnet")
	private int carnet;
	//----------------------------------------------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Contructor de alojamiento.
	 * <b>post: </b> Crea un alojamiento con los valores que entran por parametro
	 */
	public RelacionUniandes(@JsonProperty( value = "tipo") String pTipo ,  
			@JsonProperty( value = "carnet") int pCarnet) throws Exception
	{
		if(pTipo==ESTUDIANTE|| pTipo == PROFESOR || pTipo == HOTEL || pTipo == EMPLEADO || pTipo == VECINO || pTipo == FENICIA || pTipo == PROFESOR_INVITADO || pTipo == REGISTRADO || pTipo == EGRESADO || pTipo == PADRE_ESTUDIANTE || pTipo == ADMINISTRADOR_VIVIENDA_UNIVERSITARIA)
		{
			tipo = pTipo;
			carnet = pCarnet;
		}
		else
			throw new Exception ("No se pudo establecer la relacion con uniandes");
	}
	//----------------------------------------------------------------------------------------------------------------------------------
	// METODOS
	//----------------------------------------------------------------------------------------------------------------------------------

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getCarnet() {
		return carnet;
	}

	public void setCarnet(int carnet) {
		this.carnet = carnet;
	}

}

