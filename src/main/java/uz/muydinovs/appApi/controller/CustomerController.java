package uz.muydinovs.appApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.muydinovs.appApi.entity.Customer;
import uz.muydinovs.appApi.payload.ApiResponse;
import uz.muydinovs.appApi.payload.CustomerDto;
import uz.muydinovs.appApi.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Integer id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping
    public ApiResponse addCustomer(@RequestBody CustomerDto customerDto) {
        return customerService.addCustomer(customerDto);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteCustomer(@PathVariable Integer id){
        return customerService.deleteCustomer(id);
    }

    @PutMapping("/{id}")
    public ApiResponse editCustomer(@PathVariable Integer id, @RequestBody CustomerDto customerDto){
        return customerService.editCustomer(id,customerDto);
    }
}
