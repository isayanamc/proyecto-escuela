package com.escuela.dao;

import com.escuela.database.DatabaseConnection;
import com.escuela.models.Curso;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CursoDAO {

    public void insertarCurso(Curso curso) throws SQLException {
        String query = "INSERT INTO cursos_isayana (nombre, descripcion, estado) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, curso.getNombre());
            stmt.setString(2, curso.getDescripcion());
            stmt.setBoolean(3, curso.isEstado());
            stmt.executeUpdate();
        }
    }

    public List<Curso> listarCursos() throws SQLException {
        List<Curso> cursos = new ArrayList<>();
        String query = "SELECT * FROM cursos_isayana";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                cursos.add(new Curso(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("descripcion"),
                    rs.getBoolean("estado")
                ));
            }
        }
        return cursos;
    }
    
    public Curso obtenerCursoPorId(int id) throws SQLException {
        String query = "SELECT * FROM cursos_isayana WHERE id = ?";
    
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
    
            if (rs.next()) {
                return new Curso(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("descripcion"),
                    rs.getBoolean("estado")
                );
            }
        }
        return null;  
    }
    
    public void modificarCurso(Curso curso) throws SQLException {
        String query = "UPDATE cursos_isayana SET " +
                       "nombre = ?, " +
                       "descripcion = ?, " +
                       "estado = ? " +
                       "WHERE id = ?";
    
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
    
            Curso cursoActual = obtenerCursoPorId(curso.getId());
    
            stmt.setString(1, curso.getNombre().isEmpty() ? cursoActual.getNombre() : curso.getNombre());
            stmt.setString(2, curso.getDescripcion().isEmpty() ? cursoActual.getDescripcion() : curso.getDescripcion());
            stmt.setBoolean(3, curso.isEstado());
            stmt.setInt(4, curso.getId());
            stmt.executeUpdate();
        }
    }
    


    public void eliminarCurso(int id) throws SQLException {
        String query = "DELETE FROM cursos_isayana WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
