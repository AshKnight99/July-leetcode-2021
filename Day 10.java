/*
Decode Ways II
A message containing letters from A-Z can be encoded into numbers using the following mapping:

'A' -> "1"
'B' -> "2"
...
'Z' -> "26"
To decode an encoded message, all the digits must be grouped then mapped back into letters using the reverse of the mapping above (there may be multiple ways). For example, "11106" can be mapped into:

"AAJF" with the grouping (1 1 10 6)
"KJF" with the grouping (11 10 6)
Note that the grouping (1 11 06) is invalid because "06" cannot be mapped into 'F' since "6" is different from "06".

In addition to the mapping above, an encoded message may contain the '*' character, which can represent any digit from '1' to '9' ('0' is excluded). For example, the encoded message "1*" may represent any of the encoded messages "11", "12", "13", "14", "15", "16", "17", "18", or "19". Decoding "1*" is equivalent to decoding any of the encoded messages it can represent.

Given a string s containing digits and the '*' character, return the number of ways to decode it.

Since the answer may be very large, return it modulo 109 + 7.

 

Example 1:

Input: s = "*"
Output: 9
Explanation: The encoded message can represent any of the encoded messages "1", "2", "3", "4", "5", "6", "7", "8", or "9".
Each of these can be decoded to the strings "A", "B", "C", "D", "E", "F", "G", "H", and "I" respectively.
Hence, there are a total of 9 ways to decode "*".
Example 2:

Input: s = "1*"
Output: 18
Explanation: The encoded message can represent any of the encoded messages "11", "12", "13", "14", "15", "16", "17", "18", or "19".
Each of these encoded messages have 2 ways to be decoded (e.g. "11" can be decoded to "AA" or "K").
Hence, there are a total of 9 * 2 = 18 ways to decode "1*".
Example 3:

Input: s = "2*"
Output: 15
Explanation: The encoded message can represent any of the encoded messages "21", "22", "23", "24", "25", "26", "27", "28", or "29".
"21", "22", "23", "24", "25", and "26" have 2 ways of being decoded, but "27", "28", and "29" only have 1 way.
Hence, there are a total of (6 * 2) + (3 * 1) = 12 + 3 = 15 ways to decode "2*".
 

Constraints:

1 <= s.length <= 105
s[i] is a digit or '*'.
*/
class Solution {
     final int mod = 1000000007;
    public int numDecodings(String s) {
       
//         int len = s.length();
        
//         if(len == 0 || s == null)
//             return 0;
        
//         long dp[] = new long[len + 1];
//         char arr[] = s.toCharArray();
//         dp[0] = 0;
//         dp[1] = arr[0] == '*' ? 9 : (arr[0] == '0') ? 0 : 1;
        
//         for(int i = 2; i <= len; i++){
//             if(arr[i - 1] == '*'){
//                 dp[i] = 9 * dp[i - 1];          // 9 possibilities
                
//                 dp[i] = (arr[i - 2] == '1') ? ((dp[i] + 9 * dp[i - 2]) % MOD) : (arr[i - 2] == '2') ? ((dp[i] + 6 * dp[i - 2]) % MOD) : ((dp[i] + 15 * dp[i - 2]) % MOD);
                
//             }else{
//              dp[i] = arr[i - 1] != '0' ? dp[i - 1] : 0;
//              dp[i] = (arr[i - 2] == '1') ? ((dp[i] + dp[i - 2]) % MOD) : (arr[i - 2] == '2' && arr[i - 1] >= '6') ? ((dp[i] + dp[i - 2]) % MOD) : ((dp[i] + (arr[i - 1] <= '6' ? 2 : 1) * dp[i - 2]) % MOD);
                
//             }
//         }
        
       long a = 1, b = 0;
	for (int idx = s.length() - 1; idx >= 0; idx--) {

		long count = 0;
		char ch1 = s.charAt(idx);
		if (ch1 == '0') {
			count = 0;
		} else if (ch1 == '*') {
			count = (count + 9 * a) % mod;
			if (idx < s.length() - 1) {
				char ch2 = s.charAt(idx + 1);
				if (ch2 == '*')
					count = (count + 15 * b) % mod;
				else if (ch2 >= '0' && ch2 <= '6')
					count = (count + 2 * b) % mod;
				else if (ch2 > '6')
					count = (count + b) % mod;

			}
		} else {
			count = (count + a) % mod;
			if (idx < s.length() - 1) {
				if (s.charAt(idx + 1) != '*') {
					char ch2 = s.charAt(idx + 1);
					int num = (ch1 - '0') * 10 + (ch2 - '0');
					if (num <= 26)
						count = (count + b) % mod;
				} else {
					if (s.charAt(idx) == '1')
						count = (count + 9 * b) % mod;
					else if (s.charAt(idx) == '2')
						count = (count + 6 * b) % mod;
				}
			}
		}

		b = a;
		a = count;
	}

	return (int) a;
    }
}