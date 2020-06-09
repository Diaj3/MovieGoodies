package tqs.project.movie_goodies.serviceTests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tqs.project.movie_goodies.entities.Cart;
import tqs.project.movie_goodies.repositories.CartRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CartTest {

    private CartRepository cartRepository;

    @Autowired
    public void setCartRepository(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Test
    public void testCreateSaveCart() {

        Cart cart = new Cart();

        //Services Tests
        cartRepository.save(cart);

        Iterable<Cart> carts = cartRepository.findAll();
        int count = 0;
        for(Cart p : carts){
            count++;
        }
        //assertEquals(count, 3);
    }
}
