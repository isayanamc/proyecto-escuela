package com.escuela.dao;

import com.escuela.database.DatabaseConnection;
import com.escuela.models.Grupo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GrupoDAO {


    public void insertarGrupo(Grupo grupo) throws SQLException {
        String query = "INSERT INTO grupos_isayana (nombre, descripcion, estado) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, grupo.getNombre());
            stmt.setString(2, grupo.getDescripcion());
            stmt.setBoolean(3, grupo.isEstado());
            stmt.executeUpdate();
        }
    }


    public List<Grupo> listarGrupos() throws SQLException {
        List<Grupo> grupos = new ArrayList<>();
        String query = "SELECT * FROM grupos_isayana";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                grupos.add(new Grupo(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("descripcion"),
                    rs.getBoolean("estado")
                ));
            }
        }
        return grupos;
    }

    public Grupo obtenerGrupoPorId(int id) throws SQLException {
        String query = "SELECT * FROM grupos_isayana WHERE id = ?";
    
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
    
            if (rs.next()) {
                return new Grupo(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("descripcion"),
                    rs.getBoolean("estado")
                );
            }
        }
        return null; 
    }

    public void modificarGrupo(Grupo grupo) throws SQLException {
        String query = "UPDATE grupos_isayana SET " +
                       "nombre = ?, " +
                       "descripcion = ?, " +
                       "estado = ? " +
                       "WHERE id = ?";
    
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            Grupo grupoActual = obtenerGrupoPorId(grupo.getId());
    
            stmt.setString(1, grupo.getNombre().isEmpty() ? grupoActual.getNombre() : grupo.getNombre());
            stmt.setString(2, grupo.getDescripcion().isEmpty() ? grupoActual.getDescripcion() : grupo.getDescripcion());
            stmt.setBoolean(3, grupo.isEstado());
    
            stmt.setInt(4, grupo.getId());
    
            stmt.executeUpdate();
        }
    }
    
    

    public void eliminarGrupo(int id) throws SQLException {
        String query = "DELETE FROM grupos_isayana WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
