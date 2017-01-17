package casaapuestas.usuarios;

/**
 * Clase de excepci�n que se lanzar� cuando se intente realizar una operaci�n no permitida con usuarios
 * 
 * @author Eduardo G�mez S�nchez, ETSIT UVa.
 */
public class ExcepcionUsuario extends Exception {

	/** Las clases que derivan de Exception deben tener un atributo como este. */
	private static final long serialVersionUID = 1L;
	/** El login usado en la operaci�n que ha dado lugar a la excecpci�n */
	private String login;
	/** La causa de la excecpci�n */
	private CausaExcepcionUsuario causa;

	/**
	 * Constructor que permite crear una excepci�n al operar sobre usuarios
	 * 
	 * @param causa la causa de la excepci�n
	 * @param login el identificador de usuario usado en la operaci�n que ha causado la excepci�n
	 */
	public ExcepcionUsuario(CausaExcepcionUsuario causa, String login) {
		super();
		// Asigna los par�metros
		this.causa = causa;
		this.login = login;
	}

	/**
	 * M�todo que devuelve el login que dio lugar a esta excepci�n
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
	public CausaExcepcionUsuario getCausa() {
		return causa;
	}

}
