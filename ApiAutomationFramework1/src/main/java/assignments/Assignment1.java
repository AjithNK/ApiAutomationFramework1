package assignments;

import java.util.HashMap;
import java.util.Map;

public class Assignment1 {
	
	
	public static HashMap<Character, Integer> countOccurance(String str) {
		
		Map<Character, Integer> hashMap = new HashMap<Character,Integer>();

		
		for(int i=0;i<str.length();i++) {
			char ch =str.charAt(i);
			if(ch!=' ') {
				if(!(hashMap.containsKey(ch))) {
					hashMap.put(ch, 1);
				}
				else {
					hashMap.put(ch, hashMap.get(ch)+1);
				}
			
			}
		
		}
		
		return (HashMap<Character, Integer>) hashMap;
		
	}

}
