package AES_CBC;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class AESCBC {

  private static final String ENCRYPT_ALGO = "AES/CBC/PKCS5PADDING";

  public byte[] encrypt(byte[] pText, SecretKey key) throws Exception {
    try {

      String stringSecretKey = Base64.getEncoder().encodeToString(key.getEncoded());

      String iv = new String(stringSecretKey).substring(0, 16);

      Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);

      byte[] dataBytes = pText;
      int plaintextLength = dataBytes.length;
      byte[] plaintext = new byte[plaintextLength];
      System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);
    
      IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());

      cipher.init(Cipher.ENCRYPT_MODE, key, ivspec);
      byte[] encrypted = cipher.doFinal(pText);

      return new String(Base64.getEncoder().encode(encrypted)).getBytes();

    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public byte[] decrypt(byte[] cText, SecretKey key) throws Exception {
    
    try {
      String stringSecretKey = Base64.getEncoder().encodeToString(key.getEncoded());

      String iv = new String(stringSecretKey).substring(0, 16);

      byte[] encrypted = Base64.getDecoder().decode(cText);

      Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);
      IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());

      cipher.init(Cipher.DECRYPT_MODE, key, ivspec);

      byte[] original = cipher.doFinal(encrypted);
      String originalString = new String(original);

      return originalString.getBytes();

    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}