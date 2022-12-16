package AES_CBC;

import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import javax.crypto.KeyGenerator;
import java.security.SecureRandom;


public class TestAESCBCEfficiency {

    public static void main(String args[]) throws Exception {

    String input = "Each algorithm is used for some goal; therefore, you need to compare only the ones that share such a goal.";
    
    AESCBC cipher = new AESCBC();

    SecretKey key = getKey();           // 256-bit secret key (32 bytes)

    System.out.println("****Algorithm AES-CBC***");
    System.out.println("Input          : " + input);
    System.out.println("Input     (hex): " + convertBytesToHex(input.getBytes()));

    System.out.println("\n---Encryption---");

    long startTime1 = System.nanoTime();
    byte[] cText = cipher.encrypt(input.getBytes(), key);  // encrypt
    long endTime1 = System.nanoTime(); 

    System.out.println("Key       (hex): " + convertBytesToHex(key.getEncoded()));
    System.out.println("Encrypted (hex): " + convertBytesToHex(cText));
    System.out.println("Encryption Time: " + (endTime1 - startTime1)/1e6 + " ms");

    System.out.println("\n---Decryption---");

    long startTime2 = System.nanoTime();
    byte[] pText = cipher.decrypt(cText, key);  // decrypt
    long endTime2 = System.nanoTime();

    System.out.println("Key       (hex): " + convertBytesToHex(key.getEncoded()));
    System.out.println("Decrypted (hex): " + convertBytesToHex(pText));
    System.out.println("Decrypted      : " + new String(pText));
    System.out.println("Decryption Time: " + (endTime2 - startTime2)/1e6 + " ms");
    }

    private static String convertBytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte temp : bytes) {
            result.append(String.format("%02x", temp));
        }
        return result.toString();
    }

    // A 256-bit secret key (32 bytes)
    private static SecretKey getKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256, SecureRandom.getInstanceStrong());
        return keyGen.generateKey();
    }
}