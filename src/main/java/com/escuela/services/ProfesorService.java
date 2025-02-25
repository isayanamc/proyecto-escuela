package com.escuela.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.escuela.database.DatabaseConnection;
import com.escuela.models.Profesor;

public class ProfesorService {

    public void insertarProfesor(BufferedReader in, PrintStream out) throws IOException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            out.println("\n📌 Ingrese los datos del profesor:");

            out.print("Nombre: ");
            String nombre = in.readLine();

            out.print("Identificación: ");
            String identificacion = in.readLine();

            out.print("Correo electrónico: ");
            String email = in.readLine();

            out.print("Departamento: ");
            String departamento = in.readLine();

            CallableStatement stmt = conn.prepareCall("{CALL InsertarProfesor(?, ?, ?, ?)}");
            stmt.setString(1, nombre);
            stmt.setString(2, identificacion);
            stmt.setString(3, email);
            stmt.setString(4, departamento);

            stmt.execute();
            out.println("✅ Profesor insertado correctamente.");

        } catch (SQLException e) {
            out.println("❌ Error al insertar el profesor: " + e.getMessage());
        }
    }


    public void listarProfesores(PrintStream out) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            CallableStatement stmt = conn.prepareCall("{CALL ListarProfesores()}");
            ResultSet rs = stmt.executeQuery();

            out.println("\n📋 Lista de profesores:");
            while (rs.next()) {
                Profesor profesor = new Profesor(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("identificacion"),
                    rs.getString("email"),
                    rs.getString("departamento"),
                    rs.getBoolean("estado")
                );
                out.println(profesor);
            }
        } catch (SQLException e) {
            out.println("❌ Error al listar profesores: " + e.getMessage());
        }
    }


    public void modificarProfesor(BufferedReader in, PrintStream out) throws IOException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            out.println("\n✏ Modificar profesor");
    
            out.print("Ingrese el ID del profesor a modificar: ");
            int id = Integer.parseInt(in.readLine());
    
            out.print("Nuevo nombre (deje vacío para no cambiar): ");
            String nombre = in.readLine();
    
            out.print("Nueva identificación (deje vacío para no cambiar): ");
            String identificacion = in.readLine();
    
            out.print("Nuevo correo electrónico (deje vacío para no cambiar): ");
            String email = in.readLine();
    
            out.print("Nuevo departamento (deje vacío para no cambiar): ");
            String departamento = in.readLine();
    
            CallableStatement stmt = conn.prepareCall("{CALL ModificarProfesor(?, ?, ?, ?, ?)}");
            stmt.setInt(1, id);
            stmt.setString(2, nombre.isEmpty() ? null : nombre);
            stmt.setString(3, identificacion.isEmpty() ? null : identificacion);
            stmt.setString(4, email.isEmpty() ? null : email);
            stmt.setString(5, departamento.isEmpty() ? null : departamento);
    
            stmt.execute();
            out.println("✅ Profesor modificado correctamente.");
        } catch (SQLException e) {
            out.println("❌ Error al modificar el profesor: " + e.getMessage());
        }
    }
    

    public void eliminarProfesor(BufferedReader in, PrintStream out) throws IOException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            out.println("\n❌ Eliminar profesor");
    
            out.print("Ingrese el ID del profesor a eliminar: ");
            int id = Integer.parseInt(in.readLine());
    
            CallableStatement stmt = conn.prepareCall("{CALL EliminarProfesor(?)}");
            stmt.setInt(1, id);
    
            stmt.execute();
            out.println("✅ Profesor eliminado correctamente.");
        } catch (SQLException e) {
            out.println("❌ Error al eliminar el profesor: " + e.getMessage());
        }
    }
    
}
