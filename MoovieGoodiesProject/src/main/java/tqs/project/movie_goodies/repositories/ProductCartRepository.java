package tqs.project.movie_goodies.repositories;

import tqs.project.movie_goodies.entities.ProductCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCartRepository extends JpaRepository<ProductCart, Long>{ }