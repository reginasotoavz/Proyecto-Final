package modulos;
import java.util.Scanner;

public class Ayudante extends Usuario {
    public Ayudante(String nombre, String correo, String password) {
        super(nombre, correo, "Ayudante", password);
    }
    @Override
    public void mostrarMenu(SistemaTareas sistema, Scanner sc){
        int option = 0;
        do {
        System.out.println("\n>>>> Menu de Ayudante:"+this.getNombre());
        System.out.println("1. Ver lista de tareas");
        System.out.println("2. Actualizar estado de tarea");
        System.out.println("3. Salir - Cerrar sesión");
        System.out.print("Seleccione una opción: \n > ");

        try {
            option = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("⚠︎ Por favor ingrese un número válido.");
            continue;
        }

        switch (option) {
            case 1:
                sistema.listarTareas();
                break;
            case 2:
                System.out.print("Ingrese el ID de la tarea a actualizar: ");
                int idTarea = Integer.parseInt(sc.nextLine());
                System.out.print("Ingrese el nuevo estado de la tarea: ");
                String nuevoEstado = sc.nextLine();
                sistema.actualizarEstadoTarea(idTarea, nuevoEstado);
                break;
            case 3:
                System.out.println("Cerrando sesión...");
                break;
            default:
                System.out.println("⚠︎ Opción no válida. Intente de nuevo.");
        }
    } while (option != 3);
    }
}
