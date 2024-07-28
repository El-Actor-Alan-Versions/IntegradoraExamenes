package mx.edu.utez.integradiratjuans.model;

import java.util.ArrayList;
import java.util.List;

public class Opcion {
    private String texto;
    private boolean correcta;

    public Opcion(String texto, boolean correcta) {
        this.texto = texto;
        this.correcta = correcta;
    }

    // Getters y setters
    public String getTexto() { return texto; }
    public boolean isCorrecta() { return correcta; }


}
