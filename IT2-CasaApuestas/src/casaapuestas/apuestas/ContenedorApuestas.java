package casaapuestas.apuestas;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import casaapuestas.cuentas.*;
import casaapuestas.usuarios.*;

/**
 * Contiene las apuestas de un partido para una �nica modalidad.
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
		// Inicializa la lista de apuestas como un ArrayList vac�o
		listadoApuestas = new ArrayList<Apuesta>();

	}
	
		
	// |------------------------------|
	
	
	/**
	 * Este m�todo crea la nueva Apuesta correspondiente a los par�metros que recibe.
	 * @param jugadorQueApuesta
	 * @param tApuesta 
	 * @param cantidadApostada
	 * @param resultado
	 * @param equipos 
	 * @throws ExcepcionCuenta 
	 */
	public void crearApuesta(Jugador jugadorQueApuesta, TipoApuesta tApuesta, float cantidadApostada, String resultado, String equipos) throws ExcepcionCuenta
	{
		
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
	 * Este m�todo tras comprobar que no estan a�n pagadas sus apuestas, procede a pagar las apuestas realizadas para la modalidad solicitada.
	 * @param resultado El resultado de la modalidad de apuesta
	 * @throws ExcepcionUsuario Si algo ha ido mal en el pago
	 * @throws ExcepcionApuesta Si algo ha ido mal respecto a las apuestas
	 */
	public void pagarApuestasContenedor(String resultado) throws ExcepcionUsuario, ExcepcionApuesta
	{
			
		float totalApostado = (float) 0.0;
		float totalGanador = (float) 0.0;
		float ratio=0;
		String CONFIG_RATIO="configuration.ratio";
		
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
			
		
		
		//Obtenemos el valor del ratio de las propierties
		Properties prop = new Properties();
				
		try {
			//Busca el archivo en la carpeta resources
			//FileInputStream input = new FileInputStream("resources/config.properties");
			
			//Busca el archivo en la carpeta src
			FileInputStream input = new FileInputStream("src/config.properties");
			
			//Lo carga en las propierties
			prop.load(input);
					
			//Si no se encontrara la propiedad, lo carga por defecto a 0.8 (se puede quitar el primer argumento) y lo convierte a flotante
			//Hay que parsear a float porque getProperty devuelve una cadena String
			//ratio=Float.parseFloat(prop.getProperty(CONFIG_RATIO, "0.8")) *totalApostado/totalGanador;
				
					
			//Si suponemos que nunca va a haber problemas (como que est� mal escrita la propiedad, por ejemplo), entonces basta con
			ratio=Float.parseFloat(prop.getProperty(CONFIG_RATIO)) *totalApostado/totalGanador;
				
		} catch (IOException ex) {
			
			//En este caso pedimos que muestre el camino que le ha llevado a la excepci�n (tambi�n se podr�a hacer similar al resto de excepciones)
			ex.printStackTrace();
		}
		
			
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
	 * Devuelve un par�metro booleano que indica si la modalidad del contenedor de apuestas se ha pagado
	 * @return si la modalidad est� pagada
	 */
	public boolean isModalidadPagada() {
		return modalidadPagada;
	}
	
	
}