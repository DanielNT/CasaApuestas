package casaapuestas.equipos;


/**
 * @author Iss002
 * Clase que representa a un Equipo en la casa de apuestas.
 */
public class Equipo {
	
	private String nombre;
	private String nombreCompleto;
	
	/**
	 * Constructor del equipo
	 * 
	 * @param nombreEquipo nombre del equipo
	 * @param nombreCompleto Nombre completo del Equipo
	 */
	public Equipo(String nombreEquipo, String nombreCompleto){
		super();
		this.nombre = nombreEquipo;
		this.nombreCompleto = nombreCompleto;
	}
	
	/**
	 * Muestra la informaci�n b�sica del equipo. Sin ampliaci�n hace la misma funci�n
	 * que getNombre
	 * @return lo que pongamos
	 */
	public String verInfoEquipo() {
		return nombre;
	}

	/**
	 * Muestra la informaci�n completa del equipo
	 * 
	 * @return lo que pongamos
	 */
	public String verInfoCompleta() {
		return nombre + " (" + nombreCompleto + ")";
	}

	/**
	 * Muestra el nombre
	 * 
	 * @return el nombre
	 */
	public String getNombre() {
		return nombre;
	}

    /**
	 * Muestra el nombre completo
	 * 
	 * @return el nombre completo
	 */
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	
}