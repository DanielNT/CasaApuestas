package casaapuestas.equipos;


public class Equipo {
	
	private String nombre;
	private String nombreCompleto;
	
	/**
	 * Constructor del equipo
	 * 
	 * @param nombreEquipo nombre del equipo
	 */
	public Equipo(String nombreEquipo, String nombreCompleto){
		this.nombre = nombreEquipo;
		this.nombreCompleto = nombreCompleto;
	}
	
	/**
	 * Muestra la información básica del equipo
	 * 
	 * @return lo que pongamos
	 */
	public String verInfoEquipo() {
		return nombre;
	}

	/**
	 * Muestra la información completa del equipo
	 * 
	 * @return lo que pongamos
	 */
	public String verInfoCompleta() {
		return nombreCompleto;
	}
	
}