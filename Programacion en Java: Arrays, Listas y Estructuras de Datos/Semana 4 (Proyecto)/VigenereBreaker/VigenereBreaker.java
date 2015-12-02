import java.util.*;
import edu.duke.*;

/**
 * Ejercicio 1 de la Semana 4 del Curso 3.
 * Primera parte de VigenereBreaker
 * 
 * @author Carlos A. Gómez Urda
 * @version 1.0
 */
public class VigenereBreaker 
{
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
     * @return Clave usada.
     */
    public int[] tryKeyLength( String encrypted, int klength, char mostCommon) 
    {
        int[] key = new int[klength];
        CaesarCracker ccracker = new CaesarCracker( mostCommon);
        
        for (int i = 0; i < klength; i++)
        {
           String slice = sliceString( encrypted, i, klength); 
           key[i] = ccracker.getKey( slice);
        }
        
        return key;
    }

    /**
     * Romper el cifrado de Vigenere realizado en un texto de un archivo, mostrando por pantalla
     * el mensaje desencriptado.
     * 
     * @param longClave Longitud de la clave usada.
     * @param letraComun Letra más común del idioma del mensaje cifrado.
     */
    public void breakVigenere( int longClave, char letraComun) 
    {
        FileResource archivo = new FileResource();
        
        String mensaje = archivo.asString();
        int[] clave = tryKeyLength( mensaje, longClave, Character.toLowerCase( letraComun));
        
        VigenereCipher vcipher = new VigenereCipher( clave);
        System.out.println( vcipher.decrypt( mensaje));
        
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
        int[] clave = tryKeyLength( mensaje, longClave, Character.toLowerCase( letraComun));
        System.out.println( Arrays.toString( clave));
    }
}
