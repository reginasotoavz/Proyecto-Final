package modulos;
import java.util.Scanner;

public class Profesor extends Usuario {
    public Profesor(String nombre, String correo, String password) {
        super(nombre, correo, "Profesor", password);
    }
    @Override
    public void mostrarMenu(SistemaTareas sistema, Scanner sc){
        int option = 0;
        do {
        System.out.println("Menu de Profesor:"+this.getNombre());
        System.out.println("1. Registrar nuevo usuario");
        System.out.println("2. Eliminar usuario");
        System.out.println("3. Ver lista de usuarios");
        System.out.println("4. Ver lista de tareas");
        System.out.println("5. Crear nueva tarea");
        System.out.println("6. Eliminar tarea");
        System.out.println("7. Salir - Cerrar sesión");

        try {
            option = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Por favor ingrese un número válido.");
            option = -1;
            continue;
        }
        switch (option) {
            case 1:
                System.out.print("Nombre: ");
                String nombre = sc.nextLine();
                System.out.print("Correo: ");
                String correo = sc.nextLine();
                System.out.print("Contraseña: ");
                String password = sc.nextLine();
                System.out.print("Rol (Profesor/Ayudante): ");
                String rol = sc.nextLine();
                sistema.registrarUsuario(nombre, correo, password, rol);
                break;
            case 2:
                System.out.print("Ingrese el correo del usuario a eliminar: ");
                String correoEliminar = sc.nextLine();
                sistema.eliminarUsuario(correoEliminar);
                break;
            case 3:
                sistema.listarUsuarios();
                break;
            case 4:
                sistema.listarTareas();
                break;
            case 5:
                System.out.print("Ingrese el título de la nueva tarea: ");
                String titulo = sc.nextLine();
                System.out.print("Ingrese la descripción de la nueva tarea: ");
                String descripcion = sc.nextLine();
                System.out.print("Ingrese la fecha límite (YYYY-MM-DD): ");
                String fechaLimite = sc.nextLine();
                System.out.print("Ingrese el correo del usuario asignado: ");
                String usuarioAsignado = sc.nextLine();
                sistema.crearTarea(titulo, descripcion, fechaLimite, usuarioAsignado);
                break;
            case 6:
                System.out.print("Ingrese el ID de la tarea a eliminar: ");
                try {
                    int idEliminar = Integer.parseInt(sc.nextLine());
                sistema.eliminarTarea(idEliminar);
                } catch (NumberFormatException e) {
                    System.out.println("ID inválido. Por favor ingrese un número.");
                }
                break;
            case 7:
                System.out.println("Cerrando sesión...");
                break;
            default:
                System.out.println("Opción no válida. Intente de nuevo.");
        }
    } while (option != 7);
    }
}

