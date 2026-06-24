package a1.minhnb.he191060.customerservice.service;

import a1.minhnb.he191060.customerservice.entity.Customer;
import a1.minhnb.he191060.customerservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public boolean verifyCustomer(String email, String password) {
        Optional<Customer> customer = customerRepository.findByEmail(email);
        return customer.isPresent() && customer.get().getPassword().equals(password);
    }

    public Customer register(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer updateProfile(String email, Customer updatedCustomer) {
        Customer existing = customerRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Not found"));
        existing.setCustomerName(updatedCustomer.getCustomerName());
        existing.setTelephone(updatedCustomer.getTelephone());
        existing.setCustomerBirthday(updatedCustomer.getCustomerBirthday());
        return customerRepository.save(existing);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public void deleteCustomer(Integer id) {
        customerRepository.deleteById(id);
    }

    public Customer updateCustomer(Integer id, Customer customer) {
        Customer existing = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        existing.setCustomerName(customer.getCustomerName());
        existing.setTelephone(customer.getTelephone());
        existing.setEmail(customer.getEmail());
        existing.setCustomerBirthday(customer.getCustomerBirthday());
        existing.setCustomerStatus(customer.getCustomerStatus());
        return customerRepository.save(existing);
    }

    public Customer getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email).orElse(null);
    }
}
