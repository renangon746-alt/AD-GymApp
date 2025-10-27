/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import excepciones.MyException;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Alumno
 */
public class Usuario implements Serializable {
    public static int contadorId = 1;
    private int idUsuario;
    private String nombre;
    private int edad;
    private double peso; //Peso medido en Kg
    private double altura; //Altura medida en cm
    private ArrayList<Rutina> rutinas = new ArrayList<>(); //Rutinas que tiene un usuario

    // Constructor vacío
    public Usuario() {
    }

    // Constructor con parámetros
    public Usuario(String nombre, int edad, double peso, double altura) throws MyException {
        this.idUsuario = contadorId++;
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
        this.altura = altura;
    }

    // Getters
    public int getIdUsuario() {
        return idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public double getPeso() {
        return peso;
    }

    public double getAltura() {
        return altura;
    }

    public ArrayList<Rutina> getRutinas() {
        return rutinas;
    }

    // Setters
    
    public void setId(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    // Métodos de utilidad
    public void addRutina(Rutina r) {
        rutinas.add(r);
    }

    public void removeRutina(Rutina r) {
        rutinas.remove(r);
    }

    @Override
    public String toString() {
        return String.format("%-10d %-15s %-8d %-10.2f %-10.2f %-10d",
            idUsuario, nombre, edad, peso, altura, rutinas.size());
    }
}
