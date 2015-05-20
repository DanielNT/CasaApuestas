package casaapuestas.apuestas;

import java.util.HashMap;
import java.util.Map;
import java.util.Calendar;

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
	
	private TipoApuesta tApuesta;
	private Jugador jugadorQueApuesta;
	//private Calendar fechaDeApuesta;
	private float cantidadApostada;
	private String resultado;
	private String equipos;
	private boolean resolucion;		// Atributo innecesario o no utilizado por ahora. Cuando se necesite ya se quitara este comentario y otros correspondientes.

	

	
	/**
	 * Constructor que crea una instancia de apuesta
	 * @param jugadorQueApuesta
	 * @param tApuesta 
	 * @param cantidadApostada
	 * @param resultado
	 */
	public Apuesta(Jugador jugadorQueApuesta, TipoApuesta tApuesta, float cantidadApostada, String resultado, String equipos){
	//public Apuesta(Jugador jugadorQueApuesta, Calendar fechaDeApuesta, float cantidadApostada, String resultado){
		
		super();
		this.tApuesta = tApuesta;
		this.jugadorQueApuesta = jugadorQueApuesta;
		//this.fechaDeApuesta = fechaDeApuesta;
		this.cantidadApostada = cantidadApostada;
		this.resultado=resultado;
		this.equipos = equipos;
		resolucion = false;
		
	}
	
	
	
	/**
	 * @return La informacion de la apuesta
	 */
	public String verInfoApuesta(){
		
		String info = "La apuesta de tipo " + tApuesta + ": El jugador '" + jugadorQueApuesta.getLogin() + "' ha apostado " + cantidadApostada + " al resultado '" + resultado + "' en el partido " + equipos;
		return info;
	}




	/**
	 * @return the resolucion
	 */
	public boolean isResolucion() {
		return resolucion;
	}




	/**
	 * @param resolucion the resolucion to set
	 */
	public void setResolucion(boolean resolucion) {
		this.resolucion = resolucion;
	}




	/**
	 * @return the cantidadApostada
	 */
	public float getCantidadApostada() {
		return cantidadApostada;
	}




	/**
	 * @return the resultado
	 */
	public String getResultado() {
		return resultado;
	}
	
	
	
	public void realizarTransaccionApuesta() throws ExcepcionCuenta{
		CuentaCasaApuestas CCA = CuentaCasaApuestas.getInstance();
		
		jugadorQueApuesta.realizarReintegro("Pago de apuesta sobre " + equipos, cantidadApostada);
		CCA.add(cantidadApostada);
	}
	
	
	
	// |------------------------------|

	
	public Jugador getJugador() {
		return jugadorQueApuesta;
	}
	
	public String getEquipos(){
		return equipos;
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
	
	
	
	
	
	
	
	
	
	
	
//	/**
//	 * @return the resolucion
//	 */
//	public boolean getResolucion() {
//		return resolucion;
//	}
//
//	
//	public void setResolucion(boolean resolucion){
//		this.resolucion=resolucion;
//	}
	
	

}