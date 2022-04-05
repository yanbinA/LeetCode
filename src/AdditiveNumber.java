/**
 * <h3>306. 累加数</h3>
 *累加数 是一个字符串，组成它的数字可以形成累加序列。
 *
 * 一个有效的 累加序列 必须 至少 包含 3 个数。除了最开始的两个数以外，字符串中的其他数都等于它之前两个数相加的和。
 *
 * 给你一个只包含数字 '0'-'9' 的字符串，编写一个算法来判断给定输入是否是 累加数 。如果是，返回 true ；否则，返回 false 。
 *
 * 说明：累加序列里的数 不会 以 0 开头，所以不会出现 1, 2, 03 或者 1, 02, 3 的情况。
 */
public class AdditiveNumber {
    public boolean isAdditiveNumber(String num) {
        int length = num.length();
        int maxLength = length / 2;
        for (int i = 1; i <= maxLength; i++) {
            int beginIndex = length - i;
            if (i > 1 && num.charAt(beginIndex) == '0') {
                continue;
            }
            long third = Long.parseLong(num.substring(beginIndex, length));
            for (int j = 1; j <= i; j++) {
                if (j == 0) {
                    continue;
                }
                int secondIndex = beginIndex - j;
                if (secondIndex == 0) {
                    continue;
                }
                if (j > 1 && num.charAt(secondIndex) == '0') {
                    continue;
                }
                long second = Long.parseLong(num.substring(secondIndex, beginIndex));
                if (second > third) {
                    continue;
                }
                if (findAdditiveNumber(third, second, secondIndex, num)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean findAdditiveNumber(long third, long second, int endIndex, String num) {
        if (endIndex == 0) {
            return true;
        }
        long target = third - second;
        if (target < 0) {
            return false;
        }
        String value = String.valueOf(target);
        int beginIndex = endIndex - value.length();
        if (beginIndex < 0) {
            return false;
        }
        if (!num.substring(beginIndex, endIndex).equals(value)) {
            return false;
        } else {
            return findAdditiveNumber(second, target, beginIndex, num);
        }
    }


    public static void main(String[] args) {
        //199111992
        AdditiveNumber additiveNumber = new AdditiveNumber();
        System.out.println(additiveNumber.isAdditiveNumber("199111992"));
        System.out.println(additiveNumber.isAdditiveNumber("23581321345589"));
        System.out.println(additiveNumber.isAdditiveNumber("199100199"));
        System.out.println(additiveNumber.isAdditiveNumber("000000"));
    }
}
