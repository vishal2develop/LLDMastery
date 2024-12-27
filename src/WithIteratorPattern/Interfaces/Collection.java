package WithIteratorPattern.Interfaces;

// Collection interface that provides a method to create an iterator.
public interface Collection<T>{
    Iterator<T> createIterator();

}
