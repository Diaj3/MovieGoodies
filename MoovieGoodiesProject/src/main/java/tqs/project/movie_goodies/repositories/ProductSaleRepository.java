package tqs.project.movie_goodies.repositories;

import tqs.project.movie_goodies.entities.ProductSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSaleRepository extends JpaRepository<ProductSale, Long>{
}