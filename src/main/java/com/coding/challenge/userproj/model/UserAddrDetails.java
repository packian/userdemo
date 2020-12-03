package com.coding.challenge.userproj.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@SuppressWarnings("JpaDataSourceORMInspection")

@Getter
@Setter
@Entity
@Table
public class UserAddrDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int addrid;
    private String street;
    private String city;
    private String state;
    private String postcode;

    @OneToOne(mappedBy = "userAddrDetails", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonBackReference
    private UserDetails userDetails;

    public UserAddrDetails(String street, String city, String state, String postcode) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.postcode = postcode;
    }

    public UserAddrDetails() {
    }
}
