package test;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import entidades.Fruta;
import entidades.Vibora;
import juego.Juego;
import utilidades.Direccion;
import utilidades.Punto;

public class Tests {

	Vibora vibora, vibora2;
	Fruta fruta;
	ArrayList<Punto> cuerpo, cuerpo2;
	
	@Before 
	public void inicializar() {
	       vibora = new Vibora();
	       vibora2 = new Vibora();
	       fruta = new Fruta();
	       cuerpo = new ArrayList<Punto>();
	       cuerpo2 = new ArrayList<Punto>();
	       setViboras(cuerpo, cuerpo2);
	    }
	
	@Test
	public void inicializaVibora3Puntos() {
		Assert.assertEquals(3, vibora.getCuerpo().size());
	}
	
	@Test
	public void comienzaSentidoDerecha() {
		Assert.assertEquals("DERECHA", vibora.getDireccion().toString());;
	}
	
	@Test
	public void mantieneElsentidoAlMoverElOpuestoDerIzq() {
		vibora.setDireccion(Direccion.DERECHA);
		vibora.mover();
		vibora.setDireccion(Direccion.IZQUIERDA);
		Assert.assertEquals("DERECHA", vibora.getDireccion().toString());;
	}
	
	@Test
	public void mantieneElsentidoAlMoverElOpuestoIzqDer() {
		vibora.setDireccion(Direccion.ARRIBA);
		vibora.mover();
		vibora.setDireccion(Direccion.IZQUIERDA);
		vibora.mover();
		vibora.setDireccion(Direccion.DERECHA);
		Assert.assertEquals("IZQUIERDA", vibora.getDireccion().toString());;
	}
	
	@Test
	public void seMueveAArriba() {
		vibora.setDireccion(Direccion.ARRIBA);
		Assert.assertEquals("ARRIBA", vibora.getDireccion().toString());;
	}
	
	
	@Test
	public void mantieneElsentidoAlMoverElOpuestoAbajoArriba() {
		vibora.setDireccion(Direccion.ARRIBA);
		vibora.mover();
		vibora.setDireccion(Direccion.ABAJO);
		Assert.assertEquals("ARRIBA", vibora.getDireccion().toString());;
	}
	
	
	@Test
	public void mantieneElsentidoAlMoverElOpuestoArribaAbajo() {
		vibora.setDireccion(Direccion.ABAJO);
		vibora.mover();
		vibora.setDireccion(Direccion.ARRIBA);
		Assert.assertEquals("ABAJO", vibora.getDireccion().toString());;
	}
	
	@Test
	public void seMueveAAbajo() {
		vibora.setDireccion(Direccion.ABAJO);
		Assert.assertEquals("ABAJO", vibora.getDireccion().toString());;
	}
	
	@Test
	public void seMueveADerecha() {
		vibora.setDireccion(Direccion.ABAJO);
		vibora.mover();
		vibora.setDireccion(Direccion.DERECHA);
		Assert.assertEquals("DERECHA", vibora.getDireccion().toString());
	}
	
	@Test
	public void mantieneSuTamañoAlMoverse() {
		Assert.assertEquals(3, vibora.getCuerpo().size());
		if(Juego.chequearColision(vibora) == 0) {
		vibora.mover();
		vibora.borrarCola();
		Assert.assertEquals(3, vibora.getCuerpo().size());
		}
	}
	
	@Test
	public void aumentaSuTamañoAlComerFruta() {
		fruta.agregarFruta(new Punto(1,2));  //La vibora se mueve Derecha, cabeza(0,2)
		vibora.setCuerpo(cuerpo);
		 Assert.assertEquals(3, vibora.getCuerpo().size());
		if(Juego.chequearColision(vibora) == -1) {
		  vibora.mover();
		  Assert.assertEquals(4, vibora.getCuerpo().size());
		}
	}
	
	@Test
	public void muereAlChocarConMapa() {
		Assert.assertEquals(true, vibora.isViva());
		if(Juego.chequearColision(vibora) >= 1) {
			vibora.muere();
			Assert.assertEquals(false, vibora.isViva());
		}	
	}
	
	@Test
	public void vibora1ChocaConVibora2() {
		
		vibora.setCuerpo(cuerpo);
		vibora2.setCuerpo(cuerpo2);
		Assert.assertEquals(true, vibora.isViva());
		Assert.assertEquals(true, vibora2.isViva());
		Juego.aparicionVibora(cuerpo2);
		if(Juego.chequearColision(vibora) == 1) {
			vibora.muere();
			Assert.assertEquals(false, vibora.isViva());
			Assert.assertEquals(true, vibora2.isViva());
		}	
	}
	
	@Test
	public void vibora2ChocaConVibora1() {
		
		vibora.setCuerpo(cuerpo2);
		vibora2.setCuerpo(cuerpo);
		Assert.assertEquals(true, vibora.isViva());
		Assert.assertEquals(true, vibora2.isViva());
		Juego.aparicionVibora(cuerpo2);
		if(Juego.chequearColision(vibora2) == 1) {
			vibora2.muere();
			Assert.assertEquals(true, vibora.isViva());
			Assert.assertEquals(false, vibora2.isViva());
		}	
	}
	
	private void setViboras(ArrayList<Punto> cuerpo, ArrayList<Punto> cuerpo2) {
	       cuerpo.add(new Punto(0,0));
		   cuerpo.add(new Punto(0,1));
		   cuerpo.add(new Punto(0,2));
		   cuerpo2.add(new Punto(1,1));
		   cuerpo2.add(new Punto(1,2));
		   cuerpo2.add(new Punto(1,3));
	}
}
