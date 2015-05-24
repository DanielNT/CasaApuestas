package casaapuestas.usuarios;





/**
 * Clase abstracta de la que derivan el resto de los usuarios. Tiene los atributos comunes a todos ellos, y métodos <i>getter</i> y <i>setter</i>.
 * 
 * @author Eduardo Gómez Sánchez, ETSIT UVa.
 */
public abstract class Usuario {
	/** El login, o identificador único de usuario */
	private String login;
	/** La clave del usuario (en claro) */
	private String clave;
	/** El nombre del usuario */
	private String nombre;
	/** Los apellidos del usuario */
	private String apellidos;
	/** El NIF del usuario */
	private String nif;
	/** El número de teléfono del usuario */
	private String movil;
	/** La dirección de correo del usuario */
	private String correo;
	/** El método de mensajería preferido por el usuario */
	private MetodoMensajeria metodo;

	/**
	 * Constructor que crea una instancia de esta clase recibiendo como parámetros todos los atributos
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
	 * Método que devuelve la clave
	 * 
	 * @return la clave
	 */
	public String getClave() {
		return clave;
	}

	/**
	 * Método que fija la clave
	 * 
	 * @param clave el nuevo valor para la clave
	 */
	public void setClave(String clave) {
		this.clave = clave;
	}

	/**
	 * Método que devuelve el nombre
	 * 
	 * @return el nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Método que fija el nombre
	 * 
	 * @param nombre el nuevo valor para el nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Método que devuelve los apellidos
	 * 
	 * @return los apellidos
	 */
	public String getApellidos() {
		return apellidos;
	}

	/**
	 * Método que fija los apellidos
	 * 
	 * @param apellidos el nuevo valor para los apellidos
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	/**
	 * Método que devuelve el NIF
	 * 
	 * @return el NIF
	 */
	public String getNif() {
		return nif;
	}

	/**
	 * Método que fija el NIF
	 * 
	 * @param nif el nuevo valor para el NIF
	 */
	public void setNif(String nif) {
		this.nif = nif;
	}

	/**
	 * Método que devuelve el número de móvil
	 * 
	 * @return el número de móvil, como una cadena
	 */
	public String getMovil() {
		return movil;
	}

	/**
	 * Método que fija el número de móvil
	 * 
	 * @param movil el nuevo número de móvil, como una cadena
	 */
	public void setMovil(String movil) {
		this.movil = movil;
	}

	/**
	 * Método que devuelve la dirección de correo
	 * 
	 * @return la dirección correo
	 */
	public String getCorreo() {
		return correo;
	}

	/**
	 * Método que fija la dirección de correo
	 * 
	 * @param correo la nueva dirección de correo
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
	}

	/**
	 * Método que devuelve el método de mensajería preferido
	 * 
	 * @return el metodo de mensajería preferido
	 */
	public MetodoMensajeria getMetodo() {
		return metodo;
	}

	/**
	 * Método que fija el método de mensajería preferido
	 * 
	 * @param metodo el nuevo método de mensajería preferido
	 */
	public void setMetodo(MetodoMensajeria metodo) {
		this.metodo = metodo;
	}

	/**
	 * Método que devuelve el login, o identificador único de usuario
	 * 
	 * @return el login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Método que permite a un usuario mostrar sólo la información necesaria para identificarle
	 * 
	 * @return una cadena con la información que identifica a este usuario
	 */
	public String verFichaBreve() {
		// Compone una cadena con los campos relevantes y la retorna
		return login + ": " + nombre + " " + apellidos;
	}

	/**
	 * Método que permite a un usuario mostrar en una sola cadena toda su información relevante
	 * 
	 * @return una cadena con toda la información de este usuario
	 */
	public String verFichaCompleta() {
		// Compone una cadena con todos los campos y la retorna
		return "Usuario: " + login + "\n" + "Clave: " + clave + "\n" + "Nombre: " + nombre + "\n" + "Apellidos: " + apellidos + "\n" + "NIF: " + nif + "\n"
				+ "Número de móvil: " + movil + "\n" + "Dirección de correo: " + correo + "\n" + "Método de mensajería: " + metodo.toString();
	}

}
