/**
 * 393. UTF-8 编码验证
 * 给定一个表示数据的整数数组 data ，返回它是否为有效的 UTF-8 编码。
 *
 * UTF-8 中的一个字符可能的长度为 1 到 4 字节，遵循以下的规则：
 *
 * 对于 1 字节 的字符，字节的第一位设为 0 ，后面 7 位为这个符号的 unicode 码。
 * 对于 n 字节 的字符 (n > 1)，第一个字节的前 n 位都设为1，第 n+1 位设为 0 ，后面字节的前两位一律设为 10 。剩下的没有提及的二进制位，全部为这个符号的 unicode 码。
 * 这是 UTF-8 编码的工作方式：
 *
 *    Char. number range  |        UTF-8 octet sequence
 *       (hexadecimal)    |              (binary)
 *    --------------------+---------------------------------------------
 *    0000 0000-0000 007F | 0xxxxxxx
 *    0000 0080-0000 07FF | 110xxxxx 10xxxxxx
 *    0000 0800-0000 FFFF | 1110xxxx 10xxxxxx 10xxxxxx
 *    0001 0000-0010 FFFF | 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
 * 注意：输入是整数数组。只有每个整数的 最低 8 个有效位 用来存储数据。这意味着每个整数只表示 1 字节的数据。
 */
public class Utf8Validation {
    public boolean validUtf8(int[] data) {
        int n = data.length, left = 0;
        while (left < n) {
            int b = data[left];
            int count = 0;
            if (b >> 3 == 30) {
                count = 3;
            } else if (b >> 4 == 14) {
                count = 2;
            } else if (b >> 5 == 6) {
                count = 1;
            } else if (b >> 7 != 0) {
                return false;
            }
            if (left + count < n) {
                for (int i = 0; i < count; i++) {
                    left++;
                    if (data[left] >> 6 != 2) {
                        return false;
                    }
                }
            } else {
                return false;
            }
            left++;
        }
        return true;
    }

    public static void main(String[] args) {
        Utf8Validation utf8Validation = new Utf8Validation();
        System.out.println(utf8Validation.validUtf8(new int[]{255}));
    }
}
