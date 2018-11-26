package juego;

public class Tickle extends Thread {
	final private int base = 75;
	private int fruta = 0, velocidadFruta=0, duracion=0;
	private int tiempo = base;
	
	@Override
	public void run() {
		while(true){
			try {
				Thread.sleep(tiempo);
				Juego.moverVibora();
				if(tiempo != base ){
					if (duracion == 100) {
						duracion = 0;
						tiempo = base;
					}
					duracion ++;
				}
				if(fruta >= 5){
					fruta = 0;
					Juego.agregarFruta();
				}
				if(velocidadFruta >= 200){
					velocidadFruta = 0;
					Juego.agregarVelocidadFruta();
				}
				
				fruta++;
				velocidadFruta++;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void setTiempo(int nuevoTiempo){
		this.tiempo = nuevoTiempo;
	}
	
}
