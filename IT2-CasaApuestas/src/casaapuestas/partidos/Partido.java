package casaapuestas.partidos;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import casaapuestas.apuestas.*;
import casaapuestas.cuentas.*;
import casaapuestas.equipos.*;
import casaapuestas.usuarios.*;




/**
 * @author Iss002
 * Clase que representa a un Partido en la casa de apuestas.
 */
public class Partido {
	
	private String idPartido;
	private Equipo equipoL;
	private Equipo equipoV;
	private Calendar fInicApuesta;
	private Calendar fFinApuesta;
	private Map<TipoApuesta, ContenedorApuestas> listaContenedorApuestas;
	private Map<TipoApuesta, String> listaResultados;
	

	
	/**
	 * Constructor que inicializa el partido con todos los parámetros
	 * 
	 * @param idPartido El ID del partido
	 * @param equipoL Equipo local
	 * @param equipoV Equipo visitante
	 * @param fInicioApuesta La fecha de inicio de la apuesta
	 * @param fFinApuesta La fecha de fin de la apuesta
	 */
	
	public Partido(String idPartido, Equipo equipoL, Equipo equipoV, Calendar fInicioApuesta, Calendar fFinApuesta){
		
		super();
		this.idPartido = idPartido;
		this.equipoL = equipoL;
		this.equipoV = equipoV;
		this.fInicApuesta = fInicioApuesta;
		this.fFinApuesta = fFinApuesta;
		listaContenedorApuestas = new HashMap<TipoApuesta, ContenedorApuestas>();
		listaResultados = new HashMap<TipoApuesta, String>();
	
		
	}
	
	
	// |------------------------------|
	
	
	/**
	 * Muestra la info resumida del partido
	 * @return la ficha
	 */
	public String verInfoPartido() {
		String ficha = "Partido " + idPartido + ": " + equipoL.getNombre() + "-" + equipoV.getNombre();
		return ficha;
	}

	
	/**
	 * Muestra toda la información del partido
	 * @return la ficha completa
	 */
	
	public String verInfoCompleta() {
		String ficha = "Partido " + idPartido + ": " + equipoL.getNombre() + "-" + equipoV.getNombre() + "(admite apuestas entre " + verFecha(fInicApuesta) + " y " + verFecha(fFinApuesta) + ")";
		return ficha;
	}
	
	
	// |------------------------------|
	
	/**
	 * Muestra la fecha del partido en el formato que se ha indicado. Sustituye al método en desuso toLocaleString()
	 * @param c La fecha que queremos mostrar
	 * @return La fecha
	 */
	public String verFecha(Calendar c)
	{
		//SimpleDateFormat nos permite introducir un formato personalizado, en vez de estar llamando a Calendar para la hora, mes, etc.
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy kk:mm");
		return sdf.format(c.getTime());
		
	}

	
	// |------------------------------|
	
	
	/**
	 * Crea un nuevo listado con todas las apuestas para un partido concreto mostrando para cada una una ficha.
	 * 
	 * @return el listado
	 */
	public List<String> listarApuestas() {
		

		List<String> listadoApuestas = new ArrayList<String>();
		
		for (ContenedorApuestas ca : listaContenedorApuestas.values()) {
			listadoApuestas.addAll(ca.listarApuestas());
		}

		return listadoApuestas;
	}
	
	
	/**
	 * Realiza una serie de comprobaciones con los parámetros que recibe como argumentos y luego continúa con la creación de una apuesta en el sistema.
	 * @param jugadorQueApuesta
	 * @param cantidadApostada
	 * @param tApuesta
	 * @param resultado
	 * @throws ExcepcionApuesta
	 * @throws ExcepcionCuenta 
	 * @throws ExcepcionPartidos 
	 */
	public void añadirAContenedorApuestas(Jugador jugadorQueApuesta, float cantidadApostada, TipoApuesta tApuesta, String resultado) throws ExcepcionApuesta, ExcepcionCuenta, ExcepcionPartidos
	{
		
		//Obtiene la fecha actual del sistema.
		Calendar fechaActual = Calendar.getInstance();
		fechaActual.getTime();
		
		// Comprueba que la fecha actual en la que se está realizando la apuesta es válida.
		//Si no se cumple que la fecha actual es posterior a la fijada para el inicio de apuesta y anterior a la fijada para el final de apuesta....
		if(!((fechaActual.after(fInicApuesta)) && (fechaActual.before(fFinApuesta))))
		{
			// En las salidas por pantalla proporcionadas para la iteración 1 es ExcepcionApuesta pero en la iteración 2 es ExcepcionPartido. Se opta por la iteración 2 pues además de tener algo más de sentido es la iteración final.
			//throw new ExcepcionApuesta(CausaExcepcionApuestas.PARTIDO_CERRADO, idPartido);
			throw new ExcepcionPartidos(CausaExcepcionPartidos.PARTIDO_CERRADO, idPartido);
		}
	
		String aux = equipoL.getNombre() + "-" + equipoV.getNombre();
		
		// Si no existe un contenedor de apuestas para la modalidad solicitada se crea uno nuevo y se añade la apuesta al mismo.
		if(!listaContenedorApuestas.containsKey(tApuesta))
		{		
			ContenedorApuestas ca = new ContenedorApuestas();
			listaContenedorApuestas.put(tApuesta, ca);
			
			ca.crearApuesta(jugadorQueApuesta, tApuesta, cantidadApostada, resultado, aux);	
		}
		else // Como el contenedor de apuestas de esta modalidad ya existe simplemente se le añade la apuesta.
		{
			// Recupera la instancia de la colección
			ContenedorApuestas ca = listaContenedorApuestas.get(tApuesta);
			
			ca.crearApuesta(jugadorQueApuesta, tApuesta, cantidadApostada, resultado, aux);
		}
		
	}
	
	
	
	// Pretende ilustrar la trasformación de un tipo de excepción en otro tipo de excepción con el fin de evitar "acoplamientos"
	//	try{
	//	ca.crearApuesta(jugadorQueApuesta, tApuesta, cantidadApostada, resultado);
	//}catch(ExcepcionCuenta ec){
	//	throw new ExcepcionApuesta(ec.getCausa(), jugadorQueApuesta.getLogin());
	//}	
	
	
	
	/**
	 * Este método tras comprobar que el partido tiene resultados (está por lo tanto finalizado) y que existe al menos una apuesta para la modalidad solicitada, procede a pagar las apuestas realizadas sobre el partido solicitado para la modalidad solicitada.
	 * @param tApuesta
	 * @throws ExcepcionUsuario
	 * @throws ExcepcionApuesta 
	 */
	public void pagarApuestasModalidad(TipoApuesta tApuesta) throws ExcepcionUsuario, ExcepcionApuesta
	{	
		
		ContenedorApuestas ca = listaContenedorApuestas.get(tApuesta);
		
		// Comprueba si existe la modalidad.
		if(ca == null){
			throw new ExcepcionApuesta(CausaExcepcionApuestas.NO_EXISTE,idPartido);
		}
		
		// Comprueba si la modalidad está pagada.
		if(ca.isModalidadPagada())
		{
			throw new ExcepcionApuesta(CausaExcepcionApuestas.APUESTAS_YA_PAGADAS,idPartido);
		}
		
		//Comprueba si existe un resultado para la modalidad.
		if(!(listaResultados.containsKey(tApuesta)))
		{
			throw new ExcepcionApuesta(CausaExcepcionApuestas.APUESTAS_NO_PAGABLES,idPartido);
		}
		
		String resultado = listaResultados.get(tApuesta);

		ca.pagarApuestasContenedor(resultado);
			
	}
	
	
	// |------------------------------|
	
	
	/**
	 * Colecciona el resultado del partido para una modalidad concreta en el correspondiente listado.
	 * @param tApuesta
	 * @param resultado
	 * @throws ExcepcionPartidos 
	 * @throws ExcepcionApuesta 
	 */
	public void setResultadoPartido(TipoApuesta tApuesta, String resultado) throws ExcepcionPartidos, ExcepcionApuesta {
		
		Calendar aux;
		
		//Obtiene la fecha actual del sistema.
		Calendar fechaActual = Calendar.getInstance();
		fechaActual.getTime();
		
		// Obtiene en aux la fecha de final de apuestas que es la misma que la de inicio de apuestas y la suma 2 horas para determinar así la de final del partido.
		aux = (Calendar) fFinApuesta.clone();
		aux.add(Calendar.HOUR, 2);
		
//		// Sentencia para comprobar que es correcto el escenario de éxito de la iteración 2.
//		aux.set(2015, Calendar.MAY, 22, 19, 30); // LLegado a este punto utilizo esta sentencia para establecer la fecha de aux a otra de interés y así evitar que salte la excepción que viene a continuación con el objetivo de comprobar el escenario de éxito.
		
		// Compruba si el partido está finalizado. Si no está finalizado lanza la correspondiente excepcón.
		if(!(fechaActual.after(aux)))
		{
			throw new ExcepcionPartidos(CausaExcepcionPartidos.PARTIDO_ABIERTO,idPartido);
		}
		
		// Comprueba si existe la modalidad.
		if(!(listaContenedorApuestas.containsKey(tApuesta)))
		{
			throw new ExcepcionApuesta(CausaExcepcionApuestas.NO_EXISTE,idPartido);
		}
		
		ContenedorApuestas ca = listaContenedorApuestas.get(tApuesta);
		
		// Comprueba si las apuestas de esa modalidad están pagadas.
		if(ca.isModalidadPagada())
		{
			throw new ExcepcionApuesta(CausaExcepcionApuestas.APUESTAS_YA_PAGADAS,idPartido);
		}
		
		// No sabemos si había que considerar la posibilidad de que una vez que se introduce un resultado para una modalidad ya no se puede introducir otro resultado para esa misma modalidad, es decir, sobreescribirlo. Ante la duda hemos optado por considerarlo y adjuntar este comentario. Si no había que considerarlo bastaría con comentar o eliminar el if que hay a continuación de este comentario.
		if(listaResultados.containsKey(tApuesta))
		{
			throw new ExcepcionPartidos(CausaExcepcionPartidos.YA_EXISTE_RESULTADO,idPartido);
		}
		
		listaResultados.put(tApuesta, resultado);
	
	}
	
	// |------------------------------|
		
	
	
	/**
	 * @return the idPartido
	 */
	public String getIdPartido() {
		return idPartido;
	}
	
	/**
	 * @return the equipoL
	 */
	public Equipo getEquipoL() {
		return equipoL;
	}
	

	/**
	 * @param equipoL the equipoL to set
	 */
	public void setEquipoL(Equipo equipoL) {
		this.equipoL = equipoL;
	}
	
	
	/**
	 * @return the equipoV
	 */
	public Equipo getEquipoV() {
		return equipoV;
	}

	/**
	 * @param equipoV the equipoV to set
	 */
	public void setEquipoV(Equipo equipoV) {
		this.equipoV = equipoV;
	}

	/**
	 * @return the fInicApuesta
	 */
	public Calendar getfInicApuesta() {
		return fInicApuesta;
	}


	/**
	 * @param fInicApuesta the fInicApuesta to set
	 */
	public void setfInicApuesta(Calendar fInicApuesta) {
		this.fInicApuesta = fInicApuesta;
	}


	/**
	 * @return the fFinApuesta
	 */
	public Calendar getfFinApuesta() {
		return fFinApuesta;
	}


	/**
	 * @param fFinApuesta the fFinApuesta to set
	 */
	public void setfFinApuesta(Calendar fFinApuesta) {
		this.fFinApuesta = fFinApuesta;
	}


	
	
	
	// |------------------------------|
	
	

}


	