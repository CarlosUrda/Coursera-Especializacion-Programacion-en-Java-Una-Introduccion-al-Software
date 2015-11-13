import edu.duke.StorageResource;
import edu.duke.URLResource;

/**
 * 
 * 
 * @author Carlos A. Gómez Urda
 * @version 1.0
 */
public class URLFinder
{
    private static final String[] patronesURL = {"http", ".com", "www", ".es", ".net"};
    
    
    /**
     * Comprobar si existe algún patrón de dirección url en un texto.
     * 
     * @param texto Cadena a comprobar si es una dirección url.
     * @return Indice con la menor posición de un patrón url en el texto.
     */
    private int obtenerIndicePatronURL( String texto)
    {
        int indice = -1;
        
        for (String patron : patronesURL)
        {
            int indiceTmp = texto.indexOf( patron);
            if (indiceTmp != -1 && (indice == -1 || indiceTmp < indice))
                indice = indiceTmp;
        }
        
        return indice;
    }
    
    
    /**
     * Método que extrae una dirección url de un texto.
     * 
     * @param texto Texto a extraer la dirección url que pueda contener
     * @return Dirección url extraída o null.
     */
    public String extraerURL( String texto)
    {
        int indicePatronURL = obtenerIndicePatronURL( texto);
        
        if (indicePatronURL == -1)
            return null;
            
        int indiceInicial = texto.lastIndexOf( "\"", indicePatronURL);
        int indiceFinal = texto.indexOf( "\"", indicePatronURL);

        if (indiceInicial == -1 || indiceFinal == -1)
            return null;

        return texto.substring( indiceInicial+1, indiceFinal).trim();
    }
    
    
    /**
     * Obtener todas las direcciones existentes en una página web.
     * 
     * @param urlFuente Dirección url de la web a obtener los datos.
     * @param youtube Flag para extraer sólo direcciones a youtube o no.
     * @return Todas las direcciones almacenadas en un objeto StorageResource.
     */
    StorageResource obtenerDireccionesURL( String urlFuente, boolean youtube)
    {
        URLResource urlResource = new URLResource( urlFuente);
        StorageResource datos = new StorageResource();
        String url;
        
        for (String palabra : urlResource.words())
        {
            
            if (youtube && palabra.toLowerCase().indexOf( "youtube") == -1)
                continue;
            
            url = extraerURL( palabra);
            if (url != null)
                datos.add( url);
        }
        
        return datos;
    }
    
    
    /**
     * Obtener todas las direcciones de enlaces <a> existentes en una página web.
     * 
     * @param url Dirección url de la web a obtener los datos.
     * @return Todas las direcciones almacenadas en un objeto StorageResource.
     */
    StorageResource obtenerEnlacesURL( String url)
    {
        URLResource urlResource = new URLResource( url);
        String web = urlResource.asString();
        String webMinusculas = web.toLowerCase();
        StorageResource direcciones = new StorageResource();
        int indice = 0;
        
        while (true)
        {
            indice = webMinusculas.indexOf( "href", indice);
            if (indice == -1)
                break;
        
            int indiceInicio = webMinusculas.indexOf( "\"", indice);
            if (indiceInicio == -1)
                break;
            
            int indiceFinal = webMinusculas.indexOf( "\"", indiceInicio+1);
            if (indiceInicio == -1)
                break;
            
            String direccion = web.substring( indiceInicio+1, indiceFinal).trim();
            if (direccion.toLowerCase().contains( "http"))
                direcciones.add( direccion);    

            indice = indiceFinal+1;
        }
         
        return direcciones;
    }

    
    void probar1( boolean youtube)
    {
        StorageResource direccionesURL = obtenerDireccionesURL( "http://www.dukelearntoprogram.com/course2/data/newyorktimes.html", youtube);
        
        for (String direccion : direccionesURL.data())
        {
            System.out.println( direccion);
        }
        
        System.out.println( "\nNúmero de url's: " + direccionesURL.size());
    }


    void probarExamenFinal()
    {
        StorageResource direccionesURL = obtenerEnlacesURL( "http://www.dukelearntoprogram.com/course2/data/newyorktimes.html");
        int numHttps = 0;
        int numCom = 0;
        int numFinCom = 0;
        int numPuntos = 0;
        
        for (String direccion : direccionesURL.data())
        {
            System.out.println( direccion);
            if (direccion.toLowerCase().contains( "https"))
                numHttps++;
            if (direccion.toLowerCase().contains( ".com"))
            {
                numCom++;
                if (direccion.endsWith(".com") || direccion.endsWith(".com/"))
                    numFinCom++;
            }
                
            numPuntos += direccion.length() - direccion.replace( ".", "").length();
        }
        
        System.out.println( "\nNúmero de url's: " + direccionesURL.size());
        System.out.println( "\nNúmero de https: " + numHttps);
        System.out.println( "\nNúmero de .com: " + numCom);
        System.out.println( "\nNúmero de fines en .com: " + numFinCom);
        System.out.println( "\nNúmero de puntos: " + numPuntos);
    }
}
