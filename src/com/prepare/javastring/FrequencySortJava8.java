package com.prepare.javastring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* Question:
 Java program that sorts a list of positive integers by frequency (highest first)  while preserving original order for ties



Key Points:
HashMap for frequency counting — O(n) time.
Stable sorting — Java’s List.sort() is stable, so elements with the same frequency keep original order.
*/
public class FrequencySortJava8 {

	public static void main(String[] args) {
	
		//Input Numbers
		List <Integer> numbs = Arrays.asList(4, 5, 6, 5, 4, 3, 4); // 4,5,6,5,3,4
		//Count frequency using HashMap
		HashMap <Integer,Integer> frequencyMap = new HashMap<Integer,Integer>();
		for (Integer number : numbs) {
			frequencyMap.put(number,frequencyMap.getOrDefault(number, 0)+1);
		}
		//Create a list of indices for stable sort
		// Sort by frequency (descending ) stable sort keeps original order for ties
		List<Integer> sortedNums = new ArrayList<>(numbs);
		sortedNums.sort((a,b) -> {
			int freqCompare = frequencyMap.get(b) - frequencyMap.get(a); // higher frequency first
			return freqCompare; // If equal stable sort keeps a original order
		});
		
		System.out.println("Final Output");
		for (Integer integer : sortedNums) {
			System.out.printf(" "+integer );
		}
	}
}

/*[4, 5, 6, 5, 3, 4], the output will be:
[4, 5, 5, 4, 6, 3]

4 and 5 appear first (highest frequency 2)
6 and 3 appear last (frequency 1)
Original relative order preserved for ties

*/