import edu.duke.FileResource;
import java.util.Arrays;


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
     * Obtener el indice del elemento con mayor valor del array.
     * 
     * @param values Array donde encontrar el elemento de mayor valor.
     * @return Índice del elemento con el máximo valor del array.
     */
    private int maxIndex( int[] values)
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
    private int countLetters( String input, int[] counts)
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

    
    /**
     * Obtener la clave usada para encriptar un texto.
     * 
     * @param encrypted Texto encriptado.
     * @return Clave usada para la encriptar el texto.
     */
    public int getKey( String encrypted)
    {
        int longAlfa = alpha.length();
        int[] counts = new int[longAlfa];
        
        if (countLetters( encrypted, counts) == -1) return -1;
        
        int difIndex = maxIndex( counts) - alpha.indexOf( chFreq);
        return (difIndex >= 0) ? difIndex : difIndex + longAlfa;
    }

    
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
                
        CaesarCipher cipher = new CaesarCipher();
        return cipher.encrypt( encrypted, alpha.length() - key);
    }
    
    
    /**
     * Obtener una cadena con los caracteres alternos a partir de una posición inicial.
     * 
     * @param message Cadena de la cual obtener la nueva cadena.
     * @param start Índice inicial en la cadena a partir del cual empezar a obtener caracteres 
     * alternos.
     * @return Cadena con los caracteres alternos de la cadena original. Null si hay algún error.
     */
    private String halfOfString( String message, int start)
    {
        if (message == null || start < 0) return null;
        
        StringBuilder halfMessage = new StringBuilder();
        
        for (int i = start; i < message.length(); i+=2)
        {
            halfMessage.append( message.charAt( i));
        }
        
        return halfMessage.toString();
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
                
        CaesarCipher cipher = new CaesarCipher();
        return cipher.encryptTwoKeys( encrypted, alpha.length() - key0, alpha.length() - key1);
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
