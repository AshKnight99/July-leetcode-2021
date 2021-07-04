/*
Count Vowels Permutation
Given an integer n, your task is to count how many strings of length n can be formed under the following rules:

Each character is a lower case vowel ('a', 'e', 'i', 'o', 'u')
Each vowel 'a' may only be followed by an 'e'.
Each vowel 'e' may only be followed by an 'a' or an 'i'.
Each vowel 'i' may not be followed by another 'i'.
Each vowel 'o' may only be followed by an 'i' or a 'u'.
Each vowel 'u' may only be followed by an 'a'.
Since the answer may be too large, return it modulo 10^9 + 7.

 

Example 1:

Input: n = 1
Output: 5
Explanation: All possible strings are: "a", "e", "i" , "o" and "u".
Example 2:

Input: n = 2
Output: 10
Explanation: All possible strings are: "ae", "ea", "ei", "ia", "ie", "io", "iu", "oi", "ou" and "ua".
Example 3: 

Input: n = 5
Output: 68
 

Constraints:

1 <= n <= 2 * 10^4
*/
if (n == 1) return 5;

        long mod = 1000000007L;

        //aeiou corresponds to 01234
        long[] previous = new long[5];
        long[] current = new long[5];

        long sumPrevious = 4;
        for (int j = 0; j < 5; j++) previous[j] = 1;

        for (int i = 2; i <= n; i++)
        {
            //a
            current[0] = previous[1];
            //e
            current[1] = (previous[0] + previous[2]) % mod;
            //i
            current[2] = sumPrevious;
            //o
            current[3] = (previous[2] + previous[4]) % mod;
            //u
            current[4] = previous[0];

            sumPrevious = 0;
            for (int j = 0; j < 5; j++)
            {
                previous[j] = current[j];
                if (j != 2) sumPrevious += previous[j];
            }
            sumPrevious %= mod;
        }

        long result = (current[0] + current[1] + current[2] + current[3] + current[4]) % mod;

        return (int)result;