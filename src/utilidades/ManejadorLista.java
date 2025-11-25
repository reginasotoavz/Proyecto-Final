package interfaces;
import interfaces.*;
import java.util.Arrays;

public class ManejadorLista<T> implements ArreglosInterface<T> {
    @Override
    public T[] agregarElemento(T[] arreglo, T elemento)
         throws IllegalArgumentException {
        if (arreglo == null) throw new IllegalArgumentException("El arreglo no puede ser nulo.");
        if (elemento == null) throw new IllegalArgumentException("El elemento no puede ser nulo.");
        T[] nuevoArreglo = Arrays.copyOf(arreglo, arreglo.length + 1);
        nuevoArreglo[arreglo.length] = elemento;
        return nuevoArreglo;
    }

    @Override
    public T buscarElemento(T[] arreglo, int posicion)
         throws IllegalArgumentException, IndexOutOfBoundsException {
        if (arreglo == null) throw new IllegalArgumentException("El arreglo no puede ser nulo.");
        if (posicion < 0 || posicion >= arreglo.length) {
            throw new IndexOutOfBoundsException("Posición fuera de los límites del arreglo.");
        }
        return arreglo[posicion];
    }

    @Override
    public T[] eliminarElemento(T[] arreglo, int posicion)
         throws IllegalArgumentException, IndexOutOfBoundsException {
        if (arreglo == null) throw new IllegalArgumentException("El arreglo no puede ser nulo.");
        if (posicion < 0 || posicion >= arreglo.length) {
            throw new IndexOutOfBoundsException("Posición fuera de los límites del arreglo.");
        }
        T[] nuevoArreglo = Arrays.copyOf(arreglo, arreglo.length - 1);
        int j = 0;
        for (int i = 0; i < arreglo.length; i++) {
            if (i != posicion) {
                nuevoArreglo[j++] = arreglo[i];
            }
        }
        return nuevoArreglo;
    }
}