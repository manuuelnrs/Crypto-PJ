import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;

public class AESEBC {

  private static final String ENCRYPT_ALGO = "AES/ECB/PKCS5PADDING";

  public byte[] encrypt(byte[] pText, SecretKey key) throws Exception {
    try {

      Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);

      byte[] dataBytes = pText;
      int plaintextLength = dataBytes.length;
      byte[] plaintext = new byte[plaintextLength];
      System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);
    

      cipher.init(Cipher.ENCRYPT_MODE, key);
      byte[] encrypted = cipher.doFinal(pText);

      return new String(Base64.getEncoder().encode(encrypted)).getBytes();

    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public byte[] decrypt(byte[] cText, SecretKey key) throws Exception {
    
    try {


      byte[] encrypted = Base64.getDecoder().decode(cText);

      Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);

      cipher.init(Cipher.DECRYPT_MODE, key);

      byte[] original = cipher.doFinal(encrypted);
      String originalString = new String(original);

      return originalString.getBytes();

    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}