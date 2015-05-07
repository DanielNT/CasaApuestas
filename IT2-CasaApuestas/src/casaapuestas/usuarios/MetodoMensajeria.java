package casaapuestas.usuarios;

/**
 * Tipo enumerado que lista los métodos de mensajería por los que se pueden mandar avisos a los usuarios
 * 
 * @author Eduardo Gómez Sánchez, ETSIT UVa.
 */
public enum MetodoMensajeria {
	/** Método de mensajería basado en el envío de un SMS a un teléfono móvil */
	SMS,
	/** Método de mensajería basado en el envío de un correo electrónico */
	CORREO
}
