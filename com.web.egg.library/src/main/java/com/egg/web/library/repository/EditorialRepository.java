package com.egg.web.library.repository;

import com.egg.web.library.entity.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EditorialRepository extends JpaRepository<Editorial, String> {

    @Modifying
    @Query("UPDATE Editorial e SET e.name = :name WHERE e.id = :id")
    void modifyName(@Param("id") String id, @Param("name") String name);

    @Query("SELECT e FROM Editorial e WHERE e.name = :name ")
    void findByName(@Param("name") String name);

    @Query(value = "CALL listarEditorial();", nativeQuery = true)
    List<Editorial> listarEditoriales();

    @Query(value = "CALL activarEditorial(:idEditorial);", nativeQuery = true)
    String activarEditorial(@Param("idEditorial") String idEditorial);

    @Query(value = "CALL desactivarEditorial(:idEditorial);", nativeQuery = true)
    String desactivarEditorial(@Param("idEditorial") String idEditorial);



//    @Modifying
//    @Query("UPDATE Editorial e SET e.status = :status WHERE e.id = :id")
//    void changeStatus(@Param("id") String id, @Param("status") boolean status);

}
