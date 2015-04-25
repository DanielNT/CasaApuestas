package casaapuestas.apuestas;

import casaapuestas.partidos.Partido;
import casaapuestas.partidos.ResultadoQuiniela;



/**
 * @author iss002
 *
 */
public class Apuesta {
	
	private float cantidadApostada;
	private String login;
	private int idPartido;
	private tipoApuesta tipoApuesta;
	private ResultadoQuiniela rQuiniela;
	private String equipoLocal;
	private String equipoVisitante;
	
	
	/**
	 * Constructor de la clase apuesta
	 * 
	 * @param cantidadApostada La cantidad apostada
	 * @param login El login del jugador
	 * @param tApuesta El tipo de apuesta
	 * @param rQuiniela El resultado obtenido en modo quiniela
	 * @param idPartido El identificador del partido
	 * @param equipoLocal El nombre del equipo local
	 * @param equipoVisitante El nombre del equipo visitante
	 */
	public Apuesta(float cantidadApostada,String login,tipoApuesta tApuesta, ResultadoQuiniela rQuiniela,int idPartido, String equipoLocal, String equipoVisitante){
		
		this.cantidadApostada= cantidadApostada;
		this.idPartido = idPartido;
		this.equipoLocal = equipoLocal;
		this.equipoVisitante = equipoVisitante;
		this.tipoApuesta = tApuesta;
		this.rQuiniela = rQuiniela;
	}
	
	public String verApuesta() {
		String datosApuesta = idPartido + ": " + equipoLocal + "-" + equipoVisitante + " " + " Resultado quiniela "+ rQuiniela ;
		return datosApuesta;
	}
	
	
	
	/**
	 * Obtiene el identificador apuesta
	 * @return El identificador de apuesta
	 */
	public int getIdApuesta() {
		return idPartido;
	}

	/**
	 * @param login the login to set
	 */
	public void setIdApuesta(int idPartido) {
		this.idPartido= idPartido;
	}



	public enum tipoApuesta{
		/**
		 * Si es del tipo resultado (numérico)
		 */
		RESULTADO,
		
		/**
		 * Si es del tipo quiniela (1,X,2)
		 */
		QUINIELA
	}
}