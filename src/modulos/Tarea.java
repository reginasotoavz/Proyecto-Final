package modulos;

import java.time.LocalDate; // Investiga cómo usar esto
import java.time.format.DateTimeFormatter; // Para imprimir fechas bonitas

public class Tarea {
    private int id;
    private String titulo;
    private String descripcion;
    private String estado;
    private LocalDate fechaLimite;
    private String usuarioAsignado;

    public Tarea(int id, String titulo, String fechaTexto, String descripcion, String usuarioAsignado) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.usuarioAsignado = usuarioAsignado;
        this.estado = "pendiente"; // Estado inicial por defecto

        try {
            this.fechaLimite = LocalDate.parse(fechaTexto);
        } catch (DateTimeParseException e) {
            System.out.println("Formato de fecha inválido en tarea ID " + id);
            this.fechaLimite = LocalDate.now().plusDays(7); 
        }

        LocalDate fecha = LocalDate.parse(fechaTexto);
    }

    // MÉTODOS GETTERS Y SETTERS
    int getId() {
        return id;
    }
    String getTitulo() {
        return titulo;
    }
    String getDescripcion() {
        return descripcion;
    }
    String getUsuarioAsignado() {
        return usuarioAsignado;
    }

    public LocalDate getFechaLimite() {
        return fechaLimite;
    }

    setId(int nuevoId) {
        this.id = nuevoId;
    }
    setTitulo(String nuevoTitulo) {
        this.titulo = nuevoTitulo;
    }
    setDescripcion(String nuevaDescripcion) {
        this.descripcion = nuevaDescripcion;
    }
    setUsuarioAsignado(String nuevoUsuario) {
        this.usuarioAsignado = nuevoUsuario;
    }

    // El estado puede ser "pendiente", "en progreso", "completada",
    public void setEstado(String nuevoEstado) {
        this.estado = nuevoEstado;
    }

    @Override
    public String toString() {
        return "Tarea " + id + ": " + titulo + /n +
               "| Estado: " + "[" + estado + "]" + /n +
               "| Descripción: " + descripcion + /n +
               "| Asignada a: " + usuarioAsignado + /n +
               "| Fecha límite: " + fechaLimite.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}