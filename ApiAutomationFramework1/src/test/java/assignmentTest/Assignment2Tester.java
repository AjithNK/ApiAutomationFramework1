package assignmentTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import assignments.Assignment1;
import assignments.Assignment2;

public class Assignment2Tester {
	
	@Test
	public void testAnagram() {
		
		String[] words = {"eat","ate","tea","test","tset"};//declare string array

	    List<List<String>> a = new ArrayList<>();

	    for(String word : words) {   
	      boolean isPlaced = false;
	      for(List<String> group : a) {
	        if(Assignment2.areAnagrams(group.get(0), word)) {
	          group.add(word);
	          isPlaced = true;
	          break;
	        }
	      }
	      if(!isPlaced) {
	        List<String> newGroup = new ArrayList<>();
	        newGroup.add(word);
	        a.add(newGroup);
	      }
	    }

	    System.out.println(a);

	  }

	

}
