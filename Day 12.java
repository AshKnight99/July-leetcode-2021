/*
Isomorphic Strings
Given two strings s and t, determine if they are isomorphic.

Two strings s and t are isomorphic if the characters in s can be replaced to get t.

All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character, but a character may map to itself.

 

Example 1:

Input: s = "egg", t = "add"
Output: true
Example 2:

Input: s = "foo", t = "bar"
Output: false
Example 3:

Input: s = "paper", t = "title"
Output: true
 

Constraints:

1 <= s.length <= 5 * 104
t.length == s.length
s and t consist of any valid ascii character.
*/
class Solution {
    public boolean isIsomorphic(String s, String t) {
        char sarr[] = new char[256];
        char tarr[] = new char[256];
        int len = s.length();
        for(int i = 0; i < len; i++){
            char sch = s.charAt(i);
            char tch = t.charAt(i);
            if(sarr[sch] == 0 && tarr[tch] == 0){
              sarr[sch] = tch;
              tarr[tch] = sch;                
            }else if(sarr[sch] != tch) return false;
        }
        return true;
    }
}