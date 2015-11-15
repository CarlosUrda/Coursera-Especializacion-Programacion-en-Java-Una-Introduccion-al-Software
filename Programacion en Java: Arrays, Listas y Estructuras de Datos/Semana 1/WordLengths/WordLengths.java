import edu.duke.FileResource;
import java.util.Arrays;

/**
 * Ejercicio 3 de la Semana 1 del curso «Programacion en Java: Arrays, Listas y Estructuras de Datos».
 * 
 * @author Carlos A. Gómez Urda
 * @version 1.1
 */
public class WordLengths
{
    /**
     * Función para eliminar los caracteres que no son letras del principio y final de una palabra.
     * 
     * @param cadena Cadena a eliminar los caracteres al principio y final.
     * @param caracteres Número de caracteres a comprobar para eliminar por el principio y el final.
     * Si es <= 0 no comprueba nada. Si es >= longitud comprueba todos los caracteres hasta que 
     * encuentra una letra.
     * @return Cadena con los caracteres eliminados.
     */
    private String trimLetras( String cadena, int caracteres)
    {
        if (cadena == null) return null;

        int indiceIni = 0;
        int longitud = cadena.length();
        int indiceFin = longitud;
        
        if (caracteres > longitud) caracteres = longitud;

        for (int i = 0; i < caracteres; ++i)
        {
            if (Character.isLetter( cadena.charAt( i))) break;

            indiceIni = i+1;
        }

        int limite = longitud-1-caracteres;
        for (int i = longitud-1; i > limite; --i)
        {
            if (Character.isLetter( cadena.charAt( i))) break;

            indiceFin = i;
        }
            
        return cadena.substring( indiceIni, indiceFin);
    }
    
    
    /**
     * Función para eliminar los caracteres que no son letras del principio y final de una palabra.
     * 
     * @param cadena Cadena a eliminar los caracteres.
     * @return Cadena con los caracteres eliminados.
     */
    private String trimLetras( String cadena)
    {
        return trimLetras( cadena, cadena.length());
    }

    
    /**
     * Obtener el indice del elemento con mayor valor del array.
     * 
     * @param values Array donde encontrar el elemento de mayor valor.
     * @return Índice del elemento con el máximo valor del array.
     */
    private int indexOfMax( int[] values)
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
     * Contar el número de palabras de cada longitud para todas las palabras de un archivo.
     * Las longitudes a contar van desde 1 hasta la longitud del array pasado. Las palabras
     * con más caracteres que la longitud del array se consideran en el recuento como tamaño 
     * longitud del array.
     * 
     * @param resource Archivo de donde leer las palabras.
     * @param counts Número de palabras de cada longitud. El índice de cada longitud viene
     * dado por longitudPalabra-1 => Índices: [0, 1, 2, ..., longitudArray -1]
     * @return 1 Correcto; -1 Error.
     */
    private int countWordsLengths( FileResource resource, int[] counts)
    {        
        if (counts == null || counts.length == 0) return -1;
     
        Arrays.fill( counts, 0);
        int longitudMax = counts.length;
        
        for (String palabra : resource.words())
        {
            palabra = trimLetras( palabra, 1); 
            
            int longitud = palabra.length();
            if (longitud == 0) continue;
            
            int indiceLong = (longitud > longitudMax) ? longitudMax-1 : longitud-1;
            
            counts[indiceLong]++;
        }
        
        return 1;
    }
    
    
    /**
     * Probar la función countWordsLengths.
     */
    public void testCountWordsLengths()
    {
        int[] counts = new int[30];
        FileResource resource = new FileResource();
        
        if (countWordsLengths( resource, counts) == -1)
        {
            System.out.println( "Error al ejecutar la función countWordsLengths");
            return;
        }
        
        for (int i = 0; i < counts.length; i++)
            System.out.println( "Longitud " + (i+1) + " => " + counts[i] + " ocurrencias");
            
        System.out.println( "La longitud más frecuente es: " + (indexOfMax( counts)+1));
        
    }
}
