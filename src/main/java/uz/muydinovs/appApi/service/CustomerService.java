package uz.muydinovs.appApi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.muydinovs.appApi.entity.Customer;
import uz.muydinovs.appApi.payload.ApiResponse;
import uz.muydinovs.appApi.payload.CustomerDto;
import uz.muydinovs.appApi.repositry.CustomerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }


    public Customer getCustomerById(Integer id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        return optionalCustomer.orElse(null);
    }

    public ApiResponse addCustomer(CustomerDto customerDto) {
        boolean existsByPhoneNumber = customerRepository.existsByPhoneNumber(customerDto.getPhoneNumber());
        if (existsByPhoneNumber) {
            return new ApiResponse("This customer is already exists", false);
        } else {
            Customer customer = new Customer();
            customer.setFullName(customerDto.getFullName());
            customer.setPhoneNumber(customerDto.getPhoneNumber());
            customer.setAddress(customerDto.getAddress());
            customerRepository.save(customer);
            return new ApiResponse("Customer added", true);
        }
    }

    public ApiResponse deleteCustomer(Integer id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            customerRepository.deleteById(id);
            return new ApiResponse("Customer deleted", true);
        } else {
            return new ApiResponse("Unknown customer", false);
        }
    }

    public ApiResponse editCustomer(Integer id, CustomerDto customerDto) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            customer.setAddress(customerDto.getAddress());
            customer.setFullName(customerDto.getFullName());
            customer.setPhoneNumber(customerDto.getPhoneNumber());
            customerRepository.save(customer);
            return new ApiResponse("Customer edited", true);
        } else {
            return new ApiResponse("Unknown customer", false);
        }
    }
}
