/*
Word Ladder II
A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:

Every adjacent pair of words differs by a single letter.
Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
sk == endWord
Given two words, beginWord and endWord, and a dictionary wordList, return all the shortest transformation sequences from beginWord to endWord, or an empty list if no such sequence exists. Each sequence should be returned as a list of the words [beginWord, s1, s2, ..., sk].

 

Example 1:

Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
Output: [["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
Explanation: There are 2 shortest transformation sequences:
"hit" -> "hot" -> "dot" -> "dog" -> "cog"
"hit" -> "hot" -> "lot" -> "log" -> "cog"
Example 2:

Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
Output: []
Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.
 

Constraints:

1 <= beginWord.length <= 5
endWord.length == beginWord.length
1 <= wordList.length <= 1000
wordList[i].length == beginWord.length
beginWord, endWord, and wordList[i] consist of lowercase English letters.
beginWord != endWord
All the words in wordList are unique.
*/
class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        Set<String> words = new HashSet<>(wordList);
        if (!words.contains(endWord)) {
            return res;
        }
        Map<String, List<String>> map = new HashMap<>();
        Set<String> startSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        startSet.add(beginWord);
        endSet.add(endWord);
        bfs(startSet, endSet, map, words, false);
        List<String> path = new ArrayList<>();
        path.add(beginWord);
        dfs(beginWord, endWord, map, path, res);
        return res;
    }
    
    private void dfs(String beginWord, String endWord, Map<String, List<String>> map, List<String> path, List<List<String>> res) {
        if (beginWord.equals(endWord)) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (String next : map.getOrDefault(beginWord, new ArrayList<>())) {
            path.add(next);
            dfs(next, endWord, map, path, res);
            path.remove(path.size() - 1);
        }
    }
    
    private void bfs(Set<String> startSet, Set<String> endSet,
                     Map<String, List<String>> map, Set<String> words, boolean reverse) {
        if (startSet.size() == 0) {
            return;
        }
        if (startSet.size() > endSet.size()) {
            bfs(endSet, startSet, map, words, !reverse);
            return;
        }
        Set<String> temp = new HashSet<>();
        words.removeAll(startSet);
        boolean found = false;
        for (String s : startSet) {
            char[] chs = s.toCharArray();
            for (int i = 0; i < chs.length; ++i) {
                char old = chs[i];
                for (char k = 'a'; k <= 'z'; ++k) {
                    if (k == old) {
                        continue;
                    }
                    chs[i] = k;
                    String newWord = new String(chs);
                    if (words.contains(newWord)) {
                        if (endSet.contains(newWord)) {
                            found = true;
                        } else {
                            temp.add(newWord);
                        }
                        String key = !reverse ? s : newWord;
                        String val = !reverse ? newWord : s;
                        map.putIfAbsent(key, new ArrayList<>());
                        map.get(key).add(val);
                    }
                }
                chs[i] = old;
            }
        }
        if (!found) {
            bfs(temp, endSet, map, words, reverse);
        }
    }
}