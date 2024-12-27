package WithIteratorPattern.Interfaces;

// Iterator interface with methods for traversal.
public interface Iterator<T> {
    boolean hasNext();
    T next();
}
