package casaapuestas.apuestas;


import casaapuestas.cuentas.*;
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
	
	

	
	/**
	 * Constructor que crea una instancia de apuesta
	 * @param jugadorQueApuesta
	 * @param tApuesta 
	 * @param cantidadApostada
	 * @param resultado
	 * @param equipos 
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
		
	}
	
	
	// |------------------------------|
	
	
	/**
	 * @return La informacion de la apuesta
	 */
	public String verInfoApuesta(){
		
		String info = "La apuesta de tipo " + tApuesta + ": El jugador '" + jugadorQueApuesta.getLogin() + "' ha apostado " + cantidadApostada + " al resultado '" + resultado + "' en el partido " + equipos;
		return info;
	}
	
	

	// |------------------------------|
	

	/**
	 * Realiza el pago de la apuesta que ingresa en la cuenta de la casa de apuestas.
	 * @throws ExcepcionCuenta
	 */
	public void realizarTransaccionApuesta() throws ExcepcionCuenta{
		
		// Recupera la instancia de la casa de apuestas que es una clase Singleton
		CuentaCasaApuestas CCA = CuentaCasaApuestas.getInstance();
		// Realiza el pago de la apuesta en la cuenta del jugador
		jugadorQueApuesta.realizarReintegro("Pago de apuesta sobre " + equipos, cantidadApostada);
		// El pago se añade a la cuenta de la casa de apuestas.
		CCA.add(cantidadApostada);
	}
	
	
	
	/**
	 * Realiza el cobro de la apuesta que retira de la cuenta de la casa de apuestas.
	 * @param ratio
	 * @throws ExcepcionCuenta
	 */
	public void realizarPagoApuestaGanadora(float ratio){
		
		// Recupera la instancia de la casa de apuestas que es una clase Singleton
		CuentaCasaApuestas CCA = CuentaCasaApuestas.getInstance();
		// Realiza el ingreso de la apuesta en la cuenta del jugador
		jugadorQueApuesta.realizarIngreso("Cobro de premio de apuesta sobre " + equipos, ratio*cantidadApostada);
		// El ingreso se retira a la cuenta de la casa de apuestas.
		CCA.substract(ratio*cantidadApostada);
	}
	
	
	
	
	// |------------------------------|
	
	
	

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
	
	
	// |------------------------------|


}