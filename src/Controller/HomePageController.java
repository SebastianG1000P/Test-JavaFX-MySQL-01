package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import DBConnection.connection;


public class HomePageController implements Initializable {

	    @FXML private Button show;
	    @FXML private TableView<Student> table; 
	    
	    @FXML private TableColumn<Student, String> fullname;
	    @FXML private TableColumn<Student, String> password;
	    @FXML private TableColumn<Student, String> email;
	    
	    @FXML private Button closeSession;
	    
	    Connection con;
	    
	    PreparedStatement pst;
	    
	    connection conObj = new connection();

	    @FXML void showData(ActionEvent event) throws ClassNotFoundException, SQLException {
	    	
	    	con = conObj.getConnection();
	    	
	    	ObservableList<Student> data = FXCollections.observableArrayList();
	    	
	    	table.setItems(data);
	    	
	    	String str = "SELECT * FROM student;";
	    	
	    	pst = con.prepareStatement(str);
	    	
	    	ResultSet rs = pst.executeQuery();
	    	
	    	while(rs.next())
	    	{
	    		Student s = new Student(rs.getString("fullName"),rs.getString("password"),rs.getString("email"));
	    		data.add(s);
	    	}
	    	
	    }
	    
	    /*
	     * El método initialize, cuando está presente, se ejecuta al abrir una ventana de JavaFX.
	     * En initialize se puede ejecutar instrucciones para preparar la ventana
		 * antes de que el usuario interactúe con ella.
		 */ 
	    
	    @Override
		 public void initialize(URL arg0, ResourceBundle arg1) {
		  
	    	fullname.setCellValueFactory(new PropertyValueFactory<Student,String>("fullName"));
	    	password.setCellValueFactory(new PropertyValueFactory<Student,String>("password"));
	    	email.setCellValueFactory(new PropertyValueFactory<Student,String>("email"));
	    		 
		 }
	    
	    // Método agregado por Sebastián para volver de la pantalla principal a la de inicio de sesión.
	    
		@FXML
		public void goLogin(ActionEvent event) throws IOException {
			
			closeSession.getScene().getWindow().hide();
			Stage closeSession = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/FXML_Files/Login.fxml"));
			Scene scene = new Scene(root);
			closeSession.setScene(scene);
			closeSession.show();
		}
	    
	    	
    
}
