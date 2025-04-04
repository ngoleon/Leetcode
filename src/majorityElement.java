import java.util.HashMap;

public class majorityElement {
    /*
    Given an array nums of size n, return the majority element.

    The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.

    Example 1:

    Input: nums = [3,2,3]
    Output: 3

    Example 2:

    Input: nums = [2,2,1,1,1,2,2]
    Output: 2


    Constraints:

    n == nums.length
    1 <= n <= 5 * 104
    -109 <= nums[i] <= 109


    Follow-up: Could you solve the problem in linear time and in O(1) space?
     */
    public static void main(String[] args) {
        //    Input: nums = [2,2,1,1,1,2,2]
        //    Output: 2

        int[] nums = {2,2,1,1,1,2,2};
        System.out.println(majorityElement(nums));
    }

    // My solution
    public static int majorityElement(int[] nums) {
        HashMap<Integer, Integer> countingNumbers = new HashMap<>();
        int majorityCount = nums.length / 2;
        for(int num: nums){
            if(!countingNumbers.containsKey(num)) {
                countingNumbers.put(num, 1);
            } else {
                countingNumbers.put(num, countingNumbers.get(num) + 1);
            }

            // Check if current number is the majority
            if (countingNumbers.get(num) > majorityCount) {
                return num;
            }
        }
        return -1;
    }

    // Improved syntax
    public static int majorityElementImproved(int[] nums) {
        HashMap<Integer, Integer> countingNumbers = new HashMap<>();
        int majorityCount = nums.length / 2;
        for(int num: nums){
            countingNumbers.put(num, countingNumbers.getOrDefault(num, 0) + 1);

            // Check if current number is the majority
            if (countingNumbers.get(num) > majorityCount) {
                return num;
            }
        }
        return -1;
    }

    //Boyer-Moore Voting Algorithm, O(n) time, O(1) space
    public static int majorityElementBooyerMoore(int[] nums) {
        int count = 0;
        int candidate = 0;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }

        return candidate;
    }

}
