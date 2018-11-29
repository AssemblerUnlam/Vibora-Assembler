package test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import entidades.Vibora;
import juego.Juego;
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
	
}
