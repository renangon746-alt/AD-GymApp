/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;

/**
 *
 * @author Alumno
 */
public class Ejercicio implements Serializable {
    private static int contadorId = 1;
    private int idEjercicio;
    private String nombreEjercicio;
    private String grupoMuscular;
    private String descripcion;
    private int idRutina; // Relación con Rutina

    // Constructor vacío
    public Ejercicio() {
    }

    // Constructor con parámetros
    public Ejercicio(String nombreEjercicio, String grupoMuscular, String descripcion, int idRutina) {
        this.idEjercicio = contadorId++;
        this.nombreEjercicio = nombreEjercicio;
        this.grupoMuscular = grupoMuscular;
        this.descripcion = descripcion;
        this.idRutina = idRutina;
    }

    // Getters y setters
    public int getIdEjercicio() {
        return idEjercicio;
    }

    public void setIdEjercicio(int idEjercicio) {
        this.idEjercicio = idEjercicio;
    }

    public String getNombreEjercicio() {
        return nombreEjercicio;
    }

    public void setNombreEjercicio(String nombreEjercicio) {
        this.nombreEjercicio = nombreEjercicio;
    }

    public String getGrupoMuscular() {
        return grupoMuscular;
    }

    public void setGrupoMuscular(String grupoMuscular) {
        this.grupoMuscular = grupoMuscular;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdRutina() {
        return idRutina;
    }

    public void setIdRutina(int idRutina) {
        this.idRutina = idRutina;
    }

    @Override
    public String toString() {
        return String.format("%-6d %-15s %-15s %-20s %-6d",
                idEjercicio, nombreEjercicio, grupoMuscular, descripcion, idRutina);
    }
}
