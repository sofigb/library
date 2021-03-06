package com.egg.web.library.service;
import java.util.stream.Collectors;
import com.egg.web.library.entity.Book;
import com.egg.web.library.exception.MyExceptionService;
import com.egg.web.library.repository.BookRepository;
import com.egg.web.library.validation.Validation;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {
   

    @Autowired
    private BookRepository bookRep;
    @Autowired
    private EditorialService editorialser;
    @Autowired
    private AuthorService authorSer;

    @Transactional
    public void createBook( Long isbn, String title, Integer year, Integer copies, String author,String editorial) throws MyExceptionService {
//        try {
//            Validation.validationService(isbn, title, year, copies);
//        } catch (MyExceptionService e) {
//            throw new MyExceptionService();
//        }
       

        Book book = new Book(); 
        book.setIsbn(isbn);
        book.setTitle(title);
        book.setYear(year);
        book.setCopies(copies); 
        book.setAuthor(authorSer.returnAuthor(author));
        book.setEditorial(editorialser.returnEditorial(editorial));
        book.setStatus(true);
        bookRep.save(book);
    }
//NO ESTOY USANDO MI QUERY ESPECIAL PARA ESTO

    @Transactional
    public void modifyBook(String id, Long isbn, String title, Integer year, Integer copies, String author,String editorial) throws MyExceptionService {
//        try {
//            Validation.validationService(isbn, title, year, copies);
//            Optional<Book> reponse = bookRep.findById(id);
//            Validation.validationIDfound(id, reponse);
//
//        } catch (MyExceptionService e) {
//            throw new MyExceptionService();
//        }

        Book book = bookRep.findById(id).get();

       book.setIsbn(isbn);
        book.setTitle(title);
        book.setYear(year);
        book.setCopies(copies); 
        book.setAuthor(authorSer.returnAuthor(author));
        book.setEditorial(editorialser.returnEditorial(editorial));
        book.setStatus(true);
        bookRep.save(book);

    }
     @Transactional
    public void changeState(String id, Boolean status)  {
//        try {
//            Optional<Author> reponse = authorRep.findById(id);
//            Validation.validationIDfound(id, reponse);
//
//        } catch (MyExceptionService e) {
//            throw new MyExceptionService();
//        }
     
      bookRep.changeStatus(id, status);
    }

    @Transactional
    public void unsuscribe(String id) throws MyExceptionService {
        try {
            Optional<Book> reponse = bookRep.findById(id);
            Validation.validationIDfound(id, reponse);

        } catch (MyExceptionService e) {
            throw new MyExceptionService();
        }

        Book book = bookRep.findById(id).get();
        book.setStatus(false);
        bookRep.save(book);
    }

    @Transactional(readOnly = true)
    public Book lookForId(String id) {
        Optional<Book> bookOptional = bookRep.findById(id);
        return bookOptional.orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Book> findAll() {
        return bookRep.findAll();
    }
    @Transactional(readOnly = true)
    public List<Book> bookInStock() {
       
        return bookRep.findAll().stream().filter(book -> book.getRemainingCopies()>0).collect(Collectors.toList());
    }
    @Transactional
    public void borrowedCopies(String id) {
        Book book =bookRep.findById(id).get();
        
        book.setBorrowedCopies(book.getBorrowedCopies()+1);
        bookRep.save(book);
    }
    @Transactional
    public void devCopies(String id) {
        Book book =bookRep.findById(id).get();
        
        book.setBorrowedCopies(book.getBorrowedCopies()-1);
        bookRep.save(book);
    }
   
}
