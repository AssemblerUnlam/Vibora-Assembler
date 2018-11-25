package entidades;

import java.util.ArrayList;

import juego.ModeloVibora;
import utilidades.Punto;

public class Fruta {
	
	private ArrayList<Punto> listaFrutas;
	int cantidadFrutas, velocidadFruta;
	
	public Fruta(){
		this.listaFrutas=  new ArrayList<Punto>();
		cantidadFrutas=0;
		velocidadFruta=0;
	}
	
	public synchronized void agregarFruta (Punto p){
		if(listaFrutas.contains(p) || (cantidadFrutas>=200)) return;
		listaFrutas.add(p);
		ModeloVibora.notificarNuevaFruta(p);
		cantidadFrutas++;
	}
	
	
	public synchronized void agregarVelocidadFruta (Punto p){
		if(listaFrutas.contains(p) || (velocidadFruta>=4)) return;
		listaFrutas.add(p);
		ModeloVibora.notificarNuevaVelocidadFruta(p);
		velocidadFruta++;
	}
	
	
	public synchronized int eliminarFruta(Punto p, int indice){
		int aBorrar = 0;
		for (Punto punto : listaFrutas) {
			if(punto.getX() == p.getX() && punto.getY() == p.getY()){
				switch(indice){
				case -1:
					System.out.println("Nueva Fruta " + cantidadFrutas);
					cantidadFrutas --; break;
				case -2:
					System.out.println("Nueva Velocidad Fruta " + velocidadFruta);
					velocidadFruta --; break;
				}
				return aBorrar;
			}else{
				aBorrar++;
				
			}
			
		}
		
		return -1;
		
}
	
	public synchronized void borrarFrutaPorIndice(int indice){
		listaFrutas.remove(indice);
	}
	
	public synchronized ArrayList<Punto> getLista(){
		return listaFrutas;
	}
}
