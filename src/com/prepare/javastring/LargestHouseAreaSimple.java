package com.prepare.javastring;

public class LargestHouseAreaSimple {
    public static int largestHouseArea(int[][] grid, int p, int q) {
        int rows = grid.length, cols = grid[0].length;
        int[] height = new int[cols];
        int maxArea = 0;

        for (int r = 0; r < rows; r++) {
            // Step 1: update histogram heights
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 1) height[c] += 1;
                else height[c] = 0;
            }

            // Step 2: largest rectangle in histogram
            maxArea = Math.max(maxArea, largestRectangle(height, p, q));
        }
        return maxArea;
    }

    // Helper: largest rectangle in histogram with min size p x q
    private static int largestRectangle(int[] h, int p, int q) {
        int n = h.length;
        java.util.Stack<Integer> st = new java.util.Stack<>();
        int max = 0;

        for (int i = 0; i <= n; i++) {
            int curr = (i == n ? 0 : h[i]);
            while (!st.isEmpty() && curr < h[st.peek()]) {
                int height = h[st.pop()];
                int left = st.isEmpty() ? 0 : st.peek() + 1;
                int width = i - left;
                if (height >= p && width >= q) {
                    max = Math.max(max, height * width);
                }
            }
            st.push(i);
        }
        return max;
    }

    // Quick test
    public static void main(String[] args) {
        int[][] grid = {
            {1,1,1,0},
            {1,1,1,1},
            {1,1,1,1}
        };
        int p = 2, q = 2;
        System.out.println(largestHouseArea(grid, p, q)); // Output: 8
    }
}