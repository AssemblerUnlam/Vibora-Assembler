package frames;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cliente.Cliente;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

@SuppressWarnings("serial")
public class MenuSala extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;

	/**
	 * Create the dialog.
	 */
	public MenuSala() {
		setResizable(false);
		setBackground(Color.WHITE);
		setTitle("SALAS");
		setBounds(530, 430, 300, 250);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.GREEN);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JButton botonUnirse = new JButton("Unirse");
		botonUnirse.setBounds(160, 160, 100, 50);
		contentPanel.add(botonUnirse);
		botonUnirse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		botonUnirse.setEnabled(false);
		
		DefaultListModel model = new DefaultListModel();
		JList lista = new JList();
		lista.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				botonUnirse.setEnabled(true);
			}
		});
		lista.setBounds(30, 40, 230, 94);
		contentPanel.add(lista);
			
		JButton botonCrearSala = new JButton("Crear Sala");
		botonCrearSala.setBounds(30, 160, 100, 50);
		contentPanel.add(botonCrearSala);
		
		textField = new JTextField();
		textField.setBounds(30, 11, 230, 20);
		contentPanel.add(textField);
		textField.setColumns(10);
		botonCrearSala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.addElement(textField.getText());
				lista.setModel(model);
			}
		});
		
	}
}
