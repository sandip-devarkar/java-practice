package com.prepare.javastring;

/*
In a publishing house, a manuscript is sent for proofreading. The original manuscript contains a 
sequence of characters. However, due to a printing error, the proofread version is missing one
 character from the original.
👉 Task: Develop a program to help the editor find the character that was present in the original 
manuscript but is missing in the proofread version.
*/
public class MissingCharacter {

	public static void main(String[] args) {
		// Step 0: Print the problem statement
		System.out.println("Problem: Find the missing character from proofread manuscript compared to the original.");

		// Context-specific variables: original and proofread manuscripts
		String original = "abcdefg"; // Example original manuscript
		String proofread = "abcefg"; // Example proofread manuscript (missing 'd')

		char[] originalCharArray = original.toCharArray();
		char[] proofreadCharArray = proofread.toCharArray();
		findDiffernce(originalCharArray, proofreadCharArray);

	}

	private static void findDiffernce(char[] originalCharArray, char[] proofreadCharArray) {
		int originalCharArrayLength = originalCharArray.length;
		int proofreadCharArrayLength = proofreadCharArray.length;
		int loopCounter = Math.min(originalCharArrayLength, proofreadCharArrayLength);
		for (int i = 0; i < loopCounter; i++) {

		}

	}
}
