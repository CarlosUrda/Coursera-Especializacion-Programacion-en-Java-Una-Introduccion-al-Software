
/**
 * Ejercicio 2 de la Semana 1 del curso «Programacion en Java: Arrays, Listas y Estructuras de Datos».
 * 
 * @author Carlos A. Gómez Urda
 * @version 2.0
 */
public class CaesarCipher extends Caesar
{
    private String alphaFin;     // Alfabeto al cual se mapea el alfabeto base.
    private int key;
    
    
    /**
     * Constructor
     * 
     * @param key Clave a usar en la encriptación.
     */
    public CaesarCipher( int key)
    {
        key %= alpha.length();
        if (key < 0) key += alpha.length();

        this.alphaFin = alpha.substring( key) + alpha.substring( 0, key);
        this.key = key;        
    }
    
    
    /**
     * Cifrar una cadena usando el método «Cifrado del César» a partir de la clave asignada al crear
     * el objeto.
     * 
     * @param input Cadena a cifrar.
     * @return Cadena encriptada.
     */
    public String encrypt( String input)
    {
        if (input == null) return null;
        
        StringBuilder cadena = new StringBuilder( input);
        int longitud = cadena.length();
        
        for (int i = 0; i < longitud; i++)
        {
            char ch = cadena.charAt( i);
            boolean esMinusculas = Character.isLowerCase( ch);

            int indice = alpha.indexOf( Character.toLowerCase( ch));
            if (indice == -1) continue;
            
            ch = alphaFin.charAt( indice);
            if (!esMinusculas) 
                ch = Character.toUpperCase( ch);

            cadena.setCharAt( i, ch);
        }
                    
        return cadena.toString();
    }
    
    
    /**
     * Desencriptar una cadena cifrada usando el método «Cifrado del César» a partir de la clave
     * asignada al objeto.
     * 
     * @param input Cadena encriptada.
     * @return Cadena desencriptada.
     */
    public String decrypt( String encrypted)
    {
        CaesarCipher cipher = new CaesarCipher( alpha.length() - key);
        return cipher.encrypt( encrypted);
    }
    
}
