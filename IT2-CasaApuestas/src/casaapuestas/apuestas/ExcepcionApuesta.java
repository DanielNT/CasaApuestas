package casaapuestas.apuestas;

/**
 * Clase de excepción que se lanzará cuando se intente realizar una operación no permitida con usuarios
 * 
 * @author iss002 
 */
public class ExcepcionApuesta extends Exception {

	/** Las clases que derivan de Exception deben tener un atributo como este. */
	private static final long serialVersionUID = 1L;
	/** El login usado en la operación que ha dado lugar a la excepción */
	private String login;
	/** La causa de la excepción */
	private CausaExcepcionApuestas causa;

	
	/**
	 * Constructor de la clase ExcepcionApuesta
	 * 
	 * @param causa Causa de la excepción
	 * @param login El login del usuario
	 */
	public ExcepcionApuesta(CausaExcepcionApuestas causa, String login) {
		super();
		// Asigna los parámetros
		this.causa = causa;
		this.login = login;
	}

	
	/**
	 * Método que devuelve el login
	 * 
	 * @return el login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Método que devuelve la causa que dio lugar a esta excepción
	 * 
	 * @return la causa
	 */
	public CausaExcepcionApuestas getCausa() {
		return causa;
	}

}
