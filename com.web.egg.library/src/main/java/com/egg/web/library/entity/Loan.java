package com.egg.web.library.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Loan {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @CreatedDate
    @Column(nullable = false, updatable = false) //updatable = es que no se puede modificar
    private Date dateLoan;

    @LastModifiedDate
    private Date dateDev;
    private Boolean status;

    @ManyToOne
    private Book book;

    @ManyToOne
    private Customer customer;

    public Loan() {
    }

    public Loan(String id, Date dateLoan, Date dateDev, Boolean status, Book book, Customer customer) {
        this.id = id;
        this.dateLoan = dateLoan;
        this.dateDev = dateDev;
        this.status = status;
        this.book = book;
        this.customer = customer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDateLoan() {
        return dateLoan;
    }

    public void setDateLoan(Date dateLoan) {
        this.dateLoan = dateLoan;
    }

    public Date getDateDev() {
        return dateDev;
    }

    public void setDateDev(Date dateDev) {
        this.dateDev = dateDev;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}
