package sample.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sample.entity.Book;
import sample.repository.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private Logger logger = LoggerFactory.getLogger(BookService.class);

    private final String TABLE = "book";

    @Autowired
    private BookRepository bookRepository;


    public Book getBookById(int id) {
        Optional<Book> foundBook = bookRepository.findById(id);
        return foundBook.orElse(null);
    }


    public void deleteById(int id) {
        boolean isDelete = bookRepository.safeDelete(TABLE, id);
        if (!isDelete)
            logger.warn(String.format("You try to delete a doesn't exist entity from the table \"%s\"!", TABLE));
    }


    public Book setBookName(int id, String name) {
        Book book = getBookById(id);
        if (book != null) {
            book.setName(name);
            bookRepository.save(book);
            return book;
        } else {
            logger.warn("This book doesn't exist");
            return null;
        }
    }

    public Book setBookCost(int id, int cost) {
        Book book = getBookById(id);
        if (book != null) {
            book.setCost(cost);
            bookRepository.save(book);
            return book;
        } else {
            logger.warn("This book doesn't exist");
            return null;
        }
    }

    public Book setBookStock(int id, String stock) {
        Book book = getBookById(id);
        if (book != null) {
            book.setStock(stock);
            bookRepository.save(book);
            return book;
        } else {
            logger.warn("This book doesn't exist");
            return null;
        }
    }

    public Book setBookAmount(int id, int amount) {
        Book book = getBookById(id);
        if (book != null) {
            book.setAmount(amount);
            bookRepository.save(book);
            return book;
        } else {
            logger.warn("This book doesn't exist");
            return null;
        }
    }


    public Book updateBookAmount(int id, int amount) {
        Book book = getBookById(id);
        if (book != null) {
            book.setAmount(book.getAmount() + amount);
            bookRepository.save(book);
            return book;
        } else {
            logger.warn("This book doesn't exist");
            return null;
        }
    }


    public void addBook(Book book) {
        bookRepository.save(book);
    }


    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }


    public void fullReplaceById(int id, Book book) {
        bookRepository.updateBook(id, book.getName(), book.getCost(), book.getStock(), book.getAmount());
    }

}
