package casaapuestas.partidos;

import casaapuestas.cuentas.*;
import casaapuestas.partidos.Partido.ResultadoQuiniela;
import casaapuestas.usuarios.*;
import casaapuestas.equipos.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class ControladorPartidos {
	
	
	private Map<String, Partido> listaPartidos;
	private Map<String, Equipo> listaEquipos;
	
	/**
	 * Constructor que inicializa las colecciones
	 */
	public ControladorPartidos(ControladorUsuarios cu){

		listaPartidos = new HashMap<String, Partido>();
		listaEquipos =new HashMap<String, Equipo>();
	}
	
	/**
	 * Crea un nuevo listado con los partidos
	 * 
	 * @return el listado
	 */
	public List<String> listarPartidos() {
		
		List<String> listado = new ArrayList<String>();
		
		for (Partido p : listaPartidos.values()) {
			String ficha = p.verInfoPartido();
			listado.add(ficha);
		}

		return listado;
	}
	
	public List<String> listarEquipos() {
		
		List<String> listado2 = new ArrayList<String>();
		
		for (Equipo e : listaEquipos.values()) {
			String ficha2 = e.verInfoEquipo();
			listado2.add(ficha2);
		}

		return listado2;
}
	
	/**
	 * Muestra la información del partido para el ID indicado
	 * 
	 * @param idPartido
	 * @return la información completa del partido
	 */
	public String mostrarPartido(String idPartido) {
		Partido estePartido = listaPartidos.get(idPartido);
		return estePartido.verInfoCompleta();
	}
	
	
	/**
	 * Modifica los parámetros del partido introducidos
	 * 
	 * @param idPartido El ID del partido
	 * @param equipoL Equipo local
	 * @param equipoV Equipo visitante
	 * @param resultadoL El resultado del equipo local
	 * @param resultadoV El resultado del equipo visitante
	 * @param resultadoQuin El resultado en modo quiniela
	 * @param fInicApuesta La fecha de inicio de la apuesta
	 * @param hInicApuesta La hora de inicio de la apuesta
	 * @param fInicPart La fecha de inicio del partido
	 * @param hInicPart La hora de inicio del partido
	 */

	
	public void añadirPartido(String equipoL, String equipoV, Calendar fInicApuesta, Calendar fFinApuesta) {
		
		int id;
		
		for(id=0; ;id++){
			
			String idPartido= "p" + id;
			if (!listaPartidos.containsKey(idPartido)) {
		
				Partido p = new Partido(idPartido, equipoL, equipoV, 0, 0, ResultadoQuiniela.EMPATE, fInicApuesta, fFinApuesta);
				listaPartidos.put(idPartido, p);
				break;
			}
		}
		
	}
	
	
	/**
	 * Elimina un partido del listado según el ID
	 * 
	 * @param idPartido El ID del partido que vamos a eliminar
	 * @throws ExcepcionPartidos lanza una excepción si ha habido un error al eliminar el partido
	 */
	public void eliminarPartido(int idPartido) throws ExcepcionPartidos{
		
		Partido p = listaPartidos.get(idPartido);
		
		//Si existe el partido con ese ID, lo elimina de la lista
		 if (listaPartidos.containsKey(idPartido)){ 
			 listaPartidos.remove(idPartido,p);
	     }
		 else{
			 throw new ExcepcionPartidos(CausaExcepcionPartidos.ERROR_ELIMINAR, idPartido);
		 }
		 
	}
	
	
	public void nuevoEquipo(String nombre, String nombreCompleto) throws ExcepcionPartidos{
		
		// Comprueba si ya existe un jugador con este login
		if (!listaEquipos.containsKey(nombre)) {
			// Si no existe, crea la instancia
			Equipo nuevoEquipo = new Equipo(nombre, nombreCompleto);
			// Y la colecciona
			listaEquipos.put(nombre, nuevoEquipo);
		} else {
			// Pero si ya existía lanza una excepción
			 throw new ExcepcionPartidos(CausaExcepcionPartidos.YA_EXISTE_E, -1);
		}
	}
		
	
		
	public String mostrarEquipo(String nombre) {
		Equipo esteEquipo = listaEquipos.get(nombre);
		return esteEquipo.verInfoCompleta();
	}
		
		
	

}