package casaapuestas.equipos;

import casaapuestas.apuestas.*;
import casaapuestas.arranque.*;
import casaapuestas.cuentas.*;
import casaapuestas.partidos.*;
import casaapuestas.usuarios.*;


/**
 * @author Iss002
 * Clase que representa a un Equipo en la casa de apuestas.
 */
public class Equipo {
	
	private String nombre;
	private String nombreCompleto;
	
	/**
	 * Constructor del equipo
	 * 
	 * @param nombreEquipo nombre del equipo
	 * @param nombreCompleto Nombre completo del Equipo
	 */
	public Equipo(String nombreEquipo, String nombreCompleto){
		super();
		this.nombre = nombreEquipo;
		this.nombreCompleto = nombreCompleto;
	}
	
	/**
	 * Muestra la información básica del equipo
	 * 
	 * @return lo que pongamos
	 */
	public String verInfoEquipo() {
		return nombre;
	}

	/**
	 * Muestra la información completa del equipo
	 * 
	 * @return lo que pongamos
	 */
	public String verInfoCompleta() {
		return nombre + " (" + nombreCompleto + ")";
	}

	public String getNombre() {
		return nombre;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}
	
}