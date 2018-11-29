package entidades;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import utilidades.Direccion;
import utilidades.Punto;



public class Vibora {

	private final int TAMANIO_INICIAL = 3;
	
	private List<Punto> cuerpo = new ArrayList<>();
	private Direccion direccion;
	private boolean viva = true;
	
	private boolean puedeMoverse = true;
		
	
	public Vibora(){
		direccion = Direccion.DERECHA;
		Punto comienzo;
		comienzo = Punto.randomCoord();
		
		for (int i = 0; i < TAMANIO_INICIAL; i++) {
			Punto p = new Punto(comienzo.getX()+i, comienzo.getY());
			cuerpo.add(p);
		}
		
	}
	
	
	public Direccion getDireccion() {
		return direccion;
	}
	
	public void setDireccion(Direccion direccion){
		if(!puedeMoverse) return;
		if(this.direccion == Direccion.DERECHA && direccion == Direccion.IZQUIERDA){
			return;
		}if(this.direccion == Direccion.IZQUIERDA && direccion == Direccion.DERECHA){
			return;
		}
		if(this.direccion == Direccion.ABAJO && direccion == Direccion.ARRIBA){
			return;
		}if(this.direccion == Direccion.ARRIBA && direccion == Direccion.ABAJO){
			return;
		}
		puedeMoverse = false;
		
		this.direccion = direccion;
		
	}
	
	public Collection<Punto> getCuerpo(){
		return cuerpo;
	}
	
	public Punto getCabeza(){
		return this.cuerpo.get(cuerpo.size()-1);
	}
	
	public Punto getCola(){
		return this.cuerpo.get(0);
	}
	
	public void mover() {
		Punto head = getCabeza();
		Punto next = new Punto(head.getX() + direccion.x, head.getY() + direccion.y);
		cuerpo.add(next);
		puedeMoverse = true;
		
	}
	
	public Punto getNuevaCabeza(){
		return new Punto(getCabeza().getX()+direccion.x,getCabeza().getY()+direccion.y);
	}
	
	public void borrarCola(){
		cuerpo.remove(0);
	}


	public void muere() {
		viva = false;
	}
	
	public boolean isViva() {
		return viva;
	}
	
	public void setCuerpo(List<Punto> cuerpo) {
		this.cuerpo = cuerpo;
	}
	
}
