package jugador;

import java.awt.Color;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;

import entidades.Vibora;
import juego.Juego;
import servidor.AccionesServidor;
import servidor.EntradaServidor;
import servidor.SalidaServidor;
import utilidades.Direccion;
import utilidades.Punto;


@SuppressWarnings("unused")
public class ManejoJugador implements Runnable, AccionesServidor {
	private final Socket socketJugador;
	private SalidaServidor salidaServidor;
	private EntradaServidor entradaServidor;
	private String nombre = "";
	private Vibora vibora;
	private int puntaje ;
	private JugadorLogueado logger = null;
	private boolean detener = false;
	private Color color;

	private enum EstadoJugador{ INICIO, LOGUEADO }
	private EstadoJugador estado = EstadoJugador.INICIO;

	public ManejoJugador (Socket socketJugador, JugadorLogueado logger) throws IOException{
		this.socketJugador = socketJugador;
		this.logger = logger;
		this.puntaje = 0;
		Random random = new Random();
		float r = random.nextFloat();
		float g = random.nextFloat();
		float b = random.nextFloat();
		this.color = new Color(r,g,b);
	}
	
	public int getPuntaje(){
		return this.puntaje;
	}
	public String getNombre(){
		return this.nombre;
	}

	@Override
	public void run() {
		try(Socket s1 = socketJugador){
			salidaServidor = new SalidaServidor(s1.getOutputStream());
			entradaServidor  = new EntradaServidor(s1.getInputStream(), this);
			entradaServidor.doRun();
		}catch (IOException e){
			if(!detener){
				logger.mensajeSistema("se detuvo el jugador");
				finalizar();
			}
		}

	}

	public synchronized void finalizar(){
		if(!detener){
			detener=true;
			try{
				socketJugador.close();
			} catch(IOException e){ e.printStackTrace();}
			if(estado == EstadoJugador.LOGUEADO)
				Juego.eliminarJugador(nombre); 
			logger.jugadorDesconectado(socketJugador.toString(), nombre);
		}
	}

	@Override
	public void recibirNombre(String nombre) {
		String nuevoNombre = nombre;
		if(Juego.salidaUsuario(nombre) || nombre.equals("null") || nombre.length() == 0){
			nombreMal();
			finalizar();
			
		}else{
			nombreOK();
			if(estado == EstadoJugador.INICIO){
				this.nombre=nombre;
				Juego.agregarJugador(nuevoNombre, this);
				logger.unirJugadorAJuego(nuevoNombre);
			}
		}

	}



	@Override
	public void moverVibora() {
		if(estado != EstadoJugador.LOGUEADO) 
			return;
		switch( Juego.chequearColision(vibora)){
			case 1: // collision
				vibora.muere();
				salidaServidor.muerteVibora();
				finalizar();
				break;
			case 0: // movimiento regular
				vibora.mover();
				Juego.notificarNuevoMovimientoCabeza(vibora.getCabeza(),nombre,0,color);
				Juego.notificarNuevoMovimientoCola(vibora.getCola(),nombre);
				vibora.borrarCola();
				break;
			case -1 :// come manzana para crecer
				vibora.mover();
				puntaje ++;
				Juego.notificarNuevoMovimientoCabeza(vibora.getCabeza(), nombre,1,color);
				Juego.removerFruta(vibora.getCabeza(), -1);
				break;
			case -2 :// come manzana azul para crecer y velocidad
				vibora.mover();
				puntaje ++;
				Juego.notificarNuevoMovimientoCabeza(vibora.getCabeza(), nombre,1,color);
				Juego.removerFruta(vibora.getCabeza(), -2);
				Juego.cambiarVelocidad();
				break;
				
			}
	}

	@Override
	public void enviarMovimientoCabeza(Punto cabeza,String nombre, Integer puntaje,Color color) {
		salidaServidor.enviarMovimientoCabeza(cabeza, nombre, puntaje,color);
	}
	
	@Override
	public void enviarMovimientoCola(Punto cola) {
		salidaServidor.enviarMovimientoCola(cola);
	}

	@Override
	public void nombreOK() {
		salidaServidor.nombreOK();
	}

	@Override
	public void nombreMal() {
		salidaServidor.nombreMal();

	}

	@Override
	public void listaJugadores() {
		salidaServidor.enviarListaJugadores(Juego.getNombreJugadores());

	}
	
	

	@Override
	public void cambioListaJugadores(){
		salidaServidor.enviarListaJugadores(Juego.getNombreJugadores());
	}

	@Override
	public void nuevaVibora(){
		vibora = new Vibora();

		Juego.aparicionVibora(vibora.getCuerpo());

		for (ManejoJugador jugador : Juego.getTodasLasViboras()) {
			salidaServidor.enviarVibora(jugador.nombre, jugador.getCuerpoVibora(),color);
		}
		
		Juego.notificarNuevaVibora(vibora.getCuerpo(),nombre,color);
		
		ArrayList<Punto> copiaListaFruta = new ArrayList<>(Juego.getTodasLasFrutas());

		for ( Punto fruta : copiaListaFruta){
			salidaServidor.nuevaFruta(fruta);
		}
		estado = EstadoJugador.LOGUEADO;
	}

	@Override
	public void dibujarVibora(Collection<Punto> cuerpo,String nombre,Color color) {
		if(this.nombre.equals(nombre)) return;
			salidaServidor.crearVibora(cuerpo,nombre,color);
	}


	@Override
	public void cambiarDireccion(String dir) {
		if(dir.equals("M")){
			Juego.moverUnicaVibora(nombre);
		}else{
			Direccion d = null;
			switch (dir){
			case "38":
				d=Direccion.ARRIBA;
				break;
			case "40":
				d=Direccion.ABAJO;
				break;
			case "37":
				d=Direccion.IZQUIERDA;
				break;
			case "39":
				d=Direccion.DERECHA;
				break;
			default:
				logger.mensajeSistema("tecla invalida :" +dir);
			}
			vibora.setDireccion(d);
			logger.direccionJugador(nombre, dir);
		}
	}

	public Collection<Punto> getCuerpoVibora(){
		return vibora.getCuerpo();
	}

	@Override
	public void salidaJugador(ManejoJugador manejoJugador) {
		salidaServidor.limpiarVibora(manejoJugador.getCuerpoVibora());
	}

	@Override
	public void nuevaFruta(Punto p) {
		salidaServidor.nuevaFruta(p);
	}

	@Override
	public void nuevaVelocidadFruta(Punto p) {
		salidaServidor.nuevaVelocidadFruta(p);
	}

	@Override
	public void enviarPuntaje(String nombre, int puntaje) {
		salidaServidor.enviarPuntaje(nombre, puntaje);
	}

	public Color getColor() {
		return this.color;
	}


	




}
