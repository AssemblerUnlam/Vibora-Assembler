package jugador;

import java.awt.Color;

public class Jugador implements Comparable<Jugador> {
	/**
	 * La clase Jugador contiene metodos y atributos de cada jugador.
	 */
	Integer puntaje = 0 ;
	String nombre ="";
	Color color;
	
	public Jugador(String nombre, Integer puntaje, Color color) {
		this.nombre = nombre;
		this.puntaje = puntaje;
		this.color = color;
	}
	
	public void incrementarPuntaje(Integer inc){
		this.puntaje +=inc;
	}
	
	
	public int getPuntaje() {
		return puntaje;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Jugador otro = (Jugador) obj;
		if (nombre == null) {
			if (otro.nombre != null)
				return false;
		} else if (!nombre.equals(otro.nombre))
			return false;
		return true;
	}

	@Override
	public int compareTo(Jugador o) {
		int compararPuntaje = o.puntaje;
		int tmp = compararPuntaje - this.puntaje;
		if(tmp == 0) return 1;
		return tmp;
	}
	
	public Color getColor() {
		return this.color;
	}
}
