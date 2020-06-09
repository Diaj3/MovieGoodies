package tqs.project.movie_goodies.entities;

import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;


@Entity
public class Product {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Provider.class)
    @JoinColumn(name="provider_id", nullable=false)
    private Provider provider;

    private String name;
    private Double price;
    private Double oldPrice;
    private String description;
    private String image;
    private Integer quantity;
    private String type;
    private Boolean display = true;

    @CreationTimestamp
    private Date date;

    @OneToMany(mappedBy="product", fetch=FetchType.LAZY, cascade=CascadeType.PERSIST)
    private Set<ProductSale> productSale;

    @ManyToMany(mappedBy = "products", fetch = FetchType.LAZY, cascade=CascadeType.PERSIST)
    private Set<MovieTag> movieTags = new HashSet<>();


    public Long getProductId() {
        return id;
    }

    public Provider getProvider() {
        return provider;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public Double getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(Double oldPrice) {
        this.oldPrice = oldPrice;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getDisplay() {
        return display;
    }

    public void setDisplay(Boolean display) {
        this.display = display;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Date getDate() {
        return date;
    }

    public Set<MovieTag> getMovieTags() {
        return movieTags;
    }

    public Set<ProductSale> getProductSale() {
        return productSale;
    }

    public void setProductSale(Set<ProductSale> productSale) {
        this.productSale = productSale;
    }

    public void setMovieTags(Set<MovieTag> movieTags) {
        this.movieTags = movieTags;
    }

    public void addMovieTags(MovieTag movieTag) {
        this.movieTags.add(movieTag);
    }

}
