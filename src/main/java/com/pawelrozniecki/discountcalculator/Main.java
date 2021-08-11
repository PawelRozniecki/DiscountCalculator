package com.pawelrozniecki.discountcalculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("gui.fxml"));

        Parent root = loader.load();
        root.setId("root");
        primaryStage.setTitle("Discount Calculator");
        Scene scene = new Scene(root, 900, 700);

        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        primaryStage.setScene(scene);

        primaryStage.show();
    }
}
