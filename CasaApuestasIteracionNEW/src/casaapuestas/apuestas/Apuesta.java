package casaapuestas.apuestas;



/**
 * @author iss002
 *
 */
public class Apuesta {
	
	private float cantidadApostada;
	private String login;
	private String idPartido;
	private TipoApuesta tipoApuesta;
	private String resultado;
	private String eqLocal;
	private String eqVisitante;

	

	/**
	 * Constructor que crea una instancia de apuesta
	 * 
	 * @param login El login del usuario
	 * @param idPartido El id del partido
	 * @param tApuesta Tipo de la apuesta (Marcador o quiniela)
	 * @param resultado Resultado, depende del modo
	 * @param cantidadApostada Cantidad que se apuesta (con decimales)
	 */
	public Apuesta(String login, String idPartido, TipoApuesta tApuesta, String resultado,float cantidadApostada, String eqLocal, String eqVisitante){
		
		this.login=login;
		this.idPartido= idPartido;
		this.tipoApuesta=tApuesta;
		this.resultado=resultado;
		this.cantidadApostada=cantidadApostada;
		this.eqLocal=eqLocal;
		this.eqVisitante=eqVisitante;
		
	}

	public String verInfoApuesta(){
		
		String info= "La apuesta de tipo " + tipoApuesta + ": El jugador '" + login + "' ha apostado " + cantidadApostada + " al resultado '" + resultado + "' en el partido " + eqLocal + "-" + eqVisitante;
		return info;
	}
	
	
	
	/**
	 * @return the cantidadApostada
	 */
	public float getCantidadApostada() {
		return cantidadApostada;
	}


	/**
	 * @param cantidadApostada the cantidadApostada to set
	 */
	public void setCantidadApostada(float cantidadApostada) {
		this.cantidadApostada = cantidadApostada;
	}



	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}


	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}



	/**
	 * @return the idPartido
	 */
	public String getIdPartido() {
		return idPartido;
	}



	/**
	 * @param idPartido the idPartido to set
	 */
	public void setIdPartido(String idPartido) {
		this.idPartido = idPartido;
	}



	/**
	 * @return the tipoApuesta
	 */
	public TipoApuesta getTipoApuesta() {
		return tipoApuesta;
	}



	/**
	 * @param tipoApuesta the tipoApuesta to set
	 */
	public void setTipoApuesta(TipoApuesta tipoApuesta) {
		this.tipoApuesta = tipoApuesta;
	}



	/**
	 * @return the resultado
	 */
	public String getResultado() {
		return resultado;
	}



	/**
	 * @param resultado the resultado to set
	 */
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

//	/**
//	 * @return the eqLocal
//	 */
//	private String getEqLocal() {
//		return eqLocal;
//	}
//
//	/**
//	 * @param eqLocal the eqLocal to set
//	 */
//	private void setEqLocal(String eqLocal) {
//		this.eqLocal = eqLocal;
//	}
//
//	/**
//	 * @return the eqVisitante
//	 */
//	private String getEqVisitante() {
//		return eqVisitante;
//	}
//
//	/**
//	 * @param eqVisitante the eqVisitante to set
//	 */
//	private void setEqVisitante(String eqVisitante) {
//		this.eqVisitante = eqVisitante;
//	}
//	

}