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
    private int calificacion;

    public Tarea(int id, String titulo, String descripcion, String fechaTexto, String usuarioAsignado) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.usuarioAsignado = usuarioAsignado;
        this.estado = "pendiente"; // Estado inicial por defecto
        this.calificacion = 0;

        try {
            this.fechaLimite = LocalDate.parse(fechaTexto);
        } catch (DateTimeParseException e) {
            System.out.println("⚠︎ Error: Formato de fecha inválido(" + fechaTexto + ") Se usará la siguiente semana");
            this.fechaLimite = LocalDate.now().plusDays(7); 
        }
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
    public int getCalificacion(){
        return calificacion;
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
    public void setCalificacion (int nuevaCalificacion) {
        if (nuevaCalificacion < 0 || nuevaCalificacion > 10) {
            throw new IllegalArgumentException("La calificación debe de estar entre 0 y 10.");
         }
        this.calificacion = nuevaCalificacion;
    }

    @Override
    public String toString() {
        String notaStr = (calificacion > 0) ? String.valueOf(calificacion) : "Sin calificar";
        return "Tarea ID: " + id + "\nEstado: " + "[" + estado + "] \nCalificación: " + calificacion + "/10 \nDescripción: " + descripcion + "\nAsignada a: " + usuarioAsignado + "\nFecha límite: " + fechaLimite + "\n";
    }
} 