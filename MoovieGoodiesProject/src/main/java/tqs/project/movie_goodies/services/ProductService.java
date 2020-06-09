package tqs.project.movie_goodies.services;

import tqs.project.movie_goodies.entities.Product;
import tqs.project.movie_goodies.entities.Provider;
import tqs.project.movie_goodies.repositories.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ProductService implements Services<Product> {

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Iterable<Product> listAll() {
        return productRepository.findAll();
    }

    @Override
    public Product getById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public long count(){
        return productRepository.count();
    }

    public Set<Product> findByProvider(Provider provider) {
        return productRepository.findByProvider(provider);
    }

}