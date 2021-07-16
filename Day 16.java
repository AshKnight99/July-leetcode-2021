/*
4Sum
Given an array nums of n integers, return an array of all the unique quadruplets [nums[a], nums[b], nums[c], nums[d]] such that:

0 <= a, b, c, d < n
a, b, c, and d are distinct.
nums[a] + nums[b] + nums[c] + nums[d] == target
You may return the answer in any order.

 

Example 1:

Input: nums = [1,0,-1,0,-2,2], target = 0
Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
Example 2:

Input: nums = [2,2,2,2,2], target = 8
Output: [[2,2,2,2]]
 

Constraints:

1 <= nums.length <= 200
-109 <= nums[i] <= 109
-109 <= target <= 109
*/
class Solution {
   Set<List<Integer>> res;
    public List<List<Integer>> fourSum(int[] nums, int target) {
        int len = nums.length;
        res = new HashSet<>();
        if(len < 4) return new ArrayList<>(res);        
        Arrays.sort(nums);
        int left = 0, right = len - 1;
        for(int i = 0; i < len - 3; i++){
            if(nums[i] * 4 > target){break;}
            for(int j = i + 1; j < len - 2; j++){
                left = j + 1; right = len - 1;
                
                while(left < right){
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if(sum == target) {
                     res.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                     left++;
                     right--;
                    }
                    if(sum > target) right--;
                    if(sum < target) left++;
                    
                }
            }
        }
        
         return new ArrayList<>(res) ; 
    }
}