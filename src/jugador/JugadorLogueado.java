package jugador;

public interface JugadorLogueado {
	public void jugadorDesconectado(String ip, String nombre);
	public void obtenerNombreJugador(String ip, String nombre);
	public void muerteVibora(String nombre);
	public void direccionJugador(String nombre, String dir);
}
