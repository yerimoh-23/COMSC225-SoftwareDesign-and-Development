package encryption;
/**
 * the interface
 * 
 * @author Yerim Oh
 * @date March 22, 2023
 */

public interface CipherStrategy {
	/**
	 * Encrypts text using the currently selected algorithm
	 * @param plainText the text to encrypt
	 * @param key the key the algorithm should use
	 * @return 
	 */
	public String encrypt(String plainText, int key);

	/**
	 * Decrypts text using the currently selected algorithm
	 * @param cipherText the text to encrypt
	 * @param key the key the algorithm should use
	 */
	public String decrypt(String cipherText, int key);
}