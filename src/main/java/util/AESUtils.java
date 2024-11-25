package util;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Lớp cung cấp các phương thức mã hóa và giải mã dữ liệu sử dụng thuật toán
 * AES.
 */
public class AESUtils {

	private static final String PRIVATE_KEY = "3CoffeePriAESKey"; // 128 bits - 16 bytes

	private static SecretKeySpec getAESKey(String key) {
		byte[] keyBytes = key.getBytes();
		return new SecretKeySpec(keyBytes, "AES");
	}

	/**
	 * Mã hóa dữ liệu sử dụng thuật toán AES.
	 * 
	 * @param plainText Dữ liệu cần mã hóa.
	 * @return Dữ liệu đã được mã hóa.
	 * @throws Exception Nếu có lỗi trong quá trình mã hóa.
	 */
	public static String encrypt(String plainText) throws Exception {
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, getAESKey(PRIVATE_KEY));
		byte[] cipherText = cipher.doFinal(plainText.getBytes());
		return Base64.getEncoder().encodeToString(cipherText);
	}

	/**
	 * Giải mã dữ liệu đã được mã hóa sử dụng thuật toán AES.
	 * 
	 * @param cipherText Dữ liệu đã được mã hóa.
	 * @return Dữ liệu đã được giải mã.
	 * @throws Exception Nếu có lỗi trong quá trình giải mã.
	 */
	public static String decrypt(String cipherText) throws Exception {
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, getAESKey(PRIVATE_KEY));
		byte[] plainText = cipher.doFinal(Base64.getDecoder().decode(cipherText));
		return new String(plainText);
	}

}
