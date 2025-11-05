package gestores;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.Usuario;
import modelo.Rutina;
import modelo.Ejercicio;

/**
 * Clase encargada de gestionar la Base de Datos SQLite.
 * Esta clase permite importar y exportar todos los objetos del sistema
 * (Usuarios, Rutinas y Ejercicios) a un único fichero gimnasio.db
 */
public class GestorBD {

    private static final String URL = "jdbc:sqlite:gimnasio.db"; // Nombre del fichero SQLite

    /**
     * Permite establecer una conexión con la BD.
     */
    private Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    /**
     * Crea las tablas si no existen previamente.
     */
    public void crearTablas() {
        String sqlUsuarios = """
            CREATE TABLE IF NOT EXISTS USUARIO (
            id INTEGER PRIMARY KEY,
            nombre TEXT NOT NULL,
            edad INTEGER,
            peso REAL,
            altura REAL
            );
        """;

        String sqlRutinas = """
            CREATE TABLE IF NOT EXISTS RUTINA (
            id INTEGER PRIMARY KEY,
            nombre TEXT NOT NULL,
            idUsuario INTEGER,
            FOREIGN KEY(idUsuario) REFERENCES USUARIO(id)
            );
        """;

        String sqlEjercicios = """
            CREATE TABLE IF NOT EXISTS EJERCICIO (
            id INTEGER PRIMARY KEY,
            nombre TEXT NOT NULL,
            grupoMuscular TEXT,
            descripcion TEXT,
            idRutina INTEGER,
            FOREIGN KEY(idRutina) REFERENCES RUTINA(id)
            );
        """;

        try (Connection conn = conectar(); Statement stmt = conn.createStatement()) {
            stmt.execute(sqlUsuarios);
            stmt.execute(sqlRutinas);
            stmt.execute(sqlEjercicios);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al crear tablas: " + e.getMessage());
        }
    }

    /**
     * Elimina todos los registros de las tablas antes de exportar.
     */
    private void limpiarBD() {
        try (Connection conn = conectar(); Statement stmt = conn.createStatement()) {
            stmt.execute("DELETE FROM EJERCICIO;");
            stmt.execute("DELETE FROM RUTINA;");
            stmt.execute("DELETE FROM USUARIO;");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error limpiando BD: " + e.getMessage());
        }
    }

    /**
     * Exporta todos los objetos de los gestores a SQLite.
     */
    public void exportarTodo(GestorUsuarios gu, GestorRutinas gr, GestorEjercicios ge) {
        crearTablas();
        limpiarBD();

        try (Connection conn = conectar()) {

            conn.setAutoCommit(false);

            // Exportar usuarios
            PreparedStatement psUser = conn.prepareStatement(
                    "INSERT INTO USUARIO VALUES(?, ?, ?, ?, ?)");

            for (Usuario u : gu.getUsuarios()) {
                psUser.setInt(1, u.getIdUsuario());
                psUser.setString(2, u.getNombre());
                psUser.setInt(3, u.getEdad());
                psUser.setDouble(4, u.getPeso());
                psUser.setDouble(5, u.getAltura());
                psUser.executeUpdate();
            }

            // Exportar rutinas
            PreparedStatement psRut = conn.prepareStatement(
                    "INSERT INTO RUTINA VALUES(?, ?, ?)");

            for (Rutina r : gr.getRutinas()) {
                psRut.setInt(1, r.getIdRutina());
                psRut.setString(2, r.getNombreRutina());
                psRut.setInt(3, r.getIdUsuario());
                psRut.executeUpdate();
            }

            // Exportar ejercicios
            PreparedStatement psEj = conn.prepareStatement(
                    "INSERT INTO EJERCICIO VALUES(?, ?, ?, ?, ?)");

            for (Ejercicio e : ge.getEjercicios()) {
                psEj.setInt(1, e.getIdEjercicio());
                psEj.setString(2, e.getNombreEjercicio());
                psEj.setString(3, e.getGrupoMuscular());
                psEj.setString(4, e.getDescripcion());
                psEj.setInt(5, e.getIdRutina());
                psEj.executeUpdate();
            }
            conn.commit();
            JOptionPane.showMessageDialog(null, "Exportación a SQLite realizada correctamente.");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al exportar: " + e.getMessage());
        }
    }

    /**
     * Importa todos los datos desde SQLite a memoria en los gestores.
     */
    public void importarTodo(GestorUsuarios gu, GestorRutinas gr, GestorEjercicios ge) {

        try (Connection conn = conectar(); Statement stmt = conn.createStatement()) {

            // Limpiamos gestores en memoria
            gu.getUsuarios().clear();
            gr.getRutinas().clear();
            ge.getEjercicios().clear();

            // Importar usuarios
            ResultSet rsUser = stmt.executeQuery("SELECT * FROM USUARIO;");
            while (rsUser.next()) {
                gu.insertar(rsUser.getString("nombre"),
                        rsUser.getInt("edad"),
                        rsUser.getDouble("peso"),
                        rsUser.getDouble("altura"));
            }

            // Importar rutinas
            ResultSet rsRut = stmt.executeQuery("SELECT * FROM RUTINA;");
            while (rsRut.next()) {
                gr.insertar(
                    rsRut.getString("nombre"),
                    rsRut.getInt("idUsuario")
                );
            }

            // Importar ejercicios
            ResultSet rsEj = stmt.executeQuery("SELECT * FROM EJERCICIO;");
            while (rsEj.next()) {
                ge.insertar(
                    rsEj.getString("nombre"),
                    rsEj.getString("grupoMuscular"),
                    rsEj.getString("descripcion"),
                    rsEj.getInt("idRutina")
                );
            }

            JOptionPane.showMessageDialog(null, "Importación desde SQLite realizada correctamente.");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al importar: " + e.getMessage());
        }
    }
}
