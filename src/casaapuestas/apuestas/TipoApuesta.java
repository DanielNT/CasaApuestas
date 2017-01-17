package casaapuestas.apuestas;


/**
 * Enum que contiene los tipos de apuesta disponibles en el sistema.
 * 
 * @author iss002
 *
 */
public enum TipoApuesta{
	
	/**
	 * Especifica que el tipo de apuesta va a ser de tipo marcador, es decir, un resultado de tipo 'a-b' donde a y b son los goles para cada equipo del partido.
	 */
	MARCADOR,
	/**
	 * Especifica que el tipo de apuesta va a ser de tipo quiniela, es decir, un resultado de tipo '1', 'X' o '2'.
	 */
	QUINIELA,
	/**
	 * Especifica que el tipo de apuesta va a ser de tipo corner, es decir, un resultado de tipo '1', 'X' o '2' que determina el equipo con mayor número de saques de esquina en el partido o empate.
	 */
	CORNERS
}
	
