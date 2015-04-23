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

@SuppressWarnings("unused")
public class Equipo {
	
	private String nombre;
	
	public Equipo(String nombreEquipo){
		
		this.nombre = nombreEquipo;
		
	}
	
	
	
	
	public String verInfoEquipo() {
		return nombre;
	}

	public String verInfoCompleta() {
		return nombre;
	}
}