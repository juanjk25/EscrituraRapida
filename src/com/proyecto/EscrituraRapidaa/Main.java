package com.proyecto.EscrituraRapidaa;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/juego.fxml"));
        Pane root = loader.load();

        Scene scene = new Scene(root, 600, 400);

        // ✅ Aplicar CSS
        scene.getStylesheets().add(getClass().getResource("view/style.css").toExternalForm());

        // 🖼️ Icono opcional
        try {
            stage.getIcons().add(new Image(getClass().getResourceAsStream("view/icon.png")));
        } catch (Exception e) {
            System.out.println("⚠ No se encontró icon.png");
        }

        // Ventana moderna
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Escritura Rápida");
        stage.setScene(scene);

        // Animación fade-in al abrir
        FadeTransition fadeIn = new FadeTransition(Duration.millis(1200), root);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.play();

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
