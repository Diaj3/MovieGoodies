package tqs.project.movie_goodies.serviceTests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tqs.project.movie_goodies.entities.Cart;
import tqs.project.movie_goodies.entities.Product;
import tqs.project.movie_goodies.entities.ProductCart;
import tqs.project.movie_goodies.entities.Provider;
import tqs.project.movie_goodies.repositories.CartRepository;
import tqs.project.movie_goodies.repositories.ProductCartRepository;
import tqs.project.movie_goodies.repositories.ProductRepository;
import tqs.project.movie_goodies.repositories.ProviderRepository;

import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCartTest {

    private ProductCartRepository productCartRepository;
    private ProductRepository productRepository;
    private CartRepository cartRepository;
    private ProviderRepository providerRepository;

    @Autowired
    public void setProductCartRepository(ProductCartRepository productCartRepository) {
        this.productCartRepository = productCartRepository;
    }

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    public void setProviderRepository(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    @Autowired
    public void setCartRepository(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Test
    public void testCreateSaveProductCart() {

        ProductCart productCart = new ProductCart();

        //TODO Check this later (PRODUCT ID == NULL)
        Provider provider = new Provider();
        provider.setFirstName("Diogo");
        provider.setLastName("Andrade");
        provider.setEmail("diogo.andrade@ua.pt");
        provider.setPassword("pass");
        providerRepository.save(provider);

        Product product = new Product();
        product.setName("Star Wars sweater");
        product.setPrice(45.0);
        product.setDescription("Example of Marvel Mug");
        product.setImage("/images/product1.jpg");
        product.setQuantity(5);
        product.setType("clothes");
        product.setProvider(provider);

        System.out.println("PRODUCT: " + product);
        productRepository.save(product);
        Cart cart = new Cart();
        cartRepository.save(cart);

        productCart.setProduct(product);
        productCart.setQuantity(product.getQuantity());
        productCart.setCart(cart);

        productCartRepository.save(productCart);
        assertNotNull(productCart.getProductcartId());
        assertNotNull(productCart.getCart());

        //ProductCart quantity is the same as the product quantity
        //assertEquals(productCart.getQuantity(), product.getQuantity());
    }
}
