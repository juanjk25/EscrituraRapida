package com.proyecto.EscrituraRapidaa.controller;

import com.proyecto.EscrituraRapidaa.model.Constantes;
import com.proyecto.EscrituraRapidaa.model.JuegoLogic;
import com.proyecto.EscrituraRapidaa.model.Palabras;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Duration;

public class JuegoController {
    @FXML private Label lblPalabra;
    @FXML private TextField txtEntrada;
    @FXML private Label lblNivel;
    @FXML private Label lblTiempo;
    @FXML private Button btnValidar;

    private JuegoLogic juego;
    private Timeline timeline;
    private int tiempoRestante;

    @FXML
    public void initialize() {
        juego = new JuegoLogic();
        iniciarNivel();
        btnValidar.setOnAction(e -> validarEntrada());
        txtEntrada.setOnAction(e -> validarEntrada());
    }

    private void iniciarNivel() {
        txtEntrada.clear();
        String palabra = Palabras.palabraAleatoria();
        juego.setPalabraActual(palabra);
        lblPalabra.setText(palabra);
        lblNivel.setText("Nivel: " + juego.getNivel());
        tiempoRestante = juego.getTiempoPorNivel();
        lblTiempo.setText("Tiempo: " + tiempoRestante);

        if (timeline != null) timeline.stop();
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            tiempoRestante--;
            lblTiempo.setText("Tiempo: " + tiempoRestante);
            if (tiempoRestante <= 0) {
                timeline.stop();
                Platform.runLater(() -> {
                    mostrarMensajeFallo("⏰ Tiempo agotado");
                    finalizarPartida();
                });
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void validarEntrada() {
        if (timeline != null) timeline.stop();
        String entrada = txtEntrada.getText();

        if (juego.validar(entrada)) {
            boolean terminado = juego.avanzarNivel();
            Platform.runLater(() -> mostrarMensajeExito("✅ ¡Correcto! Nivel superado"));

            if (terminado) {
                Platform.runLater(() -> {
                    mostrarMensajeExito("🎉 ¡Felicitaciones! Has completado los " + Constantes.MAX_NIVELES + " niveles.");
                    finalizarPartida();
                });
            } else {
                iniciarNivel();
            }
        } else {
            Platform.runLater(() -> {
                mostrarMensajeFallo("❌ Incorrecto");
                finalizarPartida();
            });
        }
    }

    private void mostrarMensajeExito(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, msg);
        alert.setTitle("Éxito");
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    private void mostrarMensajeFallo(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR, msg);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    private void finalizarPartida() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Resumen de la partida");
        alert.setHeaderText("Juego terminado");
        alert.setContentText("Niveles completados: " + juego.getCompletados() +
                "\nTiempo restante: " + Math.max(0, tiempoRestante) +
                " segundos");
        alert.showAndWait();

        juego.reset();
        iniciarNivel();
    }
}



