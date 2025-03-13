package encryption;
/**
 * the context
 * 
 * @author Yerim Oh
 * @date March 22, 2023
 */

public class CipherContext {
	CipherStrategy cipherStrategy;

	// set strategy method
	public void setCipherStrategy (CipherStrategy cipherStrategy) {
		this.cipherStrategy = cipherStrategy;
	}
	
	public String encrypted (String plainText, int key) {
		String encrypted = cipherStrategy.encrypt(plainText, key);
		return encrypted;
	}
	
	public String decrypted (String cipherText, int key) {
		String decrypted = cipherStrategy.decrypt(cipherText, key);
		return decrypted;
	}
}