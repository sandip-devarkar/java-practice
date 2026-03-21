package com.interview;

import java.util.*;

public class TwoSumIndices {
    public static int[] findIndices(int[] arr, int target) {
        Map<Integer, Integer> map = new HashMap<>(); // value → index

        for (int i = 0; i < arr.length; i++) {
            int complement = target - arr[i];

            if (map.containsKey(complement)) {
                // Found the pair
                return new int[]{map.get(complement), i};
            }

            // Store current value with its index
            map.put(arr[i], i);
        }

        // If no pair found, return {-1, -1}
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        int[] arr = {2, 7, 11, 15};
        int target = 9;

        int[] result = findIndices(arr, target);
        System.out.println("Indices: [" + result[0] + ", " + result[1] + "]");
    }
}
