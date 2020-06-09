package tqs.project.movie_goodies.repositories;

import tqs.project.movie_goodies.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{ }