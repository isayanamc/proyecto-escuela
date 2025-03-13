package com.escuela.services;

import com.escuela.dao.EstudianteDAO;
import com.escuela.models.Estudiante;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.List;

public class EstudianteService {
    private final EstudianteDAO estudianteDAO = new EstudianteDAO();

    public void insertarEstudiante(BufferedReader in, PrintStream out) throws IOException {
        try {
            out.println("\n📌 Ingrese los datos del estudiante:");
            out.print("Nombre: ");
            String nombre = in.readLine();

            out.print("Identificación: ");
            String identificacion = in.readLine();

            out.print("Correo electrónico: ");
            String email = in.readLine();

            out.print("Fecha de nacimiento (YYYY-MM-DD): ");
            String fechaNacimiento = in.readLine();

            Estudiante estudiante = new Estudiante(0, nombre, identificacion, email, java.sql.Date.valueOf(fechaNacimiento), true);
            estudianteDAO.insertarEstudiante(estudiante);

            out.println("✅ Estudiante insertado correctamente.");
        } catch (SQLException e) {
            out.println("❌ Error al insertar el estudiante: " + e.getMessage());
        }
    }

    public void listarEstudiantes(PrintStream out) {
        try {
            List<Estudiante> estudiantes = estudianteDAO.listarEstudiantes();
            out.println("\n📋 Lista de estudiantes:");
            for (Estudiante estudiante : estudiantes) {
                out.println(estudiante);
            }
        } catch (SQLException e) {
            out.println("❌ Error al listar estudiantes: " + e.getMessage());
        }
    }


    public void modificarEstudiante(BufferedReader in, PrintStream out) throws IOException {
        try {
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
            String fechaStr = in.readLine();
            java.sql.Date fechaNacimiento = fechaStr.isEmpty() ? null : java.sql.Date.valueOf(fechaStr);
    
            Estudiante estudiante = new Estudiante(id, nombre, identificacion, email, fechaNacimiento, true);
            estudianteDAO.modificarEstudiante(estudiante);
    
            out.println("✅ Estudiante modificado correctamente.");
        } catch (SQLException e) {
            out.println("❌ Error al modificar el estudiante: " + e.getMessage());
        }
    }
    
    public void eliminarEstudiante(BufferedReader in, PrintStream out) throws IOException {
        try {
            out.println("\n❌ Eliminar estudiante");
            out.print("Ingrese el ID del estudiante a eliminar: ");
            int id = Integer.parseInt(in.readLine());
    
            // Llamar al DAO para eliminar el estudiante
            estudianteDAO.eliminarEstudiante(id);
            out.println("✅ Estudiante eliminado correctamente.");
        } catch (SQLException e) {
            out.println("❌ Error al eliminar el estudiante: " + e.getMessage());
        }
    }
    



}
