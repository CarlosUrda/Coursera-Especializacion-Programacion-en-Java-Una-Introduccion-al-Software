import java.util.ArrayList;
import edu.duke.FileResource;


/**
 * Ejercicio 2 de la Semana 2 del Curso Programación en Java: Arrays, Listas, y Estructuras de Datos.
 * 
 * Determinar el personahe que más interviene en una obra de Shakespeare.
 * 
 * @author Carlos A. Gómez Urda
 * @version 1.0
 */
public class CharactersInPlay
{
    private ArrayList<String> personajes;
    private ArrayList<Integer> aparicionesPer;
    
    private String signos = ":,.\"";
    
    
    /**
     * Constructor
     */
    public CharactersInPlay()
    {
        personajes = new ArrayList<String>();
        aparicionesPer = new ArrayList<Integer>();
    }
    
    
    /**
     * Incrementar en 1 el número de veces que aparece un personaje. Si el personaje no
     * había aparecido antes se anota como una aparición.
     * 
     * @param personaje Persona a actualizar su número de aparicionesPer.
     */
    private void update( String personaje)
    {
        int indice = personajes.indexOf( personaje);
        
        if (indice == -1)
        {
            personajes.add( personaje);
            aparicionesPer.add( 1);
        }
        else
        {
            aparicionesPer.set( indice, aparicionesPer.get( indice) + 1);
        }
    }    
    
    
    /**
     * Encontrar todos los personajes en una obra, obteniendo el número de aparicionesPer de cada uno.
     */
    public void findAllCharacters()
    {
        FileResource archivo = new FileResource();
        
        personajes.clear();
        aparicionesPer.clear();

        for (String linea : archivo.lines())
        {
            int indice = linea.indexOf( ".");
            
            if (indice == -1) continue;
            
            String personaje = linea.substring( 0, indice);
            
            if (personaje.isEmpty()) continue;
            
            update( personaje.toLowerCase().trim());
        }             
    }
    
    
    /**
     * Mostrar los personajes encontrados anteriormente en una obra cuyo número de 
     * aparicionesPer está en un rango.
     * 
     * @param num1 Número mínimo de aparicionesPer.
     * @param num2 Número máximo de aparicionesPer.
     */
    public void charactersWithNumParts( int num1, int num2)
    {
        for (int i = 0; i < personajes.size(); ++i)
        {
            int apariciones = aparicionesPer.get( i);
            
            if (apariciones >= num1 && apariciones <= num2)
                System.out.println( apariciones + ": " + personajes.get( i));
        }        
    }
        
    
    /**
     * Probador de las funciones findAllCharacters y charactersWithNumParts.
     */
    public void tester( int num1, int num2)
    {
        findAllCharacters();
        
        for (int i = 0; i < personajes.size(); ++i)
            System.out.println( aparicionesPer.get( i) + ": " + personajes.get( i));
            
        System.out.println( "\nLas apariciones con rango [" + num1 + ":" + num2 +"]");
        charactersWithNumParts( num1, num2);
    }
}
