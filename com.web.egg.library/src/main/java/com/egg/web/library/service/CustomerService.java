package com.egg.web.library.service;

import com.egg.web.library.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.egg.web.library.repository.CustomerRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
@Service
public class CustomerService {

    @Autowired
    private CustomerRepository costumerRep;

    @Transactional
    public void createCustomer(Long dni, String name, String surname, String phone) {

        Customer customer = new Customer();
        customer.setDni(dni);
        customer.setName(name);
        customer.setSurname(surname);
        customer.setPhone(phone);
        customer.setStatus(true);
        customer.setBusy(false);
        costumerRep.save(customer);
    }

    @Transactional
    public void modifyCustomer(String id, Long dni, String name, String surname, String phone) {

        Customer customer = costumerRep.findById(id).get();
        customer.setDni(dni);
        customer.setName(name);
        customer.setSurname(surname);
        customer.setPhone(phone);
        customer.setStatus(true);
        customer.setBusy(false);
        costumerRep.save(customer);

    }

    @Transactional
    public void changeState(String id, Boolean status) {

        costumerRep.changeStatus(id, status);
    }

    @Transactional(readOnly = true)
    public List<Customer> findAll() {
        return costumerRep.findAll();
    }

    @Transactional(readOnly = true)
    public Customer lookForId(String id) {

        return (costumerRep.findById(id).get());
    }
    
   
    
     @Transactional
    public void changeBussy(String id, Boolean bussy) {
        Customer customer =costumerRep.findById(id).get();
        customer.setBusy(bussy);
        costumerRep.save(customer);
    }
     @Transactional(readOnly = true)
        public List<Customer> notBussy() {
       
        return costumerRep.findAll().stream().filter(customer -> customer.getBusy()== false).collect(Collectors.toList());
    }
   
}
