package com.prepare.javastring;

/*
finds the minimum cost to buy exactly N apples using only boxes from two shops. 
I use a straightforward loop over the possible number of boxes 
from Shop A and check if the remainder can be filled by boxes from Shop B.
*/
public class MinAppleCost {
    public static void main(String[] args) {

        // ------------ INPUTS ------------
        int N = 10;        // total apples required
        int sizeA = 3;     // apples per box in Shop A
        int costA = 5;     // cost per box in Shop A
        int sizeB = 5;     // apples per box in Shop B
        int costB = 8;     // cost per box in Shop B

        // We will try all possible numbers of A-boxes.
        // (Maximum A-boxes is N / sizeA because more than that would exceed N)
        int maxA = N / sizeA;

        int bestCost = Integer.MAX_VALUE;   // start with a very large number
        int bestA = -1;                     // best number of boxes from Shop A
        int bestB = -1;                     // best number of boxes from Shop B

        // ------------ MAIN LOOP ------------
        // Try all values of A-box count: 0,1,2,...,maxA
        for (int x = 0; x <= maxA; x++) {

            int applesFromA = x * sizeA;       // apples contributed by Shop A boxes
            int remaining = N - applesFromA;   // apples that still need to be bought

            // If remaining is negative, stop (but it won't be because maxA stops extra)
            if (remaining < 0) continue;

            // Remaining apples must be EXACTLY divisible by sizeB
            if (remaining % sizeB != 0) {
                continue;   // cannot use B-boxes to fill exactly, skip this x
            }

            int y = remaining / sizeB;         // number of B-boxes needed

            int totalCost = x * costA + y * costB;  // cost of this combination

            // If this total cost is less than previous best, update best answer
            if (totalCost < bestCost) {
                bestCost = totalCost;
                bestA = x;
                bestB = y;
            }
        }

        // ------------ OUTPUT RESULT ------------
        if (bestCost == Integer.MAX_VALUE) {
            System.out.println("Impossible to buy exactly " + N + " apples.");
        } else {
            System.out.println("Minimum cost = " + bestCost);
            System.out.println("Buy " + bestA + " boxes from Shop A and " + bestB + " boxes from Shop B.");
        }
    }
}
