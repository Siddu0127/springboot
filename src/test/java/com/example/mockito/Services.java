package com.example.mockito;

import com.example.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;

public class Services {
    @Autowired
    FakeRepository fakeRepository;

    public Services(FakeRepository fakeRepository) {
        this.fakeRepository = fakeRepository;
    }



    public int findNoOfCustomer()
    {
        return fakeRepository.findAll().size();
    }
    public Customer findById(int id)
    {
        return  fakeRepository.findById(id);
    }
}
