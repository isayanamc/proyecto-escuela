package com.escuela.dao;

import com.escuela.database.DatabaseConnection;
import com.escuela.models.GrupoCurso;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class GrupoCursoDAO {


    public void asignarCursoAGrupo(int grupoId, int cursoId) throws SQLException {
        String query = "INSERT INTO grupocursos_isayana (grupo_id, curso_id) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, grupoId);
            stmt.setInt(2, cursoId);
            stmt.executeUpdate();
        }
    }

    public List<GrupoCurso> listarGrupoCursos() throws SQLException {
        List<GrupoCurso> grupoCursos = new ArrayList<>();
        String query = "SELECT * FROM grupocursos_isayana";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                grupoCursos.add(new GrupoCurso(
                    rs.getInt("id"),
                    rs.getInt("grupo_id"),
                    rs.getInt("curso_id")
                ));
            }
        }
        return grupoCursos;
    }
}
