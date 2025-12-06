package modulos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
            System.out.println("Error: Formato de fecha inválido(" + fechaTexto + ") Se usará la sigueinte semana");
            this.fechaLimite = LocalDate.now().plusDays(7); 
        }

        LocalDate fecha = LocalDate.parse(fechaTexto);
    }

    // MÉTODOS GETTERS Y SETTERS
    public int getId() {
        return id;
    }
    public String getTitulo() {
        return titulo;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public String getUsuarioAsignado() {
        return usuarioAsignado;
    }
    public LocalDate getFechaLimite() {
        return fechaLimite;
    }
    public String getEstado() {
        return estado;
    }

    public void setId(int nuevoId) {
        this.id = nuevoId;
    }
    public void setTitulo(String nuevoTitulo) {
        this.titulo = nuevoTitulo;
    }
    public void setDescripcion(String nuevaDescripcion) {
        this.descripcion = nuevaDescripcion;
    }
    public void setUsuarioAsignado(String nuevoUsuario) {
        this.usuarioAsignado = nuevoUsuario;
    }
    public void setEstado(String nuevoEstado) {
        this.estado = nuevoEstado;
    }

    @Override
    public String toString() {
        return "Tarea ID: " + id + "\nEstado: " + "[" + estado + "] \nDescripción: " + descripcion + "\nAsignada a: " + usuarioAsignado + "\nFecha límite: " + fechaLimite + "\n";
    }
} 