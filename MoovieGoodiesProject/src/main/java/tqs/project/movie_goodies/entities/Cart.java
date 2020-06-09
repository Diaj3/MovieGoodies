package tqs.project.movie_goodies.entities;

import javax.persistence.*;
import java.util.Set;




@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @OneToOne(mappedBy="cart")
    private Customer customer;

    @OneToMany(mappedBy="cart", fetch=FetchType.LAZY, cascade=CascadeType.PERSIST)
    private Set<ProductCart> productsCart;


    public Long getCartId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Set<ProductCart> getProductsCart() {
        return productsCart;
    }

    public void setProductsCart(Set<ProductCart> productsCart) {
        this.productsCart = productsCart;
    }

}
