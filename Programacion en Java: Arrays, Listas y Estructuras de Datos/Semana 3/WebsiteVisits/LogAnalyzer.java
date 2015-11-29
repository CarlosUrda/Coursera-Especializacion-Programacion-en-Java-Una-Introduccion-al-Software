
/**
 * Analizador de archivos logs de un servidor web (WebsiteVisits)
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
      * Obtener el día que ha recibido más accesos.
      * 
      * @param ipsPorDia Tabla Map con las IPs que han accedieron por día de acceso. Las IPs pueden
      * estar repetidas.
      * @return Día con el mayor número de accesos. Null si no hay ningún día.
      */
     public static String dayWithMostIPVisits( HashMap<String, ArrayList<String>> ipsPorDia)
     {
         String diaMaxAccesos = null;
         int maxAccesos = 0;
         
         for (String dia : ipsPorDia.keySet())
         {
             ArrayList<String> ips = ipsPorDia.get( dia);
             int accesos = ips.size();

             if (accesos <= maxAccesos) continue;

             maxAccesos = accesos;
             diaMaxAccesos = dia;
         }
         
         return diaMaxAccesos;
     }


     
     /**
      * Obtener la IP que ha realizado mayor número de accesos en un día determinado.
      * 
      * @param ipsPorDia Tabla Map con las IPs que han accedieron por día de acceso. Las IPs pueden
      * estar repetidas.
      * @param Día a obtener sus IPs con mayor número de accesos.
      * @return Lista de IPs con el mayor número de accesos en ese día.
      */
     public static ArrayList<String> iPsWithMostVisitsOnDay( HashMap<String,ArrayList<String>> ipsPorDia, String dia)
     {
         ArrayList<String> ips = ipsPorDia.get( dia);
         HashMap<String,Integer> accesosPorIP = new HashMap<String,Integer>();
         
         for (String ip : ips)
         {
             Integer accesos = accesosPorIP.get( ip);            
             accesosPorIP.put( ip, (accesos == null) ? 1 : accesos+1);
         }

         return iPsMostVisits( accesosPorIP);
     }

     
     /**
      * Obtener una tabla Map donde se tenga para cada fecha la lista de IP's en el archivo log que 
      * realizaron un acceso en esa fecha. Las IP's pueden aparecer repetidas.
      * 
      * @return Tabla Hash con la lista de IP's por cada fecha.
      */
     public HashMap<String,ArrayList<String>> iPsForDays()
     {
         HashMap<String,ArrayList<String>> ipsPorFecha = new HashMap<String,ArrayList<String>>();
         
         for (LogEntry entrada : records)
         {
             String ip = entrada.getIpAddress();
             Date dFecha = entrada.getAccessTime();             
             String sFecha = dFecha.toString().substring( 3, 10).trim();

             ArrayList<String> ips = ipsPorFecha.get( sFecha);
             
             if (ips == null)
             {
                 ips = new ArrayList<String>();
             }
             //else if (ips.contains( ip)) continue;    // El ejercicio no pide que no se repitan.

             ips.add( ip);
             ipsPorFecha.put( sFecha, ips);                
         }
         
         return ipsPorFecha;         
     }

     
     /**
      * Obtener el máximo número de accesos de entre todos los números de accesos de todas las IP's
      * en un archivo log.
      * 
      * @param accesosPorIP Tabla Map con el número de accesos realizados por cada IP.
      * @return Número máximo de accesos.
      */
     public static int mostNumberVisitsByIP( HashMap<String,Integer> accesosPorIP)
     {
         int maxAccesos = 0;
         
         for (String ip : accesosPorIP.keySet())
         {
             int accesos = accesosPorIP.get( ip);

             if (accesos > maxAccesos)
                maxAccesos = accesos;
         }
         
         return maxAccesos;
     }
     

     /**
      * Obtener las IP's cuyo número de accesos es el máximo de entre todos los números de accesos de 
      * todas las IP's en un archivo log.
      * 
      * @param accesosPorIP Tabla Map con el número de accesos realizados por cada IP.
      * @return Lista de IP's con número de accesos máximo.
      */
     public static ArrayList<String> iPsMostVisits( HashMap<String,Integer> accesosPorIP)
     {         
         int maxAccesos = mostNumberVisitsByIP( accesosPorIP);
         ArrayList<String> maxIPs = new ArrayList<String>();
         
         for (String ip : accesosPorIP.keySet())
         {
             int accesos = accesosPorIP.get( ip);

             if (accesos == maxAccesos) 
                maxIPs.add( ip);
         }
         
         return maxIPs;
     }
   
          
     /**
      * Calcular el número de accesos realizados por cada IP que aparecen en un archivo log.
      * 
      * @return Tabla Hash con el número de accesos por cada IP.
      */
     public HashMap<String,Integer> countVisitsPerIP()
     {
         HashMap<String,Integer> accesosPorIP = new HashMap<String,Integer>();
         
         for (LogEntry entrada : records)
         {
             String ip = entrada.getIpAddress();
             Integer accesos = accesosPorIP.get( ip);
             
             accesosPorIP.put( ip, (accesos == null) ? 1 : accesos+1);
         }
         
         return accesosPorIP;         
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
