package casaapuestas.cuentas;

/**
 * Clase de tipo singleton que gestiona la cuenta de la casa de apuestas
 */
public class CuentaCasaApuestas {
	
	private static CuentaCasaApuestas instancia;
	private float saldoCuenta;
	
	private CuentaCasaApuestas(){
		
	}
	
	/**
	 * @return Cuenta de la casa de apuestas
	 */

	public static CuentaCasaApuestas getInstance(){
		if(instancia == null){
			instancia = new CuentaCasaApuestas();
			instancia.saldoCuenta = 0;
		}
		return instancia;
	}
	/**
	 * @param saldoAAumentar es el saldo que se añade a la cuenta
	 */
	public void add(float saldoAAumentar){
		saldoCuenta = saldoCuenta + saldoAAumentar;
	}
	/**
	 * @param saldoAReducir es el saldo que se quita de la cuenta
	 * @return Devuelve true si la operación se ha realizado correctamente y false en caso contrario
	 */
	public boolean substract(float saldoAReducir){
		if(saldoAReducir > saldoCuenta)
		{
			return false;
		}
		saldoCuenta = saldoCuenta - saldoAReducir;
		return true;
	}
	/**
	 * @return Saldo de la casa de apuestas
	 */
	public float getSaldo(){
		return saldoCuenta;
	}
}