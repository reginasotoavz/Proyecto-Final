/* public class SistemaTareas {
    private List<Usuario> usuarios;
    private List<Tarea> tareas;
    private Notificador notificador; // Referencia al hilo

    public void iniciarSistema() {
        // 1. Cargar datos del archivo al iniciar
        this.usuarios = ManejadorArchivos.cargarUsuarios();
        // this.tareas = ManejadorArchivos.cargarTareas(); // (Cuando lo implementes)

        // 2. Iniciar el hilo notificador
        notificador = new Notificador(this.tareas);
        notificador.start(); // ¡Importante! Usar start(), no run()

        // 3. Mostrar menú principal (Login, etc.)
        mostrarMenuPrincipal();
    }
    
    public void cerrarSistema() {
        // Guardar antes de salir
        ManejadorArchivos.guardarUsuarios(this.usuarios);
        
        // Detener el hilo
        if (notificador != null) {
            notificador.detener();
        }
    }
} */
package modulos;

import java.util.List;
import java.util.ArrayList; // O import utilidades.ManejadorLista;
import utilidades.ManejadorArchivos;
import utilidades.Notificador;

public class SistemaTareas {

    // Listas para guardar la información en memoria mientras el programa corre
    private List<Usuario> listaUsuarios; // O tu ManejadorLista<Usuario>
    private List<Tarea> listaTareas;
    
    private Usuario usuarioLogueado; // Para saber quién está usando el sistema
    private Notificador hiloNotificador; // Referencia al hilo

    // CONSTRUCTOR
    public SistemaTareas() {
        // 1. Inicializar las listas vacías
        this.listaUsuarios = new ArrayList<>();
        this.listaTareas = new ArrayList<>();
        
        // 2. Cargar datos desde los archivos (Persistencia)
        // Pista: Llama a los métodos estáticos que creaste en ManejadorArchivos
        // this.listaUsuarios = ManejadorArchivos.cargarUsuarios();
    }

    // MÉTODOS PRINCIPALES

    public void iniciar() {
        // TODO: Aquí arranca la lógica.
        
        // 1. Iniciar el Hilo Notificador (Pista: new Notificador(...).start())
        
        // 2. Mostrar menú de Login
        // login(); 
    }

    public void login() {
        // TODO: Pedir correo al usuario con Scanner.
        // Buscar en listaUsuarios si existe ese correo.
        
        // Si existe:
        // this.usuarioLogueado = usuarioEncontrado;
        // usuarioLogueado.mostrarMenu(); (Esto llama al menú del Profesor o Ayudante)
        
        // Pista: Como Profesor y Ayudante heredan de Usuario, Java sabe
        // automáticamente qué menú mostrar (Polimorfismo).
    }
    
    // FUNCIONES QUE LLAMARÁ EL MENÚ DEL PROFESOR/AYUDANTE
    
    public void crearTarea(String titulo, String descripcion, String fecha, String correoAsignado) {
        // 1. Generar un ID (puedes usar listaTareas.size() + 1)
        // 2. Crear el objeto Tarea
        // 3. Agregarlo a listaTareas
        // 4. (Opcional) Guardar inmediatamente en archivo para no perder datos
    }

    public void listarTareas() {
        // TODO: Recorrer listaTareas e imprimir cada una.
        // for (Tarea t : listaTareas) { System.out.println(t); }
    }

    public void cerrarSistema() {
        // TODO: Guardar todo en archivos antes de salir.
        // ManejadorArchivos.guardarTareas(listaTareas);
        
        // TODO: Detener el hilo notificador para que el programa termine bien.
    }
}