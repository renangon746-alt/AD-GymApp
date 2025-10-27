package modelo;

import java.io.Serializable;
import java.util.ArrayList;

public class Rutina implements Serializable {
    private static int contadorId = 1;
    private int idRutina;
    private String nombreRutina;
    private int idUsuario; // Relación con Usuario
    private ArrayList<Ejercicio> ejercicios = new ArrayList<>(); // Colección de ejercicios de la rutina

    // 🔸 Constructor vacío (inicializa la lista para evitar NullPointerException)
    public Rutina() {
        this.idRutina = contadorId++;
    }

    // 🔸 Constructor con parámetros
    public Rutina(String nombreRutina, int idUsuario) {
        this.idRutina = contadorId++;
        this.nombreRutina = nombreRutina;
        this.idUsuario = idUsuario;
    }

    // --- Getters y setters ---
    public int getIdRutina() {
        return idRutina;
    }

    public void setIdRutina(int idRutina) {
        this.idRutina = idRutina;
    }

    public String getNombreRutina() {
        return nombreRutina;
    }

    public void setNombreRutina(String nombreRutina) {
        this.nombreRutina = nombreRutina;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public ArrayList<Ejercicio> getEjercicios() {
        // Si alguien la llama sin haberse inicializado, la crea automáticamente
        if (ejercicios == null) {
            ejercicios = new ArrayList<>();
        }
        return ejercicios;
    }

    // --- Métodos de utilidad ---
    public void addEjercicio(Ejercicio e) {
        if (ejercicios == null) {
            ejercicios = new ArrayList<>();
        }
        ejercicios.add(e);
    }

    public void removeEjercicio(Ejercicio e) {
        if (ejercicios != null) {
            ejercicios.remove(e);
        }
    }

    @Override
    public String toString() {
        return String.format("%-6d %-15s %-6d %-8d",
                idRutina, nombreRutina, idUsuario, ejercicios.size());
    }
}
