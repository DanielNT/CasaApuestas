package casaapuestas.usuarios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import casaapuestas.cuentas.ExcepcionCuenta;

import casaapuestas.apuestas.*;
import casaapuestas.arranque.*;
import casaapuestas.cuentas.*;
import casaapuestas.equipos.*;
import casaapuestas.partidos.*;


/**
 * Clase controladora que recibe los m�todos provenientes de la UI relacionados con la gesti�n de usuarios
 * 
 * @author Eduardo G�mez S�nchez, ETSIT UVa. +iss002
 */
public class ControladorUsuarios {
	/**
	 * Lista de los jugadores existentes en el sistema, indexada por su login o identificador de usuario (que por lo tanto debe ser �nico)
	 */
	private Map<String, Jugador> listaJugadores;

	/**
	 * Constructor que inicializa las colecciones
	 */
	public ControladorUsuarios() {
		super();
		// Inicializa las colecciones
		listaJugadores = new HashMap<String, Jugador>();
	}

	
	/**
	 * M�todo que crea una nueva instancia de <code>Jugador</code> y la colecciona, indexada por el <code>login</code>
	 * 
	 * @param login El login, o identificador �nico de usuario
	 * @param clave La clave del usuario (en claro)
	 * @param nombre El nombre del usuario
	 * @param apellidos Los apellidos del usuario
	 * @param nif El NIF del usuario
	 * @param movil El n�mero de tel�fono del usuario
	 * @param correo La direcci�n de correo del usuario
	 * @param metodo El m�todo de mensajer�a preferido por el usuario
	 * @throws ExcepcionUsuario Se produce una excepci�n si ya existe un usuario con este <code>login</code>
	 */
	public void crearJugador(String login, String clave, String nombre, String apellidos, String nif, String movil, String correo, MetodoMensajeria metodo)
			throws ExcepcionUsuario {
		// Comprueba si ya existe un jugador con este login
		if (!listaJugadores.containsKey(login)) {
			// Si no existe, crea la instancia
			Jugador nuevoJugador = new Jugador(login, clave, nombre, apellidos, nif, movil, correo, metodo);
			// Y la colecciona
			listaJugadores.put(login, nuevoJugador);
		} else {
			// Pero si ya exist�a lanza una excepci�n
			throw new ExcepcionUsuario(CausaExcepcionUsuario.YA_EXISTE, login);
		}
	}

	/**
	 * M�todo que permite mostrar toda la informaci�n de una instancia de <code>Jugador</code> dada por un determinado <code>login</code>
	 * 
	 * @param login login el login, o identificador �nico de usuario
	 * @return una cadena con toda la informaci�n del usuario buscado
	 * @throws ExcepcionUsuario si no existe un usuario con este <code>login</code>
	 */
	public String mostrarJugador(String login) throws ExcepcionUsuario {
		// Recupera la instancia de la colecci�n
		Jugador esteJugador = listaJugadores.get(login);
		// Si este jugador exist�a, no es null
		if (esteJugador != null) {
			// Le pide al jugador que muestre su informaci�n completa
			return esteJugador.verFichaCompleta();
		} else {
			// Pero si no exist�a lanza una excepci�n
			throw new ExcepcionUsuario(CausaExcepcionUsuario.NO_EXISTE, login);
		}
	}

	/**
	 * M�todo que modifica una instancia de <code>Jugador</code> dada por un determinado <code>login</code>
	 * 
	 * @param login El login, o identificador �nico de usuario (no se puede modificar)
	 * @param clave La clave del usuario 
	 * @param nombre El nombre del usuario
	 * @param apellidos Los apellidos del usuario
	 * @param nif El NIF del usuario
	 * @param movil El n�mero de tel�fono del usuario
	 * @param correo La direcci�n de correo del usuario
	 * @param metodo El m�todo de mensajer�a preferido por el usuario
	 * @throws ExcepcionUsuario Se produce una excepci�n si no existe un usuario con este <code>login</code>
	 */
	public void modificarJugador(String login, String clave, String nombre, String apellidos, String nif, String movil, String correo, MetodoMensajeria metodo)
			throws ExcepcionUsuario {
		// Recupera la instancia de la colecci�n
		Jugador esteJugador = listaJugadores.get(login);
		// Si este jugador exist�a, no es null
		if (esteJugador != null) {
			// Modifica uno a uno los otros atributos
			esteJugador.setClave(clave);
			esteJugador.setNombre(nombre);
			esteJugador.setApellidos(apellidos);
			esteJugador.setNif(nif);
			esteJugador.setMovil(movil);
			esteJugador.setCorreo(correo);
			esteJugador.setMetodo(metodo);
		} else {
			// Pero si no exist�a lanza una excepci�n
			throw new ExcepcionUsuario(CausaExcepcionUsuario.NO_EXISTE, login);
		}
	}

	/**
	 * M�todo que permite borrar una instancia de <code>Jugador</code> dada por un determinado <code>login</code>
	 * 
	 * @param login El login, o identificador �nico de usuario
	 * @throws ExcepcionUsuario Si no existe un usuario con este <code>login</code>
	 */
	public void eliminarJugador(String login) throws ExcepcionUsuario {
		// Borra la instancia de la colecci�n
		Jugador esteJugador = listaJugadores.remove(login);
		// Si este jugador es null es que no exist�a, as� que podemos informar de que no lo hemos borrado (porque no exist�a)
		if (esteJugador == null) {
			throw new ExcepcionUsuario(CausaExcepcionUsuario.NO_EXISTE, login);
		}
	}

	/**
	 * M�todo que permite obtener una lista de cadenas, cada una con informaci�n breve de cada instancia de <code>Jugador</code> coleccionada por este
	 * controlador.
	 * 
	 * @return Una lista de cadenas
	 */
	public List<String> listarJugadores() {
		// Inicializa la lista
		List<String> listado = new ArrayList<String>();

		// Recorre la colecci�n de jugadores
		for (Jugador j : listaJugadores.values()) {
			// A cada jugador le pide informaci�n breve
			String ficha = j.verFichaBreve();
			// Y la a�ade al listado
			listado.add(ficha);
		}

		// Al terminar retorna el listado
		return listado;
	}

	/**
	 * M�todo que permite realizar un ingreso de una <code>cantidad</code> de dinero en la cuenta del jugador identificado por el <code>login</code>
	 * 
	 * @param login El login, o identificador �nico de usuario
	 * @param cantidad La cantidad a ingresar en la cuenta (positiva)
	 * @throws ExcepcionUsuario Se produce si el usuario no existe
	 */
	public void realizarIngresoEnCuentaJugador(String login, float cantidad) throws ExcepcionUsuario {
		// Recupera la instancia de la colecci�n
		Jugador esteJugador = listaJugadores.get(login);
		// Si este jugador exist�a, no es null
		if (esteJugador != null) {
			// As� que delegamos en �l que realice el ingreso
			esteJugador.realizarIngreso("Ingreso en efectivo por el usuario " + login, cantidad);
		} else {
			// Pero si no exist�a lanza una excepci�n
			throw new ExcepcionUsuario(CausaExcepcionUsuario.NO_EXISTE, login);
		}
	}
	
	/**
	 * Realiza apuesta de una <code>cantidad</code>en la cuenta de un jugador
	 * 
	 * @param login El login del usuario
	 * @param cantidad La cantidad de apostada
	 * @param eqLocal Nombre del equipo local
	 * @param eqVisitante Nombre del equipo visitante
	 * @throws ExcepcionUsuario Si algo sale mal
	 */
	public void realizarApuestaJugador(String login, float cantidad, String eqLocal, String eqVisitante) throws ExcepcionUsuario {
		// Recupera la instancia de la colecci�n
		Jugador esteJugador = listaJugadores.get(login);
		// Si este jugador exist�a, no es null
		if (esteJugador != null) {
			// As� que delegamos en �l que realice el ingreso negativo (por ello se le suma la cantidad cambiada de signo)
			esteJugador.realizarIngreso("Pago de apuesta sobre " + eqLocal + "-" + eqVisitante, -cantidad);
		} else {
			// Pero si no exist�a lanza una excepci�n
			throw new ExcepcionUsuario(CausaExcepcionUsuario.NO_EXISTE, login);
		}
	}

	/**
	 * M�todo que permite realizar un reintegro de una <code>cantidad</code> de dinero desde la cuenta del jugador identificado por el <code>login</code>
	 * 
	 * @param login El login, o identificador �nico de usuario
	 * @param cantidad La cantidad a reintegrar desde la cuenta (positiva)
	 * @throws ExcepcionUsuario Si el usuario no existe
	 * @throws ExcepcionCuenta Si la cuenta no tiene saldo suficiente para reintegrar dicha cantidad
	 */
	public void realizarReintegroDesdeCuentaJugador(String login, float cantidad) throws ExcepcionUsuario, ExcepcionCuenta {
		// Recupera la instancia de la colecci�n
		Jugador esteJugador = listaJugadores.get(login);
		// Si este jugador exist�a, no es null
		if (esteJugador != null) {
			// As� que delegamos en �l que realice el ingreso (ojo, este m�todo lanza una excepci�n)
			esteJugador.realizarReintegro("Reintegro en efectivo por el usuario " + login, cantidad);
		} else {
			// Pero si no exist�a lanza una excepci�n
			throw new ExcepcionUsuario(CausaExcepcionUsuario.NO_EXISTE, login);
		}
	}
	
	/**
	 * M�todo que permite ver el saldo en la cuenta del jugador identificado por el <code>login</code>
	 * @param login el login, o identificador �nico de usuario
	 * @return el saldo en la cuenta del jugador
	 * @throws ExcepcionUsuario si el usuario no existe
	 */
	public float verSaldoJugador(String login) throws ExcepcionUsuario {
		// Recupera la instancia de la colecci�n
		Jugador esteJugador = listaJugadores.get(login);
		// Si este jugador exist�a, no es null
		if (esteJugador != null) {
			// As� que delegamos en �l que nos informe de su saldo
			return esteJugador.verSaldo();
		} else {
			// Pero si no exist�a lanza una excepci�n
			throw new ExcepcionUsuario(CausaExcepcionUsuario.NO_EXISTE, login);
		}
	}
	
	/**
	 * M�todo que permite obtener un listado de los movimientos de cuenta del jugador identificador por el  <code>login</code>
	 * @param login el login, o identificador �nico de usuario
	 * @return una lista de cadenas, donde cada elemento corresponde a un movimiento
	 * @throws ExcepcionUsuario si el usuario no existe
	 */
	public List<String> listarMovimientosCuentaJugador(String login) throws ExcepcionUsuario {
		// Recupera la instancia de la colecci�n
		Jugador esteJugador = listaJugadores.get(login);
		// Si este jugador exist�a, no es null
		if (esteJugador != null) {
			// As� que delegamos en �l que nos informe de sus movimientos
			return esteJugador.listarMovimientosCuenta();
		} else {
			// Pero si no exist�a lanza una excepci�n
			throw new ExcepcionUsuario(CausaExcepcionUsuario.NO_EXISTE, login);
		}
	}
	
}
