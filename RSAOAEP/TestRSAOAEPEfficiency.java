import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;

public class TestRSAOAEPEfficiency {
    public static void main(String[] args) throws Exception {

        String input = "Each algorithm is used for some goal.";

        RSAOAEP cipher = new RSAOAEP();

        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(1024);

        // Generate the KeyPair
        KeyPair pair = generator.generateKeyPair();

        // Get the public and private key
        Key pubKey = pair.getPublic();
        Key privKey = pair.getPrivate();
        

        System.out.println("****Algorithm RSA-OAEP***");
        System.out.println("\nInput          : " + input);

        System.out.println("\n---Encrypt---");

        long startTime1 = System.nanoTime();
        byte[] cText = cipher.encrypt(input.getBytes(),pubKey);
        long endTime1 = System.nanoTime(); 

        System.out.println("Encrypted (hex): " + convertBytesToHex(cText));
        System.out.println("Encrypted Time: " + (endTime1 - startTime1)/1e6 + " ms");

        System.out.println("\n---Decrypt---");
        long startTime2 = System.nanoTime();
        byte[] pText = cipher.decrypt(cText,privKey);
        long endTime2 = System.nanoTime(); 

        System.out.println("Decrypted (hex): " + convertBytesToHex(pText));
        System.out.println("Decrypted      : " + new String(pText));
        System.out.println("Decrypted Time : " + (endTime2 - startTime2)/1e6 + " ms");
    }

    private static String convertBytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte temp : bytes) {
            result.append(String.format("%02x", temp));
        }
        return result.toString();
    }

}