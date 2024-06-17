package mysqldemos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Proyecto {

    public static void main(String[] args) throws SQLException {

        // Cambia la URL, usuario y contraseña según tus necesidades
        String url = "jdbc:mysql://localhost:3306/";
        String user = "root";
        String password = "B0rnD_DAnT9";

        Connection con = null;
        Statement stmt = null;

        try {
            // Conectarse al servidor MySQL
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            
            // Crear instancia de la clase DepartamentoPS y agregar un departamento
            DepartamentoPS departamentoPS = new DepartamentoPS();
            departamentoPS.insertarDepartamento("Finanzas", "123456789", "987654321");

            // Crear instancia de la clase ProyectoAlm y agregar un proyecto
            ProyectoAlm proyectoAlm = new ProyectoAlm();
            proyectoAlm.insertarProyecto("Proyecto C", "2024-03-01", "2024-12-31", 1, 1);

            // Crear instancia de la clase GestorTranssacciones y agregar un nuevo departamento, ingeniero y proyecto
            GestorTranssacciones gestorTranssacciones = new GestorTranssacciones();
            gestorTranssacciones.insertarNuevoDepartamentoIngenieroYProyecto();
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
}
