package Klavier;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
public class Klavier extends Application {

	@Override
	public void start(Stage primaryStage){
		primaryStage.setTitle("KlavierSpiel");
		Pane pane = new Pane();
		Scene scene = new Scene(pane,500,500,Color.WHITE);
		
		
		Instrumental mon_instru= new Instrumental();
		MaClass monClavier= new MaClass(mon_instru);
		//ChangeInstrument mon_changeinstru = new ChangeInstrument();
		
		pane.getChildren().add(monClavier);
		primaryStage.setScene(scene);
		monClavier.requestFocus();
		 primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch();
	}

}
                                                                                                                                                                                                                                                                                                                                                                                                                                 package Klavier;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
public class Klavier extends Application {

	@Override
	public void start(Stage primaryStage){
		primaryStage.setTitle("KlavierSpiel");
		Pane pane = new Pane();
		Scene scene = new Scene(pane,500,500,Color.WHITE);
		
		
		Instrumental mon_instru= new Instrumental();
		MaClass monClavier= new MaClass(mon_instru);
		//ChangeInstrument mon_changeinstru = new ChangeInstrument();
		
		pane.getChildren().add(monClavier);
		primaryStage.setScene(scene);
		monClavier.requestFocus();
		 primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch();
	}

}
