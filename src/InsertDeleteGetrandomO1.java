import java.util.*;

/**
 *380. O(1) 时间插入、删除和获取随机元素
 * 实现RandomizedSet 类：
 *
 * RandomizedSet() 初始化 RandomizedSet 对象
 * bool insert(int val) 当元素 val 不存在时，向集合中插入该项，并返回 true ；否则，返回 false 。
 * bool remove(int val) 当元素 val 存在时，从集合中移除该项，并返回 true ；否则，返回 false 。
 * int getRandom() 随机返回现有集合中的一项（测试用例保证调用此方法时集合中至少存在一个元素）。每个元素应该有 相同的概率 被返回。
 * 你必须实现类的所有函数，并满足每个函数的 平均 时间复杂度为 O(1) 。
 */
public class InsertDeleteGetrandomO1 {
    static class RandomizedSet {
        Map<Integer, Integer> map;
        List<Integer> list;
        Random random = new Random();
        int size;
        public RandomizedSet() {
            map = new HashMap<>();
            list = new ArrayList<>();
        }

        public boolean insert(int val) {
            if (map.containsKey(val)) {
                return false;
            }
            list.add(size, val);
            map.put(val, size++);
            return true;
        }

        public boolean remove(int val) {

            if (!map.containsKey(val)) {
                return false;
            }
            int idx = map.get(val);
            list.set(idx, list.get(--size));
            map.put(list.get(idx), idx);

            map.remove(val);
            return true;
        }

        public int getRandom() {
            return list.get(random.nextInt(size));
        }
    }

    public static void main(String[] args) {
        RandomizedSet set = new RandomizedSet();
        set.insert(0);
//        set.insert(1);
        set.remove(0);
        set.insert(2);
        set.remove(0);
        System.out.println(set.getRandom());
    }
}
