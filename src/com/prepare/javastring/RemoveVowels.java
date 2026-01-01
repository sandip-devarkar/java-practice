package com.prepare.javastring;

import java.util.Iterator;

/*You are given a string that may contain both uppercase and lowercase letters.
The vowels of the English alphabet are:
a, e, i, o, u, A, E, I, O, U.
Write an algorithm (or program) that removes all vowels from the given string and 
outputs the new string containing only consonants and other non‑vowel characters.
*/
public class RemoveVowels {

	public static void main(String[] args) {
		// Print the problem statement 
	    System.out.println("Problem: Remove all vowels (a, e, i, o, u, A, E, I, O, U) from a given string.");
		// Context specific variables
	    String input = "Hello World";  // Example input string
	    String result = "";            // Variable to store output without vowels
	    String vowelString= "aeiouAEIOU";
       // Step 1: Loop through each character of the input string
       char[]inputCharsArray =    input.toCharArray();
       for (int i = 0; i < inputCharsArray.length; i++) {
		  char ch =  inputCharsArray[i];  // Get current character
		  
		  // Step 2: Check if character is vowel 
		  //returns -1 if ch is not found (i.e., not a vowel)
		  if(vowelString.indexOf(ch) ==-1) {  
			  result = result + ch; // Step 3: If not vowel, add to result
		  }
		// Else skip the vowel
	   }
       // Step 4: Print final result
       System.out.println("Input String: " + input);
       System.out.println("String without vowels: " + result);
	    
	}
}
/*
Approach to Code and Run (30‑minute SHL exam strategy)
Step 1: Understand the problem (5 mins)
- Read the statement carefully.
- Clarify vowels list (both uppercase and lowercase).
- Example input/output to confirm understanding
Step 2: Plan the solution (5 mins)
- Decide variables: input, result.
- Decide logic: loop through string, skip vowels.
- Choose simple check method (indexOf).
Step 3: Write the code (10 mins)
- Start with printing the problem statement.
- Declare variables.
- Write loop with condition.
- Print result.
Step 4: Dry run & test (5 mins)
- Test with "Hello World".
- Verify output matches "Hll Wrld".
- Try edge cases: empty string, all vowels, no vowels.
Step 5: Final review (5 mins)
- Ensure comments at each line.
- Ensure code is readable and matches problem statement.
- Submit confidently
*/
