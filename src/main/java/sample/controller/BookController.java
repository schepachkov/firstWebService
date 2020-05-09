package sample.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sample.entity.Book;
import sample.service.BookService;

import java.util.List;


@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;


    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable int id) {
        bookService.deleteById(id);
    }


    @PatchMapping("/update/name/{id}")
    public ResponseEntity<Book> setBookName(@PathVariable int id, @RequestBody String name) {
        Book book = bookService.setBookName(id, name);
        if (book != null)
            return ResponseEntity.ok(book);
        else
            return new ResponseEntity(HttpStatus.NOT_MODIFIED);
    }

    @PatchMapping("/update/cost/{id}")
    public ResponseEntity<Book> setBookCost(@PathVariable int id, @RequestBody int cost) {
        Book book = bookService.setBookCost(id, cost);
        if (book != null)
            return ResponseEntity.ok(book);
        else
            return new ResponseEntity(HttpStatus.NOT_MODIFIED);
    }

    @PatchMapping("/update/stock/{id}")
    public ResponseEntity<Book> setBookStock(@PathVariable int id, @RequestBody String stock) {
        Book book = bookService.setBookStock(id, stock);
        if (book != null)
            return ResponseEntity.ok(book);
        else
            return new ResponseEntity(HttpStatus.NOT_MODIFIED);
    }

    @PatchMapping("/update/amount/{id}")
    public ResponseEntity<Book> setBookAmount(@PathVariable int id, @RequestBody int amount) {
        Book book = bookService.setBookAmount(id, amount);
        if (book != null)
            return ResponseEntity.ok(book);
        else
            return new ResponseEntity(HttpStatus.NOT_MODIFIED);
    }


    @PostMapping("/create")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createBook(@RequestBody Book book) {
        bookService.addBook(book);
    }


    @GetMapping("get/all")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("get/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id) {
        Book book = bookService.getBookById(id);
        if (book != null)
            return ResponseEntity.ok(book);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PutMapping("put/{id}")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void fullReplace(@PathVariable int id, @RequestBody Book book) {
        bookService.fullReplaceById(id, book);
    }

}
