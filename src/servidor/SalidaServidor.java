package servidor;

import java.awt.Color;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import jugador.Jugador;
import utilidades.Punto;



public class SalidaServidor implements AccionesServidor {
	PrintWriter os;
	
	public SalidaServidor(OutputStream salida) throws IOException{
		this.os = new PrintWriter(salida, true);
	}

	
	@Override
	public void nombreOK() {
		os.println("NOMBRE OK");
		
	}

	@Override
	public void nombreMal() {
		os.println("NOMBRE MAL");
		
	}


	@Override
	public void enviarListaJugadores(ArrayList<Jugador> listaJugadores){
		os.println("LISTA JUGADORES");
		for (Jugador jugador : listaJugadores) {
			os.println(jugador.getNombre());
			os.println(jugador.getPuntaje());
			os.println(Integer.toString(jugador.getColor().getRGB()));
		}
		os.println(".");
	}


	@Override
	public void crearVibora(Collection<Punto> cuerpo,String nombre,Color color){
		os.println("NUEVA VIBORA");
		os.println(nombre);
		os.println(Integer.toString(color.getRGB()));
		for (Punto point : cuerpo) {
			os.println(point.getX());
			os.println(point.getY());
		}
		
		os.println(".");
		
	}

	@Override
	public void enviarVibora(String nombre,Collection<Punto> cuerpo,Color color) {
		os.println("INICIAR VIBORA");
		os.println(nombre);
		os.println(Integer.toString(color.getRGB()));
		for (Punto point : cuerpo) {
			os.println(point.getX());
			os.println(point.getY());
		}
		
		os.println(".");
		
	}


	@Override
	public void enviarMovimientoCabeza(Punto cabeza,String nombre, Integer puntaje,Color color) {
		os.println("MOVER CABEZA");
		os.println(nombre);
		os.println(cabeza.getX());
		os.println(cabeza.getY());
		os.println(puntaje.toString());
		os.println(Integer.toString(color.getRGB()));
	}

	@Override
	public void enviarMovimientoCola(Punto cola) {
		os.println("MOVER COLA");
		os.println(cola.getX());
		os.println(cola.getY());
	}

	@Override
	public void limpiarVibora(Collection<Punto> cuerpoVibora) {
		os.println("LIMPIAR");
		for (Punto point : cuerpoVibora) {
			os.println(point.getX());
			os.println(point.getY());
		}
		os.println(".");
	}


	@Override
	public void muerteVibora() {
		os.println("MUERTE");
	}


	@Override
	public void nuevaFruta(Punto p) {
		os.println("NUEVA FRUTA");
		os.println(p.getX());
		os.println(p.getY());
	}


	@Override
	public void nuevaVelocidadFruta(Punto p) {
		os.println("VELOCIDAD FRUTA");
		os.println(p.getX());
		os.println(p.getY());
	}


	@Override
	public void enviarPuntaje(String nombre, int point) {
		os.println("PUNTAJE");
		os.println(nombre);
		os.println(point);
	}


	


	
	
}
