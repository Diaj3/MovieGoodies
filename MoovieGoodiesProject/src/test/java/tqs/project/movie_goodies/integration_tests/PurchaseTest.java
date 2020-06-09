package tqs.project.movie_goodies.integration_tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;
import tqs.project.movie_goodies.controller.CustomerController;
import tqs.project.movie_goodies.entities.Cart;
import tqs.project.movie_goodies.entities.Customer;
import tqs.project.movie_goodies.entities.Product;
import tqs.project.movie_goodies.entities.ProductCart;
import tqs.project.movie_goodies.repositories.CartRepository;
import tqs.project.movie_goodies.repositories.CustomerRepository;
import tqs.project.movie_goodies.repositories.ProductCartRepository;
import tqs.project.movie_goodies.repositories.ProductRepository;

import java.security.Principal;


//TODO All the process behind purchasing an item
@RunWith(SpringRunner.class)
@SpringBootTest
public class PurchaseTest {

    private CartRepository cartRepository;
    private CustomerRepository customerRepository;
    private ProductRepository productRepository;

    private CustomerController customerController;

    @Autowired
    public void setRepository(ProductRepository productRepository, CartRepository cartRepository, ProductCartRepository productCartRepository, CustomerRepository customerRepository) {
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
        this.cartRepository = cartRepository;
    }

    @Test
    public void PurchaseProcess() {
        //Creating entities
        Customer customer = new Customer();
        Product product = new Product();
        Cart cart = new Cart();
        ProductCart productCart = new ProductCart();

        customer.setEmail("customer@gmail.com");
        customer.setPassword("password");

        Principal principal = null;
        Model model = null;

        //given(customerController.profile(customer, principal, model)

        //customerController.profile(customer, principal, model);
        //TODO Check how controller tests work


    }
}
