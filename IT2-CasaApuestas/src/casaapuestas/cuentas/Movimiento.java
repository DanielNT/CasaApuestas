package casaapuestas.cuentas;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Clase que representa un movimiento en una cuenta. Tiene una fecha, que se asigna en la creaci�n, un concepto y un importe. Ning�n atributo puede moficarse.
 * 
 * @author Eduardo G�mez S�nchez, ETSIT UVa.
 */
public class Movimiento {
	/** Fecha del movimiento, como una cadena en espa�ol */
	private String fecha;
	/** Concepto del movimiento */
	private String concepto;
	/** Importe del movimiento (positivo es un ingreso, negativo es un reintegro) */
	private float importe;

	/**
	 * Contructor que crea una instancia de movimiento
	 * 
	 * @param concepto el concepto de este movimiento
	 * @param importe el importe de este movimiento (positivo es un ingreso, negativo es un reintegro)
	 */
	public Movimiento(String concepto, float importe) {
		super();
		// Primero crea un formateador de fecha, para conseguir dar a la fecha un formato de cadena
		DateFormat formateadorFecha = DateFormat.getDateInstance(DateFormat.MEDIUM, new Locale("es", "ES"));
		// Luego obtiene del sistema el momento actual
		Date ahora = new Date();
		// Finalmente asigna al atributo la fecha formateada como una cadena
		fecha = formateadorFecha.format(ahora);
		// El resto de los atributos los inicializa con los par�metros
		this.concepto = concepto;
		this.importe = importe;
	}

	/**
	 * M�todo que devuelve la fecha del movimiento, como una cadena imprimible en espa�ol
	 * 
	 * @return la fecha, como una cadena
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * M�todo que devuelve el concepto del movimiento
	 * 
	 * @return el concepto
	 */
	public String getConcepto() {
		return concepto;
	}

	/**
	 * M�todo que devuelve el importe del movimiento (positivo es un ingreso, negativo es un reintegro)
	 * 
	 * @return el importe
	 */
	public float getImporte() {
		return importe;
	}

	/**
	 * M�todo que devuelve en una sola cadena toda la informaci�n del movimiento
	 * @return una cadena con toda la informaci�n del movimiento
	 */
	public String verFicha() {
		return String.format("%.2f", importe) + " euros, por '" + concepto + "', con fecha " + fecha;
	}

}
