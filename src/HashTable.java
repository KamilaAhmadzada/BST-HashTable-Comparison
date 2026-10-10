
import java.util.LinkedList;

public class HashTable {

    private static class Entry {
        int key;

        Entry(int key) {
            this.key = key;
        }
    }

    private final int capacity = 101;
    private final LinkedList<Entry>[] buckets;
    private int size;

    @SuppressWarnings("unchecked")
    public HashTable() {
        buckets = new LinkedList[capacity];

        for (int i = 0; i < capacity; i++) {
            buckets[i] = new LinkedList<>();
        }

        size = 0;
    }

    private int hash(int key) {
        return Math.floorMod(key, capacity);
    }

    public void insert(int key) {
        int index = hash(key);

        for (Entry entry : buckets[index]) {
            if (entry.key == key) {
                return;
            }
        }

        buckets[index].add(new Entry(key));
        size++;
    }

    public boolean search(int key) {
        int index = hash(key);

        for (Entry entry : buckets[index]) {
            if (entry.key == key) {
                return true;
            }
        }

        return false;
    }

    public void delete(int key) {
        int index = hash(key);

        for (Entry entry : buckets[index]) {
            if (entry.key == key) {
                buckets[index].remove(entry);
                size--;
                return;
            }
        }
    }

    public int size() {
        return size;
    }
}

