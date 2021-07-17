/*
Three Equal Parts
You are given an array arr which consists of only zeros and ones, divide the array into three non-empty parts such that all of these parts represent the same binary value.

If it is possible, return any [i, j] with i + 1 < j, such that:

arr[0], arr[1], ..., arr[i] is the first part,
arr[i + 1], arr[i + 2], ..., arr[j - 1] is the second part, and
arr[j], arr[j + 1], ..., arr[arr.length - 1] is the third part.
All three parts have equal binary values.
If it is not possible, return [-1, -1].

Note that the entire part is used when considering what binary value it represents. For example, [1,1,0] represents 6 in decimal, not 3. Also, leading zeros are allowed, so [0,1,1] and [1,1] represent the same value.

 

Example 1:

Input: arr = [1,0,1,0,1]
Output: [0,3]
Example 2:

Input: arr = [1,1,0,1,1]
Output: [-1,-1]
Example 3:

Input: arr = [1,1,0,0,1]
Output: [0,2]
 

Constraints:

3 <= arr.length <= 3 * 104
arr[i] is 0 or 1
*/
class Solution {
    public int[] threeEqualParts(int[] arr) {
        int n = arr.length;
        int result[] = new int[]{-1, -1};
        int countOf1 = 0;
        
        for(int i : arr)
            if(i == 1) countOf1++;
        
        if(countOf1 == 0) return new int[] {0, 2}; //only zeroes in array
        if(countOf1 % 3 != 0) return result;
        
        int numOfOnesInEachPart = countOf1 / 3;
        int indexOfFirstOnePart0 = -1, indexOfFirstOnePart1 = -1, indexOfFirstOnePart2 = -1 ;
        countOf1 = 0;
        
        //to get starting index of 1 of each part 
        for(int i = 0; i < n; i++){
            if(arr[i] == 1){
                countOf1++;
                if(numOfOnesInEachPart + 1 == countOf1) indexOfFirstOnePart1 = i;
                else if((numOfOnesInEachPart * 2) + 1 == countOf1)indexOfFirstOnePart2 = i;
                else if(countOf1 == 1)  indexOfFirstOnePart0 = i;
            }
        }
        
        // finding cutting index
        while(indexOfFirstOnePart2 < n){
            if(arr[indexOfFirstOnePart0] == arr[indexOfFirstOnePart2]  && arr[indexOfFirstOnePart1] == arr[indexOfFirstOnePart2]){ 
                indexOfFirstOnePart0++;
                indexOfFirstOnePart1++;                
                indexOfFirstOnePart2++;
       }else return result; 
        }
        
        return new int[]{indexOfFirstOnePart0 - 1, indexOfFirstOnePart1};
    }
}