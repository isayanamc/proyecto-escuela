package com.escuela.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.escuela.database.DatabaseConnection;
import com.escuela.models.Curso;


public class CursoService {

    public void insertarCurso(BufferedReader in, PrintStream out) throws IOException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            out.println("\n📚 Ingrese los datos del curso:");
    
            out.print("Nombre: ");
            String nombre = in.readLine();
    
            out.print("Descripción: ");
            String descripcion = in.readLine();
    
            CallableStatement stmt = conn.prepareCall("{CALL InsertarCurso(?, ?)}");
            stmt.setString(1, nombre);
            stmt.setString(2, descripcion);
    
            stmt.execute();
            out.println("✅ Curso insertado correctamente.");
        } catch (SQLException e) {
            out.println("❌ Error al insertar el curso: " + e.getMessage());
        }
    }

    public void listarCursos(PrintStream out) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            CallableStatement stmt = conn.prepareCall("{CALL ListarCursos()}");
            ResultSet rs = stmt.executeQuery();
    
            out.println("\n📋 Lista de cursos:");
            while (rs.next()) {
                Curso curso = new Curso(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("descripcion"),
                    rs.getBoolean("estado")
                );
                out.println(curso);
            }
        } catch (SQLException e) {
            out.println("❌ Error al listar cursos: " + e.getMessage());
        }
    }

    public void modificarCurso(BufferedReader in, PrintStream out) throws IOException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            out.println("\n✏ Modificar curso");
    
            out.print("Ingrese el ID del curso a modificar: ");
            int id = Integer.parseInt(in.readLine());
    
            out.print("Nuevo nombre (deje vacío para no cambiar): ");
            String nombre = in.readLine();
    
            out.print("Nueva descripción (deje vacío para no cambiar): ");
            String descripcion = in.readLine();
    
            CallableStatement stmt = conn.prepareCall("{CALL ModificarCurso(?, ?, ?)}");
            stmt.setInt(1, id);
            stmt.setString(2, nombre.isEmpty() ? null : nombre);
            stmt.setString(3, descripcion.isEmpty() ? null : descripcion);
    
            stmt.execute();
            out.println("✅ Curso modificado correctamente.");
        } catch (SQLException e) {
            out.println("❌ Error al modificar el curso: " + e.getMessage());
        }
    }

    public void eliminarCurso(BufferedReader in, PrintStream out) throws IOException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            out.println("\n❌ Eliminar curso");
    
            out.print("Ingrese el ID del curso a eliminar: ");
            int id = Integer.parseInt(in.readLine());
    
            CallableStatement stmt = conn.prepareCall("{CALL EliminarCurso(?)}");
            stmt.setInt(1, id);
    
            stmt.execute();
            out.println("✅ Curso eliminado correctamente.");
        } catch (SQLException e) {
            out.println("❌ Error al eliminar el curso: " + e.getMessage());
        }
    }
    
    
}
