package com.prepare.javastring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Develop a method to organize a list of positive integers such that the numbers are sorted in descending order based on their frequency of occurrence.
- Numbers that appear more frequently should come first.
- If two numbers have the same frequency, they should keep their original order from the list.
*/

public class FrequencySort {

	public static void main(String[] args) {
		// Step 0: Print the problem statement
		System.out.println(
				"Problem: Sort numbers in descending order of frequency. If frequency is same, keep original order.");
		// Context-specific variable: input list of numbers
		List<Integer> numbers = Arrays.asList(4, 5, 6, 5, 4, 3, 5, 4);

		// Step1 Count frequency of each number
		Map<Integer, Integer> frequencyMap = new HashMap<>();
		for (Integer integer : numbers) {
			frequencyMap.put(integer, frequencyMap.getOrDefault(integer, 0) + 1);
		}

		List<Integer> sorteNumbers = new ArrayList<>(numbers);
		sorteNumbers.sort((a,b) -> {
		    int freqb = frequencyMap.get(b);
		    int freqa = frequencyMap.get(a);
		    if(freqa !=freqb) { 
		    	return freqb - freqa;                     // descending order by frequency
		    }
			return numbers.indexOf(a)-numbers.indexOf(b); // maintain original order
			  
		});
		
		// Step Print Result
		System.out.println("Original List: " + numbers);
		System.out.println("Sorted by frequency: " + sorteNumbers);
	}
}
