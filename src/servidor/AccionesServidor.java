package servidor;

import java.awt.Color;
import java.util.Collection;
import java.util.TreeMap;

import jugador.ManejoJugador;
import utilidades.Punto;

public interface AccionesServidor {
	default public void recibirNombre(String nombre){}
	public void nombreOK();
	public void nombreMal();
	default public void enviarListaJugadores(TreeMap<String, Integer> listaJugadores){}
	default public void crearVibora(Collection<Punto> cuerpo,String nombre,Color color){ }
	default public void listaJugadores(){}
	default public void cambioListaJugadores(){}
	default void dibujarVibora(Collection<Punto> cuerpo,String nombre,Color color){}
	default void nuevaVibora(){}
	default void enviarVibora(String name, Collection<Punto> cuerpo,Color color){}
	default void enviarMovimientoCabeza(Punto cabeza,String nombre, Integer puntaje,Color color){}
	default void enviarMovimientoCola(Punto cola){}
	public default void cambiarDireccion(String dir){}
	public default void moverVibora(){}
	default public void salidaJugador(ManejoJugador manejoJugador){}
	default public void muerteVibora(){}
	default public void limpiarVibora(Collection<Punto> cuerpoVibora){}
	default public void nuevaFruta(Punto p){}
	default public void nuevaVelocidadFruta(Punto p){}
	default public void enviarPuntaje(String nombre, int puntaje){}
}
