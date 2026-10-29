package rw.auca.salonbookingsystem.service;

import rw.auca.salonbookingsystem.model.Customer;
import rw.auca.salonbookingsystem.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public String save(Customer customer) {
        customerRepository.save(customer);
        return "Customer saved successfully";
    }

    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getById(UUID id) {
        return customerRepository.findById(id);
    }

    public String update(UUID id, Customer customer) {
        if (!customerRepository.existsById(id)) {
            return "The customer with that id does not exist";
        }

        customer.setId(id);
        customerRepository.save(customer);
        return "Customer updated successfully";
    }

    public String delete(UUID id) {
        if (!customerRepository.existsById(id)) {
            return "The customer with that id does not exist";
        }

        customerRepository.deleteById(id);
        return "Customer deleted successfully";
    }

    public List<Customer> getByLastName(String lastName) {
        return customerRepository.findByLastNameIgnoreCaseOrderByFirstNameAsc(lastName);
    }
}