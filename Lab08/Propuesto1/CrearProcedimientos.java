package mysqldemos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CrearProcedimientos {

    public static void main(String[] args) {
        // Cambia la URL, usuario y contraseña según tus necesidades
        String url = "jdbc:mysql://localhost:3306/empresadb";
        String user = "root";
        String password = "B0rnD_DAnT9";

        Connection con = null;
        Statement stmt = null;

        try {
            // Conectarse al servidor MySQL
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();

            // Crear el procedimiento almacenado InsertarProyecto
            String createProcedure = "CREATE PROCEDURE InsertarProyecto(" +
                    "    IN nombre VARCHAR(100)," +
                    "    IN fechaInicio DATE," +
                    "    IN fechaTermino DATE," +
                    "    IN ingenieroID INT," +
                    "    IN idDpto INT" +
                    ") " +
                    "BEGIN " +
                    "    INSERT INTO Proyectos (Nombre, Fec_Inicio, Fec_Termino, IngenieroID, IDDpto) " +
                    "    VALUES (nombre, fechaInicio, fechaTermino, ingenieroID, idDpto); " +
                    "END";

            stmt.executeUpdate(createProcedure);
            System.out.println("Procedimiento almacenado creado exitosamente");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

