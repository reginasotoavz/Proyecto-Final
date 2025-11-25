import java.io.*;
import java.util.*;
import java.util.Scanner;
import java.util.InputMismatchException;
import modulos.Usuario;
import modulos.Profesor;
import modulos.Ayudante;
import modulos.Tarea;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ManejadorLista<Ayudante> manejadorAyudantes = new ManejadorLista<>();
        ManejadorLista<Profesor> manejadorProfesores = new ManejadorLista<>();

        Ayudante [] ayudantes = new Ayudante[0];
        Profesor [] profesores = new Profesor[0];

        String newnombre = scanner.nextLine();
        String newcorreo = scanner.nextLine();
        
        System.out.print("El nombre debe tener al menos 5 letras. Intente de nuevo: ");
        newnombre = scanner.nextLine();

        List<Usuario> usuarios = new ArrayList<>();
        List<Tarea> tareas = new ArrayList<>();

        // Ejemplo de creación de usuarios
        try {
            Usuario profesor = new Profesor();
            
          catch (IllegalStateException e) {
            System.out.println("Error de estado: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error de argumento: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ocurrió un error inesperado: " + e.getMessage());
        }
        
        scanner.close();
}