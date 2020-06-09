package tqs.project.movie_goodies.serviceTests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tqs.project.movie_goodies.entities.Customer;
import tqs.project.movie_goodies.entities.Provider;
import tqs.project.movie_goodies.entities.Sale;
import tqs.project.movie_goodies.repositories.CustomerRepository;
import tqs.project.movie_goodies.repositories.ProviderRepository;
import tqs.project.movie_goodies.repositories.SaleRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SaleTest {

    private SaleRepository saleRepository;
    private CustomerRepository customerRepository;
    private ProviderRepository providerRepository;

    @Autowired
    public void setSaleRepository(SaleRepository saleRepository, CustomerRepository customerRepository, ProviderRepository providerRepository) {
        this.saleRepository = saleRepository;
        this.customerRepository = customerRepository;
        this.providerRepository = providerRepository;
    }

    @Test
    public void testSalesServices() {
        Sale sale = new Sale();
        Customer customer = new Customer();
        Provider provider = new Provider();

        customerRepository.save(customer);
        providerRepository.save(provider);
        sale.setCustomer(customer);

        //TODO after Provider complete
    }



}
