

public class TestSha3b384Efficiency {

	public static void main(String args[]) throws Exception
	{
		String input = "Each algorithm is used for some goal; therefore, you need to compare only the ones that share such a goal.";
		Sha3b384 cipher = new Sha3b384();

		System.out.println("****Algorithm Sha-3 384 bits***");
		System.out.println("Input          : " + input);

		System.out.println("\n---Hashing---");

		long startTime1 = System.nanoTime();
		System.out.println("Hash: "  + cipher.hashing(input));
		long endTime1 = System.nanoTime(); 

		System.out.println("Hashing Time: " + (endTime1 - startTime1)/1e6 + " ms");

	}
}
