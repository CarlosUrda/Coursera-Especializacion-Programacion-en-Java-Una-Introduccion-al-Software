import edu.duke.*;
import java.util.*;


/**
 * Cifrado de Vigenere.
 * 
 * @author Coursera y Carlos A. Gómez Urda
 * @version 1.1
 */
public class VigenereCipher 
{
    private CaesarCipher[] ciphers;
 
    
    /**
     * Constructor
     * 
     * @param key Clave a usar en el cifrado.
     */
    public VigenereCipher(int[] key) 
    {
        this( key, "ABCDEFGHIJKLMNOPQRSTUVWXYZ");
    }
    

    /**
     * Constructor
     * 
     * @param key Clave a usar en el cifrado.
     * @param alphabet Alfabeto a considerar en el cifrado.
     */
    public VigenereCipher(int[] key, String alphabet) 
    {
        ciphers = new CaesarCipher[key.length];
        alphabet = alphabet.toUpperCase();
        
        for (int i = 0; i < key.length; i++) {
            ciphers[i] = new CaesarCipher(key[i], alphabet);
        }
    }
    
    
    public String encrypt(String input) 
    {
        StringBuilder answer = new StringBuilder();
        int i = 0;
        for (char c : input.toCharArray()) {
            int cipherIndex = i % ciphers.length;
            CaesarCipher thisCipher = ciphers[cipherIndex];
            answer.append(thisCipher.encryptLetter(c));
            i++;
        }
        return answer.toString();
    }
    
    public String decrypt(String input) {
        StringBuilder answer = new StringBuilder();
        int i = 0;
        for (char c : input.toCharArray()) {
            int cipherIndex = i % ciphers.length;
            CaesarCipher thisCipher = ciphers[cipherIndex];
            answer.append(thisCipher.decryptLetter(c));
            i++;
        }
        return answer.toString();
    }
    
    public String toString() {
        return Arrays.toString(ciphers);
    }
    
}
