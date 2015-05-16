package casaapuestas.apuestas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import casaapuestas.arranque.*;
import casaapuestas.cuentas.*;
import casaapuestas.equipos.*;
import casaapuestas.partidos.*;
import casaapuestas.usuarios.*;

public class ContenedorApuestas {
	
	/** La lista de apuestas del contenedor de apuestas */
	private List<Apuesta> listadoApuestas;
	
	public ContenedorApuestas(){
		
		super();
		// Inicializa la lista de apuestas como un ArrayList vacío
		listadoApuestas = new ArrayList<Apuesta>();

	}
	
	public List<Apuesta> getApuesta(){
		return listadoApuestas;
	}

	public void pagarApuestasCont(Map<TipoApuesta, String> listaResultados, String resultado, String eqLocal, String eqVisitante, ControladorUsuarios cu) throws ExcepcionUsuario {
		
		float cantidadApostada=0;
		float totalGanador=0;
		float ratio=(float) 0.8;
		
		
		//Buscamos las apuestas que correspondan
		for(Apuesta a: listadoApuestas)
		{
			cantidadApostada = cantidadApostada + a.getCantidadApostada();
			
			if(a.getResultado().equals(resultado)){
				a.setResolucion(true);
				totalGanador= totalGanador + a.getCantidadApostada();
			}
		}
			
		//Obtiene el ratio
		ratio=(float) (0.8*cantidadApostada/totalGanador);
			
		//Y pagamos a las que ganan
		for(Apuesta a: listadoApuestas){
				
			//Si la resolución es correcta, paga
			if(a.getResolucion()==true){
				cu.cobroDePremioEnCuentaJugador(a.getLogin(), eqLocal, eqVisitante,a.getCantidadApostada()*ratio);
			}
							
								
		}
		
	}
	
}


//Map<TipoApuesta, ContenedorApuestas> mapaContainer= null;
//String resultado = null;
//float cantidadApostada=0;
//float totalGanador=0;
//float ratio=(float) 0.8;





//for (Partido part : listaPartidos.values()) {
//
//	if(part.getIdPartido().equals(idPartido)){
//		
//		mapaContainer=part.verApuestasPartido();
//
//		if(!mapaContainer.containsKey(tApuesta)){
//			//No hay apuestas para pagar
//		}
//		
//		for(Entry<TipoApuesta, String> result : listaResultados.entrySet()){
//		 
//			if(result.getKey().equals(tApuesta)){
//				resultado=result.getValue();
//			}
//		}
//
//		
//		for(ContenedorApuestas ca: mapaContainer.values()){
//			
//			
//			for(Entry<TipoApuesta, ContenedorApuestas> cApuesta : mapaContainer.entrySet()){
//			
//				if(cApuesta.getKey().equals(tApuesta)){
//					//Determina si gana o no
//					
//					List<Apuesta> lApuesta= cApuesta.getValue().getApuesta();
//					
//					
//					for(Apuesta a: lApuesta){
//						cantidadApostada = cantidadApostada + a.getCantidadApostada();
//						
//						if(a.getResultado().equals(resultado)){
//							a.setResolucion(true);
//							totalGanador= totalGanador + a.getCantidadApostada();
//						}
//						
//					}
//					
//					//Obtiene el ratio
//					ratio=(float) (0.8*cantidadApostada/totalGanador);
//				
//					for(Apuesta a: lApuesta){
//		
//						//Si la resolución es correcta, paga
//						if(a.getResolucion()==true){
//							cu.realizarIngresoEnCuentaJugador(a.getLogin(), a.getCantidadApostada()*ratio);
//						}
//					
//						
//					}
//				
//				
//				}
//				
//			}
//			
//		}
//	}
//}