
/**
 * Analizador de archivos logs de un servidor web
 * 
 * @author Carlos A. Gómez Urda
 * @version 1.0
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     
     /**
      * Constructor
      */
     public LogAnalyzer() 
     {
         records = new ArrayList<LogEntry>();
     }
     
     
     /**
      * Leer todas las entradas de un archivo log y guardarlas internamente.
      * 
      * @param filename Archivo de log.
      */
     public void readFile(String filename) 
     {
         FileResource frArchivo = new FileResource( filename);

         for (String linea : frArchivo.lines())
             records.add( WebLogParser.parseEntry( linea));         
     }
     
     /**
      * Imprimir todas las entradas de log registradas en este objeto.
      */
     public void printAll() 
     {
         for (LogEntry le : records)
             System.out.println(le);
     }
     
     
}
