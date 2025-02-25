package com.escuela.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.escuela.database.DatabaseConnection;
import com.escuela.models.Grupo;

public class GrupoService {

    public void insertarGrupo(BufferedReader in, PrintStream out) throws IOException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            out.println("\n🏫 Ingrese los datos del grupo:");
    
            out.print("Nombre: ");
            String nombre = in.readLine();
    
            out.print("Descripción: ");
            String descripcion = in.readLine();
    
            CallableStatement stmt = conn.prepareCall("{CALL InsertarGrupo(?, ?)}");
            stmt.setString(1, nombre);
            stmt.setString(2, descripcion);
    
            stmt.execute();
            out.println("✅ Grupo insertado correctamente.");
        } catch (SQLException e) {
            out.println("❌ Error al insertar el grupo: " + e.getMessage());
        }
    }
    
    public void listarGrupos(PrintStream out) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            CallableStatement stmt = conn.prepareCall("{CALL ListarGrupos()}");
            ResultSet rs = stmt.executeQuery();
    
            out.println("\n📋 Lista de grupos:");
            while (rs.next()) {
                Grupo grupo = new Grupo(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("descripcion"),
                    rs.getBoolean("estado")
                );
                out.println(grupo);
            }
        } catch (SQLException e) {
            out.println("❌ Error al listar grupos: " + e.getMessage());
        }
    }

    public void modificarGrupo(BufferedReader in, PrintStream out) throws IOException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            out.println("\n✏ Modificar grupo");
    
            out.print("Ingrese el ID del grupo a modificar: ");
            int id = Integer.parseInt(in.readLine());
    
            out.print("Nuevo nombre (deje vacío para no cambiar): ");
            String nombre = in.readLine();
    
            out.print("Nueva descripción (deje vacío para no cambiar): ");
            String descripcion = in.readLine();
    
            CallableStatement stmt = conn.prepareCall("{CALL ModificarGrupo(?, ?, ?)}");
            stmt.setInt(1, id);
            stmt.setString(2, nombre.isEmpty() ? null : nombre);
            stmt.setString(3, descripcion.isEmpty() ? null : descripcion);
    
            stmt.execute();
            out.println("✅ Grupo modificado correctamente.");
        } catch (SQLException e) {
            out.println("❌ Error al modificar el grupo: " + e.getMessage());
        }
    }

    public void eliminarGrupo(BufferedReader in, PrintStream out) throws IOException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            out.println("\n❌ Eliminar grupo");
    
            out.print("Ingrese el ID del grupo a eliminar: ");
            int id = Integer.parseInt(in.readLine());
    
            CallableStatement stmt = conn.prepareCall("{CALL EliminarGrupo(?)}");
            stmt.setInt(1, id);
    
            stmt.execute();
            out.println("✅ Grupo eliminado correctamente.");
        } catch (SQLException e) {
            out.println("❌ Error al eliminar el grupo: " + e.getMessage());
        }
    }
    
    
}
