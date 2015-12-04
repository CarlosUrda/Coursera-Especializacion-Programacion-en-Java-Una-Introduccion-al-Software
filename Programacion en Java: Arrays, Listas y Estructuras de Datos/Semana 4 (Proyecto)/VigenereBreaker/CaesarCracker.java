import edu.duke.*;


/**
 * Rompedor del Cifrado del César.
 * 
 * @author Coursera y Carlos A. Gómez Urda
 * @version 1.1
 */
public class CaesarCracker 
{
    private String alph;
    char mostCommon;


    /**
     * Constructor con letra más común y alfabeto por defecto.
     */
    public CaesarCracker() 
    {
        this( 'e', "abcdefghijklmnopqrstuvwxyz");
    }
    
    
    /**
     * Constructor con alfabeto por defecto.
     * 
     * @param c Letra más común.
     */
    public CaesarCracker( char c) 
    {
        this( c, "abcdefghijklmnopqrstuvwxyz");
    }
    
    
    /**
     * Constructor.
     * 
     * @param c Letra más común.
     * @param alph Alfabeto.
     */
    public CaesarCracker( char c, String alph) 
    {
        mostCommon = Character.toLowerCase( c);
        this.alph = alph.toLowerCase();
    }
    
    
    public int[] countLetters(String message)
    {
        int[] counts = new int[alph.length()];
        for(int k=0; k < message.length(); k++){
            int dex = alph.indexOf(Character.toLowerCase(message.charAt(k)));
            if (dex != -1){
                counts[dex] += 1;
            }
        }
        return counts;
    }
    
    public int maxIndex(int[] vals){
        int maxDex = 0;
        for(int k=0; k < vals.length; k++)
        {
            if (vals[k] > vals[maxDex]){
                maxDex = k;
            }
        }
        return maxDex;
    }

    /**
     * Obtener la clave usada para encriptar un mensaje mediante el cifrado del César.
     * 
     * @param encrypted Mensaje encriptado.
     * @return Clave usada para la encriptación.
     */
    public int getKey(String encrypted)
    {
        int[] freqs = countLetters(encrypted);

        int dkey = maxIndex(freqs) - alph.indexOf( mostCommon);
        if (dkey < 0) 
            dkey += alph.length();

        return dkey;
    }
    
    public String decrypt(String encrypted){
        int key = getKey(encrypted);
        CaesarCipher cc = new CaesarCipher(key);
        return cc.decrypt(encrypted);
        
    }
   
}
