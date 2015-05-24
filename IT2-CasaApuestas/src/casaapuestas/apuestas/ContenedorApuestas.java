package casaapuestas.apuestas;

import java.util.ArrayList;
import java.util.List;

import casaapuestas.cuentas.*;
import casaapuestas.usuarios.*;
import casaapuestas.constantes.*;

/**
 * Contiene las apuestas de un partido para una única modalidad.
 * @author ISS 002
 *
 */
public class ContenedorApuestas {
	
	private boolean modalidadPagada;
	/** La lista de apuestas del contenedor de apuestas */
	private List<Apuesta> listadoApuestas;
	
	/**
	 * Constructor de la clase ContenedorApuestas
	 */
	public ContenedorApuestas(){
		
		super();
		modalidadPagada = false;
		// Inicializa la lista de apuestas como un ArrayList vacío
		listadoApuestas = new ArrayList<Apuesta>();

	}
	
		
	// |------------------------------|
	
	
	/**
	 * Este método crea la nueva Apuesta correspondiente a los parámetros que recibe.
	 * @param jugadorQueApuesta
	 * @param tApuesta 
	 * @param cantidadApostada
	 * @param resultado
	 * @param equipos 
	 * @throws ExcepcionCuenta 
	 */
	public void crearApuesta(Jugador jugadorQueApuesta, TipoApuesta tApuesta, float cantidadApostada, String resultado, String equipos) throws ExcepcionCuenta
	{
		
//		//Obtiene la fecha actual del sistema.
//		Calendar fechaDeApuesta = Calendar.getInstance();
//		fechaDeApuesta.getTime();
			
		// Apuesta a = new Apuesta(jugadorQueApuesta, fechaDeApuesta, cantidadApostada, resultado, equipos);
		Apuesta a = new Apuesta(jugadorQueApuesta, tApuesta, cantidadApostada, resultado, equipos);
		a.realizarTransaccionApuesta();
		listadoApuestas.add(a);
	}
	
	
	/**
	 * Crea un nuevo listado con los partidos mostrando para cada uno una ficha breve.
	 * 
	 * @return el listado
	 */
	public List<String> listarApuestas() {
		
		List<String> listado = new ArrayList<String>();
		
		for (Apuesta a : listadoApuestas) {
			String ficha = a.verInfoApuesta();
			listado.add(ficha);
		}
		
		return listado;
	}
	
	
	
	
	// |------------------------------|
	
	
	
	
	
	
	/**
	 * Este método tras comprobar que no estan aún pagadas sus apuestas, procede a pagar las apuestas realizadas para la modalidad solicitada.
	 * @param resultado
	 * @throws ExcepcionUsuario
	 * @throws ExcepcionApuesta
	 */
	public void pagarApuestasContenedor(String resultado) throws ExcepcionUsuario, ExcepcionApuesta
	{
			
		float totalApostado = (float) 0.0;
		float totalGanador = (float) 0.0;
		float ratio;
		
		for(Apuesta a: listadoApuestas)
		{
			// calcula la cantidad total que se ha apostado entre todas las apuestas.
			totalApostado = totalApostado + a.getCantidadApostada();
			
			if(a.getResultado().equals(resultado))
			{
				// calcula cuanto del total apostado proviene de apuestas ganadoras.
				totalGanador = totalGanador + a.getCantidadApostada();
			}
		}
			
		// Obtiene el ratio de pago de premios.
		ratio = (float) Constantes.RATIO_PAGO*totalApostado/totalGanador;
			
		// Se pagan las apuestas ganadoras.
		for(Apuesta a: listadoApuestas)
		{
			if(a.getResultado().equals(resultado))
			{
				a.realizarPagoApuestaGanadora(ratio);
			}							
		}
			
		modalidadPagada = true;
	}

	// |------------------------------|
	
	
	/**
	 * @return the modalidadPagada
	 */
	public boolean isModalidadPagada() {
		return modalidadPagada;
	}
	
	
}