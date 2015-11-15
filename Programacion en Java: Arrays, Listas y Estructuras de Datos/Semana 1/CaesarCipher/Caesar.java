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
        int longAlfa = alpha.length();
        int[] counts = new int[longAlfa];
        
        if (countLetters( encrypted, counts) == -1) return -1;
        
        int difIndex = maxIndex( counts) - alpha.indexOf( chFreq);
        return (difIndex >= 0) ? difIndex : difIndex + longAlfa;
    }

    
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
    
    
    /**
     * Obtener una cadena con los caracteres alternos a partir de una posición inicial.
     * 
     * @param message Cadena de la cual obtener la nueva cadena.
     * @param start Índice inicial en la cadena a partir del cual empezar a obtener caracteres 
     * alternos.
     * @return Cadena con los caracteres alternos de la cadena original. Null si hay algún error.
     */
    protected String halfOfString( String message, int start)
    {
        if (message == null || start < 0) return null;
        
        StringBuilder halfMessage = new StringBuilder();
        
        for (int i = start; i < message.length(); i+=2)
        {
            halfMessage.append( message.charAt( i));
        }
        
        return halfMessage.toString();
    }
        
}
