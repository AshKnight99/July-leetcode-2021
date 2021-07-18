/*
Reverse Nodes in k-Group


Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.

You may not alter the values in the list's nodes, only nodes themselves may be changed.

 

Example 1:


Input: head = [1,2,3,4,5], k = 2
Output: [2,1,4,3,5]
Example 2:


Input: head = [1,2,3,4,5], k = 3
Output: [3,2,1,4,5]
Example 3:

Input: head = [1,2,3,4,5], k = 1
Output: [1,2,3,4,5]
Example 4:

Input: head = [1], k = 1
Output: [1]
 

Constraints:

The number of nodes in the list is in the range sz.
1 <= sz <= 5000
0 <= Node.val <= 1000
1 <= k <= sz
 

Follow-up: Can you solve the problem in O(1) extra memory space?
*/
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || k == 1) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        int count = 0;
        ListNode curr = dummy, prev = dummy, next = dummy;
        while(curr.next != null){
            curr = curr.next;
            count++;
        }
        
        while(count >= k){
            curr = prev.next;
            next = curr.next;
            
            //reverse logic            
            for(int i = 1; i < k; i++){                
                curr.next = next.next; 
                next.next = prev.next;
                prev.next = next;
                next = curr.next;                
            }
           prev = curr;
           // System.out.print("\t" + prev.val); 
        
           count -= k; 
            
        }
        return dummy.next;
    }
}