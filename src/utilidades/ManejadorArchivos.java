package utilidades;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class ManejadorArchivos {
    public static List<String> leerArchivo(String rutaArchivo) {
        File archivo = new File(rutaArchivo);
        if (!archivo.exists()) {
            return new String [0];
        }
        List<String> lineas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                lineas.add(linea);
            }
            catch (IOException e) {
                System.err.println("Error al leer el archivo: " + e.getMessage());
                return new String[0];
        }
        return lineas.toArray(new String[0]);
    }

public static String buscarLinea(String cadenaBuscada, String rutaArchivo) {
    File archivo = new File(rutaArchivo);
    if (!archivo.exists()) {
        return null;
    }
    try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
        String linea;
        while ((linea = br.readLine()) != null) {
            if (linea.contains(cadenaBuscada)) {
                return linea;
            }
        }
    } catch (IOException e) {
        System.err.println("Error al leer el archivo: " + e.getMessage());
    }
    return null;
}

public static void escribirLineaAlFinal(String linea, String rutaArchivo) {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo, true))) {
        bw.write(linea);
        bw.newLine();
    } catch (IOException e) {
        System.err.println("Error al escribir en el archivo: " + e.getMessage());
    }
}

