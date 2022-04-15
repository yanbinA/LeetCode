import java.util.List;

/**
 *385. 迷你语法分析器
 * 给定一个字符串 s 表示一个整数嵌套列表，实现一个解析它的语法分析器并返回解析的结果 NestedInteger 。
 *
 * 列表中的每个元素只可能是整数或整数嵌套列表
 */
public class MiniParser {
    int left;
    char[] chars;
    int n;
    public NestedInteger deserialize(String s) {
        chars = s.toCharArray();
        left = 0;
        n = chars.length;
        return deserialize();
    }

    private NestedInteger deserialize() {
        if (chars[left] == '[') {
            NestedInteger nestedInteger = new NestedInteger();
            while (chars[left] != ']') {
                left++;
                if (chars[left] == ']') {
                    continue;
                }
                nestedInteger.add(deserialize());
            }
            left++;
            return nestedInteger;
        } else {
            int nested = 0;
            int positive = 1;
            if (chars[left] == '-') {
                positive = -1;
                left++;
            }
            while (left < n && (chars[left] != ']' && chars[left] != ',')) {
                nested = nested * 10 + chars[left++] - '0';
            }
            return new NestedInteger(nested * positive);
        }
    }

    public static void main(String[] args) {
        MiniParser miniParser = new MiniParser();
        miniParser.deserialize("[123,[456,[789]]]");
    }


    // This is the interface that allows for creating nested lists.
      // You should not implement it, or speculate about its implementation
      static class NestedInteger {
          // Constructor initializes an empty nested list.
          public NestedInteger(){}

          // Constructor initializes a single integer.
          public NestedInteger(int value){}

          // @return true if this NestedInteger holds a single integer, rather than a nested list.
          public boolean isInteger(){return true;}

          // @return the single integer that this NestedInteger holds, if it holds a single integer
          // Return null if this NestedInteger holds a nested list
          public Integer getInteger() {return 1;}

          // Set this NestedInteger to hold a single integer.
          public void setInteger(int value){}

          // Set this NestedInteger to hold a nested list and adds a nested integer to it.
          public void add(NestedInteger ni){}

          // @return the nested list that this NestedInteger holds, if it holds a nested list
          // Return empty list if this NestedInteger holds a single integer
          public List<NestedInteger> getList(){return null;}
      }
}
