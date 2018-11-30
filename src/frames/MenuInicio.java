package frames;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import audio.Sonido;
import utilidades.Conexion;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
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
public class MenuInicio extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) throws ClassNotFoundException, SQLException, LineUnavailableException, IOException, UnsupportedAudioFileException {
		cargarInterfaz();
	}

	public static void cargarInterfaz() throws ClassNotFoundException, SQLException, LineUnavailableException, IOException, UnsupportedAudioFileException {
			MenuInicio frame = new MenuInicio();
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			frame.setSize(800, 600);
			frame.setLocation(dim.width / 2 - frame.getWidth() / 2, dim.height / 2 - frame.getHeight() / 2);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws LineUnavailableException 
	 * @throws UnsupportedAudioFileException 
	 * @throws IOException 
	 */
	public MenuInicio() throws ClassNotFoundException, SQLException, LineUnavailableException, IOException, UnsupportedAudioFileException {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 350);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Conexion.crearBase();
		
		Clip sonido = AudioSystem.getClip();
        File a = new File("utilidades/megaman_stage_start.wav");
        sonido.open(AudioSystem.getAudioInputStream(a));
        sonido.start();
        
		JButton btnJugar = new JButton("Jugar");
		btnJugar.setBackground(Color.YELLOW);
		btnJugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					MenuJugar menuJugar = new MenuJugar();
					menuJugar.setVisible(true);
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
				
				Sonido sonido = new Sonido();
				File a = new File("utilidades/megaman-x-select.wav");
				sonido.abrir(a);
				sonido.reproducir();
			}
		});
		btnJugar.setBounds(300, 350, 200, 50);
		contentPane.add(btnJugar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setBackground(Color.YELLOW);
		btnSalir.setForeground(Color.BLACK);
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Sonido sonido = new Sonido();
				File a = new File("utilidades/megaman-x-select.wav");
				sonido.abrir(a);
				sonido.reproducir();
			}
		});
		btnSalir.setBounds(300, 450, 200, 50);
		contentPane.add(btnSalir);
		
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
			BufferedImage fondo = ImageIO.read(new File("utilidades/descarga.jpg"));
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
