package mysqldemos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CrearProyecto {

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

            // Crear la base de datos EmpresaDB
            String createDatabase = "CREATE DATABASE IF NOT EXISTS EmpresaDB";
            stmt.executeUpdate(createDatabase);

            // Usar la base de datos EmpresaDB
            String useDatabase = "USE EmpresaDB";
            stmt.executeUpdate(useDatabase);

            // Crear la tabla Departamentos
            String createDepartamentos = "CREATE TABLE IF NOT EXISTS Departamentos (" +
                    "IDDpto INT AUTO_INCREMENT PRIMARY KEY, " +
                    "Nombre VARCHAR(100) NOT NULL, " +
                    "Telefono VARCHAR(15), " +
                    "Fax VARCHAR(15))";
            stmt.executeUpdate(createDepartamentos);

            // Crear la tabla Ingenieros
            String createIngenieros = "CREATE TABLE IF NOT EXISTS Ingenieros (" +
                    "IDIng INT AUTO_INCREMENT PRIMARY KEY, " +
                    "Nombre VARCHAR(100) NOT NULL, " +
                    "Especialidad VARCHAR(100), " +
                    "Cargo VARCHAR(50))";
            stmt.executeUpdate(createIngenieros);

            // Crear la tabla Proyectos
            String createProyectos = "CREATE TABLE IF NOT EXISTS Proyectos (" +
                    "IDProy INT AUTO_INCREMENT PRIMARY KEY, " +
                    "Nombre VARCHAR(100) NOT NULL, " +
                    "Fec_Inicio DATE, " +
                    "Fec_Termino DATE, " +
                    "IngenieroID INT, " +
                    "IDDpto INT, " +
                    "FOREIGN KEY (IngenieroID) REFERENCES Ingenieros(IDIng), " +
                    "FOREIGN KEY (IDDpto) REFERENCES Departamentos(IDDpto))";
            stmt.executeUpdate(createProyectos);

            // Crear índices secundarios
            String createIndexDepartamentoNombre = "CREATE INDEX idx_departamento_nombre ON Departamentos(Nombre)";
            stmt.executeUpdate(createIndexDepartamentoNombre);

            String createIndexIngenieroNombre = "CREATE INDEX idx_ingeniero_nombre ON Ingenieros(Nombre)";
            stmt.executeUpdate(createIndexIngenieroNombre);

            String createIndexProyectoNombre = "CREATE INDEX idx_proyecto_nombre ON Proyectos(Nombre)";
            stmt.executeUpdate(createIndexProyectoNombre);

            System.out.println("Database and tables created successfully");

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
