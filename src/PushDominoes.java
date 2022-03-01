import java.util.ArrayList;
import java.util.List;

/**
 * <h3>838. 推多米诺</h3>
 * n 张多米诺骨牌排成一行，将每张多米诺骨牌垂直竖立。在开始时，同时把一些多米诺骨牌向左或向右推。
 *
 * 每过一秒，倒向左边的多米诺骨牌会推动其左侧相邻的多米诺骨牌。同样地，倒向右边的多米诺骨牌也会推动竖立在其右侧的相邻多米诺骨牌。
 *
 * 如果一张垂直竖立的多米诺骨牌的两侧同时有多米诺骨牌倒下时，由于受力平衡， 该骨牌仍然保持不变。
 *
 * 就这个问题而言，我们会认为一张正在倒下的多米诺骨牌不会对其它正在倒下或已经倒下的多米诺骨牌施加额外的力。
 *
 * 给你一个字符串 dominoes 表示这一行多米诺骨牌的初始状态，其中：
 *
 * dominoes[i] = 'L'，表示第 i 张多米诺骨牌被推向左侧，
 * dominoes[i] = 'R'，表示第 i 张多米诺骨牌被推向右侧，
 * dominoes[i] = '.'，表示没有推动第 i 张多米诺骨牌。
 * 返回表示最终状态的字符串。
 */
public class PushDominoes {

    public String pushDominoes(String dominoes) {
        String temp = "";
        while (!temp.equals(dominoes)) {
            temp = dominoes;
            dominoes = dominoes.replaceAll("R\\.L", "T");
            dominoes = dominoes.replaceAll("\\.L", "LL");
            dominoes = dominoes.replaceAll("R\\.", "RR");
            dominoes = dominoes.replaceAll("T", "R\\.L");
        }
        return dominoes;
    }

    /**
     * 执行用时：
     * 1285 ms
     * 5.40%
     * 内存消耗：
     * 43.3 MB
     * 14.41%
     */
    public String pushDominoes1(String dominoes) {
        int length = dominoes.length();
        char[] chars = dominoes.toCharArray();
        boolean push = true;
        while (push) {
            push = false;
            List<Integer> changIndex = new ArrayList<>();
            for (int i = 0; i < length; i++) {
                if (chars[i] == 'R' && i < length - 1 && chars[i + 1] == '.') {
                    chars[i + 1] = 'r';
                    changIndex.add(i + 1);
                }
                if (chars[i] == 'L' && i > 0) {
                    if (chars[i - 1] == '.') {
                        chars[i - 1] = 'l';
                        changIndex.add(i - 1);
                    }
                    if (chars[i - 1] == 'r') {
                        chars[i - 1] = '.';
                        changIndex.remove(changIndex.indexOf(i - 1));
                    }
                }
            }
            for (int index : changIndex) {
                push = true;
                chars[index] += 'A' - 'a';
            }
        }
        StringBuilder sb = new StringBuilder();
        for (char aChar : chars) {
            sb.append(aChar);
        }
        return sb.toString();
    }



    public static void main(String[] args) {
        PushDominoes pushDominoes = new PushDominoes();
        System.out.println(pushDominoes.pushDominoes(".L.R...LR..L.."));
    }
}
