package mysqldemos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ProyectoAlm {

    private String url = "jdbc:mysql://localhost:3306/empresadb";
    private String user = "root";
    private String password = "B0rnD_DAnT9";

    public void insertarProyecto(String nombre, String fechaInicio, String fechaTermino, int ingenieroID, int idDpto) {
        String sql = "{CALL InsertarProyecto(?, ?, ?, ?, ?)}";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             CallableStatement stmt = conn.prepareCall(sql)) {
            
            stmt.setString(1, nombre);
            stmt.setDate(2, java.sql.Date.valueOf(fechaInicio));
            stmt.setDate(3, java.sql.Date.valueOf(fechaTermino));
            stmt.setInt(4, ingenieroID);
            stmt.setInt(5, idDpto);
            
            stmt.execute();
            System.out.println("Proyecto insertado.");
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
