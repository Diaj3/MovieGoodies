package tqs.project.movie_goodies.entities;

import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

import javax.persistence.*;
import java.util.Set;


@Entity
public class Sale {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    private Boolean deliver = false;
    private String paymentMethod;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Customer.class)
    @JoinColumn(name="customer_id", referencedColumnName = "id", nullable=false)
    private Customer customer;

    @OneToMany(mappedBy="sale", fetch=FetchType.LAZY, cascade=CascadeType.PERSIST)
    private Set<ProductSale> productsSale;

    @CreationTimestamp
    private java.sql.Date saleAt;


    public Long getSaleId() {
        return id;
    }

    public Boolean getDeliver() {
        return deliver;
    }

    public void setDeliver(Boolean deliver) {
        this.deliver = deliver;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Set<ProductSale> getProductsSale() {
        return productsSale;
    }

    public Date getSaleAt() {
        return saleAt;
    }

}
