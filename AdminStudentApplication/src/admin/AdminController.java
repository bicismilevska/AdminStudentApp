package admin;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


import dbutil.DbConnection;
import grades.GradesClass;
import grades.GradesController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.stage.Window;
import loginapp.LoginController;
import loginapp.Main;
import signup.SignupController;


public class AdminController implements Initializable{
	@FXML
	private TextField firstname;
	@FXML
	private TextField lastname;
	@FXML
	private TextField email;
	@FXML
	private DatePicker dateofbirth;
	@FXML
	private Button addstudent;
	@FXML
	private Button logout;
	@FXML
	private Button loaddata;
	@FXML
	private Button listgrades;
	@FXML 
	private TableView<StudentData> tableviewstudent;
	@FXML
	private TableColumn<StudentData,String> column1;
	@FXML
	private TableColumn<StudentData,String> column2;
	@FXML
	private TableColumn<StudentData,String> column3;
	@FXML
	private TableColumn<StudentData,String> column4;
	@FXML
	private TableColumn<StudentData,String> column5;
	@FXML
	private ContextMenu cm;
	@FXML
	private MenuItem update;
	@FXML
	private MenuItem delete;
	private ObservableList<StudentData> data;
  private  List<StudentData> students = new ArrayList<>();
	private static final String queryStudents="SELECT * FROM students";
	private static final String queryAddStudent="INSERT INTO students (fname,lname,email,DOB) VALUES (?,?,?,?)";
	private static final String queryEmailStudents="SELECT email FROM students WHERE email=?";
	private static final String addStudentgrades="INSERT INTO studentgrades(idstudent) VALUES(?)";
	private static final String getIdForStudent="SELECT id FROM students WHERE email=?";
	private static final String deleteStudent="DELETE FROM students WHERE id =?";
	private static final String deleteStudentFromGrades="DELETE FROM studentgrades WHERE idstudent =?";
   DbConnection dbcon;
   private PreparedStatement add;
   private PreparedStatement emailsQuery;
   private PreparedStatement addStudentGrades;
   private PreparedStatement getIdStudent;
   private PreparedStatement delete1;
   private PreparedStatement delete2;
   Connection conn;

@Override
public void initialize(URL location, ResourceBundle resources) {

            dbcon=new DbConnection();
            try {
		   conn=dbcon.getConnection();
		   emailsQuery=conn.prepareStatement(queryEmailStudents);
	       add=conn.prepareStatement(queryAddStudent);
	       addStudentGrades=conn.prepareStatement(addStudentgrades);
	       getIdStudent=conn.prepareStatement(getIdForStudent);
	       delete1=conn.prepareStatement(deleteStudent);
	       delete2=conn.prepareStatement(deleteStudentFromGrades);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            loadAction();
}
public void loadAction() {
	 Task<ObservableList<StudentData>> task = new GetAllStudentsTask();
	      

	
//	  this.data=FXCollections.observableArrayList();
//	  ResultSet set=conn.createStatement().executeQuery(queryStudents);
//	  while(set.next()) {
//		  this.data.add(new StudentData(set.getString(1),set.getString(2),set.getString(3),set.getString(4)));
//	  }
//	  conn.close();
//	}catch(SQLException e) {
//		System.out.println("Couldn't load students "+ e.getMessage());
//	}
	  this.column1.setCellValueFactory(new PropertyValueFactory<StudentData, String>("ID"));
	  this.column2.setCellValueFactory(new PropertyValueFactory<StudentData, String>("firstName"));
	  this.column3.setCellValueFactory(new PropertyValueFactory<StudentData, String>("lastName"));
	  this.column4.setCellValueFactory(new PropertyValueFactory<StudentData, String>("email"));
	  this.column5.setCellValueFactory(new PropertyValueFactory<StudentData, String>("dateofbirth"));
	  this.tableviewstudent.itemsProperty().bind(task.valueProperty());

      new Thread(task).start();
}
public void addStudent() {
      
       try {
    	   if(!firstname.getText().isEmpty() && !lastname.getText().isEmpty() && !email.getText().isEmpty() && !dateofbirth.getEditor().getText().isEmpty()) { 
    		 
    		  emailsQuery.setString(1, email.getText());
    		  ResultSet result=emailsQuery.executeQuery();
    		  if(result.next()) {
    			  Alert alert = new Alert(AlertType.INFORMATION, "", ButtonType.OK);
      			alert.setHeaderText("Email already taken");
      			alert.setTitle("Add a student");
      			alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
      			alert.show();	  
    		  }
    		  else {    
				      // add.setString(1, idstudent.getText());
				       add.setString(1, firstname.getText());
				       add.setString(2, lastname.getText());
				       add.setString(3, email.getText());
				       add.setString(4, dateofbirth.getEditor().getText());
				       int record=add.executeUpdate();
				       getIdStudent.setString(1, email.getText());
				       ResultSet res=getIdStudent.executeQuery();
				        int idforthatstudent=0;
				       while(res.next()) {
				    	   idforthatstudent=res.getInt(1);
				       }
				       addStudentGrades.setInt(1, idforthatstudent);
				       addStudentGrades.execute();
				     
				       if(record==1) {
				    	   StudentData newstudent=new StudentData();
				    	   newstudent.setID(Integer.toString(idforthatstudent));
				    	   newstudent.setFirstName(firstname.getText());
				    	   newstudent.setLastName(lastname.getText());
				    	   newstudent.setEmail(email.getText());
				    	   newstudent.setDateofbirth(dateofbirth.getEditor().getText());
				    	   students.add(newstudent);
				    	   tableviewstudent.refresh();
				    	   
				    	   Alert alert = new Alert(AlertType.INFORMATION, "", ButtonType.OK);
			      			alert.setHeaderText("Student added");
			      			alert.setTitle("Add a student");
			      			alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
			      			alert.show();	
			      			loadAction();
				       }

				      	firstname.setText("");
				       	lastname.setText("");
				       	email.setText("");
				       	dateofbirth.setValue(null);
				          
				           }
    	   }
    	   else {
    			Alert alert = new Alert(AlertType.INFORMATION, "", ButtonType.OK);
    			alert.setHeaderText("Please fill in all information");
    			alert.setTitle("Add a student");
    			alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
    			alert.show();
    	   }
    	   
       }catch(SQLException e) {
    	   System.out.println("Couldn't add student "+ e.getMessage());
       }

       
       
}

public List<StudentData> querythestudents() {

    try(Statement statement = conn.createStatement();
        ResultSet results = statement.executeQuery(queryStudents)) {
        while(results.next()) {
            StudentData student = new StudentData();
            student.setID(results.getString(1));
            student.setFirstName(results.getString(2));
            student.setLastName(results.getString(3));
            student.setEmail(results.getString(4));
            student.setDateofbirth(results.getString(5));
              students.add(student);
        }

        return students;

    } catch(SQLException e) {
        System.out.println("Query failed: " + e.getMessage());
        return null;
    }
}
public void logOut() throws Exception {
 Stage thisstage=(Stage) this.logout.getScene().getWindow();
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
 
		
}
public void listGrades() {
//	final StudentData student= (StudentData) tableviewstudent.getSelectionModel().getSelectedItem();
//  	if(student==null) {
//		System.out.println("NO ARTIST SELECTED");
//		return;
	try {
		Stage stage1=new Stage();
		FXMLLoader adminloader=new FXMLLoader();
		Pane root=(Pane) adminloader.load(getClass().getResource("/grades/grade.fxml").openStream());
		GradesController sc=adminloader.getController();
		Scene scene=new Scene(root);
		stage1.setScene(scene);
		stage1.setTitle("Grades");
		stage1.setResizable(true);
		stage1.show();
	}catch(IOException e) {
		System.out.println("Couldn't open sign up window "+ e.getMessage());
		e.printStackTrace();
	}

}



public class GetAllStudentsTask extends Task {

    @Override
    public ObservableList<StudentData> call()  {
        return FXCollections.observableArrayList
                (querythestudents());
    }
}
public void deleteStudent() {
	StudentData student=tableviewstudent.getSelectionModel().getSelectedItem();
	try {
		delete1.setString(1, student.getID());
		delete2.setString(1, student.getID());
		delete1.execute();
		delete2.execute();
	     loadAction();
	}
	catch(SQLException e) {
		System.out.println("Couldn't delete student "+ e.getMessage());
	}
}
}


