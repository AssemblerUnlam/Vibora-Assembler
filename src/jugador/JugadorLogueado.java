package jugador;

public interface JugadorLogueado {
	public void jugadorConectado(String ip);
	public void jugadorDesconectado(String ip, String nombre);
	public void obtenerNombreJugador(String ip, String nombre);
	public void unirJugadorAJuego(String nombre);
	public void muerteVibora(String nombre);
	public void mensajeSistema(String mensaje);
	public void direccionJugador(String nombre, String dir);
	public Object obtenerLogueado(String nombre);
}
