package cliente;

import java.awt.Color;
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
		Color color;
		
		try(BufferedReader b = new BufferedReader(new InputStreamReader(entrada))){
			while(!detener){
				String linea = new String("");
				linea = b.readLine();
				switch(linea){
				case "NOMBRE OK":
					accionesCliente.nombreOK();
					break;
				case "NOMBRE MAL":
					accionesCliente.nombreMal();
					break;
				case "LISTA JUGADORES":
					listaJugadores = new ArrayList<>();
//					String x1;
					while(!(x = b.readLine()).equals(".")){
						puntaje = Integer.valueOf(b.readLine());
						color = new Color(Integer.parseInt(b.readLine()));
						listaJugadores.add(new Jugador(x,puntaje,color));
					}
					accionesCliente.enviarListaJugadores(listaJugadores);
					break;
				case "NUEVA VIBORA":
					cuerpo = new ArrayList<>();
					nombre = b.readLine();
					color = new Color(Integer.parseInt(b.readLine()));
					while(!(x = b.readLine()).equals(".")){
						y = b.readLine();
						
						Punto p;
						if(x == null || x.isEmpty())
							p = new Punto(0,0);
						else
							p = new Punto(Integer.valueOf(x), Integer.valueOf(y));
						
						cuerpo.add(p);
						
					}
					accionesCliente.iniciarVibora(cuerpo,nombre,color);
					break;
				case "INICIAR VIBORA":
					cuerpo = new ArrayList<>();
					nombre = b.readLine();
					color = new Color(Integer.parseInt(b.readLine()));
					while(!(x = b.readLine()).equals(".")){
						y = b.readLine();

						Punto p;
						if(x == null || x.isEmpty())
							p = new Punto(0,0);
						else
							p = new Punto(Integer.valueOf(x), Integer.valueOf(y));

						cuerpo.add(p);
					}
					accionesCliente.iniciarVibora(cuerpo,nombre,color);
					break;
				case "MOVER CABEZA":
					nombre = b.readLine();
					x=b.readLine();
					y=b.readLine();
					puntaje = Integer.valueOf(b.readLine());
					String col = b.readLine();
					color = new Color(Integer.parseInt(col));
					accionesCliente.dibujarMovimientoCabeza(new Punto(Integer.valueOf(x), Integer.valueOf(y)),nombre,color);
					if(puntaje != 0)  accionesCliente.reclasificar(nombre,puntaje); 
					break;
				case "MOVER COLA":
					x=b.readLine();
					y=b.readLine();
					accionesCliente.dibujarMovimientoCola(new Punto(Integer.valueOf(x), Integer.valueOf(y)));
					break;
				case "LIMPIAR":
					cuerpo = new ArrayList<>();
					while(!(x = b.readLine()).equals(".")){
						y = b.readLine();
						
						Punto p = new Punto(Integer.valueOf(x), Integer.valueOf(y));
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
					x=b.readLine();
					y=b.readLine();
					accionesCliente.dibujarFruta(new Punto(Integer.valueOf(x), Integer.valueOf(y)));
					break;
				case "VELOCIDAD FRUTA":
					x=b.readLine();
					y=b.readLine();
					accionesCliente.dibujarVelocidadFruta(new Punto(Integer.valueOf(x), Integer.valueOf(y)));
					break;
				default:
					throw new MyProtocolException("Entrada inválida en el cliente: " + linea);
				}
			}
		}
		
	}
	
	public void setDetener(){
		detener = true;
	}
	

}
