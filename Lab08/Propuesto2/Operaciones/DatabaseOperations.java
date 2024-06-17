package Operaciones;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseOperations {
    private static final String URL = "jdbc:mysql://localhost:3306/gestion_proyectos";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    // Método para insertar un departamento en la base de datos
    public void insertarDepartamento(String nombre) throws SQLException {
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        String sql = "INSERT INTO departamentos (nombre) VALUES (?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, nombre);
        statement.executeUpdate();
        statement.close();
        conn.close();
    }

    // Método para obtener todos los departamentos de la base de datos
    public ArrayList<String> obtenerTodosDepartamentos() throws SQLException {
        ArrayList<String> departamentos = new ArrayList<>();
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        String sql = "SELECT * FROM departamentos";
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            int id = result.getInt("id");
            String nombre = result.getString("nombre");
            departamentos.add(id + ": " + nombre);
        }
        result.close();
        statement.close();
        conn.close();
        return departamentos;
    }

    // Método para actualizar un departamento en la base de datos
    public void actualizarDepartamento(int id, String nuevoNombre) throws SQLException {
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        String sql = "UPDATE departamentos SET nombre = ? WHERE id = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, nuevoNombre);
        statement.setInt(2, id);
        statement.executeUpdate();
        statement.close();
        conn.close();
    }

    // Método para eliminar un departamento de la base de datos
    public void eliminarDepartamento(int id) throws SQLException {
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        String sql = "DELETE FROM departamentos WHERE id = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
        statement.close();
        conn.close();
    }

    // Método para insertar un ingeniero en la base de datos
    public void insertarIngeniero(String nombre) throws SQLException {
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        String sql = "INSERT INTO ingenieros (nombre) VALUES (?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, nombre);
        statement.executeUpdate();
        statement.close();
        conn.close();
    }

    // Método para obtener todos los ingenieros de la base de datos
    public ArrayList<String> obtenerTodosIngenieros() throws SQLException {
        ArrayList<String> ingenieros = new ArrayList<>();
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        String sql = "SELECT * FROM ingenieros";
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            int id = result.getInt("id");
            String nombre = result.getString("nombre");
            ingenieros.add(id + ": " + nombre);
        }
        result.close();
        statement.close();
        conn.close();
        return ingenieros;
    }

    // Método para actualizar un ingeniero en la base de datos
    public void actualizarIngeniero(int id, String nuevoNombre) throws SQLException {
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        String sql = "UPDATE ingenieros SET nombre = ? WHERE id = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, nuevoNombre);
        statement.setInt(2, id);
        statement.executeUpdate();
        statement.close();
        conn.close();
    }

    // Método para eliminar un ingeniero de la base de datos
    public void eliminarIngeniero(int id) throws SQLException {
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        String sql = "DELETE FROM ingenieros WHERE id = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
        statement.close();
        conn.close();
    }

    // Método para insertar un proyecto en la base de datos
    public void insertarProyecto(String nombre) throws SQLException {
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        String sql = "INSERT INTO proyectos (nombre) VALUES (?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, nombre);
        statement.executeUpdate();
        statement.close();
        conn.close();
    }

    // Método para obtener todos los proyectos de la base de datos
    public ArrayList<String> obtenerTodosProyectos() throws SQLException {
        ArrayList<String> proyectos = new ArrayList<>();
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        String sql = "SELECT * FROM proyectos";
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            int id = result.getInt("id");
            String nombre = result.getString("nombre");
            proyectos.add(id + ": " + nombre);
        }
        result.close();
        statement.close();
        conn.close();
        return proyectos;
    }

    // Método para actualizar un proyecto en la base de datos
    public void actualizarProyecto(int id, String nuevoNombre) throws SQLException {
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        String sql = "UPDATE proyectos SET nombre = ? WHERE id = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, nuevoNombre);
        statement.setInt(2, id);
        statement.executeUpdate();
        statement.close();
        conn.close();
    }

    // Método para eliminar un proyecto de la base de datos
    public void eliminarProyecto(int id) throws SQLException {
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        String sql = "DELETE FROM proyectos WHERE id = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
        statement.close();
        conn.close();
    }
    // Método para asignar un proyecto a un ingeniero en la base de datos
    public void asignarProyectoAIngeniero(int idIngeniero, int idProyecto) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql = "INSERT INTO ingenieros_proyectos (ingeniero_id, proyecto_id) VALUES (?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idIngeniero);
            pstmt.setInt(2, idProyecto);
            pstmt.executeUpdate();
            System.out.println("Proyecto asignado al Ingeniero correctamente.");
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new SQLException("Error al asignar proyecto al ingeniero: " + ex.getMessage());
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
    // Método para obtener todos los proyectos asignados a un ingeniero
    public ArrayList<String> obtenerProyectosDeIngeniero(int idIngeniero) throws SQLException {
        ArrayList<String> proyectos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql = "SELECT p.id, p.nombre " +
                         "FROM proyectos p " +
                         "JOIN ingenieros_proyectos ip ON p.id = ip.proyecto_id " +
                         "WHERE ip.ingeniero_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idIngeniero);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                proyectos.add(id + ": " + nombre);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new SQLException("Error al obtener proyectos del ingeniero: " + ex.getMessage());
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return proyectos;
    }
}