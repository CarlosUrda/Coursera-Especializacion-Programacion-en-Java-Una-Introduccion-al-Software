
/**
 * Probador de la clase LogAnalizer
 * 
 * @author Carlos A. Gómez Urda
 * @version 1.0
 */

import java.util.*;

public class Tester
{
    /**
     * Probador de la función iPsWithMostVisitsOnDay de LogAnalyzer.
     * 
     * @param archivo Nombre del archivo de log.
     * @param Día a obtener sus IPs con mayor número de accesos.
     */
    public void testIPsWithMostVisitsOnDay( String archivo, String dia)
    {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        
        logAnalyzer.readFile( archivo);    
        
        HashMap<String,ArrayList<String>> ipsPorDia = logAnalyzer.iPsForDays();
        
        System.out.println( "IP's por día: ");
        System.out.println( ipsPorDia);
        
        System.out.println( "\nEn el día " + dia + " las IPs con mayor número de accesos son:");
        System.out.println( LogAnalyzer.iPsWithMostVisitsOnDay( ipsPorDia, dia));
    }

    
    /**
     * Probador de la función dayWithMostIPVisits de LogAnalyzer.
     * 
     * @param archivo Nombre del archivo de log.
     */
    public void testDayWithMostIPVisits( String archivo)
    {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        
        logAnalyzer.readFile( archivo);    
        
        HashMap<String,ArrayList<String>> ipsPorDia = logAnalyzer.iPsForDays();
        
        System.out.println( "IP's por día: ");
        System.out.println( ipsPorDia);
        
        String dia = LogAnalyzer.dayWithMostIPVisits( ipsPorDia);
        int maxAccesos = ipsPorDia.get( dia).size();
        System.out.println( "Día con mayor número de accesos " + maxAccesos + ": " + dia);
    }

    
    /**
     * Probador de la función iPsForDays de LogAnalyzer.
      * 
      * @param archivo Nombre del archivo de log.
     */
    public void testIPsForDays( String archivo)
    {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        
        logAnalyzer.readFile( archivo);    
        
        HashMap<String,ArrayList<String>> ipsPorFecha = logAnalyzer.iPsForDays();
        
        System.out.println( "IP's por día: ");
        System.out.println( ipsPorFecha);
    }

    
    /**
     * Probador de la función iPsMostVisits de LogAnalyzer.
      * 
      * @param archivo Nombre del archivo de log.
     */
    public void testIPsMostVisits( String archivo)
    {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        
        logAnalyzer.readFile( archivo);    
        
        HashMap<String,Integer> accesosPorIP = logAnalyzer.countVisitsPerIP();
        
        System.out.println( "IP's distintas: " + accesosPorIP.size());
        System.out.println( logAnalyzer.countVisitsPerIP());
        System.out.println( "Número máximo de accesos: " + LogAnalyzer.mostNumberVisitsByIP( accesosPorIP));
        System.out.println( "IP's con máximo número de accesos:");
        for (String ip : LogAnalyzer.iPsMostVisits( accesosPorIP))
            System.out.println( ip);
    }

    
    /**
     * Probador de la función mostNumberVisitsByIP de LogAnalyzer.
      * 
      * @param archivo Nombre del archivo de log.
     */
    public void testMostNumberVisitsByIP( String archivo)
    {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        
        logAnalyzer.readFile( archivo);    
        
        HashMap<String,Integer> accesosPorIP = logAnalyzer.countVisitsPerIP();
        
        System.out.println( "IP's distintas: " + accesosPorIP.size());
        System.out.println( logAnalyzer.countVisitsPerIP());
        System.out.println( "Número máximo de accesos: " + LogAnalyzer.mostNumberVisitsByIP( accesosPorIP));
    }

    
    /**
     * Probador de la función countVisitsPerIP de LogAnalyzer.
      * 
      * @param archivo Nombre del archivo de log.
     */
    public void testCountVisitsPerIP( String archivo)
    {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        
        logAnalyzer.readFile( archivo);    
        
        HashMap<String,Integer> accesosPorIP = logAnalyzer.countVisitsPerIP();
        
        System.out.println( "IP's distintas: " + accesosPorIP.size());
        System.out.println( logAnalyzer.countVisitsPerIP());
    }

    
    /**
     * Probador de la función countUniqueIPsInRange de LogAnalyzer.
      * 
      * @param archivo Nombre del archivo de log.
      * @param low Valor inferior del rango de códigos de estado.
      * @param high Valor superior del rango de códigos de estado.
     */
    public void testCountUniqueIPsInRange( String archivo, int low, int high)
    {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        
        logAnalyzer.readFile( archivo);
        
        System.out.print( "IP's distintas con código de estado entre " + low + " y " + high + ": ");
        System.out.println( logAnalyzer.countUniqueIPsInRange( low, high));
    }

    
    /**
     * Probador de la función uniqueIPVisitsOnDay de LogAnalyzer.
      * 
      * @param archivo Nombre del archivo de log.
      * @param someday Fecha en formato "Mmm DD" (Dec 05; Apr 19; Jun 22; ..)
     */
    public void testUniqueIPVisitsOnDay( String archivo, String someday)
    {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        
        logAnalyzer.readFile( archivo);
        
        ArrayList<String> distintasIPs = logAnalyzer.uniqueIPVisitsOnDay( someday);
        
        System.out.println( "IP's distintas a fecha " + someday + " son " + distintasIPs.size() + ":");
        for (String ip : distintasIPs)
        {
            System.out.println(ip);
        }
    }

    
    /**
     * Probador de la función printAllHigherThanNum de LogAnalyzer.
      * 
      * @param archivo Nombre del archivo de log.
      * @param num Número de código de estado límite inferior.
     */
    public void testPrintAllHigherThanNum( String archivo, int num)
    {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        
        logAnalyzer.readFile( archivo);
        logAnalyzer.printAllHigherThanNum( num);
    }
    
    
    /**
     * Probador de la función countUniqueIPs de LogAnalyzer.
     * @param archivo Nombre del archivo de log.
     */
    public void testCountUniqueIPs( String archivo)
    {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        
        logAnalyzer.readFile( archivo);
        System.out.println( "El número de IP's distintas es: " + logAnalyzer.countUniqueIPs());
    }
    
    
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    

    /**
     * Probador de la función printAll de LogAnalyzer.
     * @param archivo Nombre del archivo de log.
     */
    public void testLogAnalyzer( String archivo) 
    {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        
        logAnalyzer.readFile( archivo);
        logAnalyzer.printAll();
    }
}
