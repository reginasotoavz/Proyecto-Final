package utilidades;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import modulos.Usuario;
import modulos.Profesor;
import modulos.Ayudante;
import modulos.Tarea;
import java.time.LocalDate;

public class ManejadorArchivos {

    // Ruta donde se guardarán los archivos
    private static final String RUTA_USUARIOS = "data/usuarios.csv";
    private static final String RUTA_TAREAS = "data/tareas.csv";

    // --- GUARDAR USUARIOS ---
    public static void guardarUsuarios(List<Usuario> usuarios) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RUTA_USUARIOS))) {
            for (Usuario u : usuarios) {
                // Formato: TIPO,NOMBRE,CORREO
                // Ejemplo: Profesor,Juan,juan@email.com
                String rol = (u instanceof Profesor) ? "Profesor" : "Ayudante";
                writer.write(rol + "," + u.getNombre() + "," + u.getCorreo());
                writer.newLine();
            }
            System.out.println("Usuarios guardados correctamente.");
        } catch (IOException e) {
            System.err.println("Error al guardar usuarios: " + e.getMessage());
        }
    }

    // --- CARGAR USUARIOS ---
    public static List<Usuario> cargarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        File archivo = new File(RUTA_USUARIOS);
        
        if (!archivo.exists()) return usuarios; // Si no existe, devuelve lista vacía

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 3) {
                    String rol = partes[0];
                    String nombre = partes[1];
                    String correo = partes[2];

                    if (rol.equals("Profesor")) {
                        usuarios.add(new Profesor(nombre, correo));
                    } else if (rol.equals("Ayudante")) {
                        usuarios.add(new Ayudante(nombre, correo));
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error al cargar usuarios: " + e.getMessage());
        }
        return usuarios;
    }
    
    public static void guardarTareas(List<Tareas>tareas) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RUTA_TAREAS))) {
            for (Tarea t : tareas) {
                // Formato: ID,TITULO,FECHA,ESTADO
                writer.write(t.getId() + "," + t.getTitulo() + "," + t.getFecha().toString() + "," + t.getEstado());
                writer.newLine();
            }
            System.out.println("Tareas guardadas correctamente.");
        } catch (IOException e) {
            System.err.println("Error al guardar tareas: " + e.getMessage());
        }
    }
    public static List<Tarea> cargarTareas() {
        List<Tarea> tareas = new ArrayList<>();
        File archivo = new File(RUTA_TAREAS);
        
        if (!archivo.exists()) return tareas; // Si no existe, devuelve lista vacía

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 4) {
                    int id = Integer.parseInt(partes[0]);
                    String titulo = partes[1];
                    LocalDate fecha = LocalDate.parse(partes[2]);
                    String estado = partes[3];

                    Tarea tarea = new Tarea(id, titulo, fecha, estado);
                    tareas.add(tarea);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al cargar tareas: " + e.getMessage());
        }
        return tareas;
    }

}