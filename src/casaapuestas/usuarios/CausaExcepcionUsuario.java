package casaapuestas.usuarios;

/**
 * Tipo enumerado ue se puede usar para especificar las causas de la excepci�n
 * 
 * @author Eduardo G�mez S�nchez, ETSIT UVa.
 */
public enum CausaExcepcionUsuario {
	/** Se ha buscado a un usuario por su login, pero no existe */
	NO_EXISTE,
	/** Se ha intentado crear a un usuario, pero ya existe otro con ese login */
	YA_EXISTE,
	/** El usuario intenta apostar m�s dinero del que tiene   */
	USUARIO_SIN_FONDOS
}