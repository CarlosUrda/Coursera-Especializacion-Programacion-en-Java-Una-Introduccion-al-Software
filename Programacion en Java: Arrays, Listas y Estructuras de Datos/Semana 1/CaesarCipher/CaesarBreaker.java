import edu.duke.FileResource;
import java.util.Arrays;


/**
 * Ejercicio 4 de la Semana 1 del curso «Programacion en Java: Arrays, Listas y Estructuras de Datos».
 * 
 * @author Carlos A. Gómez Urda
 * @version 2.0
 */
public class CaesarBreaker extends Caesar
{    
    
    /**
     * Desencriptar un texto encriptado mediante el método de cifrado del César.
     * 
     * @param encrypted Texto a desencriptar.
     * @return Cadena de texto desencriptada. Null si hay algún error.
     */
    public String decrypt( String encrypted)
    {
        if (encrypted == null) return null;
        
        int key = getKey( encrypted);
                
        CaesarCipher cipher = new CaesarCipher( alpha.length() - key);
        return cipher.encrypt( encrypted);
    }
    
    
    /**
     * Desencriptar un texto encriptado mediante el método de cifrado del César con dos claves.
     * 
     * @param encrypted Texto a desencriptar.
     * @return Cadena de texto desencriptada. Null si hay algún error.
     */
    public String decryptTwoKeys( String encrypted)
    {
        if (encrypted == null) return null;
        
        String encrypted0 = halfOfString( encrypted, 0);
        String encrypted1 = halfOfString( encrypted, 1);

        int key0 = getKey( encrypted0);
        int key1 = getKey( encrypted1);
        
        System.out.println( "Las claves son: " + key0 + " y " + key1);
                
        CaesarCipherTwo cipher = new CaesarCipherTwo( alpha.length() - key0, alpha.length() - key1);
        return cipher.encrypt( encrypted);
    }
    
    
    /**
     * Probar la función decrypt.
     */
    public void testDecrypt()
    {
        FileResource resource = new FileResource();

        String encrypted = resource.asString();
        
        String decrypted = decrypt( encrypted);
        if (decrypted == null)
        {
            System.out.println( "Error al ejecutar la función decrypt");
            return;
        }
        
        System.out.println( "Texto encriptado:\n" + encrypted);            
        System.out.println( "\nTexto desencriptado:\n" + decrypted);
        
    }
        
    /**
     * Probar la función halfOfString.
     */
    public void testHalfOfString()
    {
        String cadena = "Qbkm Zgis";
        System.out.println( "Texto original: " + cadena);
        System.out.println( "Texto medio 0: " + halfOfString( cadena, 0));
        System.out.println( "Texto medio 1: " + halfOfString( cadena, 1));
    }
    
    /**
     * Probar la función decryptTwoKeys.
     */
    public void testDecryptTwoKeys()
    {
        FileResource resource = new FileResource();

        String encrypted = resource.asString();
        
        String decrypted = decryptTwoKeys( encrypted);
        if (decrypted == null)
        {
            System.out.println( "Error al ejecutar la función decryptTwoKeys");
            return;
        }
        
        System.out.println( "Texto encriptado:\n" + encrypted);            
        System.out.println( "\nTexto desencriptado:\n" + decrypted);
        
    }
    
}
