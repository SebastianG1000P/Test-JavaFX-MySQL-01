package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import DBConnection.connection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import AlertMessage.Message;

 

public class SignUpController implements Initializable {

    @FXML private TextField fullName;
    @FXML private TextField password;
    @FXML private TextField email;
    @FXML private CheckBox check;
    @FXML private Button signup;
    @FXML private Button login;
    
    private PreparedStatement ps;
	
    Message msg = new Message();
    
    connection conOBJ = new connection();
    Connection con;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
		fullName.setStyle("-fx-text-inner-color:#4b5461;");
		email.setStyle("-fx-text-inner-color:#4b5461;");
		password.setStyle("-fx-text-inner-color:#4b5461;");
	}
		
	
	// Ir a la pantalla para abrir sesión.
	@FXML
	public void goLogin(ActionEvent event) throws IOException {
		
		login.getScene().getWindow().hide();
		Stage login = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/FXML_Files/Login.fxml"));
		Scene scene = new Scene(root);
		login.setScene(scene);
		login.show();
	}
	
	// Creación de nueva cuenta.
	// TODO: Validar que no se pueda dejar campos vacíos.
	// TODO: Validar que el email tenga formato correcto.
	// TODO: Validar que el email sea único.
	// TODO: Validar que el usuario deba verificar la contraseña escribiéndola una segunda vez.
	// TODO: Validaciones varias.
	
	@FXML
	public void signUp(ActionEvent event) throws ClassNotFoundException, SQLException {
		
		if(check.isSelected())
		{
			con = conOBJ.getConnection();
			
			String insert = "INSERT INTO student(fullName,password,email)" + "Values (?,?,?)";
			
			ps = con.prepareStatement(insert);
			
			ps.setString(1, fullName.getText());
			ps.setString(2, password.getText());
			ps.setString(3, email.getText());
			
			ps.executeUpdate();
			
			System.out.println(insert); // Revisar que la consulta sea correcta:
			
			// System.out.println("Data inserted"); // 
			
			msg.setMessage("Los datos han sido insertados.");
		}
		else
		{
				msg.setMessage("Acepta los términos y condiciones.");
		}
	}
	
}
