
package logica;

/**
 * Clase Nodo, contiene el metodo constructor de nodo
 * @author Kendall Rodríguez Camacho 2022049438 y Jocsan Adriel Perez Coto 2022437948 
 */
public class Nodo <T>
{
    public T dato;
    public Nodo<T> predecesor;
    public Nodo<T> sucesor;
    
    Nodo(){
        
        predecesor = null;
        sucesor = null;
        
    }    
}
