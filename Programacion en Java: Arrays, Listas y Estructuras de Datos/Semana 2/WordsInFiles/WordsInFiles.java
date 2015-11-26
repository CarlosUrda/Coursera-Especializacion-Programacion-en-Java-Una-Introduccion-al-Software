import edu.duke.*;
import java.util.*;
import java.io.*;


/**
 * Ejercicio 5 de la Semana 2 del Curso 3: 
 * Programa para encontrar las palabras que ocurren en el mayor número de archivos.
 * 
 * @author Carlos A. Gómez Urda 
 * @version 1.0
 */
public class WordsInFiles
{    
    private HashMap<String,ArrayList<String>> palabraMapArchivos;  // Diccionario: palabra=>{archivos}
    private String signos = ":,.\"";

    
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
     * Constructor for objects of class WordsInFiles
     */
    public WordsInFiles()
    {
        palabraMapArchivos = new HashMap<String,ArrayList<String>>();
    }
    
    
    /**
     * Añade todas las palabras de un archivo al diccionario indicando el nombre del archivo
     * en la lista de archivos a la que mapea cada palabra. Si la palabra ya se encontraba en
     * el diccionario simplemente añade el nombre del archivo a su lista mapeada en caso de
     * no estar ya incluida.
     * 
     * @param f Archivo de donde obtener las palabras.
     * @param sinSignos Indica si se deben tener en cuenta los signos junto a una palabra. Si es true
     * los signos se extraen y no se tienen en cuenta. Si es false los signos se dejan y se tienen 
     * en cuenta como parte de la palabra.
     */
    private void addWordsFromFile( File f, boolean sinSigno)
    {
        FileResource fr = new FileResource( f);
        ArrayList<String> archivos;
        
        String archivo = f.getName();
        
        for (String palabra : fr.words())
        {
            if (sinSigno) palabra = eliminarSignos( palabra);
            
            archivos = palabraMapArchivos.get( palabra);
            
            if (archivos == null)
            {
                archivos = new ArrayList<String>();
                archivos.add( archivo);
                palabraMapArchivos.put( palabra, archivos);
            }
            else
            {
                if (archivos.contains( archivo)) continue;
                
                archivos.add( archivo);
            }
            
        }
    }
    
    
    /**
     * Construye un diccionario de palabras mapeando cada palabra a los archivos donde aparece.
     * Solicita al usuario una serie de archivos y construye el diccionario a partir de todas las
     * palabras de estos archivos seleccionados.
     * 
     * @param sinSignos Indica si se deben tener en cuenta los signos junto a una palabra. Si es true
     * los signos se extraen y no se tienen en cuenta. Si es false los signos se dejan y se tienen 
     * en cuenta como parte de la palabra.
     */
    public void buildWordFileMap( boolean sinSigno)
    {
        DirectoryResource dr = new DirectoryResource();
        
        palabraMapArchivos.clear();
        
        for (File f : dr.selectedFiles())
        {
            addWordsFromFile( f, sinSigno);
        }
    }
    
    
    /**
     * Obtener el número máximo de archivos en los que aparecido alguna palabra registrada en 
     * el diccionario construido anteriormente.
     * 
     * @return Número máximo de archivos aparecidos por alguna palabra.
     */
    public int maxNumber()
    {
        int maxNumArchs = 0, numArchs;
        ArrayList<String> archivos;
                
        for (String palabra : palabraMapArchivos.keySet())
        {
            archivos = palabraMapArchivos.get( palabra);
            numArchs = archivos.size();
            if (numArchs > maxNumArchs) maxNumArchs = numArchs;
        }
        
        return maxNumArchs;
    }
    
    
    /**
     * Obtener las palabras que aparecen en exactamente un número determinado de archivos.
     * 
     * @param number Número de archivos en los que debe aparecer alguna palabra.
     * @return Lista de palabras que aparecen en el número de archivos determinado.
     */
    public ArrayList<String> wordsInNumFiles( int number)
    {
        ArrayList<String> archivos;
        ArrayList<String> palabras = new ArrayList<String>();
                
        for (String palabra : palabraMapArchivos.keySet())
        {
            archivos = palabraMapArchivos.get( palabra);
            if (archivos.size() == number) palabras.add( palabra);
        }
        
        return palabras;        
    }
    
    
    /**
     * Imprimir los archivos en los cuales una palabra ha aparecido. La información se obtiene
     * a partir del diccionario ya calculado anteriormente.
     * 
     * @param word Palabra a mostrar los archivos en los que ha aparecido.
     */
    public void printFilesIn( String word)
    {
        ArrayList<String> archivos = palabraMapArchivos.get( word);
        
        for (String archivo : archivos)
            System.out.print( archivo + " ");
    }
    
    
    /**
     * Probador de todas las funciones de esta clase
     * 
     * @param mostrarTodo Si se desea mostrar todas las palabras con los archivos en los que 
     * aparecen.
     */
    public void tester( boolean mostrarTodo)
    {
        buildWordFileMap( false);
        
        int maxNumArchs = maxNumber();
        
        System.out.println( "Número máximo de archivos de cualquier palabra: " + maxNumArchs);
        
        ArrayList<String> palabras = wordsInNumFiles( maxNumArchs);
      
        System.out.println( "\n\nTotal palabras que aparecen en tal número de archivos: " + palabras.size());
        System.out.println( "\n\nPalabras que aparecen en tal número de archivos:");
        for (String palabra : palabras)
        {
            System.out.println( "\nPalabra => " + palabra);
            printFilesIn( palabra);
        }
        
        if (!mostrarTodo) return;
        
        System.out.println( "\n\nTodas las palabras y los archivos en los que aparecen:");
        for (String palabra : palabraMapArchivos.keySet())
        {
            System.out.println( "\nPalabra => " + palabra);
            printFilesIn( palabra);
        }
        
    }

    /**
     * Probador de todas las funciones de esta clase
     * 
     * @param numArchivos Número de archivos en los que aparecen las palabras a considerar.
     */
    public void tester2( int numArchivos)
    {
        buildWordFileMap( false);
        
        ArrayList<String> palabras = wordsInNumFiles( numArchivos);
      
        System.out.println( "\n\nTotal palabras que aparecen en " + numArchivos + " archivos: " + palabras.size());
        System.out.println( "\n\nPalabras que aparecen en tal número de archivos:");
        for (String palabra : palabras)
        {
            System.out.println( "\nPalabra => " + palabra);
            printFilesIn( palabra);
        }        
    }
}
