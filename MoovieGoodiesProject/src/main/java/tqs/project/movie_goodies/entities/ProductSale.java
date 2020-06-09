package tqs.project.movie_goodies.entities;

import javax.persistence.*;


@Entity
public class ProductSale {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    private Boolean deliver = false;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Sale.class)
    @JoinColumn(name="sale_id", referencedColumnName = "id", nullable=false)
    private Sale sale;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Product.class)
    @JoinColumn(name="product_id", referencedColumnName = "id", nullable=false)
    private Product product;

    private Integer quantity;
    private Double price;


    public Long getProductSaleId() {
        return id;
    }

    public Boolean getDeliver() {
        return deliver;
    }

    public void setDeliver(Boolean deliver) {
        this.deliver = deliver;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
