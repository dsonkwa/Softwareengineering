package Praktikum_3_JavaFX;

import javafx.application.Application;
import javafx.concurrent.Worker;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Bandit extends Application {

    private int radanzahl = 3;
    private int zeilen = 3;
    private ImageView[][] bilder = new ImageView[radanzahl][zeilen];
    private RadService[] services = new RadService[radanzahl];
    private Label infoLabel = new Label("Klicke um zu starten");
    private GridPane grid = new GridPane();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        for (int i = 0; i < radanzahl; i++) {
            int bildanzahl = 6;
            services[i] = new RadService(bildanzahl);
            for (int j = 0; j < zeilen; j++) {
                bilder[i][j] = new ImageView();
                bilder[i][j].imageProperty().bind(new ImageBinding(services[i].valueProperty(), j-1, bildanzahl));
                bilder[i][j].setFitHeight(100);
                bilder[i][j].setFitWidth(100);
                grid.add(bilder[i][j], i, j);
            }
        }
        setEndCheck();
        grid.add(infoLabel, 0, 3, 3, 1);
        grid.setVgap(30);
        grid.setHgap(30);
        grid.setStyle("-fx-background: white");
        GridPane.setMargin(infoLabel, new Insets(5, 5, 5, 15));
        Scene scene = new Scene(grid, 360, 420);

        scene.setOnMouseClicked(event -> {
            for (RadService iService:services) {
                iService.restart();
            }
            infoLabel.setText("Spiel läuft...");
        });

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Einarmiger Bandit");
        primaryStage.show();
    }

    private void setEndCheck() {
        for (RadService iService:services) {
            iService.setOnSucceeded(event -> {
                boolean alleFertig = true;
                for (RadService jService:services) {
                    if (jService.getState().equals(Worker.State.RUNNING))
                        alleFertig = false;
                }
                if (alleFertig) {
                    boolean alleGleich = true;
                    for (int i = 0; i < radanzahl-1; i++) {
                        if (!services[i].getValue().equals(services[i + 1].getValue())) {
                            alleGleich = false;
                        }
                    }
                    if (alleGleich)
                        infoLabel.setText("Spiel gewonnen");
                    else
                        infoLabel.setText("Leider verloren - Klicken um neuzustarten");
                }
            });
        }
    }
}

