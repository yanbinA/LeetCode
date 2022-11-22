import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 808. 分汤
 * 有 A 和 B 两种类型 的汤。一开始每种类型的汤有 n 毫升。有四种分配操作：
 *
 * 提供 100ml 的 汤A 和 0ml 的 汤B 。
 * 提供 75ml 的 汤A 和 25ml 的 汤B 。
 * 提供 50ml 的 汤A 和 50ml 的 汤B 。
 * 提供 25ml 的 汤A 和 75ml 的 汤B 。
 * 当我们把汤分配给某人之后，汤就没有了。每个回合，我们将从四种概率同为 0.25 的操作中进行分配选择。如果汤的剩余量不足以完成某次操作，我们将尽可能分配。当两种类型的汤都分配完时，停止操作。
 *
 * 注意 不存在先分配 100 ml 汤B 的操作。
 *
 * 需要返回的值： 汤A 先分配完的概率 +  汤A和汤B 同时分配完的概率 / 2。返回值在正确答案 10-5 的范围内将被认为是正确的。
 */
public class SoupServings {
    //n=50
    //0.25*(1 + 1 + 0.5 + 0)=0.625
    //n=100
    //0.25*(1 + 0.25*(1+1+1+0.5) + 0.25*(1+1+0.5+0) + 0.25*(1+0.5+0+0))
    //0.25*(1+1.875)=0.71875
    Map<Integer, Double> map;
    public double soupServings(int n) {
        if (n > 20000) {
            return 1;
        }
        map = new HashMap<>();
        return dfs(n, n);
    }

    private double dfs(int a, int b) {
        if (a <= 0 && b > 0) {
            return 1;
        }
        if (a <= 0) {
            return 0.5;
        }
        if (b <= 0) {
            return 0;
        }
        int hash = Objects.hash(a * 17, b * 29);
//        hash = hash ^ (hash >>> 16);
        if (map.containsKey(hash)) {
            return map.get(hash);
        }
        double pro = 0;
        pro += dfs(a - 100, b);
        pro += dfs(a - 75, b - 25);
        pro += dfs(a - 50, b - 50);
        pro += dfs(a - 25, b - 75);
        pro *= 0.25;
        map.put(hash, pro);
        return pro;
    }


    public static void main(String[] args) {
        SoupServings servings = new SoupServings();
        for (int i = 0; i < 1000; i++) {
            BigDecimal decimal = BigDecimal.valueOf(servings.soupServings(i * 25));
            decimal = decimal.setScale(5, RoundingMode.HALF_UP);
            if (decimal.compareTo(BigDecimal.ONE) >= 0) {
                System.out.println("临界点:" + i * 25);
                break;
            }
        }
        System.out.println(servings.soupServings(4825));
        System.out.println(servings.soupServings(1700));

    }

}
