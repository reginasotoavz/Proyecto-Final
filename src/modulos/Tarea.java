package modulos;

package modulos;

import java.time.LocalDate; // Investiga cómo usar esto
import java.time.format.DateTimeFormatter; // Para imprimir fechas bonitas

public class Tarea {
    // ATRIBUTOS
    // TODO: Declara aquí las variables privadas.
    // El PDF pide: id (int o String), titulo (String), estado (String), fechaLimite (LocalDate)
    // y usuarioAsignado (Usuario o String con el correo).
    private int id;
    private String titulo;
    private String descripcion;
    private String estado; // Por ejemplo: "Pendiente", "En Progreso", "
    private String fechaLimite;
    private String usuarioAsignado;

    

    // CONSTRUCTOR
    public Tarea(int id, String titulo, String fechaTexto) {
        this.id = id;
        this.titulo = titulo;
        this.estado = "pendiente"; // Estado por defecto
        
        // RETO: Convertir el String fechaTexto ("2023-11-30") a un objeto LocalDate.
        // Pista: Usa LocalDate.parse(fechaTexto);
        // this.fechaLimite = ...
    }

    // MÉTODOS GETTERS Y SETTERS
    // Necesitas getters para que el Notificador pueda ver la fecha y el estado.
    
    public LocalDate getFechaLimite() {
        // return this.fechaLimite;
        return null; // (Borra esto y retorna la fecha real)
    }

    public String getEstado() {
        // return this.estado;
        return ""; 
    }
    
    // TODO: Crea el método setEstado(String nuevoEstado) para cambiarlo luego.

    // TOSTRING (Para imprimir la tarea en consola)
    @Override
    public String toString() {
        // TODO: Retorna un String bonito con la info de la tarea.
        return "Tarea " + id + ": " + titulo + " [Vence: " + "PONER_FECHA_AQUI" + "]";
    }
}