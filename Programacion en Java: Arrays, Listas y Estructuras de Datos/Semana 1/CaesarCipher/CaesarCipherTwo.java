
/**
 * Ejercicio 5 de la Semana 1 del curso «Programacion en Java: Arrays, Listas y Estructuras de Datos».
 * 
 * @author Carlos A. Gómez Urda
 * @version 1.0
 */
public class CaesarCipherTwo extends Caesar
{
    private String alphaFin1;
    private String alphaFin2;
    private int key1;
    private int key2;
    
    
    /**
     * Constructor
     * 
     * @param key1 Primera clave a usar en la encriptación.
     * @param key2 Segunda clave a usar en la encriptación.
     */
    public CaesarCipherTwo( int key1, int key2)
    {
        key1 = key1 % alpha.length();
        if (key1 < 0) key1 += alpha.length();

        key2 = key2 % alpha.length();
        if (key2 < 0) key2 += alpha.length();

        this.alphaFin1 = alpha.substring( key1) + alpha.substring( 0, key1);
        this.alphaFin2 = alpha.substring( key2) + alpha.substring( 0, key2);
        
        this.key1 = key1;
        this.key2 = key2;
    }
    

    /**
     * Cifrar una cadena usando una variación del método «Cifrado del César».
     * 
     * @param input Cadena a cifrar.
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

            ch = (i % 2 == 0) ? alphaFin1.charAt( indice) : alphaFin2.charAt( indice);
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
     * @param encrypted Cadena encriptada.
     * @return Cadena desencriptada.
     */
    public String decrypt( String encrypted)
    {
        CaesarCipherTwo cipher = new CaesarCipherTwo( alpha.length() - key1, alpha.length() - key2);
        return cipher.encrypt( encrypted);
    }
    
}
