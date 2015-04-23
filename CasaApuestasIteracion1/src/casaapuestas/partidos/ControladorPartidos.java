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
	
	/**
	 * Constructor que inicializa las colecciones
	 */
	public ControladorPartidos(){
		super();
		listaPartidos = new HashMap<Integer, Partido>();
	
	}
	
	
	/**
	 * @return
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
	 * Muestra la información del partido para el ID indicado
	 * @param idPartido
	 * @return
	 */
	public String mostrarPartido(int idPartido) {
		Partido estePartido = listaPartidos.get(idPartido);
		return estePartido.verInfoCompleta();
	}
	
	/**
	 * @param idPartido
	 * @param equipoL
	 * @param equipoV
	 * @param resultadoL
	 * @param resultadoV
	 * @param resultadoQuin
	 * @param fInicApuesta
	 * @param hInicApuesta
	 * @param fInicPart
	 * @param hInicPart
	 * @throws ExcepcionPartidos
	 */
	public void añadirPartido(int idPartido, String equipoL, String equipoV, int resultadoL, int resultadoV, ResultadoQuiniela resultadoQuin, String fInicApuesta, String hInicApuesta, String fInicPart, String hInicPart)
			throws ExcepcionPartidos {
		
		// Comprueba si ya existe un partido con ese ID
		if (!listaPartidos.containsKey(idPartido)) {
			// Si no existe, crea la instancia y además los resultados iniciales son 0 por defecto
			Partido p = new Partido(idPartido, equipoL, equipoV, 0, 0, resultadoQuin, fInicApuesta, hInicApuesta, fInicPart, hInicPart);
			// Y la colecciona
			listaPartidos.put(idPartido, p);
		} else {
			// Pero si ya existía lanza una excepción
			throw new ExcepcionPartidos(CausaExcepcionPartidos.YA_EXISTE_P, idPartido);
		}
	}

}