package com.escuela.services;

import com.escuela.dao.ProfesorDAO;
import com.escuela.models.Profesor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.List;


public class ProfesorService {
    private final ProfesorDAO profesorDAO = new ProfesorDAO();

    public void insertarProfesor(BufferedReader in, PrintStream out) throws IOException {
        try {
            out.println("\n📌 Ingrese los datos del profesor:");
            out.print("Nombre: ");
            String nombre = in.readLine();

            out.print("Identificación: ");
            String identificacion = in.readLine();

            out.print("Correo electrónico: ");
            String email = in.readLine();

            out.print("Departamento: ");
            String departamento = in.readLine();

            Profesor profesor = new Profesor(0, nombre, identificacion, email, departamento, true);
            profesorDAO.insertarProfesor(profesor);
            out.println("✅ Profesor insertado correctamente.");
        } catch (SQLException e) {
            out.println("❌ Error al insertar el profesor: " + e.getMessage());
        }
    }

    public void listarProfesores(PrintStream out) {
        try {
            List<Profesor> profesores = profesorDAO.listarProfesores();
            out.println("\n📋 Lista de profesores:");
            for (Profesor profesor : profesores) {
                out.println(profesor);
            }
        } catch (SQLException e) {
            out.println("❌ Error al listar profesores: " + e.getMessage());
        }
    }

    public void eliminarProfesor(BufferedReader in, PrintStream out) throws IOException {
        try {
            out.print("\n❌ Ingrese el ID del profesor a eliminar: ");
            int id = Integer.parseInt(in.readLine());

            profesorDAO.eliminarProfesor(id);
            out.println("✅ Profesor eliminado correctamente.");
        } catch (SQLException e) {
            out.println("❌ Error al eliminar el profesor: " + e.getMessage());
        }
    }

    public void modificarProfesor(BufferedReader in, PrintStream out) throws IOException {
        try {
            out.println("\n✏️ Modificar profesor");
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
    
            out.print("¿Está activo? (true/false): ");
            boolean estado = Boolean.parseBoolean(in.readLine());
    
            Profesor profesor = new Profesor(id,
                    nombre.isEmpty() ? null : nombre,
                    identificacion.isEmpty() ? null : identificacion,
                    email.isEmpty() ? null : email,
                    departamento.isEmpty() ? null : departamento,
                    estado);
    
            profesorDAO.modificarProfesor(profesor);
            out.println("✅ Profesor modificado correctamente.");
        } catch (SQLException e) {
            out.println("❌ Error al modificar el profesor: " + e.getMessage());
        }
    }
    
}
