package cliente;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;


public class SalidaCliente  implements AccionesCliente{
	
	PrintWriter os;
	
	public SalidaCliente(OutputStream salida) throws IOException{
		this.os = new PrintWriter(salida,true);
	}

	@Override
	public void enviarNombre(String nombre) {
		os.println("NOMBRE");
		os.println(nombre);
		
	}

	@Override
	public void pedirListaJugadores() {
		os.println("JUGADORES");
	}

	@Override
	public void enviarDireccion(String dir) {
		os.println("TECLA");
		os.println(dir);
	}

	

	
	
	

}
