/**
 * 754. 到达终点数字
 * 在一根无限长的数轴上，你站在0的位置。终点在target的位置。
 *
 * 你可以做一些数量的移动 numMoves :
 *
 * 每次你可以选择向左或向右移动。
 * 第 i 次移动（从  i == 1 开始，到 i == numMoves ），在选择的方向上走 i 步。
 * 给定整数 target ，返回 到达目标所需的 最小 移动次数(即最小 numMoves ) 。
 *
 *  提示:
 *  -10^9 <= target <= 10^9
 *  target != 0
 */
public class ReachANumber {
    public int reachNumber(int target) {
        target = Math.abs(target);
        int n = (int) Math.sqrt(target * 2);
        int sum = n * (n + 1) / 2;
        while (sum < target || (target - sum) % 2 != 0) {
            n++;
            sum += n;
        }
        return n;
    }

    public static void main(String[] args) {
        ReachANumber reachANumber = new ReachANumber();
        System.out.println(reachANumber.reachNumber(2));
        System.out.println(reachANumber.reachNumber(3));
        System.out.println(reachANumber.reachNumber(27));
        System.out.println(reachANumber.reachNumber(100));
    }
}
