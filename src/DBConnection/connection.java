package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connection extends Configs{

	Connection con;
	
	public Connection getConnection() throws ClassNotFoundException, SQLException
	{
	
		/* Causa error:
		 * Loading class `com.mysql.jdbc.Driver'. This is deprecated.
		 * The new driver class is `com.mysql.cj.jdbc.Driver'.
		 * The driver is automatically registered via the SPI
		 * and manual loading of the driver class is generally unnecessary.

		Class.forName("com.mysql.jdbc.Driver");
		*/
		
		// Código para agregar y evitar error al conectarse a la base de datos: +"?serverTimezone=UTC" 
		String str = "jdbc:mysql://"+Configs.dbhost+":"+Configs.dbport+"/"+Configs.dbname+"?serverTimezone=UTC";
		
		con = DriverManager.getConnection(str,Configs.dbuser,Configs.dbpassword);
		
		return con;
	}
}