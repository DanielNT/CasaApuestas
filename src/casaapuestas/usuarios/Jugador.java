package casaapuestas.usuarios;

import java.util.List;

import casaapuestas.cuentas.Cuenta;
import casaapuestas.cuentas.ExcepcionCuenta;




/**
 * Clase que representa a un Jugador en la casa de apuestas. Deriva de <code>Usuario</code>, añadiendo la posesión de una cuenta y métodos relacionados con las
 * apuestas.
 * 
 * @author Eduardo Gómez Sánchez, ETSIT UVa.
 */
public class Jugador extends Usuario {
	/** Cuenta de dinero del jugador ante la casa de apuestas */
	private Cuenta cuenta;

	/**
	 * Constructor que recibe como parámetros todos los campos necesarios para crear una instancia de <code>Usuario</code>
	 * 
	 * @param login el login, o identificador único de usuario
	 * @param clave la clave del usuario (en claro)
	 * @param nombre el nombre del usuario
	 * @param apellidos los apellidos del usuario
	 * @param nif el NIF del usuario
	 * @param movil el número de teléfono del usuario
	 * @param correo la dirección de correo del usuario
	 * @param metodo el método de mensajería preferido por el usuario
	 */
	public Jugador(String login, String clave, String nombre, String apellidos, String nif, String movil, String correo, MetodoMensajeria metodo) {
		// Invoca al constructor de la superclase
		super(login, clave, nombre, apellidos, nif, movil, correo, metodo);
		// Y crea una nueva cuenta
		cuenta = new Cuenta();
	}

	/**
	 * Método que permite que se realice un ingreso en la cuenta de este usuario
	 * @param concepto el concepto por el que se ingresa
	 * 
	 * @param cantidad la cantidad que se ingresa
	 */
	public void realizarIngreso(String concepto, float cantidad) {
		// Pasa la petición a la cuenta
		cuenta.realizarIngreso(concepto, cantidad);
	}

	/**
	 * Método que permite que se realice un reintegro desde la cuenta de este usuario
	 * @param concepto el concepto por el que se reintegra
	 * 
	 * @param cantidad la cantidad que se reintegra
	 * @throws ExcepcionCuenta si el saldo de la cuenta no es suficiente para reintegrar la <code>cantidad</code>
	 */
	public void realizarReintegro(String concepto, float cantidad) throws ExcepcionCuenta {
		// Pasa la petición a la cuenta
		cuenta.realizarReintegro(concepto, cantidad);
	}

	/**
	 * Método que permite que un jugador informe de su saldo
	 * @return el saldo en la cuenta del jugador
	 */
	public float verSaldo() {
		//Pasa la petición a la cuenta
		return cuenta.getSaldo();
	}

	/**
	 * Método que permite que un jugador informe de los movimientos de su cuenta
	 * @return una lista de cadenas, donde cada elemento corresponde a un movimiento
	 */
	public List<String> listarMovimientosCuenta() {
		//Pasa la petición a la cuenta
		return cuenta.listarMovimientos();
	}
}
