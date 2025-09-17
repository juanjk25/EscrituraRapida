package com.proyecto.EscrituraRapidaa.model;

public class JuegoLogic {
    private int nivel;
    private int tiempoPorNivel;
    private int completados;
    private String palabraActual;

    public JuegoLogic() {
        reset();
    }

    public void reset() {
        nivel = 1;
        tiempoPorNivel = Constantes.TIEMPO_INICIAL;
        completados = 0;
        palabraActual = "";
    }

    public void setPalabraActual(String palabra) {
        this.palabraActual = palabra;
    }

    public String getPalabraActual() {
        return palabraActual;
    }

    public int getNivel() {
        return nivel;
    }

    public int getTiempoPorNivel() {
        return tiempoPorNivel;
    }

    public int getCompletados() {
        return completados;
    }

    public boolean validar(String input) {
        return input.equals(palabraActual);
    }

    public boolean avanzarNivel() {
        completados++;
        if (completados % 5 == 0 && tiempoPorNivel > Constantes.MIN_TIEMPO) {
            tiempoPorNivel -= Constantes.REDUCCION_TIEMPO;
        }
        if (completados >= Constantes.MAX_NIVELES) {
            return true; // 🔧 se alcanzó el nivel máximo
        }
        nivel = completados + 1;
        return false;
    }
}
