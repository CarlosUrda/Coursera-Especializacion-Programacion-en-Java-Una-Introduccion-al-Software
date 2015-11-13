import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVParser;
import edu.duke.FileResource;

/**
 * Primera práctica de la semana 3 del curso «Programación en Java: Solucionando Problemas con el Software»
 * 
 * @author Carlos A. Gómez Urda
 * @version 1.0
 */
public class PaisesCSV
{
    private String msgNoEncontrado = "NO ENCONTRADO";
    /**
     * Obtener la información de un país concreto.
     * 
     * @param csvPaises Lista de paises en formato csv.
     * @param paisBuscar Cadena con el nombre del país a buscar.
     * @return Cadena con la información del país si se encuentra, o "NOT FOUND" en caso contrario.
     */
    public String infoPais( CSVParser csvPaises, String paisBuscar)
    {
        if (csvPaises == null || paisBuscar == null || paisBuscar.isEmpty())
            return msgNoEncontrado;
            
        for (CSVRecord csvPais : csvPaises)
        {
            String paisActual = csvPais.get("Country");
            if (paisActual.equalsIgnoreCase( paisBuscar))
                return paisActual + ": " + csvPais.get("Exports") + ": " + 
                       csvPais.get("Value (dollars)");
        }
        
        return msgNoEncontrado;
    }
    
    
    /**
     * Mostrar los países que exportan dos productos.
     * 
     * @param csvPaises Lista de paises en formato csv.
     * @param producto1 Primer producto a comprobar si es exportado.
     * @param producto2 Segundo producto a comprobar si es exportado.
     */
    public void listaExportadoresDosProductos( CSVParser csvPaises, String producto1, String producto2)
    {       
        if (csvPaises == null || producto1 == null || producto1.isEmpty() || 
            producto2 == null || producto2.isEmpty())
        {
            System.out.println( msgNoEncontrado);
            return;
        }
        
        String salida = "";
        
        for (CSVRecord csvPais : csvPaises)
        {
            String exportaciones = csvPais.get("Exports");
            if (exportaciones.contains( producto1) && exportaciones.contains( producto2))
                salida += csvPais.get("Country") + System.getProperty("line.separator");
        }
        
        System.out.println( salida.isEmpty() ? msgNoEncontrado : salida);
    }
    
    
    /**
     * Obtener el número de países exportadores de un producto.
     * 
     * @param csvPaises Lista de países en formato csv.
     * @param producto Producto a comprobar cuántos países lo exportan.
     * @return Número de países exportadores.
     */
    public int numeroExportadores( CSVParser csvPaises, String producto)
    {
        if (csvPaises == null || producto == null || producto.isEmpty())
            return -1;
            
        int numPaises = 0; 
        
        for (CSVRecord csvPais : csvPaises)
        {
            if (csvPais.get("Exports").contains( producto))
                numPaises++;
        }
        
        return numPaises;
    }
    
    /**
     * Mostrar por pantalla los países (y sus valores) cuyos valores en exportaciones tiene
     * más dígitos que un valor concreto.
     * 
     * @param csvPaises Lista de países en formato csv.
     * @param valor Mínimo valor en dólares a comparar su longitud.
     */
    public void grandesExportadores( CSVParser csvPaises, String valorComp)
    {
        if (csvPaises == null || valorComp == null || valorComp.isEmpty())
            System.out.println( msgNoEncontrado);
            
        int lonValorComp = valorComp.length();
        
        for (CSVRecord csvPais : csvPaises)
        {
            String valorActual = csvPais.get("Value (dollars)");
            if (valorActual.length() > lonValorComp)
                 System.out.println( csvPais.get("Country") + ": " + valorActual);
        }                 
    }
    
    
    /**
     * Probar las funciones de la práctica.
     * 
     */
    public void probar( String pais, String producto1, String producto2, String valor)
    {
        FileResource archivo = new FileResource();

        CSVParser csv = archivo.getCSVParser();        
        System.out.println( infoPais( csv, pais));

        csv = archivo.getCSVParser();        
        listaExportadoresDosProductos( csv, producto1, producto2);
        
        csv = archivo.getCSVParser();        
        System.out.println( numeroExportadores( csv, producto1));

        csv = archivo.getCSVParser();        
        grandesExportadores( csv, valor);
    }
}
