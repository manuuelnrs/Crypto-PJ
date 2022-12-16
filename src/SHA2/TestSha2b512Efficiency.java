package SHA2;

public class TestSha2b512Efficiency {

    public static void main(String args[]) throws Exception
    {
            String input = "Each algorithm is used for some goal; therefore, you need to compare only the ones that share such a goal.";
            Sha2b512 cipher = new Sha2b512();

            System.out.println("****Algorithm Sha-2 512 bits***");
            System.out.println("Input          : " + input);

            System.out.println("\n---Hashing---");

            long startTime1 = System.nanoTime(); 
            System.out.println("Hash: "  + cipher.hashing(input));
            long endTime1 = System.nanoTime();
            System.out.println("Decryption Time: " + (endTime1 - startTime1)/1e6 + " ms");
    }
}
