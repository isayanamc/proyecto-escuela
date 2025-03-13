package com.escuela.dao;

import com.escuela.database.DatabaseConnection;
import com.escuela.models.Profesor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ProfesorDAO {

    public void insertarProfesor(Profesor profesor) throws SQLException {
        String query = "INSERT INTO profesores_isayana (nombre, identificacion, email, departamento, estado) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, profesor.getNombre());
            stmt.setString(2, profesor.getIdentificacion());
            stmt.setString(3, profesor.getEmail());
            stmt.setString(4, profesor.getDepartamento());
            stmt.setBoolean(5, profesor.isEstado());
            stmt.executeUpdate();
        }
    }

    public List<Profesor> listarProfesores() throws SQLException {
        List<Profesor> profesores = new ArrayList<>();
        String query = "SELECT * FROM profesores_isayana";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                profesores.add(new Profesor(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("identificacion"),
                    rs.getString("email"),
                    rs.getString("departamento"),
                    rs.getBoolean("estado")
                ));
            }
        }
        return profesores;
    }

    public Profesor obtenerProfesorPorId(int id) throws SQLException {
        String query = "SELECT * FROM profesores_isayana WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
    
            if (rs.next()) {
                return new Profesor(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("identificacion"),
                    rs.getString("email"),
                    rs.getString("departamento"),
                    rs.getBoolean("estado")
                );
            }
        }
        return null; 
    }

    public void modificarProfesor(Profesor profesor) throws SQLException {
        String query = "UPDATE profesores_isayana SET " +
                       "nombre = ?, " +
                       "identificacion = ?, " +
                       "email = ?, " +
                       "departamento = ?, " +
                       "estado = ? " +
                       "WHERE id = ?";
    
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
    
            Profesor profesorActual = obtenerProfesorPorId(profesor.getId());
    
            if (profesorActual == null) {
                throw new SQLException("No se encontró un profesor con ID " + profesor.getId());
            }
    
            String nombre = (profesor.getNombre() == null || profesor.getNombre().trim().isEmpty()) 
                            ? profesorActual.getNombre() : profesor.getNombre();
    
            String identificacion = (profesor.getIdentificacion() == null || profesor.getIdentificacion().trim().isEmpty()) 
                                    ? profesorActual.getIdentificacion() : profesor.getIdentificacion();
    
            if (identificacion == null) {
                throw new SQLException("Error: La identificación no puede ser NULL");
            }
    
            String email = (profesor.getEmail() == null || profesor.getEmail().trim().isEmpty()) 
                           ? profesorActual.getEmail() : profesor.getEmail();
    
            String departamento = (profesor.getDepartamento() == null || profesor.getDepartamento().trim().isEmpty()) 
                                  ? profesorActual.getDepartamento() : profesor.getDepartamento();
    
            boolean estado = profesor.isEstado();
    
    
            stmt.setString(1, nombre);
            stmt.setString(2, identificacion);
            stmt.setString(3, email);
            stmt.setString(4, departamento);
            stmt.setBoolean(5, estado);
            stmt.setInt(6, profesor.getId());
    
            stmt.executeUpdate();
        }
    }
    
    
     
    

    public void eliminarProfesor(int id) throws SQLException {
        String query = "DELETE FROM profesores_isayana WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
