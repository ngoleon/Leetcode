import java.util.Arrays;
import java.util.HashMap;

public class rotateArray {
    /*
    189. Rotate Array

    Given an integer array nums, rotate the array to the right by k steps, where k is non-negative.

    Example 1:

    Input: nums = [1,2,3,4,5,6,7], k = 3
    Output: [5,6,7,1,2,3,4]
    Explanation:
    rotate 1 steps to the right: [7,1,2,3,4,5,6]
    rotate 2 steps to the right: [6,7,1,2,3,4,5]
    rotate 3 steps to the right: [5,6,7,1,2,3,4]

    Example 2:

    Input: nums = [-1,-100,3,99], k = 2
    Output: [3,99,-1,-100]
    Explanation:
    rotate 1 steps to the right: [99,-1,-100,3]
    rotate 2 steps to the right: [3,99,-1,-100]

    Constraints:

    1 <= nums.length <= 105
    -231 <= nums[i] <= 231 - 1
    0 <= k <= 105


    Follow up:

    Try to come up with as many solutions as you can. There are at least three different ways to solve this problem.
    Could you do it in-place with O(1) extra space?
     */

    public static void main(String[] args) {
        //    Example 1:
        //
        //    Input: nums = [1,2,3,4,5,6,7], k = 3
        //    Output: [5,6,7,1,2,3,4]
        //    Explanation:
        //    rotate 1 steps to the right: [7,1,2,3,4,5,6]
        //    rotate 2 steps to the right: [6,7,1,2,3,4,5]
        //    rotate 3 steps to the right: [5,6,7,1,2,3,4]

        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        int k = 3;

//        rotate(nums, k);
//        rotateCyclic(nums, k);
        rotateReverse(nums, k);
    }

    public static void rotate(int[] nums, int k) {
        k = k % nums.length;
        HashMap<Integer, Integer> valueIndex = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int index = i + k;
            if(index >= nums.length) index = index - nums.length;
            valueIndex.put(index, nums[i]);
        }

        for (int i = 0; i < nums.length; i++) {
            nums[i] = valueIndex.get(i);
        }

        System.out.println(Arrays.toString(nums));
    }

    public static void rotateCyclic(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        int count = 0;

        System.out.println("Original: " + Arrays.toString(nums));
        for (int start = 0; count < n; start++) {
            int current = start;
            int prev = nums[start];
            System.out.println("\nStart new cycle from index " + start);

            do {
                int next = (current + k) % n;
                System.out.printf("Moving nums[%d] -> nums[%d] (%d -> %d)\n", current, next, prev, nums[next]);

                int temp = nums[next];
                nums[next] = prev;
                prev = temp;
                current = next;
                count++;

                System.out.println("Current array: " + Arrays.toString(nums));
            } while (start != current);
        }

        System.out.println("\nFinal rotated array (Cyclic): " + Arrays.toString(nums));
    }


    public static void rotateReverse(int[] nums, int k) {
        k = k % nums.length;

        System.out.println("Original: " + Arrays.toString(nums));
        reverse(nums, 0, nums.length - 1);
        System.out.println("After full reverse: " + Arrays.toString(nums));

        reverse(nums, 0, k - 1);
        System.out.println("After reversing first " + k + " elements: " + Arrays.toString(nums));

        reverse(nums, k, nums.length - 1);
        System.out.println("After reversing last " + (nums.length - k) + " elements: " + Arrays.toString(nums));

        System.out.println("\nFinal rotated array (Reverse): " + Arrays.toString(nums));
    }

    private static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start++] = nums[end];
            nums[end--] = temp;
        }
    }

}
