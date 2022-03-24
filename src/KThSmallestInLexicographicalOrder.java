import java.util.Arrays;

/**
 * 440. 字典序的第K小数字
 * 给定整数 n 和 k，返回  [1, n] 中字典序第 k 小的数字。
 */
public class KThSmallestInLexicographicalOrder {
    public int findKthNumber(int n, int k) {
        int cur = 1;
        k--;
        while (k > 0) {
            long first = cur, left = cur + 1;
            int size = 0;
            while (first <= n) {
                size += Math.min(left, n + 1) - first;
                first *= 10;
                left *= 10;
            }
            if (k >= size) {
                //下一个树
                cur++;
                k -= size;
            } else {
                //当前树
                //减掉当前根
                k--;
                cur *= 10;
            }
        }
        return cur;
    }

    //直接计算9颗树的大小, 递归计算10叉数
    //执行用时：
    //0 ms, 100.00%
    //内存消耗：
    //38.4 MB, 15.36%
    //的用户
    public int findKthNumber2(int n, int k) {
        int[] one2nine = new int[9];
        int temp = n, level = 0;
        while (temp >= 10) {
            temp = temp / 10;
            level++;
        }
        int levelTotal = (int) Math.pow(10, level);
        Arrays.fill(one2nine, (levelTotal - 1) / 9);
        int let = n - levelTotal;
        for (int i = 0; i < 9; i++) {
            if (let >= levelTotal) {
                one2nine[i] += levelTotal;
                let -= levelTotal;
            } else {
                one2nine[i] += let + 1;
                break;
            }
        }
        for (int i = 0; i < 9; i++) {
            if (k > one2nine[i]) {
                k -= one2nine[i];
            } else {
                return findKthNumber(i + 1,one2nine[i], k, level);
            }
        }
        return 0;
    }

    private int findKthNumber(int start, int n, int k, int level) {
        if (k == 1) {
            return start;
        }
        k--;
        int levelTotal = 0;
        for (int i = 0; i < level; i++) {
            levelTotal = levelTotal * 10 + 1;
        }
        int[] children = new int[10];
        Arrays.fill(children, (levelTotal - 1) / 10);
        if (n > levelTotal) {
            int count = (int) Math.pow(10, level - 1);
            int let = n - levelTotal;
            for (int i = 0; i < 10; i++) {
                if (let > count) {
                    children[i] += count;
                    let -= count;
                } else {
                    children[i] += let;
                    break;
                }
            }
        }
        for (int i = 0; i < 10; i++) {
            if (k > children[i]) {
                k -= children[i];
            } else {
                return findKthNumber(start * 10 + i,children[i], k, level - 1);
            }
        }
        return 0;
    }


    public static void main(String[] args) {
        KThSmallestInLexicographicalOrder smallest = new KThSmallestInLexicographicalOrder();
        System.out.println(smallest.findKthNumber(1, 1));
        System.out.println(smallest.findKthNumber(9, 2));
        System.out.println(smallest.findKthNumber(13, 2));
        System.out.println(smallest.findKthNumber(13, 5));
        System.out.println(smallest.findKthNumber(999, 899));
    }
}
