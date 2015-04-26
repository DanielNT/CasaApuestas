package casaapuestas.partidos;

/**
 * Clase de excepción que se lanzará cuando se intente realizar una operación no permitida con usuarios
 * 
 * @author Eduardo Gómez Sánchez, ETSIT UVa.
 */
public class ExcepcionPartidos extends Exception {

	/** Las clases que derivan de Exception deben tener un atributo como este. */
	private static final long serialVersionUID = 1L;
	/** El login usado en la operación que ha dado lugar a la excecpción */
	private int idPartido;
	/** La causa de la excecpción */
	private CausaExcepcionPartidos causa;

	/**
	 * Constructor que permite crear una excepción al operar sobre usuarios
	 * 
	 * @param causa la causa de la excepción
	 * @param login el identificador de usuario usado en la operación que ha causado la excepción
	 */
	public ExcepcionPartidos(CausaExcepcionPartidos causa, int idPartido) {
		super();
		// Asigna los parámetros
		this.causa = causa;
		this.idPartido = idPartido;
	}

	/**
	 * Método que devuelve el login que dio lugar a esta excepción
	 * 
	 * @return el login
	 */
	public int getIdPartido() {
		return idPartido;
	}

	/**
	 * Método que devuelve la causa que dio lugar a esta excepción
	 * 
	 * @return la causa
	 */
	public CausaExcepcionPartidos getCausa() {
		return causa;
	}

}
