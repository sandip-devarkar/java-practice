package com.prepare.javastring;

/*
 * Problem Statement: Maximum Reaction Rate
Professor Smith is conducting a study involving a sequence of reactive substances.
Each substance has a unique reaction rate, represented as an integer.
Professor Smith performs the following steps:
He selects two equal-sized, non-overlapping groups of substances.
The substances in each group are arranged consecutively in the sequence.
He reverses the order of substances in the second group.
Each substance in the first group is then combined with the corresponding substance from the reversed second group.
The reaction rate of a combined pair is defined as the product of the reaction rates of the two substances.
The total reaction rate for the selected groups is the sum of the products of all paired substances.
Professor Smith may choose any valid pair of such groups to maximize the total reaction rate.
If the maximum achievable total reaction rate is negative, the result should be 0.*/
public class FindMaximunReactionRate {

	public static void main(String[] args) {
		System.out.println("Welcome to the FindMaximunReactionRate Programm");
		
		int[] reactionRates = {1, 2, 3, 4};
		
		if(-1 == findMaximumReactionRate(reactionRates)){
			System.out.println("Maximum Reaction Rate is not possible");
		}else {
			System.out.println("Maximum Reaction Rate is ====>"+findMaximumReactionRate(reactionRates));
		}
	}

	private static int findMaximumReactionRate(int[] reactionRates) {
		if(reactionRates == null || reactionRates.length <2) {
			return -1;
		}
		
		int totalSubstance = reactionRates.length;
		int maximumReactionRate = 0;
		
        // EVEN-length groups
		for(int centerIndex=0;centerIndex <totalSubstance ; centerIndex++)
		{
			int leftGroupIndex = centerIndex;
			int rightGroupIndex = centerIndex + 1;
			int currentReactionSum = 0;
			
			while(leftGroupIndex >= 0&& rightGroupIndex < totalSubstance) {
				currentReactionSum  = currentReactionSum + reactionRates[leftGroupIndex] * reactionRates[rightGroupIndex];
				maximumReactionRate = Integer.max(maximumReactionRate, currentReactionSum);
				leftGroupIndex--;
				rightGroupIndex++;
			}
			
		}

        // ODD-length groups
		for(int centerIndex=0;centerIndex <totalSubstance ; centerIndex++)
		{
			int leftGroupIndex = centerIndex-1;
			int rightGroupIndex = centerIndex + 1;
			int currentReactionSum = 0;
			
			while(leftGroupIndex >= 0&& rightGroupIndex < totalSubstance) {
				currentReactionSum  = currentReactionSum + reactionRates[leftGroupIndex] * reactionRates[rightGroupIndex];
				maximumReactionRate = Integer.max(maximumReactionRate, currentReactionSum);
				leftGroupIndex--;
				rightGroupIndex++;
			}
			
		}
		
		return maximumReactionRate;
	}
}
