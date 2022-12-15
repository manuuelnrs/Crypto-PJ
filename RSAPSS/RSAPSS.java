import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.Base64;


public class RSAPSS {

    public byte[] sign(byte[] pText, PrivateKey privKey) throws Exception {
        String stringText = new String(pText);
        Signature privateSignature = Signature.getInstance("RSASSA-PSS", stringText);
        privateSignature.initSign(privKey);
        privateSignature.update(pText);

        byte[] signature = privateSignature.sign();

        return signature;
    }

    public boolean verify(byte[] cText, String signature, PublicKey pubKey) throws Exception {

        Signature publicSignature = Signature.getInstance("RSASSA-PSS", signature);
        publicSignature.initVerify(pubKey);
        publicSignature.update(cText);

        byte[] signatureBytes = Base64.getDecoder().decode(signature);

        return publicSignature.verify(signatureBytes);
        
    }
    

}