package com.egg.web.library.repository;

import com.egg.web.library.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {

    @Modifying
    @Query("UPDATE Customer c SET c.status = :status WHERE c.id = :id")
    void changeStatus(@Param("id") String id, @Param("status") boolean status);
}
