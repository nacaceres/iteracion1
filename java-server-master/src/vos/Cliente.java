package vos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class Cliente
{
	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Id del cliente
	 */
	public int id;

	/**
	 * tipo del id
	 */
	
	public String tipoId;

	/**
	 * nombre del cliente
	 */
	
	public String nombre;

	/**
	 * Contacto del cliente
	 */
	
	public String contacto;

	/**
	 * cantidad total de dinero pago por el cliente
	 */
	
	public double dineroPago;

	/**
	 * numero de noches que ha tomado cada cliente mediante la aplicacion.
	 */
	
	public int numeroDeNochesTomadas;

	/**
	 * lista de los contratos que tiene el cliente
	 */
	
	public ArrayList <Contrato> contratos;

	/**
	 * lista de las reservas que tiene un cliente.
	 */
	
	public ArrayList<Reserva> reservas;

	/**
	 * Describe la relacion con uniandes que tiene el cliente
	 */
	
	public RelacionUniandes relacionUniandes;

	/**
	 * Lista de servicios preferidos por el cliente
	 */
	
	public ArrayList<Servicio> serviciosPreferidos;

	/**
	 * Alojamientos preferidos por el cliente.
	 */
	
	public ArrayList<Alojamiento> alojamientosPreferidos;

	/**
	 * Constructor de cliente.
	 */
	public Cliente(int pId, String pTipoId, String pContacto, double pDineroPago, int pNumeroDeNochesTomadas, RelacionUniandes pRelacion ){
		
	}

}

