import com.sun.security.jgss.GSSUtil;

import java.util.HashSet;

public class lengthOfLongestSubstring {
    /*
    Given a string s, find the length of the longest substring without duplicate characters.

    Example 1:

    Input: s = "abcabcbb"
    Output: 3
    Explanation: The answer is "abc", with the length of 3.
    Example 2:

    Input: s = "bbbbb"
    Output: 1
    Explanation: The answer is "b", with the length of 1.
    Example 3:

    Input: s = "pwwkew"
    Output: 3
    Explanation: The answer is "wke", with the length of 3.
    Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.


    Constraints:

    0 <= s.length <= 5 * 104
    s consists of English letters, digits, symbols and spaces.
     */
    public static void main(String[] args) {
        lengthOfLongestSubstringSlidingWindow("pwwkew");
    }

    // Crappy brute force method
    public static int lengthOfLongestSubstringBruteForce(String s) {
        int highestCount = 0;
        int counter = 0;

        HashSet<Character> storedChars = new HashSet<>();

        for(int i = 0; i < s.toCharArray().length; i++){
            if(storedChars.contains(s.charAt(i))){
                i = i - counter;
                counter = 0;
                storedChars.clear();
            } else {
                counter++;
                storedChars.add(s.charAt(i));
                highestCount = Math.max(highestCount, counter);
            }
        }
        System.out.println(highestCount);
        return highestCount;
    }

    // Much better time complexity, uses the sliding window technique

    /* Output example
    [p]
    [p, w]
    [w]
    []
    [w]
    [w, k]
    [e, w, k]
    [e, k]
    [e, w, k]
     */

    public static int lengthOfLongestSubstringSlidingWindow(String s) {
        int highestCount = 0;
        int leftWindowIndex = 0;
        int rightWindowIndex = 0;

        HashSet<Character> storedChars = new HashSet<>();

        while (rightWindowIndex < s.length()) {
            if (storedChars.contains(s.charAt(rightWindowIndex))) {
                storedChars.remove(s.charAt(leftWindowIndex));

                // Moves left index
                leftWindowIndex++;
            } else {
                storedChars.add(s.charAt(rightWindowIndex));

                // Moves right index
                rightWindowIndex++;

                highestCount = Math.max(highestCount, rightWindowIndex - leftWindowIndex);
            }
            System.out.println(storedChars);
    }
        System.out.println(highestCount);
        return highestCount;
    }

    // ASCII only means we can improve speed by using arrays and not recalling s.charAt
    public static int lengthOfLongestSubstringSlidingWindowOptimised(String s) {
        boolean[] seen = new boolean[128];
        int leftWindowIndex = 0, rightWindowIndex = 0, maxLen = 0;

        while (rightWindowIndex < s.length()) {
            char c = s.charAt(rightWindowIndex);
            if (seen[c]) {
                seen[s.charAt(leftWindowIndex)] = false;
                leftWindowIndex++;
            } else {
                seen[c] = true;
                maxLen = Math.max(maxLen, rightWindowIndex - leftWindowIndex + 1);
                rightWindowIndex++;
            }
        }

        return maxLen;
    }
}
