package juego;

import jugador.JugadorLogueado;
import servidor.Servidor;

public class MensajesAJugador implements JugadorLogueado{

	@Override
	public void direccionJugador(String nombre, String dir) {
		System.out.println(nombre + " giro " + dir);
	}

	@Override
	public void jugadorConectado(String ip) {
		System.out.println("Conectando a  ..." + ip );
		
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
	public void unirJugadorAJuego(String nombre) {
		System.out.println("Nuevo juego ejecutado por : " + nombre);
		
	}

	@Override
	public void muerteVibora(String nombre) {
		System.out.println("Murio la vibora de : "+ nombre);
		
	}

	@Override
	public void mensajeSistema(String mensaje) {
		System.out.println("Entrando mensaje de sistema : " + mensaje);
		
	}

	@Override
	public Object obtenerLogueado(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
