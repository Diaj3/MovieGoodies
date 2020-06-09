package tqs.project.movie_goodies.serviceTests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import tqs.project.movie_goodies.entities.Product;
import tqs.project.movie_goodies.entities.Provider;
import tqs.project.movie_goodies.repositories.ProductRepository;
import tqs.project.movie_goodies.repositories.ProviderRepository;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProviderRepository providerRepository;


    @Test
    public void testCreateSaveProduct() {

        Provider provider = new Provider();
        providerRepository.save(provider);

        Product product = new Product();
        product.setName("Batman Tshirt");
        product.setPrice(13.30);
        product.setDescription("Batman3 movie inspired tshirt");
        product.setQuantity(2);
        product.setImage("urlexample");
        product.setType("tshirt");
        product.setOldPrice(10.00);
        product.setDisplay(true);
        product.setProvider(provider);

        //Services Tests
        productRepository.save(product);
        assertNotNull(product.getProductId());

        Product fetched_product = productRepository.findById(product.getProductId()).orElse(null);
        assertNotNull(fetched_product);

        //Fetched values should be equal
        assertEquals(product.getProductId(), fetched_product.getProductId());
        assertEquals(product.getName(), fetched_product.getName());

        //Check the update
        fetched_product.setName("Batman2 thsirt woth joker weapons");
        productRepository.save(fetched_product);

        //Fetched values should be equal
        Product fetched_product_updated = productRepository.findById(fetched_product.getProductId()).orElse(null);
        assertEquals(fetched_product.getName(), fetched_product_updated.getName());

        //verify count of products in the repository
        long numberOfProducts = productRepository.count();
        //assertEquals(numberOfProducts, 11);

        //get all proucts, with the added one, they should be 10
        //Iterable<Product> products = productRepository.findAll();
        //int count = 0;
        //for(Product p : products){ count++; }
        //assertEquals(count, 11);
    }

}
