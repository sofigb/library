package com.egg.web.library.repository;

import com.egg.web.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {

    @Modifying
    @Query("UPDATE Book b SET b.title = :title , b.isbn = :isbn , b.year = :year , b.copies = :copies  WHERE b.id = :id")
    void modifyAll(@Param("id") String id, @Param("title") String title, @Param("isbn") Long isbn, @Param("year") Integer year, @Param("copies") Integer copies);

    @Modifying
    @Query("UPDATE Book b SET b.status = :status WHERE b.id = :id")
    void changeStatus(@Param("id") String id, @Param("status") boolean status);

    @Query("SELECT b FROM Book b WHERE b.title = :title ")
    void findByTitle(@Param("title") String title);

    @Query("SELECT b FROM Book b WHERE b.isbn = :isbn ")
    void findByISBN(@Param("isbn") Long isbn);

    @Query("SELECT a FROM Book a WHERE a.author.name LIKE :author ")
    void findByAuthor(@Param("author") String name);

}
