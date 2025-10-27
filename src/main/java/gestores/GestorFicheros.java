package gestores;

import excepciones.MyException;
import java.io.*;
import java.util.ArrayList;
import modelo.Usuario;
import modelo.Rutina;
import modelo.Ejercicio;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.File;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

public class GestorFicheros {
    
    // Archivos de texto
    private static final String ARCHIVO_USUARIOS_TXT = "usuarios.txt";
    private static final String ARCHIVO_RUTINAS_TXT = "rutinas.txt";
    private static final String ARCHIVO_EJERCICIOS_TXT = "ejercicios.txt";
    
    // Archivos binarios
    private static final String ARCHIVO_USUARIOS_DAT = "usuarios.dat";
    private static final String ARCHIVO_RUTINAS_DAT = "rutinas.dat";
    private static final String ARCHIVO_EJERCICIOS_DAT = "ejercicios.dat";
    
    // Archivos XML 
    private static final String ARCHIVO_USUARIOS_XML = "usuarios.xml";
    private static final String ARCHIVO_RUTINAS_XML = "rutinas.xml";
    private static final String ARCHIVO_EJERCICIOS_XML = "ejercicios.xml";

    public static void exportarUsuariosTexto(ArrayList<Usuario> usuarios) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_USUARIOS_TXT))) {
            for (Usuario u : usuarios) {
                bw.write(u.getNombre() + ";" + u.getEdad() + ";" + u.getPeso() + ";" + u.getAltura());
                bw.newLine();
            }
            System.out.println("Usuarios exportados correctamente a " + ARCHIVO_USUARIOS_TXT);
        } catch (IOException e) {
            System.err.println("Error al exportar usuarios: " + e.getMessage());
        }
    }

    public static ArrayList<Usuario> importarUsuariosTexto() throws MyException {
        ArrayList<Usuario> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_USUARIOS_TXT))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 4) {
                    String nombre = partes[0];
                    int edad = Integer.parseInt(partes[1]);
                    double peso = Double.parseDouble(partes[2]);
                    double altura = Double.parseDouble(partes[3]);
                    lista.add(new Usuario(nombre, edad, peso, altura));
                }
            }
            System.out.println("Usuarios importados correctamente desde " + ARCHIVO_USUARIOS_TXT);
        } catch (FileNotFoundException e) {
            System.err.println("No se encontró el archivo de usuarios (" + ARCHIVO_USUARIOS_TXT + ")");
        } catch (IOException e) {
            System.err.println("Error al leer usuarios: " + e.getMessage());
        }
        return lista;
    }

    public static void exportarRutinasTexto(ArrayList<Rutina> rutinas) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_RUTINAS_TXT))) {
            for (Rutina r : rutinas) {
                bw.write(r.getNombreRutina() + ";" + r.getIdUsuario());
                bw.newLine();
            }
            System.out.println("Rutinas exportadas correctamente a " + ARCHIVO_RUTINAS_TXT);
        } catch (IOException e) {
            System.err.println("Error al exportar rutinas: " + e.getMessage());
        }
    }

    public static ArrayList<Rutina> importarRutinasTexto() {
        ArrayList<Rutina> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_RUTINAS_TXT))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 2) {
                    String nombre = partes[0];
                    int idUsuario = Integer.parseInt(partes[1]);
                    lista.add(new Rutina(nombre, idUsuario));
                }
            }
            System.out.println("Rutinas importadas correctamente desde " + ARCHIVO_RUTINAS_TXT);
        } catch (FileNotFoundException e) {
            System.err.println("No se encontró el archivo de rutinas (" + ARCHIVO_RUTINAS_TXT + ")");
        } catch (IOException e) {
            System.err.println("Error al leer rutinas: " + e.getMessage());
        }
        return lista;
    }

    public static void exportarEjerciciosTexto(ArrayList<Ejercicio> ejercicios) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_EJERCICIOS_TXT))) {
            for (Ejercicio e : ejercicios) {
                bw.write(e.getNombreEjercicio() + ";" + e.getGrupoMuscular() + ";" +
                         e.getDescripcion() + ";" + e.getIdRutina());
                bw.newLine();
            }
            System.out.println("Ejercicios exportados correctamente a " + ARCHIVO_EJERCICIOS_TXT);
        } catch (IOException e) {
            System.err.println("Error al exportar ejercicios: " + e.getMessage());
        }
    }

    public static ArrayList<Ejercicio> importarEjerciciosTexto() {
        ArrayList<Ejercicio> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_EJERCICIOS_TXT))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 4) {
                    String nombre = partes[0];
                    String grupo = partes[1];
                    String desc = partes[2];
                    int idRutina = Integer.parseInt(partes[3]);
                    lista.add(new Ejercicio(nombre, grupo, desc, idRutina));
                }
            }
            System.out.println("Ejercicios importados correctamente desde " + ARCHIVO_EJERCICIOS_TXT);
        } catch (FileNotFoundException e) {
            System.err.println("No se encontró el archivo de ejercicios (" + ARCHIVO_EJERCICIOS_TXT + ")");
        } catch (IOException e) {
            System.err.println("Error al leer ejercicios: " + e.getMessage());
        }
        return lista;
    }

    //      IMPORTAR TEXTO
    public static void importarTodoTexto(GestorUsuarios gUsuarios, GestorRutinas gRutinas, GestorEjercicios gEjercicios) throws MyException {
        gUsuarios.getUsuarios().clear();
        gRutinas.getRutinas().clear();
        gEjercicios.getEjercicios().clear();

        gUsuarios.getUsuarios().addAll(importarUsuariosTexto());
        gRutinas.getRutinas().addAll(importarRutinasTexto());
        gEjercicios.getEjercicios().addAll(importarEjerciciosTexto());

        System.out.println("Importación completa de todos los datos.");
    }

    //      EXPORTAR TEXTO
    public static void exportarTodoTexto(GestorUsuarios gUsuarios, GestorRutinas gRutinas, GestorEjercicios gEjercicios) {
        exportarUsuariosTexto(gUsuarios.getUsuarios());
        exportarRutinasTexto(gRutinas.getRutinas());
        exportarEjerciciosTexto(gEjercicios.getEjercicios());
        System.out.println("Exportación completa de todos los datos.");
    }
    
    //      EXPORTAR BINARIO

    public static void exportarBinario(ArrayList<Usuario> usuarios,
                                       ArrayList<Rutina> rutinas,
                                       ArrayList<Ejercicio> ejercicios) {
        try {
            ObjectOutputStream oosUsuarios = new ObjectOutputStream(new FileOutputStream(ARCHIVO_USUARIOS_DAT));
            oosUsuarios.writeObject(usuarios);
            oosUsuarios.close();

            ObjectOutputStream oosRutinas = new ObjectOutputStream(new FileOutputStream(ARCHIVO_RUTINAS_DAT));
            oosRutinas.writeObject(rutinas);
            oosRutinas.close();

            ObjectOutputStream oosEjercicios = new ObjectOutputStream(new FileOutputStream(ARCHIVO_EJERCICIOS_DAT));
            oosEjercicios.writeObject(ejercicios);
            oosEjercicios.close();

            System.out.println("✅ Datos exportados correctamente en formato binario.");

        } catch (IOException e) {
            System.err.println("❌ Error al exportar en binario: " + e.getMessage());
        }
    }

    //      IMPORTAR BINARIO
    @SuppressWarnings("unchecked")
    public static ArrayList<Usuario> importarUsuariosBinario() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO_USUARIOS_DAT))) {
            return (ArrayList<Usuario>) ois.readObject();
        } catch (Exception e) {
            System.err.println("Error al importar usuarios binarios: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<Rutina> importarRutinasBinario() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO_RUTINAS_DAT))) {
            return (ArrayList<Rutina>) ois.readObject();
        } catch (Exception e) {
            System.err.println("Error al importar rutinas binarios: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<Ejercicio> importarEjerciciosBinario() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO_EJERCICIOS_DAT))) {
            return (ArrayList<Ejercicio>) ois.readObject();
        } catch (Exception e) {
            System.err.println("Error al importar ejercicios binarios: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
// -------------------- METODOS PARA EXPORTAR XML (DOM) --------------------
    public void exportarUsuariosXML(ArrayList<Usuario> usuarios) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.newDocument();

            Element root = doc.createElement("usuarios");
            doc.appendChild(root);

            for (Usuario u : usuarios) {
                Element usuarioElem = doc.createElement("usuario");
                root.appendChild(usuarioElem);

                Element id = doc.createElement("idUsuario");
                id.setTextContent(String.valueOf(u.getIdUsuario()));
                usuarioElem.appendChild(id);

                Element nombre = doc.createElement("nombre");
                nombre.setTextContent(u.getNombre());
                usuarioElem.appendChild(nombre);

                Element edad = doc.createElement("edad");
                edad.setTextContent(String.valueOf(u.getEdad()));
                usuarioElem.appendChild(edad);

                Element peso = doc.createElement("peso");
                peso.setTextContent(String.valueOf(u.getPeso()));
                usuarioElem.appendChild(peso);

                Element altura = doc.createElement("altura");
                altura.setTextContent(String.valueOf(u.getAltura()));
                usuarioElem.appendChild(altura);
            }

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer t = tf.newTransformer();
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.transform(new DOMSource(doc), new StreamResult(new File(ARCHIVO_USUARIOS_XML)));

            System.out.println("Usuarios exportados correctamente a " + ARCHIVO_USUARIOS_XML);

        } catch (Exception e) {
            System.err.println("Error exportando XML: " + e.getMessage());
        }
    }
    
    public void exportarRutinasXML(ArrayList<Rutina> rutinas) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.newDocument();

            Element root = doc.createElement("rutinas");
            doc.appendChild(root);

            for (Rutina r : rutinas) {
                Element rutinaElem = doc.createElement("rutina");
                root.appendChild(rutinaElem);

                Element id = doc.createElement("idRutina");
                id.setTextContent(String.valueOf(r.getIdRutina()));
                rutinaElem.appendChild(id);

                Element nombre = doc.createElement("nombreRutina");
                nombre.setTextContent(r.getNombreRutina());
                rutinaElem.appendChild(nombre);

                Element idUsuario = doc.createElement("idUsuario");
                idUsuario.setTextContent(String.valueOf(r.getIdUsuario()));
                rutinaElem.appendChild(idUsuario);
            }

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer t = tf.newTransformer();
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.transform(new DOMSource(doc), new StreamResult(new File(ARCHIVO_RUTINAS_XML)));

            System.out.println("Rutinas exportadas correctamente a " + ARCHIVO_RUTINAS_XML);

        } catch (Exception e) {
            System.err.println("Error exportando rutinas XML: " + e.getMessage());
        }
    }

    public void exportarEjerciciosXML(ArrayList<Ejercicio> ejercicios) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.newDocument();

            Element root = doc.createElement("ejercicios");
            doc.appendChild(root);

            for (Ejercicio e : ejercicios) {
                Element ejercicioElem = doc.createElement("ejercicio");
                root.appendChild(ejercicioElem);

                Element nombre = doc.createElement("nombreEjercicio");
                nombre.setTextContent(e.getNombreEjercicio());
                ejercicioElem.appendChild(nombre);

                Element grupo = doc.createElement("grupoMuscular");
                grupo.setTextContent(e.getGrupoMuscular());
                ejercicioElem.appendChild(grupo);

                Element desc = doc.createElement("descripcion");
                desc.setTextContent(e.getDescripcion());
                ejercicioElem.appendChild(desc);

                Element idRutina = doc.createElement("idRutina");
                idRutina.setTextContent(String.valueOf(e.getIdRutina()));
                ejercicioElem.appendChild(idRutina);
            }

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer t = tf.newTransformer();
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.transform(new DOMSource(doc), new StreamResult(new File(ARCHIVO_EJERCICIOS_XML)));

            System.out.println("Ejercicios exportados correctamente a " + ARCHIVO_EJERCICIOS_XML);

        } catch (Exception e) {
            System.err.println("Error exportando ejercicios XML: " + e.getMessage());
        }
    }
// -------------------- EXPORTAR TODO XML (DOM) --------------------
    public void exportarTodoXML(GestorUsuarios gu, GestorRutinas gr, GestorEjercicios ge) {
        exportarUsuariosXML(gu.getUsuarios());
        exportarRutinasXML(gr.getRutinas());
        exportarEjerciciosXML(ge.getEjercicios());
        System.out.println("Exportación completa a XML realizada correctamente.");
    }


    
// -------------------- METODOS PARA IMPORTAR XML (DOM) --------------------
    public ArrayList<Usuario> importarUsuariosXML() {
        ArrayList<Usuario> lista = new ArrayList<>();
        try {
            File archivo = new File(ARCHIVO_USUARIOS_XML);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(archivo);
            doc.getDocumentElement().normalize();

            NodeList nodos = doc.getElementsByTagName("usuario");
            for (int i = 0; i < nodos.getLength(); i++) {
                Node nodo = nodos.item(i);
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element e = (Element) nodo;
                    int idUsuario = Integer.parseInt(e.getElementsByTagName("idUsuario").item(0).getTextContent());
                    String nombre = e.getElementsByTagName("nombre").item(0).getTextContent();
                    int edad = Integer.parseInt(e.getElementsByTagName("edad").item(0).getTextContent());
                    double peso = Double.parseDouble(e.getElementsByTagName("peso").item(0).getTextContent());
                    double altura = Double.parseDouble(e.getElementsByTagName("altura").item(0).getTextContent());
                    lista.add(new Usuario(nombre, edad, peso, altura));
                }
            }
            System.out.println("Usuarios importados correctamente desde " + ARCHIVO_USUARIOS_XML);
        } catch (Exception e) {
            System.err.println("Error importando usuarios XML: " + e.getMessage());
        }
        return lista;
    }

    public ArrayList<Rutina> importarRutinasXML() {
        ArrayList<Rutina> lista = new ArrayList<>();
        try {
            File archivo = new File(ARCHIVO_RUTINAS_XML);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(archivo);
            doc.getDocumentElement().normalize();

            NodeList nodos = doc.getElementsByTagName("rutina");
            for (int i = 0; i < nodos.getLength(); i++) {
                Node nodo = nodos.item(i);
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element e = (Element) nodo;
                    int idUsuario = Integer.parseInt(e.getElementsByTagName("idUsuario").item(0).getTextContent());
                    String nombreRutina = e.getElementsByTagName("nombreRutina").item(0).getTextContent();
                    lista.add(new Rutina(nombreRutina, idUsuario));
                }
            }
            System.out.println("Rutinas importadas correctamente desde " + ARCHIVO_RUTINAS_XML);
        } catch (Exception e) {
            System.err.println("Error importando rutinas XML: " + e.getMessage());
        }
        return lista;
    }

    public ArrayList<Ejercicio> importarEjerciciosXML() {
        ArrayList<Ejercicio> lista = new ArrayList<>();
        try {
            File archivo = new File(ARCHIVO_EJERCICIOS_XML);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(archivo);
            doc.getDocumentElement().normalize();

            NodeList nodos = doc.getElementsByTagName("ejercicio");
            for (int i = 0; i < nodos.getLength(); i++) {
                Node nodo = nodos.item(i);
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element e = (Element) nodo;
                    String nombre = e.getElementsByTagName("nombreEjercicio").item(0).getTextContent();
                    String grupo = e.getElementsByTagName("grupoMuscular").item(0).getTextContent();
                    String desc = e.getElementsByTagName("descripcion").item(0).getTextContent();
                    int idRutina = Integer.parseInt(e.getElementsByTagName("idRutina").item(0).getTextContent());
                    lista.add(new Ejercicio(nombre, grupo, desc, idRutina));
                }
            }
            System.out.println("Ejercicios importados correctamente desde " + ARCHIVO_EJERCICIOS_XML);
        } catch (Exception e) {
            System.err.println("Error importando ejercicios XML: " + e.getMessage());
        }
        return lista;
    }

// -------------------- IMPORTAR TODO XML (DOM) --------------------
    public void importarTodoXML(GestorUsuarios gu, GestorRutinas gr, GestorEjercicios ge) {
        gu.getUsuarios().clear();
        gu.getUsuarios().addAll(importarUsuariosXML());

        gr.getRutinas().clear();
        gr.getRutinas().addAll(importarRutinasXML());

        ge.getEjercicios().clear();
        ge.getEjercicios().addAll(importarEjerciciosXML());

        System.out.println("Importación completa de todos los datos desde XML (DOM).");
    }

// -------------------- IMPORTAR TODO XML (SAX) --------------------
    public ArrayList<Usuario> importarSAX(String rutaArchivo) {
        ArrayList<Usuario> usuarios = new ArrayList<>();

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            SAXHandler handler = new SAXHandler();
            saxParser.parse(new File(rutaArchivo), handler);

            usuarios = handler.getListaUsuarios();
            System.out.println("Usuarios importados con SAX: " + usuarios.size());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return usuarios;
    }
}
