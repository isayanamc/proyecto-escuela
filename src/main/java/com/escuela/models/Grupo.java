package com.escuela.models;

public class Grupo {
    private int id;
    private String nombre;
    private String descripcion;
    private boolean estado;

    public Grupo(int id, String nombre, String descripcion, boolean estado) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public boolean isEstado() { return estado; }
    public void setEstado(boolean estado) { this.estado = estado; }

    @Override
    public String toString() {
        return "Grupo{id=" + id + 
               ", nombre='" + nombre + '\'' +
               ", descripcion='" + descripcion + '\'' +
               ", estado=" + estado + 
               '}';
    }
    

}
