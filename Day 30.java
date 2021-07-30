/*
 Map Sum Pairs
Implement the MapSum class:

MapSum() Initializes the MapSum object.
void insert(String key, int val) Inserts the key-val pair into the map. If the key already existed, the original key-value pair will be overridden to the new one.
int sum(string prefix) Returns the sum of all the pairs' value whose key starts with the prefix.
 

Example 1:

Input
["MapSum", "insert", "sum", "insert", "sum"]
[[], ["apple", 3], ["ap"], ["app", 2], ["ap"]]
Output
[null, null, 3, null, 5]

Explanation
MapSum mapSum = new MapSum();
mapSum.insert("apple", 3);  
mapSum.sum("ap");           // return 3 (apple = 3)
mapSum.insert("app", 2);    
mapSum.sum("ap");           // return 5 (apple + app = 3 + 2 = 5)
 

Constraints:

1 <= key.length, prefix.length <= 50
key and prefix consist of only lowercase English letters.
1 <= val <= 1000
At most 50 calls will be made to insert and sum.
*/
// 1st approach - Naive Approach
class MapSum {
    
    /** Initialize your data structure here. */
    HashMap<String, Integer> map;
    public MapSum() {
        map = new HashMap<>();
    }
    
    public void insert(String key, int val) {
        map.put(key, val);
    }
    
    public int sum(String prefix) {
        int total = 0;
        for(Map.Entry<String, Integer> entry : map.entrySet()){
            if(entry.getKey().startsWith(prefix))total += entry.getValue();            
        }
        return total;
    }
}

/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum obj = new MapSum();
 * obj.insert(key,val);
 * int param_2 = obj.sum(prefix);
 */

Approach 2: Using Trie
class MapSum {
    
    /** Initialize your data structure here. */
    TrieNode root;
    public MapSum() {
        root = new TrieNode();
    }
    
    public void insert(String key, int val) {
       TrieNode curr = root;
        for(char ch : key.toCharArray()){
            if(curr.children[ch - 'a'] == null) curr.children[ch - 'a'] = new TrieNode();
            curr = curr.children[ch - 'a'];            
        }
        curr.val = val;
    }
    
    public int sum(String prefix) {
        TrieNode curr = root;
        for(char ch : prefix.toCharArray()){
            if(curr.children[ch - 'a'] == null) return 0;
            curr = curr.children[ch - 'a'];            
        }
        return dfsSum(curr);        
    }
    private int dfsSum(TrieNode curr){
        int sum = curr.val;
        for(TrieNode nextNode : curr.children){
            sum += (nextNode == null) ? 0 : dfsSum(nextNode);
        }
        return sum;
    }
    //Create Trie Object
    class TrieNode{
    TrieNode children[] ;
    int val;
        TrieNode(){
         this.children = new TrieNode[26];
         this.val = 0;
        }
            
    } 
}

/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum obj = new MapSum();
 * obj.insert(key,val);
 * int param_2 = obj.sum(prefix);
 */