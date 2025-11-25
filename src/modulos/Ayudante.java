package modulos;

public class Ayudante extends Usuario {
    public Ayudante(String nombre, String correo) {
        super(nombre, correo, "Ayudante");
    }
    @Override
    public void mostrarMenu(){
        System.out.println("Menu de Ayudante:");
        System.out.println("1. Ver lista de tareas");
        System.out.println("2. Actualizar estado de tarea");
        System.out.println("3. Salir");
    }
}
