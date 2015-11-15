import edu.duke.FileResource;


/**
 * Ejercicio 6 de la Semana 1 del curso «Programacion en Java: Arrays, Listas y Estructuras de Datos».
 * 
 * @author Carlos A. Gómez Urda
 * @version 1.0
 */
public class TestCaesarCipherTwo extends Caesar
{
    /**
     * Desencriptar un texto encriptado mediante el método de cifrado del César con dos claves.
     * 
     * @param encrypted Texto a desencriptar.
     * @return Cadena de texto desencriptada. Null si hay algún error.
     */
    public String breakCaesarCipher( String encrypted)
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
     * Probar la función encrypt.
     */
    public void simpleTests( int key1, int key2)
    {
        FileResource frArchivo = new FileResource();
        CaesarCipherTwo cipher = new CaesarCipherTwo( key1, key2);

        String original = frArchivo.asString();
        String encriptado = cipher.encrypt( original);
        String desencriptado = cipher.decrypt( encriptado);
        String hackeado = breakCaesarCipher( encriptado);

        System.out.println( "Original:\n" + original);
        System.out.println( "Encriptado con claves " + key1 + " y " + key2 + ":\n" + encriptado);
        System.out.println( "Desencriptado con clave " + key1 + " y " + key2 + ":\n" + desencriptado);
        System.out.println( "Hackeado:\n" + hackeado);
    }
        
}
