package com.prepare.javastring;

import java.util.Scanner;
/*
Problem restated
- You’re given an array of error scores for employees.
- Each project can fix k errors (instead of just 1).
- We want the minimum number of projects needed so that all errors become zero.
- If all errors are already zero, print 0.
*/
public class MinimumProjectsBinarySearch {
    // Function to check if 'projects' are enough
    private static boolean canFix(int[] errors, int projects, int k) {
        long totalCapacity = (long) projects * k; // each project fixes k errors
        long totalErrors = 0;
        for (int e : errors) totalErrors += e;
        return totalCapacity >= totalErrors;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Step 1: Input
        int n = sc.nextInt(); // number of employees
        int[] errors = new int[n];
        for (int i = 0; i < n; i++) {
            errors[i] = sc.nextInt();
        }
        int k = sc.nextInt(); // errors fixed per project

        // Step 2: total errors
        long totalErrors = 0;
        for (int e : errors) totalErrors += e;

        if (totalErrors == 0) {
            System.out.println(0);
            return;
        }

        // Step 3: Binary search range [0, totalErrors]
        long low = 0, high = totalErrors, ans = totalErrors;
        while (low <= high) {
            long mid = (low + high) / 2;
            if (canFix(errors, (int) mid, k)) {
                ans = mid;      // mid works, try smaller
                high = mid - 1;
            } else {
                low = mid + 1;  // mid not enough, try bigger
            }
        }

        // Step 4: Print result
        System.out.println(ans);

        sc.close();
    }
}