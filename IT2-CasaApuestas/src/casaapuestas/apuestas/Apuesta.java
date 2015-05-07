package casaapuestas.apuestas;

import java.util.HashMap;
import java.util.Map;

import casaapuestas.arranque.*;
import casaapuestas.cuentas.*;
import casaapuestas.equipos.*;
import casaapuestas.partidos.*;
import casaapuestas.usuarios.*;



/**
 * @author iss002
 * Clase que representa a una Apuesta en la casa de apuestas.
 */
public class Apuesta {
	
	private float cantidadApostada;
	private String login;
	private String idPartido;
	private TipoApuesta tipoApuesta;
	private String resultado;
	private String eqLocal;
	private String eqVisitante;
	private boolean resolucion;		// Atributo innecesario o no utilizado por ahora. Cuando se necesite ya se quitara este comentario y otros correspondientes.
	private Map<String, Jugador> listaJugadores; // Atributo innecesario o no utilizado por ahora. Cuando se necesite ya se quitara este comentario y otros correspondientes.

	

	/**
	 * Constructor que crea una instancia de apuesta
	 * 
	 * @param login El login del usuario
	 * @param idPartido El id del partido
	 * @param tApuesta Tipo de la apuesta (Marcador o quiniela)
	 * @param resultado Resultado, depende del modo
	 * @param cantidadApostada Cantidad que se apuesta (con decimales)
	 * @param eqLocal Equipo local en el partido sobre el que se apuesta
	 * @param eqVisitante Equipo visitante en el partido sobre el que se apuesta
	 */
	public Apuesta(String login, String idPartido, TipoApuesta tApuesta, String resultado, float cantidadApostada, String eqLocal, String eqVisitante){
		
		super();
		this.login=login;
		this.idPartido= idPartido;
		this.tipoApuesta=tApuesta;
		this.resultado=resultado;
		this.cantidadApostada=cantidadApostada;
		this.eqLocal=eqLocal;
		this.eqVisitante=eqVisitante;
		resolucion = false;
		listaJugadores = new HashMap<String, Jugador>();
		
	}
	

	

	/**
	 * @return La informacion de la apuesta
	 */
	public String verInfoApuesta(){
		
		String info= "La apuesta de tipo " + tipoApuesta + ": El jugador '" + login + "' ha apostado " + cantidadApostada + " al resultado '" + resultado + "' en el partido " + eqLocal + "-" + eqVisitante;
		return info;
	}
	
	
	
	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}
	
	
	/**
	 * @return the idPartido
	 */
	public String getIdPartido() {
		return idPartido;
	}
	
	
	/**
	 * @return the tipoApuesta
	 */
	public TipoApuesta getTipoApuesta() {
		return tipoApuesta;
	}
	
		
	/**
	 * @return the resultado
	 */
	public String getResultado() {
		return resultado;
	}
	
	
	/**
	 * @return the cantidadApostada
	 */
	public float getCantidadApostada() {
		return cantidadApostada;
	}


//	/**
//	 * @return the resolucion
//	 */
//	public boolean isResolucion() {
//		return resolucion;
//	}
//
//
//	/**
//	 * @return the listaJugadores
//	 */
//	public Map<String, Jugador> getListaJugadores() {
//		return listaJugadores;
//	}
	
	

}