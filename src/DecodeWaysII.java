import java.math.BigInteger;

/**
 * <h3>解码方法II</h3>
 * 一条包含字母 A-Z 的消息通过以下的方式进行了编码：
 *
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 要 解码 一条已编码的消息，所有的数字都必须分组，然后按原来的编码方案反向映射回字母（可能存在多种方式）。例如，"11106" 可以映射为：
 *
 * "AAJF" 对应分组 (1 1 10 6)
 * "KJF" 对应分组 (11 10 6)
 * 注意，像 (1 11 06) 这样的分组是无效的，因为 "06" 不可以映射为 'F' ，因为 "6" 与 "06" 不同。
 *
 * 除了 上面描述的数字字母映射方案，编码消息中可能包含 '*' 字符，可以表示从 '1' 到 '9' 的任一数字（不包括 '0'）。例如，编码字符串 "1*" 可以表示 "11"、"12"、"13"、"14"、"15"、"16"、"17"、"18" 或 "19" 中的任意一条消息。对 "1*" 进行解码，相当于解码该字符串可以表示的任何编码消息。
 *
 * 给你一个字符串 s ，由数字和 '*' 字符组成，返回 解码 该字符串的方法 数目 。
 *
 * 由于答案数目可能非常大，返回对 109 + 7 取余 的结果。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/decode-ways-ii
 */
public class DecodeWaysII {

    public void test() {
        System.out.println(numDecodings2("1*6*7*1*9*6*2*9*2*3*3*6*3*2*2*4*7*2*9*6*0*6*4*4*1*6*9*0*5*9*2*5*7*7*0*6*9*7*1*5*5*9*3*0*4*9*2*6*2*5*7*6*1*9*4*5*8*4*7*4*2*7*1*2*1*9*1*3*0*6*"));
    }

    static final int MOD = 1000000007;

    public int numDecodings(String s) {
        int n = s.length();
        long a = 0, b = 1, c = 0;
        for (int i = 1; i <= n; ++i) {
            c = b * check1digit(s.charAt(i - 1)) % MOD;
            if (i > 1) {
                c = (c + a * check2digits(s.charAt(i - 2), s.charAt(i - 1))) % MOD;
            }
            a = b;
            b = c;
        }
        return (int) c;
    }

    public int check1digit(char ch) {
        if (ch == '0') {
            return 0;
        }
        return ch == '*' ? 9 : 1;
    }

    public int check2digits(char c0, char c1) {
        if (c0 == '*' && c1 == '*') {
            return 15;
        }
        if (c0 == '*') {
            return c1 <= '6' ? 2 : 1;
        }
        if (c1 == '*') {
            if (c0 == '1') {
                return 9;
            }
            if (c0 == '2') {
                return 6;
            }
            return 0;
        }
        return (c0 != '0' && (c0 - '0') * 10 + (c1 - '0') <= 26) ? 1 : 0;
    }

    //F(n) = F(n - 1) + combin(F(n - 2))
    //n个字符解码数 = n-1个字符解码数 + 后两位可组合的数量
    public int numDecodings2(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        if(chars[0] == '0'){
            return 0;
        }
        long b = 1, c, a;
        if(chars[0] == '*'){
            c = 9;
        }else{
            c = 1;
        }
        for (int i = 1; i < n; i++) {
            a = c;
            if(chars[i] == '0'){
                if(chars[i-1] == '*'){
                    c = b * 2;
                    b = a;
                }else if(chars[i-1] == '2' || chars[i-1] == '1'){
                    c = b;
                    b = a;
                }else{
                    return 0;
                }
            }else if(chars[i] == '*'){
                if(chars[i-1] == '*'){
                    c = c * 9 + b * 15;
                    b = a;
                }else if(chars[i-1] == '0' || chars[i-1] - '0' > 2){
                    c = c * 9;
                    b = a;
                }else if(chars[i-1] == '1'){
                    c = c * 9 + b * 9;
                    b = a;
                }else{
                    c = c * 9 + b * 6;
                    b = a;
                }
            }else{
                if(chars[i-1] == '*' && chars[i] - '0' < 7){
                    c = c + b * 2;
                    b = a;
                }else if(chars[i-1] == '*' || chars[i-1] == '2' && chars[i] - '0' < 7 || chars[i-1] == '1'){
                    c = c + b;
                    b = a;
                }else{
                    c = c;
                    b = a;
                }
            }
            b = b % (1000000007);
            c = c % (1000000007);
        }
        return (int)c;
    }
}
