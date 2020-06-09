package tqs.project.movie_goodies.entities;

import javax.persistence.*;


@Entity
public class ProductCart {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Cart.class)
    @JoinColumn(name="cart_id", referencedColumnName = "id", nullable=false)
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Product.class)
    @JoinColumn(name="product_id", referencedColumnName = "id", nullable=false)
    private Product product;

    private Integer quantity;


    public Long getProductcartId() {
        return id;
    }

    public void setProductcartId(Long id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
