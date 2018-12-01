package cliente;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;

import jugador.Jugador;
import utilidades.Punto;

public interface AccionesCliente {
	
	/**
	 * La clase AccionesCliente tiene como función tener todas las funciones que ejecuta el cliente.
	 */
	public void enviarNombre (String name); // envio nombre al servidor del cliente que se conecta
	default public void nombreOK(){};
	default public void nombreMal(){};
	default public void pedirListaJugadores(){};
	default public void enviarListaJugadores(ArrayList<Jugador> listaJugadores){};
	default public void iniciarVibora(Collection<Punto> cuerpo,String nombre,Color color){}	 
	default public void enviarDireccion(String dir){}
	default void dibujarMovimientoCabeza(Punto cabeza,String nombre,Color color){}
	default void dibujarMovimientoCola(Punto cola){}
	default public void borrarVibora(ArrayList<Punto> cuerpo){}
	default public void muere(){}
	default public void dibujarFruta(Punto a){}
	default public void dibujarVelocidadFruta(Punto punto){}
	default public void reclasificar(String nombre, Integer puntaje){}
}
