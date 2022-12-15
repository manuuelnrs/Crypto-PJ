import java.security.Key;
import java.security.SecureRandom;
import javax.crypto.Cipher;

public class RSAOAEP {

    private static final String ENCRYPT_ALGO = "RSA/ECB/OAEPWithSHA-1AndMGF1Padding";

    public byte[] encrypt(byte[] pText, Key pubKey) throws Exception {

        Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);
        SecureRandom random = new SecureRandom();

        cipher.init(Cipher.ENCRYPT_MODE, pubKey, random);
        byte[] cipherText = cipher.doFinal(pText);

        return cipherText;
    }

    public byte[] decrypt(byte[] cText, Key privKey) throws Exception {

        Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);

        cipher.init(Cipher.DECRYPT_MODE, privKey);
        byte[] plainText = cipher.doFinal(cText);
        
        return plainText;
    }
    

}