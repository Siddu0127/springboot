package com.example.service;

import com.example.beans.CustomerBean;
import com.example.dao.CustomerDao;
import com.example.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerDao customerDao;


    public List<CustomerBean> getAllIdAndName() {
        return customerDao.getAllIdAndName();
    }

    public List<Customer> getAllCustomer() {

        return customerDao.getAllCustomer();
    }

    public Customer getCustomerById(int id) {

        return customerDao.getCustomerById(id);
    }

    public void updateAll(List<Customer> customer) {
        customerDao.updateAll(customer);

    }

    public void delete(int id) {
        customerDao.delete(id);
    }

    public void update(Customer customer) {
        customerDao.update(customer);

    }

    public void saveCustomer(Customer customer) {
        customerDao.saveCustomer(customer);
    }

    public void saveCSV() throws IOException {
        customerDao.saveCSV();

    }



}
