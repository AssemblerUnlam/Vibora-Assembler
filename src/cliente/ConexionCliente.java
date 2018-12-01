package cliente;

import java.awt.Color;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import jugador.Jugador;
import servidor.Servidor;
import utilidades.Punto;

public class ConexionCliente extends Thread implements  AccionesCliente  {
	
	private ArrayList<Jugador> puntajeJugadores = new ArrayList<>();
	private Socket socket = null;
	private SalidaCliente salidaCliente;
	private EntradaCliente entradaCliente;
	private Cliente cliente;
	private boolean detener = false;
	
	public ConexionCliente(Socket s,Cliente c) {
		this.socket= s;
		this.cliente = c;
		try {
			salidaCliente = new SalidaCliente(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		try(Socket s1 = socket){
			entradaCliente = new EntradaCliente(s1.getInputStream(), this);
			entradaCliente.doRun();
		} catch (IOException e) {
			e.printStackTrace();
			finish();
		}
		
	}
	
	private void finish(){
		if(!detener){
			detener = true;
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void enviarNombre(String nombre) {
		salidaCliente.enviarNombre(nombre);
	}

	@Override
	public void nombreOK() {
		cliente.aceptarNombre();
	}

	@Override
	public void nombreMal() {
		finish();
		entradaCliente.setDetener();
		cliente.reiniciar();
		
	}

	@Override
	public void pedirListaJugadores() {
		salidaCliente.pedirListaJugadores();
		
	}

	@Override
	public void enviarListaJugadores(ArrayList<Jugador> listaJugadores) {
		Collections.sort(puntajeJugadores);
		puntajeJugadores = new ArrayList<>(listaJugadores);
		cliente.llenarListaJugadores(puntajeJugadores);
	}

	@Override
	public void iniciarVibora(Collection<Punto> cuerpo, String nombre,Color color) {
		cliente.dibujarCuerpo(cuerpo,nombre,color);
		
	}

	@Override
	public void enviarDireccion(String dir) {
		salidaCliente.enviarDireccion(dir);
	}

	@Override
	public void dibujarMovimientoCabeza(Punto cabeza,String nombre,Color color) {
		cliente.dibujarCabeza(cabeza,nombre,color);
	}

	@Override
	public void dibujarMovimientoCola(Punto cola) {
		cliente.dibujarCola(cola);
	}
	
	@Override
	public void borrarVibora(ArrayList<Punto> cuerpo) {
		cliente.borrarCuerpo(cuerpo);
	}
	
	@Override
	public void muere() {
		//**
		Servidor.quitarJugador();
		//**
		finish();
		// VENTANA: Desea JUGAR otra partida??		
		cliente.reiniciar();
	}

	@Override
	public void dibujarFruta(Punto fruta) {
		cliente.dibujarFruta(fruta);
	}

	
	@Override
	public void dibujarVelocidadFruta(Punto punto) {
		cliente.dibujarVelocidadFruta(punto);
	}

	@Override
	public void reclasificar(String nombre, Integer puntaje) {
		for (Jugador player : puntajeJugadores) {
			if(player.getNombre().equals(nombre)){
				player.incrementarPuntaje(puntaje);
				Collections.sort(puntajeJugadores);
				cliente.llenarListaJugadores(puntajeJugadores);
				return;
			}	
		}
	}
}
