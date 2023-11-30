package assignmentTest;

import org.testng.annotations.Test;

import core.RandomData;

public class RandomDataTester {

	
	@Test
	public static void verifyRandomData() {
		
		RandomData rd = new RandomData();
		rd.getRandomEmail();
		

	}

}
