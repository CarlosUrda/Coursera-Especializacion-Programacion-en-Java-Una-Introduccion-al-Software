import java.util.ArrayList;
import edu.duke.FileResource;


/**
 * Ejercicio 1 de la Semana 2 del Curso Programación en Java: Arrays, Listas, y Estructuras de Datos.
 * 
 * Determinar la palabra más frecuente en un archivo.
 * 
 * @author Carlos A. Gómez Urda
 * @version 1.0
 */
public class WordFrequencies
{
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public WordFrequencies()
    {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    
    /**
     * Encontrar las palabras distintas en un archivo y obtener el número de veces que 
     * aparece cada una.
     * 
     * @param sinSignos Indica si se deben tener en cuenta los signos junto a una palabra. Si es true
     * los signos se extraen para contar la palabra. Si es false los signos se dejan y se cuenta como
     * una palabra distinta.
     */
    public void findUnique( boolean quitarSignos)
    {
        
        
    }
}
