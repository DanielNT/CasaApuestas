package casaapuestas.partidos;

/**
 * Clase de excepci�n que se lanzar� cuando se intente realizar una operaci�n no permitida con usuarios
 * 
 * @author Eduardo G�mez S�nchez, ETSIT UVa.
 */
public class ExcepcionPartidos extends Exception {

	/** Las clases que derivan de Exception deben tener un atributo como este. */
	private static final long serialVersionUID = 1L;
	/** El login usado en la operaci�n que ha dado lugar a la excecpci�n */
	private int idPartido;
	/** La causa de la excecpci�n */
	private CausaExcepcionPartidos causa;

	/**
	 * Constructor que permite crear una excepci�n al operar sobre usuarios
	 * 
	 * @param causa la causa de la excepci�n
	 * @param login el identificador de usuario usado en la operaci�n que ha causado la excepci�n
	 */
	public ExcepcionPartidos(CausaExcepcionPartidos causa, int idPartido) {
		super();
		// Asigna los par�metros
		this.causa = causa;
		this.idPartido = idPartido;
	}

	/**
	 * M�todo que devuelve el login que dio lugar a esta excepci�n
	 * 
	 * @return el login
	 */
	public int getIdPartido() {
		return idPartido;
	}

	/**
	 * M�todo que devuelve la causa que dio lugar a esta excepci�n
	 * 
	 * @return la causa
	 */
	public CausaExcepcionPartidos getCausa() {
		return causa;
	}

}
