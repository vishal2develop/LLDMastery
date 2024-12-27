package WithIteratorPattern.ConcreteIterator;

import WithIteratorPattern.Book;
import WithIteratorPattern.Interfaces.Iterator;

public class BookIterator implements Iterator<Book> {
    private Book[] books;
    private int position;

    public BookIterator(Book[] books){
         this.books=books;
     }

    @Override
    public boolean hasNext() {
        return position < books.length && books[position] != null;
    }

    @Override
    public Book next() {
        Book book = books[position];
        position+=1;
        return book;
    }

}
