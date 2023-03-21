package com.example.mockito;

import com.example.controller.CustomerController;
import com.example.entity.Customer;
import com.example.repository.CustomerRepository;
import com.example.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import jakarta.ws.rs.core.MediaType;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)

public class CustomerControllerTest {

    private MockMvc mockMvc;
    ObjectMapper objectMapper=new ObjectMapper();
    ObjectWriter objectWrite=objectMapper.writer();

    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private CustomerService customerService;
    @InjectMocks
    private CustomerController customerController;

    Customer customer_1=new Customer(1,"siddu","sid@sjdn.com","6286386232");
    Customer customer_2=new Customer(2,"siddu","sid@sjdn.com","6286386232");
    Customer customer_3=new Customer(3,"siddu","sid@sjdn.com","6286386232");


    @Before
    public  void setup(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc= MockMvcBuilders.standaloneSetup(customerController).build();

    }
    @Test
    public  void getAllCustomer_success() throws  Exception
    {
        List<Customer> records = new ArrayList<>(Arrays.asList(customer_1,customer_2,customer_3));

        Mockito.when(customerService.getAllCustomer()).thenReturn(records);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/customer")
                .content(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(3)))
                .andExpect((ResultMatcher) jsonPath("$[2].customerName", is("siddu")));
           //   .andExpect((ResultMatcher) jsonPath("$[2].customerName", is("siddu")));




    }

}
