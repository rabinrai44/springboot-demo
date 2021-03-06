package com.zalostation.springbootdemo.customer;

import com.zalostation.springbootdemo.exception.ApiRequestException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/customers")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @GetMapping("{customerId}")
    public Customer getCustomer(@PathVariable("customerId") Long id) {
        return customerService.getCustomer(id);
    }

    @PostMapping()
    public Customer addNewCustomer(@Valid @RequestBody Customer customer) {
        return customerService.addCustomer(customer);
    }

    /**
     * Update customer
     *
     * @param customer object
     * @return customer object
     */
    @PutMapping
    public Customer updateCustomer(@Valid @RequestBody Customer customer) {
        Customer customer1 = customerService.getCustomer(customer.getId());
        if (customer1 == null) new IllegalStateException("");

        customer1.setEmail(customer.getEmail());
        customer1.setId(customer.getId());
        customer1.setName(customer.getName());
        customer1.setPassword(customer.getPassword());
        return customerService.updateCustomer(customer1);
    }

    @DeleteMapping("{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Long id) {
        var customer = customerService.getCustomer(id);
        if (customer == null) throw new ApiRequestException("Customer id " + id + " is not valid");
        customerService.deleteCustomer(customer);
    }
}
