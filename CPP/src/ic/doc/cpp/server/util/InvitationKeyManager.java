package ic.doc.cpp.server.util;

import java.security.InvalidKeyException;
import java.security.Key;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;

public class InvitationKeyManager {

	private static Key key = null;
	private static Cipher cipher = null;

	static {
		String algorithm = "AES";
		try {
			key = KeyGenerator.getInstance(algorithm).generateKey();
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
		cipher.init(Cipher.ENCRYPT_MODE, key);
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
			//wrap needed as int is 32bit and byte is 8 bit
			if( value <= 127 && value >= 0){
				result[i] = (byte)value;
			}else{
				throw new NumberFormatException("byte String contain out of range field");
			}

		}
		
		return result;
		
	}
	
	public static String decodeInvitaion(
			final String invitationCode) {


		byte[] recoveredBytes;
		String recovered="";
		try {
			cipher.init(Cipher.DECRYPT_MODE, key);
			recoveredBytes = cipher.doFinal(stringToByte(invitationCode));
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
