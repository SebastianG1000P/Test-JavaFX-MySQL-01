package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import AlertMessage.Message;
import DBConnection.connection;

public class LoginController {

    @FXML private AnchorPane anPane;
    @FXML private TextField username;
    @FXML private TextField password;
    @FXML private Button register;
    @FXML private Button login;
    
    Connection con;

    connection conClass = new connection();
    
    private PreparedStatement pst;

    @FXML
    void createLogin(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {

    	con = conClass.getConnection();
    	Message msg = new Message();

    	/* C�digo para mostrar la conexi�n.
    	 * Se us� para probar que la conexi�n funcionaba
    	 * antes de editar la base de datos.
    	System.out.println(con);
    	System.out.println("Connected to Database");
    	*/
    
    // C�digo agregado para leer registros de la base de datos.
    	
    	String str = "SELECT * FROM student WHERE email = ? AND password = ?";
    	
    	pst = con.prepareStatement(str);
    	
    	pst.setString(1, username.getText());
    	pst.setString(2, password.getText());
    	
    	ResultSet rs = pst.executeQuery();
    	int count=0;
    	
    	
    	/*
    	 * Sebasti�n: Agregu� el siguiente bloque porque por alg�n motivo 
    	 * el m�todo no estaba ingresando al bucle while,
    	 * as� que quer�a revisar qu� datos estaban siendo procesados.
    	 * El error se resolvi� por obra de los enanitos de Java, as� que ya no es necesario. 
    	 */
    	 
    	System.out.println(count); // Revisar que el conteo sea correcto.
    	System.out.println(str); // Revisar que la consulta sea correcta:
    	System.out.println(username.getText());
    	System.out.println(password.getText());
    	System.out.println(rs);
    	
    	while(rs.next())
    	{
    		count = count+1;
    		System.out.println(count); // Revisar que el conteo sea correcto.
    		System.out.println("Dentro del while"); // Marcar visualmente que se entr� al ciclo.
    	}
    	
    	System.out.println("******"); // Marcar visualmente el fin de un intento de ingreso.
    	
    	/* 
    	 * Valida que los datos ingresados por el usuario
    	 * coincidan con 1 y s�lo 1 registro en la base de datos. 
    	 */
    	
    	if(count==1)
    	{
    		// System.out.println("Login Successfull"); // Versi�n inicial, antes de crear el mensaje de error real.
    		msg.setMessage("Login is successfull");
    		
    		login.getScene().getWindow().hide();
    		
    		Stage home = new Stage();
    		Parent root = FXMLLoader.load(getClass().getResource("/FXML_Files/HomePage.fxml"));
    		home.setScene(new Scene(root));
    		home.show();
    		

    	}
    	else
    	{
    		// System.out.println("Login Failed"); // Versi�n inicial, antes de crear el mensaje de error real.
    		msg.setMessage("Login failed");
    	}
    		
    }

    @FXML
    void createRegister(ActionEvent event) throws IOException {

    	register.getScene().getWindow().hide();
    	
    	Stage signup = new Stage();
    	Parent root = FXMLLoader.load(getClass().getResource("/FXML_Files/SignUP.fxml"));
    	Scene scene = new Scene(root);
    	signup.setScene(scene);
    	signup.show();
    	
    }

}
