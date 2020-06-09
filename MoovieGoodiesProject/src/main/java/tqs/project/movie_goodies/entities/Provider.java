package tqs.project.movie_goodies.entities;

import javax.persistence.*;
import java.util.Set;


@Entity
public class Provider {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String contact;
    private String address;

    @OneToMany(mappedBy="provider", fetch=FetchType.LAZY, cascade=CascadeType.PERSIST)
    private Set<Product> products;

    public Long getProviderId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<Product> getProducts() {
        return products;
    }

}
