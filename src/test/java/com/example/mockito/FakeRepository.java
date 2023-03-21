package com.example.mockito;

import com.example.entity.Customer;

import java.util.Collection;

public interface FakeRepository {
    Collection<Customer> findAll();


    Customer findById(int id);

    Object deleteById(int id);
}
