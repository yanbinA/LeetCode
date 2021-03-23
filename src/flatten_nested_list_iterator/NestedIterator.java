package flatten_nested_list_iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Depp
 */
public class NestedIterator implements Iterator<Integer> {
    private List<Integer> list;
    private Iterator<Integer> iterator;

    public NestedIterator(List<NestedInteger> nestedList) {
        list = new ArrayList<>();
        nestedList.forEach(this::traverse);
        iterator = list.iterator();
    }

    public void traverse(NestedInteger nested) {
        if (nested.isInteger()) {
            list.add(nested.getInteger());
            return;
        }
        nested.getList().forEach(this::traverse);
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public Integer next() {
        return iterator.next();
    }
}
