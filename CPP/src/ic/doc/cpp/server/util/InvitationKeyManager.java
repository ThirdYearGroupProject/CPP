package ic.doc.cpp.server.util;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.spec.KeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.interfaces.PBEKey;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class InvitationKeyManager {

	private static String key = "NoMoreSecret";
	private static SecretKey encKey;
	private static Cipher cipher = null;

	static {
		String algorithm = "AES";
		try {
			byte[] salt = new byte[16];  
			PBEKeySpec password = new PBEKeySpec(key.toCharArray(), salt, 1000, 128);  
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");  
			PBEKey key = (PBEKey) factory.generateSecret(password);  
			encKey = new SecretKeySpec(key.getEncoded(), algorithm); 
			
			cipher = Cipher.getInstance(algorithm);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static String generateInvitaion(
			final String companyCode) throws IllegalBlockSizeException,
			BadPaddingException, InvalidKeyException {

		String input = companyCode;
		cipher.init(Cipher.ENCRYPT_MODE, encKey);
		byte[] inputBytes = input.getBytes();
		byte[] cipherText = cipher.doFinal(inputBytes);
		
		return byteToString(cipherText);

	}

	public static String byteToString(byte[] bytes){
		String result = "";
		
		for(byte each : bytes){
			int value = each;
			result = result.concat(Integer.toString(value)).concat(" ");
		}
		
		return result;
		
	}
	
	public static byte[] stringToByte( String byteString ) throws NumberFormatException{
		String[] tokens = byteString.trim().split(" ");
		byte[] result = new byte[tokens.length];
		
		for(int i = 0; i<tokens.length;i++){
			int value = Integer.parseInt(tokens[i]);
			
			result[i] = (byte)value;

		}
		
		return result;
		
	}
	
	public static String decodeInvitaion(
			final String invitationCode) {


		byte[] recoveredBytes;
		String recovered="";
		try {
			cipher.init(Cipher.DECRYPT_MODE, encKey);
			byte[] cipherText = stringToByte(invitationCode);
			recoveredBytes = cipher.doFinal(cipherText);
			recovered = new String(recoveredBytes);
		}  
		catch (NumberFormatException e) {
			// well if stringToByte parsing fail, invitation code is invalid
		}
		catch (Exception e) {
				e.printStackTrace();
		}
			
		
		return recovered;

	}

}
