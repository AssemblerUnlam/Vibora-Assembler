package jugador;

public interface JugadorLogueado {
	/**
	 * La clase JugadorLogueado contiene los metodos para enviar mensajes al servidor de cada jugador.
	 */
	public void jugadorDesconectado(String ip, String nombre);
	public void obtenerNombreJugador(String ip, String nombre);
	public void muerteVibora(String nombre);
	public void direccionJugador(String nombre, String dir);
}
