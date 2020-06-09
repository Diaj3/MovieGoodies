package tqs.project.movie_goodies.repositories;

import tqs.project.movie_goodies.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
    Customer findCustomerByEmail(String email);
}