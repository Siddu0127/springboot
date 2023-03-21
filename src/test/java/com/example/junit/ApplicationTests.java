package com.example.junit;

import com.example.entity.Customer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests {
	@LocalServerPort
	private  int port;

	private String baseUrl="http://localhost";

	private static RestTemplate restTemplate;
	@Autowired
	private Repository repo;

	@BeforeAll
	public static void init()
	{
		restTemplate =new RestTemplate();
	}
	@BeforeEach
	public  void setUp()
	{
		baseUrl=baseUrl.concat(":").concat(port+":").concat("/customer");
	}

	@Test
	public void testAddCustomer(){
		Customer customer=new Customer(1,"sid","sid@hs.in","9292393943");
		Customer response=restTemplate.postForObject(baseUrl,customer, Customer.class);
		assertEquals("sid",response.getCustomerName());
	//	assertEquals(1,repo.findAll().size());
	}


}
