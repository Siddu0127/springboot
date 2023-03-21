package com.example.junit;

import com.example.entity.Customer;
import org.springframework.data.repository.CrudRepository;

@org.springframework.stereotype.Repository
public interface Repository extends CrudRepository<Customer,Integer> {




}
