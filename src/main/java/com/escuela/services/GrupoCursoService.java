package com.escuela.services;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.escuela.database.DatabaseConnection;

public class GrupoCursoService {

    public void asignarCursoAGrupo(BufferedReader in, PrintStream out) throws IOException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            out.println("\n🔗 Asignar curso a grupo:");
    
            out.print("Ingrese el ID del grupo: ");
            int grupoId = Integer.parseInt(in.readLine());
    
            out.print("Ingrese el ID del curso: ");
            int cursoId = Integer.parseInt(in.readLine());
    
            CallableStatement stmt = conn.prepareCall("{CALL InsertarGrupoCurso(?, ?)}");
            stmt.setInt(1, grupoId);
            stmt.setInt(2, cursoId);
    
            stmt.execute();
            out.println("✅ Curso asignado al grupo correctamente.");
        } catch (SQLException e) {
            out.println("❌ Error al asignar curso al grupo: " + e.getMessage());
        }
    }

    public void listarGrupoCurso(PrintStream out) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            CallableStatement stmt = conn.prepareCall("{CALL ListarGrupoCurso()}");
            ResultSet rs = stmt.executeQuery();
    
            out.println("\n📋 Lista de grupos y cursos asignados:");
            while (rs.next()) {
                out.printf("Grupo ID: %d | Curso ID: %d%n",
                    rs.getInt("grupo_id"),
                    rs.getInt("curso_id")
                );
            }
        } catch (SQLException e) {
            out.println("❌ Error al listar grupo-curso: " + e.getMessage());
        }
    }
        
    
}
