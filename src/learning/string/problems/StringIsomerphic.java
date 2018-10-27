package learning.string.problems;

import java.util.HashMap;
import java.util.Map;

public class StringIsomerphic {

	public static void main(String[] args) {
	
		System.out.println(isIsomorphic("ab","aa"));

	}
	
	 public static boolean isIsomorphic(String s, String t) {
		    Map<Character,Character> isoMap = new HashMap<>();
		    
		    
		    for(int i=0;i<s.length();i++) {
		    	
		    	if(isoMap.containsKey(s.charAt(i)) && isoMap.get(s.charAt(i))!=t.charAt(i)) {
		    		return false;
		    	}
		    	
		    
		    	
		    	isoMap.put(s.charAt(i), t.charAt(i));
		    }
		    
		    isoMap.clear();
		    
		    for(int i=0;i<t.length();i++) {
		    	
		    	if(isoMap.containsKey(t.charAt(i)) && isoMap.get(t.charAt(i))!=s.charAt(i)) {
		    		return false;
		    	}
		    	
		    	isoMap.put(t.charAt(i), s.charAt(i));
		    }
	        return true;
	        
	    }
}
