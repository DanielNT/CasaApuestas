package casaapuestas.usuarios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import casaapuestas.cuentas.ExcepcionCuenta;




/**
 * Clase controladora que recibe los métodos provenientes de la UI relacionados con la gestión de usuarios
 * 
 * @author Eduardo Gómez Sánchez, ETSIT UVa.
 */
public class ControladorUsuarios {
	/**
	 * Lista de los jugadores existentes en el sistema, indexada por su login o identificador de usuario (que por lo tanto debe ser único)
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
	
	
	
	// |------------------------------|
	
	
	/**
	 * Método que permite obtener una lista de cadenas, cada una con información breve de cada instancia de <code>Jugador</code> coleccionada por este
	 * controlador.
	 * 
	 * @return Una lista de cadenas
	 */
	public List<String> listarJugadores() {
		// Inicializa la lista
		List<String> listado = new ArrayList<String>();

		// Recorre la colección de jugadores
		for (Jugador j : listaJugadores.values()) {
			// A cada jugador le pide información breve
			String ficha = j.verFichaBreve();
			// Y la añade al listado
			listado.add(ficha);
		}

		// Al terminar retorna el listado
		return listado;
	}
	

	/**
	 * Método que crea una nueva instancia de <code>Jugador</code> y la colecciona, indexada por el <code>login</code>
	 * 
	 * @param login el login, o identificador único de usuario
	 * @param clave la clave del usuario (en claro)
	 * @param nombre el nombre del usuario
	 * @param apellidos los apellidos del usuario
	 * @param nif el NIF del usuario
	 * @param movil el número de teléfono del usuario
	 * @param correo la dirección de correo del usuario
	 * @param metodo el método de mensajería preferido por el usuario
	 * @throws ExcepcionUsuario si ya existe un usuario con este <code>login</code>
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
			// Pero si ya existía lanza una excepción
			throw new ExcepcionUsuario(CausaExcepcionUsuario.YA_EXISTE, login);
		}
	}

	/**
	 * Método que permite mostrar toda la información de una instancia de <code>Jugador</code> dada por un determinado <code>login</code>
	 * 
	 * @param login login el login, o identificador único de usuario
	 * @return una cadena con toda la información del usuario buscado
	 * @throws ExcepcionUsuario si no existe un usuario con este <code>login</code>
	 */
	public String mostrarJugador(String login) throws ExcepcionUsuario {
		// Recupera la instancia de la colección
		Jugador esteJugador = listaJugadores.get(login);
		// Si este jugador existía, no es null
		if (esteJugador != null) {
			// Le pide al jugador que muestre su información completa
			return esteJugador.verFichaCompleta();
		} else {
			// Pero si no existía lanza una excepción
			throw new ExcepcionUsuario(CausaExcepcionUsuario.NO_EXISTE, login);
		}
	}

	/**
	 * Método que modifica una instancia de <code>Jugador</code> dada por un determinado <code>login</code>
	 * 
	 * @param login el login, o identificador único de usuario (no se puede modificar)
	 * @param clave la clave del usuario (en claro)
	 * @param nombre el nombre del usuario
	 * @param apellidos los apellidos del usuario
	 * @param nif el NIF del usuario
	 * @param movil el número de teléfono del usuario
	 * @param correo la dirección de correo del usuario
	 * @param metodo el método de mensajería preferido por el usuario
	 * @throws ExcepcionUsuario si no existe un usuario con este <code>login</code>
	 */
	public void modificarJugador(String login, String clave, String nombre, String apellidos, String nif, String movil, String correo, MetodoMensajeria metodo)
			throws ExcepcionUsuario {
		// Recupera la instancia de la colección
		Jugador esteJugador = listaJugadores.get(login);
		// Si este jugador existía, no es null
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
			// Pero si no existía lanza una excepción
			throw new ExcepcionUsuario(CausaExcepcionUsuario.NO_EXISTE, login);
		}
	}

	/**
	 * Método que permite borrar una instancia de <code>Jugador</code> dada por un determinado <code>login</code>
	 * 
	 * @param login el login, o identificador único de usuario
	 * @throws ExcepcionUsuario si no existe un usuario con este <code>login</code>
	 */
	public void eliminarJugador(String login) throws ExcepcionUsuario {
		// Borra la instancia de la colección
		Jugador esteJugador = listaJugadores.remove(login);
		// Si este jugador es null es que no existía, así que podemos informar de que no lo hemos borrado (porque no existía)
		if (esteJugador == null) {
			throw new ExcepcionUsuario(CausaExcepcionUsuario.NO_EXISTE, login);
		}
	}
	
	/**
	 * Método que permite obtener un jugador mediante su login.
	 * @param login El nombre de usuario del jugador
	 * @return el jugador
	 * @throws ExcepcionUsuario Si algo ha ido mal al obtener el jugador
	 */
	public Jugador getJugador(String login) throws ExcepcionUsuario {
		
		// Recupera la instancia de la colección
		Jugador esteJugador = listaJugadores.get(login);
		
		// Si el jugador no existe lanza una excepcion
		if (esteJugador == null) 
		{
			throw new ExcepcionUsuario(CausaExcepcionUsuario.NO_EXISTE, login);
		} 
		
		return esteJugador;
	}
	
	
	// |------------------------------|
	
	
	/**
	 * Método que permite ver el saldo en la cuenta del jugador identificado por el <code>login</code>
	 * @param login el login, o identificador único de usuario
	 * @return el saldo en la cuenta del jugador
	 * @throws ExcepcionUsuario si el usuario no existe
	 */
	public float verSaldoJugador(String login) throws ExcepcionUsuario {
		// Recupera la instancia de la colección
		Jugador esteJugador = listaJugadores.get(login);
		// Si este jugador existía, no es null
		if (esteJugador != null) {
			// Así que delegamos en él que nos informe de su saldo
			return esteJugador.verSaldo();
		} else {
			// Pero si no existía lanza una excepción
			throw new ExcepcionUsuario(CausaExcepcionUsuario.NO_EXISTE, login);
		}
	}
	
	/**
	 * Método que permite obtener un listado de los movimientos de cuenta del jugador identificador por el  <code>login</code>
	 * @param login el login, o identificador único de usuario
	 * @return una lista de cadenas, donde cada elemento corresponde a un movimiento
	 * @throws ExcepcionUsuario si el usuario no existe
	 */
	public List<String> listarMovimientosCuentaJugador(String login) throws ExcepcionUsuario {
		// Recupera la instancia de la colección
		Jugador esteJugador = listaJugadores.get(login);
		// Si este jugador existía, no es null
		if (esteJugador != null) {
			// Así que delegamos en él que nos informe de sus movimientos
			return esteJugador.listarMovimientosCuenta();
		} else {
			// Pero si no existía lanza una excepción
			throw new ExcepcionUsuario(CausaExcepcionUsuario.NO_EXISTE, login);
		}
	}
	

	/**
	 * Método que permite realizar un ingreso de una <code>cantidad</code> de dinero en la cuenta del jugador identificado por el <code>login</code>
	 * 
	 * @param login el login, o identificador único de usuario
	 * @param cantidad la cantidad a ingresar en la cuenta (positiva)
	 * @throws ExcepcionUsuario si el usuario no existe
	 */
	public void realizarIngresoEnCuentaJugador(String login, float cantidad) throws ExcepcionUsuario {
		// Recupera la instancia de la colección
		Jugador esteJugador = listaJugadores.get(login);
		// Si este jugador existía, no es null
		if (esteJugador != null) {
			// Así que delegamos en él que realice el ingreso
			esteJugador.realizarIngreso("Ingreso en efectivo por el usuario " + login, cantidad);
		} else {
			// Pero si no existía lanza una excepción
			throw new ExcepcionUsuario(CausaExcepcionUsuario.NO_EXISTE, login);
		}
	}

	/**
	 * Método que permite realizar un reintegro de una <code>cantidad</code> de dinero desde la cuenta del jugador identificado por el <code>login</code>
	 * 
	 * @param login el login, o identificador único de usuario
	 * @param cantidad la cantidad a reintegrar desde la cuenta (positiva)
	 * @throws ExcepcionUsuario si el usuario no existe
	 * @throws ExcepcionCuenta si la cuenta no tiene saldo suficiente para reintegrar dicha cantidad
	 */
	public void realizarReintegroDesdeCuentaJugador(String login, float cantidad) throws ExcepcionUsuario, ExcepcionCuenta {
		// Recupera la instancia de la colección
		Jugador esteJugador = listaJugadores.get(login);
		// Si este jugador existía, no es null
		if (esteJugador != null) {
			// Así que delegamos en él que realice el ingreso (ojo, este método lanza una excepción)
			esteJugador.realizarReintegro("Reintegro en efectivo por el usuario " + login, cantidad);
		} else {
			// Pero si no existía lanza una excepción
			throw new ExcepcionUsuario(CausaExcepcionUsuario.NO_EXISTE, login);
		}
	}
	
	
	
	
	
	
	// |------------------------------|
	
	
	
}
