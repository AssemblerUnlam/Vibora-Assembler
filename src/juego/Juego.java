package juego;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import entidades.Fruta;
import entidades.Vibora;
import jugador.Jugador;
import jugador.ManejoJugador;
import utilidades.Punto;


public class Juego {
	public static int ANCHO = 200;
	public static int ALTO = 200;
	static int mapa[][]= new int[ALTO][ANCHO];
	private static Tickle tickle;
	
	
	
	private static final TreeMap<String, ManejoJugador> listaJugadores = new TreeMap<>();
	private static TreeMap<String, ManejoJugador> copiaListaJugadores = new TreeMap<>();
	private static Fruta fruta;
	
	public  static void iniciarMapa(){ 
		for (int i = 0; i < ALTO; i++) {
			mapa[i][0] = 999;
			mapa[i][ANCHO-1] = 999;
		}
		for (int i = 0; i < mapa.length; i++) {
			mapa[0][i] = 999;
			mapa[ALTO-1][i] = 999;
		}
		fruta=new Fruta();
		tickle = new Tickle();
		tickle.start();
	}
	
	
	public static synchronized boolean salidaUsuario (String nombre){
		return listaJugadores.containsKey(nombre);
	}
	
	public static synchronized void agregarJugador (String nombre, ManejoJugador jugador){
		listaJugadores.put(nombre, jugador);
		notificarCambioJugador();
	}
	
	public static synchronized ArrayList<Jugador> getNombreJugadores(){
		ArrayList<Jugador> jugadores = new ArrayList();
		for(Map.Entry<String, ManejoJugador> entry : listaJugadores.entrySet()) {
			String key = entry.getKey();
			ManejoJugador m = entry.getValue();
			jugadores.add(new Jugador(m.getNombre(), m.getPuntaje(),m.getColor()));
		}		
		return jugadores;
	}
	
	public static synchronized void notificarCambioJugador(){
		copiaListaJugadores = new TreeMap<>(listaJugadores);
		listaJugadores.values().forEach(c -> c.cambioListaJugadores());
	}

	public static synchronized void notificarNuevaVibora(Collection<Punto> cuerpo,String nombre,Color color) {
		listaJugadores.values().forEach( c -> c.dibujarVibora(cuerpo,nombre,color));
	}
	
	public static synchronized Collection<ManejoJugador> getTodasLasViboras(){
		return listaJugadores.values();
	}
	
	public static synchronized void moverVibora(){
		
		copiaListaJugadores.values().forEach(c -> c.moverVibora());
	}
	
	public static synchronized void notificarNuevoMovimientoCabeza(Punto cabeza,String nombre, int puntaje,Color color){
		listaJugadores.values().forEach(c-> c.enviarMovimientoCabeza(cabeza, nombre,puntaje,color));
		mapa[cabeza.getY()][cabeza.getX()] = 1;
		
	}

	public static synchronized void notificarNuevoMovimientoCola(Punto cola,String nombre){
		listaJugadores.values().forEach(c-> c.enviarMovimientoCola(cola));
		mapa[cola.getY()][cola.getX()] = 0;
		
	}
	
	public static synchronized void moverUnicaVibora(String nombre){
		listaJugadores.get(nombre).moverVibora();
	}

	public static synchronized void eliminarJugador(String nombre) {
		notificarEliminarJugador(listaJugadores.get(nombre));
		listaJugadores.remove(nombre);
		notificarCambioJugador();
		copiaListaJugadores = new TreeMap<>(listaJugadores);
	}

	private static synchronized void notificarEliminarJugador(ManejoJugador manejoJugador) {
		for (Punto p : manejoJugador.getCuerpoVibora()) {
			mapa[p.getY()][p.getX()] = 0;
		}
		listaJugadores.values().forEach(c -> c.salidaJugador(manejoJugador));
	}
	
	public static synchronized int chequearColision(Vibora vibora){
		Punto punto = vibora.getNuevaCabeza();
		if(mapa[punto.getY()][punto.getX()] >= 1 ){
			return 1;
		}
		if(mapa[punto.getY()][punto.getX()] == -1 ){
			return -1;
		}
		if(mapa[punto.getY()][punto.getX()] == -2 ){
			return -2;
		}
		return 0;
		
	}

	public static synchronized void aparicionVibora(Collection<Punto> cuerpo) {
		for (Punto punto : cuerpo) {
			mapa[punto.getY()][punto.getX()] = 1;
		}
	}
	
	public static synchronized void agregarFruta(){
		fruta.agregarFruta(Punto.randomCoord());
	}

	public static synchronized void agregarVelocidadFruta(){
		fruta.agregarVelocidadFruta(Punto.randomCoord());
	}
	
	public static synchronized void notificarNuevaFruta(Punto punto){
		listaJugadores.values().forEach(c -> c.nuevaFruta(punto));
		mapa[punto.getY()][punto.getX()] = -1;
	}
	

	public static synchronized void notificarNuevaVelocidadFruta(Punto a){
		listaJugadores.values().forEach(c -> c.nuevaVelocidadFruta(a));
		mapa[a.getY()][a.getX()] = -2;
	}
	
	public static synchronized ArrayList<Punto> getTodasLasFrutas(){
		return fruta.getLista();
	}


	public static synchronized void removerFruta(Punto apple, int which) {
		
		int n = fruta.eliminarFruta(apple,which);
		if(n != -1){
			fruta.borrarFrutaPorIndice(n);
		}
		
	}


	public static void cambiarVelocidad() {
		tickle.setTiempo(10);
		
	}
	
	public static synchronized Color getColorJugador(String nombre) {
		Color color = Color.BLACK;
		color = listaJugadores.get(nombre).getColor();
		return color;
	}
	
}
	
	
