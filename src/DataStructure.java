/**
 * An abstraction of a map datastructure
 * @param <K>
 * @param <T>
 */
public interface DataStructure<K extends Comparable<K>, T> {
    void pushItem(K key, T data);
    T getItem(K key);
}
