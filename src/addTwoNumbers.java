import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class addTwoNumbers {
    /*

   You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit.
   Add the two numbers and return the sum as a linked list.
   You may assume the two numbers do not contain any leading zero, except the number 0 itself.

    Example 1:

    Input: l1 = [2,4,3], l2 = [5,6,4]
    Output: [7,0,8]
    Explanation: 342 + 465 = 807.

    Example 2:

    Input: l1 = [0], l2 = [0]
    Output: [0]

    Example 3:

    Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
    Output: [8,9,9,9,0,0,0,1]


    Constraints:

    The number of nodes in each linked list is in the range [1, 100].
    0 <= Node.val <= 9
    It is guaranteed that the list represents a number that does not have leading zeros.
    */


    public static void main(String[] args) {
        int[] l1 = {2,4,3};
        int[] l2 = {5,6,4};
//        Output: [7,0,8]
//        Explanation: 342 + 465 = 807.

        ListNode firstList = createLinkedList(l1);
        printLinkedList(firstList);
        ListNode secondList = createLinkedList(l2);
        printLinkedList(secondList);
        ListNode sum = addTwoNumbers(firstList, secondList);
        printLinkedList(sum);
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode placeholder = new ListNode(0);
        ListNode current = placeholder;

        int carry = 0;
        int sum;
        int value;

        while (l1 != null || l2 != null || carry != 0){
            int val1 = (l1 != null) ? l1.val : 0;
            int val2 = (l2 != null) ? l2.val : 0;

            sum = val1 + val2 + carry;

            // Number that is to be carried over to the next set of numbers to be added eg. 17 is a carry of 1
            carry = sum / 10;

            // Modulo 10 so that it gets the last digit as the value to be stored in the list
            value = sum % 10;

            // Adds to list
            current.next = new ListNode(value);

            // Update current node
            current = current.next;

            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        return placeholder.next;
    }

    public static ListNode createLinkedList(int[] nums){
        // Defines the head without nulling
        ListNode placeholder = new ListNode(0);

        ListNode current = placeholder;

        // Links the listnodes together
        for(int num : nums){
            current.next = new ListNode(num);
            current = current.next;
        }
        return placeholder.next;
    }

    public static void printLinkedList(ListNode node){
        ArrayList<Integer> list = new ArrayList<>();
        while (node != null){
            list.add(node.val);
            node = node.next;
        }
        System.out.println(list);
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

}
