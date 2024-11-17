package com.suma.UpwardIQ.controller;


import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.suma.UpwardIQ.model.Customer;
import com.suma.UpwardIQ.service.CustomerService;


@Controller
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	
      @GetMapping("/")
      public String getindexPage() {
    	  return "index";
      }
      @GetMapping("/save")
      public String showCustomerForm(Model model) {
          model.addAttribute("customer", new Customer());
          return "saveCustomer";
      }
      @GetMapping("/checkCustomerId")
      public ResponseEntity<Map<String, Boolean>> checkCustomerId(@RequestParam String customerId) {
          boolean exists = customerService.isCustomerIdUnique(customerId);
          
          System.out.println("-----------------------suma------------------------");
          System.out.println(customerId);
          Map<String, Boolean> response = new HashMap<>();
          response.put("exists", exists);
          return ResponseEntity.ok(response);
      }
            
   
      
      @PostMapping("/saveCustomer")
      public String saveCustomer(@ModelAttribute Customer customer,  Model model) {
           
    	  // Save customer if validation pass
          customerService.saveCustomer(customer);
          model.addAttribute("msg", " Customer inserted successfully");
          return "index"; 
      }
      
      @GetMapping("/search")
      public String showSearchForm(Model model) {
          model.addAttribute("customer", new Customer());
          return "searchPage";
      }
      
      @GetMapping("/searchCustomer")
      public String searchCustomers(@RequestParam(name = "name") String name,
                                     @RequestParam(name = "gender") String gender,
                                     Model model) {

          System.out.println("**********************************************************");
          System.out.println(name + "   " + gender);

          List<Customer> filteredCustomers = customerService.findByCustomerNameAndGender(name, gender);
          
          if (filteredCustomers.isEmpty()) {
              model.addAttribute("searchmessage", "No customers found with the given name and gender.");
          } else {
              model.addAttribute("customers", filteredCustomers);
          }

          return "searchPage"; 
      }
      
      
      @PostMapping("/exportCustomers")
      public String submitCustomers(@RequestParam("id") List<Long> Ids ,Model model) throws IOException {
          System.out.println("after exporting !!!!!!!!!!!!!!!!!!!!!!");
          List<Customer> filteredCustomers = new ArrayList<>();

         
          for (Long id : Ids) {
              Customer c = customerService.findCustomerById(id);
              filteredCustomers.add(c);
          }

          Workbook workbook = new XSSFWorkbook();
          Sheet sheet = workbook.createSheet("Customers");

          
          Row headerRow = sheet.createRow(0);
          headerRow.createCell(0).setCellValue("ID");
          headerRow.createCell(1).setCellValue("Customer ID");
          headerRow.createCell(2).setCellValue("Customer Name");
          headerRow.createCell(3).setCellValue("Gender");
          headerRow.createCell(4).setCellValue("Email");
          headerRow.createCell(5).setCellValue("Customer Type");
          headerRow.createCell(6).setCellValue("Address");

          
          int rowNum = 1;
          for (Customer customer : filteredCustomers) {
              Row row = sheet.createRow(rowNum++);
              row.createCell(0).setCellValue(customer.getId());
              row.createCell(1).setCellValue(customer.getCustomerId());
              row.createCell(2).setCellValue(customer.getName());
              row.createCell(3).setCellValue(customer.getGender());
              row.createCell(4).setCellValue(customer.getEmail());
              row.createCell(5).setCellValue(customer.getCustomerType());
              row.createCell(6).setCellValue(customer.getAddress());
          }

         
          String filePath = "src/main/resources/static/customers.xlsx"; 

         
          try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
              workbook.write(fileOut);
          }
          workbook.close();
          model.addAttribute("excelmsg", " successfully  exported customers");

         
          return "searchPage";
      }

}
