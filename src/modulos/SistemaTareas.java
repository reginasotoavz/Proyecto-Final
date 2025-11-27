package modulos;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import utilidades.ManejadorArchivos;
import utilidades.Notificador;

public class SistemaTareas {
    private List<Usuario> listaUsuarios; 
    private List<Tarea> listaTareas;
    private Usuario usuarioLogueado; 
    private Notificador hiloNotificador; 
}

    public SistemaTareas() {
        this.listaUsuarios = new ArrayList<>();
        this.listaTareas = new ArrayList<>();
        this.sc = new Scanner(System.in);
        this.listaUsuarios = ManejadorArchivos.cargarUsuarios();
        this.listaTareas = ManejadorArchivos.cargarTareas();

        if (this.listaUsuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados. Creando usuario Profesor por defecto.");
            listaUsuarios.add(new Profesor("Admin", "admincorreo@test.com, Admin123!"));
    }

    // MÉTODOS PRINCIPALES
    public void iniciar() {
        System.out.println("> Iniciando Sistema de Tareas OdinDimadinDon <");
        hilonotificador = new Notificador(this.listaTareas);
        hilonotificador.start();

        boolean salir = false;
        while (!salir) {
            login();
            System.out.println("Bienvenidx al sistema de tareas.");
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Salir");
            System.out.print("Seleccione una opción: ");
            String opcion = sc.nextLine();

            String input = sc.nextLine();
            switch (input) {
                case "1":
                    login();
                    break;
                case "2":
                    salir = true;
                    cerrarSistema();
                    System.out.println("Saliendo del sistema. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
            }
        }
    }

    public void login() {
        System.out.print("Ingrese su correo: ");
        String correoIngresado = sc.nextLine();

        System.out.print("Ingrese su contraseña: ");
        String passwordIngresada = sc.nextLine();

        Usuario usuarioEncontrado = null;

        for (Usuario u : listaUsuarios) {
            if (u .checkCredentials(correoIngresado, passwordIngresada)) {
                usuarioEncontrado = u;
                break;
            }
        }
        if (usuarioEncontrado != null) {
            System.out.println("Inicio de sesión exitoso. ¡Bienvenidx, " + usuarioEncontrado.getNombre() + "!");
            this.usuarioLogueado = usuarioEncontrado;
            usuarioLogueado.mostrarMenu(this, sc);
        } else {
            System.out.println("❌ Correo o contraseña incorrectos. Intente de nuevo.");
        }
    }
        
    public void crearTarea(String titulo, String descripcion, String fechaStr, String correoAsignado) {
        try {
            int nuevoId = listaTareas.size() + 1;
            Tarea nuevaTarea = new Tarea(nuevoId, titulo, descripcion, fechaStr, correoAsignado);
            listaTareas.add(nuevaTarea);
            System.out.println("Tarea creada exitosamente con ID: " + nuevoId);
        } catch (IllegalArgumentException e) {
            System.out.println("Error al crear tarea: " + e.getMessage());
        }
    }

    public void listarTareas() {
        System.out.println("> Lista de Tareas:");
        if (listaTareas.isEmpty()) {
            System.out.println("No hay tareas registradas.");
            return;
        } else {
            for (Tarea t : listaTareas) {
                System.out.println(t);
            }
        }

    }
    
    public void eliminarTarea(int id) {
        boolean tareaEliminada = listaTareas.removeIf(t -> t.getId() == id);
        if (tareaEliminada) {
            System.out.println("Tarea con ID " + id + " eliminada exitosamente.");
        } else {
            System.out.println("No se encontró tarea con ID " + id + ".");
        }
    }

    public void actualizarEstadoTarea(int id, String nuevoEstado) {
        boolean tareaEncontrada = false;
        for (Tarea t : listaTareas) {
            if (t.getId() == id) {
                t.setEstado(nuevoEstado);
                System.out.println("Estado de la tarea ID " + id + " actualizado a: " + nuevoEstado);
                tareaEncontrada = true;
                break;
            }
        }
        if (!tareaEncontrada) System.out.println("No se encontró tarea con ID " + id + ".");
    }

    public void registrarUsuario(String nombre, String correo, String password, String rol) {
       try { 
        for (Usuario u : listaUsuarios) {
            if (u.getCorreo().equals(correo)) {
                System.out.println("❌ Ya existe un usuario con ese correo.");
                return;
            }
        }
        if (rol.equalsIgnoreCase("Profesor")) {
            listaUsuarios.add(new Profesor(nombre, correo, password));
            System.out.println("Usuario " + nombre + " registrado exitosamente como Profesor.");
        } else if (rol.equalsIgnoreCase("Ayudante")) {
            listaUsuarios.add(new Ayudante(nombre, correo, password));
            System.out.println("Usuario " + nombre + " registrado exitosamente como Ayudante.");
        } else {
            System.out.println("❌ Rol inválido. Use 'Profesor' o 'Ayudante'.");
        }
        } catch (IllegalArgumentException e) {
            System.out.println("Error al registrar usuario: " + e.getMessage());
        }
    }

    public void eliminarUsuario(String correo) {
        if (usuarioEliminado != null && usuarioLogueado.getCorreo().equalsIgnoreCase(correo)) {
            System.out.println("❌ No puede eliminar su propio usuario mientras está logueado.");
            return;
        boolean usuarioEliminado = listaUsuarios.removeIf(u -> u.getCorreo().equalsIgnoreCase(correo));
        if (usuarioEliminado) { 
            System.out.println("Usuario con correo " + correo + " eliminado exitosamente.");
        } else {
            System.out.println("No se encontró usuario con correo " + correo + ".");
        }
    }

    public void cerrarSistema() {
        System.out.println("Guardando datos antes de salir...");
        
        ManejadorArchivos.guardarUsuarios(this.listaUsuarios);
        ManejadorArchivos.guardarTareas(this.listaTareas);

        if notificador != null {
            notificador.detener();
        }
        System.out.println("Datos guardados." +/n + "Cerrando sistema." + /n + "¡Hasta luego!");
    }