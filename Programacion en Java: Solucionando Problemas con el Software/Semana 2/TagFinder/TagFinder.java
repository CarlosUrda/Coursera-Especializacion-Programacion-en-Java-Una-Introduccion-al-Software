import edu.duke.FileResource;
import edu.duke.StorageResource;

/**
 * 
 * 
 * @author Carlos A. Gómez Urda
 * @version 1.0
 */
public class TagFinder
{
    static final String codonInicial = "ATG";
    static final String[] codonesFinal = {"TAG", "TGA", "TAA"};

    /**
     * Método que busca el final de una proteina a partir del índice inicial de la proteína 
     * dentro de una secuencia de adn.
     * El codón final tiene que cumplir la condición de estar a una distancia múltiplo de 3 
     * del inicio y debe ser el primer codón encontrado, es decir, que si el primer codón final no cumple la 
     * condición pero el segundo repetido sí, no se da por válido. Además, de entre aquellos que
     * cumplan las condiciones, se elige aquel codón final más cercano al codón inicial. 
     * 
     * @param inicio Posición inicial a partir de la cual buscar un codón final.
     * @param adn Secuencia de adn en mayúsculas donde buscar el codón final.
     * @return Indice de la posición siguiente al final de la proteína o -1 si no se encuentra.
     */
    private int indiceFinalProteina( String adn, int inicio)
    {
        int indiceCodon;        // Indice donde se encuentra cada codon final encontrado.
        int indiceFinal = -1;   // Índice final de la proteína.
        adn = adn.toUpperCase();
        
        for (String codonFinal : codonesFinal)
        {
            indiceCodon = adn.indexOf( codonFinal, inicio);
            if (indiceCodon == -1)
                continue;
        
            if ((indiceCodon-inicio) % 3 == 0 && (indiceFinal == -1 || indiceCodon < indiceFinal))
                indiceFinal = indiceCodon;
        }
        
        return indiceFinal == -1 ? indiceFinal : indiceFinal+3;
    }
    
    
    /**
     * Obtener el codón final de una proteína. 
     * 
     * @param proteina Proteina a obtener su codón final.
     * @return Si la secuencia pasada no es una proteína devuelve "", sino devuelve el codón final.
     */
    public String obtenerCodonFinal( String proteina)
    {
        if ((proteina.length() < 6) || (proteina.length() % 3 != 0))
            return "";
            
        if (!proteina.substring( 0, 3).equalsIgnoreCase( codonInicial))
            return "";

        String codonFinal = proteina.substring( proteina.length()-3);
        for (String codon: codonesFinal)
        {
            if (codonFinal.equalsIgnoreCase( codon))
                return codonFinal;
        }
        
        return "";
    }
    
    
    /**
     * Función que busca una proteína dentro de una secuencia de ADN
     * 
     * @param adn Secuencia de adn a buscar la proteína.
     * @return String con la proteína encontrada o "" en caso de no encontrarse.
     */
    public String buscarProteina( String adn)
    {
        int indiceInicial = adn.toUpperCase().indexOf( codonInicial);
        if (indiceInicial == -1)
            return "";

        int indiceFinal = indiceFinalProteina( adn, indiceInicial+3);
        if (indiceFinal == -1)
            return "";
        
        return( adn.substring( indiceInicial, indiceFinal));
    }
    
    
    
    /**
     * Función que busca todas las proteínas dentro de una secuencia de ADN
     * 
     * @param adn Secuencia de adn a buscar las proteínas.
     * @return Almacenamiento con todas las proteínas encontradas
     */
    public StorageResource buscarProteinas( String adn)
    {
        StorageResource proteinas = new StorageResource();       
        int indiceInicial = 0;
        
        while (true)
        {
            indiceInicial = adn.toUpperCase().indexOf( codonInicial, indiceInicial);
            if (indiceInicial == -1)
                break;
        
            int indiceFinal = indiceFinalProteina( adn, indiceInicial+3);
            if (indiceFinal == -1)
                indiceInicial += 3;
            else
            {
                proteinas.add( adn.substring( indiceInicial, indiceFinal));
                indiceInicial = indiceFinal;
            }
        }
        
        return proteinas;
    }
    
    
    /**
     * Obtener la proporcion de C's y G's en una secuencia de ADN.
     * 
     * @param adn Secuencia de adn a realizar la comprobación.
     * @return Fracción de la entera secuencia de ADN que corresponde a la proporción de C's y G's.
     */
    public double cgProporcion( String adn)
    {
        adn = adn.toUpperCase();
        String adnSinCG = adn.replaceAll( "[CG]", "");
        int longADN = adn.length();
        return (longADN - adnSinCG.length()) * 1.0 / longADN;
    }
    
    
    /**
     * Obtener el número de veces que aparece un codón en una secuencia de adn.
     * 
     * @param adn Secuencia de adn a realizar la comprobación.
     * @param codon Codón a buscar para obtener el número de veces que aparece.
     * @return Número de veces que aparece el codón.
     */
    public int numVecesCodon( String adn, String codon)
    {
        adn = adn.toUpperCase();
        codon = codon.toUpperCase();
        
        String adnSinCodon = adn.replaceAll( codon, "");
        int longADN = adn.length();
        return (longADN - adnSinCodon.length()) / 3;
    }

    
    /**
     * Obtener la longitud del mayor gen de entre un conjunto de genes.
     * 
     * @param genes Conjunto de genes a obtener la longitud máxima.
     * @return Longitud máxima de entre el conjunto de genes.
     */
    public int longitudMayorGen( StorageResource genes)
    {
        int mayorLong = 0;
        
        for (String gen : genes.data())
        {
            if (gen.length() > mayorLong)
                mayorLong = gen.length();
        }
        
        return mayorLong;
    }
    
    
    /**
     * Imprimir información de los genes. Aquellos con más de 60 caracteres y cuya proporción CG es
     * mayor que 0.35.
     * 
     * @param genes Genes a partir de los cuales mostrar la información
     */
    public void imprimirGenes( StorageResource genes)
    {
        StorageResource masLargos = new StorageResource();
        StorageResource masProporcion = new StorageResource();
        
        for (String gen : genes.data())
        {
            if (gen.length() > 60)
                masLargos.add( gen);
            
            if (cgProporcion( gen) > 0.35)
                masProporcion.add( gen);
        }
        
        System.out.println( "\nProteínas con más de 60 caracteres (Total: " + masLargos.size() + ")\n");
        for (String gen : masLargos.data())
            System.out.println( "Gen: " + gen);

        System.out.println( "\n\nProteínas con proporción CG mayor de 0.35 (Total: " + masProporcion.size() + ")\n");
        for (String gen : masProporcion.data())
            System.out.println( "Gen: " + gen);
    }
    
    
    /**
     * Función encargada de probar la función buscarProteina.
     */
    public void probarBuscarProteina()
    {
        String proteina, codon;
        proteina = buscarProteina( "AAATGCCCTAACTAGATTGAAACC");
        codon = obtenerCodonFinal( proteina);
        System.out.println( "AATGCTAGTTTAAATCTGA: " + proteina + " - Codón: " + codon);
        proteina = buscarProteina( "ataaactatgttttaaatgt");
        codon = obtenerCodonFinal( proteina);
        System.out.println( "ataaactatgttttaaatgt: " + proteina + " - Codón: " + codon);
        proteina = buscarProteina( "acatgataacctaag");
        codon = obtenerCodonFinal( proteina);
        System.out.println( "acatgataacctaag: " + proteina + " - Codón: " + codon);
    }
    
    
    /**
     * Función encargada de probar la función buscarProteinas.
     */
    public void probarBuscarProteinas()
    {
        FileResource archivo = new FileResource();        
        String adn = archivo.asString();
        StorageResource proteinas = buscarProteinas( adn);
        
        for (String proteina : proteinas.data())
        {
            System.out.println( "Proteína: " + proteina);
            System.out.println( "Codón: " + obtenerCodonFinal( proteina));
            System.out.println( "Longitud: " + proteina.length() + " %(" + proteina.length()%3 + ")\n");
        }
        
        System.out.println( "Proporción CG: " + cgProporcion( adn));
        System.out.println( "Tamaño total: " + proteinas.size());
        
        imprimirGenes( proteinas);
        
        System.out.println( "\nNúmero de CTG's: " + numVecesCodon( adn, "CTG"));
        System.out.println( "Longitud del gen mayor: " + longitudMayorGen( proteinas));
        
    }

}
