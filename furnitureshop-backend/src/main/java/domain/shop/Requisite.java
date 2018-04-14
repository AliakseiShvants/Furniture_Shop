package domain.shop;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * A object that represents full address of customers or manufacturers.
 */
@Component
@Scope("prototype")
@Entity
@Table(name = "REQUISITE")
public class Requisite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REQUISITE_ID")
    private Long id;

    @Column(name = "ZIP")
    private String zip;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "CITY")
    private String city;

    @Column(name = "ADDRESS")
    private String address;

    public Requisite() {
    }

    public Requisite(String zip, String country, String city, String address) {
        this.zip = zip;
        this.country = country;
        this.city = city;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return city + ", " + country;
    }
}
