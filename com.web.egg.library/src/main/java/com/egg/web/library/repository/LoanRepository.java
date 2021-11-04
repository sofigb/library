package com.egg.web.library.repository;

import com.egg.web.library.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<Loan, String> {

    @Modifying
    @Query("UPDATE Loan l SET l.status = :status WHERE l.id = :id")
    void changeStatus(@Param("id") String id, @Param("status") boolean status);
}
