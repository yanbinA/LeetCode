/**
 * <3>最小操作次数使数组元素相等</3>
 * 给你一个长度为 n 的整数数组，每次操作将会使 n - 1 个元素增加 1 。返回让数组所有元素相等的最小操作次数。
 */
public class MinimumMovesEqualArrayElements {

    /**
     *执行用时：
     * 2 ms
     * 内存消耗：
     * 39.1 MB
     */
    public int minMoves1(int[] nums) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            min = Math.min(min, nums[i]);
        }
        int moves = 0;
        for (int i = 0; i < nums.length; i++) {
            moves += nums[i] - min;
        }
        return moves;
    }

    public int minMoves(int[] nums) {
        int min = nums[0];
        int moves = 0;
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            if (num > min) {
                moves += num - min;
            }
            if (min > num) {
                moves += (min - num) * i;
                min = num;
            }
        }
        return moves;
    }
    //[83,86,77,15,93,35,86,92,49,21]

}
