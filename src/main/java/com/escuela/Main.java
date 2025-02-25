package com.escuela;  

import com.escuela.services.EstudianteService;
import com.escuela.services.GrupoCursoService;
import com.escuela.services.ProfesorService;
import com.escuela.services.CursoService;
import com.escuela.services.GrupoService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

/**
 * Enunciado: Sistema de gestión de estudiantes, profesores, cursos y grupos.
 * Nombre: Isayana Murillo
 * Fecha de creación: 17 de febrero 2025
 * Última modificación: 24 de febrero 2025
 * Versión: 1.0
 */

public class Main {  

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static PrintStream out = System.out;

    
    static EstudianteService estudianteService = new EstudianteService();
    static ProfesorService profesorService = new ProfesorService();
    static CursoService cursoService = new CursoService();
    static GrupoService grupoService = new GrupoService();
    static GrupoCursoService grupoCursoService = new GrupoCursoService();


    public static void main(String[] args) throws IOException {
        while (true) {
            out.println("\n===== 🏫 MENU PRINCIPAL =====");
            out.println("1️⃣. Gestionar Estudiantes 🎓");
            out.println("2️⃣. Gestionar Profesores 👨‍🏫");
            out.println("3️⃣. Gestionar Cursos 📚");
            out.println("4️⃣. Gestionar Grupos 👥");
            out.println("5️⃣. Gestionar Grupo-Curso 🔗");
            out.println("6️⃣. Salir 🚪");
            out.print("Seleccione una opción: ");
    
            int opcion = Integer.parseInt(in.readLine());
    
            switch (opcion) {
                case 1:
                    menuEstudiantes();
                    break;
                case 2:
                    menuProfesores();
                    break;
                case 3:
                    menuCursos();
                    break;
                case 4:
                    menuGrupos();
                    break;
                case 5:
                    menuGrupoCurso(); // 🔗 Llama al nuevo menú de Grupo-Curso
                    break;
                case 6:
                    out.println("👋 Saliendo del sistema...");
                    return;
                default:
                    out.println("⚠️ Opción no válida. Intente nuevamente.");
            }
        }
    }
    

    // ========================= MENÚ ESTUDIANTES 🎓 =========================
    private static void menuEstudiantes() throws IOException {
        while (true) {
            out.println("\n===== 🎓 GESTIONAR ESTUDIANTES =====");
            out.println("1️⃣. Agregar Estudiante ➕");
            out.println("2️⃣. Listar Estudiantes 📜");
            out.println("3️⃣. Modificar Estudiante 📝");
            out.println("4️⃣. Eliminar Estudiante ❌");
            out.println("5️⃣. Volver al menú principal 🔙");
            out.print("Seleccione una opción: ");

            int opcion = Integer.parseInt(in.readLine());

            switch (opcion) {
                case 1:
                    estudianteService.insertarEstudiante(in, out);
                    break;
                case 2:
                    estudianteService.listarEstudiantes(out);
                    break;
                case 3:
                    estudianteService.modificarEstudiante(in, out);
                    break;
                case 4:
                    estudianteService.eliminarEstudiante(in, out);
                    break;
                case 5:
                    return;
                default:
                    out.println("⚠️ Opción no válida. Intente nuevamente.");
            }
        }
    }

    // ========================= MENÚ PROFESORES 👨‍🏫 =========================
    private static void menuProfesores() throws IOException {
        while (true) {
            out.println("\n===== 👨‍🏫 GESTIONAR PROFESORES =====");
            out.println("1️⃣. Agregar Profesor ➕");
            out.println("2️⃣. Listar Profesores 📜");
            out.println("3️⃣. Modificar Profesor 📝");
            out.println("4️⃣. Eliminar Profesor ❌");
            out.println("5️⃣. Volver al menú principal 🔙");
            out.print("Seleccione una opción: ");

            int opcion = Integer.parseInt(in.readLine());

            switch (opcion) {
                case 1:
                    profesorService.insertarProfesor(in, out);
                    break;
                case 2:
                    profesorService.listarProfesores(out);
                    break;
                case 3:
                    profesorService.modificarProfesor(in, out);
                    break;
                case 4:
                    profesorService.eliminarProfesor(in, out);
                    break;
                case 5:
                    return;
                default:
                    out.println("⚠️ Opción no válida. Intente nuevamente.");
            }
        }
    }

    // ========================= MENÚ CURSOS 📚 =========================
    private static void menuCursos() throws IOException {
        while (true) {
            out.println("\n===== 📚 GESTIONAR CURSOS =====");
            out.println("1️⃣. Agregar Curso ➕");
            out.println("2️⃣. Listar Cursos 📜");
            out.println("3️⃣. Modificar Curso 📝");
            out.println("4️⃣. Eliminar Curso ❌");
            out.println("5️⃣. Volver al menú principal 🔙");
            out.print("Seleccione una opción: ");

            int opcion = Integer.parseInt(in.readLine());

            switch (opcion) {
                case 1:
                    cursoService.insertarCurso(in, out);
                    break;
                case 2:
                    cursoService.listarCursos(out);
                    break;
                case 3:
                    cursoService.modificarCurso(in, out);
                    break;
                case 4:
                    cursoService.eliminarCurso(in, out);
                    break;
                case 5:
                    return;
                default:
                    out.println("⚠️ Opción no válida. Intente nuevamente.");
            }
        }
    }

    // ========================= MENÚ GRUPOS 👥 =========================
    private static void menuGrupos() throws IOException {
        while (true) {
            out.println("\n===== 👥 GESTIONAR GRUPOS =====");
            out.println("1️⃣. Agregar Grupo ➕");
            out.println("2️⃣. Listar Grupos 📜");
            out.println("3️⃣. Modificar Grupo 📝");
            out.println("4️⃣. Eliminar Grupo ❌");
            out.println("5️⃣. Volver al menú principal 🔙");
            out.print("Seleccione una opción: ");

            int opcion = Integer.parseInt(in.readLine());

            switch (opcion) {
                case 1:
                    grupoService.insertarGrupo(in, out);
                    break;
                case 2:
                    grupoService.listarGrupos(out);
                    break;
                case 3:
                    grupoService.modificarGrupo(in, out);
                    break;
                case 4:
                    grupoService.eliminarGrupo(in, out);
                    break;
                case 5:
                    return;
                default:
                    out.println("⚠️ Opción no válida. Intente nuevamente.");
            }
        }
    }



    // ========================= MENÚ GRUPO-CURSO 🌳 =========================
    private static void menuGrupoCurso() throws IOException {
        while (true) {
            out.println("\n===== 🔗 GESTIONAR GRUPO-CURSO =====");
            out.println("1️⃣. Asignar Curso a Grupo 📌");
            out.println("2️⃣. Listar Relaciones 📋");
            out.println("3️⃣. Volver al menú principal 🔙");
            out.print("Seleccione una opción: ");
    
            int opcion = Integer.parseInt(in.readLine());
    
            switch (opcion) {
                case 1:
                    grupoCursoService.asignarCursoAGrupo(in, out);
                    break;
                case 2:
                    grupoCursoService.listarGrupoCurso(out);
                    break;
                case 3:
                    return;
                default:
                    out.println("⚠ Opción no válida. Intente nuevamente.");
            }
        }
    }
    
}
