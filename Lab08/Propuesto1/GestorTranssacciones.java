package mysqldemos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GestorTranssacciones {

    private String url = "jdbc:mysql://localhost:3306/empresadb";
    private String user = "root";
    private String password = "B0rnD_DAnT9";

    public void insertarNuevoDepartamentoIngenieroYProyecto() {
        String sqlInsertDepartamento = "INSERT INTO Departamentos (Nombre, Telefono, Fax) VALUES (?, ?, ?)";
        String sqlInsertIngeniero = "INSERT INTO Ingenieros (Nombre, Especialidad, Cargo) VALUES (?, ?, ?)";
        String sqlInsertProyecto = "INSERT INTO Proyectos (Nombre, Fec_Inicio, Fec_Termino, IngenieroID, IDDpto) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            conn.setAutoCommit(false); // Iniciar transacción

            try (PreparedStatement stmtDepartamento = conn.prepareStatement(sqlInsertDepartamento);
                 PreparedStatement stmtIngeniero = conn.prepareStatement(sqlInsertIngeniero);
                 PreparedStatement stmtProyecto = conn.prepareStatement(sqlInsertProyecto)) {

                // Insertar nuevo departamento
                stmtDepartamento.setString(1, "Marketing");
                stmtDepartamento.setString(2, "111222333");
                stmtDepartamento.setString(3, "444555666");
                stmtDepartamento.executeUpdate();

                // Insertar nuevo ingeniero
                stmtIngeniero.setString(1, "Ana Lopez");
                stmtIngeniero.setString(2, "Marketing");
                stmtIngeniero.setString(3, "Gerente");
                stmtIngeniero.executeUpdate();

                // Insertar nuevo proyecto
                stmtProyecto.setString(1, "Proyecto B");
                stmtProyecto.setDate(2, java.sql.Date.valueOf("2024-02-01"));
                stmtProyecto.setDate(3, java.sql.Date.valueOf("2024-11-30"));
                stmtProyecto.setInt(4, getLastInsertedID(conn, "Ingenieros")); // Obtener último ID de Ingenieros
                stmtProyecto.setInt(5, getLastInsertedID(conn, "Departamentos")); // Obtener último ID de Departamentos
                stmtProyecto.executeUpdate();

                conn.commit(); // Confirmar transacción
                System.out.println("Nuevos departamento, ingeniero y proyecto insertados.");

            } catch (SQLException e) {
                conn.rollback(); // Deshacer transacción en caso de error
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int getLastInsertedID(Connection conn, String tabla) throws SQLException {
        String sql = "SELECT LAST_INSERT_ID() AS last_id FROM " + tabla;
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getInt("last_id");
            }
        }
        throw new SQLException("No se pudo obtener el último ID insertado para " + tabla);
    }
}
