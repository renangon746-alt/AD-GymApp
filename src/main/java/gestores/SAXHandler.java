/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestores;

import modelo.Usuario;
import modelo.Rutina;
import modelo.Ejercicio;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.util.ArrayList;

public class SAXHandler extends DefaultHandler {

    private ArrayList<Usuario> listaUsuarios = new ArrayList<>();
    private Usuario usuarioActual;
    private Rutina rutinaActual;
    private Ejercicio ejercicioActual;
    private StringBuilder contenido = new StringBuilder();

public ArrayList<Usuario> getListaUsuarios() {
            return listaUsuarios;
        }

        @Override
        public void startDocument() throws SAXException {
            System.out.println(" Inicio de la lectura del XML (SAX)");
        }

        @Override
        public void endDocument() throws SAXException {
            System.out.println(" Fin del documento XML");
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            contenido.setLength(0);

            switch (qName.toLowerCase()) {
                case "usuario":
                    usuarioActual = new Usuario();
                    String idUsuario = attributes.getValue("id");
                    if (idUsuario != null) {
                        usuarioActual.setId(Integer.parseInt(idUsuario));
                    }
                    break;

                case "rutina":
                    rutinaActual = new Rutina();
                    String idRutina = attributes.getValue("id");
                    if (idRutina != null) {
                        rutinaActual.setIdRutina(Integer.parseInt(idRutina));
                    }
                    break;

                case "ejercicio":
                    ejercicioActual = new Ejercicio();
                    String idEjercicio = attributes.getValue("id");
                    if (idEjercicio != null) {
                        ejercicioActual.setIdEjercicio(Integer.parseInt(idEjercicio));
                    }
                    break;
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            contenido.append(ch, start, length);
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            String texto = contenido.toString().trim();

            switch (qName.toLowerCase()) {
                // -------------------- USUARIO --------------------
                case "nombre":
                    if (ejercicioActual != null) {
                        ejercicioActual.setNombreEjercicio(texto);
                    } else if (rutinaActual != null) {
                        rutinaActual.setNombreRutina(texto);
                    } else if (usuarioActual != null) {
                        usuarioActual.setNombre(texto);
                    }
                    break;

                case "edad":
                    if (usuarioActual != null) {
                        try {
                            usuarioActual.setEdad(Integer.parseInt(texto));
                        } catch (NumberFormatException e) {
                            usuarioActual.setEdad(0);
                        }
                    }
                    break;

                case "peso":
                    if (usuarioActual != null) {
                        try {
                            usuarioActual.setPeso(Double.parseDouble(texto));
                        } catch (NumberFormatException e) {
                            usuarioActual.setPeso(0);
                        }
                    }
                    break;

                case "altura":
                    if (usuarioActual != null) {
                        try {
                            usuarioActual.setAltura(Double.parseDouble(texto));
                        } catch (NumberFormatException e) {
                            usuarioActual.setAltura(0);
                        }
                    }
                    break;

                // -------------------- RUTINA --------------------
                case "idusuario":
                    if (rutinaActual != null) {
                        try {
                            rutinaActual.setIdUsuario(Integer.parseInt(texto));
                        } catch (NumberFormatException e) {
                            rutinaActual.setIdUsuario(0);
                        }
                    }
                    break;

                // -------------------- EJERCICIO --------------------
                case "grupomuscular":
                    if (ejercicioActual != null) {
                        ejercicioActual.setGrupoMuscular(texto);
                    }
                    break;

                case "descripcion":
                    if (ejercicioActual != null) {
                        ejercicioActual.setDescripcion(texto);
                    }
                    break;

                case "idrutina":
                    if (ejercicioActual != null) {
                        try {
                            ejercicioActual.setIdRutina(Integer.parseInt(texto));
                        } catch (NumberFormatException e) {
                            ejercicioActual.setIdRutina(0);
                        }
                    }
                    break;

                // -------------------- CIERRES --------------------
                case "ejercicio":
                    if (rutinaActual != null && ejercicioActual != null) {
                        rutinaActual.addEjercicio(ejercicioActual);
                        ejercicioActual = null;
                    }
                    break;

                case "rutina":
                    if (usuarioActual != null && rutinaActual != null) {
                        usuarioActual.addRutina(rutinaActual);
                        rutinaActual = null;
                    }
                    break;

                case "usuario":
                    if (usuarioActual != null) {
                        listaUsuarios.add(usuarioActual);
                        usuarioActual = null;
                    }
                    break;
            }
        }
    }

