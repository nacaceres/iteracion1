package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Seguro
{
	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Id del seguro
	 */
	@JsonProperty( value = "id")
	private Long id;
	/**
	 * valor del seguro
	 */
	@JsonProperty( value = "valor")
	public double valor;

	//----------------------------------------------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Contructor de seguro.
	 * <b>post: </b> Crea un seguro con los valores que entran por parametro
	 */
	public Seguro(@JsonProperty( value = "id") Long pId,
				  @JsonProperty( value = "valor") double pValor)
	{
		id = pId;
		valor = pValor;
	}
	//----------------------------------------------------------------------------------------------------------------------------------
	// METODOS
	//----------------------------------------------------------------------------------------------------------------------------------

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	
}

