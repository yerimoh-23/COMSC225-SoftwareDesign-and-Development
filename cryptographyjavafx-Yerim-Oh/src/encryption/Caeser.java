package encryption;
/**
 * Encrypt and decrypt text using a Caesar cipher.
 * A Caesar cipher encrypts or decrypts by shifting letters of the text.
 * Use ASCII for the ordered characters.
 *
 * @author Yerim Oh
 * @date March 22, 2023
 */

public class Caeser implements CipherStrategy {
	/**
	 * Encrypts text using Caeser cipher
	 * @param plainText the text to encrypt
	 * @param key the key the algorithm should use
	 * @return the encrypted text
	 */
	public String encrypt(String plainText, int key) {
        String encrypted = "";
        for (int i = 0; i < plainText.length(); i++) {
            int characterValue = plainText.charAt(i) + key;
            // char values are between 32 and 126 (total 95)
            if (characterValue > 126) {
            	characterValue -= 95;
            } else if (characterValue < 32) {
            	characterValue += 95;
            }
            encrypted += (char) characterValue;
        }
        return encrypted;
    }
	
	/**
	 * Decrypts text using Caeser cipher
	 * @param cipherText the text to decrypt
	 * @param key the key the algorithm should use
	 * @return the encrypted text
	 */
	public String decrypt(String cipherText, int shift) {
		String decrypted = "";
		decrypted = encrypt(cipherText, -shift);
		return decrypted;
    }
}