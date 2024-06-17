package mysqldemos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DepartamentoPS {

    private String url = "jdbc:mysql://localhost:3306/empresadb";
    private String user = "root";
    private String password = "B0rnD_DAnT9";

    public void insertarDepartamento(String nombre, String telefono, String fax) {
        String sql = "INSERT INTO Departamentos (Nombre, Telefono, Fax) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, nombre);
            stmt.setString(2, telefono);
            stmt.setString(3, fax);
            
            stmt.executeUpdate();
            System.out.println("Nuevo departamento insertado.");
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
