package casaapuestas.arranque;

import java.util.List;

import casaapuestas.cuentas.ExcepcionCuenta;
import casaapuestas.usuarios.ControladorUsuarios;
import casaapuestas.usuarios.ExcepcionUsuario;
import casaapuestas.usuarios.MetodoMensajeria;

/**
 * Clase con un main() de pruebas para la iteración 0, entregada por el profesor.
 * @author Eduardo Gómez Sánchez, ETSIT UVa.
 *
 */
public class PruebasCasaApuestas0 {

	/**
	 * Método main(). No se esperan parámetros.
	 * @param args parámetros en línea de comandos, pero se ignoran.
	 */
	public static void main(String[] args) {
		//Crea una instancia de controlador de usuarios
		ControladorUsuarios cu = new ControladorUsuarios();

		////////////////////////////////////////////////////////
		// CASOS DE USO EN ESCENARIOS DE ÉXITO
		////////////////////////////////////////////////////////	
		System.out.println("===============================================");
		System.out.println("PRUEBAS DE LA ITERACIÓN 0 - ESCENARIOS DE ÉXITO");
		System.out.println("===============================================");

		try {
			// Caso de uso "crear jugador"
			System.out.println("\nCreo tres jugadores, con identificadores 'edugom', 'mperez' y 'nadie'");
			cu.crearJugador("edugom", "miclave", "Eduardo", "Gómez Sánchez", "00000000A", "666666666", "edugom@tel.uva.es", MetodoMensajeria.SMS);
			cu.crearJugador("mperez", "suclave", "María", "Pérez Juárez", "11111111B", "000000000", "mperez@tel.uva.es", MetodoMensajeria.CORREO);
			cu.crearJugador("nadie", "ooooooo", "Ninguna", "Persona", "22222222C", "666888888", "nobody@tel.uva.es", MetodoMensajeria.SMS);

			// Función de listar, que forma parte de los casos de uso "ver jugador", "modificar jugador" y "borrar jugador"
			System.out.println("\nListo los jugadores");
			List<String> listado = cu.listarJugadores();
			for(String s : listado) {
				System.out.println(s);
			}

			//Caso de uso "ver jugador"
			System.out.println("\nMuestro los datos completos del jugador con identificador 'mperez'");
			String ficha = cu.mostrarJugador("mperez");
			System.out.println(ficha);

			//Caso de uso "modificar jugador"
			System.out.println("\nModifico el nombre y el teléfono del jugador con identificador 'mperez'");
			cu.modificarJugador("mperez", "suclave", "María Ángeles", "Pérez Juárez", "11111111B", "666777777", "mperez@tel.uva.es", MetodoMensajeria.CORREO);

			//Caso de uso "ver jugador"
			System.out.println("\nVuelvo a mostrar los datos completos del jugador con identificador 'mperez', para comprobar la modificación");
			ficha = cu.mostrarJugador("mperez");
			System.out.println(ficha);

			//Caso de uso "borrar jugador"
			System.out.println("\nElimino al jugador con identificador 'nadie'");
			cu.eliminarJugador("nadie");

			// Función de listar, que forma parte de los casos de uso "ver jugador", "modificar jugador" y "borrar jugador"
			System.out.println("\nListo los jugadores de nuevo, para comprobar la eliminación");
			listado = cu.listarJugadores();
			for(String s : listado) {
				System.out.println(s);
			}

			// Caso de uso "ingresar dinero en cuenta"
			System.out.println("\nIngreso diversas cantidades en las casaapuestas.cuentas de los usuarios");
			cu.realizarIngresoEnCuentaJugador("edugom", (float)100.00);
			cu.realizarIngresoEnCuentaJugador("mperez", (float)200.00);

			// Caso de uso "retirar dinero de cuenta"
			System.out.println("\nRetiro diversas cantidades de las casaapuestas.cuentas de los usuarios");
			cu.realizarReintegroDesdeCuentaJugador("edugom", (float)20.55);
			cu.realizarReintegroDesdeCuentaJugador("mperez", (float)80.20);

			// Caso de uso "listar movimientos de cuenta de jugador"
			System.out.println("\nListo los movimientos de la cuenta del jugador con identificador 'edugom'");
			listado = cu.listarMovimientosCuentaJugador("edugom");
			for(String s : listado) {
				System.out.println(s);
			}

			//También mostramos el saldo (podría ser parte del CU anterior, o un CU distinto)
			System.out.println("\nMuestro el saldo de la cuenta del jugador con identificador 'edugom'");
			float saldo = cu.verSaldoJugador("edugom");
			System.out.println("Saldo:" + String.format("%.2f", saldo));
		} catch (ExcepcionUsuario eu) {
			//Si se llega hasta aquí alguna operación con usuarios ha ido mal
			System.out.println("Ha fallado una operación sobre el usuario con identificador '" + eu.getLogin() + "', por la siguiente causa: " + eu.getCausa().toString());
		} catch (ExcepcionCuenta ec) {
			//Si se llega hasta aquí alguna operación con casaapuestas.cuentas ha ido mal
			System.out.println("Ha fallado una operación sobre al realizar un movimiento con concepto '" + ec.getConcepto() + "', por la siguiente causa: " + ec.getCausa().toString());
		}
		
		////////////////////////////////////////////////////////
		// CASOS DE USO EN ESCENARIOS DE FALLO
		////////////////////////////////////////////////////////	
		System.out.println("===============================================");
		System.out.println("PRUEBAS DE LA ITERACIÓN 0 - ESCENARIOS DE FALLO");
		System.out.println("===============================================");

		try {
			// Caso de uso "crear jugador": se intenta crear un usuario con un identificador que ya existe
			System.out.println("\nIntento crear un jugador con identificador 'edugom'");
			cu.crearJugador("edugom", "cni", "Francisco Nicolás", "Gómez Iglesias", "33333333D", "666999999", "nicolas@cni.es", MetodoMensajeria.SMS);
		} catch (ExcepcionUsuario eu) {
			//Si se llega hasta aquí alguna operación con usuarios ha ido mal
			System.out.println("Ha fallado una operación sobre el usuario con identificador '" + eu.getLogin() + "', por la siguiente causa: " + eu.getCausa().toString());
		}
		
		try {
			//Caso de uso "ver jugador": se intenta mostrar un jugador que no existe
			System.out.println("\nIntento mostrar los datos del jugador identificador 'nadie'");
			String ficha = cu.mostrarJugador("nadie");
			System.out.println(ficha);
		} catch (ExcepcionUsuario eu) {
			//Si se llega hasta aquí alguna operación con usuarios ha ido mal
			System.out.println("Ha fallado una operación sobre el usuario con identificador '" + eu.getLogin() + "', por la siguiente causa: " + eu.getCausa().toString());
		}
		
		try {
			//Caso de uso "modificar jugador": se intenta modificar un jugador que no existe
			System.out.println("\nIntento modificar los datos del jugador identificador 'nadie'");
			cu.modificarJugador("nadie", "xxx", "yyy", "zzz", "01234567Z", "666123456", "secreto@tel.uva.es", MetodoMensajeria.SMS);
		} catch (ExcepcionUsuario eu) {
			//Si se llega hasta aquí alguna operación con usuarios ha ido mal
			System.out.println("Ha fallado una operación sobre el usuario con identificador '" + eu.getLogin() + "', por la siguiente causa: " + eu.getCausa().toString());
		}

		try {
			//Caso de uso "borrar jugador": se intenta borrar un jugador que no existe
			System.out.println("\nIntento eliminar al jugador con identificador 'nadie'");
			cu.eliminarJugador("nadie");
		} catch (ExcepcionUsuario eu) {
			//Si se llega hasta aquí alguna operación con usuarios ha ido mal
			System.out.println("Ha fallado una operación sobre el usuario con identificador '" + eu.getLogin() + "', por la siguiente causa: " + eu.getCausa().toString());
		}

		try {
			// Caso de uso "ingresar dinero en cuenta": se intenta ingresar en la cuenta de alquien que no existe
			System.out.println("\nIntento ingresar una cantidad al jugador con identificador 'nadie'");
			cu.realizarIngresoEnCuentaJugador("nadie", (float)1000000.00);
		} catch (ExcepcionUsuario eu) {
			//Si se llega hasta aquí alguna operación con usuarios ha ido mal
			System.out.println("Ha fallado una operación sobre el usuario con identificador '" + eu.getLogin() + "', por la siguiente causa: " + eu.getCausa().toString());
		}

		try {
			// Caso de uso "retirar dinero de cuenta": primera causa de fallo, intento reintegrar de la cuenta de alquien que no existe
			System.out.println("\nIntento retirar una cantidad del jugador con identificador 'nadie'");
			cu.realizarReintegroDesdeCuentaJugador("nadie", (float)5000);
		} catch (ExcepcionUsuario eu) {
			//Si se llega hasta aquí alguna operación con usuarios ha ido mal
			System.out.println("Ha fallado una operación sobre el usuario con identificador '" + eu.getLogin() + "', por la siguiente causa: " + eu.getCausa().toString());
		} catch (ExcepcionCuenta ec) {
			//Si se llega hasta aquí alguna operación con casaapuestas.cuentas ha ido mal
			System.out.println("Ha fallado una operación sobre al realizar un movimiento con concepto '" + ec.getConcepto() + "', por la siguiente causa: " + ec.getCausa().toString());
		}

		
		try {
			// Caso de uso "retirar dinero de cuenta": segunda causa de fallo, intento reintegrar más saldo del disponible
			System.out.println("\nIntento retirar más saldo del disponible de la cuenta del jugador con identificador 'edugom'");
			cu.realizarReintegroDesdeCuentaJugador("edugom", (float)5000);
		} catch (ExcepcionUsuario eu) {
			//Si se llega hasta aquí alguna operación con usuarios ha ido mal
			System.out.println("Ha fallado una operación sobre el usuario con identificador '" + eu.getLogin() + "', por la siguiente causa: " + eu.getCausa().toString());
		} catch (ExcepcionCuenta ec) {
			//Si se llega hasta aquí alguna operación con casaapuestas.cuentas ha ido mal
			System.out.println("Ha fallado una operación sobre al realizar un movimiento con concepto '" + ec.getConcepto() + "', por la siguiente causa: " + ec.getCausa().toString());
		}

		try {
			// Caso de uso "listar movimientos de cuenta de jugador": se intentan listar los movimientos de alguien que no existe
			System.out.println("\nIntento listar los movimientos de la cuenta del jugador con identificador 'nadie'");
			List<String> listado = cu.listarMovimientosCuentaJugador("nadie");
			for(String s : listado) {
				System.out.println(s);
			}
		} catch (ExcepcionUsuario eu) {
			//Si se llega hasta aquí alguna operación con usuarios ha ido mal
			System.out.println("Ha fallado una operación sobre el usuario con identificador '" + eu.getLogin() + "', por la siguiente causa: " + eu.getCausa().toString());
		}
	}
}
