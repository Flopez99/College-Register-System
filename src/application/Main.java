package application;
	
import border_pane.BorderMenu;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import model.College;


public class Main extends Application{
	
	@Override
	public void start(Stage primaryStage) {
		College college = new College();
		
		BorderMenu borderMenu = new BorderMenu(college);
		Scene scene = new Scene(borderMenu.getPane(), 800, 400);
	//	ImagePattern pattern = new ImagePattern(new Image("OCCC.jpg"));
		primaryStage.setScene(scene);
		primaryStage.setTitle("Online Course Community College");
		
		primaryStage.show();

	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
