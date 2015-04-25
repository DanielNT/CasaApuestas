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
	
	/**
	 * Constructor del equipo
	 * 
	 * @param nombreEquipo Nombre del equipo
	 */
	public Equipo(String nombreEquipo){
		
		this.nombre = nombreEquipo;
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
		
		return nombre;
	}
	
}