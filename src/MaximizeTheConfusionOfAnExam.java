/**
 * 2024. 考试的最大困扰度
 * 一位老师正在出一场由 n 道判断题构成的考试，每道题的答案为 true （用 'T' 表示）或者 false （用 'F' 表示）。老师想增加学生对自己做出答案的不确定性，方法是 最大化 有 连续相同 结果的题数。（也就是连续出现 true 或者连续出现 false）。
 *
 * 给你一个字符串 answerKey ，其中 answerKey[i] 是第 i 个问题的正确结果。除此以外，还给你一个整数 k ，表示你能进行以下操作的最多次数：
 *
 * 每次操作中，将问题的正确答案改为 'T' 或者 'F' （也就是将 answerKey[i] 改为 'T' 或者 'F' ）。
 * 请你返回在不超过 k 次操作的情况下，最大 连续 'T' 或者 'F' 的数目。
 */
public class MaximizeTheConfusionOfAnExam {
    public int maxConsecutiveAnswers(String answerKey, int k) {
        int n = answerKey.length();
        int tc = 0, fc = 0, left = 0, right = 0, ans = 0;
        char[] chars = answerKey.toCharArray();
        for (; right < n; right++) {
            if (chars[right] == 'T') {
                tc++;
            } else {
                fc++;
            }
            while (tc > k && fc > k) {
                if (chars[left] == 'T') {
                    tc--;
                } else {
                    fc--;
                }
                left++;
            }
            ans = Math.max(fc + tc, ans);
        }
        return ans;
    }
    //定义C[n + 1],C[i]表示T/F出现的次数
    //滑动窗口计算最大窗口,窗口内T/F出现次数不大于k
    public int maxConsecutiveAnswers1(String answerKey, int k) {
        int n = answerKey.length();
        int[] TC = new int[n + 1], FC = new int[n + 1];
        int left = 0, right = 0, ans = 0;
        char[] chars = answerKey.toCharArray();
        for (int i = 0; i < n; i++) {
            if (chars[i] == 'T') {
                TC[i + 1] = TC[i] + 1;
                FC[i + 1] = FC[i];
            } else {
                TC[i + 1] = TC[i];
                FC[i + 1] = FC[i] + 1;
            }
        }
        while (right < n + 1) {
            right++;
            if (right >= n + 1) {
                break;
            }
            if (TC[right] - TC[left] <= k || FC[right] - FC[left] <= k) {
                ans = Math.max(right - left, ans);
            } else {
                while (TC[right] - TC[left] > k && FC[right] - FC[left] > k){
                    left++;
                }
            }
        }
        return ans;
    }
    //TTFTTFTT
    //0 1 2 2 3 4 4 5 6
    //0 0 0 1 1 1 2 2 2
    public static void main(String[] args) {
        MaximizeTheConfusionOfAnExam confusion = new MaximizeTheConfusionOfAnExam();
        System.out.println(confusion.maxConsecutiveAnswers("TTFTTFTT", 1));
        System.out.println(confusion.maxConsecutiveAnswers("TTFTTFTT", 0));
    }
}
