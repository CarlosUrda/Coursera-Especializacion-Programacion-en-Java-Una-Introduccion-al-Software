import edu.duke.FileResource;
import java.util.Arrays;


/**
 * Ejercicio 4 de la Semana 1 del curso «Programacion en Java: Arrays, Listas y Estructuras de Datos».
 * 
 * @author Carlos A. Gómez Urda
 * @version 1.0
 */
public class Caesar
{
    protected final String alpha = "abcdefghijklmnopqrstuvwxyz";
    
    
    /**
     * Obtener el indice del elemento con mayor valor del array.
     * 
     * @param values Array donde encontrar el elemento de mayor valor.
     * @return Índice del elemento con el máximo valor del array.
     */
    protected int maxIndex( int[] values)
    {
        if (values == null || values.length == 0) return -1;
        
        int indiceMax = 0;
        
        for (int i = 1; i < values.length; ++i)
        {
            if (values[i] > values[indiceMax]) indiceMax = i;
        }
        
        return indiceMax;
    }
    
    
    /**
     * Contar el número de letras del alfabeto en una cadena.
     * 
     * @param input Cadena a contar el número de letras..
     * @param counts Número de palabras de cada longitud. El índice de cada longitud viene
     * dado por longitudPalabra-1 => Índices: [0, 1, 2, ..., longitudArray -1]
     * @return 1 Correcto; -1 Error.
     */
    protected int countLetters( String input, int[] counts)
    {        
        if (counts == null || input == null || counts.length < alpha.length()) return -1;
        
        Arrays.fill( counts, 0);
     
        for (char ch : input.toCharArray())
        {
            int indice = alpha.indexOf( ch);
            
            if (indice == -1) continue;
            
            counts[indice]++;
        }
        
        return 1;
    }
}
