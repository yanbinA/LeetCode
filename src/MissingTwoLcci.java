import java.util.Arrays;

/**
 * 面试题 17.19. 消失的两个数字
 * 给定一个数组，包含从 1 到 N 所有的整数，但其中缺了两个数字。你能在 O(N) 时间内只用 O(1) 的空间找到它们吗？
 *
 * 以任意顺序返回这两个数字均可。
 */
public class MissingTwoLcci {
    public int[] missingTwo(int[] nums) {
        int n = nums.length;
        int[] two = new int[2];
        for (int i = 0; i < n; i++) {
            while (nums[i] > 0 && nums[i] != i + 1) {
                if (nums[i] > n) {
                    two[nums[i] - n - 1] = nums[i];
                    nums[i] = 0;
                    break;
                }
                int tem = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = tem;
            }
        }
        int idx = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                if (two[idx] == 0) {
                    two[idx++] = n + 1;
                    two[idx++] = i + 1;
                    break;
                } else {
                    two[idx++] = i + 1;
                }
            }
        }
        for (; idx < two.length; idx++) {
            two[idx] = n + idx + 1;
        }
        return two;
    }

    //8 7 6 3 2 1 0 0
    //-2 7 6 3 2 1 -1 8
    //-2 -1 6 3 2 1 7 8
    //-2 -1 1 3 2 6 7 8
    //1 -1 -2 3 2 6 7 8
    //1 2 3 -2 -1 6 7 8

    public static void main(String[] args) {
        MissingTwoLcci lcci = new MissingTwoLcci();
        System.out.println(Arrays.toString(lcci.missingTwo(new int[]{1, 2, 3, 5, 6, 7, 8, 10})));
    }

    public boolean CheckPermutation(String s1, String s2) {
        int n = s1.length();
        if (n != s2.length()) {
            return false;
        }
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        Arrays.sort(chars1);
        Arrays.sort(chars2);
        return Arrays.equals(chars1, chars2);
    }
}
