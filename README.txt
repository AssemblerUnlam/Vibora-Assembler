***** SNAKE *****
(Vibora-Assembler)

Cargar el proyecto en el Workspace de ECLIPSE.

 1) INICIAR SERVIDOR

 	-> Abrir dentro del paquete "servidor" la clase Servidor.java
 	-> Ejecutar la clase mediante el botón RUN.
 	-> En la ventana Servidor SNAKE, presionamos el botón INICIAR.
 	-> El servidor dará inicio y se quedará escuchando conexiones 	   entrantes.

 2) INICIAR JUEGO (*)
 	-> Abrir dentro del paquete "frames" la clase MenuInicio.java
 	-> Ejecutar la clase mediante el botón RUN.

 3) JUEGO
 	-> En la pantalla inicial debemos seleccionar entre Jugar o Salir.
 	-> Seleccionamos Jugar y nos propone Registrarse o Iniciar Sesion.
 	-> Seleccionamor Iniciar Sesion y nos abre la ventana de login.
 	-> Ingresamos el Usuario y Contraseña, y presionamos Jugar.
 	-> Aparece la ventana de conexión con el servidor, con nuestro Usuario
 	   y nos propone una IP Servidor para conectarse.
 	-> Presionamos Conectar y se lanza la ventana del juego.

(*) IMPORTANTE: Antes de INICIAR JUEGO es mandatorio tener el servidor
		escuchando conexiones entrantes (completar paso 1), de otra
 		forma el juego NO podrá comenzar a ejecutarse y lanzará
 		una excepción.