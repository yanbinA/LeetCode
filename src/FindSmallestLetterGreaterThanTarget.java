/**
 * <p>
 * 744. 寻找比目标字母大的最小字母
 * 给你一个排序后的字符列表 letters ，列表中只包含小写英文字母。另给出一个目标字母 target，请你寻找在这一有序列表里比目标字母大的最小字母。
 *
 * 在比较时，字母是依序循环出现的。举个例子：
 *
 * 如果目标字母 target = 'z' 并且字符列表为 letters = ['a', 'b']，则答案返回 'a'
 * </p>
 *
 * @author messi
 * @package PACKAGE_NAME
 * @description find-smallest-letter-greater-than-target
 * @date 2022-04-03 16:53
 * @verison V1.0.0
 */
public class FindSmallestLetterGreaterThanTarget {
    public char nextGreatestLetter(char[] letters, char target) {
        int left  = 0, right = letters.length - 1, mid = 0;
        while (left <= right) {
            mid = (right - left) / 2 + left;
            if (letters[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return letters[(left) % letters.length];
    }

}
