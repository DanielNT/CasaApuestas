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

@SuppressWarnings("unused")


public class Partido {
	
	private String nombre;
	
	private int idPartido;
	private String equipoL;
	private String equipoV;
	private int resultadoL;
	private int resultadoV;
	private ResultadoQuiniela resultadoQuin;
	private String fInicApuesta;
	private String hInicApuesta;
	private String fInicPart;
	private String hInicPart;
	
	public Partido(int idPartido, String equipoL, String equipoV, int resultadoL, int resultadoV, ResultadoQuiniela resultadoQuin, String fInicApuesta, String hInicApuesta, String fInicPart, String hInicPart){
		
		this.idPartido = idPartido;
		this.equipoL = equipoL;
		this.equipoV = equipoV;
		this.resultadoL = resultadoL;
		this.resultadoV = resultadoV;
		this.resultadoQuin = resultadoQuin;
		this.fInicApuesta = fInicApuesta;
		this.hInicApuesta = hInicApuesta;
		this.fInicPart = fInicPart;
		this.hInicPart = hInicPart;
		
	}
	
	
	public String verInfoPartido() {
		
		String info= idPartido + "Local: " + equipoL + " - " + "Visitante: "+ equipoV;
		
		return info;
	}

	public String verInfoCompleta() {
		return null;
	}
	
	}
