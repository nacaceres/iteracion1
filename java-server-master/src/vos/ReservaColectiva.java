package vos;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class ReservaColectiva {

	@JsonProperty( value = "idReservaColectiva")
	private Long idReservaColectiva;
	
	@JsonProperty( value = "reserva")
	private Reserva reserva;
	
	@JsonProperty( value = "cantidad")
	private int cantidad;
	
	public ReservaColectiva(@JsonProperty( value = "idReservaColectiva") Long pIdReservaColectiva,
			@JsonProperty( value = "reserva") Reserva pReserva, 
			@JsonProperty( value = "cantidad") int pCantidad)
	{
		idReservaColectiva = pIdReservaColectiva;
		reserva = pReserva;
		cantidad = pCantidad;
	}

	public Long getIdReservaColectiva() {
		return idReservaColectiva;
	}

	public void setIdReservaColectiva(Long idReservaColectiva) {
		this.idReservaColectiva = idReservaColectiva;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	
}
