package tqs.project.movie_goodies.services;

import tqs.project.movie_goodies.entities.ProductSale;
import tqs.project.movie_goodies.repositories.ProductSaleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductSaleService implements Services<ProductSale> {

    private ProductSaleRepository productSaleRepository;

    @Autowired
    public void setProductSaleRepository(ProductSaleRepository productSaleRepository) {
        this.productSaleRepository = productSaleRepository;
    }

    @Override
    public Iterable<ProductSale> listAll() {
        return productSaleRepository.findAll();
    }

    @Override
    public ProductSale getById(Long id) {
        return productSaleRepository.findById(id).orElse(null);
    }

    @Override
    public ProductSale save(ProductSale productSale) {
        return productSaleRepository.save(productSale);
    }

    @Override
    public long count(){
        return productSaleRepository.count();
    }
}