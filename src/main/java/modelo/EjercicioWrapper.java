package modelo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "ejercicios")
public class EjercicioWrapper {

    private List<Ejercicio> lista;

    public EjercicioWrapper() {}

    public EjercicioWrapper(List<Ejercicio> lista) {
        this.lista = lista;
    }

    @XmlElement(name = "ejercicio")
    public List<Ejercicio> getLista() {
        return lista;
    }

    public void setLista(List<Ejercicio> lista) {
        this.lista = lista;
    }
}
