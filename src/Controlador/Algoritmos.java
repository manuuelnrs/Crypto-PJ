package Controlador;

// Algorithms Encryption | Decryption
import ChaCha20.ChaCha20;
import AES_EBC.AESEBC;
import AES_CBC.AESCBC;
import RSAOAEP.RSAOAEP;

// Algorithms Hash Operations
import SHA2.Sha2b384;
import SHA2.Sha2b512;
import SHA3.Sha3b384;
import SHA3.Sha3b512;

//Crypto and Security Libraries
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.PSSParameterSpec;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class Algoritmos {
    
    // Content Tables Vars
    public static String[][] TestVectorEncryption = new String[4][5];
    public static String[][] TestVectorDecryption = new String[4][5];
    public static String[][] TestVectorHash = new String[4][5];
    public static String[][] TestVectorSigning = new String[4][4];
    public static String[][] TestVectorVerifying = new String[4][4];
    
    // Test Vectors Vars
    public static String input = "Once upon a time there was a little dog named glue, he fell and got stuck";
    public static String input2 = "Don't cry, you're mine, For forever, for forever";
    public static String input3 = "ABCDEFGHIJKLMNÑOPQRSTUVWXZ1234567890";
    public static String input4 = ".***Each algorithm is used for some purpose; compare those of the common purpose***.";
    
    /* --------------- Tests Efficiency --------------- */
    public static void EncryptionOperations() throws Exception
    {
        ChaCha20 cipher = new ChaCha20();
        SecretKey key = getKey();           // 256-bit secret key (32 bytes)
        byte[] nonce = getNonce();          // 96-bit nonce (12 bytes)
        int counter = 1;                    // 32-bit initial count (8 bytes)

        System.out.println("****Algorithm ChaCha20***");
        System.out.println("Input          : " + input);
        System.out.println("Input     (hex): " + convertBytesToHex(input.getBytes()));
        System.out.println("\n---Encryption---");

        long startTime1 = System.nanoTime();
        byte[] cText = cipher.encrypt(input.getBytes(), key, nonce, counter);   // encrypt
        long endTime1 = System.nanoTime(); 

        System.out.println("Key       (hex): " + convertBytesToHex(key.getEncoded()));
        System.out.println("Nonce     (hex): " + convertBytesToHex(nonce));
        System.out.println("Counter        : " + counter);
        System.out.println("Encrypted (hex): " + convertBytesToHex(cText));
        String time = (endTime1 - startTime1)/1e6 + " ms";
        System.out.println("Encryption Time: " + time);
        TestVectorEncryption[0][0] = "Vector 1";
        TestVectorEncryption[0][1] = time;
        
        
        System.out.println("\n---Decryption---");
        long startTime2 = System.nanoTime();
        byte[] pText = cipher.decrypt(cText, key);              // decrypt
        long endTime2 = System.nanoTime();
        
        System.out.println("Key       (hex): " + convertBytesToHex(key.getEncoded()));
        System.out.println("Decrypted (hex): " + convertBytesToHex(pText));
        System.out.println("Decrypted      : " + new String(pText));
        time = (endTime2 - startTime2)/1e6 + " ms";
        System.out.println("Decryption Time: " + time);
        TestVectorDecryption[0][0] = "Vector 1";
        TestVectorDecryption[0][1] = time;
        
        startTime1 = System.nanoTime(); // Vector 2
        cText = cipher.encrypt(input2.getBytes(), key, nonce, counter);   // encrypt
        endTime1 = System.nanoTime(); 
        time = (endTime1 - startTime1)/1e6 + " ms";
        TestVectorEncryption[1][0] = "Vector 2";
        TestVectorEncryption[1][1] = time;
        startTime2 = System.nanoTime();
        pText = cipher.decrypt(cText, key);              // decrypt
        endTime2 = System.nanoTime();
        time = (endTime2 - startTime2)/1e6 + " ms";
        TestVectorDecryption[1][0] = "Vector 2";
        TestVectorDecryption[1][1] = time;
        
        startTime1 = System.nanoTime(); // Vector 3
        cText = cipher.encrypt(input3.getBytes(), key, nonce, counter);   // encrypt
        endTime1 = System.nanoTime(); 
        time = (endTime1 - startTime1)/1e6 + " ms";
        TestVectorEncryption[2][0] = "Vector 3";
        TestVectorEncryption[2][1] = time;
        startTime2 = System.nanoTime();
        pText = cipher.decrypt(cText, key);              // decrypt
        endTime2 = System.nanoTime();
        time = (endTime2 - startTime2)/1e6 + " ms";
        TestVectorDecryption[2][0] = "Vector 3";
        TestVectorDecryption[2][1] = time;
        
        startTime1 = System.nanoTime(); // Vector 4
        cText = cipher.encrypt(input4.getBytes(), key, nonce, counter);   // encrypt
        endTime1 = System.nanoTime(); 
        time = (endTime1 - startTime1)/1e6 + " ms";
        TestVectorEncryption[3][0] = "Vector 4";
        TestVectorEncryption[3][1] = time;
        startTime2 = System.nanoTime();
        pText = cipher.decrypt(cText, key);              // decrypt
        endTime2 = System.nanoTime();
        time = (endTime2 - startTime2)/1e6 + " ms";
        TestVectorDecryption[3][0] = "Vector 4";
        TestVectorDecryption[3][1] = time;
        /////////////////////////////////
        AESEBC cipher2 = new AESEBC();
        key = getKey();           // 256-bit secret key (32 bytes)

        System.out.println("****Algorithm AES-EBC***");
        System.out.println("Input          : " + input);
        System.out.println("Input     (hex): " + convertBytesToHex(input.getBytes()));
        System.out.println("\n---Encryption---");

        startTime1 = System.nanoTime();
        cText = cipher2.encrypt(input.getBytes(), key);  // encrypt
        endTime1 = System.nanoTime(); 

        System.out.println("Key       (hex): " + convertBytesToHex(key.getEncoded()));
        System.out.println("Encrypted (hex): " + convertBytesToHex(cText));
        time = (endTime1 - startTime1)/1e6 + " ms";
        System.out.println("Encryption Time: " + time);
        TestVectorEncryption[0][2] = time;
        
        System.out.println("\n---Decryption---");
        startTime2 = System.nanoTime();
        pText = cipher2.decrypt(cText, key);  // decrypt
        endTime2 = System.nanoTime();

        System.out.println("Key       (hex): " + convertBytesToHex(key.getEncoded()));
        System.out.println("Decrypted (hex): " + convertBytesToHex(pText));
        System.out.println("Decrypted      : " + new String(pText));
        time = (endTime2 - startTime2)/1e6 + " ms";
        System.out.println("Decryption Time: " + time);
        TestVectorDecryption[0][2] = time;
        
        startTime1 = System.nanoTime(); // Vector 2
        cText = cipher2.encrypt(input2.getBytes(), key);  // encrypt
        endTime1 = System.nanoTime(); 
        time = (endTime1 - startTime1)/1e6 + " ms";
        TestVectorEncryption[1][2] = time;
        startTime2 = System.nanoTime();
        pText = cipher2.decrypt(cText, key);  // decrypt
        endTime2 = System.nanoTime();
        time = (endTime2 - startTime2)/1e6 + " ms";
        TestVectorDecryption[1][2] = time;
        
        startTime1 = System.nanoTime(); // Vector 3
        cText = cipher2.encrypt(input3.getBytes(), key);  // encrypt
        endTime1 = System.nanoTime(); 
        time = (endTime1 - startTime1)/1e6 + " ms";
        TestVectorEncryption[2][2] = time;
        startTime2 = System.nanoTime();
        pText = cipher.decrypt(cText, key);              // decrypt
        endTime2 = System.nanoTime();
        time = (endTime2 - startTime2)/1e6 + " ms";
        TestVectorDecryption[2][2] = time;
        
        startTime1 = System.nanoTime(); // Vector 4
        cText = cipher2.encrypt(input4.getBytes(), key);  // encrypt
        endTime1 = System.nanoTime(); 
        time = (endTime1 - startTime1)/1e6 + " ms";
        TestVectorEncryption[3][2] = time;
        startTime2 = System.nanoTime();
        pText = cipher.decrypt(cText, key);              // decrypt
        endTime2 = System.nanoTime();
        time = (endTime2 - startTime2)/1e6 + " ms";
        TestVectorDecryption[3][2] = time;
        
        /////////////////////////////////
        AESCBC cipher3 = new AESCBC();
        key = getKey();           // 256-bit secret key (32 bytes)

        System.out.println("****Algorithm AES-CBC***");
        System.out.println("Input          : " + input);
        System.out.println("Input     (hex): " + convertBytesToHex(input.getBytes()));
        System.out.println("\n---Encryption---");

        startTime1 = System.nanoTime();
        cText = cipher3.encrypt(input.getBytes(), key);  // encrypt
        endTime1 = System.nanoTime(); 

        System.out.println("Key       (hex): " + convertBytesToHex(key.getEncoded()));
        System.out.println("Encrypted (hex): " + convertBytesToHex(cText));
        time = (endTime1 - startTime1)/1e6 + " ms";
        System.out.println("Encryption Time: " + time );
        TestVectorEncryption[0][3] = time;

        System.out.println("\n---Decryption---");
        startTime2 = System.nanoTime();
        pText = cipher3.decrypt(cText, key);  // decrypt
        endTime2 = System.nanoTime();

        System.out.println("Key       (hex): " + convertBytesToHex(key.getEncoded()));
        System.out.println("Decrypted (hex): " + convertBytesToHex(pText));
        System.out.println("Decrypted      : " + new String(pText));
        time = (endTime2 - startTime2)/1e6 + " ms";
        System.out.println("Decryption Time: " + time);
        TestVectorDecryption[0][3] = time;
        
        startTime1 = System.nanoTime(); // Vector 2
        cText = cipher3.encrypt(input2.getBytes(), key);  // encrypt
        endTime1 = System.nanoTime(); 
        time = (endTime1 - startTime1)/1e6 + " ms";
        TestVectorEncryption[1][3] = time;
        startTime2 = System.nanoTime();
        pText = cipher3.decrypt(cText, key);  // decrypt
        endTime2 = System.nanoTime();
        time = (endTime2 - startTime2)/1e6 + " ms";
        TestVectorDecryption[1][3] = time;
        
        startTime1 = System.nanoTime(); // Vector 3
        cText = cipher3.encrypt(input3.getBytes(), key);  // encrypt
        endTime1 = System.nanoTime(); 
        time = (endTime1 - startTime1)/1e6 + " ms";
        TestVectorEncryption[2][3] = time;
        startTime2 = System.nanoTime();
        pText = cipher3.decrypt(cText, key);  // decrypt
        endTime2 = System.nanoTime();
        time = (endTime2 - startTime2)/1e6 + " ms";  
        TestVectorDecryption[2][3] = time;
        
        startTime1 = System.nanoTime(); // Vector 3
        cText = cipher3.encrypt(input4.getBytes(), key);  // encrypt
        endTime1 = System.nanoTime(); 
        time = (endTime1 - startTime1)/1e6 + " ms";
        TestVectorEncryption[3][3] = time;
        startTime2 = System.nanoTime();
        pText = cipher3.decrypt(cText, key);  // decrypt
        endTime2 = System.nanoTime();
        time = (endTime2 - startTime2)/1e6 + " ms";  
        TestVectorDecryption[3][3] = time;
        
        /////////////////////////////////
        RSAOAEP cipher4 = new RSAOAEP();
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

        startTime1 = System.nanoTime();
        cText = cipher4.encrypt(input.getBytes(),pubKey);
        endTime1 = System.nanoTime(); 

        System.out.println("Encrypted (hex): " + convertBytesToHex(cText));
        time = (endTime1 - startTime1)/1e6 + " ms";
        System.out.println("Encrypted Time: " + time);
        TestVectorEncryption[0][4] = time;
        

        System.out.println("\n---Decrypt---");
        startTime2 = System.nanoTime();
        pText = cipher4.decrypt(cText,privKey);
        endTime2 = System.nanoTime(); 

        System.out.println("Decrypted (hex): " + convertBytesToHex(pText));
        System.out.println("Decrypted      : " + new String(pText));
        time = (endTime2 - startTime2)/1e6 + " ms";
        System.out.println("Decrypted Time : " + time);
        TestVectorDecryption[0][4] = time;
        
        startTime1 = System.nanoTime(); // Vector 2
        cText = cipher4.encrypt(input2.getBytes(),pubKey);
        endTime1 = System.nanoTime(); 
        time = (endTime1 - startTime1)/1e6 + " ms";
        TestVectorEncryption[1][4] = time;
        startTime2 = System.nanoTime();
        pText = cipher4.decrypt(cText,privKey);  // decrypt
        endTime2 = System.nanoTime();
        time = (endTime2 - startTime2)/1e6 + " ms";
        TestVectorDecryption[1][4] = time;
        
        startTime1 = System.nanoTime(); // Vector 3
        cText = cipher4.encrypt(input3.getBytes(),pubKey);
        endTime1 = System.nanoTime(); 
        time = (endTime1 - startTime1)/1e6 + " ms";
        TestVectorEncryption[2][4] = time;
        startTime2 = System.nanoTime();
        pText = cipher4.decrypt(cText,privKey);  // decrypt
        endTime2 = System.nanoTime();
        time = (endTime2 - startTime2)/1e6 + " ms";  
        TestVectorDecryption[2][4] = time;
        
        startTime1 = System.nanoTime(); // Vector 4
        cText = cipher4.encrypt(input4.getBytes(),pubKey);
        endTime1 = System.nanoTime(); 
        time = (endTime1 - startTime1)/1e6 + " ms";
        TestVectorEncryption[3][4] = time;
        startTime2 = System.nanoTime();
        pText = cipher4.decrypt(cText,privKey);  // decrypt
        endTime2 = System.nanoTime();
        time = (endTime2 - startTime2)/1e6 + " ms";  
        TestVectorDecryption[3][4] = time;
    }
    
    
    public static void HashOperations() throws Exception
    {    
        Sha2b384 cipher = new Sha2b384();
        System.out.println("****Algorithm Sha-2 384 bits***");
        System.out.println("Input          : " + input);
        System.out.println("\n---Hashing---");

        long startTime1 = System.nanoTime();
        System.out.println("Hash: "  + cipher.hashing(input));
        long endTime1 = System.nanoTime(); 
        String time = (endTime1 - startTime1)/1e6 + " ms";
        System.out.println("Hashing Time: " + time);
        TestVectorHash[0][0] = "Vector 1";
        TestVectorHash[0][1] = time;
        
        startTime1 = System.nanoTime(); // Vector 2
        System.out.println("Hash: "  + cipher.hashing(input2));
        endTime1 = System.nanoTime(); 
        time = (endTime1 - startTime1)/1e6 + " ms";
        System.out.println("Hashing Time: " + time);
        TestVectorHash[1][0] = "Vector 2";
        TestVectorHash[1][1] = time;
        
        startTime1 = System.nanoTime(); // Vector 3
        System.out.println("Hash: "  + cipher.hashing(input3));
        endTime1 = System.nanoTime(); 
        time = (endTime1 - startTime1)/1e6 + " ms";
        System.out.println("Hashing Time: " + time);
        TestVectorHash[2][0] = "Vector 3";
        TestVectorHash[2][1] = time;
        
        startTime1 = System.nanoTime(); // Vector 4
        System.out.println("Hash: "  + cipher.hashing(input4));
        endTime1 = System.nanoTime(); 
        time = (endTime1 - startTime1)/1e6 + " ms";
        System.out.println("Hashing Time: " + time);
        TestVectorHash[3][0] = "Vector 4";
        TestVectorHash[3][1] = time;
        
        /////////////////////////////////
        Sha2b512 cipher2 = new Sha2b512();
        System.out.println("****Algorithm Sha-2 512 bits***");
        System.out.println("Input          : " + input);
        System.out.println("\n---Hashing---");

        startTime1 = System.nanoTime(); 
        System.out.println("Hash: "  + cipher2.hashing(input));
        endTime1 = System.nanoTime();
        time = (endTime1 - startTime1)/1e6 + " ms";
        System.out.println("Hashing Time: " + time);
        TestVectorHash[0][2] = time;
        
        startTime1 = System.nanoTime(); // Vector 2
        System.out.println("Hash: "  + cipher2.hashing(input2));
        endTime1 = System.nanoTime();
        time = (endTime1 - startTime1)/1e6 + " ms";
        System.out.println("Hashing Time: " + time);
        TestVectorHash[1][2] = time;
        
        startTime1 = System.nanoTime(); // Vector 3
        System.out.println("Hash: "  + cipher2.hashing(input3));
        endTime1 = System.nanoTime();
        time = (endTime1 - startTime1)/1e6 + " ms";
        System.out.println("Hashing Time: " + time);
        TestVectorHash[2][2] = time;
        
        startTime1 = System.nanoTime(); // Vector 4
        System.out.println("Hash: "  + cipher2.hashing(input4));
        endTime1 = System.nanoTime();
        time = (endTime1 - startTime1)/1e6 + " ms";
        System.out.println("Hashing Time: " + time);
        TestVectorHash[3][2] = time;
        
        /////////////////////////////////
        Sha3b384 cipher3 = new Sha3b384();
        System.out.println("****Algorithm Sha-3 384 bits***");
        System.out.println("Input          : " + input);
        System.out.println("\n---Hashing---");

        startTime1 = System.nanoTime();
        System.out.println("Hash: "  + cipher3.hashing(input));
        endTime1 = System.nanoTime();
        time = (endTime1 - startTime1)/1e6 + " ms";
        System.out.println("Hashing Time: " + time);
        TestVectorHash[0][3] = time;
        
        startTime1 = System.nanoTime(); // Vector 2
        System.out.println("Hash: "  + cipher3.hashing(input2));
        endTime1 = System.nanoTime();
        time = (endTime1 - startTime1)/1e6 + " ms";
        System.out.println("Hashing Time: " + time);
        TestVectorHash[1][3] = time;
        
        startTime1 = System.nanoTime(); // Vector 3
        System.out.println("Hash: "  + cipher3.hashing(input3));
        endTime1 = System.nanoTime();
        time = (endTime1 - startTime1)/1e6 + " ms";
        System.out.println("Hashing Time: " + time);
        TestVectorHash[2][3] = time;
        
        startTime1 = System.nanoTime(); // Vector 4
        System.out.println("Hash: "  + cipher3.hashing(input4));
        endTime1 = System.nanoTime();
        time = (endTime1 - startTime1)/1e6 + " ms";
        System.out.println("Hashing Time: " + time);
        TestVectorHash[3][3] = time;
        
        /////////////////////////////////
        Sha3b512 cipher4 = new Sha3b512();
        System.out.println("****Algorithm Sha-3 512 bits***");
        System.out.println("Input          : " + input);
        System.out.println("\n---Hashing---");

        startTime1 = System.nanoTime();
        System.out.println("Hash: "  + cipher4.hashing(input));
        endTime1 = System.nanoTime(); 
        time = (endTime1 - startTime1)/1e6 + " ms";
        System.out.println("Hashing Time: " + time);
        TestVectorHash[0][4] = time;
        
        startTime1 = System.nanoTime(); //Vector 2
        System.out.println("Hash: "  + cipher4.hashing(input2));
        endTime1 = System.nanoTime(); 
        time = (endTime1 - startTime1)/1e6 + " ms";
        System.out.println("Hashing Time: " + time);
        TestVectorHash[1][4] = time;
        
        startTime1 = System.nanoTime(); //Vector 3
        System.out.println("Hash: "  + cipher4.hashing(input3));
        endTime1 = System.nanoTime(); 
        time = (endTime1 - startTime1)/1e6 + " ms";
        System.out.println("Hashing Time: " + time);
        TestVectorHash[2][4] = time;
        
        startTime1 = System.nanoTime(); //Vector 4
        System.out.println("Hash: "  + cipher4.hashing(input4));
        endTime1 = System.nanoTime(); 
        time = (endTime1 - startTime1)/1e6 + " ms";
        System.out.println("Hashing Time: " + time);
        TestVectorHash[3][4] = time;
    }
    
    
    public static void SigningOperations() throws Exception
    {      
        KeyPairGenerator g = KeyPairGenerator.getInstance("RSASSA-PSS", "SunRsaSign");
        g.initialize(1024); //KeySize 21024 bits
        KeyPair kp = g.genKeyPair();
        PrivateKey privKey = kp.getPrivate();
        PublicKey pubKey = kp.getPublic();
        
        System.out.println("****Algorithm RSA-PSS***");
        System.out.println("\nInput          : " + input);
        System.out.println("\n---Sign---");
        
        Signature s = Signature.getInstance("RSASSA-PSS", "SunRsaSign");
        s.setParameter(PSSParameterSpec.DEFAULT);
        s.initSign(privKey);
        s.update(input.getBytes()); //Vector
        
        long startTime1 = System.nanoTime();
        byte[] signature = s.sign();
        long endTime1 = System.nanoTime(); 

        System.out.println("Signed (hex): " + convertBytesToHex(signature));
        String time = (endTime1 - startTime1)/1e6 + " ms";
        System.out.println("Signed Time: " + time);
        TestVectorSigning[0][0] = "Vector 1";
        TestVectorSigning[0][1] = time;

        System.out.println("\n---Verify---");        
        s.initVerify(pubKey);
        s.update(input.getBytes()); //Vector
        
        long startTime2 = System.nanoTime();
        boolean pTextVerify = s.verify(signature);
        long endTime2 = System.nanoTime(); 

        System.out.println("Is ok?: " + pTextVerify);
        time = (endTime2 - startTime2)/1e6 + " ms";
        System.out.println("Verified Time : " + time);
        TestVectorVerifying[0][0] = "Vector 1";
        TestVectorVerifying[0][1] = time;
        
        s.initSign(privKey);
        s.update(input2.getBytes()); //Vector 2
        
        startTime1 = System.nanoTime();
        signature = s.sign();
        endTime1 = System.nanoTime(); 

        System.out.println("Signed (hex): " + convertBytesToHex(signature));
        time = (endTime1 - startTime1)/1e6 + " ms";
        System.out.println("Signed Time: " + time);
        TestVectorSigning[1][0] = "Vector 2";
        TestVectorSigning[1][1] = time;

        System.out.println("\n---Verify---");        
        s.initVerify(pubKey);
        s.update(input2.getBytes()); //Vector
        
        startTime2 = System.nanoTime();
        pTextVerify = s.verify(signature);
        endTime2 = System.nanoTime(); 

        System.out.println("Is ok?: " + pTextVerify);
        time = (endTime2 - startTime2)/1e6 + " ms";
        System.out.println("Verified Time : " + time);
        TestVectorVerifying[1][0] = "Vector 2";
        TestVectorVerifying[1][1] = time;
        
        s.initSign(privKey);
        s.update(input3.getBytes()); //Vector 3
        startTime1 = System.nanoTime();
        signature = s.sign();
        endTime1 = System.nanoTime(); 

        System.out.println("Signed (hex): " + convertBytesToHex(signature));
        time = (endTime1 - startTime1)/1e6 + " ms";
        System.out.println("Signed Time: " + time);
        TestVectorSigning[2][0] = "Vector 3";
        TestVectorSigning[2][1] = time;

        System.out.println("\n---Verify---");        
        s.initVerify(pubKey);
        s.update(input3.getBytes()); //Vector
        
        startTime2 = System.nanoTime();
        pTextVerify = s.verify(signature);
        endTime2 = System.nanoTime(); 

        System.out.println("Is ok?: " + pTextVerify);
        time = (endTime2 - startTime2)/1e6 + " ms";
        System.out.println("Verified Time : " + time);
        TestVectorVerifying[2][0] = "Vector 3";
        TestVectorVerifying[2][1] = time;
        
        s.initSign(privKey);
        s.update(input4.getBytes()); //Vector 4
        startTime1 = System.nanoTime();
        signature = s.sign();
        endTime1 = System.nanoTime(); 

        System.out.println("Signed (hex): " + convertBytesToHex(signature));
        time = (endTime1 - startTime1)/1e6 + " ms";
        System.out.println("Signed Time: " + time);
        TestVectorSigning[3][0] = "Vector 4";
        TestVectorSigning[3][1] = time;

        System.out.println("\n---Verify---");        
        s.initVerify(pubKey);
        s.update(input4.getBytes()); //Vector
        
        startTime2 = System.nanoTime();
        pTextVerify = s.verify(signature);
        endTime2 = System.nanoTime(); 

        System.out.println("Is ok?: " + pTextVerify);
        time = (endTime2 - startTime2)/1e6 + " ms";
        System.out.println("Verified Time : " + time);
        TestVectorVerifying[3][0] = "Vector 4";
        TestVectorVerifying[3][1] = time;
        
        /////////////////////////////////
        g = KeyPairGenerator.getInstance("EC", "SunEC");
        ECGenParameterSpec ecsp = new ECGenParameterSpec("secp521r1");
        g.initialize(ecsp);
        kp = g.genKeyPair();
        privKey = kp.getPrivate();
        pubKey = kp.getPublic();
        
        System.out.println("****Algorithm ECDSA-PF 521***");
        System.out.println("\nInput          : " + input);
        System.out.println("\n---Sign---");
        
        s = Signature.getInstance("SHA256withECDSA", "SunEC");
        s.initSign(privKey);
        s.update(input.getBytes()); //Vector
        
        startTime1 = System.nanoTime();
        signature = s.sign();
        endTime1 = System.nanoTime(); 
        
        System.out.println("Signed (hex): " + convertBytesToHex(signature));
        time = (endTime1 - startTime1)/1e6 + " ms";
        System.out.println("Signed Time: " + time);
        TestVectorSigning[0][2] = time;
        
        
        System.out.println("\n---Verify---");
        s.initVerify(pubKey);
        s.update(input.getBytes()); //Vector
        
        startTime2 = System.nanoTime();
        pTextVerify = s.verify(signature);
        endTime2 = System.nanoTime(); 

        System.out.println("Is ok?: " + pTextVerify);
        time = (endTime2 - startTime2)/1e6 + " ms";
        System.out.println("Verified Time : " + time);
        TestVectorVerifying[0][2] = time;
        
        s.initSign(privKey);
        s.update(input2.getBytes()); //Vector 2
        startTime1 = System.nanoTime();
        signature = s.sign();
        endTime1 = System.nanoTime(); 
        
        System.out.println("Signed (hex): " + convertBytesToHex(signature));
        time = (endTime1 - startTime1)/1e6 + " ms";
        System.out.println("Signed Time: " + time);
        TestVectorSigning[1][2] = time;
        
        
        System.out.println("\n---Verify---");
        s.initVerify(pubKey);
        s.update(input2.getBytes()); //Vector
        
        startTime2 = System.nanoTime();
        pTextVerify = s.verify(signature);
        endTime2 = System.nanoTime(); 

        System.out.println("Is ok?: " + pTextVerify);
        time = (endTime2 - startTime2)/1e6 + " ms";
        System.out.println("Verified Time : " + time);
        TestVectorVerifying[1][2] = time;
        
        s.initSign(privKey);
        s.update(input3.getBytes()); //Vector 3
        startTime1 = System.nanoTime();
        signature = s.sign();
        endTime1 = System.nanoTime(); 
        
        System.out.println("Signed (hex): " + convertBytesToHex(signature));
        time = (endTime1 - startTime1)/1e6 + " ms";
        System.out.println("Signed Time: " + time);
        TestVectorSigning[2][2] = time;
        
        
        System.out.println("\n---Verify---");
        s.initVerify(pubKey);
        s.update(input3.getBytes()); //Vector
        
        startTime2 = System.nanoTime();
        pTextVerify = s.verify(signature);
        endTime2 = System.nanoTime(); 

        System.out.println("Is ok?: " + pTextVerify);
        time = (endTime2 - startTime2)/1e6 + " ms";
        System.out.println("Verified Time : " + time);
        TestVectorVerifying[2][2] = time;
        
        s.initSign(privKey);
        s.update(input4.getBytes()); //Vector 4
        startTime1 = System.nanoTime();
        signature = s.sign();
        endTime1 = System.nanoTime(); 
        
        System.out.println("Signed (hex): " + convertBytesToHex(signature));
        time = (endTime1 - startTime1)/1e6 + " ms";
        System.out.println("Signed Time: " + time);
        TestVectorSigning[3][2] = time;
        
        
        System.out.println("\n---Verify---");
        s.initVerify(pubKey);
        s.update(input4.getBytes()); //Vector
        
        startTime2 = System.nanoTime();
        pTextVerify = s.verify(signature);
        endTime2 = System.nanoTime(); 

        System.out.println("Is ok?: " + pTextVerify);
        time = (endTime2 - startTime2)/1e6 + " ms";
        System.out.println("Verified Time : " + time);
        TestVectorVerifying[3][2] = time;
        
        /////////////////////////////////
        g = KeyPairGenerator.getInstance("EC", "SunEC");
        ecsp = new ECGenParameterSpec("sect571r1");
        g.initialize(ecsp);
        kp = g.genKeyPair();
        privKey = kp.getPrivate();
        pubKey = kp.getPublic();
        
        System.out.println("****Algorithm ECDSA-BF 571***");
        System.out.println("\nInput          : " + input);
        System.out.println("\n---Sign---");
        
        s = Signature.getInstance("SHA256withECDSA", "SunEC");
        s.initSign(privKey);
        s.update(input.getBytes()); //Vector
        
        startTime1 = System.nanoTime();
        signature = s.sign();
        endTime1 = System.nanoTime(); 
        
        System.out.println("Signed (hex): " + convertBytesToHex(signature));
        time = (endTime1 - startTime1)/1e6 + " ms";
        System.out.println("Signed Time: " + time);
        TestVectorSigning[0][3] = time;
        
        
        System.out.println("\n---Verify---");
        s.initVerify(pubKey);
        s.update(input.getBytes()); //Vector
        
        startTime2 = System.nanoTime();
        pTextVerify = s.verify(signature);
        endTime2 = System.nanoTime(); 

        System.out.println("Is ok?: " + pTextVerify);
        time = (endTime2 - startTime2)/1e6 + " ms";
        System.out.println("Verified Time : " + time);
        TestVectorVerifying[0][3] = time;
        
        s.initSign(privKey);
        s.update(input2.getBytes()); //Vector 2
        startTime1 = System.nanoTime();
        signature = s.sign();
        endTime1 = System.nanoTime(); 
        
        System.out.println("Signed (hex): " + convertBytesToHex(signature));
        time = (endTime1 - startTime1)/1e6 + " ms";
        System.out.println("Signed Time: " + time);
        TestVectorSigning[1][3] = time;
        
        
        System.out.println("\n---Verify---");
        s.initVerify(pubKey);
        s.update(input2.getBytes()); //Vector
        
        startTime2 = System.nanoTime();
        pTextVerify = s.verify(signature);
        endTime2 = System.nanoTime(); 

        System.out.println("Is ok?: " + pTextVerify);
        time = (endTime2 - startTime2)/1e6 + " ms";
        System.out.println("Verified Time : " + time);
        TestVectorVerifying[1][3] = time;
        
        s.initSign(privKey);
        s.update(input3.getBytes()); //Vector 3
        startTime1 = System.nanoTime();
        signature = s.sign();
        endTime1 = System.nanoTime(); 
        
        System.out.println("Signed (hex): " + convertBytesToHex(signature));
        time = (endTime1 - startTime1)/1e6 + " ms";
        System.out.println("Signed Time: " + time);
        TestVectorSigning[2][3] = time;
        
        
        System.out.println("\n---Verify---");
        s.initVerify(pubKey);
        s.update(input3.getBytes()); //Vector
        
        startTime2 = System.nanoTime();
        pTextVerify = s.verify(signature);
        endTime2 = System.nanoTime(); 

        System.out.println("Is ok?: " + pTextVerify);
        time = (endTime2 - startTime2)/1e6 + " ms";
        System.out.println("Verified Time : " + time);
        TestVectorVerifying[2][3] = time;
        
        s.initSign(privKey);
        s.update(input4.getBytes()); //Vector 4
        startTime1 = System.nanoTime();
        signature = s.sign();
        endTime1 = System.nanoTime(); 
        
        System.out.println("Signed (hex): " + convertBytesToHex(signature));
        time = (endTime1 - startTime1)/1e6 + " ms";
        System.out.println("Signed Time: " + time);
        TestVectorSigning[3][3] = time;
        
        
        System.out.println("\n---Verify---");
        s.initVerify(pubKey);
        s.update(input4.getBytes()); //Vector
        
        startTime2 = System.nanoTime();
        pTextVerify = s.verify(signature);
        endTime2 = System.nanoTime(); 

        System.out.println("Is ok?: " + pTextVerify);
        time = (endTime2 - startTime2)/1e6 + " ms";
        System.out.println("Verified Time : " + time);
        TestVectorVerifying[3][3] = time;
        
    }
    
    /* --------------- Tests Efficiency --------------- */
    
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
    // 96-bit nonce (12 bytes)

    private static byte[] getNonce() {
        byte[] newNonce = new byte[12];
        new SecureRandom().nextBytes(newNonce);
        return newNonce;
    }
    
}
