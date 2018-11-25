package utilidades;

import java.util.Random;

import juego.ModeloVibora;

public class Punto {
	@Override
	public String toString() {
		return "Punto [x=" + x + ", y=" + y + "]";
	}

	private static Random rnd = new Random();
	
	private int x;
	private int y;
	
	public Punto (int x, int y){
		this.setX(x);
		this.setY(y);
	}

	public int getX() {
		return this.x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return this.y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public static Punto randomCoord(){
		int x = rnd.nextInt(ModeloVibora.ANCHO-10);
		int y = rnd.nextInt(ModeloVibora.ALTO-10);
		return new Punto(x, y);
	}
}
