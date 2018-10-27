package learning.string.problems;

import java.util.Arrays;

/*
 * We are to write the letters of a given string S, from left to right into lines. Each line has maximum width 100 units, and if writing a letter would cause the width of the line to exceed 100 units, it is written on the next line. We are given an array widths, an array where widths[0] is the width of 'a', widths[1] is the width of 'b', ..., and widths[25] is the width of 'z'.

Now answer two questions: how many lines have at least one character from S, and what is the width used by the last such line? Return your answer as an integer list of length 2.

 

Example :
Input: 
widths = [10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10]
S = "abcdefghijklmnopqrstuvwxyz"
Output: [3, 60]
Explanation: 
All letters have the same length of 10. To write all 26 letters,
we need two full lines and one line with 60 units.

Example :
Input: 
widths = [4,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10]
S = "bbbcccdddaaa"
Output: [2, 4]
Explanation: 
All letters except 'a' have the same length of 10, and 
"bbbcccdddaa" will cover 9 * 10 + 2 * 4 = 98 units.
For the last 'a', it is written on the second line because
there is only 2 units left in the first line.
So the answer is 2 lines, plus 4 units in the second line.
 

Note:

The length of S will be in the range [1, 1000].
S will only contain lowercase letters.
widths is an array of length 26.
widths[i] will be in the range of [2, 10].
 */
public class NumberOfLinesToWriteString {

	public static void main(String[] args) {
		int[] widths = { 4, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10,
				10, 10 };
		int[] result = numberOfLines(widths, "bbbcccdddaaa");
		System.out.println(Arrays.toString(result));

	}

	public static int[] numberOfLines(int[] widths, String S) {

		int[] result = new int[2];
		int noOfLines = 1;
		int lineWidth = 100;
		for (int i = 0; i < S.length(); i++) {
			int size = widths[S.charAt(i) - 97];
			if (size > lineWidth) {
				noOfLines++;
				lineWidth = 100;
				i--;
				continue;
			}
			lineWidth = lineWidth - size;

			if (lineWidth == 0) {
				noOfLines++;
				lineWidth = 100;
			}

		}

		result[0] = noOfLines;
		result[1] = (lineWidth - 100) * -1;

		return result;

	}

	public static int[] numberOfLinesActual(int[] widths, String S) {
		int lines = 1, width = 0;
		for (char c : S.toCharArray()) {
			int w = widths[c - 'a'];
			width += w;
			if (width > 100) {
				lines++;
				width = w;
			}
		}

		return new int[] { lines, width };
	}

}
