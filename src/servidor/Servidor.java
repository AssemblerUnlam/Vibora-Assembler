package servidor;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import juego.MensajesAJugador;
import juego.ModeloVibora;
import jugador.JugadorLogueado;
import jugador.ManejoJugador;


public class Servidor extends Thread {
	private int puerto = 1234;
	private static ServerSocket servidor;
	private boolean detener = false;
	private JugadorLogueado logger = null;
	private static Thread server;
	public static JTextArea log;
	private final static int ANCHO = 700;
	private final static int ALTO = 640;
	private final static int ALTO_LOG = 520;
	private final static int ANCHO_LOG = ANCHO - 25;
	
	public void run() {
		try{
			logger = new MensajesAJugador();
			logger.mensajeSistema ("Servidor online");		
			log.append("Iniciando el servidor..." + System.lineSeparator());
			servidor = new ServerSocket(puerto);
			log.append("Esperando conexiones..." + System.lineSeparator());
			servidor.setSoTimeout(1000);
			ModeloVibora.iniciarMapa();
			while(!detener){
				try{
					Socket socketJugador = servidor.accept();
					log.append("el cliente : " + socketJugador.getInetAddress().getHostAddress() + " se ha conectado" + System.lineSeparator());
					logger.jugadorConectado(socketJugador.toString());
					new Thread(new ManejoJugador(socketJugador, logger)).start();
				}catch(SocketTimeoutException ex){
				}
			}
		} catch (IOException e) {
		}
		
	}
	
	public synchronized void finalizar(){
		this.detener=true;
	}
	
	public static void main(String[] args) {
		cargarInterfaz();
	}

	public static void cargarInterfaz() {
		JFrame ventana = new JFrame("Servidor VIBORA");
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setSize(ANCHO, ALTO);
		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);
		ventana.setLayout(null);
		ventana.setBackground(Color.BLACK);
		JLabel titulo = new JLabel("SERVIDOR VIBORA...");
		titulo.setFont(new Font("Courier New", Font.BOLD, 16));
		titulo.setBounds(10, 0, 200, 30);
		ventana.add(titulo);

		log = new JTextArea();
		log.setEditable(false);
		log.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		JScrollPane scroll = new JScrollPane(log, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setBounds(10, 40, ANCHO_LOG, ALTO_LOG);
		ventana.add(scroll);

		final JButton botonIniciar = new JButton();
		final JButton botonDetener = new JButton();
		botonIniciar.setText("Iniciar");
		botonIniciar.setBounds(220, ALTO - 70, 100, 30);
		botonIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				server = new Thread(new Servidor());
				server.start();
				botonIniciar.setEnabled(false);
				botonDetener.setEnabled(true);
			}
		});

		ventana.add(botonIniciar);

		botonDetener.setText("Detener");
		botonDetener.setBounds(360, ALTO - 70, 100, 30);
		botonDetener.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					server.stop();
					servidor.close();
					log.append("El servidor se ha detenido." + System.lineSeparator());
				} catch (IOException e1) {
					log.append("Fallo al intentar detener el servidor." + System.lineSeparator());
				}
				botonDetener.setEnabled(false);
				botonIniciar.setEnabled(true);
			}
		});
		botonDetener.setEnabled(false);
		ventana.add(botonDetener);

		ventana.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		ventana.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				if (servidor != null) {
					try {
						server.stop();
						servidor.close();
						log.append("El servidor se ha detenido." + System.lineSeparator());
					} catch (IOException e) {
						log.append("Fallo al intentar detener el servidor." + System.lineSeparator());
						System.exit(1);
					}
				}
				System.exit(0);
			}
		});

		ventana.setVisible(true);
		
	}
	
	public static JTextArea getLog() {
		return log;
	}
	
	public static void setLog(final JTextArea log) {
		Servidor.log = log;
	}
}
