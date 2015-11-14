
/**
 * Ejercicio 1 de la Semana 1 del curso «Programacion en Java: Arrays, Listas y Estructuras de Datos».
 * 
 * @author Carlos A. Gómez Urda
 * @version 1.0
 */
public class WordPlay
{
    /**
     * Comprobar si un carácter es una vocal (mayúsculas o minúsculas)
     * 
     * @param ch Carácter a comprobar
     * @return True o False si es una vocal o no.
     */
    private boolean isVowel( char ch)
    {
        String vocales = "aeiou";
        
        return vocales.contains( String.valueOf( Character.toLowerCase( ch)));
        
    }
    
    
    /**
     * Sustituir las vocales de una cadena por un carácter determinado.
     * 
     * @param phrase Cadena en la cual realizar la sustitución.
     * @param ch Carácter a sustituir.
     * @return Cadena con las vocales sustituidas. Null si hay algún error.
     */
    private String replaceVowels( String phrase, char ch)
    {
        if (phrase == null) return null;
        
        StringBuilder cadena = new StringBuilder( phrase);
        int longitud = cadena.length();
        
        for (int i = 0; i < longitud; i++)
            if (isVowel( cadena.charAt( i))) cadena.setCharAt( i, ch);
        
        return cadena.toString();
    }
    
    
    /**
     * Sustituir en una cadena un carácter determinado por '*' si está en posición impar o '+' si
     * está en posición par.
     * 
     * @param phrase Cadena en la cual realizar la sustitución.
     * @param ch Carácter a sustituir (ya esté en mayus o minus).
     * @return Cadena con las vocales sustituidas. Null si hay algún error.
     */
    private String emphasize( String phrase, char ch)
    {
        if (phrase == null) return null;
        
        StringBuilder cadena = new StringBuilder( phrase);
        int longitud = cadena.length();
        
        ch = Character.toLowerCase( ch);
        
        for (int i = 0; i < longitud; i++)
        {
            if (ch != Character.toLowerCase( cadena.charAt( i))) continue;
            
            cadena.setCharAt( i, (i % 2) == 0 ? '*' : '+');            
        }
                    
        return cadena.toString();
    }

    
    /**
     * Probar la función isVowel
     * 
     * @param ch Carácter a comprobar
     */
    public void testIsVowel( char ch)
    {
        String esVocal = isVowel( ch) ? "Verdad" : "Falso";
        System.out.println( "Carácter " + ch + ": " + esVocal);
    }
    
    /**
     * Probar la función replaceVowels
     * 
     * @param cadena Cadena a sustituir las vocales por un carácter
     * @param ch Carácter a sustituir.
     */
    public void testReplaceVowel( String cadena, char ch)
    {
        System.out.println( cadena + " (" + ch + ") => " + replaceVowels( cadena, ch));
    }
    
    /**
     * Probar la función emphasize.
     * 
     * @param phrase Cadena en la cual realizar la sustitución.
     * @param ch Carácter a sustituir (ya esté en mayus o minus).
     */
    public void testEmphasize( String cadena, char ch)
    {
        System.out.println( cadena + " (" + ch + ") => " + emphasize( cadena, ch));
    }
    
}
