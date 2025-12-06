package modulos;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import utilidades.ManejadorArchivos;
import utilidades.Notificador;

public class SistemaTareas {
    // ATRIBUTOS
    private List<Usuario> listaUsuarios; 
    private List<Tarea> listaTareas;
    private Usuario usuarioLogueado; 
    private Notificador hiloNotificador; 
    private Scanner sc;

    // CONSTRUCTOR
    public SistemaTareas() {
        this.listaUsuarios = new ArrayList<>();
        this.listaTareas = new ArrayList<>();
        this.sc = new Scanner(System.in);
        // Cargar datos desde archivos
        this.listaUsuarios = ManejadorArchivos.cargarUsuarios();
        this.listaTareas = ManejadorArchivos.cargarTareas();
        // Si no hay usuarios, se crea un usuario Profesor por defecto
        if (this.listaUsuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados. Creando usuario Profesor por defecto.");
            listaUsuarios.add(new Profesor("Profesor", "Profesor@correo.com", "Admin123!"));
    }
    }

    // MÉTODOS PRINCIPALES
    public void iniciar() {
        System.out.println(">>>> INICIANDO SISTEMA DE TAREAS 'OdinDimadinDon'");
        // Iniciar el hilo notificador
        hiloNotificador = new Notificador(this.listaTareas);
        hiloNotificador.start();

        boolean salir = false;
        while (!salir) {
            // Mostrar menú principal (Login / Salir)
            System.out.println(":) Bienvenidx al sistema de tareas.");
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Salir");
            System.out.print("Seleccione una opción: \n > ");
            String input = sc.nextLine();

            switch (input) {
                case "1":
                    login();
                    break;
                case "2":
                    salir = true;
                    cerrarSistema();
                    System.out.println("<<<< Saliendo del sistema. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("⚠︎ Opción no válida. Por favor, intente de nuevo.");
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
            System.out.println(":) Inicio de sesión exitoso. ¡Bienvenidx, " + usuarioEncontrado.getNombre() + "!");
            this.usuarioLogueado = usuarioEncontrado;
            usuarioLogueado.mostrarMenu(this, sc);
        } else {
            System.out.println("⚠︎ Correo o contraseña incorrectos.");
        }
    }

    // MÉTODOS DE GESTIÓN DE USUARIOS Y TAREAS    
    // TAREAS
    public void crearTarea(String titulo, String descripcion, String fechaStr, String correoAsignado) {
        try {
            int nuevoId = listaTareas.size() + 1;
            Tarea nuevaTarea = new Tarea(nuevoId, titulo, descripcion, fechaStr, correoAsignado);
            listaTareas.add(nuevaTarea);
            System.out.println("Tarea creada exitosamente con ID: " + nuevoId);
        } catch (IllegalArgumentException e) {
            System.out.println("⚠︎ Error al crear tarea: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("⚠︎ Error inesperado al crear tarea (revise formato): " + e.getMessage());
        }
    }

    public void listarTareas() {
        System.out.println("\n>>>> Lista de Tareas:");
        if (listaTareas.isEmpty()) {
            System.out.println(":( No hay tareas registradas.");
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
            System.out.println(":) Tarea con ID " + id + " eliminada exitosamente.");
        } else {
            System.out.println("⚠︎ No se encontró tarea con ID " + id + ".");
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
        if (!tareaEncontrada) System.out.println("⚠︎ No se encontró tarea con ID " + id + ".");
    }

    // USUARIOS
    public boolean registrarUsuario(String nombre, String correo, String password, String rol) {
       try { 
        // Verificar si el correo ya está registrado
        for (Usuario u : listaUsuarios) {
            if (u.getCorreo().equalsIgnoreCase(correo)) {
                System.out.println("⚠︎ Ya existe un usuario con ese correo.");
                return false;
            }
        }
        // Crear y agregar el nuevo usuario según el rol
        if (rol.equalsIgnoreCase("Profesor")) {
            listaUsuarios.add(new Profesor(nombre, correo, password));
            System.out.println(":) Usuario " + nombre + " registrado exitosamente como Profesor.");
            return true;
        } else if (rol.equalsIgnoreCase("Ayudante")) {
            listaUsuarios.add(new Ayudante(nombre, correo, password));
            System.out.println(":) Usuario " + nombre + " registrado exitosamente como Ayudante.");
            return true;
        } else {
            System.out.println("⚠︎ Rol inválido. Use 'Profesor' o 'Ayudante'.");
            return false;
        } 
    } catch (IllegalArgumentException e) {
            System.out.println("⚠︎ Error al registrar usuario: " + e.getMessage());
            return false;
    }
    } 

    public void listarUsuarios() {
        System.out.println("\n>>>> Lista de Usuarios:");
        if (listaUsuarios.isEmpty()) {
            System.out.println(":( No hay usuarios registrados.");
            return;
        } else {
            for (Usuario u : listaUsuarios) {
                System.out.println(u);
            }
        }
    }

    public void eliminarUsuario(String correo) {
        if (usuarioLogueado != null && usuarioLogueado.getCorreo().equalsIgnoreCase(correo)) {
            System.out.println("⚠︎ No puede eliminar su propio usuario mientras está logueado.");
            return;
        }
        boolean usuarioEliminado = listaUsuarios.removeIf(u -> u.getCorreo().equalsIgnoreCase(correo));
        
        if (usuarioEliminado) { 
            System.out.println(":) Usuario con correo " + correo + " eliminado exitosamente.");
        } else {
            System.out.println("⚠︎ No se encontró usuario con correo " + correo + ".");
        }
    }

    // MÉTODO DE CIERRE DEL SISTEMA
    public void cerrarSistema() {
        System.out.println("<<<< Guardando datos antes de salir...");
        
        ManejadorArchivos.guardarUsuarios(this.listaUsuarios);
        ManejadorArchivos.guardarTareas(this.listaTareas);

        if (hiloNotificador != null) {
            hiloNotificador.detener();
        }
        System.out.println(":) Datos guardados. \n Cerrando sistema. \n ¡Hasta luego! \n :(");
    }
}