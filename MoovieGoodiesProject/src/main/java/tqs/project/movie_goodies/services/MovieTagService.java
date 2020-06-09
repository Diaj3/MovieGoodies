package tqs.project.movie_goodies.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tqs.project.movie_goodies.entities.MovieTag;
import tqs.project.movie_goodies.repositories.MovieTagRepository;

@Service
public class MovieTagService implements Services<MovieTag> {

    @Autowired
    private MovieTagRepository movieTagRepository;

    @Override
    public Iterable<MovieTag> listAll() {
        return movieTagRepository.findAll();
    }

    @Override
    public MovieTag getById(Long id) {
        return movieTagRepository.findById(id).orElse(null);
    }

    @Override
    public MovieTag save(MovieTag movieTag) {
        return movieTagRepository.save(movieTag);
    }

    @Override
    public long count() {
        return movieTagRepository.count();
    }

    public MovieTag findByTag(String tag) {
        return movieTagRepository.findByTag(tag.toUpperCase());
    }
}
