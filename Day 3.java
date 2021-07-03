/*
Max Sum of Rectangle No Larger Than K
Given an m x n matrix matrix and an integer k, return the max sum of a rectangle in the matrix such that its sum is no larger than k.

It is guaranteed that there will be a rectangle with a sum no larger than k.

 

Example 1:


Input: matrix = [[1,0,1],[0,-2,3]], k = 2
Output: 2
Explanation: Because the sum of the blue rectangle [[0, 1], [-2, 3]] is 2, and 2 is the max number no larger than k (k = 2).
Example 2:

Input: matrix = [[2,2,-1]], k = 3
Output: 3
 

Constraints:

m == matrix.length
n == matrix[i].length
1 <= m, n <= 100
-100 <= matrix[i][j] <= 100
-105 <= k <= 105
 

Follow up: What if the number of rows is much larger than the number of columns?
*/
class Solution {
    public int maxSumSubmatrix(int[][] arr, int k) {
        int rows = arr.length;
        int cols = arr[0].length;
        int maxK = Integer.MIN_VALUE;
        for(int i = 0; i < cols; i++){
            int dp[] = new int[rows];
            for(int j = i; j < cols; j++){
                for(int l = 0; l < rows; l++){
                    dp[l] += arr[l][j];
                }
                int currSum = maxSubArray(dp, k);
                maxK = Math.max(maxK, currSum);                 
                if(maxK == k)
                    return k;
            }
        }
        return maxK;
    }
    public int maxSubArray(int[] arr, int k) {
        int max = Integer.MIN_VALUE;
        int currSum = 0;
        TreeSet<Integer> set = new TreeSet<>();
        set.add(0);
        for (int i = 0; i < arr.length; i++) {
            currSum += arr[i];
            Integer ceilValue = set.ceiling(currSum - k);
            if(ceilValue != null) {
                max = Math.max(max, currSum - ceilValue);
            }
            set.add(currSum);
        }
        return max;
    }
}