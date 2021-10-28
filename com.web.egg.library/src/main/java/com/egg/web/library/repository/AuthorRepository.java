package com.egg.web.library.repository;

import com.egg.web.library.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, String> {


    @Modifying
    @Query("update Author a SET a.name =:name WHERE a.id =:id")
    void modifyName(@Param("id") String ide, @Param("name") String name);
   

    @Modifying
    @Query("UPDATE Author a SET a.status = :status WHERE a.id = :id")
    void changeStatus(@Param("id") String id, @Param("status") boolean status);

    @Query("SELECT a FROM Author a WHERE a.name = :name ")
    void findByName(@Param("name") String name);
}
