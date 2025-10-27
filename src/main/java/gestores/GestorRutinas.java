/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestores;

import excepciones.MyException;
import java.util.ArrayList;
import modelo.Rutina;
import modelo.Usuario;

/**
 *
 * @author Alumno
 */
public class GestorRutinas {
    private ArrayList<Rutina> rutinas;
    private GestorUsuarios gu;

    public GestorRutinas(GestorUsuarios gu) {
        this.gu = gu;
        this.rutinas = new ArrayList<>();
    }

    public void insertar(String nombre, int idUsuario) throws MyException {
        Usuario usu = gu.buscarPorId(idUsuario);
        if (usu == null) {
            throw new MyException("El usuario con id " + idUsuario + " no existe. Asegurese de introducir uno existente.");
        }
        Rutina nueva = new Rutina(nombre, idUsuario);
        rutinas.add(nueva);
        usu.addRutina(nueva);
        
    }
    
    public void ComporobarIdUsu() {
        /*if (idUsu <= Usuario.contadorId && idUsu > 0) {
            
        }*/
    }

    public Rutina buscarPorId(int id) {
        for (Rutina r : rutinas) {
            if (r.getIdRutina() == id) {
                return r;
            }
        }
        return null;
    }

    public void borrar(int id) {
        for (int i = 0; i < rutinas.size(); i++) {
            if (rutinas.get(i).getIdRutina() == id) {
                rutinas.remove(i);
                break;
            }
        }
        // También se podría hacer con -> rutinas.removeIf(r -> r.getIdRutina() == id);
    }

    public ArrayList<Rutina> getRutinas() {
        return rutinas;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("RUTINAS\n");
        sb.append(String.format("%-6s %-15s %-6s %-8s\n",
                "ID", "Nombre", "IDUsu", "Ejercicios"));
        sb.append("-------------------------------------------------------------\n");

        if (rutinas.isEmpty()) {
            sb.append("No hay rutinas registradas.\n");
        } else {
            for (Rutina r : rutinas) {
                sb.append(r.toString()).append("\n");
            }
        }
    return sb.toString();
    } 
}
