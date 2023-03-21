package com.example.junit;

import com.example.entity.Customer;
import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.junit.Assert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.Assert.*;


@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CustomerTest {

    @Autowired
    private Repository customerRepository;


    @Test
    @Order(1)
    @Rollback(value = false)
    public  void testSave()
    {
        Customer customer1=new Customer(1,"sid","sod@ds.in","1232387932");
        Customer customer2=new Customer(2,"sid","sod@ds.in","1232387932");

        Customer c=customerRepository.save(customer1);
        customerRepository.save(customer2);
        assertNotNull(c);
        Assertions.assertThat(c.getId()).isGreaterThan(0);




    }
    @Rule
    public ExpectedException expected=ExpectedException.none();

    @Test()
    public void testNameIsNull() {
        expected.expect(NullPointerException.class);
        Customer customer1=new Customer(1,null,null,null);
        customerRepository.save(customer1);
        //  assertNull(customer1);
        assertNull(customer1.getCustomerName());
        assertTrue(customer1.getCustomerEmail()==null);
    }



    @Test
    @Order(2)
    public  void testById()
    {
       /* Customer customer1=new Customer(2,"sid","sod@ds.in","1232387932");
        Customer customer2=new Customer(1
                ,"sid","sod@ds.in","1232387932");
        repositorys.save(customer1);
        repositorys.save(customer2);

        */
        Customer customer=customerRepository.findById(2).get();
        assertThat(customer.getId()).isEqualTo(2);
        assertEquals("sid",customer.getCustomerName());



    }
    @Test
    @Order(3)
    public  void testGetCustomerList()
    {
      /*  Customer customer1=new Customer(2,"sid","sod@ds.in","1232387932");
        Customer customer2=new Customer(1
                ,"sid","sod@ds.in","1232387932");
        repositorys.save(customer1);
        repositorys.save(customer2);

       */
        List<Customer> customers= (List<Customer>) customerRepository.findAll();
        assertThat(customers.size()).isGreaterThan(1);


    }
    @Test
    @Order(4)
    @Rollback(value = false)
    public  void testUpdate() {
       /* Customer customer1 = new Customer(2, "sid", "sod@ds.in", "1232387932");
        Customer customer2 = new Customer(1
                , "sid", "sod@ds.in", "1232387932");
        repositorys.save(customer1);
        repositorys.save(customer2);

        */
        Customer c=customerRepository.findById(1).get();
        c.setCustomerName("siddharth");
        assertThat(c.getCustomerName()).isEqualTo("siddharth");
    }
    @Test
    @Order(5)
    @Rollback(value = false)
    public  void testDelete()
    {
      /*  int id=2;
        Customer customer1=new Customer(2,"sid","sod@ds.in","1232387932");
        Customer customer2=new Customer(1
                ,"sid","sod@ds.in","1232387932");
        repositorys.save(customer1);
        repositorys.save(customer2);

       */
        int id=2;

        boolean exists=customerRepository.findById(id).isPresent();
        customerRepository.deleteById(id);
        boolean notExist=customerRepository.findById(id).isPresent();
        assertTrue(exists);
        assertFalse(notExist);



    }


}

