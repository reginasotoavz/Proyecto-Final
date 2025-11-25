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
                String tipo = (u instanceof Profesor) ? "Profesor" : "Ayudante";
                writer.write(tipo + "," + u.getNombre() + "," + u.getEmail());
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
                    String tipo = partes[0];
                    String nombre = partes[1];
                    String email = partes[2];

                    if (tipo.equals("Profesor")) {
                        usuarios.add(new Profesor(nombre, email));
                    } else if (tipo.equals("Ayudante")) {
                        usuarios.add(new Ayudante(nombre, email));
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error al cargar usuarios: " + e.getMessage());
        }
        return usuarios;
    }
    
    // --- AQUÍ FALTARÍA LA LÓGICA SIMILAR PARA TAREAS (ID,TITULO,FECHA,ESTADO) ---
}