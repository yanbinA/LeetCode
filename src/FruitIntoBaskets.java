/**
 * 904. 水果成篮
 * 你正在探访一家农场，农场从左到右种植了一排果树。这些树用一个整数数组 fruits 表示，其中 fruits[i] 是第 i 棵树上的水果 种类 。
 *
 * 你想要尽可能多地收集水果。然而，农场的主人设定了一些严格的规矩，你必须按照要求采摘水果：
 *
 * 你只有 两个 篮子，并且每个篮子只能装 单一类型 的水果。每个篮子能够装的水果总量没有限制。
 * 你可以选择任意一棵树开始采摘，你必须从 每棵 树（包括开始采摘的树）上 恰好摘一个水果 。采摘的水果应当符合篮子中的水果类型。每采摘一次，你将会向右移动到下一棵树，并继续采摘。
 * 一旦你走到某棵树前，但水果不符合篮子的水果类型，那么就必须停止采摘。
 * 给你一个整数数组 fruits ，返回你可以收集的水果的 最大 数目。
 */
public class FruitIntoBaskets {
    public int totalFruit(int[] fruits) {
        int ans = 0;
        int f1 = -1;
        int f2 = -1;
        int last = -1;
        int con = 0;
        int count = 0;
        for (int fruit : fruits) {
            if (fruit != f1 && fruit != f2) {
                f1 = last;
                ans = Math.max(count, ans);
                count = con;
                f2 = fruit;
                count++;
                last = f2;
                con = 1;
                continue;
            }
            if (fruit == f1) {
                if (last == f1) {
                    con++;
                } else {
                    con = 1;
                    last = f1;
                }
            }
            if (fruit == f2) {
                if (last == f2) {
                    con++;
                } else {
                    con = 1;
                    last = f2;
                }
            }
            count++;
        }
        return Math.max(ans, count);
    }

    public static void main(String[] args) {
        FruitIntoBaskets fruit = new FruitIntoBaskets();
        System.out.println(fruit.totalFruit(new int[]{1, 2, 1}));
        System.out.println(fruit.totalFruit(new int[]{0,1,2,2}));
        System.out.println(fruit.totalFruit(new int[]{1,2,3,2,2}));
        System.out.println(fruit.totalFruit(new int[]{3,3,3,1,2,1,1,2,3,3,4}));
    }
}
