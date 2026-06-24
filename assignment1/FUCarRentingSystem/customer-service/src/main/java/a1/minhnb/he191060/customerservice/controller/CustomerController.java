package a1.minhnb.he191060.customerservice.controller;

import a1.minhnb.he191060.customerservice.dto.AuthRequestDTO;
import a1.minhnb.he191060.customerservice.entity.Customer;
import a1.minhnb.he191060.customerservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/verify")
    public ResponseEntity<Boolean> verifyCustomer(@RequestBody AuthRequestDTO request) {
        boolean isValid = customerService.verifyCustomer(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(isValid);
    }

    @PostMapping("/register")
    public ResponseEntity<Customer> register(@RequestBody Customer customer) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.register(customer));
    }

    @GetMapping("/profile")
    public ResponseEntity<Customer> getProfile(@RequestHeader(value = "X-User-Role", required = false) String role, @RequestHeader(value = "X-User-Email", required = false) String email) {
        if (!"CUSTOMER".equals(role)) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        return ResponseEntity.ok(customerService.getCustomerByEmail(email));
    }

    @PutMapping("/profile")
    public ResponseEntity<Customer> updateProfile(@RequestHeader(value = "X-User-Role", required = false) String role, @RequestHeader(value = "X-User-Email", required = false) String email, @RequestBody Customer customer) {
        if (!"CUSTOMER".equals(role)) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        return ResponseEntity.ok(customerService.updateProfile(email, customer));
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers(@RequestHeader(value = "X-User-Role", required = false) String role) {
        if (!"ADMIN".equals(role)) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@RequestHeader(value = "X-User-Role", required = false) String role, @PathVariable Integer id, @RequestBody Customer customer) {
        if (!"ADMIN".equals(role)) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        return ResponseEntity.ok(customerService.updateCustomer(id, customer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@RequestHeader(value = "X-User-Role", required = false) String role, @PathVariable Integer id) {
        if (!"ADMIN".equals(role)) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}
