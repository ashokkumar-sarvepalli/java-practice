package learning.string.problems;

/*
 * 
Given an array of characters, compress it in-place.

The length after compression must always be smaller than or equal to the original array.

Every element of the array should be a character (not int) of length 1.

After you are done modifying the input array in-place, return the new length of the array.


a2b2c3
 */
public class StringCompression {

	public static void main(String[] args) {
		char []myChar = new char[] {'a','a','a','b','b','d','d','d'};
		System.out.println(compress(myChar));
		System.out.println(myChar);

	}

	public static int compress(char[] chars) {
		
		if(chars.length==0 || chars.length==1) return chars.length;

		char scan = chars[0];
		int overallCount = 1;
		int scanCount=1;
        int k=1;

		for (int i = 1; i < chars.length; i++) {

			if (scan == chars[i]) {
				scanCount++;
				continue;
			}
			
			if(scanCount>1) {
				overallCount = overallCount + 2;
				chars[k++] = (char) (scanCount +'0');
				scanCount=1;
			}
			else {
				overallCount = overallCount + 1;
			}
			scan = chars[i];
			chars[k++] = chars[i];

		}
		
		if(scanCount>1) { 
			
			overallCount++;
			chars[k++] = (char)(scanCount +'0');
		
		}
		
		chars[k++] = '\0';
		
		System.out.println(chars);

		return overallCount;

	}
}
