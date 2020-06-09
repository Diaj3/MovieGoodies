package tqs.project.movie_goodies.services;

import tqs.project.movie_goodies.entities.Cart;
import tqs.project.movie_goodies.repositories.CartRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService implements Services<Cart> {

    private CartRepository cartRepository;

    @Autowired
    public void setCartRepository(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public Iterable<Cart> listAll() {
        return cartRepository.findAll();
    }

    @Override
    public Cart getById(Long id) {
        return cartRepository.findById(id).orElse(null);
    }

    @Override
    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public long count(){
        return cartRepository.count();
    }
}