package casaapuestas.usuarios;

/**
 * Clase abstracta de la que derivan el resto de los usuarios. Tiene los atributos comunes a todos ellos, y m�todos <i>getter</i> y <i>setter</i>.
 * 
 * @author Eduardo G�mez S�nchez, ETSIT UVa.
 */
public abstract class Usuario {
	/** El login, o identificador �nico de usuario */
	private String login;
	/** La clave del usuario (en claro) */
	private String clave;
	/** El nombre del usuario */
	private String nombre;
	/** Los apellidos del usuario */
	private String apellidos;
	/** El NIF del usuario */
	private String nif;
	/** El n�mero de tel�fono del usuario */
	private String movil;
	/** La direcci�n de correo del usuario */
	private String correo;
	/** El m�todo de mensajer�a preferido por el usuario */
	private MetodoMensajeria metodo;

	/**
	 * Constructor que crea una instancia de esta clase recibiendo como par�metros todos los atributos
	 * 
	 * @param login el login, o identificador �nico de usuario
	 * @param clave la clave del usuario (en claro)
	 * @param nombre el nombre del usuario
	 * @param apellidos los apellidos del usuario
	 * @param nif el NIF del usuario
	 * @param movil el n�mero de tel�fono del usuario
	 * @param correo la direcci�n de correo del usuario
	 * @param metodo el m�todo de mensajer�a preferido por el usuario
	 */
	public Usuario(String login, String clave, String nombre, String apellidos, String nif, String movil, String correo, MetodoMensajeria metodo) {
		super();
		this.login = login;
		this.clave = clave;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.nif = nif;
		this.movil = movil;
		this.correo = correo;
		this.metodo = metodo;
	}

	/**
	 * M�todo que devuelve la clave
	 * 
	 * @return la clave
	 */
	public String getClave() {
		return clave;
	}

	/**
	 * M�todo que fija la clave
	 * 
	 * @param clave el nuevo valor para la clave
	 */
	public void setClave(String clave) {
		this.clave = clave;
	}

	/**
	 * M�todo que devuelve el nombre
	 * 
	 * @return el nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * M�todo que fija el nombre
	 * 
	 * @param nombre el nuevo valor para el nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * M�todo que devuelve los apellidos
	 * 
	 * @return los apellidos
	 */
	public String getApellidos() {
		return apellidos;
	}

	/**
	 * M�todo que fija los apellidos
	 * 
	 * @param apellidos el nuevo valor para los apellidos
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	/**
	 * M�todo que devuelve el NIF
	 * 
	 * @return el NIF
	 */
	public String getNif() {
		return nif;
	}

	/**
	 * M�todo que fija el NIF
	 * 
	 * @param nif el nuevo valor para el NIF
	 */
	public void setNif(String nif) {
		this.nif = nif;
	}

	/**
	 * M�todo que devuelve el n�mero de m�vil
	 * 
	 * @return el n�mero de m�vil, como una cadena
	 */
	public String getMovil() {
		return movil;
	}

	/**
	 * M�todo que fija el n�mero de m�vil
	 * 
	 * @param movil el nuevo n�mero de m�vil, como una cadena
	 */
	public void setMovil(String movil) {
		this.movil = movil;
	}

	/**
	 * M�todo que devuelve la direcci�n de correo
	 * 
	 * @return la direcci�n correo
	 */
	public String getCorreo() {
		return correo;
	}

	/**
	 * M�todo que fija la direcci�n de correo
	 * 
	 * @param correo la nueva direcci�n de correo
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
	}

	/**
	 * M�todo que devuelve el m�todo de mensajer�a preferido
	 * 
	 * @return el metodo de mensajer�a preferido
	 */
	public MetodoMensajeria getMetodo() {
		return metodo;
	}

	/**
	 * M�todo que fija el m�todo de mensajer�a preferido
	 * 
	 * @param metodo el nuevo m�todo de mensajer�a preferido
	 */
	public void setMetodo(MetodoMensajeria metodo) {
		this.metodo = metodo;
	}

	/**
	 * M�todo que devuelve el login, o identificador �nico de usuario
	 * 
	 * @return el login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * M�todo que permite a un usuario mostrar s�lo la informaci�n necesaria para identificarle
	 * 
	 * @return una cadena con la informaci�n que identifica a este usuario
	 */
	public String verFichaBreve() {
		// Compone una cadena con los campos relevantes y la retorna
		return login + ": " + nombre + " " + apellidos;
	}

	/**
	 * M�todo que permite a un usuario mostrar en una sola cadena toda su informaci�n relevante
	 * 
	 * @return una cadena con toda la informaci�n de este usuario
	 */
	public String verFichaCompleta() {
		// Compone una cadena con todos los campos y la retorna
		return "Usuario: " + login + "\n" + "Clave: " + clave + "\n" + "Nombre: " + nombre + "\n" + "Apellidos: " + apellidos + "\n" + "NIF: " + nif + "\n"
				+ "N�mero de m�vil: " + movil + "\n" + "Direcci�n de correo: " + correo + "\n" + "M�todo de mensajer�a: " + metodo.toString();
	}

}
