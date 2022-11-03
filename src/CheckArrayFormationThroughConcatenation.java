import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 1640. 能否连接形成数组
 * 给你一个整数数组 arr ，数组中的每个整数 互不相同 。另有一个由整数数组构成的数组 pieces，其中的整数也 互不相同 。请你以 任意顺序 连接 pieces 中的数组以形成 arr 。但是，不允许 对每个数组 pieces[i] 中的整数重新排序。
 *
 * 如果可以连接 pieces 中的数组形成 arr ，返回 true ；否则，返回 false 。
 */
public class CheckArrayFormationThroughConcatenation {
    public boolean canFormArray(int[] arr, int[][] pieces) {
        Map<Integer, int[]> map = new HashMap<>(pieces.length, 1);
        int n = arr.length;
        int pn = pieces.length;
        int pl = 0;
        for (int[] piece : pieces) {
            map.put(piece[0], piece);
        }
        int left = 0;
        while (left < n) {
            int value = arr[left];
            if (!map.containsKey(value)) {
                for (; pl < pn; pl++) {
                    map.put(pieces[pl][0], pieces[pl]);
                    if (pieces[pl][0] == value) {
                        break;
                    }
                }
                if (pl == pn) {
                    return false;
                }
            }
            int[] piece = map.get(value);
            left++;
            for (int i = 1; i < piece.length; i++) {
                if (left >= n || arr[left] != piece[i]) {
                    return false;
                }
                left++;
            }
        }
        return true;
    }
}
