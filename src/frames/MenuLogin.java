package frames;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cliente.Cliente;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

@SuppressWarnings("serial")
public class MenuLogin extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textUsuario;
	private JPasswordField passContraseña;

	/**
	 * Create the dialog.
	 */
	public MenuLogin() {
		setResizable(false);
		setBackground(Color.WHITE);
		setTitle("Inicio de Sesi\u00F3n");
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
			
			{
				JButton okButton = new JButton("Jugar");
				okButton.setBounds(30, 160, 100, 50);
				contentPanel.add(okButton);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Cliente cliente = new Cliente(textUsuario.getText());
						//Conexion conectar = new Conexion();
						
						//if(conectar.iniciarSesion(textUsuario.getText(), String.valueOf(passContraseña.getPassword())))
							//dispose();
					}
				});
			}
			
			
			{
				JButton cancelButton = new JButton("Volver");
				cancelButton.setBounds(160, 160, 100, 50);
				contentPanel.add(cancelButton);
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
			}
		}
	}
}
