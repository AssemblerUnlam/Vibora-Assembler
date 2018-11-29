package test;

import org.junit.Test;

import entidades.Vibora;
import junit.framework.Assert;
import utilidades.Direccion;

public class Tests {

	@Test
	public void inicializaVibora3Puntos() {
		Vibora vibora = new Vibora();
		Assert.assertEquals(3, vibora.getCuerpo().size());
	}
	
	@Test
	public void comienzaSentidoDerecha() {
		Vibora vibora = new Vibora();
		Assert.assertEquals("DERECHA", vibora.getDireccion().toString());;
	}
	
	public void seMueveAIzquieda() {
		Vibora vibora = new Vibora();
		vibora.setDireccion(Direccion.IZQUIERDA);
		Assert.assertEquals("IZQUIERDA", vibora.getDireccion().toString());;
	}
	
	public void seMueveAArriba() {
		Vibora vibora = new Vibora();
		vibora.setDireccion(Direccion.ARRIBA);
		Assert.assertEquals("ARRIBA", vibora.getDireccion().toString());;
	}
	
	public void seMueveAAbajo() {
		Vibora vibora = new Vibora();
		vibora.setDireccion(Direccion.ABAJO);
		Assert.assertEquals("ABAJO", vibora.getDireccion().toString());;
	}
	
	public void seMueveADerecha() {
		Vibora vibora = new Vibora();
		vibora.setDireccion(Direccion.ABAJO);
		vibora.setDireccion(Direccion.DERECHA);
		Assert.assertEquals("DERECHA", vibora.getDireccion().toString());;
	}
}
