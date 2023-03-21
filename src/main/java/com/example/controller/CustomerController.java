package com.example.controller;

import com.example.beans.CustomerBean;
import com.example.entity.Customer;
import com.example.service.CustomerService;
import com.example.utile.CustomResponse;
import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.RedirectView;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController implements WebMvcConfigurer {
    @Autowired
    CustomerService customerService;

    @GetMapping()
    private /* ResponseEntity<CustomResponse>*/ List<Customer> getAllCustomer() {
        List<Customer> customer = customerService.getAllCustomer();
        // return new ResponseEntity<>(new CustomResponse(HttpStatus.OK, "customers details",customer), HttpStatus.OK);
        return customer;
    }

    @GetMapping("/{id}")
    private ResponseEntity<CustomResponse> getCustomer(@PathVariable("id") int id) {

        Customer customer = customerService.getCustomerById(id);
        return new ResponseEntity<>(new CustomResponse(HttpStatus.OK, "customer with id ", customer), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    private String deleteCustomer(@PathVariable("id") int id) {

        customerService.delete(id);
        return "deleted";
    }

    @PostMapping()
    private ResponseEntity<CustomResponse> saveCustomer(@Valid @RequestBody Customer customer) {
        customerService.update(customer);
        return new ResponseEntity<>(new CustomResponse(HttpStatus.OK, "customer details saved", customer), HttpStatus.OK);
    }

    @PostMapping("/multiple")
    private void saveMultiple(@Valid @RequestBody List<Customer> customers) {
        // for(Customer customer : customers) {
        customerService.updateAll(customers);

    }

    @GetMapping("/getAllIdAndName")
    private ResponseEntity<CustomResponse> getAllIdAndName() {
        List<CustomerBean> customer = customerService.getAllIdAndName();
        return new ResponseEntity<>(new CustomResponse(HttpStatus.OK, "Saved", customer), HttpStatus.OK);
    }

    @PostMapping("/readCsv")
    private void saveCsv() throws IOException {
        customerService.saveCSV();
    }

    @PostMapping("/upload")
    private String uploadCsv(@RequestParam("file") MultipartFile file) throws IOException {
        List<Customer> c = new ArrayList<>();
        InputStream inputStream = file.getInputStream();
        CsvParserSettings settings = new CsvParserSettings();
        settings.setHeaderExtractionEnabled(true);
        CsvParser parser = new CsvParser(settings);
        List<Record> parseAllRecords = parser.parseAllRecords(inputStream);
        parseAllRecords.forEach(record -> {
            Customer customerDetails = new Customer(Integer.parseInt(record.getString("id")), record.getString("name"), record.getString("email"), record.getString("phone"));

            c.add(customerDetails);

        });
        customerService.updateAll(c);
        return "upload success";
    }

    @GetMapping("/download")
    private void export(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
      /*  String filename="cust.csv";
        String headerKey="Content-Disposition";
        String headerValue="attachment; filename" +filename;
        response.setHeader(headerKey,headerValue);

       */

        List<Customer> customerList = customerService.getAllCustomer();
        ICsvBeanWriter csvBeanWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"id", "customerName", "customerEmail", "customerPhone"};
        String[] nameMapping = {"id", "customerName", "customerEmail", "customerPhone"};

        csvBeanWriter.writeHeader(csvHeader);

        for (Customer c : customerList) {
            csvBeanWriter.write(c, nameMapping);
        }
        csvBeanWriter.close();
    }

    @GetMapping("/showCustomer")
    public ModelAndView showAllCustomer() {
        ModelAndView mav = new ModelAndView("list-customer");
        List<Customer> list = customerService.getAllCustomer();
        mav.addObject("customers", list);
        return mav;
    }

    @GetMapping("/addCustomer")
    public ModelAndView add() {

        ModelAndView mav = new ModelAndView("add-customer");
        Customer newCustomer = new Customer();
        mav.addObject("customer", newCustomer);
        return mav;


    }

    @PostMapping("/save")

    public RedirectView save(@ModelAttribute("customer") @Valid Customer customer, Errors error, Model model) {
        if (error.hasErrors()) {
            return new RedirectView("addCustomer");


        } else {
            customerService.update(customer);


            return new RedirectView("showCustomer");


        }

    }

    @GetMapping("/updateCustomer")
    public ModelAndView update(@RequestParam int id) {
        ModelAndView mav = new ModelAndView("update-customer");
        Customer customer = customerService.getCustomerById(id);

        mav.addObject("customer", customer);
        return mav;
    }

    @GetMapping("/deleteCustomer")
    public RedirectView delete(@RequestParam int id) {
        ModelAndView mav = new ModelAndView("list-customer");
        customerService.delete(id);
        return new RedirectView("showCustomer");
    }


}