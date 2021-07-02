/*
Find K Closest Elements
Given a sorted integer array arr, two integers k and x, return the k closest integers to x in the array. The result should also be sorted in ascending order.

An integer a is closer to x than an integer b if:

|a - x| < |b - x|, or
|a - x| == |b - x| and a < b
 

Example 1:

Input: arr = [1,2,3,4,5], k = 4, x = 3
Output: [1,2,3,4]
Example 2:

Input: arr = [1,2,3,4,5], k = 4, x = -1
Output: [1,2,3,4]
 

Constraints:

1 <= k <= arr.length
1 <= arr.length <= 104
arr is sorted in ascending order.
-104 <= arr[i], x <= 104
*/
class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
            PriorityQueue <Pair<Integer,Integer>> maxheap = new PriorityQueue<>((a,b) ->{
            if(b.getKey() - a.getKey() == 0)
                return b.getValue() - a.getValue();
            return 
                b.getKey() - a.getKey();
        });
        for(int i = 0;i < arr.length;i++){
            maxheap.add(new Pair(Math.abs(arr[i] - x),arr[i]));
            if(maxheap.size() > k)
                maxheap.poll();
        }
        List <Integer> l = new ArrayList<>();
        while(!maxheap.isEmpty())
            l.add(maxheap.poll().getValue());
        Collections.sort(l);
        return l;
        
    }
}