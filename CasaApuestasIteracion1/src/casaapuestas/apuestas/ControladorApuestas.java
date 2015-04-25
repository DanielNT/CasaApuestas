package casaapuestas.apuestas;

import casaapuestas.apuestas.*;
import casaapuestas.partidos.Partido;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;


public class ControladorApuestas {
	
	private Map<Integer, Apuesta> listaApuestaPartidos;


	public List<String> listarApuestasPartido(int idPartidoApuesta) {
		
		List<String> listado = new ArrayList<String>();
		
		for (Apuesta a : listaApuestaPartidos.values()) {
			
			if(idPartidoApuesta==a.getIdApuesta()){
				String ficha = a.verApuesta();
				listado.add(ficha);
			}
		}

		return listado;
	}
	
//	public void darDatosApuesta(int tipoApuesta, float cantidadApostada, String login,int idPartido) {
//		return;
//	}
//	

	
}