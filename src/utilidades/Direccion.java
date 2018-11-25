package utilidades;

public enum Direccion {
	ARRIBA(0,-1), ABAJO(0,1), IZQUIERDA(-1,0),DERECHA(1,0);
	
	public int x;
	public int y;
	
	Direccion(int x,int y){
		this.x = x;
		this.y = y;
	}
	
}
