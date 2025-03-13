package com.escuela.services;

import com.escuela.dao.CursoDAO;
import com.escuela.models.Curso;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.List;

/**
 * Servicio para manejar la lógica de negocio de Curso.
 */
public class CursoService {
    private final CursoDAO cursoDAO = new CursoDAO();

    public void insertarCurso(BufferedReader in, PrintStream out) throws IOException {
        try {
            out.println("\n📌 Ingrese los datos del curso:");
            out.print("Nombre: ");
            String nombre = in.readLine();

            out.print("Descripción: ");
            String descripcion = in.readLine();

            Curso curso = new Curso(0, nombre, descripcion, true);
            cursoDAO.insertarCurso(curso);
            out.println("✅ Curso insertado correctamente.");
        } catch (SQLException e) {
            out.println("❌ Error al insertar el curso: " + e.getMessage());
        }
    }

    public void listarCursos(PrintStream out) {
        try {
            List<Curso> cursos = cursoDAO.listarCursos();
            out.println("\n📋 Lista de cursos:");
            for (Curso curso : cursos) {
                out.println(curso);
            }
        } catch (SQLException e) {
            out.println("❌ Error al listar cursos: " + e.getMessage());
        }
    }

    public void modificarCurso(BufferedReader in, PrintStream out) throws IOException {
        try {
            out.print("\n✏ Ingrese el ID del curso a modificar: ");
            int id = Integer.parseInt(in.readLine());

            out.print("Nuevo nombre (deje vacío para no cambiar): ");
            String nombre = in.readLine();

            out.print("Nueva descripción (deje vacío para no cambiar): ");
            String descripcion = in.readLine();

            Curso curso = new Curso(id, nombre.isEmpty() ? null : nombre, 
                                        descripcion.isEmpty() ? null : descripcion, true);
            cursoDAO.modificarCurso(curso);
            out.println("✅ Curso modificado correctamente.");
        } catch (SQLException e) {
            out.println("❌ Error al modificar el curso: " + e.getMessage());
        }
    }

    public void eliminarCurso(BufferedReader in, PrintStream out) throws IOException {
        try {
            out.print("\n❌ Ingrese el ID del curso a eliminar: ");
            int id = Integer.parseInt(in.readLine());

            cursoDAO.eliminarCurso(id);
            out.println("✅ Curso eliminado correctamente.");
        } catch (SQLException e) {
            out.println("❌ Error al eliminar el curso: " + e.getMessage());
        }
    }
}
