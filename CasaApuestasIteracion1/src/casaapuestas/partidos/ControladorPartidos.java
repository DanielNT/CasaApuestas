package casaapuestas.partidos;

import casaapuestas.cuentas.*;
import casaapuestas.usuarios.*;
import casaapuestas.equipos.*;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class ControladorPartidos {
	
	
	private int idPartido;
	private Map<Integer, Partido> listaPartidos;
	
	public ControladorPartidos(){
		super();
		listaPartidos = new HashMap<Integer, Partido>();
	
	}
	
	
	public List<String> listaPartidos() {
		
		List<String> listado = new ArrayList<String>();
		
		for (Partido p : listaPartidos.values()) {
			String ficha = p.verInfoPartido();
			listado.add(ficha);
		}

		return listado;
	}
	
	public String mostrarPartido(int idPartido) {
		Partido estePartido = listaPartidos.get(idPartido);
		return estePartido.verInfoCompleta();
	}
	
	public void añadirPartido(int idPartido, String equipoL, String equipoV, int resultadoL, int resultadoV, ResultadoQuiniela resultadoQuin, String fInicApuesta, String hInicApuesta, String fInicPart, String hInicPart)
			throws ExcepcionUsuario {
		
		// Comprueba si ya existe un partido con ese ID
		if (!listaPartidos.containsKey(idPartido)) {
			// Si no existe, crea la instancia y además los resultados iniciales son 0 por defecto
			Partido p = new Partido(idPartido, equipoL, equipoV, 0, 0, resultadoQuin, fInicApuesta, hInicApuesta, fInicPart, hInicPart);
			// Y la colecciona
			listaPartidos.put(idPartido, p);
		} else {
			// Pero si ya existía lanza una excepción
			throw new ExcepcionUsuario(CausaExcepcionUsuario.YA_EXISTE, "Error al crear: "+ idPartido);
		}
	}

}