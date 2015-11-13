import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVParser;
import edu.duke.FileResource;
import edu.duke.DirectoryResource;
import java.io.File;

/**
 * Segunda práctica de la semana 3 del curso «Programación en Java: Solucionando Problemas con el Software»
 * 
 * @author Carlos A. Gómez Urda
 * @version 1.0
 */
public class TiempoCSV
{
    /**
     * Compara la hora más fría hasta ahora con la hora actual.
     * 
     * @param horaMasFria Hora más fría obtenida hasta ahora.
     * @param horaActual Hora actual a comparar su temperatura.
     * @return Registro con la hora más fría.
     */
    private CSVRecord compararHoraMasFria( CSVRecord horaMasFria, CSVRecord horaActual)
    {
        double tempActual = Double.parseDouble( horaActual.get("TemperatureF"));
        if (tempActual == -9999)
            return horaMasFria;
        
        if (horaMasFria == null)
            return horaActual;

        double tempMasFria = Double.parseDouble( horaMasFria.get("TemperatureF"));
        
        return (tempMasFria > tempActual) ? horaActual : horaMasFria;
    }
  
    
    /**
     * Compara la hora menos húmeda hasta ahora con la hora actual.
     * 
     * @param horaHumedadMenor Hora menos húmeda obtenida hasta ahora.
     * @param horaActual Hora actual a comparar su humedad.
     * @return Registro con la hora menos húmeda.
     */
    private CSVRecord compararHumedadMenor( CSVRecord horaHumedadMenor, CSVRecord horaActual)
    {
        if (horaActual.get("Humidity").equalsIgnoreCase( "N/A"))
            return horaHumedadMenor;

        int humedadActual = Integer.parseInt( horaActual.get("Humidity"));
        
        if (horaHumedadMenor == null)
            return horaActual;

        int humedadMenor = Integer.parseInt( horaHumedadMenor.get("Humidity"));
        
        return (humedadMenor > humedadActual) ? horaActual : horaHumedadMenor;
    }
  
    
    /**
     * Obtener el registro con los datos de la hora con la temperatura más fría de una serie 
     * de horas de un archivo csv.
     * 
     * @param horas Registros de horas con las temperaturas.
     * @return Registro de la hora más fría.
     */
    public CSVRecord horaMasFriaEnArchivo( CSVParser horas)
    {
        if (horas == null) return null;

        CSVRecord horaMasFria = null;
        for (CSVRecord horaActual : horas)
        {
           horaMasFria = compararHoraMasFria( horaMasFria, horaActual);
        }
        
        return horaMasFria;
    }
    
    
    /**
     * Obtener la temperatura media de una lista de horas de un archivo csv.
     * 
     * @param horas Registros de horas con las temperaturas.
     * @return Temperatura media.
     */
    public Double temperaturaMediaEnArchivo( CSVParser horas)
    {
        if (horas == null) return null;

        double tempMedia = 0;
        int numHoras = 0;
        for (CSVRecord horaActual : horas)
        {
            tempMedia += Double.parseDouble( horaActual.get("TemperatureF"));
            numHoras++;
        }
        
        return (numHoras != 0) ? tempMedia / numHoras : null;
    }
    
    

    /**
     * Obtener la temperatura media de una lista de horas de un archivo csv cuya humedad es 
     * superior a una determinada.
     * 
     * @param horas Registros de horas con las temperaturas.
     * @param humedad Valor de la humedad umbral.
     * @return Temperatura media.
     */
    public Double temperaturaMediaConHumedadAltaEnArchivo( CSVParser horas, int humedad)
    {
        if (horas == null) return null;

        double tempMedia = 0;
        int numHoras = 0;
        for (CSVRecord horaActual : horas)
        {
            if (Integer.parseInt( horaActual.get( "Humidity")) < humedad)
                continue;
            tempMedia += Double.parseDouble( horaActual.get("TemperatureF"));
            numHoras++;
        }
        
        return (numHoras != 0) ? tempMedia / numHoras : null;        
    }

    
    /**
     * Obtener el registro con los datos de la hora con la humedad más baja de una serie 
     * de horas de un archivo csv.
     * 
     * @param horas Registros de horas con las humedades.
     * @return Registro de la hora con la humedad más baja.
     */
    public CSVRecord humedadMenorEnArchivo( CSVParser horas)
    {
        if (horas == null) return null;

        CSVRecord horaHumedadMenor = null;
        for (CSVRecord horaActual : horas)
        {
           horaHumedadMenor = compararHumedadMenor( horaHumedadMenor, horaActual);
        }
        
        return horaHumedadMenor;
    }
    
    
    /**
     * Obtener el registro con los datos de la hora con la humedad más baja de una serie 
     * de horas de varios archivos csv.
     * 
     * @return Registro de la hora con la humedad más baja.
     */
    public CSVRecord humedadMenorEnArchivos()
    {
        DirectoryResource archivosR = new DirectoryResource();
        CSVRecord horaHumedadMenor = null;
        
        for (File archivo : archivosR.selectedFiles())
        {
            FileResource archivoR = new FileResource( archivo);
            CSVRecord horaActual = humedadMenorEnArchivo( archivoR.getCSVParser());
            horaHumedadMenor = compararHumedadMenor( horaHumedadMenor, horaActual);
        }
        
        return horaHumedadMenor;
    }
    
    
    /**
     * Obtener el nombre del archivo que contiene la temperatura más fría.
     * 
     * @return Nombre del archivo con la temperatura más fría.
     */
    public String archivoConTemperaturaMasFria()
    {
        DirectoryResource archivosR = new DirectoryResource();
        String archivoMasFrio = null;
        CSVRecord horaMasFria = null;
        
        for (File archivo : archivosR.selectedFiles())
        {
            FileResource archivoR = new FileResource( archivo);
            CSVRecord horaActual = horaMasFriaEnArchivo( archivoR.getCSVParser());
            CSVRecord hora = compararHoraMasFria( horaMasFria, horaActual);
            if (hora != horaMasFria)
            {
                horaMasFria = horaActual;
                archivoMasFrio = archivo.getPath();
            }            
        }
        
        return archivoMasFrio;
    }
 
    
    public void probarHoraMasFriaEnArchivo()
    {
        FileResource archivo = new FileResource();
        CSVParser horas = archivo.getCSVParser();
        
        CSVRecord hora = horaMasFriaEnArchivo( horas);
        
        System.out.println( hora.get("DateUTC") + ", " + hora.get("TemperatureF"));
    }
    
    
    public void probarArchivoConTemperaturaMasFria()
    {
        String archivo = archivoConTemperaturaMasFria();
        if (archivo == null)
        {
            System.out.println( "No existe el archivo");
            return;
        }
            
        FileResource archivoR = new FileResource( archivo);
        CSVParser horasArchivo = archivoR.getCSVParser();
        CSVRecord horaMasFria = horaMasFriaEnArchivo( horasArchivo);
        
        // Se imprimen los datos del archivo con el día más frío.
        System.out.println( "El día más frío está en el archivo " + archivo);
        System.out.println( "La temperatura más fría en ese día fue " + horaMasFria.get("TemperatureF"));
        System.out.println( "Todas las temperaturas del día más frío fueron: ");
        horasArchivo = archivoR.getCSVParser();
        for (CSVRecord hora : horasArchivo)
            System.out.println( hora.get("DateUTC") + ": " + hora.get("TemperatureF"));
                
    }


    public void probarHumedadMenorEnArchivo()
    {
        FileResource archivo = new FileResource();
        CSVParser horas = archivo.getCSVParser();
        
        CSVRecord horaHumedadMenor = humedadMenorEnArchivo( horas);
        
        System.out.println( "La humedad más baja fue " + horaHumedadMenor.get("Humidity") + " at " + horaHumedadMenor.get("DateUTC"));
    }
    
    
    public void probarHumedadMenorEnArchivos()
    {
        CSVRecord horaHumedadMenor = humedadMenorEnArchivos();
        
        System.out.println( "La humedad más baja fue " + horaHumedadMenor.get("Humidity") + " at " + horaHumedadMenor.get("DateUTC"));
    }
    

    public void probarTemperaturaMediaEnArchivo()
    {
        FileResource archivoR = new FileResource();
        CSVParser horas = archivoR.getCSVParser();
        
        Double tempMedia = temperaturaMediaEnArchivo( horas);
              
        if (tempMedia == null)
            System.out.println( "No hay temperatura media");
        else
            System.out.println( "La temperatura media en el archivo es de " + tempMedia);
    }

    
    public void probarTemperaturaMediaConHumedadAltaEnArchivo( int humedad)
    {
        FileResource archivoR = new FileResource();
        CSVParser horas = archivoR.getCSVParser();
        
        Double tempMedia = temperaturaMediaConHumedadAltaEnArchivo( horas, humedad);
                
        if (tempMedia == null)
            System.out.println( "No hay temperatura media con humedad " + humedad);
        else
            System.out.println( "La temperatura media con humedad " + humedad + " en el archivo es de " + tempMedia);
    }
}
