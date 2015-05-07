package casaapuestas.apuestas;

/**
 * Clase de excepci�n que se lanzar� cuando se intente realizar una operaci�n no permitida con usuarios
 * 
 * @author iss002 
 */
public class ExcepcionApuesta extends Exception {

	/** Las clases que derivan de Exception deben tener un atributo como este. */
	private static final long serialVersionUID = 1L;
	/** El login usado en la operaci�n que ha dado lugar a la excepci�n */
	private String login;
	/** La causa de la excepci�n */
	private CausaExcepcionApuestas causa;

	
	/**
	 * Constructor de la clase ExcepcionApuesta
	 * 
	 * @param causa Causa de la excepci�n
	 * @param login El login del usuario
	 */
	public ExcepcionApuesta(CausaExcepcionApuestas causa, String login) {
		super();
		// Asigna los par�metros
		this.causa = causa;
		this.login = login;
	}

	
	/**
	 * M�todo que devuelve el login
	 * 
	 * @return el login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * M�todo que devuelve la causa que dio lugar a esta excepci�n
	 * 
	 * @return la causa
	 */
	public CausaExcepcionApuestas getCausa() {
		return causa;
	}

}
