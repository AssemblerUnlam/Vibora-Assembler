package cliente;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import juego.PantallaJuego;
import juego.Juego;
import jugador.Jugador;
import utilidades.Punto;

public class Cliente extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	static int SCALE = 3; //para cambiar el tamaño de la ventana

	private int puerto = 1234;
	private  Socket socket;
	private boolean conectado = false;
	private String direccionConectado;
	private String nombre="" ;
	private ConexionCliente conexion;

	private JPanel comienzoPanel;
	private JPanel pantalla;
	private PantallaJuego pantallaJuego;
	private JTextField nombreField;
	private JLabel nombreLabel ;
	private JButton botonAceptar;
	private JScrollPane listaJugadoresPane;
	private JList<String> listaJugadores;
	
	private JTextField direccionField;
	private JLabel direccionLabel;
	
	
	

	public Cliente(String nombreUsuario) {
		puerto = 1234;
		direccionConectado = "127.0.0.1";
		nombre = nombreUsuario;
		conectado = false;
		iniciar();
	}

	private void conectar(){
		if(conectado) return;
		try {
			socket = new Socket(direccionConectado, puerto);
			conexion = new ConexionCliente(socket, this);
		} catch (IOException e) {
			e.printStackTrace();
			
		}
		conectado = true;
		conexion.start();

		conexion.enviarNombre(nombre);
	}


	private void iniciar(){


		comienzoPanel = new JPanel(new GridBagLayout() );
		pantalla = new JPanel(new BorderLayout() );
		pantallaJuego = new PantallaJuego(Juego.ANCHO,Juego.ALTO,SCALE);

		pantalla.setPreferredSize(new Dimension(Juego.ANCHO*SCALE,Juego.ALTO*SCALE));
		listaJugadores = new JList<>();
		listaJugadoresPane = new JScrollPane(listaJugadores);

		
		nombreField = new JTextField(5);
		botonAceptar = new JButton("Conectar");

		pantallaJuego.addKeyListener( 
				new KeyListener() {

					@Override
					public void keyTyped(KeyEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public void keyReleased(KeyEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public void keyPressed(KeyEvent e) {
						// TODO Auto-generated method stub
						int tecla = e.getKeyCode();
						if(tecla == KeyEvent.VK_LEFT || tecla == KeyEvent.VK_DOWN || tecla == KeyEvent.VK_RIGHT || tecla == KeyEvent.VK_UP || tecla == 'M')
							conexion.enviarDireccion("" + tecla);
					}
				}
				);
		
		direccionField = new JTextField("127.0.0.1");
		direccionField.setPreferredSize(new Dimension(90, 20));

		botonAceptar.addActionListener( l->{
			this.nombre = nombreField.getText();
			setContentPane(pantalla);
			pantallaJuego.requestFocusInWindow();
			validate();
			
			this.direccionConectado = direccionField.getText();
			conectar();


		});
		
		direccionLabel = new JLabel("IP Servidor: ");
		nombreLabel = new JLabel("Nombre de usuario: ");
		
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0; c.gridy = 0;
		c.insets = new Insets(10, 10, 10, 0);
		comienzoPanel.add(nombreLabel,c);
		c.gridx = 1; c.gridy = 0; 
		comienzoPanel.add(nombreField,c);
		
		
		c.gridx = 2; c.gridy = 0;
		comienzoPanel.add(direccionLabel,c);
		c.gridx =3;
		comienzoPanel.add(direccionField,c);
		
		
		c.gridy = 3; c.gridx = 0; c.gridwidth = 4;
		comienzoPanel.add(botonAceptar,c);


		listaJugadoresPane.setPreferredSize(new Dimension(150, 50));
		listaJugadoresPane.setFocusable(false);
		listaJugadores.setFocusable(false);
		pantalla.add(pantallaJuego,BorderLayout.CENTER);
		pantalla.add(listaJugadoresPane,BorderLayout.EAST);

		nombreField.setText(this.nombre);
		nombreField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					botonAceptar.doClick();
				}
			}
		});
		setResizable(false);
		setPreferredSize(new Dimension(Juego.ANCHO*SCALE+150, Juego.ALTO*SCALE));
		setTitle("Snake");
		setContentPane(comienzoPanel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null); //centre la fenetre
		setVisible(true);
	}

	public String getNombre(){
		return this.nombre;
	}
	public void setNombre(String name){
		this.nombre = name;
	}

	public void llenarListaJugadores( ArrayList<Jugador> puntajeJugadores){
		Collection<String> salida = new ArrayList<>();
		for(Jugador entry : puntajeJugadores){
			salida.add(entry.getNombre() +"  "+ entry.getPuntaje());
		}
		
		listaJugadores.setListData(salida.toArray(new String[0]));
		
	}
	
	public void aceptarNombre() {
		conexion.pedirListaJugadores();

	}

	public void dibujarCuerpo(Collection<Punto> cuerpo,String nombre) {
		if(nombre.equals(this.nombre)){
			pantallaJuego.dibujarMisPuntos(cuerpo);
		}else{
			pantallaJuego.dibujarPuntos(cuerpo);
		}
	}

	public void dibujarCabeza(Punto cabeza,String nombre){
		if(nombre.equals(this.nombre)){
			pantallaJuego.dibujarMiCabeza(cabeza);
		}else{
			pantallaJuego.dibujarCabeza(cabeza);
		}
	}

	public void dibujarCola(Punto cola){
		pantallaJuego.dibujarCola(cola);
	}

	public void borrarCuerpo(ArrayList<Punto> bodyP) {
		pantallaJuego.borrarCuerpo(bodyP);

	}
	
	public void dibujarFruta(Punto a){
		pantallaJuego.dibujarFruta(a);
	}

	public void dibujarVelocidadFruta(Punto a){
		pantallaJuego.dibujarVelocidadFruta(a);
	}
	
	public void reiniciar(){
		
		conectado = false;
		iniciar();
		setContentPane(comienzoPanel);
	}
}