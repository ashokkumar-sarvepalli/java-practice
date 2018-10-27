package learning.string.problems;

import java.util.Arrays;
import java.util.List;

/*
 * Write a function that takes a string as input and reverse only the vowels of a string.

Example 1:

Input: "hello"
Output: "holle"
Example 2:

Input: "leetcode"
Output: "leotcede"
Note:
The vowels does not include the letter "y".

 */
public class ReverseVowels {

	public static void main(String[] args) {
		System.out.println(reverseVowels("leetcode"));

	}
	
	
	 public static String reverseVowels(String s) {
		 
		  char [] input = s.toCharArray();
	        
	        List<Character> vowelList = Arrays.asList('a','e','i','o','u');
	        
	        int middle = s.length()/2;
	        int last = s.length()-1;
	        int start = 0;
	        while(start<last){
	            
	            if(vowelList.contains(input[start]) && vowelList.contains(input[last])){
	                char temp = input[start];
	                input[start] = input[last];
	                input[last] = temp;
	                start++;
	                last--;
	            }
	            else if(vowelList.contains(input[start])){
	                last--;
	            }
	            else{
	                start++;
	            }
	            
	        }
	        
	        return new String(input);
		 
	 }

}
