package loginapp;
	
import java.util.Optional;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	public static Stage primarystage;
	@Override
	public void start(Stage stage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
			stage.setTitle("School Management");
			 	Scene scene = new Scene(root,600,400);
			stage.setScene(scene);
	stage.show();;
	stage.setOnCloseRequest(new EventHandler<WindowEvent>() {

		@Override
		public void handle(WindowEvent event) {
			Dialog<ButtonType> dialog=new Dialog<ButtonType>();
			dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
			dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
			dialog.setTitle("Quit");
			dialog.setHeaderText("Are you sure you want to quit the application?");
			Optional<ButtonType> result=dialog.showAndWait();
			if(result.isPresent()&& result.get()==ButtonType.OK) {
			primarystage.close();
			Platform.exit();
			System.exit(0);
			}
			else if(result.isPresent()&& result.get()==ButtonType.CANCEL) {
			event.consume();
			}	
		}	
	});
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		primarystage=stage;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
