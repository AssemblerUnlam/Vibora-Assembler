***** SNAKE *****
(Vibora-Assembler)

Cargar el proyecto en el Workspace de ECLIPSE.

 1) INICIAR SERVIDOR

 	-> Abrir dentro del paquete "servidor" la clase Servidor.java
 	-> Ejecutar la clase mediante el bot�n RUN.
 	-> En la ventana Servidor SNAKE, presionamos el bot�n INICIAR.
 	-> El servidor dar� inicio y se quedar� escuchando conexiones 	   entrantes.

 2) INICIAR JUEGO (*)
 	-> Abrir dentro del paquete "frames" la clase MenuInicio.java
 	-> Ejecutar la clase mediante el bot�n RUN.

 3) JUEGO
 	-> En la pantalla inicial debemos seleccionar entre Jugar o Salir.
 	-> Seleccionamos Jugar y nos propone Registrarse o Iniciar Sesion.
 	-> Seleccionamor Iniciar Sesion y nos abre la ventana de login.
 	-> Ingresamos el Usuario y Contrase�a, y presionamos Jugar.
 	-> Aparece la ventana de conexi�n con el servidor, con nuestro Usuario
 	   y nos propone una IP Servidor para conectarse.
 	-> Presionamos Conectar y se lanza la ventana del juego.

(*) IMPORTANTE: Antes de INICIAR JUEGO es mandatorio tener el servidor
		escuchando conexiones entrantes (completar paso 1), de otra
 		forma el juego NO podr� comenzar a ejecutarse y lanzar�
 		una excepci�n.