import edu.duke.*;
import java.util.*;


/**
 * Ejercicio 4 de la Semana 2 del Curso 3.
 * Contar codones en una secuencia de ADN.
 * 
 * @author Carlos A. Gómez Urda
 * @version 1.0
 */
public class CodonCount
{
    private HashMap<String, Integer> frecuenciaCodones;

    /**
     * Constructor 
     */
    public CodonCount()
    {
        frecuenciaCodones = new HashMap<String,Integer>();
    }

    
    /**
     * Número de codones distintos obtenidos en la última lectura realizada de ADN.
     * 
     * @return Número de codones distintos.
     */
    public int getNumberOfUniqueCodons()
    {
        return frecuenciaCodones.size();
    }
    
    
    /**
     * Obtener la frecuencia de un codón en la última lectura realizada de ADN.
     * 
     * @param codon Codón a obtener su frecuencia.
     * @return Número de apariciones del codón en la última lectura.
     */
    public int getCodonFreq( String codon)
    {
        return frecuenciaCodones.getOrDefault( codon, 0);
    }
    
    
    /**
     * Obtener el número de apariciones de codones en una secuencia de ADN en un marco de
     * lectura determinado (marco de lectura es la lectura de la secuencia de codones empezando
     * en el primer (0), segundo (1) o tercer símbolo (2) del ADN)
     * 
     * @param start Posición inicial del marco de lectura de secuencia de codones (0, 1 o 2).
     * @param dna Secuencia de ADN.
     */
    public void buildCodonMap( int start, String dna)
    {
        frecuenciaCodones.clear();
        
        if (start < 0 || start > 2 || dna == null || dna.isEmpty())
            return;
                    
        for (int i = start; (i+3) <= dna.length(); i+=3)
        {
            String codon = dna.substring( i, i+3);
            frecuenciaCodones.put( codon, frecuenciaCodones.getOrDefault( codon, 0) + 1);
        }
    }
    
    
    /**
     * Obtener el primer codon más frecuente en el último marco de lectura analizado.
     * 
     * @return Codón más frecuente obtenido.
     */
    public String getMostCommonCodon()
    {
        int frecuenciaMax = 0;
        String codonMax = null;
        
        for (String codon : frecuenciaCodones.keySet())
        {
            int frecuencia = frecuenciaCodones.get( codon);
            if (frecuencia <= frecuenciaMax) continue;
            
            frecuenciaMax = frecuencia;
            codonMax = codon;            
        }
        
        return codonMax;
    }
    
    
    /**
     * Imprimir todos los codones obtenidos cuyas frecuencias están en el rango [start,end]
     * 
     * @param start Mínima frecuencia para mostrar un codón
     * @param end Máxima frecuencia para mostrar un codón.
     */
    public void printCodonCounts( int start, int end)
    {
        for (String codon : frecuenciaCodones.keySet())
        {
            int frecuencia = frecuenciaCodones.get( codon);
            if (frecuencia < start || frecuencia > end) continue;
            
            System.out.println( frecuencia + ": " + codon);
        }
    }
    
    
    /**
     * Probador de las funciones de esta clase.
     * @param start Mínima frecuencia para mostrar un codón
     * @param end Máxima frecuencia para mostrar un codón.
     */
    public static void tester( int start, int end)
    {
        FileResource archivo = new FileResource();
        
        String adn = archivo.asString().trim().toUpperCase();

        for (int i = 0; i < 3; i++)
        {
            CodonCount codonCount = new CodonCount();       
            codonCount.buildCodonMap( i, adn);
            
            System.out.println( "\nMarco de lectura " + i);
            System.out.println( "Número de codones distintos: " + codonCount.getNumberOfUniqueCodons());
            String codon = codonCount.getMostCommonCodon();
            System.out.println( "Codón más frecuente: " + codon + " => " + codonCount.getCodonFreq( codon));
            codonCount.printCodonCounts( start, end);
        }
    }
}
