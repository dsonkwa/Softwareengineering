package Klavier;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

@SuppressWarnings("restriction")
public class ChangeInstrument extends Parent {
	
	public ChangeInstrument() {
		
		GridPane gridpane=new GridPane();
		ImageView piano = new ImageView(new Image(ChangeInstrument.class.getResourceAsStream
				("file:///C:/eclipse-workspace/KlavierSpiel/src/Klavier/image/327153%5B1%5D.png")));
        piano.setFitHeight(50);
        piano.setPreserveRatio(true);
        ImageView guitare = new ImageView(new Image(ChangeInstrument.class.getResourceAsStream
        		("file:///C:/eclipse-workspace/KlavierSpiel/src/Klavier/image/327155%5B1%5D.png")));
        guitare.setFitHeight(50);
        guitare.setPreserveRatio(true);
        ImageView orgue = new ImageView(new Image(ChangeInstrument.class.getResourceAsStream
        		("file:///C:/eclipse-workspace/KlavierSpiel/src/Klavier/image/327154%5B1%5D.png")));
        orgue.setFitHeight(50);
        orgue.setPreserveRatio(true);
        
        //on ajoute nos images à notre layout
        gridpane.add(piano, 1, 0);
        gridpane.add(guitare, 1, 1);
        gridpane.add(orgue, 1, 2);
        gridpane.setVgap(15);
        
        this.getChildren().add(gridpane);
        this.setTranslateX(100);
        this.setTranslateY(30);



		
	}

}
