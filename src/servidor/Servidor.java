package servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

import juego.MensajesAJugador;
import juego.ModeloVibora;
import jugador.JugadorLogueado;
import jugador.ManejoJugador;


public class Servidor extends Thread {
	private int puerto;
	ServerSocket serverSocket;
	private boolean detener = false;
	private JugadorLogueado logger = null;
	
	public Servidor (int puerto) throws IOException{
		this.puerto = puerto;
		logger = new MensajesAJugador();
		logger.mensajeSistema ("Servidor online");		
		this.start();
	}
	
	public void run() {
		try(ServerSocket serverSocket = new ServerSocket(puerto)){
			serverSocket.setSoTimeout(1000);
			ModeloVibora.iniciarMapa();
			while(!detener){
				try{
					Socket socketJugador = serverSocket.accept();
					logger.jugadorConectado(socketJugador.toString());
					new Thread(new ManejoJugador(socketJugador, logger)).start();
				}catch(SocketTimeoutException ex){
				}
			}
		} catch (IOException e) {
			
		}
		
	}
	
	public synchronized void finalizar(){
		this.detener=true;
	}
}
