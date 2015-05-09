package casaapuestas.partidos;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import casaapuestas.apuestas.Apuesta;
import casaapuestas.apuestas.CausaExcepcionApuestas;
import casaapuestas.apuestas.ContenedorApuestas;
import casaapuestas.apuestas.ExcepcionApuesta;
import casaapuestas.apuestas.TipoApuesta;
import casaapuestas.equipos.CausaExcepcionEquipo;
import casaapuestas.equipos.Equipo;
import casaapuestas.equipos.ExcepcionEquipo;
import casaapuestas.usuarios.CausaExcepcionUsuario;
import casaapuestas.usuarios.ControladorUsuarios;
import casaapuestas.usuarios.ExcepcionUsuario;

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
		
		// Comprueba si ya existe un jugador con este login
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
			throw new ExcepcionPartidos(CausaExcepcionPartidos.NO_PART_ABIERTOS_APUESTAS,"0");
		}
		
		return listadoPartidos;
	
	}
		
	
	//Usamos una constante estática para solucionar el problema de la asignación de ID a los partidos. Cada nuevo partido incrementa la constante
		private static int ID =0;
		
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
		public void añadirPartido(String equipoL, String equipoV, Calendar fInicApuesta, Calendar fFinApuesta) throws ExcepcionPartidos,ExcepcionEquipo {
			
				String idPartido= "p" + ID;
				
				comprobarEquipos(equipoL,equipoV);
				
				//si la fecha de inicio es posterior a la del final
				if(fInicApuesta.after(fFinApuesta))
				{
					
					throw new ExcepcionPartidos(CausaExcepcionPartidos.FECHAS_INCORRECTAS, idPartido);
				}
				
				if (!listaPartidos.containsKey(idPartido)) {
			
					Partido p = new Partido(idPartido, equipoL, equipoV, fInicApuesta, fFinApuesta);
					listaPartidos.put(idPartido, p);
					//Incrementa la constante estática
					ID++;	
				}
				//Es necesario implementar excepción aquí  si el id ya está en uso por otro partido
				 else{
					 
				 throw new ExcepcionPartidos(CausaExcepcionPartidos.YA_EXISTE_P, idPartido);
			 }
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
			
			//Comprueba si los equipos existen
			comprobarEquipos(equipoL,equipoV);
			
			//si la fecha de inicio es posterior a la del final
			if(fInicApuesta.after(fFinApuesta))
			{
				
				throw new ExcepcionPartidos(CausaExcepcionPartidos.FECHAS_INCORRECTAS, idPartido);
			}
			
			
			// Recupera la instancia de la colección
			Partido estePartido = listaPartidos.get(idPartido);
			
		
			// Si estePartido existía, no es null
			if (estePartido != null) {
				
				//En el caso de que los equipos no coincidan, manda una excepción
				if((!estePartido.getEquipoL().equals(equipoL)) || (!estePartido.getEquipoV().equals(equipoV))){
					
					throw new ExcepcionEquipo(CausaExcepcionEquipo.EQ_INCORRECTO, idPartido);
				}
				
				// Modifica los atributos si todo va bien
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
			
			Partido p = listaPartidos.get(idPartido);
			
			//Si existe el partido con ese ID, lo elimina de la lista
			 if (listaPartidos.containsKey(idPartido)){ 
				 listaPartidos.remove(idPartido,p);
		     }
			 else{
				 throw new ExcepcionPartidos(CausaExcepcionPartidos.ERROR_ELIMINAR, idPartido);
			 }
			 
		}
		
		
		
		/**
		 * Algoritmo básico para comprobar si los equipos existen
		 * @param equipoL El nombre del equipo local.
		 * @param equipoV El nombre del equipo visitante.

		 * @throws ExcepcionEquipo Excepción si hay un error
		 */
		public void comprobarEquipos (String equipoL,String equipoV) throws ExcepcionEquipo{
			
			//Inicialmente a false
			boolean EL=false;
			boolean EV=false;
			
			for (Equipo e : listaEquipos.values()) {
				if(equipoL==equipoV)
				{
					//Si el visitante y el local es el mismo, es que está duplicado
					throw new ExcepcionEquipo(CausaExcepcionEquipo.EQ_DUPLICADO, equipoL);
				}
				if(e.getNombre().equals(equipoL))
				{
					//Existe el equipo local (variable a true)
					EL=true;
				}
				if (e.getNombre().equals(equipoV))
				{
					//Existe el equipo visitante (variable a true)
					EV=true;
				}
				
			}

			//Si no ha encontrado en la lista al visitante o al local lanza la excepción
			if(EL==false) {
				 throw new ExcepcionEquipo(CausaExcepcionEquipo.NO_EXISTE, equipoL);
			 }
			
			if(EV==false) {
				 throw new ExcepcionEquipo(CausaExcepcionEquipo.NO_EXISTE, equipoV);
			 }
		}
		
		
		
		
		
		
		
		
			
		
		// |------------------------------|
		
		public List<String> listarApuestasPartido(String idPartido){
			
			List<String> listadoApuestasPartido = new ArrayList<String>();
			Map<TipoApuesta, ContenedorApuestas> mapaContainer;
			
			for (Partido p : listaPartidos.values()) {
				
				if(p.getIdPartido().equals(idPartido)){
					
					mapaContainer=p.verApuestasPartido();
		
				for	(Map.Entry<TipoApuesta, ContenedorApuestas> entry : mapaContainer.entrySet())	{
						
					TipoApuesta tApuesta = entry.getKey();
					ContenedorApuestas values = entry.getValue();
						
//						if(mapaContainer==null){
//							
//							}
					for(Apuesta a: values.getApuesta()){
						String ficha = a.verInfoApuesta();
						listadoApuestasPartido.add(ficha);
					}
					break;	
				}
				}
				
			}
			
			
			return listadoApuestasPartido;
		}
		
		
		public void pagarApuestasPartido(String idPartido, TipoApuesta tApuesta){
			
		}
		
		public void fijarResultadoPartido(String idPartido, TipoApuesta tApuesta, String resultado){
	
		}
	
		
		
		
	
	/**
	 * Crea una nueva apuesta en la lista de Apuestas (del tipo LinkedHashSet, es decir, ordenada). Revisa si es posible apostar
	 * 
	 * @param login El login del usuario
	 * @param idPartido El id del partido al que se apuesta
	 * @param tApuesta El tipo de apuesta
	 * @param resultado El resultado al que el usuario apuesta
	 * @param cantidadApostada La cantidad de dinero apostada
	 * @throws ExcepcionApuesta Si hay problemas en la creación de apuesta
	 * @throws ExcepcionUsuario 
	 * @throws ExcepcionPartidos Si no existe el id del partido
	 */
	public void nuevaApuesta(String login, String idPartido, TipoApuesta tApuesta, String resultado,float cantidadApostada)throws ExcepcionApuesta, ExcepcionUsuario, ExcepcionPartidos{

		
		Map<TipoApuesta, ContenedorApuestas> mapaContainer;
		
		//Si el saldo del jugador es menor que la cantidad apostada, salta la excepción
		if((cu.verSaldoJugador(login)-cantidadApostada)<0)
		{
			throw new ExcepcionUsuario(CausaExcepcionUsuario.USUARIO_SIN_FONDOS, login);
		}
		
		//Variable de control de la búsqueda;
		boolean BUSQUEDA= false; 
		
		//Obtiene la fecha actual del sistema para saber si se puede apostar
		Calendar fechaActual = Calendar.getInstance();
		fechaActual.getTime();
	
	
		for(Partido p : listaPartidos.values()){
			
			if (p.getIdPartido().equals(idPartido)) {
				
			
				//Si la fecha actual es posterior a la fijada para el inicio de apuesta e inferior a la de fin Apuesta
				if((fechaActual.before(p.getfFinApuesta())) && (fechaActual.after(p.getfInicApuesta()))){
	
					mapaContainer=p.verApuestasPartido();
					
					for	(Map.Entry<TipoApuesta, ContenedorApuestas> entry : mapaContainer.entrySet())	{
						
						ContenedorApuestas values = entry.getValue();
							
					
					Apuesta nuevaApuesta = new Apuesta(login, idPartido, tApuesta,resultado,cantidadApostada,p.getEquipoL(),p.getEquipoV());
					// Y la colecciona
					values.getApuesta().add(nuevaApuesta);
					cu.realizarApuestaJugador(login, cantidadApostada, p.getEquipoL(), p.getEquipoV());
					
					//Pone las variables de control a TRUE
					BUSQUEDA=true;

					//sale del bucle
					break;
					}
				}
			
				else{
					throw new ExcepcionApuesta(CausaExcepcionApuestas.PARTIDO_CERRADO, idPartido);
				}
			}
			
		}
			//Manda una excepción para el partido solo si no encuentra el id
			if (BUSQUEDA==false){
				throw new ExcepcionPartidos(CausaExcepcionPartidos.NO_EXISTE, idPartido);
			}	
		
	}
	

}