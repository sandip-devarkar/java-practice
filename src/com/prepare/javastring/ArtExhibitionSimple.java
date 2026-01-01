package com.prepare.javastring;

import java.util.Iterator;

/*
An attendee is visiting an art exhibition where each artwork is rated by stars.
There are two categories of artworks available:
Paintings
Sculptures
The attendee wants to purchase artworks only from these two categories.
Each artwork has:A costA star rating
The attendee has a fixed budget and wants to:
Buy at least one painting
Buy at least one sculpture
Ensure the total cost does not exceed the budget
Maximize the total star rating of the purchased artworks
If it is not possible to purchase at least one artwork from both categories within the given budget, 
the output should be -1.
*/
public class ArtExhibitionSimple {

	public static void main(String[] args) {
		int []paintingCost = {4,6};
		int[] paintingRating = {6,9};
		int[] scluptureCost = {3,5};
		int[] scluptureRating = {5,8};
		
		int budget = 10;
		System.out.println("maxRating" +maxTotalRating(paintingCost,paintingRating,scluptureCost,scluptureRating,budget));
	}

	private static int maxTotalRating(int[] paintingCost, int[] paintingRating, int[] scluptureCost,
			int[] scluptureRating,int budget) {
		
		int maxRating = -1;
		
		for(int i=0; i< paintingCost.length; i++) {
			for(int j=0; j <scluptureCost.length; j++) {
				
				int totalCost = paintingCost[i] +scluptureCost[j];
				
				if(totalCost < budget) {
					maxRating = Integer.max(maxRating,paintingRating[i]+scluptureRating[j]);
				}
			}
		}
		return maxRating;
	}
}
