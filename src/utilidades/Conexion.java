package utilidades;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

import audio.Sonido;

public class Conexion {

	private static Statement consulta;
	private static Connection conexion;
	private static ResultSet resultado;
	private static PreparedStatement ps;

	public static void crearBase() throws ClassNotFoundException, SQLException {

		conectar();
		consulta = conexion.createStatement();

		consulta.executeUpdate("CREATE TABLE IF NOT EXISTS JUGADOR (" + "USUARIO STRING PRIMARY KEY NOT NULL, "
				+ "CONTRASEÑA STRING NOT NULL, " + "PUNTAJE INTEGER)");
		desconectar();
	}

	public static void conectar() throws ClassNotFoundException {
		Class.forName("org.sqlite.JDBC");
		try {
			conexion = DriverManager.getConnection("jdbc:sqlite:snake.db");

		} catch (SQLException ex) {
			System.err.println("No se ha podido conectar a la base de datos\n" + ex.getMessage());
		}
	}

	public static void desconectar() {
		try {
			conexion.close();
		} catch (SQLException ex) {
			System.err.println("No se ha podido desconectar de la base de datos\n" + ex.getMessage());
		}
	}

	public boolean registrar(String usuario, String contraseña) throws SQLException, ClassNotFoundException {
		
		if (usuario.isEmpty() || contraseña.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Se deben completar todos los campos", "Error al registrar", 2);
			return false;
		}
		
		try {
			conectar();
			ps = conexion.prepareStatement("INSERT INTO JUGADOR (USUARIO, CONTRASEÑA, PUNTAJE) VALUES (?, ?, ?)");
			ps.setString(1, usuario);
			ps.setString(2, contraseña);
			ps.setInt(3, 0);
			ps.execute();
			desconectar();
			Sonido sonido = new Sonido();
			File a = new File("utilidades/mario-bros_vida.wav");
			sonido.abrir(a);
			sonido.reproducir();
			JOptionPane.showMessageDialog(null, "¡Bienvenido " + usuario + " a Snake The GAME!", "Bienvenido", 1);
			return true;
		} catch (SQLException e) {

			JOptionPane.showMessageDialog(null, "El usuario ya existe", "Error al registrar", 2);
		}
		return false;

	}

	public boolean iniciarSesion(String usuario, String contraseña) {

		try {
			conectar();
			ps = conexion.prepareStatement("SELECT USUARIO, CONTRASEÑA FROM JUGADOR");
			resultado = ps.executeQuery();
			while (resultado.next()) {
				if (resultado.getString("USUARIO").equals(usuario)
						&& resultado.getString("CONTRASEÑA").equals(contraseña)) {
					resultado.close();
					desconectar();
					return true;
				}
			}
			desconectar();
			JOptionPane.showMessageDialog(null, "Datos no válidos", "Error al inicar sesión", 2);

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return false;
	}

	public static boolean actualizarPuntaje(String usuario, int puntaje) throws ClassNotFoundException {
		try {
			conectar();
			ps = conexion.prepareStatement("SELECT PUNTAJE FROM JUGADOR WHERE USUARIO = ?");
			ps.setString(1, usuario);
			resultado = ps.executeQuery();
			if (puntaje > resultado.getInt(1)) {
				ps = conexion.prepareStatement("UPDATE JUGADOR SET PUNTAJE = ? WHERE USUARIO = ?");
				ps.setInt(1, puntaje);
				ps.setString(2, usuario);
				ps.execute();
			}
			resultado.close();
			desconectar();
			return true;

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}

		return false;
	}

}
