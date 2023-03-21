package com.example.dao;

import com.example.beans.CustomerBean;
import com.example.entity.Customer;
import com.example.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Service
public class CustomerDao {
    @Autowired
    CustomerRepository customerRepository;

    public List<CustomerBean> getAllIdAndName() {
        List<Object[]> customers = customerRepository.getIdAndName();
        List<CustomerBean> customerBeans = new ArrayList<>();
        for (Object[] customer : customers) {
            CustomerBean customerBean = new CustomerBean();
            customerBean.setId(customer[0].toString());
            customerBean.setName(customer[1].toString());
            customerBeans.add(customerBean);
        }

        return customerBeans;
    }
    public List<Customer> getAllCustomer() {
        List<Customer> customers = new ArrayList<Customer>();
        customerRepository.findAll().forEach(customer -> customers.add(customer));
        return customers;
    }
    public Customer getCustomerById(int id) {

        return customerRepository.findById(id).get();
    }

    public void updateAll(List<Customer> customer) {
        for (Customer customer1 : customer) {
            customerRepository.save(customer1);
        }
    }

    public void delete(int id) {
        customerRepository.deleteById(id);
    }

    public void update(Customer customer) {
        customerRepository.save(customer);

    }

    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public void saveCSV() throws IOException {


        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/customer.csv"));
            String line = br.readLine();

            while (true) {

                try {
                    if (!((line = br.readLine()) != null)) break;
                    String[] data = line.split(",");
                    Customer c = new Customer(Integer.parseInt(data[0]), data[1], data[2], data[3]);

                    customerRepository.save(c);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}
