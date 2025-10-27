package modelo;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
//Añadio dependencias al pom.xml
@XmlRootElement(name = "gimnasio")
public class GimnasioXML {

    private ArrayList<Usuario> usuarios;
    private ArrayList<Rutina> rutinas;
    private ArrayList<Ejercicio> ejercicios;

    public GimnasioXML() {
        usuarios = new ArrayList<>();
        rutinas = new ArrayList<>();
        ejercicios = new ArrayList<>();
    }

    @XmlElement(name = "usuario")
    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    @XmlElement(name = "rutina")
    public ArrayList<Rutina> getRutinas() {
        return rutinas;
    }

    public void setRutinas(ArrayList<Rutina> rutinas) {
        this.rutinas = rutinas;
    }

    @XmlElement(name = "ejercicio")
    public ArrayList<Ejercicio> getEjercicios() {
        return ejercicios;
    }

    public void setEjercicios(ArrayList<Ejercicio> ejercicios) {
        this.ejercicios = ejercicios;
    }
}
