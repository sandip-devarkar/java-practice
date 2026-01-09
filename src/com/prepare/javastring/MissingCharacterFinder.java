package com.prepare.javastring;

import java.util.Iterator;

/*
Problem:
In a publishing house, a manuscript is sent for proofreading. The original manuscript contains a sequence of characters. Due to a printing error, the proofread version is missing one character.
👉 Task: Identify the missing character by comparing the original manuscript with the proofread version.

Input:
- First line: stringSent → original manuscript.
- Second line: stringRec → proofread manuscript.
Output:
- Print the missing character.

*/
public class MissingCharacterFinder  {

	public static void main(String[] args) {
		// Step 0: Print the problem statement
		System.out.println("Problem: Find the missing character from proofread manuscript compared to the original.");

		// Context-specific variables: original and proofread manuscripts
		String original = "abcdefg"; // Example original manuscript
		String proofread = "abcefg"; // Example proofread manuscript (missing 'd')
		
		// Step 2: Convert both strings to character arrays
		char[] originalCharArray = original.toCharArray();
		char[] proofreadCharArray = proofread.toCharArray();
		
		// step3: Count frequency of each character in the original 
		int [] frquency  = new int[255];
		for (char character : originalCharArray) {
			frquency[character] =  frquency[character]  + 1;
		}
		
		 // Step 4: Subtract frequency using proofread string
		for (char character : proofreadCharArray) {
			frquency[character] =  frquency[character]  - 1;
		}
		
		// Step 5: Find the character with frequency still > 0
		char missingCharcter= ' ';
		for (int i = 0; i < frquency.length; i++) {
			if(frquency[i] >0) {
				missingCharcter = (char)i;
				break;
			}
		}
		// Step 6: Print result
        System.out.println("Missing Character: " + missingCharcter);


	}

	
}
