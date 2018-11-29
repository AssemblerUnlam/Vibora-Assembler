package frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import audio.Sonido;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class MenuJugar extends JFrame {

	private JPanel contentPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuJugar frame = new MenuJugar();
					Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
					frame.setSize(800, 600);
					frame.setLocation(dim.width / 2 - frame.getWidth() / 2, dim.height / 2 - frame.getHeight() / 2);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public MenuJugar() throws ClassNotFoundException, SQLException {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 800, 600);
		setLocation(287, 87);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnRegistro = new JButton("Registrarse");
		btnRegistro.setBackground(Color.YELLOW);
		btnRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MenuRegistro ventanaRegistro = new MenuRegistro();
				ventanaRegistro.setVisible(true);
				Sonido sonido = new Sonido();
				File a = new File("megaman-x-select.wav");
				sonido.abrir(a);
				sonido.reproducir();
			}
		});
		btnRegistro.setBounds(300, 350, 200, 50);
		contentPane.add(btnRegistro);
		
		JButton btnLogin = new JButton("Iniciar Sesi\u00F3n");
		btnLogin.setBackground(Color.YELLOW);
		btnLogin.setForeground(Color.BLACK);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MenuLogin ventanaLogin = new MenuLogin();
				ventanaLogin.setVisible(true);
				Sonido sonido = new Sonido();
				File a = new File("megaman-x-select.wav");
				sonido.abrir(a);
				sonido.reproducir();
			}
		});
		btnLogin.setBounds(300, 450, 200, 50);
		contentPane.add(btnLogin);
		
		JLabel lblSnakeTheGame = new JLabel("SNAKE THE GAME");
		lblSnakeTheGame.setBackground(Color.GREEN);
		lblSnakeTheGame.setFont(new Font("Comic Sans MS", Font.ITALIC, 48));
		lblSnakeTheGame.setForeground(Color.RED);
		lblSnakeTheGame.setBounds(180, 200, 600, 100);
		contentPane.add(lblSnakeTheGame);
	}
	
	public void paint(Graphics gra) {
		super.paint(gra);
		try {
			BufferedImage fondo = ImageIO.read(new File("descarga.jpg"));
			gra.drawImage(fondo, 265, 50, this);
		} catch (IOException e) {
			e.printStackTrace();
		}
		gra.setColor(Color.GREEN);
		int y = 30;
		for(int i = 0; i < 15; i++) {
			gra.fillOval(20, y, 25, 25);
			y += 45;
		}
		y = 60;
		for(int i = 0; i < 18; i++) {
			gra.fillOval(y, 30, 25, 25);
			y += 40;
		}
		y = 30;
		for(int i = 0; i < 18; i++) {
			gra.fillOval(740, y, 25, 25);
			y += 45;
		}
		
		y = 60;
		for(int i = 0; i < 18; i++) {
			gra.fillOval(y, 570, 25, 25);
			y += 40;
		}
	}
	
}
