import edu.duke.*;
import java.util.*;


/**
 * Ejercicio 6 de la Semana 2 del Curso 3: 
 * Modificar el programa GladLib para aprovechar el uso de diccionario (HashMap)
 * 
 * @author Carlos A. Gómez Urda 
 * @version 1.0
 */
public class GladLibMap {
    private HashMap<String,ArrayList<String>> myMap;
    private String[] categorias = {"adjective", "noun", "color", "country", "name", "animal", 
                                   "timeframe", "verb", "fruit"};
    
    private ArrayList<String> palabrasUsadas;         // Palabras ya usadas.
    private ArrayList<String> categoriasUsadas;       // Categorías ya usadas.
    
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    

    /**
     * Constructor.
     */
    public GladLibMap()
    {
        this( dataSourceDirectory);
    }
    

    /**
     * Constructor.
     * 
     * @param source Fuente de donde obtener los archivos con la lista de palabras por categorías.
     */
    public GladLibMap(String source)
    {
        myMap = new HashMap<String,ArrayList<String>>();
        myRandom = new Random();
        initializeFromSource(source);
    }
    
    
    /**
     * Inicializar la lista de palabras de cada categoría.
     * 
     * @param source Fuente de donde obtener los archivos con la lista de palabras por categorías.
     */
    private void initializeFromSource(String source) 
    {
        for (String categoria : categorias)
            myMap.put( categoria, readIt(source+"/"+categoria+".txt")); 
    }

    
    /**
     * Obtener una palabra aleatoria de una lista de palabras.
     * 
     * @param source Lista de palabras.
     * @return Palabra aleatoria obtenida.
     */
    private String randomFrom(ArrayList<String> source)
    {
        return source.get( myRandom.nextInt(source.size()));
    }

    
    /**
     * Obtener una palabra aleatoria de una categoría.
     * 
     * @param label Categoría.
     * @return Palabra aleatoria obtenida de la categoría.
     */
    private String getSubstitute(String label) 
    {
        if (label.equals("number")) 
            return ""+myRandom.nextInt(50)+5;

        ArrayList<String> palabras = myMap.get( label);

        if (palabras == null) 
            return "**UNKNOWN**";

        return randomFrom( palabras);
    }
    
    
    private String processWord(String w)
    {
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);

        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String label = w.substring(first+1,last);
        String sub;
            
        if (!categoriasUsadas.contains( label))
            categoriasUsadas.add( label);
        
        do {
            sub = getSubstitute( label);
        } while (palabrasUsadas.contains( sub));
        
        palabrasUsadas.add( sub);
        
        return prefix+sub+suffix;
    }
    
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source)
    {
        String story = "";
        palabrasUsadas = new ArrayList<String>();
        categoriasUsadas = new ArrayList<String>();

        if (source.trim().startsWith("http"))
        {
            URLResource resource = new URLResource(source);
            for(String word : resource.words())
                story = story + processWord(word) + " ";
        }
        else
        {
            FileResource resource = new FileResource(source);
            for(String word : resource.words())
                story = story + processWord(word) + " ";
        }

        return story;
    }
    
    
    private ArrayList<String> readIt(String source)
    {
        ArrayList<String> list = new ArrayList<String>();

        if (source.trim().startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }

        return list;
    }
    
    
    /**
     * Obtener el número total de palabras en todas las listas de la tabla Hash (todas las palabras
     * posibles a ser usadas para reemplazar las etiquetas)
     * 
     * @return Número total de palabras en toda la tabla Hash.
     */
    public int totalWordsInMap()
    {
        int totalPalabras = 0;
        
        for (String etiqueta : myMap.keySet())
            totalPalabras += myMap.get( etiqueta).size();
        
        return totalPalabras;
    }
    
    
    /**
     * Obtener el número total de palabras de algunas de las listas de la tabla Hash (palabras
     * posibles a ser usadas para reemplazar las etiquetas)
     * 
     * @return Número total de palabras de las categorías usadas.
     */
    public int totalWordsConsidered()
    {
        int totalPalabras = 0;
        
        for (String etiqueta : categoriasUsadas)
        {
            ArrayList<String> palabras = myMap.get( etiqueta);
            if (palabras == null) continue;
            
            totalPalabras += palabras.size();
        }

        return totalPalabras;
    }
    
    
    /**
     * Probador de las funciones anteriores.
     */
    public void makeStory(){
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        System.out.println( "\n\nPalabras total cambiadas: " + palabrasUsadas.size());
        System.out.println( "Palabras total disponibles: " + totalWordsInMap());
        System.out.println( "Palabras total disponibles en categorías usadas: " + totalWordsConsidered());
    }
    


}