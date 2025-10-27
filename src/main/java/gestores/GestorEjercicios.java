/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestores;

import excepciones.MyException;
import java.util.ArrayList;
import modelo.Ejercicio;
import modelo.Rutina;

/**
 *
 * @author Alumno
 */
public class GestorEjercicios {
    private ArrayList<Ejercicio> ejercicios = new ArrayList<>();
    private GestorRutinas gr;

    public GestorEjercicios(GestorRutinas gr) {
        this.gr = gr;
    }
    
    public void insertar (String nombreEjercicio, String grupoMuscular, String descripcion, int idRutina) throws MyException {
        Rutina r = gr.buscarPorId(idRutina);
        if (r == null) {
            throw new MyException("La rutina con id " + idRutina + " no existe. Asegurese de introducir una existente.");
        }
        
        Ejercicio nuevo = new Ejercicio(nombreEjercicio, grupoMuscular, descripcion, idRutina);
        ejercicios.add(nuevo);
        r.addEjercicio(nuevo);
    }
    
    public Ejercicio BuscarPorId(int id) {
        for (Ejercicio e: ejercicios) {
            if (e.getIdEjercicio() == id) {
                return e;
            }
        }
        return null;
    }
    
    public Ejercicio buscarPorNombre(String nombre) {
        for (Ejercicio e : ejercicios) {
            if (e.getNombreEjercicio().equalsIgnoreCase(nombre)) {
                return e;
            }
        }
        return null;
    }
    
    public void borrarPorId(int id) { 
        for (int i = 0; i < ejercicios.size(); i++) {
            if (ejercicios.get(i).getIdEjercicio() == id) {
                ejercicios.remove(i);
                break;
            }
        }
    }
    
    public void borrarPorNombre(String nombre) {
    for (int i = 0; i < ejercicios.size(); i++) {
        if (ejercicios.get(i).getNombreEjercicio().equalsIgnoreCase(nombre)) {
            ejercicios.remove(i);
            break; // salir después de eliminar el primero que coincida
        }
    }
}
    
    public ArrayList<Ejercicio> getEjercicios() {
        return ejercicios; //mejorar
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("EJERCICIOS\n");
        sb.append(String.format("%-6s %-15s %-15s %-20s %-6s\n",
                "ID", "Nombre", "GrupoMuscular","Descripcion", "Rutinas"));
        sb.append("---------------------------------------------------------------------------------------------\n");

        if (ejercicios.isEmpty()) {
            sb.append("No hay ejercicios registrados.\n");
        } else {
            for (Ejercicio e : ejercicios) {
                sb.append(e.toString()).append("\n");
            }
        }
    return sb.toString();
    }
    
}
