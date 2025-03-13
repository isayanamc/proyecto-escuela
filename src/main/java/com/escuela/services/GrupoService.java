package com.escuela.services;

import com.escuela.dao.GrupoDAO;
import com.escuela.models.Grupo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.List;

/**
 * Servicio para manejar la lógica de negocio de Grupo.
 */
public class GrupoService {
    private final GrupoDAO grupoDAO = new GrupoDAO();

    public void insertarGrupo(BufferedReader in, PrintStream out) throws IOException {
        try {
            out.println("\n📌 Ingrese los datos del grupo:");
            out.print("Nombre: ");
            String nombre = in.readLine();

            out.print("Descripción: ");
            String descripcion = in.readLine();

            Grupo grupo = new Grupo(0, nombre, descripcion, true);
            grupoDAO.insertarGrupo(grupo);
            out.println("✅ Grupo insertado correctamente.");
        } catch (SQLException e) {
            out.println("❌ Error al insertar el grupo: " + e.getMessage());
        }
    }

    public void listarGrupos(PrintStream out) {
        try {
            List<Grupo> grupos = grupoDAO.listarGrupos();
            out.println("\n📋 Lista de grupos:");
            for (Grupo grupo : grupos) {
                out.println(grupo);
            }
        } catch (SQLException e) {
            out.println("❌ Error al listar grupos: " + e.getMessage());
        }
    }

    public void modificarGrupo(BufferedReader in, PrintStream out) throws IOException {
        try {
            out.print("\n✏ Ingrese el ID del grupo a modificar: ");
            int id = Integer.parseInt(in.readLine());

            out.print("Nuevo nombre (deje vacío para no cambiar): ");
            String nombre = in.readLine();

            out.print("Nueva descripción (deje vacío para no cambiar): ");
            String descripcion = in.readLine();

            Grupo grupo = new Grupo(id, nombre.isEmpty() ? null : nombre, 
                                        descripcion.isEmpty() ? null : descripcion, true);
            grupoDAO.modificarGrupo(grupo);
            out.println("✅ Grupo modificado correctamente.");
        } catch (SQLException e) {
            out.println("❌ Error al modificar el grupo: " + e.getMessage());
        }
    }

    public void eliminarGrupo(BufferedReader in, PrintStream out) throws IOException {
        try {
            out.print("\n❌ Ingrese el ID del grupo a eliminar: ");
            int id = Integer.parseInt(in.readLine());

            grupoDAO.eliminarGrupo(id);
            out.println("✅ Grupo eliminado correctamente.");
        } catch (SQLException e) {
            out.println("❌ Error al eliminar el grupo: " + e.getMessage());
        }
    }
}
