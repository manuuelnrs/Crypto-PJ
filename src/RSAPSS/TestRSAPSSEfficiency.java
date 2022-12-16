package RSAPSS;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

public class TestRSAPSSEfficiency {
    public static void main(String[] args) throws Exception {

        String input = "Each algorithm is used for some goal.";

        RSAPSS cipher = new RSAPSS();

        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(1024);

        // Generate the KeyPair
        KeyPair pair = generator.generateKeyPair();

        // Get the public and private key
        PublicKey  pubKey = pair.getPublic();
        PrivateKey  privKey = pair.getPrivate();
        

        System.out.println("****Algorithm RSA-PSS***");
        System.out.println("\nInput          : " + input);

        System.out.println("\n---Sign---");

        long startTime1 = System.nanoTime();
        byte[] signature = cipher.sign(input.getBytes(),privKey);
        long endTime1 = System.nanoTime(); 

        System.out.println("Signed (hex): " + convertBytesToHex(signature));
        System.out.println("Signed Time: " + (endTime1 - startTime1)/1e6 + " ms");

        System.out.println("\n---Verify---");
        long startTime2 = System.nanoTime();
        boolean pTextVerify = cipher.verify(input.getBytes(), convertBytesToHex(signature),pubKey);
        long endTime2 = System.nanoTime(); 

        System.out.println("Is ok?: " + pTextVerify);
        System.out.println("Verified Time : " + (endTime2 - startTime2)/1e6 + " ms");
    }

    private static String convertBytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte temp : bytes) {
            result.append(String.format("%02x", temp));
        }
        return result.toString();
    }

}