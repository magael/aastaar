package mj.aastaar.datastructures;

/**
 *
 * @author MJ
 */
public class CustomEntry<K, V> {
    
    private K key;
    private V value;
    private CustomEntry next;
    private CustomEntry prev;

    public CustomEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }
    
    public V getValue(K key) {
        return value;
    }

    public K getKey() {
        return key;
    }
    
    public CustomEntry<K, V> getNext() {
        return next;
    }

    public CustomEntry<K, V> getPrev() {
        return prev;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public void setNext(CustomEntry<K, V> next) {
        this.next = next;
    }

    public void setPrev(CustomEntry<K, V> prev) {
        this.prev = prev;
    }
}
