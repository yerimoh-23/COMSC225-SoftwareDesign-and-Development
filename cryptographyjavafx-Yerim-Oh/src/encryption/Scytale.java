package encryption;
/**
 * Encrypt and decrypt text using Scytale.
 * A Scytale encrypts or decrypts by changing the order of letters.
 * Write the letters in rows with a fixed number of columns.
 *
 * @author Yerim Oh
 * @date March 22, 2023
 */

public class Scytale implements CipherStrategy {
	/**
	 * Encrypts text using Scytale
	 * @param plainText the text to encrypt
	 * @param key the key the algorithm should use
	 * @return the encrypted text
	 */
	public String encrypt(String plainText, int key) {
        String encrypted = "";
        int id = 0;
        
        // make a char 2D array
        // if plainText.length()%key!=0 -> add 1 to the height of the array
        int height = plainText.length()%key==0 ? plainText.length()/key : (plainText.length()/key)+1;
        char[][] letters = new char[height][key];
        
        // change plainText to char array
        char[] texts = plainText.toCharArray();
        
        for (int i = 0; i < height; i++) {
			for (int j = 0; j < key; j++) {
				// if done putting every character in the array -> fill in with space characters
				letters[i][j] = id < texts.length ? texts[id] : ' ';
				id++;
			}
		}
        
        // add the completed 2D array to the string
        
        for (int j = 0; j < key; j++) {
        	for (int i = 0; i < height; i++) {
				encrypted += letters[i][j];
			}
		}
        
        return encrypted;
    }
	
	/**
	 * Decrypts text using Scytale
	 * @param cipherText the text to decrypt
	 * @param key the key the algorithm should use
	 * @return the encrypted text
	 */
	public String decrypt(String cipherText, int key) {
		String decrypted = "";
        int id = 0;
        
        // make a char 2D array
        // if cipherText.length()%key!=0 -> add 1 to the height of the array
        int height = cipherText.length()%key==0 ? cipherText.length()/key : (cipherText.length()/key)+1;
        char[][] letters = new char[height][key];
        
        // change cipherText to char array
        char[] texts = cipherText.toCharArray();
        
        for (int j = 0; j < key; j++) {
        	for (int i = 0; i < height; i++) {
				// if done putting every character in the array -> fill in with space characters
				letters[i][j] = id < texts.length ? texts[id] : ' ';
				id++;
			}
		}
        
        // add the completed 2D array to the string
        for (int i = 0; i < height; i++) {
			for (int j = 0; j < key; j++) {
				decrypted += letters[i][j];
			}
		}
        
        return decrypted;
    }
}