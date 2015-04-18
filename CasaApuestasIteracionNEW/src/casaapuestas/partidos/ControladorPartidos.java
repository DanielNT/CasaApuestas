package casaapuestas.partidos;

import casaapuestas.apuestas.Apuesta;
import casaapuestas.apuestas.CausaExcepcionApuestas;
import casaapuestas.apuestas.ExcepcionApuesta;
import casaapuestas.apuestas.TipoApuesta;
import casaapuestas.cuentas.*;
import casaapuestas.partidos.Partido.ResultadoQuiniela;
import casaapuestas.usuarios.*;
import casaapuestas.equipos.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class ControladorPartidos {
	
	
	private Map<String, Partido> listaPartidos;
	private Map<String, Equipo> listaEquipos;
	private Map<String, Apuesta> listadoApuestas;
	
	/**
	 * Constructor que inicializa las colecciones
	 */
	public ControladorPartidos(ControladorUsuarios cu){

		listaPartidos = new HashMap<String, Partido>();
		listaEquipos =new HashMap<String, Equipo>();
		listadoApuestas =new LinkedHashMap<String, Apuesta>();
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
	
	
	public List<String> listarApuestasPartido(String idPartido) {
		
		List<String> listado4 = new ArrayList<String>();
		
		if (!listaPartidos.containsKey(idPartido)) {
			
			for (Partido p : listaPartidos.values()) {
		
				//Para todas las apuestas
				for (Apuesta a : listadoApuestas.values()) {
					String ficha = a.verInfoApuesta() + p.getEquipoL() + "-" + p.getEquipoV();
					listado4.add(ficha);
				}
			}
		
		}
		return listado4;
	}
	
	
	
	/**
	 * @param login
	 * @param idPartido
	 * @param tApuesta
	 * @param resultado
	 * @param cantidadApostada
	 * @throws ExcepcionApuesta
	 */
	public void nuevaApuesta(String login, String idPartido, TipoApuesta tApuesta, String resultado,float cantidadApostada)throws ExcepcionApuesta{

				if (!listadoApuestas.containsKey(idPartido)) {
					// Si no existe, crea la instancia
					Apuesta nuevaApuesta = new Apuesta(login, idPartido, tApuesta,resultado,cantidadApostada);
					// Y la colecciona
					listadoApuestas.put(idPartido, nuevaApuesta);
				} else {
					// Pero si ya existía lanza una excepción
					 throw new ExcepcionApuesta(CausaExcepcionApuestas.ERROR_CREAR_APUESTA, login);
				}
	}
	
	
	
	
	
	
	
	
	/**
	 * Muestra la información del partido para el ID indicado
	 * 
	 * @param idPartido
	 * @return la información completa del partido
	 */
	public String mostrarPartido(String idPartido){
		
		Partido estePartido = listaPartidos.get(idPartido);
		return estePartido.verInfoCompleta();
	
	}
	
	
	
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
	public void eliminarPartido(String idPartido) throws ExcepcionPartidos{
		
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
			 throw new ExcepcionPartidos(CausaExcepcionPartidos.YA_EXISTE_E, nombre);
		}
	}
		
	
		
	public String mostrarEquipo(String nombre) {
		Equipo esteEquipo = listaEquipos.get(nombre);
		return esteEquipo.verInfoCompleta();
	}
		
	public void modificarPartido(String idPartido, String equipoL, String equipoV, Calendar fInicApuesta, Calendar fFinApuesta)
			throws ExcepcionPartidos {
		
		// Recupera la instancia de la colección
		Partido estePartido = listaPartidos.get(idPartido);
		// Si este jugador existía, no es null
		if (estePartido != null) {
			// Modifica uno a uno los otros atributos
			estePartido.setfInicApuesta(fInicApuesta);
			estePartido.setfFinApuesta(fFinApuesta);
			
		} else {
			// Pero si no existía lanza una excepción
			throw new ExcepcionPartidos(CausaExcepcionPartidos.NO_EXISTE, idPartido);
		}
	}
		
	/**
	 * Muestra partidos abiertos a apuestas
	 * @return lista de los partidos abiertos a apuestas
	 */
	public List<String> verPartidosAbiertosAApuesta() throws ExcepcionPartidos{
		
		List<String> listado3 = new ArrayList<String>();
		
		//Obtiene la fecha actual del sistema
		Calendar fechaActual = Calendar.getInstance();
		fechaActual.getTime();
	
		
		for(Partido p : listaPartidos.values()){
			
			//Si la fecha actual es posterior a la fijada para el inicio de apuesta e inferior a la de fin Apuesta
			if(fechaActual.before(p.getfFinApuesta()) && fechaActual.after(p.getfInicApuesta())){
				
				String ficha3 = p.verInfoPartido();
				listado3.add(ficha3);
			}
		}
		
		//Si el listado está vacío, manda una excepción (o bien avisa de que no hay partidos abiertos)
		if(listado3.isEmpty()){
			throw new ExcepcionPartidos(CausaExcepcionPartidos.NO_PART_ABIERTOS_APUESTAS,"0");
		}
		
		return listado3;
	
	}

}