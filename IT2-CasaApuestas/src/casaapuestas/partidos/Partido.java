package casaapuestas.partidos;


import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import casaapuestas.apuestas.ContenedorApuestas;
import casaapuestas.apuestas.TipoApuesta;




/**
 * @author Iss002
 * Clase que representa a un Partido en la casa de apuestas.
 */

public class Partido {
	
	private String idPartido;
	private String equipoL;
	private String equipoV;
	private Calendar fInicApuesta;
	private Calendar fFinApuesta;
	private Map<TipoApuesta, ContenedorApuestas> listaContenedorApuestas = new HashMap<TipoApuesta, ContenedorApuestas>();;
	private Map<TipoApuesta, String> listaResultados= new HashMap<TipoApuesta, String>();;
	
	
	

	/**
	 * Constructor que inicializa el partido con todos los parámetros
	 * 
	 * @param idPartido El ID del partido
	 * @param equipoL El nombre del equipo local
	 * @param equipoV El nombre del equipo visitante
	 * @param fInicioApuesta La fecha de inicio de las apuestas
	 * @param fFinApuesta La fecha del final de las apuestas
	 */
	
	public Partido(String idPartido, String equipoL, String equipoV, Calendar fInicioApuesta, Calendar fFinApuesta){
		
		super();
		this.idPartido = idPartido;
		this.equipoL = equipoL;
		this.equipoV = equipoV;
		this.fInicApuesta = fInicioApuesta;
		this.fFinApuesta = fFinApuesta;
	
		
	}
	
	
	
	/**
	 * Muestra la info resumida del partido
	 * @return la ficha
	 */
	public String verInfoPartido() {
		String ficha = "Partido " + idPartido + ": " + equipoL + "-" + equipoV;
		return ficha;
	}

	/**
	 * Muestra toda la información del partido
	 * @return la ficha completa
	 */
	
	public String verInfoCompleta() {
		String ficha = "Partido " + idPartido + ": " + equipoL + "-" + equipoV + "(admite apuestas entre " + fInicApuesta.getTime().toLocaleString() + " y " + fFinApuesta.getTime().toLocaleString() + ")";
		return ficha;
	}
	
	
	
	/**
	 * @return the idPartido
	 */
	public String getIdPartido() {
		return idPartido;
	}
	
	/**
	 * @return the equipoL
	 */
	public String getEquipoL() {
		return equipoL;
	}
	
	
	/**
	 * @return the equipoV
	 */
	public String getEquipoV() {
		return equipoV;
	}


	/**
	 * @return the fInicApuesta
	 */
	public Calendar getfInicApuesta() {
		return fInicApuesta;
	}


	/**
	 * @param fInicApuesta the fInicApuesta to set
	 */
	public void setfInicApuesta(Calendar fInicApuesta) {
		this.fInicApuesta = fInicApuesta;
	}


	/**
	 * @return the fFinApuesta
	 */
	public Calendar getfFinApuesta() {
		return fFinApuesta;
	}


	/**
	 * @param fFinApuesta the fFinApuesta to set
	 */
	public void setfFinApuesta(Calendar fFinApuesta) {
		this.fFinApuesta = fFinApuesta;
	}
	
	public Map<TipoApuesta, ContenedorApuestas> verApuestasPartido(){
		return listaContenedorApuestas; 
	}
	
	


}


	