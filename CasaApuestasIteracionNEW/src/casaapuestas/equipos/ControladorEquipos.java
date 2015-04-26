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


//Falta modificar (commit de ejemplo)

public class ControladorEquipos {
	
	private Map<String, Equipo> listaEquipos;
	
	public ControladorEquipos(){
		
		listaEquipos = new HashMap<String, Equipo>();
		
	}
	
	public List<String> listarEquipos() {
		
		List<String> listado = new ArrayList<String>();
		
		for (Equipo e : listaEquipos.values()) {
			String ficha = e.verInfoEquipo();
			listado.add(ficha);
		}

		return listado;
	}
	
	public String mostrarEquipo(String nombre) {
		Equipo esteEquipo = listaEquipos.get(nombre);
		return esteEquipo.verInfoCompleta();
	}
	
	public void nuevoEquipo(String nombre, String nombreCompleto){
		// Comprueba si ya existe un jugador con este login
		if (!listaEquipos.containsKey(nombre)) {
			// Si no existe, crea la instancia
			Equipo nuevoEquipo = new Equipo(nombre, nombreCompleto);
			// Y la colecciona
			listaEquipos.put(nombre, nuevoEquipo);
		} else {
			// Pero si ya existía lanza una excepción
			
	}
		
	}
}