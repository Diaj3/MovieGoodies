package tqs.project.movie_goodies.services;

import tqs.project.movie_goodies.entities.ProductCart;
import tqs.project.movie_goodies.repositories.ProductCartRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductCartService implements Services<ProductCart> {

    private ProductCartRepository productCartRepository;

    @Autowired
    public void setProductCartRepository(ProductCartRepository productCartRepository) {
        this.productCartRepository = productCartRepository;
    }

    @Override
    public Iterable<ProductCart> listAll() {
        return productCartRepository.findAll();
    }

    @Override
    public ProductCart getById(Long id) {
        return productCartRepository.findById(id).orElse(null);
    }

    @Override
    public ProductCart save(ProductCart productCart) {
        return productCartRepository.save(productCart);
    }

    @Override
    public long count(){
        return productCartRepository.count();
    }

    public void delete(ProductCart productCart) {
        productCartRepository.delete(productCart);
    }
}