
/**
 * Ejercicio 1 de la Semana 1 del curso «Programacion en Java: Arrays, Listas y Estructuras de Datos»
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
    
    
    public void testIsVowel( char ch)
    {
        System.out.println( "Carácter " + ch + ": " + (isVowel( ch) ? "Verdad" : "Falso"));
    }
}
