package casaapuestas.arranque;

import java.util.Calendar;
import java.util.List;

import casaapuestas.equipos.ExcepcionEquipo;
import casaapuestas.partidos.ControladorPartidos;
import casaapuestas.partidos.ExcepcionPartidos;
import casaapuestas.usuarios.ControladorUsuarios;
import casaapuestas.usuarios.ExcepcionUsuario;
import casaapuestas.usuarios.MetodoMensajeria;

/**
 * Clase con un main() de pruebas para la iteración 0, entregada por el profesor.
 * @author Eduardo Gómez Sánchez, ETSIT UVa.
 *
 */
public class PruebasCasaApuestas1 {

	/**
	 * Método main(). No se esperan parámetros.
	 * @param args parámetros en línea de comandos, pero se ignoran.
	 */
	public static void main(String[] args) {
		//Crea una instancia de controlador de usuarios
		ControladorUsuarios cu = new ControladorUsuarios();
		//Crea una instancia de controlador de partidos
		ControladorPartidos cp = new ControladorPartidos(cu);

		////////////////////////////////////////////////////////
		// CASOS DE USO PREVIOS
		////////////////////////////////////////////////////////	
		try {
			// Crea dos jugadores
			cu.crearJugador("edugom", "miclave", "Eduardo", "Gómez Sánchez", "00000000A", "666666666", "edugom@tel.uva.es", MetodoMensajeria.SMS);
			cu.crearJugador("mperez", "suclave", "María Ángeles", "Pérez Juárez", "11111111B", "666777777", "mperez@tel.uva.es", MetodoMensajeria.CORREO);
			// Ingresa dinero en sus cuentas
			cu.realizarIngresoEnCuentaJugador("edugom", (float)100.00);
			cu.realizarIngresoEnCuentaJugador("mperez", (float)200.00);
		} catch (ExcepcionUsuario eu) {
			//No se debería llegar hasta aquí, pero hay que comprobarlo
			System.out.println("Ha fallado una operación sobre el usuario con identificador '" + eu.getLogin() + "', por la siguiente causa: " + eu.getCausa().toString());
		}
		
		////////////////////////////////////////////////////////
		// CASOS DE USO EN ESCENARIOS DE ÉXITO
		////////////////////////////////////////////////////////	
		System.out.println("===============================================");
		System.out.println("PRUEBAS DE LA ITERACIÓN 1 - ESCENARIOS DE ÉXITO");
		System.out.println("===============================================");

		try {
			// Caso de uso "crear equipo"
			System.out.println("\nCreo seis equipos");
			cp.nuevoEquipo("Valladolid", "Real Valladolid Club de Fútbol, S.A.D");
			cp.nuevoEquipo("Salamanca", "Unión Deportiva Salamanca, S.A.D.");
			cp.nuevoEquipo("Mirandés", "Club Deportivo Mirandés, S.A.D.");
			cp.nuevoEquipo("Ponferradina", "Sociedad Deportiva Ponferradina, S.A.D");
			cp.nuevoEquipo("Burgos", "Real Burgos Club de Fútbol, S.A.D");
			cp.nuevoEquipo("Zamora", "Zamora Club de Fútbol");

			//Función de listar, que forma parte de los casos de uso "ver equipo", "modificar equipo" o "borrar equipo"
			System.out.println("\nListo los partidos existentes");
			List<String> listado = cp.listarEquipos();
			for(String s : listado) {
				System.out.println(s);
			}
			
			// Caso de uso "ver equipo"
			System.out.println("\nMuestro los datos completos del equipo con identificador 'Zamora'");
			String ficha = cp.mostrarEquipo("Zamora");
			System.out.println(ficha);
	
			//Operaciones auxiliares
			//Se crea una fecha de inicio de las apuestas, que se fija al 17 de abril de 2015, a las 8:00
			Calendar inicioApuestas = Calendar.getInstance();
			inicioApuestas.clear();
			inicioApuestas.set(2015, Calendar.APRIL, 17, 8, 0);
			//Se crea una fecha de fin de las apuestas, que se fija al 19 de abril de 2015, a las 19:30
			Calendar finApuestas = Calendar.getInstance();
			finApuestas.clear();
			finApuestas.set(2015, Calendar.APRIL, 19, 19, 30);
			//Se crean otras dos fechas de inicio y fin de apuestas, para el fin de semana siguiente
			Calendar inicioApuestas2 = Calendar.getInstance();
			inicioApuestas2.clear();
			inicioApuestas2.set(2015, Calendar.APRIL, 24, 8, 0);
			Calendar finApuestas2 = Calendar.getInstance();
			finApuestas2.clear();
			finApuestas2.set(2015, Calendar.APRIL, 26, 19, 30);
	
			//Caso de uso "crear partido"
			System.out.println("\nCreo tres partidos");
			cp.añadirPartido("Valladolid", "Salamanca", inicioApuestas, finApuestas);
			cp.añadirPartido("Mirandés", "Ponferradina", inicioApuestas, finApuestas);
			cp.añadirPartido("Burgos", "Zamora", inicioApuestas, finApuestas);
			
			// Función de listar, que forma parte de los casos de uso "ver partido", "modificar partido" y "borrar partido"
			System.out.println("\nListo los partidos existentes");
			listado = cp.listarPartidos();
			for(String s : listado) {
				System.out.println(s);
			}

			//Caso de uso "ver partido"
			System.out.println("\nMuestro el partido con identificador 'p2'");
			ficha = cp.mostrarPartido("p2");
			System.out.println(ficha);

			//Caso de uso "modificar partido"
			System.out.println("\nModifico el partido con identificador 'p2'");
			cp.modificarPartido("p2", "Burgos", "Zamora", inicioApuestas2, finApuestas2);

			//Caso de uso "ver partido"
			System.out.println("\nMuestro el partido con identificador 'p2', para comprobar la modificación");
			ficha = cp.mostrarPartido("p2");
			System.out.println(ficha);
//			
//			//Caso de uso "eliminar partido"
//			System.out.println("\nElimino el partido con identificador 'p2'");
//			cp.eliminarPartido("p2");
//			
//			// Función de listar, que forma parte de los casos de uso "ver partido", "modificar partido" y "borrar partido"
//			System.out.println("\nListo los partidos existentes, para comprobar la eliminación");
//			listado = cp.listarPartidos();
//			for(String s : listado) {
//				System.out.println(s);
//			}
//			
//			//Caso de uso "crear partido"
//			System.out.println("\nCreo un partido adicional, pero admitiendo apuestas una semana más tarde");
//			cp.nuevoPartido("Burgos", "Zamora", inicioApuestas2, finApuestas2);
//
//			//Caso de uso "ver partido"
//			System.out.println("\nMuestro el partido con identificador 'p3', recién creado");
//			ficha = cp.mostrarPartido("p3");
//			System.out.println(ficha);
//
//			
//			// Función de listar, limitando el listado a los partidos que admiten apuestas, necesaria en "crear apuesta"
//			//NOTA: para que esto funcione hay que "trucar" el sistema de manera que crea que la fecha actual está en medio del intervalo en el que se admiten apuestas
//			System.out.println("\nListo los partidos que admiten apuestas (sólo deberían salir dos)");
//			listado = cp.listarPartidosAbiertos();
//			for(String s : listado) {
//				System.out.println(s);
//			}
//			
//			//Caso de uso "crear apuesta" (después de haber listado los usuarios y haber visto que tienen saldo, y también de haber listado los partidos abiertos)
//			System.out.println("\nCreo dos apuestas de 'marcador' y dos apuestas de 'quiniela' sobre el partido con identificador 'p0'");
//			cp.nuevaApuesta("edugom", "p0", TipoApuesta.MARCADOR, "1-1", (float)20.0);
//			cp.nuevaApuesta("mperez", "p0", TipoApuesta.MARCADOR, "2-1", (float)10.0);
//			cp.nuevaApuesta("edugom", "p0", TipoApuesta.QUINIELA, "2", (float)15.0);
//			cp.nuevaApuesta("mperez", "p0", TipoApuesta.QUINIELA, "X", (float)8.5);
//			
//			//Caso de uso "listar apuestas partido"
//			System.out.println("\nListo las apuestas realizadas sobre el partido con identificador 'p0'");
//			listado = cp.listarApuestasPartido("p0");
//			for(String s : listado) {
//				System.out.println(s);
//			}
//			
//			// Caso de uso "listar movimientos de cuenta de jugador"
//			System.out.println("\nListo los movimientos de la cuenta del jugador con identificador 'edugom' para comprobar que se han anotado sus apuestas");
//			listado = cu.listarMovimientosCuentaJugador("edugom");
//			for(String s : listado) {
//				System.out.println(s);
//			}
//
//		} catch (ExcepcionEquipo ee) {
//			//Si se llega hasta aquí alguna operación con equipos ha ido mal
//			System.out.println("Ha fallado una operación sobre el equipo con identificador '" + ee.getIdEquipo() + "', por la siguiente causa: " + ee.getCausa().toString());
		} 
			catch (ExcepcionPartidos ep) {
			//Si se llega hasta aquí alguna operación con partidos ha ido mal
			System.out.println("Ha fallado una operación sobre el partido con identificador '" + ep.getIdPartido() + "', por la siguiente causa: " + ep.getCausa().toString());
		} 
//			catch (ExcepcionUsuario eu) {
//			//Si se llega hasta aquí alguna operación con usuarios ha ido mal
//			System.out.println("Ha fallado una operación sobre el usuario con identificador '" + eu.getLogin() + "', por la siguiente causa: " + eu.getCausa().toString());
//		} 
//			catch (ExcepcionApuesta ea) {
//			//Si se llega hasta aquí alguna operación con apuestas ha ido mal
//			System.out.println("Ha fallado una operación de apuestas por la siguiente causa: " + ea.getCausa().toString());
//		}
//		
//		////////////////////////////////////////////////////////
//		// CASOS DE USO EN ESCENARIOS DE FALLO
//		////////////////////////////////////////////////////////	
//		System.out.println("===============================================");
//		System.out.println("PRUEBAS DE LA ITERACIÓN 1 - ESCENARIOS DE FALLO");
//		System.out.println("===============================================");
//
//		try {
//			// Caso de uso "crear equipo": se intenta crear un equipo con un nombre corto que ya existe
//			System.out.println("\nIntento crear un equipo con un identificador 'Valladolid'");
//			cp.nuevoEquipo("Valladolid", "Fútbol Club La UVa");
//		} catch (ExcepcionEquipo ee) {
//			//Si se llega hasta aquí alguna operación con equipos ha ido mal
//			System.out.println("Ha fallado una operación sobre el equipo con identificador '" + ee.getIdEquipo() + "', por la siguiente causa: " + ee.getCausa().toString());
//		}
//		
//		try {
//			// Caso de uso "ver equipo": se intenta mostrar un equipo que no existe
//			System.out.println("\nIntento mostrar los datos del equipo 'Telecos'");
//			String ficha = cp.mostrarEquipo("Telecos");
//			System.out.println(ficha);
//
//		} catch (ExcepcionEquipo ee) {
//			//Si se llega hasta aquí alguna operación con equipos ha ido mal
//			System.out.println("Ha fallado una operación sobre el equipo con identificador '" + ee.getIdEquipo() + "', por la siguiente causa: " + ee.getCausa().toString());
//		}
//		
//		//Operaciones auxiliares
//		//Se crea una fecha de inicio de las apuestas, que se fija al 17 de abril de 2015, a las 8:00
//		Calendar inicioApuestas = Calendar.getInstance();
//		inicioApuestas.clear();
//		inicioApuestas.set(2015, Calendar.APRIL, 17, 8, 0);
//		//Se crea una fecha de fin de las apuestas, que se fija al 19 de abril de 2015, a las 19:30
//		Calendar finApuestas = Calendar.getInstance();
//		finApuestas.clear();
//		finApuestas.set(2015, Calendar.APRIL, 19, 19, 30);
//
//		try {
//			//Caso de uso "crear partido": se intenta crear un partido con fechas en orden inverso
//			System.out.println("\nIntento crear un partido con las fechas en orden inverso");
//			cp.nuevoPartido("Valladolid", "Salamanca", finApuestas, inicioApuestas);
//		} catch (ExcepcionEquipo ee) {
//			//Si se llega hasta aquí alguna operación con equipos ha ido mal
//			System.out.println("Ha fallado una operación sobre el equipo con identificador '" + ee.getIdEquipo() + "', por la siguiente causa: " + ee.getCausa().toString());
//		} catch (ExcepcionPartido ep) {
//			//Si se llega hasta aquí alguna operación con partidos ha ido mal
//			System.out.println("Ha fallado una operación sobre el partido con identificador '" + ep.getIdPartido() + "', por la siguiente causa: " + ep.getCausa().toString());
//		}
//		
//		try {
//			//Caso de uso "crear partido": se intenta crear un partido con equipos que no existen
//			System.out.println("\nIntento crear un partido con equipos que no existen");
//			cp.nuevoPartido("Telecos", "Informáticos", inicioApuestas, finApuestas);
//		} catch (ExcepcionEquipo ee) {
//			//Si se llega hasta aquí alguna operación con equipos ha ido mal
//			System.out.println("Ha fallado una operación sobre el equipo con identificador '" + ee.getIdEquipo() + "', por la siguiente causa: " + ee.getCausa().toString());
//		} catch (ExcepcionPartido ep) {
//			//Si se llega hasta aquí alguna operación con partidos ha ido mal
//			System.out.println("Ha fallado una operación sobre el partido con identificador '" + ep.getIdPartido() + "', por la siguiente causa: " + ep.getCausa().toString());
//		}	
//		
//		try {
//			//Caso de uso "modificar partido": se intenta modificar un partido con fechas en orden inverso
//			System.out.println("\nIntento modificar un partido con las fechas en orden inverso");
//			cp.modificarPartido("p1", "Valladolid", "Salamanca", finApuestas, inicioApuestas);
//		} catch (ExcepcionEquipo ee) {
//			//Si se llega hasta aquí alguna operación con equipos ha ido mal
//			System.out.println("Ha fallado una operación sobre el equipo con identificador '" + ee.getIdEquipo() + "', por la siguiente causa: " + ee.getCausa().toString());
//		} catch (ExcepcionPartido ep) {
//			//Si se llega hasta aquí alguna operación con partidos ha ido mal
//			System.out.println("Ha fallado una operación sobre el partido con identificador '" + ep.getIdPartido() + "', por la siguiente causa: " + ep.getCausa().toString());
//		}
//
//		try {
//			//Caso de uso "modificar partido": se intenta modificar un partido con equipos que no existen
//			System.out.println("\nIntento modificar un partido con equipos que no existen");
//			cp.modificarPartido("p1", "Telecos", "Informáticos", inicioApuestas, finApuestas);
//		} catch (ExcepcionEquipo ee) {
//			//Si se llega hasta aquí alguna operación con equipos ha ido mal
//			System.out.println("Ha fallado una operación sobre el equipo con identificador '" + ee.getIdEquipo() + "', por la siguiente causa: " + ee.getCausa().toString());
//		} catch (ExcepcionPartido ep) {
//			//Si se llega hasta aquí alguna operación con partidos ha ido mal
//			System.out.println("Ha fallado una operación sobre el partido con identificador '" + ep.getIdPartido() + "', por la siguiente causa: " + ep.getCausa().toString());
//		}	
//
//		try {
//			//Caso de uso "ver partido": se intenta mostrar un partido que no existe
//			System.out.println("\nIntento mostrar el partido con identificador 'ninguno'");
//			String ficha = cp.mostrarPartido("ninguno");
//			System.out.println(ficha);
//		} catch (ExcepcionPartido ep) {
//			//Si se llega hasta aquí alguna operación con partidos ha ido mal
//			System.out.println("Ha fallado una operación sobre el partido con identificador '" + ep.getIdPartido() + "', por la siguiente causa: " + ep.getCausa().toString());
//		}
//
//		try {
//			//Caso de uso "modificar partido": se intenta modificar un partido que no existe
//			System.out.println("\nIntento modificar el partido con identificador 'ninguno'");
//			cp.modificarPartido("ninguno", "Valladolid", "Salamanca", inicioApuestas, finApuestas);
//		} catch (ExcepcionEquipo ee) {
//			//Si se llega hasta aquí alguna operación con equipos ha ido mal
//			System.out.println("Ha fallado una operación sobre el equipo con identificador '" + ee.getIdEquipo() + "', por la siguiente causa: " + ee.getCausa().toString());
//		} catch (ExcepcionPartido ep) {
//			//Si se llega hasta aquí alguna operación con partidos ha ido mal
//			System.out.println("Ha fallado una operación sobre el partido con identificador '" + ep.getIdPartido() + "', por la siguiente causa: " + ep.getCausa().toString());
//		}
//		
//		try {
//			//Caso de uso "eliminar partido": se intenta eliminar un partido que no existe
//			System.out.println("\nIntento eliminar el partido con identificador 'ninguno'");
//			cp.eliminarPartido("ninguno");
//		} catch (ExcepcionPartido ep) {
//			//Si se llega hasta aquí alguna operación con partidos ha ido mal
//			System.out.println("Ha fallado una operación sobre el partido con identificador '" + ep.getIdPartido() + "', por la siguiente causa: " + ep.getCausa().toString());
//		}
//
//		try {
//			//Caso de uso "crear apuesta": se intenta crear una apuesta sobre un partido que no existe
//			System.out.println("\nIntento crear una apuesta sobre el partido con identificador 'ninguno'");
//			cp.nuevaApuesta("edugom", "ninguno", TipoApuesta.MARCADOR, "0-0", (float)10.0);		
//		} catch (ExcepcionPartido ep) {
//			//Si se llega hasta aquí alguna operación con partidos ha ido mal
//			System.out.println("Ha fallado una operación sobre el partido con identificador '" + ep.getIdPartido() + "', por la siguiente causa: " + ep.getCausa().toString());
//		} catch (ExcepcionUsuario eu) {
//			//Si se llega hasta aquí alguna operación con usuarios ha ido mal
//			System.out.println("Ha fallado una operación sobre el usuario con identificador '" + eu.getLogin() + "', por la siguiente causa: " + eu.getCausa().toString());
//		} catch (ExcepcionApuesta ea) {
//			//Si se llega hasta aquí alguna operación con apuestas ha ido mal
//			System.out.println("Ha fallado una operación de apuestas por la siguiente causa: " + ea.getCausa().toString());
//		}
//		
//		try {
//			//Caso de uso "crear apuesta": se intenta crear una apuesta sobre un partido que no acepta apuestas
//			System.out.println("\nIntento crear una apuesta sobre un partido que no acepta apuestas hasta la semana siguiente");
//			cp.nuevaApuesta("edugom", "p3", TipoApuesta.MARCADOR, "0-0", (float)10.0);		
//		} catch (ExcepcionPartido ep) {
//			//Si se llega hasta aquí alguna operación con partidos ha ido mal
//			System.out.println("Ha fallado una operación sobre el partido con identificador '" + ep.getIdPartido() + "', por la siguiente causa: " + ep.getCausa().toString());
//		} catch (ExcepcionUsuario eu) {
//			//Si se llega hasta aquí alguna operación con usuarios ha ido mal
//			System.out.println("Ha fallado una operación sobre el usuario con identificador '" + eu.getLogin() + "', por la siguiente causa: " + eu.getCausa().toString());
//		} catch (ExcepcionApuesta ea) {
//			//Si se llega hasta aquí alguna operación con apuestas ha ido mal
//			System.out.println("Ha fallado una operación de apuestas por la siguiente causa: " + ea.getCausa().toString());
//		}
//
//		try {
//			//Caso de uso "crear apuesta": se intenta crear una apuesta por encima del saldo del usuario
//			System.out.println("\nIntento crear una apuesta por 200 euros de un jugador que tiene sólo 65 euros");
//			cp.nuevaApuesta("edugom", "p0", TipoApuesta.MARCADOR, "0-0", (float)200.0);		
//		} catch (ExcepcionPartido ep) {
//			//Si se llega hasta aquí alguna operación con partidos ha ido mal
//			System.out.println("Ha fallado una operación sobre el partido con identificador '" + ep.getIdPartido() + "', por la siguiente causa: " + ep.getCausa().toString());
//		} catch (ExcepcionUsuario eu) {
//			//Si se llega hasta aquí alguna operación con usuarios ha ido mal
//			System.out.println("Ha fallado una operación sobre el usuario con identificador '" + eu.getLogin() + "', por la siguiente causa: " + eu.getCausa().toString());
//		} catch (ExcepcionApuesta ea) {
//			//Si se llega hasta aquí alguna operación con apuestas ha ido mal
//			System.out.println("Ha fallado una operación de apuestas por la siguiente causa: " + ea.getCausa().toString());
		
	}
}

