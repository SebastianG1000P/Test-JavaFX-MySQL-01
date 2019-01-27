Ejercicio para probar uso de interfaz gráfica y acceso a base de datos con JavaFX y JDBC.

Software utilizado:

Java 8
JavaFX
Scene Builder 8
MySQL Connector 8.0.13
MySQL Workbench 8.0

Fuente: https://www.udemy.com/learn-javafx-tutorials-from-beginners-level/


----------

V1.0

	Primera versión funcional.
	
	- Hecho con los controles estándar de JavaFX, en vez de los controles de JFoenix.
	- Código corregido para poder conectarse a la base de datos.
		- Línea eliminada por ser incompatible con la versión actual de JDBC y dar error:
				Class.forName("com.mysql.jdbc.Driver");
		- Código agregado a la dirección de conexión a la base de datos para eliminar problema con la zona horaria:
				+"?serverTimezone=UTC"