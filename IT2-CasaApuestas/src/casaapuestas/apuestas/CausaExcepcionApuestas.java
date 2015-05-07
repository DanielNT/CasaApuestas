package casaapuestas.apuestas;

/**
 * @author iss002
 * Tipo enumerado que se puede usar para especificar las causas de la excepci�n
 *
 */
public enum CausaExcepcionApuestas {
	/**
	 * Se ha intentado crear una apuesta, pero ya existe.
	 */
	ERROR_CREAR_APUESTA,
	/**
	 * La apuesta ya existe
	 */
	YA_EXISTE,
	/**
	 * El partido sobre el que se quiere realizar la apuesta est� cerrado a apuestas.
	 */
	PARTIDO_CERRADO
	
}
