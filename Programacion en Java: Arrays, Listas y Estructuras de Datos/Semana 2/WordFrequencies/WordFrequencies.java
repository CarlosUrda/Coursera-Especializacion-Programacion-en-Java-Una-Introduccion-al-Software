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
    
    private String signos = ":,.\"";
    
    
    /**
     * Constructor
     */
    public WordFrequencies()
    {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    
    /**
     * Eliminar todos los caracteres que son signos de puntuación al principio y al final.
     * 
     * @param palabra Palabra a eliminar sus signos
     */
    private String eliminarSignos( String palabra)
    {
        if (palabra == null) return null;
        
        palabra = palabra.replaceAll( "[" + signos + "]", " ");
        return palabra.trim();
    }
    
    
    /**
     * Encontrar las palabras distintas en un archivo y obtener el número de veces que 
     * aparece cada una.
     * 
     * @param sinSignos Indica si se deben tener en cuenta los signos junto a una palabra. Si es true
     * los signos se extraen para contar la palabra. Si es false los signos se dejan y se cuenta como
     * una palabra distinta.
     */
    public void findUnique( boolean sinSignos)
    {
        FileResource archivo = new FileResource();
        int indice;
        
        myWords.clear();
        myFreqs.clear();

        for (String palabra : archivo.words())
        {
            palabra = palabra.toLowerCase();
            
            if (sinSignos)
                palabra = eliminarSignos( palabra);

            indice = myWords.indexOf( palabra);
                
            if (indice == -1)
            {
                myWords.add( palabra);
                myFreqs.add( 1);
            }
            else
                myFreqs.set( indice, myFreqs.get( indice) + 1);
        }
             
    }
    
    
    /**
     * Obtener el índice de la palabra más frecuente obtenida anteriormente.
     * 
     * @return Índice de la palabra más frecuente. -1 si no hay ninguna palabra.
     */
    public int findIndexMax()
    {
        int palabras = myFreqs.size();
        int maxIndice = -1;
        int maxFreq = 0;
        
        for (int i = 0; i < palabras; i++)
        {
            int freq = myFreqs.get( i);

            if (freq <= maxFreq) continue;
            
            maxIndice = i;
            maxFreq = freq;                
        }
        
        return maxIndice;
    }
    
    
    /**
     * Probador de las funciones findUnique y findIndexMax.
     */
    public void tester()
    {
        findUnique( false);
        
        int palabras = myWords.size();
        int indiceMax = findIndexMax();

        System.out.println( "El número de palabras distintas son: " + palabras);
        
        for (int i = 0; i < palabras; ++i)
            System.out.println( myFreqs.get( i) + ": " + myWords.get( i));
            
        System.out.println( "La palabra más frecuente es => " + myWords.get( indiceMax) + ": " + 
                            myFreqs.get( indiceMax));                
    }
}
