package casaapuestas.equipos;

/**
 * Clase de excepción que se lanzará cuando se intente realizar una operación no permitida con usuarios
 * 
 * @author Eduardo Gómez Sánchez, ETSIT UVa.
 */
public class ExcepcionEquipo extends Exception {

	/** Las clases que derivan de Exception deben tener un atributo como este. */
	private static final long serialVersionUID = 1L;
	/** El login usado en la operación que ha dado lugar a la excecpción */
	private String idEquipo;
	/** La causa de la excecpción */
	private CausaExcepcionEquipo causa;

	/**
	 * Constructor que permite crear una excepción al operar sobre usuarios
	 * 
	 * @param causa la causa de la excepción
	 * @param login el identificador de usuario usado en la operación que ha causado la excepción
	 */
	public ExcepcionEquipo(CausaExcepcionEquipo causa, String nombre) {
		super();
		// Asigna los parámetros
		this.causa = causa;
		this.idEquipo = nombre;
	}

	
	public String getIdEquipo() {
		return idEquipo;
	}

	/**
	 * Método que devuelve la causa que dio lugar a esta excepción
	 * 
	 * @return la causa
	 */
	public CausaExcepcionEquipo getCausa() {
		return causa;
	}

}
