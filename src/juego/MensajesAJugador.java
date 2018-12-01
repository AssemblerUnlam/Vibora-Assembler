package juego;

import jugador.JugadorLogueado;
import servidor.Servidor;

public class MensajesAJugador implements JugadorLogueado{

	@Override
	public void direccionJugador(String nombre, String dir) {
		Servidor.getLog().append(nombre + " giro " + dir + System.lineSeparator());
	}

	@Override
	public void jugadorDesconectado(String ip, String nombre) {
		Servidor.getLog().append("Jugador" + nombre + "desconectado desde" + ip + System.lineSeparator());
		
	}

	@Override
	public void obtenerNombreJugador(String ip, String nombre) {
		Servidor.getLog().append("Jugador conectado a " +ip +" nuevo nombre es " + nombre + System.lineSeparator());
		
	}

	@Override
	public void muerteVibora(String nombre) {
		Servidor.getLog().append("Murio la vibora de : "+ nombre + System.lineSeparator());
		
	}
	
}
