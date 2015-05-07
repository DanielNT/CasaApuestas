package casaapuestas.equipos;

/**
 * Clase de excepci�n que se lanzar� cuando se intente realizar una operaci�n no permitida con equipos
 * 
 * @author Iss002
 */
public class ExcepcionEquipo extends Exception {

	/** Las clases que derivan de Exception deben tener un atributo como este. */
	private static final long serialVersionUID = 1L;
	/** El login usado en la operaci�n que ha dado lugar a la excecpci�n */
	private String idEquipo;
	/** La causa de la excecpci�n */
	private CausaExcepcionEquipo causa;

	/**
	 * Constructor que permite crear una excepci�n al operar sobre usuarios
	 * 
	 * @param causa la causa de la excepci�n
	 * @param nombre nombre del equipo
	 */
	public ExcepcionEquipo(CausaExcepcionEquipo causa, String nombre) {
		super();
		// Asigna los par�metros
		this.causa = causa;
		this.idEquipo = nombre;
	}

	
	/**
	 * @return el identificador del equipo
	 */
	public String getIdEquipo() {
		return idEquipo;
	}

	/**
	 * M�todo que devuelve la causa que dio lugar a esta excepci�n
	 * 
	 * @return la causa
	 */
	public CausaExcepcionEquipo getCausa() {
		return causa;
	}

}
