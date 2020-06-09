package tqs.project.movie_goodies.loaders;

import tqs.project.movie_goodies.entities.*;
import tqs.project.movie_goodies.repositories.*;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;


@Component
public class ItemLoad implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductCartRepository productCartRepository;

    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private ProductSaleRepository productSaleRepository;

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private MovieTagRepository movieTagRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        Customer customer = new Customer();
        customer.setFirstName("Diogo");
        customer.setLastName("Andrade");
        customer.setEmail("diogo.andrade@ua.pt");
        customer.setPassword("pass");
        customer.setAddress("I live in next door");
        customer.setContact("969696960");
        customerRepository.save(customer);

        Cart cart = new Cart();
        cart.setCustomer(customer);
        cartRepository.save(cart);

        customer.setCart(cart);
        customerRepository.save(customer);

        Provider provider = new Provider();
        provider.setFirstName("João");
        provider.setLastName("Dias");
        provider.setEmail("dias@ua.pt");
        provider.setPassword("pass");
        providerRepository.save(provider);

        Product product8 = new Product();
        product8.setName("Marvel Comic version t-shirt");
        product8.setPrice(22.5);
        product8.setOldPrice(31.99);
        product8.setDescription("Example of Marvel Mug");
        product8.setImage("/images/product8.jpg");
        product8.setQuantity(6);
        product8.setType("clothes");
        product8.setProvider(provider);
        productRepository.save(product8);

        ProductCart productCart = new ProductCart();
        productCart.setQuantity(3);
        productCart.setProduct(product8);
        productCart.setCart(cart);
        productCartRepository.save(productCart);

        Product product9 = new Product();
        product9.setName("Groot Ceramic Mug");
        product9.setPrice(24.99);
        product9.setOldPrice(31.99);
        product9.setDescription("Example of Marvel Mug");
        product9.setImage("/images/product9.jpg");
        product9.setQuantity(6);
        product9.setType("accessory");
        product9.setProvider(provider);
        productRepository.save(product9);

        productCart = new ProductCart();
        productCart.setQuantity(1);
        productCart.setProduct(product9);
        productCart.setCart(cart);
        productCartRepository.save(productCart);

        Sale sale = new Sale();
        sale.setCustomer(customer);
        sale.setPaymentMethod("MBway");
        saleRepository.save(sale);

        ProductSale productSale = new ProductSale();
        productSale.setProduct(product8);
        productSale.setQuantity(3);
        productSale.setPrice(6.0);
        productSale.setSale(sale);
        productSaleRepository.save(productSale);

        productSale = new ProductSale();
        productSale.setProduct(product9);
        productSale.setQuantity(5);
        productSale.setPrice(1.0);
        productSale.setSale(sale);
        productSaleRepository.save(productSale);

        sale = new Sale();
        sale.setCustomer(customer);
        sale.setDeliver(true);
        sale.setPaymentMethod("MBway");
        saleRepository.save(sale);

        productSale = new ProductSale();
        productSale.setProduct(product8);
        productSale.setQuantity(1);
        productSale.setPrice(7.0);
        productSale.setSale(sale);
        productSale.setDeliver(true);
        productSaleRepository.save(productSale);

        Product product = new Product();
        product.setName("Star Wars sweater");
        product.setPrice(45.0);
        product.setDescription("Example of Marvel Mug");
        product.setImage("/images/product1.jpg");
        product.setQuantity(6);
        product.setType("clothes");
        product.setProvider(provider);
        productRepository.save(product);

        Product product2 = new Product();
        product2.setName("BB-8 Figurine");
        product2.setPrice(90.0);
        product2.setDescription("Example of Marvel Mug");
        product2.setImage("/images/product2.jpg");
        product2.setQuantity(6);
        product2.setType("toy");
        product2.setProvider(provider);
        productRepository.save(product2);

        Product product3 = new Product();
        product3.setName("Mandalorian 6'' Black Series Action Figure");
        product3.setPrice(40.0);
        product3.setDescription("Example of Marvel Mug");
        product3.setImage("/images/product3.jpg");
        product3.setQuantity(6);
        product3.setType("toy");
        product3.setProvider(provider);
        productRepository.save(product3);

        Product product4 = new Product();
        product4.setName("Batman Funko Pop figurine");
        product4.setPrice(15.5);
        product4.setDescription("Example of Warner Mug");
        product4.setImage("/images/product4.jpg");
        product4.setQuantity(6);
        product4.setType("toy");
        product4.setProvider(provider);
        productRepository.save(product4);

        Product product5 = new Product();
        product5.setName("Batman mug");
        product5.setPrice(15.0);
        product5.setImage("/images/product5.jpg");
        product5.setQuantity(6);
        product5.setType("accessory");
        product5.setProvider(provider);
        productRepository.save(product5);

        Product product6 = new Product();
        product6.setName("Batman hoodie");
        product6.setPrice(50.0);
        product6.setImage("/images/product6.jpg");
        product6.setQuantity(6);
        product6.setType("clothes");
        product6.setProvider(provider);
        productRepository.save(product6);

        Product product7 = new Product();
        product7.setName("Marvel Comic version t-shirt");
        product7.setPrice(40.0);
        product7.setDescription("Example of Marvel Mug");
        product7.setImage("/images/product7.jpg");
        product7.setQuantity(6);
        product7.setType("clothes");
        product7.setDisplay(false);
        product7.setProvider(provider);
        productRepository.save(product7);

        MovieTag movieTag = new MovieTag();
        movieTag.setTag("MARVEL".toUpperCase());
        movieTagRepository.save(movieTag);

        movieTag.addProduct(product7);
        movieTag.addProduct(product8);
        movieTagRepository.save(movieTag);

        MovieTag movieTag2 = new MovieTag();
        movieTag2.setTag("WARNER".toUpperCase());
        movieTagRepository.save(movieTag2);

        movieTag2.addProduct(product4);
        movieTag2.addProduct(product5);
        movieTag2.addProduct(product6);
        movieTagRepository.save(movieTag2);

        MovieTag movieTag3 = new MovieTag();
        movieTag3.setTag("Batman".toUpperCase());
        movieTagRepository.save(movieTag3);

        movieTag3.addProduct(product4);
        movieTag3.addProduct(product5);
        movieTag3.addProduct(product6);
        movieTagRepository.save(movieTag3);

        MovieTag movieTag4 = new MovieTag();
        movieTag4.setTag("Star wars".toUpperCase());
        movieTagRepository.save(movieTag4);

        movieTag4.addProduct(product);
        movieTag4.addProduct(product2);
        movieTag4.addProduct(product3);
        movieTag4.addProduct(product9);
        movieTagRepository.save(movieTag4);
    }

}