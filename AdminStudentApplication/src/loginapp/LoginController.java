package loginapp;


import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import admin.AdminController;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import signup.SignupController;
import students.StudentController;

public class LoginController implements Initializable {
   @FXML
   private Label dbstatus;
   @FXML
   private TextField username;
   @FXML 
   private TextField password;
   @FXML
   private ComboBox<option> combobox;
   @FXML
   private Button loginButton;
   @FXML
   private Label loginLabel;
   @FXML
   private Button signup1;
   LoginModel loginmodel=new LoginModel();
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		if(loginmodel.isDatabaseConnected()) {
			dbstatus.setText("Connected to the Database");
		}
		else {
			dbstatus.setText("Not connected to the Database");
		}
		this.combobox.setItems(FXCollections.observableArrayList(option.values()));
	}
	public void Login() {

		try {
			if(!combobox.getSelectionModel().isEmpty() && !(this.password.getText().isEmpty()) && !(this.password.getText().isEmpty())) {
		if(this.loginmodel.isLogin(this.username.getText(), this.password.getText(), ((option)combobox.getValue()).toString())){
			Stage stage=(Stage) this.loginButton.getScene().getWindow();
			stage.close();
			switch(((option)combobox.getValue()).toString()) {
			case "Admin":
				adminLogin();
			         break;
			case "Student":
				studentLogin();
				
			break;
			}
			
		}else {
			Alert alert = new Alert(AlertType.INFORMATION, "", ButtonType.OK);
			alert.setHeaderText("Invalid username or password");
			alert.setTitle("Login Info");
			alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
			alert.show();
			this.loginLabel.setText("Invalid inputs");
		}
		}
		else {
			Alert alert = new Alert(AlertType.INFORMATION, "", ButtonType.OK);
			alert.setHeaderText("Please fill up all the information");
			alert.setTitle("Login Info");
			alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
			alert.show();
		
		}
	}catch(Exception e) {
		System.out.println("Action denied "+ e.getMessage());
		e.printStackTrace();
	}
	}
	public void studentLogin() {
		try {
			Stage stage1=new Stage();
			FXMLLoader loader=new FXMLLoader();
			Pane root=(Pane) loader.load(getClass().getResource("/students/studentFXML.fxml").openStream());
			StudentController sc=loader.getController();
			Scene scene=new Scene(root);
			stage1.setScene(scene);
			stage1.setTitle("Student DashBoard");
			stage1.setResizable(false);
			stage1.show();
		}catch(IOException e) {
			System.out.println("Couldn't login student "+ e.getMessage());
		}
	}
	public void adminLogin() {
		try {
			Stage stage1=new Stage();
			FXMLLoader adminloader=new FXMLLoader();
			Pane root=(Pane) adminloader.load(getClass().getResource("/admin/admin.fxml").openStream());
			AdminController sc=adminloader.getController();
			Scene scene=new Scene(root);
			stage1.setScene(scene);
			stage1.setTitle("Admin DashBoard");
			stage1.setResizable(true);
			stage1.show();
			stage1.setOnCloseRequest(new EventHandler<WindowEvent>() {

				@Override
				public void handle(WindowEvent event) {
					Dialog<ButtonType> dialog=new Dialog<ButtonType>();
					dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
					dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
					dialog.setTitle("Quit");
					dialog.setHeaderText("Are you sure you want to quit the application?");
					Optional<ButtonType> result=dialog.showAndWait();
					if(result.isPresent()&& result.get()==ButtonType.OK) {
					stage1.close();
					Platform.exit();
					}
					else if(result.isPresent()&& result.get()==ButtonType.CANCEL) {
					event.consume();
					}	
				}	
			});
		}catch(IOException e) {
			System.out.println("Couldn't login admin "+ e.getMessage());
			e.printStackTrace();
		}
	}
	public void signupwindow() {
		try {
			Stage stage1=new Stage();
			FXMLLoader signuploader=new FXMLLoader();
			Pane root=(Pane) signuploader.load(getClass().getResource("/signup/signup.fxml").openStream());
			SignupController sc=signuploader.getController();
			Scene scene=new Scene(root);
			stage1.setScene(scene);
			stage1.setTitle("Create an account");
			stage1.setResizable(false);
			stage1.show();
			stage1.setOnCloseRequest(new EventHandler<WindowEvent>() {

				@Override
				public void handle(WindowEvent event) {
					Dialog<ButtonType> dialog=new Dialog<ButtonType>();
					dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
					dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
					dialog.setTitle("Quit");
					dialog.setHeaderText("Are you sure you want to quit creating a new account?");
					Optional<ButtonType> result=dialog.showAndWait();
					if(result.isPresent()&& result.get()==ButtonType.OK) {
					stage1.close();
					}
					else if(result.isPresent()&& result.get()==ButtonType.CANCEL) {
					event.consume();
					}	
				}	
			});
		}catch(IOException e) {
			System.out.println("Couldn't open sign up window "+ e.getMessage());
			e.printStackTrace();
		}
	}
	

}
