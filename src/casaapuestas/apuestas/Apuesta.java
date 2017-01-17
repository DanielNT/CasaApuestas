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
	private float cantidadApostada;
	private String resultado;
	private String equipos;
	
	
	/**
	 * Constructor que crea una instancia de apuesta
	 * @param jugadorQueApuesta El jugador que realiza la apuesta
	 * @param tApuesta El tipo de apuesta (modalidad)
	 * @param cantidadApostada La cantidad que apuesta el jugador
	 * @param resultado El resultado al que apuesta en la modalidad
	 * @param equipos Los equipos local y visitante
	 */
	public Apuesta(Jugador jugadorQueApuesta, TipoApuesta tApuesta, float cantidadApostada, String resultado, String equipos){
		
		super();
		this.tApuesta = tApuesta;
		this.jugadorQueApuesta = jugadorQueApuesta;
		this.cantidadApostada = cantidadApostada;
		this.resultado=resultado;
		this.equipos = equipos;
		
	}
	
	
	// |------------------------------|
	
	
	/**
	 * Devuelve la información de la apuesta, que es una cadena que utiliza diferentes parámetros
	 * @return La informacion de la apuesta
	 */
	public String verInfoApuesta(){
		String info = "La apuesta de tipo " + tApuesta + ": El jugador '" + jugadorQueApuesta.getLogin() + "' ha apostado " + cantidadApostada + " al resultado '" + resultado + "' en el partido " + equipos;
		
		return info;
	}
	
	

	// |------------------------------|
	

	/**
	 * Realiza el pago de la apuesta que ingresa en la cuenta de la casa de apuestas.
	 * @throws ExcepcionCuenta Si algo ha ido mal en la gestión de cuentas
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
	 * @param ratio El ratio que se utiliza para pagar
	 * @throws ExcepcionCuenta Si algo ha ido mal al realizar el pago
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
	 * Devuelve en un float la cantidad apostada
	 * @return la cantidad apostada
	 */
	public float getCantidadApostada() {
		return cantidadApostada;
	}


	/**
	 * Devuelve el resultado
	 * @return el resultado
	 */
	public String getResultado() {
		return resultado;
	}
	
	
	// |------------------------------|


}