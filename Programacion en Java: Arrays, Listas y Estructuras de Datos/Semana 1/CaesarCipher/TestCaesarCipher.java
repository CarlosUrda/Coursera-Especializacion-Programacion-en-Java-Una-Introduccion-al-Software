import edu.duke.FileResource;


/**
 * Ejercicio 5 de la Semana 1 del curso «Programacion en Java: Arrays, Listas y Estructuras de Datos».
 * 
 * @author Carlos A. Gómez Urda
 * @version 1.0
 */
public class TestCaesarCipher extends Caesar
{
    
    /**
     * Desencriptar un texto encriptado mediante el método de cifrado del César.
     * 
     * @param encrypted Texto a desencriptar.
     * @return Cadena de texto desencriptada. Null si hay algún error.
     */
    public String breakCaesarCipher( String encrypted)
    {
        if (encrypted == null) return null;
        
        int key = getKey( encrypted);
                
        CaesarCipher cipher = new CaesarCipher( alpha.length() - key);
        return cipher.encrypt( encrypted);
    }
    

    /**
     * Probar la función encrypt.
     */
    public void simpleTests( int key)
    {
        FileResource frArchivo = new FileResource();
        CaesarCipher cipher = new CaesarCipher( key);

        String original = frArchivo.asString();
        String encriptado = cipher.encrypt( original);
        String desencriptado = cipher.decrypt( encriptado);
        String hackeado = breakCaesarCipher( encriptado);

        System.out.println( "Original:\n" + original);
        System.out.println( "Encriptado con clave " + key + ":\n" + encriptado);
        System.out.println( "Desencriptado con clave " + key + ":\n" + desencriptado);
        System.out.println( "Hackeado:\n" + hackeado);
    }
        
}
