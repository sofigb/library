package com.egg.web.library.service;

import com.egg.web.library.entity.Loan;
import com.egg.web.library.repository.BookRepository;
import com.egg.web.library.repository.CustomerRepository;
import com.egg.web.library.repository.LoanRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRep;
    @Autowired
    private BookService bookSer;
    @Autowired
    private CustomerService customerSer;

    @Transactional
    public void createLoad(String idbook, String idCustomer) {
        Loan loan = new Loan();
        loan.setBook(bookSer.lookForId(idbook));
        loan.setCustomer(customerSer.lookForId(idCustomer));
        customerSer.changeBussy(idCustomer, Boolean.TRUE);
        bookSer.borrowedCopies(idbook);
        loan.setStatus(true);
        loanRep.save(loan);
    }

    @Transactional
    public void changeState(String id, Boolean status) {
        
        customerSer.changeBussy(loanRep.findById(id).get().getCustomer().getId() ,Boolean.FALSE);
        bookSer.devCopies(loanRep.findById(id).get().getBook().getId());
        loanRep.changeStatus(id, status);
    }

    @Transactional
    public void modifyLoad(String idloan, String idbook, String idCustomer) {
        Loan loan = loanRep.findById(idloan).get();
        loan.setBook(bookSer.lookForId(idbook));
        loan.setCustomer(customerSer.lookForId(idCustomer));
        loanRep.save(loan);
    }

    @Transactional(readOnly = true)
    public List<Loan> findAllTrue() {
        return loanRep.findAll().stream().filter(loan -> loan.getStatus()==true).collect(Collectors.toList());
    }
    
     @Transactional(readOnly = true)
    public List<Loan> findAllFalse() {
        return loanRep.findAll().stream().filter(loan -> loan.getStatus()==false).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Loan lookForId(String id) {
        return (loanRep.findById(id).get());
    }
}
