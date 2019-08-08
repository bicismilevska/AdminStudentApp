package signup;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import dbutil.DbConnection;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import loginapp.LoginController;
import loginapp.option;

public class SignupController implements Initializable {
@FXML
private TextField user;
@FXML
private TextField pass;
@FXML
private ComboBox<option> aors;
@FXML
private Button  signup2;
@FXML
private Label label1;
@FXML
private Label label2;
private DbConnection db;
private Connection con;
private static final String queryAddAccount="INSERT INTO login(username,password,division) VALUES (?,?,?)";
private PreparedStatement statementAddAccount;
private static final String queryUsernames="SELECT username FROM login WHERE username =?";
private static final String queryPasswords="SELECT password FROM login WHERE password =?";
private PreparedStatement queryUser;
private PreparedStatement queryPass;
LoginController lg=new LoginController();


@Override
public void initialize(URL location, ResourceBundle resources) {
	db=new DbConnection();
	this.aors.setItems(FXCollections.observableArrayList(option.values()));
	try {
		con=db.getConnection();
		statementAddAccount=con.prepareStatement(queryAddAccount);
		queryUser=con.prepareStatement(queryUsernames);
		queryPass=con.prepareStatement(queryPasswords);

	}
	catch(SQLException e) {
		System.out.println("Something went wrong "+ e.getMessage());
	}
	
}
@FXML
public void addAccount(ActionEvent event) {

	try { 
		if(!aors.getSelectionModel().isEmpty() && !(this.user.getText().isEmpty()) && !(this.pass.getText().isEmpty())) {
		queryUser.setString(1, user.getText());
	      queryPass.setString(1, pass.getText());
	      ResultSet res=queryUser.executeQuery();
	      ResultSet heh=queryPass.executeQuery();
	      int count=0;
	      if(res.next()) {
	    	  label1.setText("Username already taken");
	    	  System.out.println("Username taken");
	    	  count=1;
	      }
	      if(heh.next()) {
	    	  label2.setText("Password already taken"); 
	    	  System.out.println("Password taken");
	    	  count=1;
	      }
	     if(count==0) {
		statementAddAccount.setString(1, user.getText());
		statementAddAccount.setString(2, pass.getText());
		statementAddAccount.setString(3, ((option)aors.getValue()).toString());
		int record=statementAddAccount.executeUpdate();	
		if(record == 1 ) {
           System.out.println("Added ");
       	Alert alert = new Alert(AlertType.INFORMATION, "", ButtonType.OK);
		alert.setHeaderText("New account created");
		alert.setTitle("Account information");
		alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
		Optional<ButtonType> result=alert.showAndWait();
		if(result.get()==ButtonType.OK) {
		     Stage stage=(Stage) this.signup2.getScene().getWindow();
				stage.close();
				statementAddAccount.close();
				queryUser.close();
				queryPass.close();
				con.close();
        } 
		}
		else {
            throw new SQLException("The song insert failed");
        }
		
	     
	     
		}	
		}else {	Alert alert = new Alert(AlertType.INFORMATION, "", ButtonType.OK);
		alert.setHeaderText("Please fill in all information");
		alert.setTitle("Sign Up Info");
		alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
		alert.show();
			
		}
//		
			
	}catch(SQLException e) {
		System.out.println("Couldn't add account" + e.getMessage());
		e.printStackTrace();
	}

}

}

