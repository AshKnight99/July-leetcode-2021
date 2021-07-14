/*
Custom Sort String
order and str are strings composed of lowercase letters. In order, no letter occurs more than once.

order was sorted in some custom order previously. We want to permute the characters of str so that they match the order that order was sorted. More specifically, if x occurs before y in order, then x should occur before y in the returned string.

Return any permutation of str (as a string) that satisfies this property.

Example:
Input: 
order = "cba"
str = "abcd"
Output: "cbad"
Explanation: 
"a", "b", "c" appear in order, so the order of "a", "b", "c" should be "c", "b", and "a". 
Since "d" does not appear in order, it can be at any position in the returned string. "dcba", "cdba", "cbda" are also valid outputs.
 

Note:

order has length at most 26, and no character is repeated in order.
str has length at most 200.
order and str consist of lowercase letters only.
*/
class Solution {
    public String customSortString(String order, String str) {
        int arr[] = new int[26];
        int orderLen = order.length();
        int strLen = str.length();
        
        //str can have repetition
        for(char ch : str.toCharArray())
            arr[ch - 'a'] += 1;
        String output = ""; // to build the string
        
        for(char ch : order.toCharArray()){
            if(arr[ch - 'a'] != 0){
            while(arr[ch - 'a'] != 0){
            output += ch;
            arr[ch - 'a'] -= 1;
            }
            }
        }
        
        for(int i = 0; i < 26; i++){
            if(arr[i] != 0){
                while(arr[i] != 0){
                    output += (char) (i + 'a');
                    arr[i] -=1;
                }
            }
        }
       return output; 
        
    }
}