/*
Beautiful Array
For some fixed n, an array nums is beautiful if it is a permutation of the integers 1, 2, ..., n, such that:

For every i < j, there is no k with i < k < j such that nums[k] * 2 = nums[i] + nums[j].

Given n, return any beautiful array nums.  (It is guaranteed that one exists.)

 

Example 1:

Input: n = 4
Output: [2,1,4,3]
Example 2:

Input: n = 5
Output: [3,1,2,5,4]
 

Note:

1 <= n <= 1000
*/
class Solution {
    public int[] beautifulArray(int n) {        
        //recursively 
        int result[] = new int[n];
        if(n == 1) return new int[]{1}; // odd arr end condition
        if(n == 2) return new int[]{1, 2}; // even arr end condition
        else{
            // divide and conquer
            int odd[] = beautifulArray((n + 1) / 2);
            int even[] =  beautifulArray(n / 2);
            for(int i = 0; i < odd.length; i++) result[i] = 2 * odd[i] - 1;
            for(int i = 0; i < even.length; i++) result[odd.length + i] = 2 * even[i];
        }
        return result;
    }
}