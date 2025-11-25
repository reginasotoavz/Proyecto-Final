import java.io.*;
import java.util.*;
import java.util.Scanner;
import java.util.InputMismatchException;
import modulos.Usuario;
import modulos.Profesor;
import modulos.Ayudante;
import modulos.Tarea;

public class Main {
    private static List<User> users = new ArrayList<>();
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String filename = "usuarios.csv";
        ManejadorLista<Ayudante> manejadorAyudantes = new ManejadorLista<>();
        ManejadorLista<Profesor> manejadorProfesores = new ManejadorLista<>();

        Ayudante [] ayudantes = new Ayudante[0];
        Profesor [] profesores = new Profesor[0];
        
        try {
            loadUsers(filename);
            System.out.println("=== Sistema de Login ===");

            System.out.print("Correo: ");
            String email = sc.nextLine().trim();

            System.out.print("Password: ");
            String password = sc.nextLine().trim();

            User loggedUser = authenticate(email, password);

            if (loggedUser != null) {
                System.out.println("\nBienvenido " + loggedUser.getEmail() + "!");
                showMenuByRole(loggedUser.getRole(), sc);
            } else {
                System.out.println("\n❌ Credenciales incorrectas. Intente nuevamente.");
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error: Archivo no encontrado: " + filename);
        } catch (IOException e) {
            System.out.println("Error de lectura del archivo.");
        } finally {
            sc.close();
        }
    }

    private static void loadUsers(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 3) {
                String email = parts[0].trim();
                String password = parts[1].trim();
                String role = parts[2].trim().toUpperCase();
                users.add(new User(email, password, role));
            }
        }
        reader.close();
    }

    private static User authenticate(String email, String password) {
        for (User u : users) {
            if (u.checkCredentials(email, password)) {
                return u;
            }
        }
        return null;
    }

    private static void showMenuByRole(String role, Scanner sc) {
        switch (role) {
            case "ADMIN":
                showAdminMenu(sc);
                break;
            case "USER":
                showUserMenu(sc);
                break;
            case "GUEST":
                showGuestMenu(sc);
                break;
            default:
                System.out.println("Rol desconocido. No hay menú disponible.");
        }
    }

    private static void showAdminMenu(Scanner sc) {
        System.out.println("\n=== Menú ADMIN ===");
        System.out.println("1. Ver lista de usuarios");
        System.out.println("2. Crear nuevo usuario");
        System.out.println("3. Salir");
        System.out.print("Opción: ");
        int option = sc.nextInt();
        sc.nextLine(); // limpiar buffer

        switch (option) {
            case 1:
                System.out.println("Mostrando lista de usuarios (solo ejemplo)...");
                break;
            case 2:
                System.out.println("Creando nuevo usuario (solo ejemplo)...");
                break;
            case 3:
                System.out.println("Saliendo...");
                break;
            default:
                System.out.println("Opción inválida.");
        }
    }

    private static void showUserMenu(Scanner sc) {
        System.out.println("\n=== Menú USER ===");
        System.out.println("1. Ver perfil");
        System.out.println("2. Cambiar contraseña");
        System.out.println("3. Salir");
        System.out.print("Opción: ");
        int option = sc.nextInt();
        sc.nextLine();

        switch (option) {
            case 1:
                System.out.println("Mostrando perfil...");
                break;
            case 2:
                System.out.println("Cambiando contraseña...");
                break;
            case 3:
                System.out.println("Saliendo...");
                break;
            default:
                System.out.println("Opción inválida.");
        }
    }

    private static void showGuestMenu(Scanner sc) {
        System.out.println("\n=== Menú GUEST ===");
        System.out.println("1. Ver información general");
        System.out.println("2. Salir");
        System.out.print("Opción: ");
        int option = sc.nextInt();
        sc.nextLine();

        switch (option) {
            case 1:
                System.out.println("Mostrando información general...");
                break;
            case 2:
                System.out.println("Saliendo...");
                break;
            default:
                System.out.println("Opción inválida.");
        }

}
}