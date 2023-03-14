package dev.wcs.nad.tariffmanager.adapter.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dev.wcs.nad.tariffmanager.adapter.rest.dto.customer.AddressDto;
import dev.wcs.nad.tariffmanager.adapter.rest.dto.customer.CreateCustomerDto;
import dev.wcs.nad.tariffmanager.adapter.rest.dto.customer.CustomerDto;
import dev.wcs.nad.tariffmanager.mapper.mapstruct.EntityToDtoMapper;
import dev.wcs.nad.tariffmanager.mapper.simple.CustomerMapper;
import dev.wcs.nad.tariffmanager.persistence.entity.Customer;
import dev.wcs.nad.tariffmanager.service.CustomerService;

@RestController
public class CustomerController extends CustomerMapper{

    private final CustomerService customerService;
    private final EntityToDtoMapper entityToDtoMapper;
    
    @Autowired
    private CustomerMapper customerMapper;

    public CustomerController(CustomerService customerService, EntityToDtoMapper entityToDtoMapper) {
        this.customerService = customerService;
        this.entityToDtoMapper = entityToDtoMapper;
    }

    @GetMapping("/api/customers")
    public List<CustomerDto> displayCustomers() {
        List<CustomerDto> customerDtos = new ArrayList<>();
        for (Customer customer: customerService.readAllCustomers()) {
            customerDtos.add(entityToDtoMapper.customerToCustomerDto(customer));
        }
        return customerDtos;
    }

    @PostMapping("/api/customers")
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CreateCustomerDto createCustomerDto) {
        CustomerDto customerDto = entityToDtoMapper.customerToCustomerDto(customerService.createCustomer(entityToDtoMapper.createCustomerDtoToCustomer(createCustomerDto)));
        return ResponseEntity.ok(customerDto);
    }

    @PutMapping("/api/customers/{id}")
    public ResponseEntity<CustomerDto> assignAddress(@PathVariable("id") Long customerId, @RequestBody AddressDto addressDto) {
        Customer customerEntity = customerService.assignAddress(customerId, entityToDtoMapper.mapAddressDto(addressDto));
        return ResponseEntity.ok(entityToDtoMapper.customerToCustomerDto(customerEntity));
    }
}
