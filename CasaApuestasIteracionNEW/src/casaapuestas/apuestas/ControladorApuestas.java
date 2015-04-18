package casaapuestas.apuestas;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import casaapuestas.cuentas.CuentaCasaApuestas;
import casaapuestas.equipos.Equipo;
import casaapuestas.partidos.CausaExcepcionPartidos;
import casaapuestas.partidos.ExcepcionPartidos;
import casaapuestas.partidos.Partido;


/**
 * @author iss002
 *
 */
public class ControladorApuestas {
	
	private Map<String, Apuesta> listadoApuestas;
	
	/**
	 * Constructor que inicializa las colecciones
	 */
	public ControladorApuestas(){
		
		listadoApuestas = new TreeMap<String, Apuesta>();
		
	}
	
	/**
	 * @param login
	 * @param idPartido
	 * @param tApuesta
	 * @param resultado
	 * @param cantidadApostada
	 * @throws ExcepcionApuesta
	 */
	public void nuevaApuesta(String login, String idPartido, TipoApuesta tApuesta, String resultado,float cantidadApostada, String eqLocal, String eqVisitante)throws ExcepcionApuesta{

				if (!listadoApuestas.containsKey(idPartido)) {
					// Si no existe, crea la instancia
					Apuesta nuevaApuesta = new Apuesta(login, idPartido, tApuesta,resultado,cantidadApostada,eqLocal,eqVisitante);
					// Y la colecciona
					listadoApuestas.put(idPartido, nuevaApuesta);
				} else {
					// Pero si ya existía lanza una excepción
					 throw new ExcepcionApuesta(CausaExcepcionApuestas.YA_EXISTE, login);
				}
	}
	

	
//	/**
//	 * @param login
//	 * @param idPartido id del partido elegido tras ejecutar el comando verPartidosAbiertosAApuestas
//	 */
//	public void darDatosApuesta(String login,int idPartido){
//		Scanner user_input = new Scanner( System.in );
//		String opcion;
//		String resultado;
//		Integer RLocal;
//		Integer RVisitante;
//		float cantidadApostada;
//		CuentaCasaApuestas CCA = CuentaCasaApuestas.getInstance();
//		
//		System.out.println("Elige el tipo de apuesta:\n  1 Para apuesta tipo quiniela\n  2 Para apuesta tipo resultado\n");
//		opcion = user_input.next();
//		//trows ExcepcionApuestas{
//		if(opcion.contentEquals("1")){
//			System.out.println("Introduce 1,x o 2:\n");
//			resultado = user_input.next();
//			if(!rQuiniela.contentEquals("1")&&!rQuiniela.contentEquals("x")&&!rQuiniela.contentEquals("2")){
//				//throw new ExcepcionApuestas(CausaExcepcionApuestas.INTRODUCE_ALGO_VALIDO,0);
//			}
//		}
//		else if(opcion.contentEquals("2")){
//			System.out.println("Introduce el resultado del equipo local:\n");
//			RLocal = Integer.parseInt(user_input.next());
//			System.out.println("\nIntroduce el resultado del equipo visitante:\n");
//			RVisitante = Integer.parseInt(user_input.next());
//		}
//		else{
//			
//		}
//		//}
//		System.out.println("Introduce el resultado del equipo local:\n");
//		cantidadApostada = Float.parseFloat(user_input.next());
//		
//		//Ahora reducimos el dinero de la cuenta del jugador
//		realizarReintegro("Apuesta", cantidadApostada);
//		//Y lo añadimos a la cuenta de la casa de apuestas
//		CCA.add(cantidadApostada);
//		Apuesta Apu = new Apuesta(cantidadApostada, login, opcion, rQuiniela, idPartido, null, null);
//		listadoApuestas.add(Apu);
//		return;
//	}


	
}