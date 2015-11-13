import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVParser;
import edu.duke.FileResource;
import edu.duke.DirectoryResource;
import edu.duke.StorageResource;
import java.util.ArrayList;
import java.util.Comparator;
import java.io.File;

/**
 * Mini proyecto de la semana 4 del curso «Programación en Java: Solucionando Problemas con el Software»
 * 
 * @author Carlos A. Gómez Urda
 * @version 1.2.3
 */

public class NacimientosCSV
{
    private final String rutaAnno = "/windows/D/Coursera/Java/NacimientosCSV/us_babynames_by_year/";
    
    
    /**
     * Comparador que se usará para ordenar la clasificación de nombres por número de nacimientos
     */
    private class NacimientosComparator implements Comparator<NacimientosNombre>
    {
        @Override
        public int compare( NacimientosNombre nacNombre1, NacimientosNombre nacNombre2)
        {
            return nacNombre2.getNacimientos() - nacNombre1.getNacimientos();
        }
    }
    
    
    /**
     * Estructura de cada elemento que se usará como entrada del ArrayList que contiene la lista de 
     * todos los nacimientos ordenados por número de nacimientos. Este objeto contiene el número de
     * nacimientos en un determinado nombre y género.
     */
    private class NacimientosNombre
    {
        private String nombre;
        private String genero;      // Género de este nombre
        private int nacimientos;    // Número de nacimientos de este nombre
        
        
        /**
         * Constructor.
         * 
         * @param nombre Nombre a asignar.
         * @param genero Género ("F" o "M") a asignar a este nombre.
         * @param nacimientos Número de nacimientos a asignar a este nombre.
         */
        public NacimientosNombre( String nombre, String genero, int nacimientos)
        {
            this.nombre = new String( nombre);
            this.genero = new String( genero);
            this.nacimientos = nacimientos;
        }
        
        
        /**
         * Constructor.
         * 
         * @param nombre Nombre a asignar.
         */
        public NacimientosNombre( String nombre, String genero)
        {
            this( nombre, genero, -1);
        }
        
       
        /**
         * Sobreescritura del método equals. Realiza las siguientes comparaciones dependiendo del tipo de
         * objeto pasado:
         * - Si es NacimientosNombre y tiene nombre, se comparan los nombres.
         * - Si es NacimientosNombre y tiene nombre y género, se comparan ambos.
         * - Si es NacimientosNombre y no tiene nombre ni género, se comprueba si los nacimientos de este 
         *   objeto son mayores a los nacimientos del objeto pasado.
         * 
         * @param o Objeto con el cual realizar la comparación.
         */
        @Override
        public boolean equals(Object o) 
        {            
            if (!(o instanceof NacimientosNombre))
                return this == o;
            
            NacimientosNombre nacNombre = (NacimientosNombre) o;

            if (this.nombre == null)
                return this.nombre == nacNombre.getNombre();
                
            if (this.genero == null)
                return this.nombre.equalsIgnoreCase( nacNombre.getNombre());
                
             return this.nombre.equalsIgnoreCase( nacNombre.getNombre()) &&
                    this.genero.equalsIgnoreCase( nacNombre.getGenero());            
                   
        }
        
        
        /**
         * Obtener el nombre de los nacimientos
         * 
         * @return Nombre del número de nacimientos
         */
        public String getNombre()
        {
            if (this.nombre == null)
                return this.nombre;
            
            return new String( this.nombre);
        }

        /**
         * Obtener el género del nombre de los nacimientos
         * 
         * @return Género del nombre del número de nacimientos
         */
        public String getGenero()
        {
            if (this.genero == null)
                return this.genero;
            
            return new String( this.genero);
        }

        /**
         * Obtener el número de nacimientos
         * 
         * @return Número de nacimientos
         */
        public int getNacimientos()
        {
            return this.nacimientos;
        }
        
        /**
         * Cambia el nombre de los nacimientos
         * 
         * @param nombre Nombre a asignar.
         */
        public void setNombre( String nombre)
        {
            this.nombre = new String( nombre);
        }

        /**
         * Cambia el género del nombre de los nacimientos
         * 
         * @param genero Género ("F" o "M") a asignar a este nombre.
         */
        public void setGenero( String genero)
        {
            this.genero = new String( genero);
        }
        
        /**
         * Cambia el número de nacimientos
         * 
         * @param nacimientos Número de nacimientos a asignar a este nombre.
         */
        public void setNacimientos( int nacimientos)
        {
            this.nacimientos = nacimientos;
        }
        
        /**
         * Añadir nacimientos al número de nacimientos. Si los nacimientos son negativos, se resta.
         * 
         * @param nacimientos Número de nacimientos a incrementar. 
         * @return Número de nacimientos resultantes después de la modificación.
         */
        public int incNacimientos( int nacimientos)
        {
            return this.nacimientos += nacimientos;
        }
    }
        
    
    
    /**
     * Mostrar el número de nacimientos totales, total de niños, niñas y número distinto de nombres 
     * de niños y niñas (totalBirths)
     * 
     * @param csvArchivo Objeto CSVParser con todos los nacimientos a comprobar
     */
    private void nacimientosTotales( CSVParser csvArchivo)
    {
        StorageResource nombres, nombresChicos, nombresChicas;
        int totalChicos = 0, totalChicas = 0;
        
        nombres = new StorageResource();            // Todos los nombres existentes.
        nombresChicos = new StorageResource();      // Todos los nombres existentes de chicos.
        nombresChicas = new StorageResource();      // Todos los nombres existentes de chicos.
        
        for (CSVRecord registro : csvArchivo)
        {
            String genero = registro.get( 1);
            String nombre = registro.get( 0);
            
            if (!genero.equalsIgnoreCase( "F") && !genero.equalsIgnoreCase( "M"))  continue;
            
            if (!nombres.contains( nombre)) nombres.add( nombre);

            if (genero.equalsIgnoreCase( "F"))
            {
                totalChicas += Integer.parseInt( registro.get( 2));
                if (!nombresChicas.contains( nombre)) nombresChicas.add( nombre);
            }
            else
            {
                totalChicos += Integer.parseInt( registro.get( 2));
                if (!nombresChicos.contains( nombre)) nombresChicos.add( nombre);
            }
        }
        
        System.out.println( "Total de nacimientos: " + (totalChicos + totalChicas));
        System.out.println( "Total de chicos: " + totalChicos);
        System.out.println( "Total de chicas: " + totalChicas);       
        System.out.println( "Total de nombres: " + nombres.size());
        System.out.println( "Total de nombres chicos: " + nombresChicos.size());
        System.out.println( "Total de nombres chicas: " + nombresChicas.size());
    }
    

    /**
     * Obtener la clasificación de nombres según el número de nacimientos y en un determinado género.
     * Si un nombre aparece en varias entradas distintas dentro del mismo género, el número de 
     * nacimientos a tener en cuenta de ese nombre para la clasificación es la suma de los números de
     * nacimientos aparecidos para ese nombre.
     * 
     * @param csvNacimientos Conjunto de nombres con sus nacimientos y género.
     * @param genero Género a filtrar del conjunto de nombres.
     */
    private ArrayList<NacimientosNombre> getClasificacion( CSVParser csvNacimientos, String genero)
    {
        ArrayList<NacimientosNombre> listaClasificacion = new ArrayList<NacimientosNombre>();  
        
        for (CSVRecord nacimiento : csvNacimientos)
        {
            String elemGenero = nacimiento.get( 1);
            
            if (!elemGenero.equalsIgnoreCase( genero)) continue;

            int elemNacimientos = Integer.parseInt( nacimiento.get( 2));
                       
            NacimientosNombre nacNombre = new NacimientosNombre( nacimiento.get( 0), elemGenero, elemNacimientos);
            int indice = listaClasificacion.indexOf( nacNombre);
            if (indice != -1)
            {
                nacNombre.incNacimientos( listaClasificacion.get( indice).getNacimientos());
                listaClasificacion.set( indice, nacNombre);
            }
            else
                listaClasificacion.add( nacNombre);
        }
        
        listaClasificacion.sort( new NacimientosComparator());
        
        return listaClasificacion;
    }
    
    
    /**
     * Obtener la posición en la clasificación por número de nacimientos de un nombre con un determinado
     * género y en un año en concreto.
     * 
     * @param anno Año en el cual realizar la comprobación.
     * @param nombre Nombre del nacido a obtener su clasificación.
     * @param genero Género del nacido ("F" o "M"). 
     * @return Posición de la clasificación del nombre con ese género. 0 si no lo encuentra.
     */
    private int getPosicion( int anno, String nombre, String genero)
    {
        FileResource frArchivo = new FileResource( rutaAnno+"yob"+anno+".csv");
        CSVParser csvNacimientos = frArchivo.getCSVParser( false);
        ArrayList<NacimientosNombre> listaClasificacion;  
        
        listaClasificacion = getClasificacion( csvNacimientos, genero);
        
        return listaClasificacion.indexOf( new NacimientosNombre( nombre, genero)) + 1;
    }
    
    
    /**
     * Obtener el nombre de la persona con una determinada posición en la clasificación de nombres 
     * por número de nacimientos en un año concreto y con un género
     * 
     * @param anno Año de la clasificación.
     * @param posicion Posición del nombre en la clasificación.
     * @param genero Género del nombre a obtener ("F" o "M").
     * @return Nombre en la posición de la clasificación. Si no existe ningún nombre en esa posición
     * devuelve null.
     */
    private String getNombre( int anno, int posicion, String genero)
    {
        FileResource frArchivo = new FileResource( rutaAnno+"yob"+anno+".csv");
        CSVParser csvNacimientos = frArchivo.getCSVParser( false);
        
        ArrayList<NacimientosNombre> listaClasificacion = getClasificacion( csvNacimientos, genero);
        
        try 
        {
            return listaClasificacion.get( posicion-1).getNombre();
        }
        catch (IndexOutOfBoundsException e)
        {
            return null;
        }
    }
    
    
    /**
     * Obtener el nombre que se hubiese escogido para una persona en un nuevo año, si el nombre escogido
     * en este nuevo año hubiese sido aquel con la misma popularidad (posición en la clasificación) que
     * la del nombre elegido en el año original.
     * 
     * @param nombre Nombre original.
     * @param anno Año original en el cual se eligió el nombre original.
     * @param annoNuevo Nuevo año del que obtener el nuevo nombre que se elegiría con la misma 
     * popularidad que la del nombre en el año original.
     * @param genero Genero a filtrar los nombres.
     * @return Nuevo nombre escogido en el nuevo año. Null si no existe el nombre en el año original o
     * no hay ningún nombre en el nuevo año con la misma popularidad que el nombre antiguo en el año
     * original.
     */
    private String queNombreSeriaEnAnno( String nombre, int anno, int annoNuevo, String genero)
    {
        FileResource frArchivo = new FileResource( rutaAnno+"yob"+anno+".csv");
        CSVParser csvNacimientos = frArchivo.getCSVParser( false);

        ArrayList<NacimientosNombre> listaClasificacionOrigen = getClasificacion( csvNacimientos, genero);
        
        int posicion = listaClasificacionOrigen.indexOf( new NacimientosNombre( nombre, genero));
        if (posicion == -1)
            return null;
            
        frArchivo = new FileResource( rutaAnno+"yob"+annoNuevo+".csv");
        csvNacimientos = frArchivo.getCSVParser( false);

        ArrayList<NacimientosNombre> listaClasificacionNuevo = getClasificacion( csvNacimientos, genero);  
       
        try 
        {
            return listaClasificacionNuevo.get( posicion).getNombre();
        }
        catch (IndexOutOfBoundsException e)
        {
            return null;
        }
    }
    
    
    /**
     * Obtener el año de mejor posición en la clasificación de un nombre por el número de nacimientos.
     * 
     * @param nombre Nombre a obtener el año de su mejor clasificación.
     * @param genero Genero a filtrar los años.
     * @return Año de la mejor clasificación del nombre. Si el nombre no se encuentra en ninguno de 
     * los años seleccionados, devuelve -1.
     */
    private int getAnnoMayorPosicion( String nombre, String genero)
    {
        DirectoryResource drArchivos = new DirectoryResource();
        int mejorPosicion = -1;
        int mejorAnno = -1;
        
        for (File fArchivo : drArchivos.selectedFiles())
        {
            FileResource frArchivo = new FileResource( fArchivo);
            CSVParser csvNacimientos = frArchivo.getCSVParser( false);
            
            ArrayList<NacimientosNombre> listaClasificacion = getClasificacion( csvNacimientos, genero);
            int posicion = listaClasificacion.indexOf( new NacimientosNombre( nombre, genero));

            if (posicion == -1 || (mejorPosicion != -1 && posicion >= mejorPosicion)) continue;
            
            mejorPosicion = posicion;
            String anno = fArchivo.getName().replace( "yob", "").replace( "short", "").replace( ".csv", "");
            mejorAnno = Integer.parseInt( anno);
        }
        
        return mejorAnno;
    }
    
    
    /**
     * Obtener la posición media de clasificación (según el número de nacimientos) de un nombre en varios 
     * archivos.
     * 
     * @param nombre Nombre a obtener su clasificación media.
     * @param genero Genero a filtrar en los archivos años.
     * @param sinClasificar Estado para saber si se tienen en cuenta los años donde el nombre no ha obtenido
     * ninguna clasificación. Si no se tienen en cuenta esos años se ignoran, pero si se tienen en cuenta
     * se toma como posición en dichos años la máxima posición posible + 1.
     * @return Clasificación (por número de nacimientos) media del nombre. -1 si el nombre no tiene
     * ningun clasificación en ningún archivo seleccionado.
     */
    private double getPosicionMedia( String nombre, String genero, boolean sinClasificar)
    {
        DirectoryResource drArchivos = new DirectoryResource();
        int totalPosiciones = 0;    // Suma de todas las posiciones obtenidas
        int numAnnos = 0;           // Número de años calculados para realizar la media

        NacimientosNombre nacNombre = new NacimientosNombre( nombre, genero);
        
        for (File fArchivo : drArchivos.selectedFiles())
        {
            FileResource frArchivo = new FileResource( fArchivo);
            CSVParser csvNacimientos = frArchivo.getCSVParser( false);
            
            ArrayList<NacimientosNombre> listaClasificacion = getClasificacion( csvNacimientos, genero);
            int posicion = listaClasificacion.indexOf( nacNombre) + 1;
            
            if (posicion == 0 && !sinClasificar) continue;

            numAnnos++;         
            totalPosiciones += (posicion != 0) ? posicion : (listaClasificacion.size() + 1);
        }
        
        return (totalPosiciones == 0) ? -1 : (totalPosiciones * 1.0 / numAnnos);
    }

    
    /**
     * Obtener el número de nacimientos total de todos los nombres con posición más alta en la clasificación
     * de un año concreto y para un género determinado.
     * 
     * @param nombre Nombre a partir del cual obtener aquellos con posiciones mayores.
     * @param genero Genero por el cual filtrar los años.
     * @param sinClasificar Estado para saber si en el caso de no aparación del nombre en la clasificación,
     * tener en cuenta el total de nacimientos de todos los nombres o simplemente devolver -1.
     * @return Total de nacimientos de los nombres con clasificación más alta o -1 si el nombre no se encuentra
     * y no se ha activado el estado sinClasificar. Si está activado el estado sinClasificar y el nombre no 
     * se encuentra, se devuelve la suma total de nacimientos de toda la clasificación en ese año para ese
     * género en concreto.
     */
    private int getTotalNacimientosPosicionMayor( int anno, String nombre, String genero, boolean sinClasificar)
    {        
        FileResource frArchivo = new FileResource( rutaAnno+"yob"+anno+".csv");
        CSVParser csvNacimientos = frArchivo.getCSVParser( false);
        int totalNacimientos = 0;

        ArrayList<NacimientosNombre> listaClasificacion = getClasificacion( csvNacimientos, genero);
        
        int posicion = listaClasificacion.indexOf( new NacimientosNombre( nombre, genero));

        if (posicion == -1 && !sinClasificar) return -1;       
        
        if (posicion == -1) posicion = listaClasificacion.size();
            
        for (int i = posicion-1; i >= 0; i--)
            totalNacimientos += listaClasificacion.get( i).getNacimientos();
        
        return totalNacimientos;
    }
    
    
    /**
     * Probar la función nacimientosTotales (Parte 1 del proyecto)
     */
    public void probarNacimientosTotales()
    {
        FileResource frArchivo = new FileResource();
        
        nacimientosTotales( frArchivo.getCSVParser( false));
        
    }
    
    
    /**
     * Probar la función getPosicion (Parte 2 del proyecto).
     * 
     * @param anno Año en el cual realizar la comprobación.
     * @param nombre Nombre del nacido a obtener su clasificación.
     * @param genero Género del nacido ("F" o "M"). 
     */
    public void probarGetPosicion( int anno, String nombre, String genero)
    {
        int posicion = getPosicion( anno, nombre, genero);
        
        System.out.print( "Año: " + anno + "; Nombre: " + nombre + "; Género: " + genero + "; Pos: ");
        System.out.println( (posicion == 0) ? "No encontrado" : posicion);
    }
    

    /**
     * Probar la función getNombre (Parte 3 del proyecto).
     * 
     * @param anno Año de la clasificación.
     * @param posicion Posición del nombre en la clasificación.
     * @param genero Género del nombre a obtener ("F" o "M").
     */
    public void probarGetNombre( int anno, int posicion, String genero)
    {
        String nombre = getNombre( anno, posicion, genero);
        
        System.out.print( "Año: " + anno + "; Posición: " + posicion + "; Género: " + genero + "; Nombre: ");
        System.out.println( (nombre == null) ? "No encontrado" : nombre);
    }
    
    
    /**
     * Probar la función getNombrequeNombreSeriaEnAnno (Parte 4 del proyecto).
     * 
     * @param nombre Nombre original.
     * @param anno Año original en el cual se eligió el nombre original.
     * @param annoNuevo Nuevo año del que obtener el nuevo nombre que se elegiría con la misma 
     * popularidad que la del nombre en el año original.
     * @param genero Genero a filtrar los nombres.
     */
    public void probarQueNombreSeriaEnAnno( String nombre, int anno, int annoNuevo, String genero)
    {
        String nombreNuevo = queNombreSeriaEnAnno( nombre, anno, annoNuevo, genero);
        
        System.out.print( "Año: " + anno + "; Nombre: " + nombre + "; Género: " + genero + 
                          "; Nuevo año: " + annoNuevo + "; Nuevo nombre: ");
        System.out.println( (nombreNuevo == null) ? "No encontrado" : nombreNuevo);        
    }
    

    /**
     * Probar la función getAnnoMayorPosicion (Parte 5 del proyecto).
     * 
     * @param nombre Nombre a obtener el año de su mejor clasificación.
     * @param genero Genero a filtrar los años.
     */
    public void probarGetAnnoMayorPosicion( String nombre, String genero)
    {
        int annoMejor = getAnnoMayorPosicion( nombre, genero);

        System.out.println( "Nombre: " + nombre + "; Género: " + genero + 
                            "; Mejor año: " + ((annoMejor == -1) ? "Ninguno" : annoMejor));
    }


    /**
     * Probar la función getPosicionMedia (Parte 6 del proyecto).
     * 
     * @param nombre Nombre a obtener la clasificación media en varios años.
     * @param genero Genero a filtrar en las clasificaciones de cada año.
     * @param sinClasificar Estado para saber si se tienen en cuenta la posición en los años donde el nombre 
     * no ha obtenido ninguna clasificación. Si no se tienen en cuenta, la posición en esos años se ignora; 
     * pero si se tienen en cuenta se toma como posición en dichos años la máxima posición posible + 1.
     */
    public void probarGetPosicionMedia( String nombre, String genero, boolean sinClasificar)
    {
        double posicionMedia = getPosicionMedia( nombre, genero, sinClasificar);

        System.out.println( "Nombre: " + nombre + "; Género: " + genero + 
                            "; Posición media: " + ((posicionMedia == -1) ? "Ninguna" : posicionMedia));
    }    


    /**
     * Probar la función getTotalNacimientosPosicionMayor (Parte 7 del proyecto).
     * 
     * @param nombre Nombre a partir del cual obtener aquellos con posiciones mayores.
     * @param genero Genero por el cual filtrar los años.
     * @param sinClasificar Estado para saber si en el caso de no aparación del nombre en la clasificación,
     * tener en cuenta el total de nacimientos de todos los nombres o simplemente devolver -1.
     * @return Total de nacimientos de los nombres con clasificación más alta o -1 si el nombre no se encuentra
     * y no se ha activado el estado sinClasificar. Si está activado el estado sinClasificar y el nombre no 
     * se encuentra, se devuelve la suma total de nacimientos de toda la clasificación en ese año para ese
     * género en concreto.
     */
    public void probarGetTotalNacimientosPosicionMayor( int anno, String nombre, String genero, boolean sinClasificar)
    {
        int totalNacimientos = getTotalNacimientosPosicionMayor( anno, nombre, genero, sinClasificar);

        System.out.println( "Anno: " + anno + "; Nombre: " + nombre + "; Género: " + genero + 
                            "; Total nacimientos más alto: " + ((totalNacimientos == -1) ? "Ninguna" : totalNacimientos));
    }
}
