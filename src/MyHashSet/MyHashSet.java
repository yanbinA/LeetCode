package MyHashSet;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author Depp
 */
public class MyHashSet {
    private boolean[] tables = new boolean[1000009];
    /** Initialize your data structure here. */
    public MyHashSet() {
    }

    public void add(int key) {
        tables[key] = true;
    }

    public void remove(int key) {
        tables[key] = false;
    }

    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        return tables[key];
    }
}
