package decode_ways;

public class Solution {

    public int numDecodings(String s) {
        int length = s.length();
        int[] ways = new int[length + 1], signal = new int[length + 1], nums = new int[length + 1];
        ways[0] = 1;
        signal[0] = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int num = chars[i] - '0';
            nums[i + 1] = num;
            if ((nums[i] == 0 || nums[i] > 2) && num == 0) {
                return 0;
            }
            if (nums[i] == 0) {
                ways[i + 1] = ways[i];
                signal[i + 1] = ways[i];
                continue;
            }
            if (num == 0) {
                ways[i + 1] = ways[i - 1];
                signal[i + 1] = 0;
                continue;
            }
            if (nums[i] * 10 + num > 26) {
                ways[i + 1] = ways[i];
                signal[i + 1] = ways[i];
                continue;
            }
            ways[i + 1] = ways[i] + signal[i];
            signal[i + 1] = ways[i];
        }
        return ways[length];
    }

}
