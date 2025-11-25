package interfaces;
import modulos.*;

public interface ArreglosInterface<T> {
    T[] agregarElemento(T[] arreglo, T elemento)
         throws IllegalArgumentException;
    
    T buscarElemento(T[] arreglo, int posicion)
         throws IllegalArgumentException, IndexOutOfBoundsException;

    T[] eliminarElemento(T[] arreglo, int posicion)
         throws IllegalArgumentException, IndexOutOfBoundsException;
}