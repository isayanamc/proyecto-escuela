package com.escuela;

import com.escuela.services.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Clase encargada de gestionar el menú principal del sistema.
 */
public class MenuHandler {
    private BufferedReader in;
    private PrintStream out;
    private EstudianteService estudianteService;
    private ProfesorService profesorService;
    private CursoService cursoService;
    private GrupoService grupoService;
    private GrupoCursoService grupoCursoService;

    public MenuHandler(BufferedReader in, PrintStream out, EstudianteService estudianteService, ProfesorService profesorService, CursoService cursoService, GrupoService grupoService, GrupoCursoService grupoCursoService) {
        this.in = in;
        this.out = out;
        this.estudianteService = estudianteService;
        this.profesorService = profesorService;
        this.cursoService = cursoService;
        this.grupoService = grupoService;
        this.grupoCursoService = grupoCursoService;
    }

    public void start() {
        boolean running = true;
        while (running) {
            out.println("\n===== 🏫 MENU PRINCIPAL =====");
            out.println("1️⃣. Gestionar Estudiantes 🎓");
            out.println("2️⃣. Gestionar Profesores 👨‍🏫");
            out.println("3️⃣. Gestionar Cursos 📚");
            out.println("4️⃣. Gestionar Grupos 👥");
            out.println("5️⃣. Gestionar Grupo-Curso 🔗");
            out.println("6️⃣. Salir 🚪");
            out.print("Seleccione una opción: ");
            
            try {
                int opcion = Integer.parseInt(in.readLine());
                switch (opcion) {
                    case 1:
                        gestionarEstudiantes();
                        break;
                    case 2:
                        gestionarProfesores();
                        break;
                    case 3:
                        gestionarCursos();
                        break;
                    case 4:
                        gestionarGrupos();
                        break;
                    case 5:
                        gestionarGrupoCursos();
                        break;
                    case 6:
                        running = false;
                        break;
                    default:
                        out.println("⚠️ Opción inválida, intente de nuevo.");
                }
                
            } catch (IOException | NumberFormatException e) {
                out.println("⚠️ Error en la entrada, intente de nuevo.");
            } 
        }
    }

    private void gestionarEstudiantes() throws IOException {
        out.println("\n📌 Gestión de Estudiantes");
        out.println("1. Insertar Estudiante");
        out.println("2. Listar Estudiantes");
        out.println("3. Modificar Estudiante");
        out.println("4. Eliminar Estudiante");
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
            default:
                out.println("⚠️ Opción inválida.");
        }
    }


    private void gestionarProfesores() throws IOException {
        out.println("\n📌 Gestión de Profesores");
        out.println("1. Insertar Profesor");
        out.println("2. Listar Profesores");
        out.println("3. Modificar Profesor");
        out.println("4. Eliminar Profesor");
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
            default:
                out.println("⚠️ Opción inválida.");
        }
    }
    
    private void gestionarCursos() throws IOException {
        out.println("\n📌 Gestión de Cursos");
        out.println("1. Insertar Curso");
        out.println("2. Listar Cursos");
        out.println("3. Modificar Curso");
        out.println("4. Eliminar Curso");
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
            default:
                out.println("⚠️ Opción inválida.");
        }
    }

    private void gestionarGrupos() throws IOException {
        out.println("\n📌 Gestión de Grupos");
        out.println("1. Insertar Grupo");
        out.println("2. Listar Grupos");
        out.println("3. Modificar Grupo");
        out.println("4. Eliminar Grupo");
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
            default:
                out.println("⚠️ Opción inválida.");
        }
    }

    private void gestionarGrupoCursos() throws IOException {
        out.println("\n📚 Gestión de Relaciones Grupo-Curso");
        out.println("1. Asignar Curso a Grupo");
        out.println("2. Listar Relaciones Grupo-Curso");
        out.print("Seleccione una opción: ");
    
        int opcion = Integer.parseInt(in.readLine());
        switch (opcion) {
            case 1:
                grupoCursoService.asignarCursoAGrupo(in, out);
                break;
            case 2:
                grupoCursoService.listarGrupoCursos(out);
                break;
            default:
                out.println("⚠️ Opción inválida.");
        }
    }
    

}
