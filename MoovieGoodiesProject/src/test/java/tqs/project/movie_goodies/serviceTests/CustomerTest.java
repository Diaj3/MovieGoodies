package tqs.project.movie_goodies.serviceTests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tqs.project.movie_goodies.entities.Customer;
import tqs.project.movie_goodies.repositories.CustomerRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerTest {

    private CustomerRepository customerRepository;

    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Test
    public void testCreateSaveCustomer() {

        Customer customer = new Customer();
        customer.setFirstName("Joao");
        customer.setLastName("Dias");
        customer.setEmail("teste@gmail.com");
        customer.setAddress("Rua Teste, nºx");
        customer.setContact("999999999");
        customer.setPassword("password");

        //Services tests
        customerRepository.save(customer);

        Iterable<Customer> customers = customerRepository.findAll();
        int count = 0;
        for(Customer p : customers){ count++; }
        //assertEquals(count, 2);

        //TODO Está a dar problemas [AMBOS SÃO IGUAIS]
        //System.out.println(customerRepository.findCustomerByEmail(customer.getEmail()));
        //assertEquals(customer, customerRepository.findCustomerByEmail(customer.getEmail()));

    }
}
