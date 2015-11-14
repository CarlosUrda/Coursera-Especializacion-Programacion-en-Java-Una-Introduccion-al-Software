import edu.duke.FileResource;

/**
 * Ejercicio 2 de la Semana 1 del curso «Programacion en Java: Arrays, Listas y Estructuras de Datos».
 * 
 * @author Carlos A. Gómez Urda
 * @version 1.0
 */
public class CaesarCipher
{
    /**
     * Cifrar una cadena usando el método «Cifrado del César».
     * 
     * @param input Cadena a cifrar.
     * @param key Clave a usar en el cifrado.
     */
    private String encrypt( String input, int key)
    {
        if (input == null) return null;
        
        String alfaIni = "abcdefghijklmnopqrstuvwxyz";
        key %= alfaIni.length();
        
        String alfaFin = alfaIni.substring( key) + alfaIni.substring( 0, key);
        
        StringBuilder cadena = new StringBuilder( input);
        int longitud = cadena.length();
        
        for (int i = 0; i < longitud; i++)
        {
            char ch = cadena.charAt( i);
            boolean esMinusculas = Character.isLowerCase( ch);

            int indice = alfaIni.indexOf( Character.toLowerCase( ch));
            if (indice == -1) continue;
            
            ch = alfaFin.charAt( indice);
            if (!esMinusculas) 
                ch = Character.toUpperCase( ch);

            cadena.setCharAt( i, ch);
        }
                    
        return cadena.toString();
    }
    
    
    /**
     * Cifrar una cadena usando una variación del método «Cifrado del César».
     * 
     * @param input Cadena a cifrar.
     * @param key1 Clave a usar en el cifrado para las letras impares.
     * @param key2 Clave a usar en el cifrado para las letras pares.
     */
    private String encryptTwoKeys( String input, int key1, int key2)
    {
        if (input == null) return null;
        
        String alfaIni = "abcdefghijklmnopqrstuvwxyz";
        key1 %= alfaIni.length();
        key2 %= alfaIni.length();
        
        String alfaFin1 = alfaIni.substring( key1) + alfaIni.substring( 0, key1);
        String alfaFin2 = alfaIni.substring( key2) + alfaIni.substring( 0, key2);
        
        StringBuilder cadena = new StringBuilder( input);
        int longitud = cadena.length();
        
        for (int i = 0; i < longitud; i++)
        {
            char ch = cadena.charAt( i);
            boolean esMinusculas = Character.isLowerCase( ch);

            int indice = alfaIni.indexOf( Character.toLowerCase( ch));
            if (indice == -1) continue;

            ch = (i % 2 == 0) ? alfaFin1.charAt( indice) : alfaFin2.charAt( indice);
            if (!esMinusculas) 
                ch = Character.toUpperCase( ch);

            cadena.setCharAt( i, ch);
        }
                    
        return cadena.toString();
    }

    
    /**
     * Probar la función encrypt.
     * 
     * @param key Clave a usar en el cifrado.
     */
    public void testEncrypt( int key)
    {
        FileResource frArchivo = new FileResource();
        System.out.println( encrypt( frArchivo.asString(), key));
    }
    /**
     * Probar la función encryptTwoKeys.
     * 
     * @param key Clave a usar en el cifrado.
     */
    public void testEncryptTwoKeys( int key1, int key2)
    {
        FileResource frArchivo = new FileResource();
        System.out.println( encryptTwoKeys( frArchivo.asString(), key1, key2));
    }
    
}
