package com.mycompany.module1.jpa;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author saj
 */
@Entity
@Table(name = "T_Address")
public class Address implements Serializable {

    public Address() {
    }

    public Address(String country, String city, String zip, String street, String streetNumber) {
        this.country = country;
        this.city = city;
        this.zip = zip;
        this.street = street;
        this.streetNumber = streetNumber;
    }

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "country", length = 50)
    private String country;

    @Column(name = "city", length = 100)
    private String city;

    @Column(name = "zip", length = 10)
    private String zip;

    @Column(name = "street", length = 50)
    private String street;

    @Column(name = "streetNumber", length = 50)
    private String streetNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + Objects.hashCode(this.country);
        hash = 43 * hash + Objects.hashCode(this.city);
        hash = 43 * hash + Objects.hashCode(this.zip);
        hash = 43 * hash + Objects.hashCode(this.street);
        hash = 43 * hash + Objects.hashCode(this.streetNumber);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Address other = (Address) obj;
        if (!Objects.equals(this.country, other.country)) {
            return false;
        }
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (!Objects.equals(this.zip, other.zip)) {
            return false;
        }
        if (!Objects.equals(this.street, other.street)) {
            return false;
        }
        if (!Objects.equals(this.streetNumber, other.streetNumber)) {
            return false;
        }
        return true;
    }
}
