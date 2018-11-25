package cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import jugador.Jugador;
import utilidades.MyProtocolException;
import utilidades.Punto;

public class EntradaCliente {
	
	private AccionesCliente accionesCliente;
	private InputStream entrada;
	private boolean detener = false;
	
	public EntradaCliente(InputStream entrada, AccionesCliente accionesCliente) {
		this.entrada = entrada;
		this.accionesCliente = accionesCliente;
	}
	public void doRun() throws IOException{
		ArrayList<Jugador> listaJugadores;
		ArrayList<Punto> cuerpo;
		String nombre;
		String x;
		String y;
		Integer puntaje;
		
		try(BufferedReader b = new BufferedReader(new InputStreamReader(entrada))){
			while(!detener){
				String linea = b.readLine();
				switch(linea){
				case "NOMBRE OK":
					accionesCliente.nombreOK();
					break;
				case "NOMBRE MAL":
					accionesCliente.nombreMal();
					break;
				case "LISTA JUGADORES":
					listaJugadores = new ArrayList<>();
					String x1;
					while(!(x1 = b.readLine()).equals(".")){
						puntaje = Integer.valueOf(b.readLine());
						listaJugadores.add(new Jugador(x1,puntaje));
					}
					accionesCliente.enviarListaJugadores(listaJugadores);
					break;
				case "NUEVA VIBORA":
					cuerpo = new ArrayList<>();
					nombre = b.readLine();
					
					while(!(x1 = b.readLine()).equals(".")){
						y = b.readLine();
						
						Punto p = new Punto(Integer.valueOf(x1), Integer.valueOf(y));
						cuerpo.add(p);
						
					}
					
					accionesCliente.iniciarVibora(cuerpo,nombre);
					break;
				case "INICIAR VIBORA":
					cuerpo = new ArrayList<>();
					nombre = b.readLine();
					
					while(!(x1 = b.readLine()).equals(".")){
						y = b.readLine();
						
						Punto p = new Punto(Integer.valueOf(x1), Integer.valueOf(y));
						cuerpo.add(p);
					}
					
					accionesCliente.iniciarVibora(cuerpo,nombre);
					break;
				case "MOVER CABEZA":
					nombre = b.readLine();
					x1=b.readLine();
					y=b.readLine();
					puntaje = Integer.valueOf(b.readLine());
					accionesCliente.dibujarMovimientoCabeza(new Punto(Integer.valueOf(x1), Integer.valueOf(y)),nombre);
					if(puntaje != 0)  accionesCliente.reclasificar(nombre,puntaje); 
					break;
				case "MOVER COLA":
					x1=b.readLine();
					y=b.readLine();
					accionesCliente.dibujarMovimientoCola(new Punto(Integer.valueOf(x1), Integer.valueOf(y)));
					break;
				case "LIMPIAR":
					cuerpo = new ArrayList<>();
					while(!(x1 = b.readLine()).equals(".")){
						y = b.readLine();
						
						Punto p = new Punto(Integer.valueOf(x1), Integer.valueOf(y));
						cuerpo.add(p);
					}
					accionesCliente.borrarVibora(cuerpo);
					break;
				case "PUNTAJE":
					nombre = b.readLine();
					y=b.readLine();
					accionesCliente.reclasificar(nombre, Integer.valueOf(y));
					break;
				case "MUERTE":
					accionesCliente.muere();
					detener = true;
					break;
				case "NUEVA FRUTA":
					x1=b.readLine();
					y=b.readLine();
					accionesCliente.dibujarFruta(new Punto(Integer.valueOf(x1), Integer.valueOf(y)));
					break;
				case "VELOCIDAD FRUTA":
					x1=b.readLine();
					y=b.readLine();
					accionesCliente.dibujarVelocidadFruta(new Punto(Integer.valueOf(x1), Integer.valueOf(y)));
					break;
				default:
					throw new MyProtocolException("Invalid input on client: " + linea);
				}
			}
		}
		
	}
	
	public void setDetener(){
		detener = true;
	}
	

}
