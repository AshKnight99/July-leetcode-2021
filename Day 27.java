/*
3Sum Closest
Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest to target. Return the sum of the three integers. You may assume that each input would have exactly one solution.

 

Example 1:

Input: nums = [-1,2,1,-4], target = 1
Output: 2
Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 

Constraints:

3 <= nums.length <= 10^3
-10^3 <= nums[i] <= 10^3
-10^4 <= target <= 10^4
*/
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int len = nums.length;
        int temp = 0, ans = 0;
        int diff = Integer.MAX_VALUE;
        int left = 0, right = len - 1;
        for(int i = 0; i < len - 2; i++){
            left = i + 1;
            right = len - 1;
            while(left < right){
                temp = nums[i] + nums[left] + nums[right];
                if(temp == target) return target;
                if(diff > Math.abs(target - temp)){
                diff =  Math.abs(target - temp);
                ans = temp;
                }
                if(target > temp) left++;
                else right--;
            }
        }
        return ans;
    }
}