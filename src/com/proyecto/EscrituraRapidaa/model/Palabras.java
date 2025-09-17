package com.proyecto.EscrituraRapidaa.model;

import java.util.List;
import java.util.Random;

public class Palabras {
    private static final List<String> LISTA = List.of(
            "Hola mundo",
            "JavaFX es genial",
            "Programación orientada a eventos",
            "Escritura rápida",
            "Desarrollo ágil",
            "Nivel de dificultad",
            "Interfaz gráfica",
            "Buen trabajo",
            "Practica diaria",
            "Tiempo límite",
            "Java",
            "Univalle"
    );

    public static String palabraAleatoria() {
        Random random = new Random();
        return LISTA.get(random.nextInt(LISTA.size()));
    }
}
