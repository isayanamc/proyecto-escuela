package com.escuela;

import com.escuela.services.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;


public class Main {
    public static void main(String[] args) {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintStream out = System.out;

        EstudianteService estudianteService = new EstudianteService();
        ProfesorService profesorService = new ProfesorService();
        CursoService cursoService = new CursoService();
        GrupoService grupoService = new GrupoService();
        GrupoCursoService grupoCursoService = new GrupoCursoService();

        MenuHandler menuHandler = new MenuHandler(in, out, estudianteService, profesorService, cursoService, grupoService, grupoCursoService);
        menuHandler.start();
    }
}
