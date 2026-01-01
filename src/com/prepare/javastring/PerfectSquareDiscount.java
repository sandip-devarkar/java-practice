package com.prepare.javastring;

/*
A retail store is organizing a promotional event. Each shopper has a unique ShopperID numbered from 0 to N-1. The store manager has compiled the total purchase amounts for these shoppers.
 Shoppers whose total purchase amounts are perfect squares will receive a discount.
Task: Develop a program to count how many shoppers are eligible for the discount.
*/
public class PerfectSquareDiscount {

	public static void main(String[] args) {
		// Step 0: Print the problem statement
        System.out.println("Problem: Count shoppers whose purchase amounts are perfect squares.");

        // Context-specific variable: purchase amounts of shoppers
        int[] purchases = {10, 16, 25, 7, 9}; // Example amounts for ShopperIDs 0..4

        int eligibleCount = 0; // Variable to count eligible shoppers

        for (int i = 0; i < purchases.length; i++) {
            if(findTheEligbleShoppers(purchases[i])) {
            	eligibleCount ++;
            }

		}
        // Step 4: Print result
        System.out.println("Total shoppers eligible for discount: " + eligibleCount);

        
	}

	private static boolean findTheEligbleShoppers(int purchase) {
		   int sqrtPurchase = (int) Math.sqrt(purchase);
		return purchase  == sqrtPurchase * sqrtPurchase;
	}
}
