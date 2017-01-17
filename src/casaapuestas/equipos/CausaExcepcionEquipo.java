package casaapuestas.equipos;

/**
 * @author iss002
 * 
 * Tipo enumerado que se puede usar para especificar las causas de la excepci�n
 */
public enum CausaExcepcionEquipo {
	/**
	 * El equipo ya existe en el sistema.
	 */
	YA_EXISTE,
	/**
	 * El equipo que se solicita al sistema no existe.
	 */
	NO_EXISTE,
	/**
	 * El equipo est� duplicado, es decir son el mismo equipo.
	 */
	EQ_DUPLICADO,
	/**
	 * Uno o dos de los equipos no pertenecen a la lista. Es decir son err�neos o no existen.
	 */
	EQ_INCORRECTO
	
}
