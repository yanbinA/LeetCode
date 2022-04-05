/**
 * 6. Z 字形变换
 * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
 *
 * 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
 *
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
 */
public class ZigzagConversion {
    public String convert(String s, int numRows) {
        int length = s.length(), t = numRows * 2 - 2;
        if (numRows == 1 || length <= numRows) {
            return s;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j + i < length; j += t) {
                sb.append(s.charAt(j + i));
                if (0 < i && i < numRows - 1 && j + t - i < length) {
                    sb.append(s.charAt(j + t - i));
                }
            }
        }
        return sb.toString();
    }
}
