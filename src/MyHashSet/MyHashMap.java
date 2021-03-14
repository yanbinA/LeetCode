package MyHashSet;

import java.util.Arrays;

public class MyHashMap {
    private int[] tables = new int[1000009];
    private boolean[] has = new boolean[1000009];
    /** Initialize your data structure here. */
    public MyHashMap() {
    }

    /** value will always be non-negative. */
    public void put(int key, int value) {
        tables[key] = value;
        has[key] = true;
    }

    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        return has[key] ? tables[key] : -1;
    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        has[key] = false;
    }
}
