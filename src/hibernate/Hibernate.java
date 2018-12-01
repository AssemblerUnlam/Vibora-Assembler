package hibernate;

import java.io.File;
import java.util.List;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import audio.Sonido;


public class Hibernate {

	
	public Session conectarConBase() {
		
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		return session;
	}

	@SuppressWarnings("unchecked")
	public boolean registrar(String usuario, String contrasenia) {

		if (usuario.isEmpty() || contrasenia.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Se deben completar todos los campos", "Error al registrar", 2);
			return false;
		}

		Session session = conectarConBase();

		Query q = session.createQuery("select j.usuario from Jugador j");
		List<String> listaDeJugadores = q.getResultList();
		for (String j : listaDeJugadores)
			if (usuario.equals(j)) {
				JOptionPane.showMessageDialog(null, "El usuario ya existe", "Error al registrar", 2);
				return false;
			}

		Transaction tx = session.beginTransaction();

		Jugador jugador = new Jugador();
		jugador.setUsuario(usuario);
		jugador.setContrasenia(contrasenia);
		jugador.setPuntaje(0);

		try {
			session.save(jugador);
			tx.commit();

			Sonido sonido = new Sonido();
			File a = new File("utilidades/mario-bros_vida.wav");
			sonido.abrir(a);
			sonido.reproducir();
			JOptionPane.showMessageDialog(null, "¡Bienvenido " + usuario + " a Snake The GAME!", "Bienvenido", 1);
			return true;

		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public boolean iniciarSesion(String usuario, String contrasenia) {

		Session session = conectarConBase();

		try {

			Query q = session.createQuery("select j.usuario from Jugador j");
			List<String> listaDeUsuarios = q.getResultList();

			q = session.createQuery("select j.contrasenia from Jugador j");
			List<String> listaDeContrasenias = q.getResultList();

			for (int i = 0; i < listaDeUsuarios.size(); i++) {
				if (usuario.equals(listaDeUsuarios.get(i)) && contrasenia.equals(listaDeContrasenias.get(i)))
					return true;
			}

			JOptionPane.showMessageDialog(null, "Datos no válidos", "Error al inicar sesión", 2);

		} finally {
			session.close();
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public boolean actualizarPuntaje(String usuario, int puntaje) {

		Session session = conectarConBase();
		Transaction tx = session.beginTransaction();

		try {

			Query q = session.createQuery("select j.puntaje from Jugador j where j.usuario = :u ");
			q.setParameter("u", usuario);
			List<Integer> listaDePuntajes = q.getResultList();

			if (puntaje > listaDePuntajes.get(0)) {
				q = session.createQuery("update Jugador set puntaje = :p where usuario = :u");
				q.setParameter("p", puntaje);
				q.setParameter("u", usuario);
				q.executeUpdate();
				tx.commit();
				return true;

			} else {
				tx.rollback();
				return false;
			}

		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public Jugador maximoPuntaje() {

		Session session = conectarConBase();

		try {
			Query q = session.createQuery("select usuario, MAX(puntaje) from Jugador");
			List<Jugador> listaDePuntajes = q.getResultList();
			return listaDePuntajes.get(0);

		} finally {
			session.close();
		}

	}
}
