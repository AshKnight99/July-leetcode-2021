/* Kth Smallest Element in a Sorted Matrix

Solution
Given an n x n matrix where each of the rows and columns are sorted in ascending order, return the kth smallest element in the matrix.

Note that it is the kth smallest element in the sorted order, not the kth distinct element.

 

Example 1:

Input: matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8
Output: 13
Explanation: The elements in the matrix are [1,5,9,10,11,12,13,13,15], and the 8th smallest number is 13
Example 2:

Input: matrix = [[-5]], k = 1
Output: -5
 

Constraints:

n == matrix.length
n == matrix[i].length
1 <= n <= 300
-109 <= matrix[i][j] <= 109
All the rows and columns of matrix are guaranteed to be sorted in non-decreasing order.
1 <= k <= n2
*/
class Solution {
    public int kthSmallest(int[][] matrix, int k) {
         int n = matrix.length;       
        int left = matrix[0][0], right = matrix[n - 1][n - 1];
        
        while(left < right){
            int mid = left + (right - left) /  2;
            int count = 0, maxNum = left;
            for(int r = 0, c = n - 1; r < n; r++){
                while(c >= 0 && matrix[r][c] > mid) c--;
                if(c >= 0){
                    count += (c + 1);
                    maxNum = Math.max(maxNum, matrix[r][c]);
                }
            }
            
            if(count == k) return maxNum;
            if(count > k) right = mid;
            else left = mid + 1;
        }
        return left;
    }
}
/*
Ologn solution:
class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        List <Integer> list = new ArrayList <>();
        for (int r[] : matrix){
            for(int c : r){            
                list.add(c);               
            }
        }
       Collections.sort(list);
           // System.out.print(list.get(k - 1));
        return list.get(k - 1);    
        
    }
}