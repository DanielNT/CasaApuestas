package casaapuestas.partidos;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import casaapuestas.apuestas.*;
import casaapuestas.arranque.*;
import casaapuestas.cuentas.*;
import casaapuestas.equipos.*;
import casaapuestas.usuarios.*;




/**
 * @author Iss002
 * Clase que representa a un Partido en la casa de apuestas.
 */
public class Partido {
	
	private String idPartido;
	private Equipo equipoL;
	private Equipo equipoV;
	private Calendar fInicApuesta;
	private Calendar fFinApuesta;
	private Map<TipoApuesta, ContenedorApuestas> listaContenedorApuestas;
	private Map<TipoApuesta, String> listaResultados;
	

	
	/**
	 * Constructor que inicializa el partido con todos los parámetros
	 * 
	 * @param idPartido El ID del partido
	 * @param equipoL Equipo local
	 * @param equipoV Equipo visitante
	 * @param fInicioApuesta La fecha de inicio de la apuesta
	 * @param fFinApuesta La fecha de fin de la apuesta
	 */
	
	public Partido(String idPartido, Equipo equipoL, Equipo equipoV, Calendar fInicioApuesta, Calendar fFinApuesta){
		
		super();
		this.idPartido = idPartido;
		this.equipoL = equipoL;
		this.equipoV = equipoV;
		this.fInicApuesta = fInicioApuesta;
		this.fFinApuesta = fFinApuesta;
		listaContenedorApuestas = new HashMap<TipoApuesta, ContenedorApuestas>();
		listaResultados = new HashMap<TipoApuesta, String>();
	
		
	}
	
	
	
	/**
	 * Muestra la info resumida del partido
	 * @return la ficha
	 */
	public String verInfoPartido() {
		String ficha = "Partido " + idPartido + ": " + equipoL.getNombre() + "-" + equipoV.getNombre();
		return ficha;
	}

	
	/**
	 * Muestra toda la información del partido
	 * @return la ficha completa
	 */
	
	public String verInfoCompleta() {
		String ficha = "Partido " + idPartido + ": " + equipoL.getNombre() + "-" + equipoV.getNombre() + "(admite apuestas entre " + verFecha(fInicApuesta) + " y " + verFecha(fFinApuesta) + ")";
		return ficha;
	}
	
	/**
	 * Muestra la fecha del partido en el formato que se ha indicado. Sustituye al método en desuso toLocaleString
	 * 
	 * @param c1 La fecha que queremos mostrar
	 * @return La fecha
	 */
	public String verFecha(Calendar c1)
	{
		//SimpleDateFormat nos permite introducir un formato personalizado, en vez de estar llamando a Calendar para la hora, mes, etc.
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy 'a las' hh:mm:ss");
		return sdf.format(c1.getTime());
		
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
	public Equipo getEquipoL() {
		return equipoL;
	}
	

	/**
	 * @param equipoL the equipoL to set
	 */
	public void setEquipoL(Equipo equipoL) {
		this.equipoL = equipoL;
	}
	
	
	/**
	 * @return the equipoV
	 */
	public Equipo getEquipoV() {
		return equipoV;
	}

	/**
	 * @param equipoV the equipoV to set
	 */
	public void setEquipoV(Equipo equipoV) {
		this.equipoV = equipoV;
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
	
	
	
	

	
	
	
	// |------------------------------|
	
	
	
	
	
	
	
	
	
	/**
	 * Realiza una serie de comprobaciones con los parámetros que recibe como argumentos y luego continúa con la creaciónde una apuesta en el sistema.
	 * @param jugadorQueApuesta
	 * @param cantidadApostada
	 * @param tApuesta
	 * @param resultado
	 * @throws ExcepcionApuesta
	 * @throws ExcepcionCuenta 
	 */
	public void añadirAContenedorApuestas(Jugador jugadorQueApuesta, float cantidadApostada, TipoApuesta tApuesta, String resultado) throws ExcepcionApuesta, ExcepcionCuenta
	{
	
		String aux = equipoL.getNombre() + "-" + equipoV.getNombre();
		
		// Si no existe un contenedor de apuestas para la modalidad solicitada se crea uno nuevo y se añade la apuesta al mismo.
		if(!listaContenedorApuestas.containsKey(tApuesta))
		{		
			ContenedorApuestas ca = new ContenedorApuestas();
			listaContenedorApuestas.put(tApuesta, ca);
			
				ca.crearApuesta(jugadorQueApuesta, tApuesta, cantidadApostada, resultado, aux);
			
//			try{
//				ca.crearApuesta(jugadorQueApuesta, tApuesta, cantidadApostada, resultado);
//			}catch(ExcepcionCuenta ec){
//				throw new ExcepcionApuesta(ec.getCausa(), jugadorQueApuesta.getLogin());
//			}
			
		}
		else // Como el contenedor de apuestas de esta modalidad ya existe simplemente se le añade la apuesta.
		{
			// Recupera la instancia de la colección
			ContenedorApuestas ca = listaContenedorApuestas.get(tApuesta);
			
			ca.crearApuesta(jugadorQueApuesta, tApuesta, cantidadApostada, resultado, aux);
		}
		
	}
	
	
	
	/**
	 * Crea un nuevo listado con los partidos mostrando para cada uno una ficha breve.
	 * 
	 * @return el listado
	 */
	public List<String> listarApuestas() {
		

		List<String> listadoApuestas = new ArrayList<String>();
		
		for (ContenedorApuestas ca : listaContenedorApuestas.values()) {
			listadoApuestas.addAll(ca.listarApuestas());
		}

		return listadoApuestas;
	}
	
	
	
	
	public void setResultadoPartido(TipoApuesta tApuesta, String resultado) {
		
		listaResultados.put(tApuesta, resultado);
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
//	public Map<TipoApuesta, ContenedorApuestas> verApuestasPartido(){
//		return listaContenedorApuestas; 
//	}
//
//	
//	public Map<TipoApuesta, ContenedorApuestas> verApuestasPartido(){
//		return listaContenedorApuestas; 
//	}
//	
//	public Map<TipoApuesta, String> verResultadosPartido(){
//		return listaResultados; 
//	}
//
//
//
//	public void setResultadoPartido(TipoApuesta tApuesta, String resultado) {
//		listaResultados.put(tApuesta,resultado);
//	}
//
//
//
//public void pagarApuestas(TipoApuesta tApuesta,String eqLocal, String eqVisitante, ControladorUsuarios cu) throws ExcepcionUsuario {
//		
//		String result=listaResultados.get(tApuesta);
//		
//		listaContenedorApuestas.get(tApuesta).pagarApuestasCont(listaResultados,result,eqLocal,eqVisitante, cu);
//		
//	}

}


	