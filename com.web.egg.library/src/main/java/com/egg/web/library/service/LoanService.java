package com.egg.web.library.service;

import com.egg.web.library.entity.Loan;
import com.egg.web.library.repository.BookRepository;
import com.egg.web.library.repository.CustomerRepository;
import com.egg.web.library.repository.LoanRepository;
import java.util.List;
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
        loanRep.save(loan);
    }

    @Transactional
    public void modifyLoad(String idloan, String idbook, String idCustomer) {
        Loan loan = loanRep.findById(idloan).get();
        loan.setBook(bookSer.lookForId(idbook));
        loan.setCustomer(customerSer.lookForId(idCustomer));
        loanRep.save(loan);
    }

    @Transactional(readOnly = true)
    public List<Loan> findAll() {
        return loanRep.findAll();
    }

    @Transactional(readOnly = true)
    public Loan lookForId(String id) {
        return (loanRep.findById(id).get());
    }
}
