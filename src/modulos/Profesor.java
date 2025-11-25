package modulos;

public class Profesor extends Usuario {
    public Profesor(String nombre, String correo) {
        super(nombre, correo, "Profesor");
    }
    @Override
    public void mostrarMenu(){
        System.out.println("Menu de Profesor:");
        System.out.println("1. Registrar nuevo usuario");
        System.out.println("2. Eliminar usuario");
        System.out.println("3. Ver lista de usuarios");
        System.out.println("4. Ver lista de tareas");
        System.out.println("5. Crear nueva tarea");
        System.out.println("6. Eliminar tarea");
        System.out.println("7. Salir");
    }

}