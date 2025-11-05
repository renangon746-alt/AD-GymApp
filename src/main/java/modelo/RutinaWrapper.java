package modelo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "rutinas")
public class RutinaWrapper {

    private List<Rutina> lista;

    public RutinaWrapper() {}

    public RutinaWrapper(List<Rutina> lista) {
        this.lista = lista;
    }

    @XmlElement(name = "rutina")
    public List<Rutina> getLista() {
        return lista;
    }

    public void setLista(List<Rutina> lista) {
        this.lista = lista;
    }
}
