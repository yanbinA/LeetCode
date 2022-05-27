/**
 * 面试题 17.11. 单词距离
 * 有个内含单词的超大文本文件，给定任意两个不同的单词，找出在这个文件中这两个单词的最短距离(相隔单词数)。如果寻找过程在这个文件中会重复多次，而每次寻找的单词不同，你能对此优化吗?
 */
public class FindClosestLcci {
    public int findClosest(String[] words, String word1, String word2) {
        int idx1 = -1, idx2 = -1, min = words.length;
        for (int i = 0; i < words.length; i++) {
            if (word1.equals(words[i])) {
                idx1 = i;
                if (idx2 >= 0) {
                    min = Math.min(idx1 - idx2, min);
                    if (min == 1) {
                        return min;
                    }
                }

            }
            if (word2.equals(words[i])) {
                idx2 = i;
                if (idx1 >= 0) {
                    min = Math.min(idx2 - idx1, min);
                    if (min == 1) {
                        return min;
                    }
                }
            }
        }
        return min;
    }
}
