package tqs.project.movie_goodies.repositories;

import tqs.project.movie_goodies.entities.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long>{
    Provider findProviderByEmail(String email);
}