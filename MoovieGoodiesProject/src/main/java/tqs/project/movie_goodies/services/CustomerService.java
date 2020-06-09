package tqs.project.movie_goodies.services;

import tqs.project.movie_goodies.entities.Customer;
import tqs.project.movie_goodies.repositories.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class CustomerService implements UserDetailsService, Services<Customer> {

    private CustomerRepository customerRepository;

    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer user = findCustomerByEmail(email);
        UserBuilder builder = null;
        if (user != null) {
            builder = org.springframework.security.core.userdetails.User.withUsername(user.getCustomerId() +"_CUSTOMER");
            builder.password(new BCryptPasswordEncoder().encode(user.getPassword()));
            builder.roles("CUSTOMER");
        } else {
            throw new UsernameNotFoundException("User not found.");
        }
        return builder.build();
    }

    public Customer findCustomerByEmail(String email) {
        return customerRepository.findCustomerByEmail(email);
    }

    @Override
    public Iterable<Customer> listAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public long count(){
        return customerRepository.count();
    }
}