package casaapuestas.partidos;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import casaapuestas.apuestas.Apuesta;
import casaapuestas.apuestas.CausaExcepcionApuestas;
import casaapuestas.apuestas.ExcepcionApuesta;
import casaapuestas.apuestas.TipoApuesta;
import casaapuestas.equipos.CausaExcepcionEquipo;
import casaapuestas.equipos.Equipo;
import casaapuestas.equipos.ExcepcionEquipo;
import casaapuestas.partidos.Partido.ResultadoQuiniela;
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
	private Set <Apuesta> listadoApuestas;
	
	
	/**
	 * Constructor que inicializa las colecciones
	 */
	public ControladorPartidos(ControladorUsuarios cu){

		this.cu=cu;
		listaPartidos = new HashMap<String, Partido>();
		listaEquipos =new HashMap<String, Equipo>();
		listadoApuestas =new LinkedHashSet<Apuesta>();

	}
	
	/**
	 * Crea un nuevo listado con los partidos
	 * 
	 * @return el listado
	 */
	public List<String> listarPartidos() {
		
		List<String> listado = new ArrayList<String>();
		
		for (Partido p : listaPartidos.values()) {
			String ficha = p.verInfoPartido();
			listado.add(ficha);
		}

		return listado;
	}
	
	/**
	 * Crea un nuevo listado con los equipos
	 * 
	 * @return el listado
	 */
	public List<String> listarEquipos() {
		
		List<String> listado2 = new ArrayList<String>();
		
		for (Equipo e : listaEquipos.values()) {
			String ficha2 = e.verInfoEquipo();
			listado2.add(ficha2);
		}

		return listado2;
	}
	
	
	/**
	 * Lista las apuestas para el partido con id pasado como parámetro
	 * 
	 * @param idPartido El id del partido que queremos consultar
	 * @return el listado de apuestas
	 */
	public List<String> listarApuestasPartido(String idPartido) {

		List<String> listado4 = new ArrayList<String>();
		
		//Creamos un iterador que recorra las diferentes apuestas del listadoApuestas
		Iterator<Apuesta> iter = listadoApuestas.iterator();
		
		if (listaPartidos.containsKey(idPartido)) {
				
				//Para todas las apuestas
				while(iter.hasNext()) {
					Apuesta a= (Apuesta) iter.next();
					String ficha = a.verInfoApuesta();
					listado4.add(ficha);
				}
		}
		
		return listado4;
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
	 * @throws ExcepcionPartidos Si no existe el id del partido
	 */
	public void nuevaApuesta(String login, String idPartido, TipoApuesta tApuesta, String resultado,float cantidadApostada)throws ExcepcionApuesta, ExcepcionUsuario, ExcepcionPartidos{

		//Si el saldo del jugador es menor que la cantidad apostada, salta la excepción
		if((cu.verSaldoJugador(login)-cantidadApostada)<0)
		{
			throw new ExcepcionUsuario(CausaExcepcionUsuario.USUARIO_SIN_FONDOS, login);
		}
		
		//Variable de control de la búsqueda;
		boolean BUS=false;
		//Obtiene la fecha actual del sistema para saber si se puede apostar
		Calendar fechaActual = Calendar.getInstance();
		fechaActual.getTime();
	
	
		for(Partido p : listaPartidos.values()){
			
			if (listaPartidos.containsKey(idPartido)) {
			
				//Si la fecha actual es posterior a la fijada para el inicio de apuesta e inferior a la de fin Apuesta
				if((fechaActual.before(p.getfFinApuesta())) && (fechaActual.after(p.getfInicApuesta()))){
					System.out.println("\n" + p.getfInicApuesta().getTime().toLocaleString() +"\n");
					System.out.println("\n" + p.getfFinApuesta().getTime().toLocaleString() +"\n");
					System.out.println("\n" + fechaActual.getTime().toLocaleString() +"\n");
					
					Apuesta nuevaApuesta = new Apuesta(login, idPartido, tApuesta,resultado,cantidadApostada,p.getEquipoL(),p.getEquipoV());
					// Y la colecciona
					listadoApuestas.add(nuevaApuesta);
					cu.realizarApuestaJugador(login, cantidadApostada, p.getEquipoL(), p.getEquipoV());
					BUS=true;
					break;
				}
			
				else{
					throw new ExcepcionApuesta(CausaExcepcionApuestas.PARTIDO_CERRADO, idPartido);
				}
			}
			
			//Manda una excepción para el partido
			if (BUS==false){
				throw new ExcepcionPartidos(CausaExcepcionPartidos.NO_EXISTE, idPartido);
			}
		}			
		
	}
	
	
	
	/**
	 * Muestra la información del partido para el ID indicado
	 * 
	 * @param idPartido
	 * @return la información completa del partido
	 */
	public String mostrarPartido(String idPartido) throws ExcepcionPartidos{
		
		Partido estePartido = listaPartidos.get(idPartido);
		
		if(estePartido==null)
		{
			throw new ExcepcionPartidos(CausaExcepcionPartidos.NO_EXISTE, idPartido);
		}
		
		return estePartido.verInfoCompleta();
	
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
	 * @throws ExcepcionPartidos 
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
		
				Partido p = new Partido(idPartido, equipoL, equipoV, 0, 0, ResultadoQuiniela.EMPATE, fInicApuesta, fFinApuesta);
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
	 * Muestra partidos abiertos a apuestas
	 * @return lista de los partidos abiertos a apuestas
	 * @throws ExcepcionPartidos Si no hay partidos abiertos a apuesta
	 */
	public List<String> verPartidosAbiertosAApuesta() throws ExcepcionPartidos{
		
		List<String> listado3 = new ArrayList<String>();
		
		//Obtiene la fecha actual del sistema
		Calendar fechaActual = Calendar.getInstance();
		fechaActual.getTime();
	
		
		for(Partido p : listaPartidos.values()){
			
			//Si la fecha actual es posterior a la fijada para el inicio de apuesta e inferior a la de fin Apuesta
			if(fechaActual.before(p.getfFinApuesta()) && fechaActual.after(p.getfInicApuesta())){
				
				String ficha3 = p.verInfoPartido();
				listado3.add(ficha3);
			}
		}
		
		//Si el listado está vacío, manda una excepción (o bien avisa de que no hay partidos abiertos)
		if(listado3.isEmpty()){
			throw new ExcepcionPartidos(CausaExcepcionPartidos.NO_PART_ABIERTOS_APUESTAS,"0");
		}
		
		return listado3;
	
	}
	
	/**
	 * Algoritmo básico para comprobar si los equipos existen
	 * 
	 * @param eqLocal El nombre del equipo local
	 * @param eqVisitante El nombre del equipo visitante
	 * @throws ExcepcionEquipo Excepción si hay un error
	 */
	public void comprobarEquipos (String equipoL,String equipoV) throws ExcepcionEquipo{
		
		//Inicialmente a cero
		int EL=0;
		int EV=0;
		
		for (Equipo e : listaEquipos.values()) {
			if(equipoL==equipoV)
			{
				//Si el visitante y el local es el mismo, es que está duplicado
				throw new ExcepcionEquipo(CausaExcepcionEquipo.EQ_DUPLICADO, equipoL);
			}
			if(e.verInfoEquipo().equals(equipoL))
			{
				//Existe el equipo local (variable a 1)
				EL=1;
			}
			if (e.verInfoEquipo().equals(equipoV))
			{
				//Existe el equipo visitante (variable a 1)
				EV=1;
			}
			
		}

		//Si no ha encontrado en la lista al visitante o local lanza la excepción
		if(EL==0) {
			 throw new ExcepcionEquipo(CausaExcepcionEquipo.NO_EXISTE, equipoL);
		 }
		
		if(EV==0) {
			 throw new ExcepcionEquipo(CausaExcepcionEquipo.NO_EXISTE, equipoV);
		 }
	}
	

}