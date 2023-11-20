package assignmentTest;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import assignments.Assignment1;

public class Assignment1Tester {
	
	@Test
	public void testOccuranceOfACharacter() {
		
		String str="AA";
		Map<Character,Integer> hashMapExpected= new HashMap<Character,Integer>();
		
		hashMapExpected.put('A',2);
		
		
		Map<Character,Integer> hashMapActual= new HashMap<Character,Integer>();
		hashMapActual=Assignment1.countOccurance(str);
		
		Assert.assertEquals(hashMapExpected, hashMapActual);
		
		
		
		
	}
	

}
