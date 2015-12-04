import java.util.*;
import edu.duke.*;
import org.apache.commons.lang3.*;

/**
 * Ejercicio 1 y 2 de la Semana 4 del Curso 3.
 * Primera parte de VigenereBreaker
 * 
 * @author Carlos A. Gómez Urda
 * @version 1.1
 */
public class VigenereBreaker 
{
    private HashSet<String> dictionary;
    
    private int maxLongClave;         // Máxima longitud de clave a buscar.
    private int[] ultimaClave;        // Última clave usada.
    private int mejorPalabrasReales;  // Mejor número de palabras reales encontradas entre los descifrados de distintos tamaños de clave.
    
    private String alfabeto = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    
    
    /**
     * Constructor
     * 
     * @param Máximo tamaño de clave a probar para romper el cifrado.
     */
    public VigenereBreaker( int maxLongClave)
    {
        this.maxLongClave = maxLongClave;
    }
    
    
    /**
     * Constructor
     */
    public VigenereBreaker()
    {
        this( 100);
    }

    
    /**
     * Obtener un diccionario.
     * 
     * @param fr Archivo con todas las palabras del diccionario de un idioma. Cada línea contiene
     * una palabra.
     * @return Conjunto de palabras del diccionario.
     */
    private HashSet<String> readDictionary( FileResource fr)
    {
        HashSet<String> diccionario = new HashSet<String>();

        for (String palabra : fr.lines())
            diccionario.add( palabra.toLowerCase());
     
        return diccionario;
    }
    
    
    /**
     * Contar el número de palabras de un mensaje que se encuentran dentro de un diccionario.
     * 
     * @param message Mensaje a contar
     */
    private int countWords( String message, HashSet<String> dictionary)
    {
        int numPalabras = 0;
        String[] palabras = message.split( "\\W");
                
        for (String palabra : palabras)
        {
            if (dictionary.contains( palabra.toLowerCase()))
                numPalabras++;
        }
        
        return numPalabras;
    }
    

    /**
     * Obtener la letra más frecuente a partir de la frecuencia de letras en una tabla Map. Las
     * frecuencias deben ser positivas.
     * 
     * @param frecLetras Tabla Hash con la frecuencia por cada letra.
     * @return Letra más frecuente
     */
    private char letraMasFrec( HashMap<Character,Integer> frecLetras)
    {
        int maxFrec = 0;
        char maxLetra = 0;
        
        for (char letra : frecLetras.keySet())
        {       
            int frec = frecLetras.get( letra);
            if (frec <= maxFrec) continue;

            maxLetra = letra;
            maxFrec = frec; 
        }
        
        return maxLetra;
    }


    
    /**
     * A partir de una cadena obtener otra cadena formada por los caracteres de una posición concreta
     * whichSlice dentro de cada una de las ventanas de tamaño totalSlices. Obtener los caracteres
     * cuya posición módulo totalSlices es whichSlice.
     * 
     * @param whichSlice Posición dentro de la ventana de los caracteres a obtener.
     * @param totalSlices Tamaño de la ventana.
     * @return Cadena obtenida con los caracteres correctos dentro de cada ventana.
     */
    private String sliceString(String message, int whichSlice, int totalSlices) 
    {
        StringBuilder slice = new StringBuilder();
        
        for (int i = whichSlice % totalSlices; i < message.length(); i+=totalSlices)
            slice.append( message.charAt( i));
        
        return slice.toString();
    }

    
    /**
     * Calcular la clave usada para encriptar un mensaje.
     * 
     * @param klength Longitud de la clave.
     * @param mostCommon Letra más común del lenguaje del mensaje.
     * @param alpha Alfabeto del mensaje encriptado.
     * @return Clave usada.
     */
    private int[] tryKeyLength( String encrypted, int klength, char mostCommon, String alpha) 
    {
        int[] key = new int[klength];
        CaesarCracker ccracker = new CaesarCracker( mostCommon, alpha);
        
        for (int i = 0; i < klength; i++)
        {
           String slice = sliceString( encrypted, i, klength); 
           key[i] = ccracker.getKey( slice);
        }
        
        return key;
    }

    
    /**
     * Romper el cifrado de Vigenere con un tamaño de clave determinada.
     * 
     * @param Mensaje encriptado.
     * @param longClave Longitud de la clave usada.
     * @param letraComun Letra más común del idioma del mensaje cifrado.
     * @param alfa Alfabeto del mensaje.
     * @return Mensaje desencriptado.
     */
    public String breakVigenere( String encriptado, int longClave, char letraComun, String alfa) 
    {
        ultimaClave = tryKeyLength( encriptado, longClave, letraComun, alfa);
        
        VigenereCipher vcipher = new VigenereCipher( ultimaClave, alfa);
        return vcipher.decrypt( encriptado);        
    }

    
    /**
     * Romper el cifrado de Vigenere con longitud de clave desconocida.
     * 
     * @param encrypted Texto encryptado con cifrado de Vigenere.
     * @param dictionary Diccionario de palabras del lenguaje del texto cifrado.
     * @return Texto desencriptado.
     */
    public String breakForLanguage( String encrypted, HashSet<String> dictionary)
    {
        HashMap<Character,Integer> frecLetras = contarLetrasDiccionario( dictionary);
        char letraMasComun = letraMasFrec( frecLetras);
//         Object[] arrayLetras = frecLetras.keySet().toArray();
//         Character[] letrasAlfa = Arrays.copyOf( arrayLetras, arrayLetras.length, Character[].class);
//         String alfa = new String( ArrayUtils.toPrimitive( letrasAlfa));
        String alfa = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        
        mejorPalabrasReales = 0;
        int mejorLongClave = 0;
        
        for (int longClave = 1; longClave <= maxLongClave; longClave++)
        {
            String decrypted = breakVigenere( encrypted, longClave, letraMasComun, alfa);

            int palabrasReales = countWords( decrypted, dictionary);
            if (palabrasReales <= mejorPalabrasReales) continue;
            
            mejorPalabrasReales = palabrasReales;
            mejorLongClave = longClave;
        }
        
        return breakVigenere( encrypted, mejorLongClave, letraMasComun, alfa);        
    }
    
    
    /**
     * Contar el la frecuencia con que aparece una letra en un diccionario.
     * 
     * @param diccionario Diccionario de un lenguaje.
     * @return Tabla con la frecuencia por cada letra.
     */
    private HashMap<Character,Integer> contarLetrasDiccionario( HashSet<String> diccionario)
    {
        HashMap<Character,Integer> frecLetras = new HashMap<Character,Integer>();
        
        for (String palabra : diccionario)
        {
            palabra = palabra.trim();
            
            for (char letra : palabra.toCharArray())
                frecLetras.put( letra, frecLetras.getOrDefault( letra, 0) + 1);
        }
        
        return frecLetras;
    }
    
    
    /**
     * Probador del método tryKeyLength.
     * 
     * @param longClave Longitud de la clave usada.
     * @param letraComun Letra más común del idioma del mensaje cifrado.
     */
    public void testTryKeyLength( int longClave, char letraComun)
    {
        FileResource archivo = new FileResource();
        
        String mensaje = archivo.asString();
        int[] clave = tryKeyLength( mensaje, longClave, Character.toLowerCase( letraComun), alfabeto);
        System.out.println( Arrays.toString( clave));
    }

    
    /**
     * Probador de la función breakVigenere
     * 
     * @param longClave Longitud de la clave usada.
     * @param letraComun Letra más común del idioma del mensaje cifrado.
     */
    public void testBreakVigenere( int longClave, char letraComun) 
    {
        FileResource archivo = new FileResource();        
        String mensaje = archivo.asString();
        
        System.out.println( "*** Mensaje desencriptado ***");
        System.out.println( breakVigenere( mensaje, longClave, letraComun, alfabeto));
    }    
    

    /**
     * Probador de la función breakForLanguage
     * 
     * @param longClave Longitud de la clave usada.
     * @param letraComun Letra más común del idioma del mensaje cifrado.
     */
    public void testBreakForLanguage() 
    {
        FileResource archivoMen = new FileResource();        
        String mensaje = archivoMen.asString();                

        FileResource archivoDic = new FileResource();        
        
        System.out.println( breakForLanguage( mensaje, readDictionary( archivoDic)));
        System.out.println( ultimaClave.length + " => " + Arrays.toString( ultimaClave));
        System.out.println( "Palabras reales: " + mejorPalabrasReales);
    }    
    
}
