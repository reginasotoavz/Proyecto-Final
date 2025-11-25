public class SistemaTareas {
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
}