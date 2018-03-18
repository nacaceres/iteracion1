package vos;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;


public class Operador
{
	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Id del operador
	 */
	@JsonProperty( value = "id")
	private Long id;

	/**
	 * tipo de id del operador
	 */
	@JsonProperty( value = "tipoId")
	private String tipoId;

	/**
	 * nombre del operador
	 */
	@JsonProperty( value = "nombre")
	private String nombre;

	/**
	 * contacto del operador
	 */
	@JsonProperty( value = "contacto")
	private String contacto;

	/**
	 * Alojamientos que ofrece el operador
	 */
	@JsonProperty( value = "alojamientos")
	private List<Alojamiento> alojamientos;

	/**
	 * Relacion que mantiene el operador con uniandes.
	 */
	@JsonProperty( value = "relacionUniandes")
	private RelacionUniandes relacionUniandes;

	//----------------------------------------------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Contructor de operador.
	 * <b>post: </b> Crea un operador con los valores que entran por parametro
	 */
	public Operador(@JsonProperty( value = "id") Long pId,
				    @JsonProperty( value = "tipoId")String pTipoId,
				    @JsonProperty( value = "nombre")String pNombre,
				    @JsonProperty( value = "contacto") String pContacto,
				    @JsonProperty( value = "alojamientos")List<Alojamiento>pAlojamientos,
				    @JsonProperty( value = "relacionUniandes")RelacionUniandes pRelacionUniandes)
	{
		id = pId;
		tipoId = pTipoId;
		nombre=pNombre;
		contacto = pContacto;
		alojamientos = pAlojamientos;
		relacionUniandes = pRelacionUniandes;
	}
	//----------------------------------------------------------------------------------------------------------------------------------
	// METODOS
	//----------------------------------------------------------------------------------------------------------------------------------

	
	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}


	public String getTipoId() {
		return tipoId;
	}


	public String getNombre() {
		return nombre;
	}


	public String getContacto() {
		return contacto;
	}


	public List<Alojamiento> getAlojamientos() {
		return alojamientos;
	}


	public RelacionUniandes getRelacionUniandes() {
		return relacionUniandes;
	}


	public void setTipoId(String tipoId) {
		this.tipoId = tipoId;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setContacto(String contacto) {
		this.contacto = contacto;
	}

	public void setAlojamientos(List<Alojamiento> alojamientos) {
		this.alojamientos = alojamientos;
	}

	public void setRelacionUniandes(RelacionUniandes relacionUniandes) {
		this.relacionUniandes = relacionUniandes;
	}

	
}

