package com.escuela.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

import com.escuela.database.DatabaseConnection;
import com.escuela.models.Estudiante;

public class EstudianteService {

    public void insertarEstudiante(BufferedReader in, PrintStream out) throws IOException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            out.println("\n📌 Ingrese los datos del estudiante:");

            out.print("Nombre: ");
            String nombre = in.readLine();

            out.print("Identificación: ");
            String identificacion = in.readLine();

            out.print("Correo electrónico: ");
            String email = in.readLine();

            out.print("Fecha de nacimiento (YYYY-MM-DD): ");
            String fechaNacimiento = in.readLine();

            CallableStatement stmt = conn.prepareCall("{CALL InsertarEstudiante(?, ?, ?, ?)}");
            stmt.setString(1, nombre);
            stmt.setString(2, identificacion);
            stmt.setString(3, email);
            stmt.setString(4, fechaNacimiento);

            stmt.execute();
            out.println("✅ Estudiante insertado correctamente.");

        } catch (SQLException e) {
            out.println("❌ Error al insertar el estudiante: " + e.getMessage());
        }
    }

    public void listarEstudiantes(PrintStream out) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            CallableStatement stmt = conn.prepareCall("{CALL ListarEstudiantes()}");
            ResultSet rs = stmt.executeQuery();

            out.println("\n📋 Lista de estudiantes:");
            while (rs.next()) {
                Estudiante estudiante = new Estudiante(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("identificacion"),
                    rs.getString("email"),
                    rs.getDate("fecha_nacimiento"),
                    rs.getBoolean("estado")
                );
                out.println(estudiante);
            }

        } catch (SQLException e) {
            out.println("❌ Error al listar estudiantes: " + e.getMessage());
        }
    }

    public void modificarEstudiante(BufferedReader in, PrintStream out) throws IOException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            out.println("\n✏️ Modificar estudiante");

            out.print("Ingrese el ID del estudiante a modificar: ");
            int id = Integer.parseInt(in.readLine());

            out.print("Nuevo nombre (deje vacío para no cambiar): ");
            String nombre = in.readLine();

            out.print("Nueva identificación (deje vacío para no cambiar): ");
            String identificacion = in.readLine();

            out.print("Nuevo correo electrónico (deje vacío para no cambiar): ");
            String email = in.readLine();

            out.print("Nueva fecha de nacimiento (YYYY-MM-DD, deje vacío para no cambiar): ");
            Date fechaNacimiento = null;
            String fechaStr = in.readLine();
            
            if (!fechaStr.isEmpty()) {
                fechaNacimiento = Date.valueOf(fechaStr);
            }
            
            CallableStatement stmt = conn.prepareCall("{CALL ModificarEstudiante(?, ?, ?, ?, ?)}");
            stmt.setInt(1, id);
            stmt.setString(2, nombre.isEmpty() ? null : nombre);
            stmt.setString(3, identificacion.isEmpty() ? null : identificacion);
            stmt.setString(4, email.isEmpty() ? null : email);
            stmt.setDate(5, fechaNacimiento);

            stmt.execute();
            out.println("✅ Estudiante modificado correctamente.");

        } catch (SQLException e) {
            out.println("❌ Error al modificar el estudiante: " + e.getMessage());
        }

    }

    public void eliminarEstudiante(BufferedReader in, PrintStream out) throws IOException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            out.println("\n❌ Eliminar estudiante");

            out.print("Ingrese el ID del estudiante a eliminar: ");
            int id = Integer.parseInt(in.readLine());

            CallableStatement stmt = conn.prepareCall("{CALL EliminarEstudiante(?)}");
            stmt.setInt(1, id);
            stmt.execute();

            out.println("✅ Estudiante eliminado correctamente.");

        } catch (SQLException e) {
            out.println("❌ Error al eliminar el estudiante: " + e.getMessage());
        }
    }

}
