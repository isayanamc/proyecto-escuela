package com.escuela.services;

import com.escuela.dao.GrupoCursoDAO;
import com.escuela.models.GrupoCurso;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.List;


public class GrupoCursoService {
    private final GrupoCursoDAO grupoCursoDAO = new GrupoCursoDAO();

    public void asignarCursoAGrupo(BufferedReader in, PrintStream out) throws IOException {
        try {
            out.println("\n📌 Asignar Curso a Grupo");
            out.print("Ingrese el ID del grupo: ");
            int grupoId = Integer.parseInt(in.readLine());

            out.print("Ingrese el ID del curso: ");
            int cursoId = Integer.parseInt(in.readLine());

            grupoCursoDAO.asignarCursoAGrupo(grupoId, cursoId);
            out.println("✅ Curso asignado al grupo correctamente.");
        } catch (SQLException e) {
            out.println("❌ Error al asignar el curso al grupo: " + e.getMessage());
        }
    }

    public void listarGrupoCursos(PrintStream out) {
        try {
            List<GrupoCurso> grupoCursos = grupoCursoDAO.listarGrupoCursos();
            out.println("\n📋 Lista de relaciones Grupo-Curso:");
            for (GrupoCurso grupoCurso : grupoCursos) {
                out.println("ID: " + grupoCurso.getId() +
                            ", Grupo ID: " + grupoCurso.getGrupoId() +
                            ", Curso ID: " + grupoCurso.getCursoId());
            }
        } catch (SQLException e) {
            out.println("❌ Error al listar relaciones Grupo-Curso: " + e.getMessage());
        }
    }
}
