package casaapuestas.partidos;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
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
	private Map<TipoApuesta, String> listaResultados;
	
	
	/**
	 * Constructor que inicializa las colecciones
	 * @param cu controlador de usuarios
	 */
	public ControladorPartidos(ControladorUsuarios cu){

		this.cu=cu;
		listaPartidos = new HashMap<String, Partido>();
		listaEquipos = new HashMap<String, Equipo>();
		listaResultados=new HashMap<TipoApuesta, String>();

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
				
				boolean comprobacion = false;
				
				if(listaEquipos.containsKey(equipoL) && listaEquipos.containsKey(equipoV))
				{
					comprobacion=true;
				}
				
				if(comprobacion==false){
					throw new ExcepcionEquipo(CausaExcepcionEquipo.NO_EXISTE, equipoL+ "o" + equipoV);
				}
				
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
			boolean comprobacion = false;
			
			if(listaEquipos.containsKey(equipoL) && listaEquipos.containsKey(equipoV))
			{
				comprobacion=true;
			}
			
			if(comprobacion==false){
				throw new ExcepcionEquipo(CausaExcepcionEquipo.NO_EXISTE, equipoL+ "o" + equipoV);
			}
			
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
		 * Lista todas las apuestas del partido, clasificadas según modalidad
		 * 
		 * @param idPartido El id del partido del cual queremos saber todas las apuestas
		 * @return el listado de las apuestas
		 */
		
		public List<String> listarApuestasPartido(String idPartido){
			
			List<String> listadoApuestasPartido = new ArrayList<String>();
			Map<TipoApuesta, ContenedorApuestas> mapaContainer;
			
			for (Partido p : listaPartidos.values()) {
				
				if(p.getIdPartido().equals(idPartido)){
					
					mapaContainer=p.verApuestasPartido();
		
					for(ContenedorApuestas ca: mapaContainer.values()){
						
						for(Apuesta a: ca.getApuesta()){
							String ficha = a.verInfoApuesta();
							listadoApuestasPartido.add(ficha);
						}
						
					}
				}
			}
			
			return listadoApuestasPartido;
		}
		
		/**
		 * Crea una nueva apuesta con los parámetros introducidos y la añade al partido
		 * 
		 * @param login
		 * @param idPartido
		 * @param tApuesta
		 * @param resultado
		 * @param cantidadApostada
		 * @throws ExcepcionApuesta
		 * @throws ExcepcionUsuario
		 * @throws ExcepcionPartidos
		 */
		
		public void nuevaApuesta(String login, String idPartido, TipoApuesta tApuesta, String resultado,float cantidadApostada)throws ExcepcionApuesta, ExcepcionUsuario, ExcepcionPartidos{

			Map<TipoApuesta, ContenedorApuestas> mapaContainer = null;
			
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
		
			if(!listaPartidos.containsKey(idPartido)){
				throw new ExcepcionPartidos(CausaExcepcionPartidos.NO_EXISTE,idPartido);
			}
		
			for(Partido p : listaPartidos.values()){
				
			//Si la fecha actual es posterior a la fijada para el inicio de apuesta e inferior a la de fin Apuesta	
			if((fechaActual.before(p.getfFinApuesta())) && (fechaActual.after(p.getfInicApuesta()))){	
				
				if (p.getIdPartido().equals(idPartido)) {
					
					mapaContainer=p.verApuestasPartido();
					ContenedorApuestas ca=null;
				
						if(mapaContainer.containsKey(tApuesta))
						{
							Apuesta nuevaApuesta = new Apuesta(login, idPartido, tApuesta,resultado,cantidadApostada,p.getEquipoL(),p.getEquipoV());
							ca=mapaContainer.get(tApuesta);
							ca.getApuesta().add(nuevaApuesta);
							cu.realizarApuestaJugador(login, cantidadApostada, p.getEquipoL(), p.getEquipoV());
						}
						else{
							ContenedorApuestas cb= new ContenedorApuestas();
							mapaContainer.put(tApuesta, cb);
							Apuesta nuevaApuesta = new Apuesta(login, idPartido, tApuesta,resultado,cantidadApostada,p.getEquipoL(),p.getEquipoV());
							cb.getApuesta().add(nuevaApuesta);
							cu.realizarApuestaJugador(login, cantidadApostada, p.getEquipoL(), p.getEquipoV());
						}
						BUSQUEDA=true;
						break;
					}
			
			}
			
			else{
				throw new ExcepcionApuesta(CausaExcepcionApuestas.PARTIDO_CERRADO, idPartido);
			}
			}
			
			
			if (BUSQUEDA==false){
				throw new ExcepcionPartidos(CausaExcepcionPartidos.NO_EXISTE, idPartido);
			}
		
	}
		

		public void pagarApuestasPartido(String idPartido, TipoApuesta tApuesta) throws ExcepcionUsuario{
			
			Map<TipoApuesta, ContenedorApuestas> mapaContainer= null;
			String resultado = null;
			float cantidadApostada=0;
			float totalGanador=0;
			float ratio=(float) 0.8;
			
			
			for (Partido part : listaPartidos.values()) {

				if(part.getIdPartido().equals(idPartido)){
					
					mapaContainer=part.verApuestasPartido();
		
					if(!mapaContainer.containsKey(tApuesta)){
						//No hay apuestas para pagar
					}
					
					for(Entry<TipoApuesta, String> result : listaResultados.entrySet()){
					 
						if(result.getKey().equals(tApuesta)){
							resultado=result.getValue();
						}
					}
			
					
					for(ContenedorApuestas ca: mapaContainer.values()){
						
						
						for(Entry<TipoApuesta, ContenedorApuestas> cApuesta : mapaContainer.entrySet()){
						
							if(cApuesta.getKey().equals(tApuesta)){
								//Determina si gana o no
								
								List<Apuesta> lApuesta= cApuesta.getValue().getApuesta();
								
								
								for(Apuesta a: lApuesta){
									cantidadApostada = cantidadApostada + a.getCantidadApostada();
									
									if(a.getResultado().equals(resultado)){
										a.setResolucion(true);
										totalGanador= totalGanador + a.getCantidadApostada();
									}
									
								}
								
								//Obtiene el ratio
								ratio=(float) (0.8*cantidadApostada/totalGanador);
							
								for(Apuesta a: lApuesta){
					
									//Si la resolución es correcta, paga
									if(a.getResolucion()==true){
										cu.realizarIngresoEnCuentaJugador(a.getLogin(), a.getCantidadApostada()*ratio);
									}
								
									
								}
							
							
							}
							
						}
						
					}
				}
			}
			
			
		}
		
		public void fijarResultadoPartido(String idPartido, TipoApuesta tApuesta, String resultado) throws ExcepcionPartidos{
			
//			listaResultados=new HashMap<TipoApuesta, String>();
			if(!listaPartidos.containsKey(idPartido)){
				throw new ExcepcionPartidos(CausaExcepcionPartidos.NO_EXISTE,idPartido);
			}
			
			for(Partido p : listaPartidos.values()){
				
				if (p.getIdPartido().equals(idPartido)) {
					
					if(listaPartidos.containsKey(tApuesta)){
						
						listaResultados.replace(tApuesta, resultado);
					}
					else{
				
						listaResultados.put(tApuesta, resultado);
					}
					break;
				}
			}
		}

}
	