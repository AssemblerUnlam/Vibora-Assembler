package frames;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import audio.Sonido;
import utilidades.Conexion;

import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.SQLException;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Color;

@SuppressWarnings("serial")
public class MenuRegistro extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textUsuario;
	private JPasswordField passContraseña;

	/**
	 * Create the dialog.
	 */
	public MenuRegistro() {
		setResizable(false);
		setBackground(Color.WHITE);
		setTitle("Registrarse");
		setBounds(530, 430, 300, 250);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.GREEN);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		textUsuario = new JTextField();
		textUsuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				textUsuario.setText("");
			}
		});
		textUsuario.setToolTipText("");
		textUsuario.addKeyListener(new KeyAdapter() {

			@Override
			public void keyTyped(KeyEvent arg0) {

			}
		});
		textUsuario.setBounds(140, 40, 120, 20);
		contentPanel.add(textUsuario);
		textUsuario.setColumns(10);
		{
			JLabel lblUsuario = new JLabel("Usuario");
			lblUsuario.setBounds(30, 40, 90, 20);
			contentPanel.add(lblUsuario);
		}
		{
			JLabel lblContraseña = new JLabel("Contrase\u00F1a");
			lblContraseña.setBounds(30, 100, 90, 20);
			contentPanel.add(lblContraseña);
		}

		passContraseña = new JPasswordField();
		passContraseña.setBounds(140, 100, 120, 20);
		contentPanel.add(passContraseña);
		{
			JButton okButton = new JButton("Confirmar");
			okButton.setBounds(30, 160, 100, 50);
			contentPanel.add(okButton);
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Conexion conectar = new Conexion();

					try {
						if (conectar.registrar(textUsuario.getText(), String.valueOf(passContraseña.getPassword())))
							dispose();
					} catch (ClassNotFoundException | SQLException e) {
						e.printStackTrace();
					}
				}
			});
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}

		{
			JButton cancelButton = new JButton("Volver");
			cancelButton.setBounds(160, 160, 100, 50);
			contentPanel.add(cancelButton);
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Sonido sonido = new Sonido();
					File a = new File("megaman-x-select.wav");
					sonido.abrir(a);
					sonido.reproducir();
					dispose();
				}
			});
			cancelButton.setActionCommand("Cancel");
		}
	}
}

