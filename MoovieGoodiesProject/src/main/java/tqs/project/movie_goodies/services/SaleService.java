package tqs.project.movie_goodies.services;

import tqs.project.movie_goodies.entities.Sale;
import tqs.project.movie_goodies.repositories.SaleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleService implements Services<Sale> {

    private SaleRepository saleRepository;

    @Autowired
    public void setSaleRepository(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    @Override
    public Iterable<Sale> listAll() {
        return saleRepository.findAll();
    }

    @Override
    public Sale getById(Long id) {
        return saleRepository.findById(id).orElse(null);
    }

    @Override
    public Sale save(Sale sale) {
        return saleRepository.save(sale);
    }

    @Override
    public long count(){
        return saleRepository.count();
    }
}