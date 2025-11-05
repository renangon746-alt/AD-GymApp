package modelo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "usuarios")
public class UsuarioWrapper {

    private List<Usuario> lista;

    public UsuarioWrapper() {}

    public UsuarioWrapper(List<Usuario> lista) {
        this.lista = lista;
    }

    @XmlElement(name = "usuario")
    public List<Usuario> getLista() {
        return lista;
    }

    public void setLista(List<Usuario> lista) {
        this.lista = lista;
    }
}
