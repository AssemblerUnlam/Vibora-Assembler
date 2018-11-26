package juego;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

import utilidades.Punto;


public class PantallaJuego extends JComponent {
		
	
	private static final long serialVersionUID = 1L;

	BufferedImage imagen;

	private int scale;
	
	BufferedImage fondo; 
	
	private int factor = 7; //grosor del pixel
	int ancho;
	int alto;
	
	int centerX = 0;
	int centerY = 0;
	
	int rangeX ; //tamaño de la ventana
	int rangeY ;
	
	int exScale;
	
	
	public PantallaJuego(int ancho,int alto,int scale) {
		
		
		this.rangeX = ancho*scale;
		this.rangeY = alto*scale;
		
		this.ancho = ancho*factor;
		this.alto = alto*factor;
		
		
		imagen = new BufferedImage(this.ancho, this.alto, BufferedImage.TYPE_INT_ARGB);
		setPreferredSize(new Dimension(ancho, alto));
		
		
		
		
		try {
			fondo = ImageIO.read(new File("src/fond.jpg"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.exScale = scale;
		
		this.scale = factor;
		
	}
	
	
	
	public void dibujarPuntos(Collection<Punto> punto){
		Graphics g = imagen.getGraphics();
		g.setColor(Color.RED);
		for (Punto p: punto) {
			g.fillRect(p.getX()*scale, p.getY()*scale, scale, scale);
			
		}
		repaint();
		
	}
	public void dibujarMisPuntos(Collection<Punto> punto){
		Graphics g = imagen.getGraphics();
		g.setColor(Color.WHITE);
		for (Punto p: punto) {
			g.fillRect(p.getX()*scale, p.getY()*scale, scale, scale);
			centerX = p.getX()*scale;
			centerY = p.getY()*scale;
		}
		repaint();
		
	}
	
	public void dibujarCabeza(Punto p){
		Graphics g = imagen.getGraphics();
		g.setColor(Color.RED);
		g.fillRect(p.getX()*scale, p.getY()*scale, scale, scale);
		
		repaint();
	}
	public void dibujarMiCabeza(Punto p){
		Graphics g = imagen.getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(p.getX()*scale, p.getY()*scale, scale, scale);
		
		centerX = p.getX()*scale;
		centerY = p.getY()*scale;
		repaint();
	}
	
	public void dibujarCola(Punto cola){
		Graphics g = imagen.getGraphics();
		
		int borrar = fondo.getRGB(cola.getX()*scale,cola.getY()*scale);
		
		g.setColor(new Color(borrar));
		
		g.fillRect(cola.getX()*scale, cola.getY()*scale, scale, scale);
		repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		int offsetX = -centerX+rangeX/2;
		int offsetY = -centerY+rangeY/2;
		
		if(centerX < rangeX/2){ //limite
			offsetX = 0;
		}else if(centerX > ancho-rangeX/2 ){
			offsetX =  -ancho+rangeX;

		}
		if(centerY < rangeY/2){
			offsetY = 0;
		}else if(centerY > alto-rangeY/2){
			offsetY = -alto+rangeY;					
		}
		
		
		
		g.drawImage(fondo, offsetX  , offsetY,null);
		g.drawImage(imagen, offsetX , offsetY, null);
	}


	public void borrarCuerpo(ArrayList<Punto> cuerpo) {
		Graphics g = imagen.getGraphics();

		for (Punto p: cuerpo) {
			int erase = fondo.getRGB(p.getX()*scale,p.getY()*scale);
			
			g.setColor(new Color(erase));
			g.fillRect(p.getX()*scale, p.getY()*scale, scale, scale);
		}
		repaint();
	}
	
	public void dibujarFruta(Punto p){
		Graphics g = imagen.getGraphics();
		g.setColor(Color.GREEN);
		g.fillRect(p.getX()*scale, p.getY()*scale, scale, scale);
		repaint();
	}
	

	public void dibujarVelocidadFruta(Punto p){
		Graphics g = imagen.getGraphics();
		g.setColor(Color.BLUE);
		g.fillRect(p.getX()*scale, p.getY()*scale, scale, scale);
		repaint();
	}
}
