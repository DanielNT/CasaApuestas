package casaapuestas.equipos;

import casaapuestas.cuentas.*;
import casaapuestas.usuarios.*;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;


public class Equipo {
	
	private String nombre;
	private String nombreCompleto;
	
	/**
	 * Constructor del equipo
	 * 
	 * @param nombreEquipo nombre del equipo
	 */
	public Equipo(String nombreEquipo, String nombreCompleto){
		this.nombre = nombreEquipo;
		this.nombreCompleto = nombreCompleto;
	}
	
	/**
	 * Muestra la informaci�n b�sica del equipo
	 * 
	 * @return lo que pongamos
	 */
	public String verInfoEquipo() {
		return nombre;
	}

	/**
	 * Muestra la informaci�n completa del equipo
	 * 
	 * @return lo que pongamos
	 */
	public String verInfoCompleta() {
		return nombreCompleto;
	}
	
}