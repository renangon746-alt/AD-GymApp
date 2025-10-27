/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestores;

import excepciones.MyException;
import java.util.ArrayList;
import modelo.Usuario;

/**
 *
 * @author Alumno
 */
public class GestorUsuarios {
    private ArrayList<Usuario> usuarios = new ArrayList<>();

    public void insertar (String nombre, int edad, double peso, double altura) throws MyException { 
        Usuario nuevo = new Usuario(nombre, edad, peso, altura); 
        usuarios.add(nuevo);
    }
    
    public Usuario buscarPorId(int id) throws MyException {
        for (Usuario u : usuarios) {
            if (u.getIdUsuario() == id) 
                return u;
        }
        return null;
    }
    
    public Usuario buscarPorNombre(String nombre) throws MyException {
        for (Usuario u : usuarios) {
            if (u.getNombre().equalsIgnoreCase(nombre)) {
                return u;
            }
        }
        return null;
    }
    
    public ArrayList<Usuario> buscarPorEdad(int edad) throws MyException {
    ArrayList<Usuario> encontrados = new ArrayList<>();
    for (Usuario u : usuarios) {
        if (u.getEdad() == edad) {
            encontrados.add(u);
        }
    }
    return encontrados;
    }
    
    public String mostrarPorEdad(int edad) throws MyException {
    ArrayList<Usuario> encontrados = buscarPorEdad(edad);
    if (encontrados.isEmpty()) {
        return null;
    }

    StringBuilder sb = new StringBuilder();
    sb.append("Usuarios con edad ").append(edad).append(":\n\n");

    for (Usuario u : encontrados) {
        sb.append(u.toString()).append("\n");
    }

    return sb.toString();
}
    
    public void borrar(int id) throws MyException { 
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getIdUsuario() == id) {
                usuarios.remove(i);
                break; // salir después de eliminar
            }
        }
        /* Tambien se puede hacer asi --> usuarios.removeIf(u -> u.getIdUsuario() == id); */
    }
    
    public ArrayList<Usuario> getUsuarios() {
        return usuarios; //mejorar
    }
    
    public boolean modificarUsuario(int id, String nuevoNombre, int nuevaEdad, double nuevoPeso, double nuevaAltura) {
        for (Usuario u : usuarios) {
            if (u.getIdUsuario() == id) {
                u.setNombre(nuevoNombre);
                u.setEdad(nuevaEdad);
                u.setPeso(nuevoPeso);
                u.setAltura(nuevaAltura);
                return true;
            }
        }
        return false;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("USUARIOS\n");
        sb.append(String.format("%-10s %-15s %-8s %-10s %-10s %-10s\n",
                "ID", "Nombre", "Edad", "Peso", "Altura", "Rutinas"));
        sb.append("-------------------------------------------------------------\n");

        if (usuarios.isEmpty()) {
            sb.append("No hay usuarios registrados.\n");
        } else {
            for (Usuario u : usuarios) {
                sb.append(u.toString()).append("\n");
            }
        }
    return sb.toString();
    }
    
}
