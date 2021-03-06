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
	 * Constructor que inicializa el partido con todos los par�metros
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
	 * Muestra toda la informaci�n del partido
	 * @return la ficha completa
	 */
	
	public String verInfoCompleta() {
		String ficha = "Partido " + idPartido + ": " + equipoL.getNombre() + "-" + equipoV.getNombre() + "(admite apuestas entre " + verFecha(fInicApuesta) + " y " + verFecha(fFinApuesta) + ")";
		return ficha;
	}
	
	
	// |------------------------------|
	
	/**
	 * Muestra la fecha del partido en el formato que se ha indicado. Sustituye al m�todo en desuso toLocaleString()
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
	 * Realiza una serie de comprobaciones con los par�metros que recibe como argumentos y luego contin�a con la creaci�n de una apuesta en el sistema.
	 * @param jugadorQueApuesta Pasa el jugador que apuesta como par�metro
	 * @param cantidadApostada La cantidad que se apuesta
	 * @param tApuesta La modalidad de apuesta
	 * @param resultado El resultado apostado
	 * @throws ExcepcionApuesta Si algo ha ido mal en la gesti�n de apuestas
	 * @throws ExcepcionCuenta Si algo ha ido mal en la gesti�n de cuentas
	 * @throws ExcepcionPartidos Si algo ha ido mal en la gesti�n de partidos
	 */
	public void a�adirAContenedorApuestas(Jugador jugadorQueApuesta, float cantidadApostada, TipoApuesta tApuesta, String resultado) throws ExcepcionApuesta, ExcepcionCuenta, ExcepcionPartidos
	{
		
		//Obtiene la fecha actual del sistema.
		Calendar fechaActual = Calendar.getInstance();
		fechaActual.getTime();
		
		// Comprueba que la fecha actual en la que se est� realizando la apuesta es v�lida.
		//Si no se cumple que la fecha actual es posterior a la fijada para el inicio de apuesta y anterior a la fijada para el final de apuesta....
		if(!((fechaActual.after(fInicApuesta)) && (fechaActual.before(fFinApuesta))))
		{
			// En las salidas por pantalla proporcionadas para la iteraci�n 1 es ExcepcionApuesta pero en la iteraci�n 2 es ExcepcionPartido. Se opta por la iteraci�n 2 pues adem�s de tener algo m�s de sentido es la iteraci�n final.
			//throw new ExcepcionApuesta(CausaExcepcionApuestas.PARTIDO_CERRADO, idPartido);
			throw new ExcepcionPartidos(CausaExcepcionPartidos.PARTIDO_CERRADO, idPartido);
		}
	
		String aux = equipoL.getNombre() + "-" + equipoV.getNombre();
		
		// Si no existe un contenedor de apuestas para la modalidad solicitada se crea uno nuevo y se a�ade la apuesta al mismo.
		if(!listaContenedorApuestas.containsKey(tApuesta))
		{		
			ContenedorApuestas ca = new ContenedorApuestas();
			listaContenedorApuestas.put(tApuesta, ca);
			
			ca.crearApuesta(jugadorQueApuesta, tApuesta, cantidadApostada, resultado, aux);	
		}
		else // Como el contenedor de apuestas de esta modalidad ya existe simplemente se le a�ade la apuesta.
		{
			// Recupera la instancia de la colecci�n
			ContenedorApuestas ca = listaContenedorApuestas.get(tApuesta);
			
			ca.crearApuesta(jugadorQueApuesta, tApuesta, cantidadApostada, resultado, aux);
		}
		
	}
	
	
	
	// Pretende ilustrar la trasformaci�n de un tipo de excepci�n en otro tipo de excepci�n con el fin de evitar "acoplamientos"
	//	try{
	//	ca.crearApuesta(jugadorQueApuesta, tApuesta, cantidadApostada, resultado);
	//}catch(ExcepcionCuenta ec){
	//	throw new ExcepcionApuesta(ec.getCausa(), jugadorQueApuesta.getLogin());
	//}	
	
	
	
	/**
	 * Este m�todo tras comprobar que el partido tiene resultados (est� por lo tanto finalizado) y que existe al menos una apuesta para la modalidad solicitada, procede a pagar las apuestas realizadas sobre el partido solicitado para la modalidad solicitada.
	 * @param tApuesta El tipo de modalidad
	 * @throws ExcepcionUsuario Si algo ha ido mal en la gesti�n de usuarios
	 * @throws ExcepcionApuesta Si algo ha ido mal en la gesti�n de partidos
	 */
	public void pagarApuestasModalidad(TipoApuesta tApuesta) throws ExcepcionUsuario, ExcepcionApuesta
	{	
		
		ContenedorApuestas ca = listaContenedorApuestas.get(tApuesta);
		
		// Comprueba si existe la modalidad.
		if(ca == null){
			throw new ExcepcionApuesta(CausaExcepcionApuestas.NO_EXISTE,idPartido);
		}
		
		// Comprueba si la modalidad est� pagada.
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
	 * @param tApuesta La modalidad de apuesta
	 * @param resultado El resultado que introducimos
	 * @throws ExcepcionPartidos Si algo ha ido mal en la gesti�n de partidos
	 * @throws ExcepcionApuesta Si algo ha ido mal en la gesti�n de partidos
	 */
	public void setResultadoPartido(TipoApuesta tApuesta, String resultado) throws ExcepcionPartidos, ExcepcionApuesta {
		
		Calendar aux;
		
		//Obtiene la fecha actual del sistema.
		Calendar fechaActual = Calendar.getInstance();
		fechaActual.getTime();
		
		// Obtiene en aux la fecha de final de apuestas que es la misma que la de inicio de apuestas y la suma 2 horas para determinar as� la de final del partido.
		aux = (Calendar) fFinApuesta.clone();
		aux.add(Calendar.HOUR, 2);
		
		// Sentencia para comprobar que es correcto el escenario de �xito de la iteraci�n 2.
		//aux.set(2015, Calendar.MAY, 22, 19, 30); // Llegado a este punto utilizo esta sentencia para establecer la fecha de aux a otra de inter�s y as� evitar que salte la excepci�n que viene a continuaci�n con el objetivo de comprobar el escenario de �xito.
		
		// Compruba si el partido est� finalizado. Si no est� finalizado lanza la correspondiente excepc�n.
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
		
		// Comprueba si las apuestas de esa modalidad est�n pagadas.
		if(ca.isModalidadPagada())
		{
			throw new ExcepcionApuesta(CausaExcepcionApuestas.APUESTAS_YA_PAGADAS,idPartido);
		}
		
		// No sabemos si hab�a que considerar la posibilidad de que una vez que se introduce un resultado para una modalidad ya no se puede introducir otro resultado para esa misma modalidad, es decir, sobreescribirlo. Ante la duda hemos optado por considerarlo y adjuntar este comentario. Si no hab�a que considerarlo bastar�a con comentar o eliminar el if que hay a continuaci�n de este comentario.
		if(listaResultados.containsKey(tApuesta))
		{
			throw new ExcepcionPartidos(CausaExcepcionPartidos.YA_EXISTE_RESULTADO,idPartido);
		}
		
		listaResultados.put(tApuesta, resultado);
	
	}
	
	// |------------------------------|
		
	
	
	/**
	 * Devuelve el identificador del partido
	 * @return el idPartido
	 */
	public String getIdPartido() {
		return idPartido;
	}
	
	/**
	 * Devuelve el equipo local
	 * @return el equipo local
	 */
	public Equipo getEquipoL() {
		return equipoL;
	}
	

	/**
	 * Cambia el equipo local
	 * @param equipoL El equipo local nuevo
	 */
	public void setEquipoL(Equipo equipoL) {
		this.equipoL = equipoL;
	}
	
	
	/**
	 * Devuelve el equipo visitante
	 * @return el equipo visitante
	 */
	public Equipo getEquipoV() {
		return equipoV;
	}

	/**
	 * Cambia el equipo visitante
	 * @param equipoV El equipo visitante nuevo
	 */
	public void setEquipoV(Equipo equipoV) {
		this.equipoV = equipoV;
	}

	/**
	 * Devuelve la fecha en la que se comienza a permitir nuevas apuestas
	 * @return the fInicApuesta
	 */
	public Calendar getfInicApuesta() {
		return fInicApuesta;
	}


	/**
	 * Cambia la fecha de inicio de las apuestas
	 * @param fInicApuesta La nueva fecha de inicio de apuestas
	 */
	public void setfInicApuesta(Calendar fInicApuesta) {
		this.fInicApuesta = fInicApuesta;
	}


	/**
	 * Devuelve la fecha en la que ya no se permiten nuevas apuestas
	 * @return la fecha de fin de apuesta
	 */
	public Calendar getfFinApuesta() {
		return fFinApuesta;
	}


	/**
	 * Cambia la fecha de fin de las apuestas
	 * @param fFinApuesta La nueva fecha de fin de apuestas
	 */
	public void setfFinApuesta(Calendar fFinApuesta) {
		this.fFinApuesta = fFinApuesta;
	}


	
	
	
	// |------------------------------|
	
	

}


	