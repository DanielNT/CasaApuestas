package casaapuestas.cuentas;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa una cuenta de dinero mantenida en la aplicaci�n. Tiene un saldo y una lista de movimientos.
 * 
 * @author Eduardo G�mez S�nchez, ETSIT UVa.
 */
public class Cuenta {
	/** El saldo de la cuenta */
	private float saldo;
	/** La lista de movimientos de la cuenta */
	private List<Movimiento> listaMovimientos;

	/**
	 * Constructor que permite crear una cuenta nueva, por lo que su saldo inicial ser� 0
	 */
	public Cuenta() {
		super();
		// Inicializa el saldo
		saldo = 0;
		// Inicializa la lista de movimientos como un ArrayList vac�o
		listaMovimientos = new ArrayList<Movimiento>();
	}

	
	/**
	 * M�todo que permite recuperar el saldo de una cuenta
	 * @return el saldo
	 */
	public float getSaldo() {
		return saldo;
	}


	/**
	 * M�todo que permite realizar un ingreso en una cuenta
	 * 
	 * @param concepto el concepto por el que se ingresa
	 * @param cantidad la cantidad (positiva) que se ingresa
	 */
	public void realizarIngreso(String concepto, float cantidad) {
		// Crea el movimiento
		Movimiento m = new Movimiento(concepto, cantidad);
		// Lo a�ade a la lista de movimientos
		listaMovimientos.add(m);
		// Actualiza el saldo
		saldo += cantidad;
	}

	/**
	 * M�todo que permite realizar un reintegro desde una cuenta
	 * 
	 * @param concepto el concepto por el que se reintegra
	 * @param cantidad la cantidad (positiva) que se reintegra
	 * @throws ExcepcionCuenta si la <code>cantidad</code> es mayor que el saldo de la cuenta
	 */
	public void realizarReintegro(String concepto, float cantidad) throws ExcepcionCuenta {
		// Comprueba si hay saldo suficiente
		if (saldo >= cantidad) {
			// Crea el movimiento, cambiando el signo a la cantidad
			Movimiento m = new Movimiento(concepto, -cantidad);
			// Lo a�ade a la lista de movimientos
			listaMovimientos.add(m);
			// Actualiza el saldo
			saldo -= cantidad;
		} else {
			// Si el saldo no era suficiente, lanza una excepci�n
			throw new ExcepcionCuenta(CausaExcepcionCuentas.SALDO_INSUFICIENTE, concepto);
		}
	}


	/**
	 * M�todo que permite a una cuenta entregar un listado de los movimientos
	 * @return una lista de cadenas, donde cada elemento corresponde a un movimiento
	 */
	public List<String> listarMovimientos() {
		//Inicializa el listado
		List<String> listado = new ArrayList<String>();
		
		//Recorre la lista de movimientos
		for(Movimiento m : listaMovimientos) {
			//A cada movimiento le pide que informe sobre �l, y el resultado lo a�ade al listado
			listado.add(m.verFicha());
		}

		//Al terminar retorna el listado
		return listado;
	}

}
