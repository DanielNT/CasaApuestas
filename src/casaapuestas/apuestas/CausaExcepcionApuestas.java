package casaapuestas.apuestas;

/**
 * @author iss002
 * Tipo enumerado que se puede usar para especificar las causas de la excepción
 *
 */
public enum CausaExcepcionApuestas {
	/**
	 * Se ha intentado crear una apuesta, pero ya existe.
	 */
	ERROR_CREAR_APUESTA,
	/**
	 * La apuesta ya existe.
	 */
	YA_EXISTE,
	/**
	 * El partido sobre el que se quiere realizar la apuesta está cerrado a apuestas.
	 */
	PARTIDO_CERRADO,
	/**
	 * La modalidad de apuesta que se quiere realizar no existe.
	 */
	NO_EXISTE,
	/**
	 * Las apuestas que se solicitan pagar para un partido ya se han pagado.
	 */
	APUESTAS_YA_PAGADAS,
	/**
	 * Las apuestas no se pueden pagar.
	 */
	APUESTAS_NO_PAGABLES,
	/**
	 * El usuario no tiene fondos para pagar la cantidad que quiere apostar.
	 */
	USUARIO_SIN_FONDOS
	
}
