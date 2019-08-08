package students;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import dbutil.DbConnection;
import grades.GradesClass;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import loginapp.LoginController;



public class StudentController implements Initializable {
	@FXML
	private TextField stuid;
	@FXML
	private TextField one;
	@FXML
	private TextField two;
	@FXML
	private TextField three;
	@FXML
	private TextField four;
	@FXML
	private TextField five;
	@FXML
	private TextField six;
	@FXML
	private TextField seven;
	@FXML
	private TextField eight;
	@FXML
	private TextField nine;
	@FXML
	private Button logouti;
	private static final String getgrades="SELECT * FROM studentgrades where idstudent =?";
	private PreparedStatement getgradesstatement;
	
	  DbConnection dbcon;
	   Connection conn;
	   

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	            dbcon=new DbConnection();
	            try {
			   conn=dbcon.getConnection();
			   getgradesstatement=conn.prepareStatement(getgrades);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	
	
	public void getgetget() {
		try {
		if(!stuid.getText().isEmpty()) {
			GradesClass gc=new GradesClass();
			getgradesstatement.setString(1, stuid.getText());
			ResultSet res=getgradesstatement.executeQuery();
			while(res.next()) {
//				gc.setIDstudent(stuid.getText());
//				gc.setMath(res.getString(2));
//				gc.setPhysics(res.getString(3));
//				gc.setScience(res.getString(4));
				one.setText(res.getString(2));
				two.setText(res.getString(3));
				three.setText(res.getString(4));
				four.setText(res.getString(5));
				five.setText(res.getString(6));
				six.setText(res.getString(7));
				seven.setText(res.getString(8));
				eight.setText(res.getString(9));
				nine.setText(res.getString(10));
			}
			
			
		}else {
			   Alert alert = new Alert(AlertType.INFORMATION, "", ButtonType.OK);
     			alert.setHeaderText("You haven't entered your ID");
     			alert.setTitle("Students");
     			alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
     			alert.show();
		}
			getgradesstatement.close();
		}catch(SQLException e) {
			System.out.println("Couldn't add grades "+ e.getMessage());
		
		}
		
	}
	public void logOutFromStudent() throws Exception {
		 Stage thisstage=(Stage) this.logouti.getScene().getWindow();
		 thisstage.close();
			Stage stage1=new Stage();
			FXMLLoader loader=new FXMLLoader();
			Pane root;
		 root = (Pane) loader.load(getClass().getResource("/loginapp/main.fxml").openStream());
			LoginController sc=loader.getController();
			Scene scene=new Scene(root);
			stage1.setScene(scene);
			stage1.setTitle("Admin DashBoard");
			stage1.setResizable(false);
			stage1.show();
			conn.close();
		 
				
		}
}
