package utilidades;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import modulos.Usuario;
import modulos.Profesor;
import modulos.Ayudante;
import modulos.Tarea;

public class ManejadorArchivos {

    // Ruta donde se guardarán los archivos
    private static final String RUTA_USUARIOS = "data/usuarios.csv";
    private static final String RUTA_TAREAS = "data/tareas.csv";

    // GUARDAR USUARIOS
    public static void guardarUsuarios(List<Usuario> usuarios) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RUTA_USUARIOS))) {
            for (Usuario u: usuarios) {
                writer.write(rol + "," + u.getNombre() + "," + u.getCorreo()+ "," + u.getPassword());
                writer.newLine();
            System.out.println("Usuarios guardados correctamente.");
        } 
        } catch (IOException e) {
            System.err.println("Error al guardar usuarios: " + e.getMessage());
        }
    }

    // CARGAR USUARIOS
    public static List<Usuario> cargarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        File archivo = new File(RUTA_USUARIOS);
        
        if (!archivo.exists()) return usuarios;

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 4) {
                    String rol = partes[0];
                    String nombre = partes[1];
                    String correo = partes[2];
                    String password = partes[3];

                    if (rol.equals("Profesor")) {
                        usuarios.add(new Profesor(nombre, correo, password));
                    } else if (rol.equals("Ayudante")) {
                        usuarios.add(new Ayudante(nombre, correo, password));
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error al cargar usuarios: " + e.getMessage());
        }
        return usuarios;
    }
    
    // GUARDAR TAREAS
    public static void guardarTareas(List<Tarea>tareas) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RUTA_TAREAS))) {
            for (Tarea t : tareas) {
                writer.write(t.getId() + "," + t.getTitulo() + "," + t.getDescripcion + "," + t.getFechaLimite().toString() + "," + t.getUsuarioAsignado + "," + t.getEstado());
                writer.newLine();
            }
            System.out.println("Tareas guardadas correctamente.");
        } catch (IOException e) {
            System.err.println("Error al guardar tareas: " + e.getMessage());
        }
    }

    // CARGAR TAREAS
    public static List<Tarea> cargarTareas() {
        List<Tarea> tareas = new ArrayList<>();
        File archivo = new File(RUTA_TAREAS);
        
        if (!archivo.exists()) return tareas;

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 6) {
                    try {
                    int id = Integer.parseInt(partes[0]);
                    String titulo = partes[1];
                    String descripcion = partes [2]
                    String fechaStr = partes[3];
                    String asignado = partes[4];
                    String estado = partes[5];

                    Tarea t = new Tarea(id, titulo, descripcion, fechaStr, asignado);
                    t.setEstado(estado);
                    
                    tareas.add(tarea);
        } catch (NumberFormatException e) {
            System.err.println("Error al leer ID de tarea: " + linea);
        }
    }
}
} catch (IOException e) {
    System.err.println("Error al cargar tareas: "+ e.getMessage());
}
return tareas;
}
}