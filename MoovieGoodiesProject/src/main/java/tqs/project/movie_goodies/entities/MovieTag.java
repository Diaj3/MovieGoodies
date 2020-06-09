package tqs.project.movie_goodies.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class MovieTag {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String tag;

    @ManyToMany(fetch = FetchType.LAZY, cascade=CascadeType.PERSIST)
    @JoinTable(name = "product_movietag",
            joinColumns = {
                    @JoinColumn(name = "product_id", referencedColumnName = "id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "movietag_id", referencedColumnName = "id",
                            nullable = false, updatable = false)})
    private Set<Product> products = new HashSet<>();

    public Long getMovieTagId() {
        return id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

}
