package jugador;

public class Jugador implements Comparable<Jugador> {
	Integer puntaje = 0 ;
	String nombre ="";
	
	public Jugador(String nombre, Integer puntaje) {
		this.nombre = nombre;
		this.puntaje = puntaje;
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
}
