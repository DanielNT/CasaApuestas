package casaapuestas.partidos;

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
 * Controlador de partidos, integra el de apuestas y el de equipos
 * 
 * @author iss002
 *
 */
public class ControladorPartidos {
	
	
	private ControladorUsuarios cu;
	private Map<String, Partido> listaPartidos;
	private Map<String, Equipo> listaEquipos;
	
	
	/**
	 * Constructor que inicializa las colecciones
	 * @param cu controlador de usuarios
	 */
	public ControladorPartidos(ControladorUsuarios cu){

		this.cu=cu;
		listaPartidos = new HashMap<String, Partido>();
		listaEquipos = new HashMap<String, Equipo>();

	}
	
	
	// |------------------------------|
	
	
	/**
	 * Crea un nuevo listado con los equipos mostrando para cada uno una ficha breve.
	 * 
	 * @return el listado
	 */
	public List<String> listarEquipos() {
		
		List<String> listadoEquipos = new ArrayList<String>();
		
		for (Equipo e : listaEquipos.values()) {
			String ficha = e.verInfoEquipo();
			listadoEquipos.add(ficha);
		}

		return listadoEquipos;
	}
	

	/**
	 * Crea un nuevo equipo en el sistema
	 * 
	 * @param nombre El nombre corto del equipo, sirve como identificador
	 * @param nombreCompleto El nombre completo del equipo
	 * @throws ExcepcionEquipo Si ya existe un equipo con el mismo nombre (no confundir con nombre completo)
	 */
	public void nuevoEquipo(String nombre, String nombreCompleto) throws ExcepcionEquipo{
		
		// Comprueba si ya existe un equipo con este login
		if (!listaEquipos.containsKey(nombre)) {
			// Si no existe, crea la instancia
			Equipo nuevoEquipo = new Equipo(nombre, nombreCompleto);
			// Y la colecciona
			listaEquipos.put(nombre, nuevoEquipo);
		} 
		else {
			// Pero si ya exist�a lanza una excepci�n
			 throw new ExcepcionEquipo(CausaExcepcionEquipo.YA_EXISTE, nombre);
		}
	}
		
		
	/**
	 * Muestra la informaci�n del equipo completa
	 * 
	 * @param nombre Nombre que identifica al equipo (equivalente a un identificador)
	 * @return la info completa
	 * @throws ExcepcionEquipo si ha habido error al mostrar el equipo
	 */
	public String mostrarEquipo(String nombre) throws ExcepcionEquipo {
		
		// Recupera la instancia de la colecci�n
		Equipo esteEquipo = listaEquipos.get(nombre);
		
		if(esteEquipo==null){
			throw new ExcepcionEquipo(CausaExcepcionEquipo.NO_EXISTE, nombre);
		}
		
		return esteEquipo.verInfoCompleta();
	}
	
	
	// |------------------------------|
	
	
	/**
	 * Crea un nuevo listado con los partidos mostrando para cada uno una ficha breve.
	 * 
	 * @return el listado
	 */
	public List<String> listarPartidos() {
		
		List<String> listadoPartidos = new ArrayList<String>();
		
		for (Partido p : listaPartidos.values()) {
			String ficha = p.verInfoPartido();
			listadoPartidos.add(ficha);
		}

		return listadoPartidos;
	}
	
	
			
	/**
	 * A�ade un partido al sistema (a la lista de partidos)
	 * 
	 * @param equipoL El nombre del equipo local
	 * @param equipoV El nombre del equipo visitante
	 * @param fInicApuesta La fecha de inicio de las apuestas
	 * @param fFinApuesta La fecha del fin de las apuestas
	 * @throws ExcepcionPartidos Lanza una excepcion de partidos
	 * @throws ExcepcionEquipo Lanza una excepcion de equipo
	 */
	public void a�adirPartido(String equipoL, String equipoV, Calendar fInicApuesta, Calendar fFinApuesta) throws ExcepcionPartidos,ExcepcionEquipo {
		
			// Se utiliza para la asignación de ID a los partidos. A cada partido se le asigna el ID con el numero más pequeño que esté disponible.	Cada ID es único y por lo tanto no existirán dos partidos con el mismo ID simultáneamente.		
			String idPartido;
			
			for(int id = 0; ; id++)
			{
				idPartido = "p" + id;
				
				if(!listaPartidos.containsKey(idPartido)){
					break;
				}
			}
		
			//Inicialmente a false
			boolean comprobacion = false;			
			
			//Comprueba si los equipos existen. Comprueba si los equipos son el mismo.
			comprobacion = ((listaEquipos.containsKey(equipoL)) && (listaEquipos.containsKey(equipoV)) && (!equipoL.equals(equipoV)));
			
			if(comprobacion == false)
			{
				throw new ExcepcionEquipo(CausaExcepcionEquipo.EQ_INCORRECTO, idPartido + ": " + equipoL + " y/o " + equipoV);
			}
			
			Equipo equipoLocal = listaEquipos.get(equipoL);
			Equipo equipoVisitante = listaEquipos.get(equipoV);
			
			//si la fecha de inicio es posterior a la del final
			if(fInicApuesta.after(fFinApuesta))
			{
				throw new ExcepcionPartidos(CausaExcepcionPartidos.FECHAS_INCORRECTAS, "<ninguno>");
			}
			
			Partido p = new Partido(idPartido, equipoLocal, equipoVisitante, fInicApuesta, fFinApuesta);
			listaPartidos.put(idPartido, p);
	}


	/**
	 * Muestra la informaci�n del partido para el id indicado
	 * 
	 * @param idPartido
	 * @return la informaci�n completa del partido
	 * @throws ExcepcionPartidos Si no existe el partido lanza una excepcion
	 */
	public String mostrarPartido(String idPartido) throws ExcepcionPartidos{
		
		Partido estePartido = listaPartidos.get(idPartido);
		
		if(estePartido==null)
		{
			throw new ExcepcionPartidos(CausaExcepcionPartidos.NO_EXISTE, idPartido);
		}
		
		return estePartido.verInfoCompleta();
	
	}
		
	
	/**
	 * Modifica una instancia partido, concretamente la fecha de inicio y del fin de apuestas
	 * 
	 * @param idPartido El identificador del partido
	 * @param equipoL El equipo local
	 * @param equipoV El equipo visitante
	 * @param fInicApuesta La fecha de inicio de apuestas
	 * @param fFinApuesta La fecha de fin de apuestas
	 * @throws ExcepcionPartidos Si algo ha ido mal
	 * @throws ExcepcionEquipo  Si alg�n equipo no existe
	 */
	public void modificarPartido(String idPartido, String equipoL, String equipoV, Calendar fInicApuesta, Calendar fFinApuesta)
			throws ExcepcionPartidos, ExcepcionEquipo {
		
		// Recupera la instancia de la colecci�n
		Partido estePartido = listaPartidos.get(idPartido);
		
		//Inicialmente a false
		boolean comprobacion = false;				
		
		//Comprueba si los equipos existen. Comprueba si los equipos son el mismo.
		comprobacion = ((listaEquipos.containsKey(equipoL)) && (listaEquipos.containsKey(equipoV)) && (!equipoL.equals(equipoV)));
		
		if(comprobacion == false)
		{
			throw new ExcepcionEquipo(CausaExcepcionEquipo.EQ_INCORRECTO, idPartido + ": " + equipoL + " y/o " + equipoV);
		}
		
		Equipo equipoLocal = listaEquipos.get(equipoL);
		Equipo equipoVisitante = listaEquipos.get(equipoV);
		
		//si la fecha de inicio es posterior a la del final
		if(fInicApuesta.after(fFinApuesta))
		{
			throw new ExcepcionPartidos(CausaExcepcionPartidos.FECHAS_INCORRECTAS, idPartido);
		}
		
	
		// Si estePartido exist�a, no es null
		if (estePartido != null) {
			
			// Modifica los atributos si todo va bien
			estePartido.setEquipoL(equipoLocal);
			estePartido.setEquipoV(equipoVisitante);
			estePartido.setfInicApuesta(fInicApuesta);
			estePartido.setfFinApuesta(fFinApuesta);
		}
		else
		{
			// Pero si no exist�a lanza una excepci�n
			throw new ExcepcionPartidos(CausaExcepcionPartidos.NO_EXISTE, idPartido);
		}
	}
	

	/**
	 * Elimina un partido del listado seg�n el ID
	 * 
	 * @param idPartido El ID del partido que vamos a eliminar
	 * @throws ExcepcionPartidos lanza una excepci�n si ha habido un error al eliminar el partido
	 */
	public void eliminarPartido(String idPartido) throws ExcepcionPartidos{
		
		// Borra la instancia de la colecci�n
		Partido p = listaPartidos.remove(idPartido);
		// Si p es null es que no exist�a dicho partido, as� que podemos informar de que no lo hemos borrado (porque no exist�a)
		if (p == null) {
			 throw new ExcepcionPartidos(CausaExcepcionPartidos.NO_EXISTE, idPartido);
		 }
		 
	}
	
	
	/**
	 * Crea un nuevo listado s�lo con los partidos que se encuentran abiertos a apuestas mostrando para cada uno una ficha breve.
	 * @return lista de los partidos abiertos a apuestas
	 * @throws ExcepcionPartidos Si no hay partidos abiertos a apuesta
	 */
	public List<String> verPartidosAbiertosAApuesta() throws ExcepcionPartidos{
		
		List<String> listadoPartidos = new ArrayList<String>();
		
		//Obtiene la fecha actual del sistema
		Calendar fechaActual = Calendar.getInstance();
		fechaActual.getTime();
	
		
		for(Partido p : listaPartidos.values()){
			
			//Si se cumple que la fecha actual es posterior a la fijada para el inicio de apuesta y anterior a la fijada para el final de apuesta....
			if((fechaActual.after(p.getfInicApuesta())) && (fechaActual.before(p.getfFinApuesta()))){
				
				String ficha = p.verInfoPartido();
				listadoPartidos.add(ficha);
			}
		}
		
		//Si el listado est� vac�o, manda una excepci�n (o bien avisa de que no hay partidos abiertos)
		if(listadoPartidos.isEmpty()){
			throw new ExcepcionPartidos(CausaExcepcionPartidos.NO_PART_ABIERTOS_APUESTAS,"varios");
		}
		
		return listadoPartidos;
	
	}
	
	
	// |------------------------------|
	
	
	/**
	 * Introduce el resultado para un partido para una modalidad en concreto.
	 * @param idPartido
	 * @param tApuesta
	 * @param resultado
	 * @throws ExcepcionPartidos
	 * @throws ExcepcionApuesta 
	 */
	public void fijarResultadoPartido(String idPartido, TipoApuesta tApuesta, String resultado) throws ExcepcionPartidos, ExcepcionApuesta {
		
		Partido p = listaPartidos.get(idPartido);
		
		// Comprueba si existe el partido y en caso de no existir lanza la correspondiente excepci�n.
		if(p == null){
			throw new ExcepcionPartidos(CausaExcepcionPartidos.NO_EXISTE,idPartido);
		}
		
		p.setResultadoPartido(tApuesta, resultado);
		
	}
	
	
	// |------------------------------|
	
	
	/**
	 * Lista todas las apuestas del partido, clasificadas seg�n modalidad
	 * 
	 * @param idPartido El id del partido del cual queremos saber todas las apuestas
	 * @return el listado de las apuestas
	 * @throws ExcepcionPartidos 
	 */
	public List<String> listarApuestasPartido(String idPartido) throws ExcepcionPartidos{
		
		List<String> listadoApuestasPartido = new ArrayList<String>();
		
		if(!listaPartidos.containsKey(idPartido)){
			throw new ExcepcionPartidos(CausaExcepcionPartidos.NO_EXISTE, idPartido);
		}
		
		Partido estePartido = listaPartidos.get(idPartido);
		
		listadoApuestasPartido = estePartido.listarApuestas();
		
		return listadoApuestasPartido;
	}
	
	
	
	/**
	 * Realiza una serie de comprobaciones con los par�metros que recibe como argumentos y luego contin�a con la creaci�n de una apuesta en el sistema.
	 * @param login
	 * @param idPartido
	 * @param tApuesta
	 * @param resultado
	 * @param cantidadApostada
	 * @throws ExcepcionApuesta
	 * @throws ExcepcionUsuario
	 * @throws ExcepcionPartidos
	 * @throws ExcepcionCuenta 
	 */
	public void nuevaApuesta(String login, String idPartido, TipoApuesta tApuesta, String resultado,float cantidadApostada)throws ExcepcionApuesta, ExcepcionUsuario, ExcepcionPartidos, ExcepcionCuenta
	{
	
		//Si el saldo del jugador es menor que la cantidad apostada se produce la excepci�n correspondiente
		if((cu.verSaldoJugador(login)-cantidadApostada)<0)
		{
			throw new ExcepcionApuesta(CausaExcepcionApuestas.USUARIO_SIN_FONDOS, login);
		}
		
		// Si el partido no existe se produce la excepci�n correspondiente
		if(!listaPartidos.containsKey(idPartido)){
			
			throw new ExcepcionPartidos(CausaExcepcionPartidos.NO_EXISTE,idPartido);				
		}
		
		// Recupera la instancia de la colecci�n
		Partido p = listaPartidos.get(idPartido);
				
		Jugador jugadorQueApuesta = cu.getJugador(login);
		
		p.a�adirAContenedorApuestas(jugadorQueApuesta, cantidadApostada, tApuesta, resultado);
		
	}
	
	
	
				
		
		
	// |------------------------------|
		
	
	
	
	/**
	 * Este m�todo tras comprobar que existe el partido solicitado procede a pagar las apuestas realizadas para la modalidad y partido solicitados.
	 * @param idPartido
	 * @param tApuesta
	 * @throws ExcepcionPartidos
	 * @throws ExcepcionUsuario
	 * @throws ExcepcionApuesta
	 */
	public void pagarApuestasPartido(String idPartido, TipoApuesta tApuesta) throws ExcepcionPartidos, ExcepcionUsuario, ExcepcionApuesta
	{
		
		Partido p = listaPartidos.get(idPartido);
		
		if(p == null){
			throw new ExcepcionPartidos(CausaExcepcionPartidos.NO_EXISTE,idPartido);
		}
		
		p.pagarApuestasModalidad(tApuesta);
		
	}
		

}