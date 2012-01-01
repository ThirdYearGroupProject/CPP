package ic.doc.cpp.server;

import java.security.InvalidKeyException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

import ic.doc.cpp.server.util.InvitationKeyManager;
import junit.framework.Assert;

import org.junit.Test;

public class InvitationKeyManagerTest {

	@Test
	public void StringToByteTest2() {


		String plainText = "adxv";

		byte[] ByteCode = InvitationKeyManager.stringToByte(plainText);
		String plainText2 = InvitationKeyManager.byteToString(ByteCode);
		
	}
	
	@Test
	public void normalTest() throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

		String companyCode = "5";
		String inviteCode = InvitationKeyManager.generateInvitaion( companyCode);
		String plainText = InvitationKeyManager.decodeInvitaion( inviteCode);
		Assert.assertEquals(companyCode,plainText);
	}

	@Test
	public void wrongUserTest() throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

		String companyCode = "5";
		String inviteCode = InvitationKeyManager.generateInvitaion( companyCode.concat("1"));
		String plainText = InvitationKeyManager.decodeInvitaion( inviteCode);
		Assert.assertFalse( companyCode.equals(plainText));
	}
	
	@Test
	public void StringToByteTest() {


		byte[] inviteCode = new byte[4];
		inviteCode[0] = (byte)0;
		inviteCode[1] = (byte)52;
		inviteCode[2] = (byte)112;
		inviteCode[3] = (byte)127;
		String plainText;
		plainText = InvitationKeyManager.byteToString(inviteCode);
		byte[] ByteCode = InvitationKeyManager.stringToByte(plainText);
		Assert.assertTrue(ByteCode[0]==inviteCode[0]
				&&ByteCode[1]==inviteCode[1]
				&&ByteCode[2]==inviteCode[2]
				&&ByteCode[3]==inviteCode[3]);
		
	}
	
}
