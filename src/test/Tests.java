package test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import entidades.Vibora;
import utilidades.Direccion;

public class Tests {

	Vibora vibora;
	
	@Before 
	public void inicializar() {
	       vibora = new Vibora();
	    }
	
	@Test
	public void inicializaVibora3Puntos() {
		Assert.assertEquals(3, vibora.getCuerpo().size());
	}
	
	@Test
	public void comienzaSentidoDerecha() {
		Assert.assertEquals("DERECHA", vibora.getDireccion().toString());;
	}
	
	public void seMueveAIzquieda() {
		vibora.setDireccion(Direccion.IZQUIERDA);
		Assert.assertEquals("IZQUIERDA", vibora.getDireccion().toString());;
	}
	
	public void seMueveAArriba() {
		vibora.setDireccion(Direccion.ARRIBA);
		Assert.assertEquals("ARRIBA", vibora.getDireccion().toString());;
	}
	
	public void seMueveAAbajo() {
		vibora.setDireccion(Direccion.ABAJO);
		Assert.assertEquals("ABAJO", vibora.getDireccion().toString());;
	}
	
	public void seMueveADerecha() {
		vibora.setDireccion(Direccion.ABAJO);
		vibora.setDireccion(Direccion.DERECHA);
		Assert.assertEquals("DERECHA", vibora.getDireccion().toString());;
	}
}
