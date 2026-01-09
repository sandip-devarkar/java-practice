package com.prepare.javastring;

/*
 * Problem:
In a town, there are N landmarks aligned in a straight line.
- Each landmark has a unique identifier and a position (distance from the town’s entrance).
- A developer wants to construct the largest possible building between two landmarks.
👉 Task: Find the identifiers of the two landmarks between which the largest plot of land can be constructed.

 */
public class LargestPlot {
	
	public static void main(String[] args) {
		   // Step 0: Print the problem statement
		   // System.out.println("Problem: Find two landmarks with the largest distance between them.");
			 // Context-specific variables: identifiers and positions
			int ids[] = {1, 2, 3, 4, 5};       // Landmark identifiers
			int[] positions = {2,10,4,25,15}; // Positions relative to town entrance
			
			// Step 1: Track max distance and landmark IDs
			int maxDistance = 0;
			int landMark1 = -1;
			int landMark2 = -1;
			
			// compare all pairs of landmarks
			
			for(int i=0; i< positions.length; i++) {
				
				for(int j= i+1; i< positions.length; i++) {
					int distance = Math.abs(positions[j] - positions[i]);
					if(distance >maxDistance) {
						
						maxDistance = distance;
						 landMark1 = ids[i];
						 landMark2 = ids[j];
					}
				}
				
			}
			// Step 3: Print result
	        System.out.println("Largest plot is between Landmark " + landMark1 + " and Landmark " + landMark2);
	        System.out.println("Distance = " + maxDistance);
	}
}
