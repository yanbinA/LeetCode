import java.util.*;

/**
 * 828. 统计子串中的唯一字符
 * 我们定义了一个函数 countUniqueChars(s) 来统计字符串 s 中的唯一字符，并返回唯一字符的个数。
 *
 * 例如：s = "LEETCODE" ，则其中 "L", "T","C","O","D" 都是唯一字符，因为它们只出现一次，所以 countUniqueChars(s) = 5 。
 *
 * 本题将会给你一个字符串 s ，我们需要返回 countUniqueChars(t) 的总和，其中 t 是 s 的子字符串。输入用例保证返回值为 32 位整数。
 *
 * 注意，某些子字符串可能是重复的，但你统计时也必须算上这些重复的子字符串（也就是说，你必须统计 s 的所有子字符串中的唯一字符）。
 */
public class CountUniqueCharactersOfAllSubstringsOfAGivenString {
    public int uniqueLetterString(String s) {
        int n = s.length();
        int[][] map = new int[26][2];
        for (int[] i : map) {
            i[0] = -1;
            i[1] = -1;
        }
        char[] chars = s.toCharArray();
        int ans = 0, sum = 0;
        for (int i = 0; i < n; i++) {
            int[] list = map[chars[i] - 'A'];
            //倒数第二个相同元素位置
            int ls = list[1];
            //倒数第一个相同元素位置
            int last = list[0];
            //ls+1~last中个数都-1
//                sum -= last - ls;
            //last+1~i中个数都+1
//                sum += i - last;
            sum +=i - last - last + ls;
            list[1] = list[0];
            list[0] = i;

            ans += sum;
        }
        return ans;
    }

    public static void main(String[] args) {
        CountUniqueCharactersOfAllSubstringsOfAGivenString ogj = new CountUniqueCharactersOfAllSubstringsOfAGivenString();
        System.out.println(ogj.uniqueLetterString("ABCCC"));
    }

    //A  B  C  D  A  E  D  D  D
    //1
    //2  1
    //3  2  1
    //4  3  2  1
    //3  4  3  2  1
    //4  5  4  3  2  1
    //3  4  3  2  3  2  1
    //3  4  3  2  2  1  0  1
    //3  4  3  2  2  1  0  0  1


    //i
// j//1
    //3  1
    //6  3  1
    //10 6  3  1
    //13 10 6  3  1           sum = arr[j][0] - 1 +
    //19 15 10 6  3  1
    //3  4  3  2  3  2  1
    //2  3  2  1  1  0  0  1
}
