package modulos;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SistemaTareas sistema = new SistemaTareas();
        
        // Pista: Aquí podrías crear un usuario "Admin" por defecto si la lista está vacía
        // sistema.registrarUsuario(new Profesor("Odin", "odin@profe.com"));

        sistema.iniciar();
        
        // MENÚ TEMPORAL PARA PROBAR (Borrar luego de implementar Login real)
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Crear Tarea de prueba");
        System.out.println("2. Ver Tareas");
        System.out.println("3. Salir");
        
        int op = sc.nextInt();
        if (op == 1) sistema.crearTarea("Examen Parcial", "2025-11-30", "ayudante@test.com");
        if (op == 2) sistema.listarTareas();
        if (op == 3) sistema.apagar();
    }
}