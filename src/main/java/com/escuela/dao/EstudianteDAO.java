package com.escuela.dao;

import com.escuela.database.DatabaseConnection;
import com.escuela.models.Estudiante;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDAO {
    
    public void insertarEstudiante(Estudiante estudiante) throws SQLException {
        String query = "INSERT INTO estudiantes_isayana (nombre, identificacion, email, fecha_nacimiento) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, estudiante.getNombre());
            stmt.setString(2, estudiante.getIdentificacion());
            stmt.setString(3, estudiante.getEmail());
            stmt.setDate(4, new java.sql.Date(estudiante.getFechaNacimiento().getTime()));
            stmt.executeUpdate();
        }
    }

    public List<Estudiante> listarEstudiantes() throws SQLException {
        List<Estudiante> estudiantes = new ArrayList<>();
        String query = "SELECT * FROM estudiantes_isayana";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                estudiantes.add(new Estudiante(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("identificacion"),
                    rs.getString("email"),
                    rs.getDate("fecha_nacimiento"),
                    rs.getBoolean("estado")
                ));
            }
        }
        return estudiantes;
    }

    public Estudiante obtenerEstudiantePorId(int id) throws SQLException {
        String query = "SELECT * FROM estudiantes_isayana WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
    
            if (rs.next()) {
                return new Estudiante(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("identificacion"),
                    rs.getString("email"),
                    rs.getDate("fecha_nacimiento"),
                    rs.getBoolean("estado")
                );
            }
        }
        return null;
    }

    public void modificarEstudiante(Estudiante estudiante) throws SQLException {
        String query = "UPDATE estudiantes_isayana SET " +
                       "nombre = ?, " +
                       "identificacion = ?, " +
                       "email = ?, " +
                       "fecha_nacimiento = ? " +
                       "WHERE id = ?";
    
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
    
            Estudiante estudianteActual = obtenerEstudiantePorId(estudiante.getId());
    
            if (estudianteActual == null) {
                throw new SQLException("No se encontró un estudiante con ID " + estudiante.getId());
            }
    
            stmt.setString(1, estudiante.getNombre().isEmpty() ? estudianteActual.getNombre() : estudiante.getNombre());
            stmt.setString(2, estudiante.getIdentificacion().isEmpty() ? estudianteActual.getIdentificacion() : estudiante.getIdentificacion());
            stmt.setString(3, estudiante.getEmail().isEmpty() ? estudianteActual.getEmail() : estudiante.getEmail());
    
            if (estudiante.getFechaNacimiento() != null) {
                stmt.setDate(4, new java.sql.Date(estudiante.getFechaNacimiento().getTime()));
            } else {
                stmt.setDate(4, estudianteActual.getFechaNacimiento() != null 
                                ? new java.sql.Date(estudianteActual.getFechaNacimiento().getTime()) 
                                : null);
            }

            stmt.setInt(5, estudiante.getId());
    
            stmt.executeUpdate();
        }
    }
    

    public void eliminarEstudiante(int id) throws SQLException {
        String query = "DELETE FROM estudiantes_isayana WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
