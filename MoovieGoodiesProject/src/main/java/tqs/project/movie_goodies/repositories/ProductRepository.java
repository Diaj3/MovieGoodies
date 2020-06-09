package tqs.project.movie_goodies.repositories;

import tqs.project.movie_goodies.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tqs.project.movie_goodies.entities.Provider;

import java.util.List;
import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Set<Product> findByProvider(Provider provider);
}