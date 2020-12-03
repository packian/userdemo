package com.coding.challenge.userproj.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@SuppressWarnings("JpaDataSourceORMInspection")
@Getter
@Setter
@Entity
@Table
public class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int empid;
    private String title;
    private String firstn;
    private String lastname;
    private String gender;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_addrid")
    @JsonManagedReference
    private UserAddrDetails userAddrDetails;

    public UserDetails(String title, String firstn, String lastname, String gender, UserAddrDetails userAddrDetails) {
        this.title = title;
        this.firstn = firstn;
        this.lastname = lastname;
        this.gender = gender;
        this.userAddrDetails = userAddrDetails;
        this.userAddrDetails.setUserDetails(this);
    }

    public UserDetails() {
    }
}