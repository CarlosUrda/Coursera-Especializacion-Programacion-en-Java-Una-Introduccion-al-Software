import edu.duke.*;

/**
 * Cifrado del César.
 * 
 * @author Coursera y Carlos A. Gómez Urda
 * @version 1.1
 */
public class CaesarCipher 
{
    private String alphabet;
    private String shiftedAlphabet;
    private int theKey;

    
    /**
     * Constructor
     * 
     * @param key Clave a usar en el cifrado.
     * @param alphabet Alfabeto a usar en el cifrado.
     */
    public CaesarCipher(int key, String alphabet) 
    {
        theKey = key;
        this.alphabet = alphabet.toUpperCase();
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0,key);

        this.alphabet = this.alphabet + this.alphabet.toLowerCase();
        shiftedAlphabet = shiftedAlphabet + shiftedAlphabet.toLowerCase();
    }


    /**
     * Constructor
     * 
     * @param key Clave a usar en el cifrado.
     */
    public CaesarCipher( int key) 
    {
        this( key, "ABCDEFGHIJKLMNOPQRSTUVWXYZ");
    }

    
    /**
     * Obtener el alfabeto.
     * 
     * @return Alfabeto del cifrado.
     */
    public String getAlphabet()
    {
        return alphabet;
    }
    
    
    private char transformLetter(char c, String from, String to) {
        int idx = from.indexOf(c);
        if (idx != -1) {
            return to.charAt(idx);
        }
        return c;
    }
    
    public char encryptLetter(char c) {
        return transformLetter(c, alphabet, shiftedAlphabet);
    }
    
    public char decryptLetter(char c) {
        return transformLetter(c, shiftedAlphabet, alphabet);
    }
    
    private String transform(String input, String from, String to){
        StringBuilder sb = new StringBuilder(input);
        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            c = transformLetter(c, from, to);
            sb.setCharAt(i, c);
        }
        return sb.toString();
    }
    
    public String encrypt(String input) {
        return transform(input, alphabet, shiftedAlphabet);
    }
    
    public String decrypt(String input) {
        return transform(input, shiftedAlphabet, alphabet);
    }
    
    public String toString() {
        return "" + theKey;
    }
    
}
