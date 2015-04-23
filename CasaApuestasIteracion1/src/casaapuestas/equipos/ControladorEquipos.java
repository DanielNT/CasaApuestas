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




public class ControladorEquipos {
	
	private String nombre;
	
	private Map<String, Equipo> listaEquipos;
	
	public ControladorEquipos(){
		
		listaEquipos = new HashMap<String, Equipo>();
		
	}
	
	public List<String> listaEquipos() {
		
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
}