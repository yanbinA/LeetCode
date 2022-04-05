/**
 * <h3>798. 得分最高的最小轮调</h3>
 * 给你一个数组 nums，我们可以将它按一个非负整数 k 进行轮调，这样可以使数组变为 [nums[k], nums[k + 1], ... nums[nums.length - 1], nums[0], nums[1], ..., nums[k-1]] 的形式。此后，任何值小于或等于其索引的项都可以记作一分。
 *
 * 例如，数组为 nums = [2,4,1,3,0]，我们按 k = 2 进行轮调后，它将变成 [1,3,0,2,4]。这将记为 3 分，因为 1 > 0 [不计分]、3 > 1 [不计分]、0 <= 2 [计 1 分]、2 <= 3 [计 1 分]，4 <= 4 [计 1 分]。
 * 在所有可能的轮调中，返回我们所能得到的最高分数对应的轮调下标 k 。如果有多个答案，返回满足条件的最小的下标 k 。
 */
public class SmallestRotationWithHighestScore {
    public int bestRotation(int[] nums) {
        int length = nums.length;
        int[] scores = new int[length];
        for (int i = 0; i < length; i++) {
            int num = nums[i];
            if (num <= i) {
                for (int k = 0; k <= i - num; k++) {
                    scores[k]++;
                }
                num = i + 1;
            }
            for (int k = i + 1; k <= length - num + i; k++) {
                scores[k]++;
            }
        }

        int bestRotation = 0, maxScore = 0;
        for (int i = 0; i < scores.length; i++) {
            if (scores[i] > maxScore) {
                maxScore = scores[i];
                bestRotation = i;
            }
        }
        return bestRotation;
    }

    public int bestRotation1(int[] nums) {
        int bestRotation = 0, maxScore = 0;
        int length = nums.length;
        for (int k = 0; k < length; k++) {
            int score = 0;
            for (int i = 0; i < length; i++) {
                if (nums[i] <= Math.floorMod(i - k, length)) {
                    score++;
                }
            }
            if (score > maxScore) {
                maxScore = score;
                bestRotation = k;
            }
        }
        return bestRotation;
    }

    public static void main(String[] args) {
        int[] ints = {1,3,0,2,4};
        SmallestRotationWithHighestScore rotation = new SmallestRotationWithHighestScore();
        System.out.println(rotation.bestRotation(ints));
    }
}
