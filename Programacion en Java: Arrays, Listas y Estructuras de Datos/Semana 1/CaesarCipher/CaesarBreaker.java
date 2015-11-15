import edu.duke.FileResource;

/**
 * Ejercicio 4 de la Semana 1 del curso «Programacion en Java: Arrays, Listas y Estructuras de Datos».
 * 
 * @author Carlos A. Gómez Urda
 * @version 1.0
 */
public class CaesarBreaker extends Caesar
{
    private char chFreq = 'e';
    
    
    /**
     * Cambiar la letra más frecuente del alfabeto.
     * 
     * @param chFreq Nueva letra más frecuente en el alfabeto. Tiene que ser una de las letras
     * presentes en el alfabeto.
     */
    public void setChFreq( char chFreq)
    {
        if (alpha.indexOf( chFreq) == -1) return;

        this.chFreq = chFreq;
    }
    
    
    /**
     * Obtener la letra más frecuente del alfabeto.
     * 
     * @return Letra establecida como más frecuente en el alfabeto.
     */
    public char getChFreq()
    {
        return this.chFreq;
    }

    
    /**
     * Obtener la clave usada para encriptar un texto.
     * 
     * @param encrypted Texto encriptado.
     * @return Clave usada para la encriptar el texto.
     */
    public int getKey( String encrypted)
    {
        int[] counts = new int[alpha.length()];
        
        if (countLetters( encrypted, counts) == -1) return -1;
        
        return (maxIndex( counts) - alpha.indexOf( chFreq)) % alpha.length();
    }

    
    /**
     * Desencriptar un texto encriptado mediante el método de cifrado del César.
     * 
     * @param encrypted Texto a desencriptar.
     * @param chFreq Letra más frecuente en el alfabeto.
     * @return Cadena de texto desencriptada. Null si hay algún error.
     */
    public String decrypt( String encrypted)
    {
        if (encrypted == null) return null;
        
        int key = getKey( encrypted);
                
        CaesarCipher cipher = new CaesarCipher();
        return cipher.encrypt( encrypted, alpha.length() - key);
    }
    
    
    /**
     * Probar la función countWordsLengths.
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
}
