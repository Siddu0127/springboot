package com.example.mockito;

import com.example.junit.Repository;
import com.example.entity.Customer;
import com.example.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;
import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class FakeTest {

    @Mock
    FakeRepository fakeRepository;

    @InjectMocks
    Services services;




    @Test
    public void testListOfCustomer(){
       // FakeRepository fakeRepository= Mockito.mock(FakeRepository.class);

      //  Services services1=new Services(fakeRepository);

        Customer customer1=new Customer(2,"sid","sod@ds.in","1232387932");
        Customer customer2=new Customer(1,"sid","sod@ds.in","1232387932");
        Collection<Customer> customers=new ArrayList<>();
        customers.add(customer1);
        customers.add(customer2);
        assertThat(customers.size()).isEqualTo(2);

        Mockito.when(fakeRepository.findAll()).thenReturn(customers);


        assertEquals(2,services.findNoOfCustomer());

        Mockito.verify(fakeRepository).findAll();



    }
    @Test
    public void testById(){

        Customer customer1=new Customer(2,"sid","sod@ds.in","1232387932");
        Customer customer2=new Customer(1,"sid","sod@ds.in","1232387932");
        Collection<Customer> customers=new ArrayList<>();
        customers.add(customer1);
        customers.add(customer2);


        Mockito.when(fakeRepository.findById(1)).thenReturn(customer2);

        assertThat(customer2).isEqualTo(services.findById(1));


        Mockito.verify(fakeRepository).findById(1);



    }
    @Test
    public void testUpdate(){

        Customer customer1=new Customer(2,"sid","sod@ds.in","1232387932");
        Customer customer2=new Customer(1,"sid","sod@ds.in","1232387932");
        Collection<Customer> customers=new ArrayList<>();
        customers.add(customer1);
        customers.add(customer2);


        Mockito.when(fakeRepository.findById(1)).thenReturn(customer2);



        Customer c=services.findById(1);
        c.setCustomerName("siddharth");
        assertThat(c.getCustomerName()).isEqualTo("xdart");



    }
    @Test
    public void testDeleteById(){

        Customer customer1=new Customer(1,"sid","sod@ds.in","1232387932");
        Customer customer2=new Customer(2,"sid","sod@ds.in","1232387932");
        Collection<Customer> customers=new ArrayList<>();
        customers.add(customer1);
        customers.add(customer2);


        Mockito.when(fakeRepository.deleteById(1)).thenReturn(false);

        customers.remove(customer1);

        assertThat(fakeRepository.deleteById(1)).isEqualTo(customers.contains(customer2));





    }

}
