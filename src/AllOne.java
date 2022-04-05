import java.util.*;

/**
 * <p>
 * 432. 全 O(1) 的数据结构
 * 请你设计一个用于存储字符串计数的数据结构，并能够返回计数最小和最大的字符串。
 *
 * 实现 AllOne 类：
 *
 * AllOne() 初始化数据结构的对象。
 * inc(String key) 字符串 key 的计数增加 1 。如果数据结构中尚不存在 key ，那么插入计数为 1 的 key 。
 * dec(String key) 字符串 key 的计数减少 1 。如果 key 的计数在减少后为 0 ，那么需要将这个 key 从数据结构中删除。测试用例保证：在减少计数前，key 存在于数据结构中。
 * getMaxKey() 返回任意一个计数最大的字符串。如果没有元素存在，返回一个空字符串 "" 。
 * getMinKey() 返回任意一个计数最小的字符串。如果没有元素存在，返回一个空字符串 "" 。
 * </p>
 *
 * @author messi
 * @package PACKAGE_NAME
 * @description all-oone-data-structure
 * @date 2022-03-16 10:18
 * @verison V1.0.0
 */
public class AllOne {

    private Map<String, KeyEntry> keyEntryMap;
    private KeyEntry root;

    public AllOne() {
        this.keyEntryMap = new HashMap<>();
        this.root = new KeyEntry();
        this.root.prev = root;
        this.root.next = root;
    }

    public void inc(String key) {
        if (keyEntryMap.containsKey(key)) {
            KeyEntry cur = keyEntryMap.get(key);
            KeyEntry next = cur.next;
            if (next.count != cur.count + 1) {
                keyEntryMap.put(key, cur.insert(cur.count + 1, key));
            } else {
                next.elements.add(key);
                keyEntryMap.put(key, next);
            }
            cur.elements.remove(key);
            if (cur.elements.isEmpty()) {
                cur.remove();
            }
        } else {

            if (root.next.count == 1) {
                root.next.elements.add(key);
                keyEntryMap.put(key,  root.next);
            } else {
                keyEntryMap.put(key, root.insert(1, key));
            }


        }
    }


    public void dec(String key) {
        KeyEntry cur = keyEntryMap.get(key);
        if (cur.count != 1) {
            KeyEntry pre = cur.prev;
            if (pre.count != cur.count - 1) {
                keyEntryMap.put(key, pre.insert(cur.count - 1, key));
            } else {
                pre.elements.add(key);
                keyEntryMap.put(key, pre);
            }
        } else {
            keyEntryMap.remove(key);
        }
        cur.elements.remove(key);
        if (cur.elements.isEmpty()) {
            cur.remove();
        }
    }

    public String getMaxKey() {
        return root.prev.elements.stream().iterator().next();
    }



    public String getMinKey() {
        return root.next.elements.stream().iterator().next();
    }


    static class KeyEntry {
        int count;
        Set<String> elements;
        KeyEntry prev;
        KeyEntry next;

        KeyEntry(){
            this(0, "");
        }

        KeyEntry(int count, String key) {
            this.count = count;
            this.elements = new HashSet<>();
            this.elements.add(key);
        }

        KeyEntry insert(int count, String key) {
            KeyEntry current = new KeyEntry(count, key);
            current.prev = this;
            current.next = this.next;
            current.prev.next = current;
            current.next.prev = current;
            return current;
        }

        public void remove() {
            this.next.prev = this.prev;
            this.prev.next = this.next;
        }
    }


    public static void main(String[] args) {
//["AllOne","inc","inc","inc","inc","inc","dec","getMaxKey","getMinKey","inc","inc","inc","getMaxKey","getMinKey","inc","inc","getMinKey"]
//[[],["hello"],["hello"],["world"],["world"],["hello"],["world"],[],[],["world"],["world"],["leet"],[],[],["leet"],["leet"],[]]
        AllOne obj = new AllOne();
        obj.inc("hello");
        obj.inc("hello");
        obj.inc("world");
        obj.inc("world");
        obj.inc("hello");
        obj.dec("world");
        System.out.println(obj.getMaxKey());
        System.out.println(obj.getMinKey());
        obj.inc("world");
        obj.inc("world");
        obj.inc("leet");
        System.out.println(obj.getMaxKey());
        System.out.println(obj.getMinKey());
        obj.inc("leet");
        obj.inc("leet");
        System.out.println(obj.getMinKey());
        String param_4 = obj.getMinKey();
    }


}
