package casaapuestas.partidos;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import casaapuestas.apuestas.*;
import casaapuestas.arranque.*;
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
			// Pero si ya existía lanza una excepción
			 throw new ExcepcionEquipo(CausaExcepcionEquipo.YA_EXISTE, nombre);
		}
	}
		
		
	/**
	 * Muestra la información del equipo completa
	 * 
	 * @param nombre Nombre que identifica al equipo (equivalente a un identificador)
	 * @return la info completa
	 * @throws ExcepcionEquipo si ha habido error al mostrar el equipo
	 */
	public String mostrarEquipo(String nombre) throws ExcepcionEquipo {
		
		// Recupera la instancia de la colección
		Equipo esteEquipo = listaEquipos.get(nombre);
		
		if(esteEquipo==null){
			throw new ExcepcionEquipo(CausaExcepcionEquipo.NO_EXISTE, nombre);
		}
		
		return esteEquipo.verInfoCompleta();
	}	
	
	
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
	 * Crea un nuevo listado sólo con los partidos que se encuentran abiertos a apuestas mostrando para cada uno una ficha breve.
	 * @return lista de los partidos abiertos a apuestas
	 * @throws ExcepcionPartidos Si no hay partidos abiertos a apuesta
	 */
	public List<String> verPartidosAbiertosAApuesta() throws ExcepcionPartidos{
		
		List<String> listadoPartidos = new ArrayList<String>();
		
		//Obtiene la fecha actual del sistema
		Calendar fechaActual = Calendar.getInstance();
		fechaActual.getTime();
	
		
		for(Partido p : listaPartidos.values()){
			
			//Si la fecha actual es posterior a la fijada para el inicio de apuesta e inferior a la de fin Apuesta
			if(fechaActual.before(p.getfFinApuesta()) && fechaActual.after(p.getfInicApuesta())){
				
				String ficha = p.verInfoPartido();
				listadoPartidos.add(ficha);
			}
		}
		
		//Si el listado está vacío, manda una excepción (o bien avisa de que no hay partidos abiertos)
		if(listadoPartidos.isEmpty()){
			throw new ExcepcionPartidos(CausaExcepcionPartidos.NO_PART_ABIERTOS_APUESTAS,"varios");
		}
		
		return listadoPartidos;
	
	}
		
	
	
		
		/**
		 * Añade un partido al sistema (a la lista de partidos)
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
					throw new ExcepcionPartidos(CausaExcepcionPartidos.FECHAS_INCORRECTAS, idPartido);
				}
				
				Partido p = new Partido(idPartido, equipoLocal, equipoVisitante, fInicApuesta, fFinApuesta);
				listaPartidos.put(idPartido, p);
		}
	
	
		/**
		 * Muestra la información del partido para el ID indicado
		 * 
		 * @param idPartido
		 * @return la información completa del partido
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
		 * @throws ExcepcionEquipo  Si algún equipo no existe
		 */
		public void modificarPartido(String idPartido, String equipoL, String equipoV, Calendar fInicApuesta, Calendar fFinApuesta)
				throws ExcepcionPartidos, ExcepcionEquipo {
			
			// Recupera la instancia de la colección
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
			
		
			// Si estePartido existía, no es null
			if (estePartido != null) {
				
				// Modifica los atributos si todo va bien
				estePartido.setEquipoL(equipoLocal);
				estePartido.setEquipoV(equipoVisitante);
				estePartido.setfInicApuesta(fInicApuesta);
				estePartido.setfFinApuesta(fFinApuesta);
			}
			
			
			else{
				// Pero si no existía lanza una excepción
				throw new ExcepcionPartidos(CausaExcepcionPartidos.NO_EXISTE, idPartido);
			}
		}
			
		
		/**
		 * Elimina un partido del listado según el ID
		 * 
		 * @param idPartido El ID del partido que vamos a eliminar
		 * @throws ExcepcionPartidos lanza una excepción si ha habido un error al eliminar el partido
		 */
		public void eliminarPartido(String idPartido) throws ExcepcionPartidos{
			
			// Borra la instancia de la colección
			Partido p = listaPartidos.remove(idPartido);
			// Si p es null es que no existía dicho partido, así que podemos informar de que no lo hemos borrado (porque no existía)
			if (p == null) {
				 throw new ExcepcionPartidos(CausaExcepcionPartidos.NO_EXISTE, idPartido);
			 }
			 
		}
		
		
		
		
		
		
		
		
		
		// |------------------------------|
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	
		/**
		 * Realiza una serie de comprobaciones con los parámetros que recibe como argumentos y luego continúa con la creaciónde una apuesta en el sistema.
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
		public void nuevaApuesta(String login, String idPartido, TipoApuesta tApuesta, String resultado,float cantidadApostada)throws ExcepcionApuesta, ExcepcionUsuario, ExcepcionPartidos, ExcepcionCuenta{
		
			//Si el saldo del jugador es menor que la cantidad apostada se produce la excepción correspondiente
			if((cu.verSaldoJugador(login)-cantidadApostada)<0)
			{
				throw new ExcepcionUsuario(CausaExcepcionUsuario.USUARIO_SIN_FONDOS, login);
			}
			
			// Si el partido no existe se produce la excepción correspondiente
			if(!listaPartidos.containsKey(idPartido)){
				
				throw new ExcepcionPartidos(CausaExcepcionPartidos.NO_EXISTE,idPartido);				
			}
			
			// Recupera la instancia de la colección
			Partido p = listaPartidos.get(idPartido);
			
			//Obtiene la fecha actual del sistema.
			Calendar fechaActual = Calendar.getInstance();
			fechaActual.getTime();
			
			// Comprueba que la fecha actual en la que se está realizando la apuesta es válida.
			//Si no se cumple que la fecha actual es posterior a la fijada para el inicio de apuesta y anterior a la fijada para el final de apuesta....
			if(!((fechaActual.after(p.getfInicApuesta())) && (fechaActual.before(p.getfFinApuesta()))))
			{
				throw new ExcepcionApuesta(CausaExcepcionApuestas.PARTIDO_CERRADO, idPartido);
			}
			
			Jugador jugadorQueApuesta = cu.getJugador(login);
			
			p.a�adirAContenedorApuestas(jugadorQueApuesta, cantidadApostada, tApuesta, resultado);
			
	}
		
		
		/**
		 * Lista todas las apuestas del partido, clasificadas según modalidad
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


		public void fijarResultadoPartido(String idPartido, TipoApuesta tApuesta, String resultado) throws ExcepcionPartidos {
			
			Partido p = listaPartidos.get(idPartido);
			
			if(p == null){
				throw new ExcepcionPartidos(CausaExcepcionPartidos.NO_EXISTE,idPartido);
			}
			p.setResultadoPartido(tApuesta, resultado);
			
		}


		public void pagarApuestasPartido(String idPartido, TipoApuesta tApuesta) {
			// TODO Auto-generated method stub
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//		/**
//		 * Crea una nueva apuesta en la lista de Apuestas (del tipo LinkedHashSet, es decir, ordenada). Revisa si es posible apostar
//		 * 
//		 * @param login El login del usuario
//		 * @param idPartido El id del partido al que se apuesta
//		 * @param tApuesta El tipo de apuesta
//		 * @param resultado El resultado al que el usuario apuesta
//		 * @param cantidadApostada La cantidad de dinero apostada
//		 * @throws ExcepcionApuesta Si hay problemas en la creación de apuesta
//		 * @throws ExcepcionUsuario 
//		 * @throws ExcepcionPartidos Si no existe el id del partido
//		 */
//		public void nuevaApuesta(String login, String idPartido, TipoApuesta tApuesta, String resultado, float cantidadApostada)throws ExcepcionApuesta, ExcepcionUsuario, ExcepcionPartidos{
//
//
//			//Si el saldo del jugador es menor que la cantidad apostada, salta la excepción
//			if((cu.verSaldoJugador(login)-cantidadApostada)<0)
//			{
//				throw new ExcepcionUsuario(CausaExcepcionUsuario.USUARIO_SIN_FONDOS, login);
//			}
//			
//			//Variable de control de la búsqueda;
//			boolean BUSQUEDA= false; 
//			
//			//Obtiene la fecha actual del sistema para saber si se puede apostar
//			Calendar fechaActual = Calendar.getInstance();
//			fechaActual.getTime();
//
//			
//			for(Partido p : listaPartidos.values()){
//				
//				if (p.getIdPartido().equals(idPartido)) {
//				
//					//Si la fecha actual es posterior a la fijada para el inicio de apuesta e inferior a la de fin Apuesta
//					if((fechaActual.before(p.getfFinApuesta())) && (fechaActual.after(p.getfInicApuesta()))){
//	
//						Apuesta nuevaApuesta = new Apuesta(login, idPartido, tApuesta,resultado,cantidadApostada,p.getEquipoL(),p.getEquipoV());
//						// Y la colecciona
//						listadoApuestas.add(nuevaApuesta);
//						cu.realizarApuestaJugador(login, cantidadApostada, p.getEquipoL(), p.getEquipoV());
//						
//						//Pone las variables de control a TRUE
//						BUSQUEDA=true;
//
//						//sale del bucle
//						break;
//					}
//				
//					else{
//						throw new ExcepcionApuesta(CausaExcepcionApuestas.PARTIDO_CERRADO, idPartido);
//					}
//				}
//				
//			}
//				//Manda una excepción para el partido solo si no encuentra el id
//				if (BUSQUEDA==false){
//					throw new ExcepcionPartidos(CausaExcepcionPartidos.NO_EXISTE, idPartido);
//				}	
//			
//		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//		/**
//		 * Lista todas las apuestas del partido, clasificadas según modalidad
//		 * 
//		 * @param idPartido El id del partido del cual queremos saber todas las apuestas
//		 * @return el listado de las apuestas
//		 */
//		
//		public List<String> listarApuestasPartido(String idPartido){
//			
//			List<String> listadoApuestasPartido = new ArrayList<String>();
//			Map<TipoApuesta, ContenedorApuestas> mapaContainer;
//			
//			for (Partido p : listaPartidos.values()) {
//				
//				if(p.getIdPartido().equals(idPartido)){
//					
//					mapaContainer=p.verApuestasPartido();
//		
//					for(ContenedorApuestas ca: mapaContainer.values()){
//						
//						for(Apuesta a: ca.getApuesta()){
//							String ficha = a.verInfoApuesta();
//							listadoApuestasPartido.add(ficha);
//						}
//						
//					}
//				}
//			}
//			
//			return listadoApuestasPartido;
//		}
//		
//		
//
//		public void pagarApuestasPartido(String idPartido, TipoApuesta tApuesta) throws ExcepcionPartidos, ExcepcionUsuario{
//			
//			Partido p;
//			p = listaPartidos.get(idPartido);
//			if(p == null){
//				throw new ExcepcionPartidos(CausaExcepcionPartidos.NO_EXISTE,idPartido);
//			}
//			
//			p.pagarApuestas(tApuesta,p.getEquipoL(),p.getEquipoV(),cu);
//			
//		}
//		
//		public void fijarResultadoPartido(String idPartido, TipoApuesta tApuesta, String resultado) throws ExcepcionPartidos{
//			
//			Partido p;
//			p = listaPartidos.get(idPartido);
//			if(p == null){
//				throw new ExcepcionPartidos(CausaExcepcionPartidos.NO_EXISTE,idPartido);
//			}
//			p.setResultadoPartido(tApuesta,resultado);
//			
//		}
		
		
		

}