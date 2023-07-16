package com.example.demo.service;


import com.example.demo.dto.CustomerDTO;
import com.example.demo.entity.customer;
import com.example.demo.exception.CustomerNotFoundException;
import com.example.demo.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // this method return all Customer.
//    @Override
//    public List<CategoryDto> getAllCategories() {
//        List<Category> categories = categoryRepository.findAll();
//        return categories.stream().map(category -> mapToDTO(category))
//                .collect(Collectors.toList());
//    }
//
    public List<CustomerDTO> getAllCustomers() {
        List<customer> customers = customerRepository.findAll();
        return mapCustomersToDTOs(customers);
    }

    public CustomerDTO getCustomerById(Long customerId) {
        customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + customerId));
        return mapCustomerToDTO(customer);
    }

    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        customer customer = mapDTOToCustomer(customerDTO);
        customer savedCustomer ;
        savedCustomer = customerRepository.save(customer);
        return mapCustomerToDTO(savedCustomer);
    }

    public CustomerDTO updateCustomer(Long customerId, CustomerDTO customerDTO) {
        customer existingCustomer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + customerId));

        existingCustomer.setFirstName(customerDTO.getFirstName());
        existingCustomer.setLastName(customerDTO.getLastName());
        existingCustomer.setBornAt(customerDTO.getBornAt());

        customer updatedCustomer = customerRepository.save(existingCustomer);
        return mapCustomerToDTO(updatedCustomer);
    }
    public CustomerDTO partiallyUpdateCustomer(Long customerId, CustomerDTO customerDTO) {
        customer existingCustomer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + customerId));

        if (customerDTO.getFirstName() != null) {
            existingCustomer.setFirstName(customerDTO.getFirstName());
        }
        if (customerDTO.getLastName() != null) {
            existingCustomer.setLastName(customerDTO.getLastName());
        }
        if (customerDTO.getBornAt() != null) {
            existingCustomer.setBornAt(customerDTO.getBornAt());
        }

        customer updatedCustomer = customerRepository.save(existingCustomer);
        return mapCustomerToDTO(updatedCustomer);
    }
    public void deleteCustomer(Long customerId) {
        if (!customerRepository.existsById(customerId)) {
            throw new CustomerNotFoundException("Customer not found with id: " + customerId);
        }
        customerRepository.deleteById(customerId);
    }

    // Helper methods for mapping between DTOs and entities
    private CustomerDTO mapCustomerToDTO(customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setFirstName(customer.getFirstName());
        customerDTO.setLastName(customer.getLastName());
        customerDTO.setBornAt(customer.getBornAt());
        return customerDTO;
    }

    private List<CustomerDTO> mapCustomersToDTOs(List<customer> customers) {
        return customers.stream()
                .map(this::mapCustomerToDTO)
                .collect(Collectors.toList());
    }

    private customer mapDTOToCustomer(CustomerDTO customerDTO) {
        customer customer = new customer();
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setBornAt(customerDTO.getBornAt());
        return customer;
    }
}