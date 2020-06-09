package tqs.project.movie_goodies.services;


public interface Services<K> {
    Iterable<K> listAll();
    K getById(Long id);
    K save(K k);
    long count();
}