package tqs.project.movie_goodies.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tqs.project.movie_goodies.entities.MovieTag;


@Repository
public interface MovieTagRepository extends JpaRepository<MovieTag, Long> {
    MovieTag findByTag (String tag);
}