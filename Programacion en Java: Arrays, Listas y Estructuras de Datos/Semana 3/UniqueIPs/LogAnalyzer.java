
/**
 * Analizador de archivos logs de un servidor web (UniqueIPs)
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
      * Contar el número de IP's distintas que hay registradas como entradas del log.
      * 
      * @return Número de IP's distintas.
      */
     public int countUniqueIPs()
     {
         ArrayList<String> distintasIPs = new ArrayList<String>();
         
         for (LogEntry entrada : records)
         {
             String ip = entrada.getIpAddress();
             if (distintasIPs.contains( ip)) continue;
             
             distintasIPs.add( ip);                         
         }
         
         return distintasIPs.size();
     }
     
     
     /**
      * Obtener una lista de IP's sin repetir que aparezcan en el registro de entradas del log
      * y cuya fecha de acceso sea una determinada.
      * 
      * @param someday Fecha en formato "Mmm DD" (Dec 05; Apr 19; Jun 22; ..)
      */
     public ArrayList<String> uniqueIPVisitsOnDay( String someday)
     {
         ArrayList<String> distintasIPs = new ArrayList<String>();
         
         for (LogEntry entrada : records)
         {
             String ip = entrada.getIpAddress();
             Date dFecha = entrada.getAccessTime();             
             String sFecha = dFecha.toString().substring( 3, 10).trim();
             
             if (!someday.equals( sFecha)) continue;
             
             if (distintasIPs.contains( ip)) continue;
             
             distintasIPs.add( ip);                         
         }
         
         return distintasIPs;
     }
     
     
     /**
      * Obtener el número de IPs distintas que tienen un código de estado dentro de un rango
      * [low,high] incluidos los extremos.
      * 
      * @param low Valor inferior del rango de códigos de estado.
      * @param high Valor superior del rango de códigos de estado.
      * @return Número de IP's distintas con código de estado en el rango.
      */
     public int countUniqueIPsInRange( int low, int high)
     {
         ArrayList<String> distintasIPs = new ArrayList<String>();
         
         for (LogEntry entrada : records)
         {
             String ip = entrada.getIpAddress();
             int estado = entrada.getStatusCode();
             
             if (estado < low || estado > high) continue;
             
             if (distintasIPs.contains( ip)) continue;
             
             distintasIPs.add( ip);                         
         }
         
         return distintasIPs.size();
     }
     
     
     /**
      * Imprimir todas las entradas cuyo número de estado sea mayor que uno determinado.
      * 
      * @param num Número de código de estado límite inferior.
      */
     public void printAllHigherThanNum( int num)
     {
         for (LogEntry entrada: records)
         {
             if (entrada.getStatusCode() <= num) continue;
             
             System.out.println( entrada);
         }
            
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
