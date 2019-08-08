package grades;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.event.ChangeListener;

import admin.StudentData;
import dbutil.DbConnection;
import javafx.beans.property.SimpleStringProperty;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;

public class GradesController implements Initializable {
	@FXML
	 private TableView<GradesClass>tableviewgrades;
	@FXML
	private TableColumn<GradesClass,String> studentidcolumn;
	@FXML
	private TableColumn<GradesClass,String> mathcolumn;
	@FXML
	private TableColumn<GradesClass,String> physicscolumn;
	@FXML
	private TableColumn<GradesClass,String> sciencecolumn;
	@FXML
	private TableColumn<GradesClass,String> businesscolumn;
	@FXML
	private TableColumn<GradesClass,String> historycolumn;
	@FXML
	private TableColumn<GradesClass,String> managementcolumn;
	@FXML
	private TableColumn<GradesClass,String> biologycolumn;
	@FXML
	private TableColumn<GradesClass,String> philosophycolumn;
	@FXML
	private TableColumn<GradesClass,String> frenchcolumn;
	@FXML
	private TextField id1;
	@FXML
	private TextField math1;
	@FXML
	private TextField phy1;
	@FXML
	private TextField sci1;
	@FXML
	private TextField bus1;
	@FXML
	private TextField his1;
	@FXML
	private TextField man1;
	@FXML
	private TextField bio1;
	@FXML
	private TextField phi1;
	@FXML
	private TextField fre1;
	@FXML

 		 
	private static final String queryStudentGrades="SELECT * FROM studentgrades";
private static final String addMathGrade="UPDATE studentgrades SET Math = ? WHERE idstudent = ?";
private static final String addPhysicsGrade="UPDATE studentgrades SET Physics = ? WHERE idstudent = ?";
private static final String addScienceGrade="UPDATE studentgrades SET Science = ? WHERE idstudent = ?";
private static final String addBusinessGrade="UPDATE studentgrades SET Business = ? WHERE idstudent = ?";
private static final String addHistoryGrade="UPDATE studentgrades SET History = ? WHERE idstudent = ?";
private static final String addManagementGrade="UPDATE studentgrades SET Management = ? WHERE idstudent = ?";
private static final String addBiologyGrade="UPDATE studentgrades SET Biology = ? WHERE idstudent = ?";
private static final String addPhilosophyGrade="UPDATE studentgrades SET Philosophy = ? WHERE idstudent = ?";
private static final String addFrenchGrade="UPDATE studentgrades SET French = ? WHERE idstudent = ?";
	   DbConnection dbcon;
	   private PreparedStatement showgrades;
	   private PreparedStatement mathGrade;
	   private PreparedStatement phyGrade;
	   private PreparedStatement sciGrade;
	   private PreparedStatement busGrade;
	   private PreparedStatement hisGrade;
	   private PreparedStatement manGrade;
	   private PreparedStatement bioGrade;
	   private PreparedStatement phiGrade;
	   private PreparedStatement freGrade;
	   
	   Connection conn;
	   
	   public void initialize(URL location, ResourceBundle resources) {

	               dbcon=new DbConnection();
	               try {
	   		   conn=dbcon.getConnection();
	   	       showgrades=conn.prepareStatement(queryStudentGrades);
	   	       mathGrade=conn.prepareStatement(addMathGrade);
	   	       phyGrade=conn.prepareStatement(addPhysicsGrade);
	   	       sciGrade=conn.prepareStatement(addScienceGrade);
	   	       busGrade=conn.prepareStatement(addBusinessGrade);
	   	       hisGrade=conn.prepareStatement(addHistoryGrade);
	   	       manGrade=conn.prepareStatement(addManagementGrade);
	   	       bioGrade=conn.prepareStatement(addBiologyGrade);
	   	       phiGrade=conn.prepareStatement(addPhilosophyGrade);
	   	       freGrade=conn.prepareStatement(addFrenchGrade);
	   	    
	   			} catch (SQLException e) {
	   				// TODO Auto-generated catch block
	   				e.printStackTrace();
	   			}
	   
	               loadGrades();
	            
	   }
	   public void loadGrades() {
		   Task<ObservableList<GradesClass>> task2=new Task<ObservableList<GradesClass>>(){

			@Override
			protected ObservableList<GradesClass> call() throws Exception {
			        return FXCollections.observableArrayList(queryshowinggrades());
			}
			   
		   };
		   this.studentidcolumn.setCellValueFactory(new PropertyValueFactory<GradesClass, String>("IDstudent"));
           this.mathcolumn.setCellValueFactory(new PropertyValueFactory<GradesClass, String>("math"));
           this.physicscolumn.setCellValueFactory(new PropertyValueFactory<GradesClass, String>("physics"));
           this.sciencecolumn.setCellValueFactory(new PropertyValueFactory<GradesClass, String>("science"));
           this.businesscolumn.setCellValueFactory(new PropertyValueFactory<GradesClass, String>("business"));
           this.historycolumn.setCellValueFactory(new PropertyValueFactory<GradesClass, String>("history"));
           this.managementcolumn.setCellValueFactory(new PropertyValueFactory<GradesClass, String>("management"));
            this.biologycolumn.setCellValueFactory(new PropertyValueFactory<GradesClass, String>("biology"));
           this.philosophycolumn.setCellValueFactory(new PropertyValueFactory<GradesClass, String>("philosophy"));
           this.frenchcolumn.setCellValueFactory(new PropertyValueFactory<GradesClass, String>("french"));
           this.tableviewgrades.itemsProperty().bind(task2.valueProperty());
           new Thread(task2).start();
	   
	   }
	   
	   
	   private List<GradesClass> queryshowinggrades(){
			List<GradesClass> list=new ArrayList<>();
			try {
			ResultSet res=showgrades.executeQuery();
			while(res.next()) {
				GradesClass gc=new GradesClass();
			       gc.setIDstudent(res.getString(1));
			       gc.setMath(res.getString(2));
			       gc.setPhysics(res.getString(3));
			       gc.setScience(res.getString(4));
			       gc.setBusiness(res.getString(5));
			       gc.setHistory(res.getString(6));
			       gc.setManagement(res.getString(7));
			       gc.setBiology(res.getString(8));
			       gc.setPhilosophy(res.getString(9));
			       gc.setFrench(res.getString(10));
			       System.out.println(gc.getIDstudent() + " " + gc.getMath()+" "+ gc.getPhysics()+""+ gc.getScience()+""+ gc.getBusiness());
			      list.add(gc);
			}
			System.out.println();
			 showgrades.close();
			return list;
			   
			}catch(SQLException e) {
				System.out.println("huhuhuh" + e.getMessage());
				e.printStackTrace();
				return null;
			}
		}
	   public void addNewGrades() {
		   if(!math1.getText().isEmpty() || !phy1.getText().isEmpty() || !sci1.getText().isEmpty() || !bus1.getText().isEmpty() ||!his1.getText().isEmpty() || 
				   !man1.getText().isEmpty() || !bio1.getText().isEmpty() ||!phi1.getText().isEmpty() ||!fre1.getText().isEmpty()) {
		     GradesClass gradeclass= (GradesClass) tableviewgrades.getSelectionModel().getSelectedItem();
		     if(gradeclass==null) {
		    	   Alert alert = new Alert(AlertType.INFORMATION, "", ButtonType.OK);
	      			alert.setHeaderText("You haven't selected any student");
	      			alert.setTitle("Grade");
	      			alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
	      			alert.show();
		     }
		    
		     if(!math1.getText().isEmpty()) {
		     Task<Boolean> task2=new Task<Boolean>(){
					@Override
					protected Boolean call() throws Exception {
					        return addMath(gradeclass.getIDstudent(),math1.getText(),mathGrade);
					}
					   
				   };

			task2.setOnSucceeded(e->{
				if(task2.valueProperty().get()) {
					gradeclass.setMath(math1.getText());
					tableviewgrades.refresh();
					math1.setText("");
				}
			});
				  
			new Thread(task2).start();
		   System.out.println(math1.getText());
		     }
		     if(!phy1.getText().isEmpty()) {
			     Task<Boolean> task3=new Task<Boolean>(){
							@Override
							protected Boolean call() throws Exception {
							        return addMath(gradeclass.getIDstudent(),phy1.getText(),phyGrade);
							}
							   
						   };

					task3.setOnSucceeded(e->{
						if(task3.valueProperty().get()) {
							gradeclass.setPhysics(phy1.getText());
							tableviewgrades.refresh();
							phy1.setText("");
						}
					});
						  
					new Thread(task3).start();
		     }
		     if(!sci1.getText().isEmpty()) {
		         Task<Boolean> task4=new Task<Boolean>(){
							@Override
							protected Boolean call() throws Exception {
							        return addMath(gradeclass.getIDstudent(),sci1.getText(),sciGrade);
							}
							   
						   };

					task4.setOnSucceeded(e->{
						if(task4.valueProperty().get()) {
							gradeclass.setScience(sci1.getText());
							tableviewgrades.refresh();
							sci1.setText("");
						}
					});
						  
					new Thread(task4).start();
		     }
		     if(!bus1.getText().isEmpty()) {
		         Task<Boolean> task5=new Task<Boolean>(){
							@Override
							protected Boolean call() throws Exception {
							        return addMath(gradeclass.getIDstudent(),bus1.getText(),busGrade);
							}
							   
						   };

					task5.setOnSucceeded(e->{
						if(task5.valueProperty().get()) {
							gradeclass.setBusiness(bus1.getText());
							tableviewgrades.refresh();
							bus1.setText("");
						}
					});
						  
					new Thread(task5).start();
		     }
		     if(!his1.getText().isEmpty()) {
		         Task<Boolean> task6=new Task<Boolean>(){
						@Override
						protected Boolean call() throws Exception {
						        return addMath(gradeclass.getIDstudent(),his1.getText(),hisGrade);
						}
						   
					   };

				task6.setOnSucceeded(e->{
					if(task6.valueProperty().get()) {
						gradeclass.setHistory(his1.getText());
						tableviewgrades.refresh();
						his1.setText("");
					}
				});
					  
				new Thread(task6).start();
		     }
		     if(!man1.getText().isEmpty()) {
		         Task<Boolean> task7=new Task<Boolean>(){
		   						@Override
		   						protected Boolean call() throws Exception {
		   						        return addMath(gradeclass.getIDstudent(),man1.getText(),manGrade);
		   						}
		   						   
		   					   };

		   				task7.setOnSucceeded(e->{
		   					if(task7.valueProperty().get()) {
		   						gradeclass.setManagement(man1.getText());
		   						tableviewgrades.refresh();
		   						man1.setText("");
		   					}
		   				});
		   					  
		   				new Thread(task7).start();
		     }
		     if(!bio1.getText().isEmpty()) {
		    	    Task<Boolean> task8=new Task<Boolean>(){
   						@Override
   						protected Boolean call() throws Exception {
   						        return addMath(gradeclass.getIDstudent(),bio1.getText(),bioGrade);
   						}
   						   
   					   };

   				task8.setOnSucceeded(e->{
   					if(task8.valueProperty().get()) {
   						gradeclass.setBiology(bio1.getText());
   						tableviewgrades.refresh();
   						bio1.setText("");
   					}
   				});
   					  
   				new Thread(task8).start();
		     }
		     if(!phi1.getText().isEmpty()) {
			    	    Task<Boolean> task9=new Task<Boolean>(){
	   						@Override
	   						protected Boolean call() throws Exception {
	   						        return addMath(gradeclass.getIDstudent(),phi1.getText(),phiGrade);
	   						}
	   						   
	   					   };

	   				task9.setOnSucceeded(e->{
	   					if(task9.valueProperty().get()) {
	   						gradeclass.setPhilosophy(phi1.getText());
	   						tableviewgrades.refresh();
	   						phi1.setText("");
	   					}
	   				});
	   					  
	   				new Thread(task9).start();
		     }
		     if(!fre1.getText().isEmpty()) {
		    	    Task<Boolean> task9=new Task<Boolean>(){
	   						@Override
	   						protected Boolean call() throws Exception {
	   						        return addMath(gradeclass.getIDstudent(),fre1.getText(),freGrade);
	   						}
	   						   
	   					   };

	   				task9.setOnSucceeded(e->{
	   					if(task9.valueProperty().get()) {
	   						gradeclass.setFrench(fre1.getText());
	   						tableviewgrades.refresh();
	   						fre1.setText("");
	   					}
	   				});
	   					  
	   				new Thread(task9).start();
		     }
		     id1.setText("");
		   }else {
			   Alert alert = new Alert(AlertType.INFORMATION, "", ButtonType.OK);
     			alert.setHeaderText("You haven't entered any grade");
     			alert.setTitle("Grades");
     			alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
     			alert.show();
		   }
			}
	   
	   public boolean addMath(String id,String newMath,PreparedStatement statement) {
		   try {
			   
			   statement.setString(1, newMath);
		         statement.setString(2, id);
		         int affectedrows=statement.executeUpdate();
		         return affectedrows==1;
			   
		   }catch(SQLException e) {
			   System.out.println("Couldn't add "+ e.getMessage());
			   e.printStackTrace();
			   return false;
		   }
	   }
	   public void clickedItem() {
		   GradesClass gradeclass=tableviewgrades.getSelectionModel().getSelectedItem();
		   if(gradeclass!=null) {
			   id1.setText(gradeclass.getIDstudent());
		   }
		   System.out.println("clickeddddddd");
	   }
}
