package casaapuestas.partidos;

/**
 * @author iss002
 * 
 * Tipo enumerado que se puede usar para especificar las causas de la excepción
 */
public enum CausaExcepcionPartidos {
	/**
	 * Ya existe el partido con ese identificador.
	 */
	YA_EXISTE_P,
	/**
	 * Ya existe ese equipo.
	 */
	YA_EXISTE_E,
	/**
	 * No existe el partido.
	 */
	NO_EXISTE,
	/**
	 * No hay partidos abiertos a apuestas.
	 */
	NO_PART_ABIERTOS_APUESTAS,
	/**
	 * La fecha de inicio de apuestas es posterior a la de fin de apuestas.
	 */
	FECHAS_INCORRECTAS,
	/**
	 * El partido aún no ha finalizado.
	 */
	PARTIDO_ABIERTO,
	/**
	 * El resultado para el partido ya existe.
	 */
	YA_EXISTE_RESULTADO,
	/**
	 * El partido sobre el que se quiere realizar la apuesta está cerrado a apuestas.
	 */
	PARTIDO_CERRADO
	
}
